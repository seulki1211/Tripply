package com.kh.tripply.message.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.message.common.MessageSearch;
import com.kh.tripply.message.domain.Message;
import com.kh.tripply.message.service.MessageService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService mService;
	
	//쪽지 쓰기 뷰
	@RequestMapping(value = "/message/writeView.kh", method = RequestMethod.GET)
	public String showMessageWrite() {
		return "message/messageWriteForm";
	}
	
	
	// 받는이 체크
		@RequestMapping(value="/message/chkReciever.kh", method = RequestMethod.POST)
		public ModelAndView chkMsgReciever(ModelAndView mv
				, @RequestParam("msgReciever") String msgReciever) {
			
			try {
				
				int result = mService.chkMsgReciever(msgReciever);
				mv.addObject("chkResult", result);
				mv.addObject("msgReciever", msgReciever);
				mv.setViewName("message/messageWriteForm");

				
			} catch (Exception e) {
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");
			}
			
			return mv;
			
		}
	
	// 쪽지 보내기
	@RequestMapping(value = "/message/send.kh", method = RequestMethod.POST)
	public ModelAndView sendMessage(ModelAndView mv
									, @ModelAttribute Message msg) {
		
		try {
			int result = mService.sendMessage(msg);
			mv.addObject("msgWriter", msg.getMsgWriter());
			mv.setViewName("redirect:/message/sendList.kh");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	//보낸 쪽지함
	@RequestMapping(value = "/message/sendList.kh", method = RequestMethod.GET)
	public ModelAndView sendMsgList(ModelAndView mv
			, @RequestParam(value = "page", required = false) Integer page
			,@RequestParam("msgWriter") String msgWriter
			,HttpServletRequest request) {
		
		try {
			MessageSearch mSearch = new MessageSearch("","",msgWriter,"");
			// 페이징
			int currentPage = (page != null) ? page : 1;
			int totalCount = mService.getTotalSendCount(mSearch);
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
			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("urlVal", "sendList");
			// 페이징
			
			//검색 조건
			mv.addObject("mSearch", mSearch);
			
			//검색 결과
			List<Message> sendList = mService.printSendList(mSearch,currentPage, boardLimit);
			mv.addObject("sendList", sendList);
			mv.setViewName("message/messageSendListView");
			
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
		
	}

	// 받은 쪽지함
	@RequestMapping(value = "/message/recvList.kh", method = RequestMethod.GET)
	public ModelAndView recvMsgList(ModelAndView mv
			, @RequestParam(value = "page", required = false) Integer page 
			, HttpServletRequest request
			, @RequestParam("msgReciever") 		String msgReciever) {
		
		try {
			
			// 페이징
			MessageSearch mSearch = new MessageSearch("","","",msgReciever);
			int currentPage = (page != null) ? page : 1;
			int totalCount = mService.getTotalRecvCount(mSearch);
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
			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("urlVal", "sendList");
			// 페이징
			
			//검색 조건
			mv.addObject("mSearch", mSearch);
			
			// 검색 결과
			List<Message> recvList = mService.printRecvList(mSearch,currentPage,boardLimit);
			mv.addObject("recvList", recvList);
			mv.setViewName("message/messageRecvListView");
			
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	
	
	// 쪽지 열람
	@RequestMapping(value = "message/detail.kh", method = RequestMethod.GET)
		public ModelAndView noticeDetailView(ModelAndView mv, @RequestParam("msgNo") int msgNo,
				@RequestParam("page") Integer page, HttpSession session) {

			try {
				Message msg = mService.printOneNotice(msgNo);
				mv.addObject("msg", msg);
				mv.addObject("page", page);

				mv.setViewName("message/messageDetailView");
			} catch (Exception e) {
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");
			}

			return mv;

		}
	
	//쪽지 검색 
		@RequestMapping(value = "/message/search.kh", method = RequestMethod.GET)
		public ModelAndView recvMsgList(ModelAndView mv
				, @RequestParam("searchCondition") 	String searchCondition
				, @RequestParam("searchArea") 		String searchArea
				, @RequestParam("searchValue") 		String searchValue
				, @RequestParam("loginUserNickname") String loginUserNickname
				, @RequestParam(value = "page", required = false) Integer page 
				) {
			
			try {
				if(searchArea.equals("msgWriter")) {
					MessageSearch mSearch = new MessageSearch(searchCondition,searchValue,loginUserNickname,"");
					// 페이징
					int currentPage = (page != null) ? page : 1;
					int totalCount = mService.getTotalSendCount(mSearch);
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
					mv.addObject("currentPage", currentPage);
					mv.addObject("maxPage", maxPage);
					mv.addObject("startNavi", startNavi);
					mv.addObject("endNavi", endNavi);
					mv.addObject("urlVal", "sendList");
					// 페이징
					
					//검색 조건
					mv.addObject("mSearch", mSearch);
					
					// 검색 결과
					List<Message> sendList = mService.printSearchSendList(mSearch,currentPage,boardLimit);

					mv.addObject("sendList", sendList);
					mv.setViewName("message/messageSendListView");
					
				}else {
					MessageSearch mSearch = new MessageSearch(searchCondition,searchValue,"",loginUserNickname);
					// 페이징
					int currentPage = (page != null) ? page : 1;
					int totalCount = mService.getTotalRecvCount(mSearch);
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
					mv.addObject("currentPage", currentPage);
					mv.addObject("maxPage", maxPage);
					mv.addObject("startNavi", startNavi);
					mv.addObject("endNavi", endNavi);
					mv.addObject("urlVal", "recvList");
					// 페이징
					
					//검색 조건
					mv.addObject("mSearch", mSearch);
					
					// 검색 결과
					List<Message> recvList = mService.printSearchRecvList(mSearch,currentPage,boardLimit);
					System.out.println(recvList.toString());
					mv.addObject("recvList", recvList);
					mv.setViewName("message/messageRecvListView");
				}
				
				
			} catch (Exception e) {
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");
			}
			return mv;
		}
	
}
