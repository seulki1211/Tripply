package com.kh.tripply.banner.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.banner.domain.Banner;
import com.kh.tripply.banner.service.BannerService;
import com.kh.tripply.banner.store.BannerStore;

@Service
public class BannerServiceImpl implements BannerService{
	
	@Autowired
	private BannerStore bStore;
	@Autowired
	private SqlSessionTemplate session;

	// 배너 등록
	@Override
	public int registerBanner(Banner banner) {
		int result = bStore.insertBanner(session, banner);
		return result;
	}

	// 배너 리스트
	@Override
	public List<Banner> printAllBanner() {
		List<Banner> bList = bStore.selectAllBanner(session);
		return bList;
	}

	@Override
	public int removeOneByNo(int bannerNo) {
		int result = bStore.deleteOneByNo(session, bannerNo);
		return result;
	}
	
	
}
