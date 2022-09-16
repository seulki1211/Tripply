package com.kh.tripply.party.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.party.domain.Party;
import com.kh.tripply.party.service.PartyService;

@Controller
public class PartyController {
	@Autowired
	private PartyService pService;
	
@RequestMapping(value="/party/writeView.kh", method=RequestMethod.GET)
	public String showBoardWrite() {
		return "party/partyWriteForm";
	}

@RequestMapping(value="/party/register.kh", method=RequestMethod.POST)
public ModelAndView registerBoard(ModelAndView mv
								,@ModelAttribute Party party
//								,@RequestParam(value = "uploadFile", required = false) 
//												MultipartFile uploadFile
//								, HttpServletRequest request
								) { // resources 경로 가져오려고
	
	try {
		
//		String boardFilename = uploadFile.getOriginalFilename();
//		
//		if(!boardFilename.equals("")) {
//			String root = request.getSession().getServletContext().getRealPath("resources");
//			String savePath = root + "\\buploadFiles"; // 저장경로 지정
//			File file = new File(savePath);
//			
//			// 파일 이름이 같다고 안들어가는 경우 방지 --> 파일명을 날짜+시간으로 바꿈
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//			String boardFileRename = sdf.format(new Date(System.currentTimeMillis()))+"."
//					+boardFilename.substring(boardFilename.lastIndexOf(".")+1);// .다음부터 끝까지 잘라서 반환
//			if(!file.exists()) {
//				file.mkdir(); // 경로 폴더가 없으면 폴더 생성
//			}
//			uploadFile.transferTo(new File(savePath + "\\" + boardFileRename));//파일을 buploadFile경로에 저장하는 메소드
//			party.setPartyFileName(boardFilename);
//			party.setPartyFileRename(boardFileRename); //모든 파일이 고유한 값을 갖게 해야함
//			
//			String boardFilepath = savePath + "\\" +boardFileRename;// 절대경로
//			
//			party.setPartyFilePath(boardFilepath);
//			
//		}
		
		int result = pService.registerParty(party);
			mv.setViewName("redirect:/board/list.kh");
		
	}catch (Exception e) {
		e.printStackTrace();
		mv.addObject("msg", e.getMessage());
		mv.setViewName("common/errorPage");		
		}
	return mv;
}
}
