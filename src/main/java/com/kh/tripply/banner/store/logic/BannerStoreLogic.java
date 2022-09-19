package com.kh.tripply.banner.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.banner.domain.Banner;
import com.kh.tripply.banner.store.BannerStore;

@Repository
public class BannerStoreLogic implements BannerStore {

	@Override
	public int insertBanner(SqlSession session, Banner banner) {
		int result = session.insert("bannerMapper.insertBanner", banner);
		return result;
	}

	@Override
	public List<Banner> selectAllBanner(SqlSession session) {
		List<Banner> bList = session.selectList("bannerMapper.selectAllBanner");
		return bList;
	}

}
