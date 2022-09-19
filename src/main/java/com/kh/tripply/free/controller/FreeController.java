package com.kh.tripply.free.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class FreeController {

	// 게시글 등록 화면
	@RequestMapping(value="/free/writeView.kh", method=RequestMethod.GET)
	public String showFreeWrite() {
		return "free/freeWriteForm";
	}
	
	
}
