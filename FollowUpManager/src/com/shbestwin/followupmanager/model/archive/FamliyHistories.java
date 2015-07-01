package com.shbestwin.followupmanager.model.archive;
/**
 * 
 * @author cheng.shao
 * @Description 家族史
 */
public class FamliyHistories {
	private String diseaseNum;
	private String diseaseName;
	
	public FamliyHistories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FamliyHistories(String diseaseNum, String diseaseName) {
		super();
		this.diseaseNum = diseaseNum;
		this.diseaseName = diseaseName;
	}

	public String getDiseaseNum() {
		return diseaseNum;
	}

	public void setDiseaseNum(String diseaseNum) {
		this.diseaseNum = diseaseNum;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	@Override
	public String toString() {
		return "FamliyHistories [diseaseNum=" + diseaseNum + ", diseaseName="
				+ diseaseName + "]";
	}
	
}
