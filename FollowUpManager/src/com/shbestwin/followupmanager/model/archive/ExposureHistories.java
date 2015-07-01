package com.shbestwin.followupmanager.model.archive;
/**
 * 
 * @author cheng.shao
 * @Description 暴露史
 */
public class ExposureHistories {
	private String exposureNum;//编号
	private String exposureName;//暴露史名称
	public ExposureHistories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExposureHistories(String exposureNum, String exposureName) {
		super();
		this.exposureNum = exposureNum;
		this.exposureName = exposureName;
	}
	@Override
	public String toString() {
		return "ExposureHistories [exposureNum=" + exposureNum
				+ ", exposureName=" + exposureName + "]";
	}
	public String getExposureNum() {
		return exposureNum;
	}
	public void setExposureNum(String exposureNum) {
		this.exposureNum = exposureNum;
	}
	public String getExposureName() {
		return exposureName;
	}
	public void setExposureName(String exposureName) {
		this.exposureName = exposureName;
	}
}
