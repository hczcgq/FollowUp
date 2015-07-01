package com.shbestwin.followupmanager.model.archive;

public class Disability {
	private String disabilityNo;
	private DisabilityLevel disabilityLevel;
	private DisabilityType disabilityType;
	
	public Disability() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDisabilityNo() {
		return disabilityNo;
	}
	public void setDisabilityNo(String disabilityNo) {
		this.disabilityNo = disabilityNo;
	}
	public DisabilityLevel getDisabilityLevel() {
		return disabilityLevel;
	}
	public void setDisabilityLevel(DisabilityLevel disabilityLevel) {
		this.disabilityLevel = disabilityLevel;
	}
	public DisabilityType getDisabilityType() {
		return disabilityType;
	}
	public void setDisabilityType(DisabilityType disabilityType) {
		this.disabilityType = disabilityType;
	}
	public Disability(String disabilityNo, DisabilityLevel disabilityLevel,
			DisabilityType disabilityType) {
		super();
		this.disabilityNo = disabilityNo;
		this.disabilityLevel = disabilityLevel;
		this.disabilityType = disabilityType;
	}
	@Override
	public String toString() {
		return "Disability [disabilityNo=" + disabilityNo
				+ ", disabilityLevel=" + disabilityLevel + ", disabilityType="
				+ disabilityType + "]";
	}
	public class DisabilityLevel{
		private String levelNum;
		private String levelName;
		@Override
		public String toString() {
			return "DisabilityLevel [levelNum=" + levelNum + ", levelName="
					+ levelName + "]";
		}
		public DisabilityLevel(String levelNum, String levelName) {
			super();
			this.levelNum = levelNum;
			this.levelName = levelName;
		}
		public DisabilityLevel() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getLevelNum() {
			return levelNum;
		}
		public void setLevelNum(String levelNum) {
			this.levelNum = levelNum;
		}
		public String getLevelName() {
			return levelName;
		}
		public void setLevelName(String levelName) {
			this.levelName = levelName;
		}
	}
	public class DisabilityType{
		private String typeNum;
		private String typeName;
		public DisabilityType() {
			super();
			// TODO Auto-generated constructor stub
		}
		public DisabilityType(String typeNum, String typeName) {
			super();
			this.typeNum = typeNum;
			this.typeName = typeName;
		}
		public String getTypeNum() {
			return typeNum;
		}
		public void setTypeNum(String typeNum) {
			this.typeNum = typeNum;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		@Override
		public String toString() {
			return "DisabilityType [typeNum=" + typeNum + ", typeName="
					+ typeName + "]";
		}
	}
}
