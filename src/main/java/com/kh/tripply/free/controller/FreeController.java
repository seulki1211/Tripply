package com.kh.tripply.free.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.free.domain.Free;
import com.kh.tripply.free.domain.FreeReply;
import com.kh.tripply.free.service.FreeService;
import com.kh.tripply.member.domain.Member;

@Controller
public class FreeController {
	@Autowired
	private FreeService fService;

	// 게시글 등록 화면
	@RequestMapping(value = "/free/writeView.kh", method = RequestMethod.GET)
	public String showFreeWrite() {
		return "free/freeWriteForm";
	}

	/**
	 * 게시글 등록
	 * 
	 * @param mv
	 * @param free
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/free/register.kh", method = RequestMethod.POST)
	public ModelAndView registBoard(ModelAndView mv, @ModelAttribute Free free, HttpServletRequest request) {
		try {
			int result = fService.registerBoard(free);
			mv.setViewName("redirect:/free/list.kh");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping(value = "/free/modifyView.kh", method = RequestMethod.GET)
	public ModelAndView freeModifyView(ModelAndView mv, @RequestParam("boardNo") Integer boardNo,
			@RequestParam("page") int page) {
		try {
			Free free = fService.printOneByNo(boardNo);
			mv.addObject("free", free);
			mv.addObject("page", page);
			mv.setViewName("free/freeModifyForm");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	/**
	 * 게시글 수정
	 * 
	 * @param board
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/free/modify.kh", method = RequestMethod.POST)
	public ModelAndView freeModify(@ModelAttribute Free free, ModelAndView mv,
			@RequestParam(value = "reloadFile", required = false) MultipartFile reloadFile,
			@RequestParam("page") Integer page, HttpServletRequest request) {
		try {
			if (reloadFile != null) {
				String freeFilename = reloadFile.getOriginalFilename();
				// 수정, 1. 대체(replace) / 2. 삭제 후 저장
				// 파일삭제
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savedPath = root + "\\buploadFiles";
				File file = new File(savedPath + "\\" + free.getFreeFileRename());
				if (file.exists()) {
					file.delete();
				}
				// 파일 다시 저장
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String freeFileRename = sdf.format(new Date(System.currentTimeMillis())) + "."
						+ freeFilename.substring(freeFilename.lastIndexOf(".") + 1);
				String freeFilepath = savedPath + "\\" + freeFileRename;
				reloadFile.transferTo(new File(freeFilepath));
				free.setFreeFilename(freeFilename);
				free.setFreeFileRename(freeFileRename);
			}
			int result = fService.modifyBoard(free);
			mv.addObject("free", free);
//			mv.setViewName("redirect:/free/detail.kh?boardNo=" + free.getBoardNo() + "&page=" + page);
			mv.setViewName("redirect:/free/list.kh?page=" + page);
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/free/remove.kh", method = RequestMethod.GET)
	public String freeRemove(HttpSession session, Model model, @RequestParam("page") Integer page) {
		try {
			int boardNo = (int) session.getAttribute("boardNo");
			int result = fService.removeOneByNo(boardNo);
			if (result > 0) {
				session.removeAttribute("boardNo");
			}
			return "redirect:/free/list.kh?page=" + page;
		} catch (Exception e) {
			model.addAttribute("msg", e.toString());
			return "common/errorPage";
		}
	}

	/**
	 * 게시글 전체 조회
	 * 
	 * @param mv
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/free/list.kh", method = RequestMethod.GET)
	public ModelAndView boardListView(ModelAndView mv, @RequestParam(value = "page", required = false) Integer page) {
		/////////////////////////////////////////////////////////////////////////
		int currentPage = (page != null) ? page : 1;
		int totalCount = fService.getTotalCount("", "");
		int boardLimit = 10;
		int naviLimit = 5;
		int maxPage;
		int startNavi;
		int endNavi;
		// 23/5 = 4.8 + 0.9 = 5(.7)
		maxPage = (int) ((double) totalCount / boardLimit + 0.9);
		startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
		endNavi = startNavi + naviLimit - 1;
		if (maxPage < endNavi) {
			endNavi = maxPage;
		}
		//////////////////////////////////////////////////////////////////////////
		// /board/list.kh?page=${currentPage }
		List<Free> fList = fService.printAllBoard(currentPage, boardLimit);
		if (!fList.isEmpty()) {
			mv.addObject("urlVal", "list");
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("fList", fList);
		}
		mv.setViewName("/free/freeListView");
		return mv;
	}

	/**
	 * 게시글 상세 조회
	 * 
	 * @param mv
	 * @param boardNo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/free/detail.kh", method = RequestMethod.GET)
	public ModelAndView freeDetailView(ModelAndView mv, @RequestParam("boardNo") Integer boardNo,
			@RequestParam("page") Integer page, HttpSession session) {
		try {
			Free free = fService.printOneByNo(boardNo);
			List<FreeReply> fRList = fService.printAllReply(boardNo);
			// 댓글조회하기 위해 댓글모음을 List로 받아서 detailView.jsp에서 가져옴
			// 이름 오타 안나게 조심할 것!
			session.setAttribute("boardNo", free.getBoardNo());
			// 세션에 boardNo 저장 -> 삭제하기 위해서
			mv.addObject("fRList", fRList);
			mv.addObject("free", free);
			mv.addObject("page", page);
			mv.setViewName("free/freeDetailView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	/**
	 * 게시글 조건 검색
	 * 
	 * @param mv
	 * @param searchCondition
	 * @param searchValue
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/free/search.kh", method = RequestMethod.GET)
	public ModelAndView boardSearchList(ModelAndView mv, @RequestParam("searchCondition") String searchCondition,
			@RequestParam("searchValue") String searchValue,
			@RequestParam(value = "page", required = false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = fService.getTotalCount(searchCondition, searchValue);
			int boardLimit = 10;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;
			maxPage = (int) ((double) totalCount / boardLimit + 0.9);
			startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
			endNavi = startNavi + naviLimit - 1;
			if (maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<Free> fList = fService.printAllByValue(searchCondition, searchValue, currentPage, boardLimit);
			if (!fList.isEmpty()) {
				mv.addObject("fList", fList);
			} else {
				mv.addObject("fList", null);
			}
			mv.addObject("urlVal", "search");
			mv.addObject("searchCondition", searchCondition);
			mv.addObject("searchValue", searchValue);
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.setViewName("free/freeListView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}

	/**
	 * 내가 쓴 게시글 조회
	 * 
	 * @param mv
	 * @param page
	 * @return
	 *//*
		 * @RequestMapping(value = "/free/myList.kh", method = RequestMethod.GET) public
		 * ModelAndView freeMyList(ModelAndView mv,
		 * 
		 * @RequestParam(value = "page", required = false) Integer page) {
		 * ///////////////////////////////////////////////////////////////////////// int
		 * currentPage = (page != null) ? page : 1; int totalCount =
		 * fService.getEveryTotalCount("", ""); int boardLimit = 10; int naviLimit = 5;
		 * int maxPage; int startNavi; int endNavi; // 23/5 = 4.8 + 0.9 = 5(.7) maxPage
		 * = (int) ((double) totalCount / boardLimit + 0.9); startNavi = ((int)
		 * ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1; endNavi =
		 * startNavi + naviLimit - 1; if (maxPage < endNavi) { endNavi = maxPage; }
		 * ////////////////////////////////////////////////////////////////////////// //
		 * /board/list.kh?page=${currentPage } List<Free> fList =
		 * fService.printEveryTbl(currentPage, boardLimit); if (!fList.isEmpty()) {
		 * mv.addObject("urlVal", "myList"); mv.addObject("maxPage", maxPage);
		 * mv.addObject("currentPage", currentPage); mv.addObject("startNavi",
		 * startNavi); mv.addObject("endNavi", endNavi); mv.addObject("fList", fList); }
		 * mv.setViewName("free/freeMyWrite"); return mv; }
		 */

