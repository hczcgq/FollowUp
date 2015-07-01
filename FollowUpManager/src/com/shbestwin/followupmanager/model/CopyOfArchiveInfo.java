package com.shbestwin.followupmanager.model;

public class CopyOfArchiveInfo {
	//定义规则，终端编号+yyyyMMdd+三位顺序数字，比如当天有5个人 第一个建档的就是001，以此类推002 003 004 005
	private String archiveNo;// 档案编号（编号的规则是？）
	private String cardNo;// 卡号（指什么卡号？）

	// 身份证读卡器能读取的字段
	private String name;// 姓名
	private String idcard;// 身份号码
	private String gendar;// 性别（未知、男、女）
	private String birthday;// 出生日期
	private String ethnic;// 民族
	private String picturePath;// 图像

	private String relationship;// 和户主的关系
	private String workUnit;// 工作单位
	private String telephone;// 本人电话
	private String contactName;// 联系人姓名
	private String contactPhone;// 联系人电话
	private String email;// 电子邮箱
	private String postcode;// 邮编
	private String residentType;// 常住类型（户籍、非户籍、其他）

	private String nationality;// 国籍
	private String certificateType;// 证件类型
	private String bloodType;// 血型
	private String RHNegative;// RH阴性（否、是、不祥）
	private String education;// 文化程度
	private String job;// 职业
	private String marriage;// 婚姻状况
	private String insuranceType;// 医疗费用支付方式
	private String assistType;// 援助类型
	private String drugAllergy;// 药物过敏史
	private String exposure;// 暴露史

	private String residentAddress;// 户籍地址
	private String familyAddress;// 家庭地址
	private String negativeEvent;// 负性事件
	private String managerUnit;// 管理单位
	private String createDate;// 建档日期（年月日）
}
