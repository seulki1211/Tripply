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

	@Override
	public int deleteOneByNo(SqlSession session, int bannerNo) {
		int result = session.update("bannerMapper.deleteBanner", bannerNo);
		return result;
	}

	@Override
	public int chkOneBanner(SqlSession session, int bannerNo) {
		int chkBanner = session.selectOne("bannerMapper.chkOneBanner", bannerNo);
		return chkBanner;
	}

	@Override
	public int updateBanner(SqlSession session, Banner banner) {
		int result = session.update("bannerMapper.updateBanner", banner);
		return result;
	}

	@Override
	public Banner selectOneBanenr(SqlSession session, int bannerNo) {
		Banner oneBanner = session.selectOne("bannerMapper.selectOneBanner", bannerNo);
		return oneBanner;
	}

	

}
