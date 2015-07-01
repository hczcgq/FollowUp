package com.shbestwin.followupmanager.model.device;

public class BloodPressure {

	private int systolicPressure;// 收缩压
	private int diastolicPressure;// 舒张压
	private int pulseRate;// 脉率（次/分）

	public BloodPressure() {
	}

	public BloodPressure(int systolicPressure, int diastolicPressure, int pulseRate) {
		super();
		this.systolicPressure = systolicPressure;
		this.diastolicPressure = diastolicPressure;
		this.pulseRate = pulseRate;
	}

	public int getSystolicPressure() {
		return systolicPressure;
	}

	public void setSystolicPressure(int systolicPressure) {
		this.systolicPressure = systolicPressure;
	}

	public int getDiastolicPressure() {
		return diastolicPressure;
	}

	public void setDiastolicPressure(int diastolicPressure) {
		this.diastolicPressure = diastolicPressure;
	}

	public int getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(int pulseRate) {
		this.pulseRate = pulseRate;
	}

	@Override
	public String toString() {
		return "BloodPressure [systolicPressure=" + systolicPressure + ", diastolicPressure=" + diastolicPressure + ", pulseRate=" + pulseRate + "]";
	}
	
	public String getConclusion() {
		//TODO 增加血压的结论
		return "";
	}

}
