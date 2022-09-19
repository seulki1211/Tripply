package com.kh.tripply.banner.domain;


public class Banner {
	private int bannerNo;
	private String bannerWriter;
	private String bannerFileName;
	private String bannerFileRename;
	private String bannerFilePath;
	public int getBannerNo() {
		return bannerNo;
	}
	public void setBannerNo(int bannerNo) {
		this.bannerNo = bannerNo;
	}
	public String getBannerWriter() {
		return bannerWriter;
	}
	public void setBannerWriter(String bannerWriter) {
		this.bannerWriter = bannerWriter;
	}
	public String getBannerFileName() {
		return bannerFileName;
	}
	public void setBannerFileName(String bannerFileName) {
		this.bannerFileName = bannerFileName;
	}
	public String getBannerFileRename() {
		return bannerFileRename;
	}
	public void setBannerFileRename(String bannerFileRename) {
		this.bannerFileRename = bannerFileRename;
	}
	public String getBannerFilePath() {
		return bannerFilePath;
	}
	public void setBannerFilePath(String bannerFilePath) {
		this.bannerFilePath = bannerFilePath;
	}
	@Override
	public String toString() {
		return "Banner [bannerNo=" + bannerNo + ", bannerWriter=" + bannerWriter + ", bannerFileName=" + bannerFileName
				+ ", bannerFileRename=" + bannerFileRename + ", bannerFilePath=" + bannerFilePath + "]";
	}

	
}
