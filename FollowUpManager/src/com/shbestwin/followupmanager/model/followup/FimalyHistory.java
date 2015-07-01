package com.shbestwin.followupmanager.model.followup;

public class FimalyHistory {
	private String jzslb_num;// 编号
	private String jzslb_name;// 过敏史名称

	public FimalyHistory() {
		super();
	}

	public FimalyHistory(String jzslb_num, String jzslb_name) {
		super();
		this.jzslb_num = jzslb_num;
		this.jzslb_name = jzslb_name;
	}

	

	public String getJzslb_num() {
		return jzslb_num;
	}

	public void setJzslb_num(String jzslb_num) {
		this.jzslb_num = jzslb_num;
	}

	public String getJzslb_name() {
		return jzslb_name;
	}

	public void setJzslb_name(String jzslb_name) {
		this.jzslb_name = jzslb_name;
	}

	@Override
	public String toString() {
		return "jzslb [jzslb_num=" + jzslb_num + ", jzslb_name=" + jzslb_name + "]";
	}

}
