package com.kh.tripply.party.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.party.domain.Party;
import com.kh.tripply.party.domain.PartyReply;
import com.kh.tripply.party.service.PartyService;

@Controller
public class PartyController {
	@Autowired
	private PartyService pService;

	// 동행자 게시판 등록view
	@RequestMapping(value = "/party/writeView.kh", method = RequestMethod.GET)
	public String showBoardWrite() {
		return "party/partyWriteForm";
	}

	// 동행자 게시판 게시글 등록
	@RequestMapping(value = "/party/register.kh", method = RequestMethod.POST)
	public ModelAndView registerBoard(ModelAndView mv, @ModelAttribute Party party,
			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile,
			HttpServletRequest request) { // resources 경로 가져오려고

		try {

			String boardFilename = uploadFile.getOriginalFilename();

			if (!boardFilename.equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\partyuploadFiles"; // 저장경로 지정
				File file = new File(savePath);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String boardFileRename = sdf.format(new Date(System.currentTimeMillis())) + "."
						+ boardFilename.substring(boardFilename.lastIndexOf(".") + 1);// .다음부터 끝까지 잘라서 반환
				if (!file.exists()) {
					file.mkdir(); // 경로 폴더가 없으면 폴더 생성
				}
				uploadFile.transferTo(new File(savePath + "\\" + boardFileRename));// 파일을 buploadFile경로에 저장하는 메소드
				party.setPartyFileName(boardFilename);
				party.setPartyFileRename(boardFileRename); // 모든 파일이 고유한 값을 갖게 해야함

				String boardFilepath = savePath + "\\" + boardFileRename;// 절대경로
				party.setPartyFilePath(boardFilepath);

			}

			int result = pService.registerParty(party);
			mv.setViewName("redirect:/party/list.kh");

		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 동행자 게시판 리스트
	@RequestMapping(value = "/party/list.kh", method = RequestMethod.GET)
	public ModelAndView partyListView(ModelAndView mv, @ModelAttribute Party party,
			@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request) {

		try {
			// 페이징
			int currentPage = (page != null) ? page : 1;
			int totalCount = pService.getTotalCount("", "","");
			int boardLimit = 9;
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
			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("urlVal", "list");
			// 페이징

			List<Party> pList = pService.printAllParty(currentPage, boardLimit);
			mv.addObject("pList", pList);
			
			Date today = new Date(System.currentTimeMillis());
			mv.addObject("today",today);
			mv.setViewName("party/partyListView");

		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
		}
		return mv;
	}

	// 동행자 디테일뷰 + 댓글 뷰

	@RequestMapping(value = "/party/detail.kh", method = RequestMethod.GET)
	public ModelAndView partyDetailView(ModelAndView mv
			, @RequestParam("partyNo") int partyNo
			, @RequestParam("page") Integer page
			, HttpSession session) {

		try {
			Party party = pService.printOneParty(partyNo);
			mv.addObject("party", party);
			mv.addObject("page", page);

			int refBoardNo = partyNo;
			List<PartyReply> prList = pService.printAllPartyReply(refBoardNo);
			mv.addObject("prList", prList);
			
			mv.setViewName("party/partyDetailView");
			
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}

		return mv;

	}

	// 동행자 삭제
	@RequestMapping(value = "/party/remove.kh", method = RequestMethod.GET)
	public String partyRemove(HttpSession session, @RequestParam("partyNo") int partyNo,
			@RequestParam("page") Integer page) {

		try {
			int result = pService.removeOneByNo(partyNo);
			if (result > 0) {
				return "redirect:/party/list.kh?page=" + page;

			}
		} catch (Exception e) {
			return "common/errorPage";
		}
		return "redirect:/party/list.kh?page=" + page;
	}

	// 동행자 수정뷰 연결
	@RequestMapping(value = "/party/modifyView.kh", method = RequestMethod.GET)
	public ModelAndView showPartyModify(
			@RequestParam("page") Integer page
			, @RequestParam("partyNo") Integer partyNo
			, ModelAndView mv) {

		try {

			Party party = pService.printOneParty(partyNo);
			mv.addObject("page", page);
			mv.addObject("party", party);

			mv.setViewName("party/partyModifyForm");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	// 동행자 수정
	@RequestMapping(value = "/party/modify.kh", method = RequestMethod.POST)
	public ModelAndView modifyParty(ModelAndView mv, 
			@ModelAttribute Party party
			, @RequestParam("page") Integer page,
			@RequestParam(value = "reloadFile", required = false) MultipartFile reloadFile,
			HttpServletRequest request) {

		try {

			String partyFileName = reloadFile.getOriginalFilename();

			if (reloadFile != null && !partyFileName.equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savedPath = root + "\\buploadFiles";
				File file = new File(savedPath + "\\" + party.getPartyFileRename()); // 경로와 파일 이름으로 파일객체 생성
				if (file.exists()) { // 파일이 존재하면
					file.delete(); // 삭제
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String partyFileRename = sdf.format(new Date(System.currentTimeMillis())) + "."
						+ partyFileName.substring(partyFileName.lastIndexOf(".") + 1);

				String partyFilePath = savedPath + "\\" + partyFileRename;

				reloadFile.transferTo(new File(partyFilePath)); // 저장

				party.setPartyFileName(partyFileName);
				party.setPartyFileRename(partyFileRename);
				party.setPartyFilePath(partyFilePath);
			}

			int result = pService.modifyParty(party);
			mv.addObject("party", party);
			mv.setViewName("redirect:/party/detail.kh?partyNo=" + party.getPartyNo() + "&page=" + page);
	//		mv.setViewName("redirect:/party/list.kh?page=" + page);

		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}

		return mv;
	}
	
	// 동행자 게시판 검색
	@RequestMapping(value = "party/search.kh", method = RequestMethod.GET)
	public ModelAndView boardSearchList(ModelAndView mv
								, @RequestParam("searchCondition") 	String searchCondition
								, @RequestParam("searchRegion") 	String searchRegion
								, @RequestParam("searchValue") 		String searchValue
								, @RequestParam(value = "page", required=false) Integer page) {
			
		try {
			/////////////////////////////////페이징///////////////////////////////
			int currentPage = (page != null) ? page : 1;
			int totalCount = pService.getTotalCount(searchCondition, searchValue, searchRegion);
			int boardLimit = 9;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;
			
			maxPage = (int)((double)totalCount/boardLimit + 0.9);
			startNavi = ((int)((double)currentPage/naviLimit + 0.9)-1)*naviLimit+1;
			endNavi = startNavi + naviLimit - 1;
			if(maxPage < endNavi) {
				endNavi = maxPage;
			}

			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);  
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			/////////////////////////////////페이징///////////////////////////////
			
			List<Party> pList = pService.printAllByValue(searchCondition, searchValue, searchRegion ,currentPage, boardLimit);

			if (!pList.isEmpty()) {
				mv.addObject("pList", pList);
			} else {
				mv.addObject("pList", null);
			}
			
			/////////////////////////////////검색조건+검색어 남기기///////////////////////////////
			mv.addObject("searchCondition",searchCondition);
			mv.addObject("searchRegion", searchRegion);
			mv.addObject("searchValue", searchValue);
			
			//urlVal로 url 상황에 맞게 변경하기//////
			mv.addObject("urlVal", "search");

			mv.setViewName("party/partyListView");

		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
		}

		return mv;
	}
	
	// 댓글 등록
	@RequestMapping(value="/party/addReply.kh", method = RequestMethod.POST)
	public ModelAndView addPartyReply(ModelAndView mv
			, @ModelAttribute PartyReply pReply
			, @RequestParam("page") int page
			, @RequestParam("pReplyWriter") String pReplyWriter
			, HttpSession session) {
		
		try {
			pReply.setpReplyWriter(pReplyWriter);
			int result = pService.addPartyReply(pReply);
			
			mv.setViewName("redirect:/party/detail.kh?partyNo=" + pReply.getRefBoardNo() + "&page=" + page);

		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");

		}
		return mv;
		
	}
	
	// 댓글 삭제
	@RequestMapping(value = "/party/removeReply.kh", method = RequestMethod.GET)
	public ModelAndView removeReply(ModelAndView mv
			,@RequestParam("pReplyNo") Integer pReplyNo
			,@RequestParam("partyNo") Integer partyNo
			,@RequestParam("page") int page) {
		
		try {
			int result = pService.removeOneReply(pReplyNo);
			mv.setViewName("redirect:/party/detail.kh?partyNo=" + partyNo +"&page=" + page);
			

		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
			}
		return mv;
		
	}
	
	// 댓글 수정 
	@RequestMapping(value = "/party/modifyReply.kh", method = RequestMethod.POST)
	public ModelAndView modifyPartyReply(ModelAndView mv
			, @ModelAttribute PartyReply pReply
			, @RequestParam("page") Integer page
			, HttpSession session) {
		
		try {
			int result = pService.modifyReply(pReply);
			mv.setViewName("redirect:/party/detail.kh?partyNo="+pReply.getRefBoardNo()+"&page=" + page);
			
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
		
	}

	
}
