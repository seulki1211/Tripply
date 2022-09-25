package com.kh.tripply.trade.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.kh.tripply.trade.domain.Trade;
import com.kh.tripply.trade.domain.TradeReply;
import com.kh.tripply.trade.service.TradeService;

@Controller
public class TradeController {
	@Autowired
	TradeService tService;

	
	/**
	 * 거래 게시판 목록 페이지 출력
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/trade/list.kh",method=RequestMethod.GET)
	public ModelAndView tradeListView(ModelAndView mv,
			@RequestParam(value="page",required=false) Integer currentPage) {

		//1.page를 null체크한다.
		int page = (currentPage!=null)?currentPage : 1;
		
		//2.페이징에 필요한 Paging객체를 생성한다. Paging객체는 화면과 RowBounds에 필요한 값을 get할 수 있다.
		Paging paging = new Paging(tService.getTotalCount(), page,9,5);
		
		try {
			//3.거래게시판 목록 List를 SELECT 한다.
			List<Trade> tList = tService.printAllTrade(paging);
			if(!tList.isEmpty()) {
				mv.addObject("tList",tList).addObject("paging",paging)
				.setViewName("trade/tradeList");
			}else {
			}
			
		} catch (Exception e) {
		}
		//4.tradeList.jsp의 페이지네비 링크에서 사용할 url을 동적으로 변경해주기 위한 부분이다.
		// 검색 결과 조회와 구분을 위하여 'list'문자열을 화면에 전달한다.
		mv.addObject("urlVal","list");
		return mv;
	}
	
	/**
	 * 거래게시판 검색 결과 페이지 출력
	 * @param mv
	 * @param search
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/trade/search.kh",method=RequestMethod.GET)
	public ModelAndView tradeSearchView(ModelAndView mv,
			@ModelAttribute Search search,
			@RequestParam(value="page",required=false)Integer currentPage) {

		//1.page null체크한다.
		int page = (currentPage!=null)?currentPage : 1;
		
		//2.페이징에 필요한 Paging객체를 생성한다. Paging객체는 화면과 RowBounds에 필요한 값을 get할 수 있다.
		Paging paging = new Paging(tService.getTotalCount(), page,9,5);
		
		try {
			//3.거래게시판 검색결과 List를 SELECT한다.
			List<Trade> tList = tService.printSearchTrade(search, paging);
			if(!tList.isEmpty()) {
				mv.addObject("tList",tList).addObject("search",search).addObject("paging",paging)
				.setViewName("trade/tradeList");
			}else {
				
			}
		} catch (Exception e) {
		}
		
		//4.tradeList.jsp의 페이지네비 링크에서 사용할 url을 동적으로 변경해주기 위한 부분이다.
		// 검색 결과 조회와 구분을 위하여 'search'문자열을 화면에 전달한다.
		mv.addObject("urlVal","search");
		return mv;
	}
	
	/**
	 * 거래 게시물 작성 페이지 이동
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/trade/writeView.kh",method=RequestMethod.GET)
	public ModelAndView tradeWriteView(ModelAndView mv,
			HttpSession session) {
		//1.로그인 여부를 확인하고 로그인하지 않은 경우 list로 리다이렉트한다.(mv반환)
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
			mv.setViewName("redirect/trade/list.kh");
			return mv;
		}
		
		mv.setViewName("trade/tradeWrite");
		return mv;
	}
	
	/**
	 * 거래 게시물 저장
	 * @param mv
	 * @param trade
	 * @return
	 */
	@RequestMapping(value="/trade/write.kh",method=RequestMethod.POST)
	public ModelAndView tradeWrite(ModelAndView mv,
			@ModelAttribute Trade trade) {
		try {
			//1. 작성페이지에서 전달받은 trade로 INSERT한다.	
			int result = tService.registerTrade(trade);
			if(result>0) {
				mv.setViewName("redirect:/trade/list.kh?page=1");
			}else {
				
			}
		} catch (Exception e) {
		}
		return mv;
	}
	
