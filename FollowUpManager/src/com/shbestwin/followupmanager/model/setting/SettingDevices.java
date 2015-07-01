package com.shbestwin.followupmanager.model.setting;

import java.util.List;

import com.google.gson.annotations.Expose;

public class SettingDevices {
	@Expose
	private Device selected;

	@Expose
	private List<Device> all;

	public Device getSelected() {
		return selected;
	}

	public void setSelected(Device selected) {
		this.selected = selected;
	}

	public List<Device> getAll() {
		return all;
	}

	public void setAll(List<Device> all) {
		this.all = all;
	}

}
