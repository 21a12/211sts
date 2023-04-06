package com.ktdsuniversity.admin.mvppl.vo;

import com.ktdsuniversity.admin.common.vo.AbstractVO;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;

public class MvPplVO extends AbstractVO {	
	
	private String mvPplId;
	private String prflPctr;
	private String nm;
	private String rlNm;
	private String crtDt;
	private String crtr;
	private String mdfyDt;
	private String mdfyr;
	private String useYn;
	private String delYn;
	
	private MbrVO crtrMbrVO;
	private MbrVO mdfyrMbrVO;
	
	private String isDeletePctr;

	
	public String getMvPplId() {
		return mvPplId;
	}
	public void setMvPplId(String mvPplId) {
		this.mvPplId = mvPplId;
	}
	public String getPrflPctr() {
		return prflPctr;
	}
	public void setPrflPctr(String prflPctr) {
		this.prflPctr = prflPctr;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getRlNm() {
		return rlNm;
	}
	public void setRlNm(String rlNm) {
		this.rlNm = rlNm;
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
	
	public String getIsDeletePctr() {
		return isDeletePctr;
	}
	public void setIsDeletePctr(String isDeletePctr) {
		this.isDeletePctr = isDeletePctr;
	}
	
}	
