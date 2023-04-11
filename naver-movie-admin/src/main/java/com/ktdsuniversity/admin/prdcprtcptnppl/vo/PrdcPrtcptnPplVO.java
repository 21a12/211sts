package com.ktdsuniversity.admin.prdcprtcptnppl.vo;

import java.util.List;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;
import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

public class PrdcPrtcptnPplVO {	
	
	private String prdcPrtcptnId;
	private String mvId;
	private String mvPplId;
	private String mssn;
	private String rspnsbltRolNm;
	private String crtDt;
	private String crtr;
	private String mdfyDt;
	private String mdfyr;
	private String useYn;
	private String delYn;
	
	private String added; 
	private String modified; 
	private String deleted;	// prdcPrtcptnId 
	
	private MvPplVO mvPplVO;
	private MbrVO crtrMbrVO;
	private MbrVO mdfyrMbrVO;
	
	
	
	public String getPrdcPrtcptnId() {
		return prdcPrtcptnId;
	}
	public void setPrdcPrtcptnId(String prdcPrtcptnId) {
		this.prdcPrtcptnId = prdcPrtcptnId;
	}
	public String getMvId() {
		return mvId;
	}
	public void setMvId(String mvId) {
		this.mvId = mvId;
	}
	public String getMvPplId() {
		return mvPplId;
	}
	public void setMvPplId(String mvPplId) {
		this.mvPplId = mvPplId;
	}
	public String getMssn() {
		return mssn;
	}
	public void setMssn(String mssn) {
		this.mssn = mssn;
	}
	public String getRspnsbltRolNm() {
		return rspnsbltRolNm;
	}
	public void setRspnsbltRolNm(String rspnsbltRolNm) {
		this.rspnsbltRolNm = rspnsbltRolNm;
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
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
	public MvPplVO getMvPplVO() {
		return mvPplVO;
	}
	public void setMvPplVO(MvPplVO mvPplVO) {
		this.mvPplVO = mvPplVO;
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