	/**
	 * 내가 쓴 게시글 조회
	 * 
	 * @param mv
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/free/myList.kh", method = RequestMethod.GET)
	public ModelAndView freeMyList(ModelAndView mv 
			, @RequestParam(value = "page", required = false) Integer page
			, HttpSession session
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
		/////////////////////////////////////////////////////////////////////////
		Member member = (Member) session.getAttribute("loginUser");
		// session에 저장된 로그인정보를 getAttribute를 통해 불러옴.
		// Member객체에 담아서 사용하기 위해 Object타입을 Member로 형변환해주고 member 변수에 담음.
		String memberNickname = member.getMemberNickname();
		String memberFileRename = member.getMemberFileRename();
		System.out.println(memberFileRename);
		// 로그인유저의 닉네임을 getter메소드로 불러와서 mapper에서 ${memberNickname}으로 호출해서 사용.
		// session 불러오는 건 아주 많이 쓰이니 기억해둘것
		int currentPage = (page != null) ? page : 1;
		int totalCount = fService.getEveryTotalCount("", "");
		int boardLimit = 10;
		int naviLimit = 5;
		int maxPage;
		int startNavi;
		int endNavi;
		// 23/5 = 4.8 + 0.9 = 5(.7)
		maxPage = (int) ((double) totalCount / boardLimit + 0.9);
		startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
		endNavi = startNavi + naviLimit - 1;
		if (maxPage < endNavi) {
			endNavi = maxPage;
		}
		//////////////////////////////////////////////////////////////////////////
		// /board/list.kh?page=${currentPage }
		List<Free> fList = fService.printEveryTbl(currentPage, boardLimit, memberNickname);
		if (!fList.isEmpty()) {
			mv.addObject("urlVal", "myList");
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("fList", fList);
			mv.addObject("member", member);
		}
		mv.setViewName("free/freeMyWrite");
		return mv;
	}
	
	// 댓글 관리
	/**
	 * 댓글 등록
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/free/addReply.kh", method = RequestMethod.POST)
	public ModelAndView addBoardReply(ModelAndView mv
			, @ModelAttribute FreeReply fReply
			, @RequestParam("page") int page
			, HttpSession session) {
		Member member = (Member) session.getAttribute("loginUser");
		String freeReplyWriter = member.getMemberId();
		fReply.setFreeReplyWriter(freeReplyWriter);
		int result = fService.registerReply(fReply);
		if (result > 0) {
			mv.setViewName("redirect:/free/detail.kh?boardNo=" + fReply.getBoardNo() + "&page=" + page);
		}
		return mv;
	}

	@RequestMapping(value="/free/modifyReply.kh", method=RequestMethod.POST)
	public String modifyBoardReply(
			//@RequestParam("replyNo") Integer replyNo
			//, @RequestParam("replyContents") String replyContents
			@ModelAttribute FreeReply fReply) {
		int result = fService.modifyReply(fReply);
//		return "redirect:/board/detail.kh?boardNo=";
		return "redirect:/free/list.kh";
	}
	
	@RequestMapping(value = "/free/removeReply.kh", method = RequestMethod.POST)
	public String removeBoardReply(@RequestParam("freeReplyNo") Integer freeReplyNo) {
		int result = fService.deleteReply(freeReplyNo);
		return "redirect:/free/list.kh";
	}

}
