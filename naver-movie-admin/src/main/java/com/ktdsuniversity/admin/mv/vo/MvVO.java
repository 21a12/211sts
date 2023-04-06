package com.ktdsuniversity.admin.mv.vo;

import java.util.List;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;
import com.ktdsuniversity.admin.mvgnr.vo.MvGnrVO;
import com.ktdsuniversity.admin.prdcprtcptnppl.vo.PrdcPrtcptnPplVO;

public class MvVO {	
	
	private String mvId;
	private String mvTtl;
	private String engTtl;
	private String scrnStt;
	private int scrnTm;
	private String opngDt;
	private String grd;
	private String pstr;
	private String smr;
	private String crtDt;
	private String crtr;
	private String mdfyDt;
	private String mdfyr;
	private String useYn;
	private String delYn;
	
	private List<MvGnrVO> gnrList;
	private List<PrdcPrtcptnPplVO> pplList;
	private MbrVO crtrMbrVO;
	private MbrVO mdfyrMbrVO;
	
	public String getMvId() {
		return mvId;
	}
	public void setMvId(String mvId) {
		this.mvId = mvId;
	}
	public String getMvTtl() {
		return mvTtl;
	}
	public void setMvTtl(String mvTtl) {
		this.mvTtl = mvTtl;
	}
	public String getEngTtl() {
		return engTtl;
	}
	public void setEngTtl(String engTtl) {
		this.engTtl = engTtl;
	}
	public String getScrnStt() {
		return scrnStt;
	}
	public void setScrnStt(String scrnStt) {
		this.scrnStt = scrnStt;
	}
	public int getScrnTm() {
		return scrnTm;
	}
	public void setScrnTm(int scrnTm) {
		this.scrnTm = scrnTm;
	}
	public String getOpngDt() {
		return opngDt;
	}
	public void setOpngDt(String opngDt) {
		this.opngDt = opngDt;
	}
	public String getGrd() {
		return grd;
	}
	public void setGrd(String grd) {
		this.grd = grd;
	}
	public String getPstr() {
		return pstr;
	}
	public void setPstr(String pstr) {
		this.pstr = pstr;
	}
	public String getSmr() {
		return smr;
	}
	public void setSmr(String smr) {
		this.smr = smr;
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
	
	public List<MvGnrVO> getGnrList() {
		return gnrList;
	}
	public void setGnrList(List<MvGnrVO> gnrList) {
		this.gnrList = gnrList;
	}
	public List<PrdcPrtcptnPplVO> getPplList() {
		return pplList;
	}
	public void setPplList(List<PrdcPrtcptnPplVO> pplList) {
		this.pplList = pplList;
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
