package com.kh.tripply.trade.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.kh.tripply.common.Paging;
import com.kh.tripply.trade.domain.Trade;
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
	@RequestMapping(value="trade/list.kh",method=RequestMethod.GET)
	public ModelAndView tradeListView(ModelAndView mv,
			@RequestParam(value="currentPage",required=false) Integer page) {
		int currentPage = (page!=null)?page : 1;
		Paging paging = new Paging(tService.getTotalCount(), currentPage,9,5);
		try {
			List<Trade> tList = tService.printAllTrade(paging);
			System.out.println(tList.get(0).getTradeTitle());
			if(!tList.isEmpty()) {
				mv.addObject("tList",tList).addObject("paging",paging)
				.setViewName("trade/tradeList");
			}else {
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		mv.addObject("urlVal","list");
		return mv;
	}
	
	/**
	 * 거래 게시물 작성 페이지 이동
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/trade/writeView.kh",method=RequestMethod.GET)
	public ModelAndView tradeWriteView(ModelAndView mv) {
		mv.setViewName("trade/tradeWrite");
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
			// 1)썸머노트의 insertImage 설정값에 넣어줄 파일의 경로.
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
}
