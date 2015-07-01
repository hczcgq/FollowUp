package com.shbestwin.followupmanager.model.followup;

public class PastHistory {
	private String jws_num;// 编号
	private String jws_name;// 过敏史名称

	public PastHistory() {
		super();
	}

	public PastHistory(String jws_num, String jws_name) {
		super();
		this.jws_num = jws_num;
		this.jws_name = jws_name;
	}

	public String getJws_num() {
		return jws_num;
	}

	public void setJws_num(String jws_num) {
		this.jws_num = jws_num;
	}

	public String getJws_name() {
		return jws_name;
	}

	public void setJws_name(String jws_name) {
		this.jws_name = jws_name;
	}

	@Override
	public String toString() {
		return "jws [jws_num=" + jws_num + ", jws_name=" + jws_name + "]";
	}

}
