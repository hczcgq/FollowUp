package com.shbestwin.followupmanager.model.device;

/**
 * 
 * @ClassName: BloodFat
 * @Description: 血脂
 *
 */
public class BloodFat {
	public static final int TYPE_BLOOD_GLUCOSE = 0x001;
	public static final int TYPE_BLOOD_CHOL = 0x002;
	public static final int TYPE_BLOOD_TG = 0x003;
	public static final int TYPE_BLOOD_HDL = 0x004;
	public static final int TYPE_BLOOD_LDL = 0x005;

	private int type;// 类型，包括：血糖
	private float bloodGlucose;// 血糖，范围：[0-100]，字节位置：[28-31]，单位，(mmol/L)
	private float bloodCHOL;// 血总胆固醇，范围：[0-100]，字节位置：[28-31]，单位，(mmol/L)
	private float bloodTG;// 血甘油三脂，范围：[0-100]，字节位置：[28-31]，单位，(mmol/L)
	private float bloodHDL;// 血高密度脂蛋白，范围：[0-100]，字节位置：[28-31]，单位，(mmol/L)
	private float bloodLDL;// 低密度脂蛋白

	public BloodFat() {
		super();
	}

	public BloodFat(int type, float bloodCHOL, float bloodTG, float bloodHDL) {
		super();
		this.type = type;
		this.bloodCHOL = bloodCHOL;
		this.bloodTG = bloodTG;
		this.bloodHDL = bloodHDL;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getBloodGlucose() {
		return bloodGlucose;
	}

	public void setBloodGlucose(float bloodGlucose) {
		this.bloodGlucose = bloodGlucose;
	}

	public float getBloodCHOL() {
		return bloodCHOL;
	}

	public void setBloodCHOL(float bloodCHOL) {
		this.bloodCHOL = bloodCHOL;
	}

	public float getBloodTG() {
		return bloodTG;
	}

	public void setBloodTG(float bloodTG) {
		this.bloodTG = bloodTG;
	}

	public float getBloodHDL() {
		return bloodHDL;
	}

	public void setBloodHDL(float bloodHDL) {
		this.bloodHDL = bloodHDL;
	}

	public float getBloodLDL() {
		return bloodLDL;
	}

	public void setBloodLDL(float bloodLDL) {
		this.bloodLDL = bloodLDL;
	}

	public static String getConclusion(int type, float value) {
		switch (type) {
		case TYPE_BLOOD_GLUCOSE:// 血糖
			// 3.90-6.10mmol/L
			if (value < 3.90) {
				return "偏低";
			} else if (value > 6.10) {
				return "偏高";
			} else {
				return "正常";
			}
		case TYPE_BLOOD_CHOL:// 血总胆固醇
			// 2.82-5.95mmol/L
			if (value < 2.82) {
				return "偏低";
			} else if (value > 5.95) {
				return "偏高";
			} else {
				return "正常";
			}
		case TYPE_BLOOD_TG:// 血甘油三脂
			// 0.56-1.70mmol/L
			if (value < 0.56) {
				return "偏低";
			} else if (value > 1.70) {
				return "偏高";
			} else {
				return "正常";
			}
		case TYPE_BLOOD_HDL:// 血高密度脂蛋白
			// 1.16-1.42mmol/L
			if (value < 1.16) {
				return "偏低";
			} else if (value > 1.42) {
				return "偏高";
			} else {
				return "正常";
			}
		case TYPE_BLOOD_LDL:// 低密度脂蛋白
			// 低密度脂蛋白胆固醇=总胆固醇-高密度脂蛋白胆固醇-（甘油三酯/2.2）
			// 正常参考范围：2.1-3.1。
			if (value < 2.1) {
				return "偏低";
			} else if (value > 3.1) {
				return "偏高";
			} else {
				return "正常";
			}
		}

		return "";
	}
}
