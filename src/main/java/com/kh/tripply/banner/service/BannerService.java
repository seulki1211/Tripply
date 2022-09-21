package com.kh.tripply.banner.service;

import java.util.List;

import com.kh.tripply.banner.domain.Banner;

public interface BannerService {
	
	public int registerBanner(Banner banner);

	public List<Banner> printAllBanner(); 
	
	public int removeOneByNo(int bannerNo);
	
	public int chkBanner(int bannerNo);
	
	public int renewBanner(Banner banner);
}
