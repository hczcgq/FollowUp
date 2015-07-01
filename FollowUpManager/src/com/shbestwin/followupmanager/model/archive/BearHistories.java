package com.shbestwin.followupmanager.model.archive;

public class BearHistories {
	private String childrenNum;//子女人数
	private String fetusNum;//胎次
	private String birthNum;//产次
	private String streamNum;//人流次数
	private String odinopoeiaNum;//引产次数
	private String contraceptive;//避孕措施
	
	public BearHistories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BearHistories(String childrenNum, String fetusNum, String birthNum,
			String streamNum, String odinopoeiaNum, String contraceptive) {
		super();
		this.childrenNum = childrenNum;
		this.fetusNum = fetusNum;
		this.birthNum = birthNum;
		this.streamNum = streamNum;
		this.odinopoeiaNum = odinopoeiaNum;
		this.contraceptive = contraceptive;
	}
	public String getChildrenNum() {
		return childrenNum;
	}
	public void setChildrenNum(String childrenNum) {
		this.childrenNum = childrenNum;
	}
	public String getFetusNum() {
		return fetusNum;
	}
	public void setFetusNum(String fetusNum) {
		this.fetusNum = fetusNum;
	}
	public String getBirthNum() {
		return birthNum;
	}
	public void setBirthNum(String birthNum) {
		this.birthNum = birthNum;
	}
	public String getStreamNum() {
		return streamNum;
	}
	public void setStreamNum(String streamNum) {
		this.streamNum = streamNum;
	}
	public String getOdinopoeiaNum() {
		return odinopoeiaNum;
	}
	public void setOdinopoeiaNum(String odinopoeiaNum) {
		this.odinopoeiaNum = odinopoeiaNum;
	}
	public String getContraceptive() {
		return contraceptive;
	}
	public void setContraceptive(String contraceptive) {
		this.contraceptive = contraceptive;
	}
	@Override
	public String toString() {
		return "BearHistories [childrenNum=" + childrenNum + ", fetusNum="
				+ fetusNum + ", birthNum=" + birthNum + ", streamNum="
				+ streamNum + ", odinopoeiaNum=" + odinopoeiaNum
				+ ", contraceptive=" + contraceptive + "]";
	}
}
