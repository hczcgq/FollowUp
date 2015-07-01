package com.shbestwin.followupmanager.model.archive;
/**
 * 
 * @author Administrator
 * 
 *	@Discription:过敏史
 */
public class AllergyHistories {
	private String allergyNum;// 编号
	private String allergyName;// 过敏史名称
	
	public AllergyHistories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AllergyHistories(String allergyNum, String allergyName) {
		super();
		this.allergyNum = allergyNum;
		this.allergyName = allergyName;
	}
	public String getAllergyNum() {
		return allergyNum;
	}
	public void setAllergyNum(String allergyNum) {
		this.allergyNum = allergyNum;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	@Override
	public String toString() {
		return "AllergyHistories [allergyNum=" + allergyNum + ", allergyName="
				+ allergyName + "]";
	}
	
}
