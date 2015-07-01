package com.shbestwin.followupmanager.model.followup;

public class SelfHistory {
	private String grs_num;// 编号
	private String grs_name;// 过敏史名称

	public SelfHistory() {
		super();
	}

	public SelfHistory(String grs_num, String grs_name) {
		super();
		this.grs_num = grs_num;
		this.grs_name = grs_name;
	}

	public String getGrs_num() {
		return grs_num;
	}

	public void setGrs_num(String grs_num) {
		this.grs_num = grs_num;
	}

	public String getGrs_name() {
		return grs_name;
	}

	public void setGrs_name(String grs_name) {
		this.grs_name = grs_name;
	}

	@Override
	public String toString() {
		return "grs [grs_num=" + grs_num + ", grs_name=" + grs_name + "]";
	}

}
