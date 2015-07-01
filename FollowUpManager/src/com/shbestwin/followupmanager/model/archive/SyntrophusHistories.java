package com.shbestwin.followupmanager.model.archive;
/**
 * 
 * @author Administrator
 *@Description 遗传病史
 */
public class SyntrophusHistories {
	private String pastNum;
	private String pastName;
	
	public SyntrophusHistories(String pastNum, String pastName) {
		super();
		this.pastNum = pastNum;
		this.pastName = pastName;
	}
	public SyntrophusHistories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPastNum() {
		return pastNum;
	}
	public void setPastNum(String pastNum) {
		this.pastNum = pastNum;
	}
	public String getPastName() {
		return pastName;
	}
	public void setPastName(String pastName) {
		this.pastName = pastName;
	}
	@Override
	public String toString() {
		return "SyntrophusHistories [pastNum=" + pastNum + ", pastName="
				+ pastName + "]";
	}
	
}
