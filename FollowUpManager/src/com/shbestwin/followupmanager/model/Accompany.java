package com.shbestwin.followupmanager.model;

public class Accompany {
	
	public static final int ACNO_REPORTHYPERTENSION=100;
	public static final int ACNO_REPORTDIABETESMELLITUS=101;
	public static final int ACNO_HYPERTENSION=102;
	public static final int ACNO_DIABETESMELLITUS=103;
	public static final int ACNO_CEREBRALAPOPLWXY=104;
	public static final int ACNO_MENTALDISEASE=105;
	public static final int ACNO_ANTENATAL1=106;
	public static final int ACNO_ANTENATAL2_5=107;
	public static final int ACNO_INSPECT42=108;
	public static final int ACNO_POSTPARTUM=109;
	public static final int ACNO_NEONATE=110;
	public static final int ACNO_YEAESONLD0=111;
	public static final int ACNO_YEAESONLD1_2=112;
	public static final int ACNO_YEAESONLD3_6=113;
	public static final int ACNO_AGEDNESS=114;
	public static final int ACNO_DISABILITY=115;
	
	
	
	private String accompanyNo;// 编号
	private String idcard;// 身份号码
	private String name;// 姓名
	private String gender;// 性别（未知、男、女）
	private String birthday;// 出生日期
	private String ethnic;// 民族
	private String telephone;// 本人电话
	private String address;// 家庭地址
	private String current_time; // 本月随访时间
	private String next_time; // 下次随访时间
	private String accompany_item;// 随访项
	private String create_time;// 创建时间
	private String update_time;// 更新时间
	private String reported;// 是否随访

	
	
	
	public Accompany() {
		super();
	}

	public Accompany(String accompanyNo,String idcard, String name, String gender,
			String birthday, String ethnic, String telephone, String address,
			String current_time, String next_time, String accompany_item,
			String create_time, String update_time,String reported) {
		super();
		this.accompanyNo=accompanyNo;
		this.idcard = idcard;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.ethnic = ethnic;
		this.telephone = telephone;
		this.address = address;
		this.current_time = current_time;
		this.next_time = next_time;
		this.accompany_item = accompany_item;
		this.create_time = create_time;
		this.update_time = update_time;
		this.reported = reported;
	}

	public String getAccompanyNo() {
		return accompanyNo;
	}

	public void setAccompanyNo(String accompanyNo) {
		this.accompanyNo = accompanyNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
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

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCurrent_time() {
		return current_time;
	}

	public void setCurrent_time(String current_time) {
		this.current_time = current_time;
	}

	public String getNext_time() {
		return next_time;
	}

	public void setNext_time(String next_time) {
		this.next_time = next_time;
	}

	public String getAccompany_item() {
		return accompany_item;
	}

	public void setAccompany_item(String accompany_item) {
		this.accompany_item = accompany_item;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

    public String getReported() {
        return reported;
    }

    public void setReported(String reported) {
        this.reported = reported;
    }
}
