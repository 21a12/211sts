package com.ktdsuniversity.admin.common.vo;

public abstract class AbstractVO {
	
	private int pageNo;
	private int viewCnt;
	private int totalCount;
	private int lastPage;
	private int lastGroup;
	private int rnum;
	
	// 추상클래스이므로 상속한애만 쓸수있게 
	// public 대신 protected를 사용
	protected AbstractVO() {
		// 기본값을 설정
		this.pageNo = 0;
		this.viewCnt = 10;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getLastGroup() {
		return lastGroup;
	}
	public void setLastGroup(int lastGroup) {
		this.lastGroup = lastGroup;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
}
