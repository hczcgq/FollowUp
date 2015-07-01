package com.shbestwin.followupmanager.model.followup;

public class PostpartumLeft {
	private String rfjc_zcrx_num;// 编号
	private String rfjc_zcrx_name;// 过敏史名称

	public PostpartumLeft() {
		super();
	}

	public PostpartumLeft(String rfjc_zcrx_num, String rfjc_zcrx_name) {
		super();
		this.rfjc_zcrx_num = rfjc_zcrx_num;
		this.rfjc_zcrx_name = rfjc_zcrx_name;
	}

	public String getRfjc_zcrx_num() {
		return rfjc_zcrx_num;
	}

	public void setRfjc_zcrx_num(String rfjc_zcrx_num) {
		this.rfjc_zcrx_num = rfjc_zcrx_num;
	}

	public String getRfjc_zcrx_name() {
		return rfjc_zcrx_name;
	}

	public void setRfjc_zcrx_name(String rfjc_zcrx_name) {
		this.rfjc_zcrx_name = rfjc_zcrx_name;
	}

	@Override
	public String toString() {
		return "rfjc_zcrx [rfjc_zcrx_num=" + rfjc_zcrx_num + ", rfjc_zcrx_name=" + rfjc_zcrx_name + "]";
	}

}
