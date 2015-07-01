package com.shbestwin.followupmanager.model.followup;

public class PostpartumRight {
	private String rfjc_ycrx_num;// 编号
	private String rfjc_ycrx_name;// 过敏史名称

	public PostpartumRight() {
		super();
	}

	public PostpartumRight(String rfjc_ycrx_num, String rfjc_ycrx_name) {
		super();
		this.rfjc_ycrx_num = rfjc_ycrx_num;
		this.rfjc_ycrx_name = rfjc_ycrx_name;
	}

	public String getRfjc_ycrx_num() {
		return rfjc_ycrx_num;
	}

	public void setRfjc_ycrx_num(String rfjc_ycrx_num) {
		this.rfjc_ycrx_num = rfjc_ycrx_num;
	}

	public String getRfjc_ycrx_name() {
		return rfjc_ycrx_name;
	}

	public void setRfjc_ycrx_name(String rfjc_ycrx_name) {
		this.rfjc_ycrx_name = rfjc_ycrx_name;
	}

	@Override
	public String toString() {
		return "rfjc_ycrx [rfjc_ycrx_num=" + rfjc_ycrx_num + ", rfjc_ycrx_name=" + rfjc_ycrx_name + "]";
	}

}
