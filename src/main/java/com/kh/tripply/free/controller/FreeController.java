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

@Controller
public class FreeController {
	@Autowired
	private FreeService fService;
	
	// 게시글 등록 화면
	@RequestMapping(value="/free/writeView.kh", method=RequestMethod.GET)
	public String showFreeWrite() {
		return "free/freeWriteForm";
	}
	
	/**
	 * 게시글 등록, 첨부파일 등록
	 * @param mv
	 * @param free
	 * @return mv
	 */
	@RequestMapping(value="/free/register.kh", method=RequestMethod.POST)
	public ModelAndView registBoard(
			ModelAndView mv
			, @ModelAttribute Free free
//			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			,HttpServletRequest request) {
		try {
//			String boardFilename = uploadFile.getOriginalFilename();
//			if(!boardFilename.equals("")) {
//				///////////////////////////////////////////////////////////////////////////
//				String root = request.getSession().getServletContext().getRealPath("resources");
//				String savePath = root + "\\buploadFiles";
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//				String boardFileRename 
//				= sdf.format(new Date(System.currentTimeMillis()))+"."
//						+boardFilename.substring(boardFilename.lastIndexOf(".")+1);
//				// 1.png, img.png
//				File file = new File(savePath);
//				if(!file.exists()) {
//					file.mkdir();
//				}
//				/////////////////////////////////////////////////////////////////////////////
//				uploadFile.transferTo(new File(savePath+"\\"+boardFileRename)); 
//				// 파일을 buploadFiles 경로에 저장하는 메소드
//				String boardFilepath = savePath+"\\"+boardFileRename;
//				free.setFreeFilename(boardFilename);
//				free.setFreeFileRename(boardFileRename);
//				free.setFreeFilepath(boardFilepath);
//			}
			int result = fService.registerBoard(free);
			mv.setViewName("redirect:/free/list.kh");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/free/modifyView.kh", method=RequestMethod.GET)
	public ModelAndView freeModifyView(
			ModelAndView mv
			,@RequestParam("boardNo") Integer boardNo
			,@RequestParam("page") int page) {
		try {
			Free free = fService.printOneByNo(boardNo);
			mv.addObject("free", free);
			mv.addObject("page", page);
			mv.setViewName("free/modifyForm");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	/**
	 * 게시글 수정
	 * @param board
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/free/modify.kh", method=RequestMethod.POST)
	public ModelAndView freeModify(
			@ModelAttribute Free free
			, ModelAndView mv
			,@RequestParam(value="reloadFile", required=false) MultipartFile reloadFile
			,@RequestParam("page") Integer page
			,HttpServletRequest request) {
		try {
			String freeFilename = reloadFile.getOriginalFilename();
			if(reloadFile != null && !freeFilename.equals("")) {
				// 수정, 1. 대체(replace) / 2. 삭제 후 저장
				// 파일삭제
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savedPath = root + "\\buploadFiles";
				File file = new File(savedPath + "\\" + free.getFreeFileRename());
				if(file.exists()) {
					file.delete();
				}
				// 파일 다시 저장
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String freeFileRename = sdf.format(new Date(System.currentTimeMillis()))
						+ "." + freeFilename.substring(freeFilename.lastIndexOf(".")+1);
				String freeFilepath = savedPath + "\\" + freeFileRename;
				reloadFile.transferTo(new File(freeFilepath));
				free.setFreeFilename(freeFilename);
				free.setFreeFileRename(freeFileRename);
				free.setFreeFilepath(freeFilepath);
			}
			int result = fService.modifyBoard(free);
			mv.setViewName("redirect:/free/list.kh?page="+page);
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	/**
	 * 게시글 삭제 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/free/remove.kh", method=RequestMethod.GET)
	public String freeRemove(
			HttpSession session
			, Model model
			, @RequestParam("page") Integer page) {
		try {
			int boardNo = (int)session.getAttribute("boardNo");
			int result = fService.removeOneByNo(boardNo);
			if(result > 0) {
				session.removeAttribute("boardNo");
			}
			return "redirect:/free/list.kh?page="+page;
		} catch (Exception e) {
			model.addAttribute("msg", e.toString());
			return "common/errorPage";
		}
	}
	
	/**
	 * 게시글 전체 조회
	 * @param mv
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/free/list.kh", method=RequestMethod.GET)
	public ModelAndView boardListView(
			ModelAndView mv
			,@RequestParam(value="page", required=false) Integer page) {
		/////////////////////////////////////////////////////////////////////////
		int currentPage = (page != null) ? page : 1;
		int totalCount = fService.getTotalCount("","");
		int boardLimit = 10;
		int naviLimit = 5;
		int maxPage;
		int startNavi;
		int endNavi;
		// 23/5 = 4.8 + 0.9 = 5(.7)
		maxPage = (int)((double)totalCount/boardLimit + 0.9);
		startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
		endNavi = startNavi + naviLimit - 1;
		if(maxPage < endNavi) {
			endNavi = maxPage;
		}
		//////////////////////////////////////////////////////////////////////////
		// /board/list.kh?page=${currentPage }
		List<Free> fList = fService.printAllBoard(currentPage, boardLimit);
		if(!fList.isEmpty()) {
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
	 * @param mv
	 * @param boardNo
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/free/detail.kh", method=RequestMethod.GET)
	public ModelAndView freeDetailView(
			ModelAndView mv
			, @RequestParam("boardNo") Integer boardNo
			, @RequestParam("page") Integer page
			, HttpSession session) {
		try {
			Free free = fService.printOneByNo(boardNo);
			List<FreeReply> rList = fService.printAllReply(boardNo);
			session.setAttribute("boardNo", free.getBoardNo());
			// 세션에 boardNo 저장 -> 삭제하기 위해서
			mv.addObject("rList", rList);
			mv.addObject("free", free);
			mv.addObject("page", page);
			mv.setViewName("free/detailView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
}
