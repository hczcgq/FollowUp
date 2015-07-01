package com.shbestwin.followupmanager.model;

public class MenuItem {
	private int iconResId;
	private String title;

	public MenuItem() {
	}

	public MenuItem(int iconResId, String title) {
		super();
		this.iconResId = iconResId;
		this.title = title;
	}

	public int getIconResId() {
		return iconResId;
	}

	public void setIconResId(int iconResId) {
		this.iconResId = iconResId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
