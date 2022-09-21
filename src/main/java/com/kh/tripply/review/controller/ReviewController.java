package com.kh.tripply.review.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.member.domain.Member;
import com.kh.tripply.review.domain.Review;
import com.kh.tripply.review.domain.ReviewReply;
import com.kh.tripply.review.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	ReviewService rService;

	/**
	 * 후기 게시판 목록 페이지 출력
	 * 
	 * @param mv,page
	 * @return mv.addObject : rList, paging / mv.setViewName("review/reviewList")
	 */
	@RequestMapping(value = "/review/list.kh", method = RequestMethod.GET)
	public ModelAndView reviewListView(ModelAndView mv,
			@RequestParam(value = "currentPage", required = false) Integer page) {
		int currentPage = (page != null) ? page : 1;
//		게시물의 총 개수, 현재페이지, 페이지 당 게시물 개수, 페이징네비 사이즈
		Paging paging = new Paging(rService.getTotalCount(), currentPage, 9, 5);
		try {
			List<Review> rList = rService.printAllReview(paging);
			if (!rList.isEmpty()) {
				mv.addObject("rList", rList).addObject("paging", paging)
				.setViewName("review/reviewList");
			} else {

			}
		} catch (Exception e) {
		}
		mv.addObject("urlVal","list");
		return mv;
	}
	
	/**
	 * 후기게시판 검색 결과 페이지 출력
	 * @param mv
	 * @param search
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/review/search.kh",method=RequestMethod.GET)
	public ModelAndView reviewSerchVIew(ModelAndView mv,
			@ModelAttribute Search search,
			@RequestParam(value="currentPage",required=false)Integer page) {
		int currentPage = (page != null)? page : 1;
		Paging paging = new Paging(rService.getSearchCount(search),currentPage,9,5);
		try {
			List<Review> rList = rService.printSearchReview(search,paging);
			if(!rList.isEmpty()) {
				mv.addObject("rList",rList).addObject("search",search).addObject("paging",paging)
				.setViewName("review/reviewList");
			}else {
				
			}
		} catch (Exception e) {
		}
		mv.addObject("urlVal","search");
		return mv;
	}

	/**
	 * 후기 게시물 작성 페이지 이동
	 * 
	 * @return String: "/review/reviewWrite"
	 */
	@RequestMapping(value = "/review/writeView.kh", method = RequestMethod.GET)
	public String reviewWriteView(HttpSession session) {
//로그인 체크 구현 필요	
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser != null) {
			return "/review/reviewWrite";
		}
		return "redirect:/review/list.kh";
	}

	/**
	 * 후기 게시물 저장
	 * 
	 * @param mv
	 * @return mv.seViewName("/review/reviewList")
	 */
	@RequestMapping(value = "/review/write.kh", method = RequestMethod.POST)
	public ModelAndView reviewWrite(ModelAndView mv, @ModelAttribute Review review) {

		int result = rService.registerReview(review);
		try {
			if (result > 0) {
				mv.setViewName("redirect:/review/list.kh?currnentPage=1");
			} else {
				mv.addObject("msg", "게시물 저장에 실패하였습니다.").setViewName("/common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("/common/errorPage");
		}
		return mv;
	}

	/**
	 * 후기 게시물 상세 페이지 이동
	 * 댓글 기능 추가
	 * @param mv,boardNo
	 * @return
	 */
	@RequestMapping(value = "/review/detailView.kh", method = RequestMethod.GET)
	public ModelAndView reviewDetailView(ModelAndView mv, 
			@RequestParam("boardNo") Integer boardNo,
			@RequestParam("currentPage") Integer currentPage,
			HttpSession session) {
//로그인 체크 구현 필요
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
			mv.setViewName("redirect:/review/list.kh");
			return mv;
		}
		
		try {
//수정이나 삭제 후 게시판의 기본 페이지로 돌아가기 위해 session에 저장
			session.setAttribute("currentPage", currentPage);
			Review review = rService.printOneReviewByNo(boardNo);
//댓글 출력
			
			List<ReviewReply> rReplyList = rService.printReviewReplyByNo(boardNo);
//조회수 올리기			
			
			int dupleCheck;
			if(session.getAttribute("preventDuplication")==null) {
				dupleCheck=-1;
			}else {
				dupleCheck=(int)session.getAttribute("preventDuplication");
			}
			if(dupleCheck != boardNo) {
				int result = rService.reviewViewCount(boardNo);
				session.setAttribute("preventDuplication",boardNo);
			}
			
			if(!rReplyList.isEmpty()) {
				mv.addObject("rReplyList", rReplyList);
			}else {
				mv.addObject("rReplyList",null);
			}
			if (review != null) {
				mv.addObject("review", review).
				setViewName("/review/reviewDetail");
			} else {
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mv;
	}

	/**
	 * 후기 게시물 삭제
	 * 
	 * @param mv,boardNo,review,session
	 * @return mv
	 */
	@RequestMapping(value = "/review/remove.kh", method = RequestMethod.GET)
	public ModelAndView reviewDelete(ModelAndView mv, 
			@RequestParam("boardNo") Integer boardNo, 
			Review review,
			HttpSession session) {
//로그인 유저와(세션) 작성자 체크 필요 or 화면에서 체크.
		try {
//		String loginUser = (String)session.getAttribute("loginUser");
			Member loginUser = (Member) session.getAttribute("loginUser");
			review.setReviewWriter(loginUser.getMemberId());
			review.setBoardNo(boardNo);
			int result = rService.removeReviewByNo(review);
			if (result > 0) {
//삭제가 성공하면 session에 저장했던 currentPage를 불러와 쿼리스트링으로 사용하고 session은 비운다.
				int currentPage = (int) session.getAttribute("currentPage");
				mv.setViewName("redirect:/review/list.kh?currentPage="+currentPage);
				session.removeAttribute("currentPage");
			} else {

			}
		} catch (Exception e) {
		}
		return mv;
	}

	/**
	 * 후기게시판 수정 화면 이동
	 * 
	 * @param mv
	 * @param boardNo
	 * @return
	 */
	@RequestMapping(value = "/review/modifyView.kh", method = RequestMethod.GET)
	public ModelAndView reviewModifyView(ModelAndView mv, 
			@RequestParam("boardNo") Integer boardNo) {

		Review review = rService.printOneReviewByNo(boardNo);
		if (review != null) {
			mv.addObject("review", review).setViewName("review/reviewModify");
		} else {
		}
		return mv;
	}
	
	/**
	 * 후기게시판 수정 로직
	 * @param mv
	 * @param review
	 * @return
	 */
	@RequestMapping(value="/review/modify.kh",method=RequestMethod.POST)
	public ModelAndView reviewModify(ModelAndView mv,
			@ModelAttribute Review review,
			HttpSession session) {
		int result = rService.modifyReviewByNo(review);
		if(result>0) {
			int currentPage = (int)session.getAttribute("currentPage");
			mv.setViewName("redirect:/review/list.kh?currentPage="+currentPage);
			session.removeAttribute("currentPage");
		}else {
			
		}
		return mv;
	}

	/**
	 * 썸대노트 ajax 매핑 메소드+1 에디터 업로드 이미지 저장 및 파일 경로 json반환
	 * 
	 * @param multipartFile
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/review/uploadSummernoteImageFile", method = RequestMethod.POST)
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) {
		JsonObject jsonObject = new JsonObject();
		try {
			//에디터에서 업로드한 file을 MultipartFile로 받았다.
			
			//1.파일 이름과 경로를 설정한다.
			String originalFileName = multipartFile.getOriginalFilename();
			String root = request.getSession().getServletContext().getRealPath("resources");
			String savePath = root + "\\image\\review\\summerImageFiles";
			
			//2.파일이름이 중복되지 않도록 재정의 해준다.
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String boardFileRename = sdf.format(new Date(System.currentTimeMillis())) + "." + extension;
			
			//3.저장할 경로의 폴더(디렉토리)가 없으면 새로 만든다.
			File targetFile = new File(savePath);
			if (!targetFile.exists()) {
				targetFile.mkdir();
			}
			
			//4.설정한경로에 재정의한 이름으로 파일을 저장한다.
			multipartFile.transferTo(new File(savePath + "\\" + boardFileRename));
			
			//5.ajax의 success로 리턴해줄 json오브젝트에 프로퍼티를 저장해준다.
			// 1)썸머노트의 insertImage 설정값에 넣어줄 파일의 경로.
			// 2)원래 파일이름
			// 3)ajax 성공여부
			jsonObject.addProperty("url", "/resources/image/review/summerImageFiles/" + boardFileRename);
			jsonObject.addProperty("originName", originalFileName);
			jsonObject.addProperty("responseCode", "success");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonObject;
	}
	
/////////////////////댓글기능///////////////////	
	
	/**
	 * 댓글 등록
	 * @param mv
	 * @param currentPage
	 * @param rReply
	 * @return
	 */
	@RequestMapping(value="/review/reply/write.kh",method=RequestMethod.POST)
	public ModelAndView reviewReplyWrite(ModelAndView mv,
			@RequestParam("currentPage") Integer currentPage,
			@ModelAttribute ReviewReply rReply) {
		int boardNo = rReply.getBoardNo();
		int result = rService.registerReviewReply(rReply);
		if(result>0 ) {
			mv.setViewName("redirect:/review/detailView.kh?currentPage="+currentPage+"&boardNo="+boardNo);
		}else {
		}
		return mv;
	}
	
	

}
