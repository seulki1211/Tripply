package com.kh.tripply.banner.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.banner.domain.Banner;

public interface BannerStore {
	
	//등록 쿼리
	public int insertBanner(SqlSession session, Banner banner);

	//리스트 쿼리
	public List<Banner> selectAllBanner(SqlSession session); 

	public int deleteOneByNo(SqlSession session, int bannerNo);

}
