package com.shbestwin.followupmanager.model.device;

public class BloodOximeter {
	private int bloodOxygen;// 血氧值
	private int pulseRate;// 脉率（次/分）

	public BloodOximeter() {
	}

	public BloodOximeter(int bloodOxygen, int pulseRate) {
		super();
		this.bloodOxygen = bloodOxygen;
		this.pulseRate = pulseRate;
	}

	public int getBloodOxygen() {
		return bloodOxygen;
	}

	public void setBloodOxygen(int bloodOxygen) {
		this.bloodOxygen = bloodOxygen;
	}

	public int getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(int pulseRate) {
		this.pulseRate = pulseRate;
	}

	// 小于90%为低血氧证。等于90%-94%为供氧不足，94%以上为血氧正常
	public static String getConclusion(int value) {
		if (value < 90) {
			return "低血氧症";
		} else if (value > 94) {
			return "血氧正常";
		} else {
			return "供氧不足";
		}
	}
}
