package com.ktdsuniversity.admin.mvgnr.vo;

import com.ktdsuniversity.admin.gnr.vo.GnrVO;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;

public class MvGnrVO {	
	
	private String mvId;
	private int gnrId;
	private String crtDt;
	private String crtr;
	private String mdfyDt;
	private String mdfyr;
	private String useYn;
	private String delYn;
	
	private String added;
	private String deleted;
	
	private GnrVO gnrVO;
	
	private MbrVO crtrMbrVO;
	private MbrVO mdfyrMbrVO;
	
	
	public String getMvId() {
		return mvId;
	}
	public void setMvId(String mvId) {
		this.mvId = mvId;
	}
	public int getGnrId() {
		return gnrId;
	}
	public void setGnrId(int gnrId) {
		this.gnrId = gnrId;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getCrtr() {
		return crtr;
	}
	public void setCrtr(String crtr) {
		this.crtr = crtr;
	}
	public String getMdfyDt() {
		return mdfyDt;
	}
	public void setMdfyDt(String mdfyDt) {
		this.mdfyDt = mdfyDt;
	}
	public String getMdfyr() {
		return mdfyr;
	}
	public void setMdfyr(String mdfyr) {
		this.mdfyr = mdfyr;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
	
	public String getAdded() {
		return added;
	}
	public void setAdded(String added) {
		this.added = added;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
	public GnrVO getGnrVO() {
		return gnrVO;
	}
	public void setGnrVO(GnrVO gnrVO) {
		this.gnrVO = gnrVO;
	}
	
	
	public MbrVO getCrtrMbrVO() {
		return crtrMbrVO;
	}
	public void setCrtrMbrVO(MbrVO crtrMbrVO) {
		this.crtrMbrVO = crtrMbrVO;
	}
	public MbrVO getMdfyrMbrVO() {
		return mdfyrMbrVO;
	}
	public void setMdfyrMbrVO(MbrVO mdfyrMbrVO) {
		this.mdfyrMbrVO = mdfyrMbrVO;
	}
	
}	
