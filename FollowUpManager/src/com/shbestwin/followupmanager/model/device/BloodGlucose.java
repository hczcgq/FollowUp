package com.shbestwin.followupmanager.model.device;

/**
 * 
 * @ClassName: BloodGlucose
 * @Description: 血糖
 *
 */
public class BloodGlucose {
	public static final int TYPE_EMPTY_STOMACH = 0;// 空腹血糖
	public static final int TYPE_AFTER_2HOUR = 1;// 餐后2小时血糖

	private int type;

	private float bloodGlucose;

	public BloodGlucose() {
	}

	public BloodGlucose(float bloodGlucose) {
		super();
		this.bloodGlucose = bloodGlucose;
	}

	public float getBloodGlucose() {
		return bloodGlucose;
	}

	public void setBloodGlucose(float bloodGlucose) {
		this.bloodGlucose = bloodGlucose;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BloodGlucose [type=" + type + ", bloodGlucose=" + bloodGlucose + "]";
	}

	public static String getConclusion(int type, float value) {
		switch (type) {
		// 空腹血糖:正常为3.9～6.1毫摩尔/升。小于3.9毫摩尔/升为低血糖，大于6.1-6.9mmol/L为血糖偏高，大于6.9mmol/L为高血糖
		case TYPE_EMPTY_STOMACH:
			if (value < 3.9) {
				return "低血糖";
			} else if (value < 6.1) {
				return "血糖正常";
			} else if (value <= 6.9) {
				return "血糖偏高";
			} else {
				return "高血糖";
			}
			// 餐后2小时血糖：餐后2小时：正常为3.9～7.8毫摩尔/升，小于3.9毫摩尔/升为低血糖，,大于7.8-11mmol/L为血糖偏高，大于11.1mmol/L为高血糖
		case TYPE_AFTER_2HOUR:
			if (value < 3.9) {
				return "低血糖";
			} else if (value < 7.9) {
				return "血糖正常";
			} else if (value <= 11) {
				return "血糖偏高";
			} else {
				return "高血糖";
			}
		}
		return "";
	}
}