	/**
	 * 거래 게시물 상세조회
	 * @param mv
	 * @param boardNo
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/trade/detail.kh",method=RequestMethod.GET)
	public ModelAndView tradeDetailView(ModelAndView mv,
			@RequestParam("boardNo") Integer boardNo,
			@RequestParam("page") Integer page,
			HttpSession session) {
		
		//1.로그인 여부를 확인하고 로그인하지 않은 경우 list로 리다이렉트한다.
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
			mv.setViewName("redirect:/trade/list.kh");
			return mv;
		}
		try {
			//2.수정이나 삭제 후 게시판의 원래 페이지로 돌아가기 위해 session에 page를 저장한다.
			session.setAttribute("page", page);
			
			//3.세션을 이용하여 중복카운팅을 방지하며 조회수를 UPDATE한다.
			int dupleCheck;
			if(session.getAttribute("preventDuplication") == null) {
				dupleCheck = -1; //boardNo와 겹칠 수 없는 숫자 -1로 설정.
			}else {
				dupleCheck = (int)session.getAttribute("preventDuplication");
			}
			if(dupleCheck != boardNo) {
				int result = tService.tradeViewCount(boardNo);
				session.setAttribute("preventDuplication",boardNo);
			}
	
			//4.해당 글의 댓글 List를 SELECT한다. 댓글이 없으면 null처리한다.
			List<TradeReply> tReplyList = tService.printTradeReplyByNo(boardNo);
			if(!tReplyList.isEmpty()) {
				mv.addObject("tReplyList",tReplyList);
			}else {
				mv.addObject("tReplyList",null);
			}
			
			//5.boardNo에 해당하는 Trade 데이터를 SELECT한다.
			Trade trade = tService.printOneTradeByNo(boardNo);
			if(trade != null) {
				mv.addObject("trade",trade).
				setViewName("/trade/tradeDetail");
			}else {
				
			}
		} catch (Exception e) {
		}
		return mv;
	}
	
	/**
	 * 거래게시물 수정페이지 이동
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/trade/modifyView.kh",method=RequestMethod.GET)
	public ModelAndView tradeModifyView(ModelAndView mv,
			@RequestParam("boardNo") Integer boardNo) {
		
		Trade trade = tService.printOneTradeByNo(boardNo);
		if(trade != null) {
			mv.addObject("trade",trade).setViewName("trade/tradeModify");
		}else {
		}
		return mv;
	}
	
	/**
	 * 거래게시물 수정
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/trade/modify.kh",method=RequestMethod.POST)
	public ModelAndView tradeModify(ModelAndView mv,
			@ModelAttribute Trade trade,
			HttpSession session) {
		//화면에서 로그인유저 == 작성자 인 경우 수정 버튼이 노출된다.
		
		//1.수정페이지에서 넘겨받은 trade로 UPDATE한다.
		int result = tService.modifyTradeByNo(trade);
		if(result>0) {
			
			//2. 수정 후 원래의 페이지로 돌아가기 위해 세션에서 page를 꺼낸다.
			//사용한 세션을 삭제해준다.
			int page = (int)session.getAttribute("page");
			mv.setViewName("redirect:/trade/list.kh?page="+page);
			session.removeAttribute("page");
		}else {
			
		}
		return mv;
	}
	
	/**
	 * 거래게시물 삭제
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/trade/remove.kh",method=RequestMethod.GET)
	public ModelAndView tradeRemove(ModelAndView mv,
			@RequestParam("boardNo")Integer boardNo,
			Trade trade,  //데이터 전달용으로 trade객체를 주입한다.
			HttpSession session) {
			//화면에서 로그인유저 == 작성자 인 경우 삭제 버튼이 노출된다.
		
		try {
			
			//1. 로그인 유저의 ID를 가져와 전달용 review객체에 set한다.
			// boardNo도 전달용 review객체에 set한다.
			Member loginUser = (Member)session.getAttribute("loginUser");
			trade.setTradeWriter(loginUser.getMemberId());
			trade.setBoardNo(boardNo);
			
			//2.DELETE한다.
			int result = tService.removeTradeByNo(trade);
			if(result>0) {
				
				//3.삭제가 성공하면 세션에서 원래 페이지 값을 꺼내고 리다이렉트 시 전달값으로 사용한다.
				//사용한 세션은 제거한다.
				int page = (int)session.getAttribute("page");
				mv.setViewName("redirect:/trade/list.kh?page="+page);
				session.removeAttribute("page");
			}
		
		} catch (Exception e) {
		}
		return mv;
	}
	
	/**
	 * 썸대노트
	 * 썸머노트 에디터에서 이미지 업로드 이벤트 발생시 실행되는
	 *  ajax에 매핑되는 컨트롤러이다.
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/trade/uploadSummernoteImageFile",method=RequestMethod.POST)
	public JsonObject uploadSummernoteImageFile(
			@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) {
		JsonObject jsonObject = new JsonObject();
		try {
			//에디터에서 업로드한 file을 MultipartFile로 받았다.
	
			//1.파일 이름과 경로를 설정한다.
			String originalFileName = multipartFile.getOriginalFilename();
			String root = request.getSession().getServletContext().getRealPath("resources");
			String savePath = root+"\\image\\trade\\summerTemp";
			
			//2.파일이름이 중복되지 않도록 재정의 해준다.
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String currentTime = sdf.format(new Date(System.currentTimeMillis()));
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
			String boardFileRename = currentTime + "." + extension;
			
			//3.저장할 경로의 폴더(디렉토리)가 없으면 새로 만든다.
			File targetDirectory = new File(savePath);
			if(!targetDirectory.exists()) {
				targetDirectory.mkdir();
			}
			
			//4.설정한경로에 재정의한 이름으로 파일을 저장한다.
			multipartFile.transferTo(new File(savePath + "\\" + boardFileRename));
			
			
			//5.ajax의 success로 리턴해줄 json오브젝트에 프로퍼티를 저장해준다.
			// 1)썸대노트의 insertImage 설정값에 넣어줄 파일의 경로.
			// 2)원래 파일이름
			// 3)ajax 성공여부
			jsonObject.addProperty("url","/resources/image/trade/summerTemp/"+ boardFileRename);
			jsonObject.addProperty("originName",originalFileName);
			jsonObject.addProperty("responseCode","success");
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}


/////////////////////댓글기능///////////////////	

	/**
	 * 거래게시물 댓글 등록 기능
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/trade/reply/write.kh",method=RequestMethod.POST)
	public ModelAndView tradeReplyWrite(ModelAndView mv,
			@RequestParam("page") Integer page,
			@ModelAttribute TradeReply tReply) {
		
		//1.댓글작성form에서 가져온 rReply를 INSERT해준다.
		int result = tService.registerTradeReply(tReply);
		if(result>0) {
			
			//2.등록 성공 시 파라미터 값을 전달하면서 상세페이지로 리다이렉트한다.
			int boardNo = tReply.getBoardNo();
			mv.setViewName("redirect:/trade/detail.kh?page="+page+"&boardNo="+boardNo);
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
	@RequestMapping(value="/trade/reply/modify.kh",method=RequestMethod.POST)
	public ModelAndView tradeReplyModify(ModelAndView mv,
			@ModelAttribute TradeReply tReply,
			@RequestParam("page") Integer page) {
		
		//1. UPDATE문을 이용하여 게시물의 내용을 변경한다.
		int result = tService.modifyTradeReply(tReply);
		if(result>0) {

			//2.로직 성공 후 현재의 상세페이지로 리다이렉트한다.
			int boardNo = tReply.getBoardNo();
			mv.setViewName("redirect:/trade/detail.kh?page="+page+"&boardNo="+boardNo);
		}else {
			
		}
		return mv;
	}
	
	/**
	 * 댓글 삭제
	 * @param mv
	 * @param tReply
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/trade/reply/remove.kh",method=RequestMethod.POST)
	public ModelAndView tradeReplyRemove(ModelAndView mv,
			@ModelAttribute TradeReply tReply,
			@RequestParam("page") Integer page) {
		
		//1. UPDATE문을 이용하여 게시물의 내용과 상태를 변경한다.
		int result = tService.removeTradeReply(tReply);
		if(result>0) {
			
			//2.로직 성공 후 현재의 상세페이지로 리다이렉트한다.
			int boardNo = tReply.getBoardNo();
			mv.setViewName("redirect:/trade/detail.kh?page="+page+"&boardNo="+boardNo);
		}else {
			
		}
		return mv;
	}
	

	/**
	 * 거래 게시물 댓글 채택
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/trade/reply/choice.kh", method= RequestMethod.POST)
	public ModelAndView tradeReplyChoice(ModelAndView mv,
			@RequestParam("page") Integer page,
			@ModelAttribute Trade trade,
			@ModelAttribute TradeReply tReply
			) {
		
		//1. Trade객체(게시물번호,채택된 댓글작성자,입찰가격)를 파라미터로 거래 게시물을 UPDATE한다.
		// buyerId = 댓글작성자, finalBiddingPrice = 입찰가격
		int result = tService.modifyTradeChoice(trade);
		if(result>0) {
				
				//2.채택된 댓글의 trChoiced를 'Y'로 UPDATE한다.
				int choicedResult = tService.modifyTradeReplyChoiced(tReply);
				if(choicedResult > 0) {
					//3.로직 처리 후 현재 상세페이지로 리다이렉트한다.
					int boardNo = trade.getBoardNo();
					mv.setViewName("redirect:/trade/detail.kh?page="+page+"&boardNo="+boardNo);
				}
		}else {
		}
		return mv;
	}
	
	/**
	 * 거래 게시물 댓글 채택 취소
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/trade/reply/choiceCancel.kh",method=RequestMethod.POST)
	public ModelAndView tradeReplyChoiceCancel(ModelAndView mv,
			@RequestParam("page") Integer page,
			@ModelAttribute Trade trade,
			@ModelAttribute TradeReply tReply) {
		
				//1. Trade객체(게시물번호,채택된 댓글작성자,입찰가격)를 파라미터로 거래 게시물을 UPDATE한다.
				// buyerId = 댓글작성자, finalBiddingPrice = 입찰가격
				int result = tService.modifyTradeChoice(trade);
				if(result>0) {
						
				//2.채택된 댓글의 trChoiced를 'Y'로 UPDATE한다.
				int choicedResult = tService.modifyTradeReplyChoiced(tReply);
				if(choicedResult > 0) {
					//3.로직 처리 후 현재 상세페이지로 리다이렉트한다.
					int boardNo = trade.getBoardNo();
					mv.setViewName("redirect:/trade/detail.kh?page="+page+"&boardNo="+boardNo);
				}
		}else {
		}

		
		
		return mv;
	}
}

