package com.shbestwin.followupmanager.model.followup;

public class FimalyHistoryCa {
	private String jzszl_num;// 编号
	private String jzszl_name;// 过敏史名称

	public FimalyHistoryCa() {
		super();
	}

	public FimalyHistoryCa(String jzszl_num, String jzszl_name) {
		super();
		this.jzszl_num = jzszl_num;
		this.jzszl_name = jzszl_name;
	}

	public String getJzszl_num() {
		return jzszl_num;
	}

	public void setJzszl_num(String jzszl_num) {
		this.jzszl_num = jzszl_num;
	}

	public String getJzszl_name() {
		return jzszl_name;
	}

	public void setJzszl_name(String jzszl_name) {
		this.jzszl_name = jzszl_name;
	}

	@Override
	public String toString() {
		return "jzszl [jzszl_num=" + jzszl_num + ", jzszl_name=" + jzszl_name	 + "]";
	}

}
