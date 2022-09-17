package com.kh.tripply.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kh.tripply.review.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	ReviewService rService;
	

}
