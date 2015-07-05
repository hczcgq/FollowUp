package com.shbestwin.followupmanager.model.followup;

public class CheckBoxItem {
	private String item_num;// 编号

	private String item_name;// 名称

	public CheckBoxItem() {
		super();
	}

	public CheckBoxItem(String item_num, String item_name) {
		super();
		this.item_num = item_num;
		this.item_name = item_name;
	}

	public String getItem_num() {
		return item_num;
	}

	public void setItem_num(String item_num) {
		this.item_num = item_num;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	@Override
	public String toString() {
		return "jws [item_num=" + item_num + ", item_name=" + item_name + "]";
	}

}
