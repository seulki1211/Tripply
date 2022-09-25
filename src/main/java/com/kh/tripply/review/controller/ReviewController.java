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
			@RequestParam(value = "page", required = false) Integer currentPage) {
		
		//1.page를 null체크한다.
		int page = (currentPage != null) ? currentPage : 1;
		
		//2.페이징에 필요한 Paging객체를 생성한다. Paging객체는 화면과 RowBounds에 필요한 값을 get할 수 있다.
		Paging paging = new Paging(rService.getTotalCount(), page, 9, 5);
		
		try {
			//3.후기게시판 목록 List를 SELECT 한다.
			List<Review> rList = rService.printAllReview(paging);
			if (!rList.isEmpty()) {
				mv.addObject("rList", rList).addObject("paging", paging)
				.setViewName("review/reviewList");
			} else {

			}
		} catch (Exception e) {
		}
		//4.tradeList.jsp의 페이지네비 링크에서 사용할 url을 동적으로 변경해주기 위한 부분이다.
		// 검색 결과 조회와 구분을 위하여 'list'문자열을 화면에 전달한다.
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
	public ModelAndView reviewSearchView(ModelAndView mv,
			@ModelAttribute Search search,
			@RequestParam(value="page",required=false)Integer currentPage) {
		
		//1.page null체크한다.
		int page = (currentPage != null)? currentPage : 1;
		
		//2.페이징에 필요한 Paging객체를 생성한다. Paging객체는 화면과 RowBounds에 필요한 값을 get할 수 있다.
		Paging paging = new Paging(rService.getSearchCount(search),page,9,5);
		
		try {
			//3.후기게시판 검색결과 List를 SELECT 한다.
			List<Review> rList = rService.printSearchReview(search,paging);
			if(!rList.isEmpty()) {
				mv.addObject("rList",rList).addObject("search",search).addObject("paging",paging)
				.setViewName("review/reviewList");
			}else {
				
			}
		} catch (Exception e) {
		}
		
		//4.reviewList.jsp의 페이지네비 링크에서 사용할 url을 동적으로 변경해주기 위한 부분이다.
		// 검색 결과 조회와 구분을 위하여 'search'문자열을 화면에 전달한다.
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
		//1.로그인 여부를 확인하고 로그인하지 않은 경우 list로 리다이렉트한다.(String반환)
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
	public ModelAndView reviewWrite(ModelAndView mv, 
			@ModelAttribute Review review) {
		
		//1. 작성페이지에서 전달받은 review로 INSERT한다.
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
	 * 후기 게시물 상세 조회
	 * 댓글 기능 추가
	 * @param mv,boardNo
	 * @return
	 */
	@RequestMapping(value = "/review/detail.kh", method = RequestMethod.GET)
	public ModelAndView reviewDetailView(ModelAndView mv, 
			@RequestParam("boardNo") Integer boardNo,
			@RequestParam("page") Integer page,
			HttpSession session) {
		
		//1.로그인 여부를 확인하고 로그인하지 않은 경우 list로 리다이렉트한다.
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
			mv.setViewName("redirect:/review/list.kh");
			return mv;
		}
		
		try {
			//2.수정이나 삭제 후 게시판의 원래 페이지로 돌아가기 위해 session에 page를 저장한다.
			session.setAttribute("page", page);
			
			//3.세션을 이용하여 중복카운팅을 방지하며 조회수를 UPDATE한다.	
			int dupleCheck;
			if(session.getAttribute("preventDuplication") == null) {
				dupleCheck=-1; //boardNo와 겹칠 수 없는 숫자 -1로 설정.
			}else {
				dupleCheck=(int)session.getAttribute("preventDuplication");
			}
			if(dupleCheck != boardNo) {
				int result = rService.reviewViewCount(boardNo);
				session.setAttribute("preventDuplication",boardNo);
			}
			
			//4.해당 글의 댓글 List를 SELECT한다. 댓글이 없으면 null처리한다.
			List<ReviewReply> rReplyList = rService.printReviewReplyByNo(boardNo);
			if(!rReplyList.isEmpty()) {
				mv.addObject("rReplyList", rReplyList);
			}else {
				mv.addObject("rReplyList",null);
			}
			
			//5.boardNo에 해당하는 Review 데이터를 SELECT한다.
			Review review = rService.printOneReviewByNo(boardNo);
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
	 * 후기게시판 수정페이지 이동
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
	 * 후기게시판 수정
	 * @param mv
	 * @param review
	 * @return
	 */
	@RequestMapping(value="/review/modify.kh",method=RequestMethod.POST)
	public ModelAndView reviewModify(ModelAndView mv,
			@ModelAttribute Review review,
			HttpSession session) {
	    //화면에서 로그인유저 == 작성자 인 경우 수정 버튼이 노출된다.
		
		//1.수정페이지에서 넘겨받은 review로 UPDATE한다.
		int result = rService.modifyReviewByNo(review);
		if(result>0) {
			
			//2. 수정 후 원래의 페이지로 돌아가기 위해 세션에서 page를 꺼낸다.
			//사용한 세션을 삭제해준다.
			int page = (int)session.getAttribute("page");
			mv.setViewName("redirect:/review/list.kh?page="+page);
			session.removeAttribute("page");
		}else {
			
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
			Review review,  //데이터 전달용으로 review 객체를 주입한다.
			HttpSession session) {
		//화면에서 로그인유저 == 작성자 인 경우 삭제 버튼이 노출된다.
		
		try {
			
			//1. 로그인 유저의 ID를 가져와 전달용 review객체에 set한다.
			// boardNo도 전달용 review객체에 set한다.
			Member loginUser = (Member) session.getAttribute("loginUser");
			review.setReviewWriter(loginUser.getMemberId());
			review.setBoardNo(boardNo);
			
			//2.DELETE한다.
			int result = rService.removeReviewByNo(review);
			if (result > 0) {
				
				//3.삭제가 성공하면 세션에서 원래 페이지 값을 꺼내고 리다이렉트 시 전달값으로 사용한다.
				//사용한 세션은 제거한다.
				int page = (int) session.getAttribute("page");
				mv.setViewName("redirect:/review/list.kh?page="+page);
				session.removeAttribute("page");
			} else {

			}
		} catch (Exception e) {
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
	 * @param page
	 * @param rReply
	 * @return
	 */
	@RequestMapping(value="/review/reply/write.kh",method=RequestMethod.POST)
	public ModelAndView reviewReplyWrite(ModelAndView mv,
			@RequestParam("page") Integer page,
			@ModelAttribute ReviewReply rReply) {

		//1.댓글작성form에서 가져온 rReply를 INSERT해준다.
		int result = rService.registerReviewReply(rReply);
		if(result>0 ) {
			
			//2.등록 성공 시 파라미터 값을 전달하면서 상세페이지로 리다이렉트한다.
			int boardNo = rReply.getBoardNo();
			mv.setViewName("redirect:/review/detail.kh?page="+page+"&boardNo="+boardNo);
		}else {
		}
		return mv;
	}
	
	
	/**
	 * 댓글 수정 기능
	 * @param mv
	 * @param tReply
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/review/reply/modify.kh",method=RequestMethod.POST)
	public ModelAndView tradeReplyModify(ModelAndView mv,
			@ModelAttribute ReviewReply rReply,
			@RequestParam("page") Integer page) {
		
		//1. UPDATE문을 이용하여 게시물의 내용을 변경한다.
		int result = rService.modifyReviewReply(rReply);
		if(result>0) {
			
			//2.로직 성공 후 현재의 상세페이지로 리다이렉트한다.
			int boardNo = rReply.getBoardNo();
			mv.setViewName("redirect:/review/detail.kh?page="+page+"&boardNo="+boardNo);
		}else {
			
		}
		return mv;
	}
	
	
	/**
	 * 댓글 삭제
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/review/reply/remove.kh",method=RequestMethod.POST)
	public ModelAndView reviewReplyRemove(ModelAndView mv,
			@ModelAttribute ReviewReply rReply,
			@RequestParam("page") Integer page) {
		
		//1. UPDATE문을 이용하여 게시물의 내용과 상태를 변경한다.
		int result = rService.removeReviewReply(rReply);
		if(result>0) {
			
		//2.로직 성공 후 현재의 상세페이지로 리다이렉트한다.
		int boardNo = rReply.getBoardNo();
		mv.setViewName("redirect:/review/detail.kh?page="+page+"&boardNo="+boardNo);
		}else {
			
		}
		return mv;
	}
	
	

}
