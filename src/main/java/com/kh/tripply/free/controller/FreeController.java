package com.kh.tripply.free.controller;

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

import com.kh.tripply.free.domain.Free;
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
	
	@RequestMapping(value="/free/register.kh", method=RequestMethod.POST)
	public ModelAndView registerFreeBoard(
			ModelAndView mv
			, @ModelAttribute Free free
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request) {
		try {
			String freeFilename = uploadFile.getOriginalFilename();
			if(!freeFilename.equals("")) {
				/////////////////////////////////////////////////////////////////////////
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\buploadFiles";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String freeFileRename 
				= sdf.format(new Date(System.currentTimeMillis()))+"."
						+freeFilename.substring(freeFilename.lastIndexOf(".")+1);
				// 1.png, img.png
				File file = new File(savePath);
				if(!file.exists()) {
					file.mkdir();
				}
				///////////////////////////////////////////////////////////////////////////
				uploadFile.transferTo(new File(savePath+"\\"+freeFileRename)); 
				// 파일을 buploadFiles 경로에 저장하는 메소드
				String freeFilepath = savePath+"\\"+freeFileRename;
				free.setFreeFilename(freeFilename);
				free.setFreeFileRename(freeFileRename);
				free.setFreeFilepath(freeFilepath);
			}
			int result = fService.registerBoard(free);
			mv.setViewName("redirect:/board/list.kh");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
}
