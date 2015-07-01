package com.shbestwin.followupmanager.model.archive;

public class FamilyMember {
	private String name;// 姓名
	private String gender;// 性别
	private String birthday;// 出生日期
	private String relationship;// 与户主关系
	private String education;// 文化程度
	private String job;// 职业
	private String idcard;// 身份号码
	private String marriage;// 婚姻状况
	private String personalStatus;// 个人状况
	private String remark;// 备注

	public FamilyMember() {
	}

	public FamilyMember(String name, String gender, String birthday, String relationship, String education, String job, String idcard, String marriage, String personalStatus, String remark) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.relationship = relationship;
		this.education = education;
		this.job = job;
		this.idcard = idcard;
		this.marriage = marriage;
		this.personalStatus = personalStatus;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	

	public String getPersonalStatus() {
		return personalStatus;
	}

	public void setPersonalStatus(String personalStatus) {
		this.personalStatus = personalStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "FamilyMember [name=" + name + "name, gender=" + gender + "gender, birthday=" + birthday + "birthday, relationship=" + relationship + "relationship, education=" + education + "education, job=" + job + "job, idcard=" + idcard + "idcard, marriage=" + marriage + "marriage, personalStatus="
				+ personalStatus + "personalStatus, remark=" + remark + "remark,]";
	}
}
