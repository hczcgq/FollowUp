package com.shbestwin.followupmanager.model.archive;

/**
 * 
 * @ClassName: PastHistory
 * @Description: 既往史
 * @author junbin.he
 * @date 2015年1月21日 下午1:55:03
 *
 */
public class PastHistory {
	public static final int TYPE_DISEASE = 1;// 疾病
	public static final int TYPE_OPRATION = 2;// 手术
	public static final int TYPE_INJURY = 3;// 外伤
	public static final int TYPE_TRANSFUSION = 4;// 输血

	private int type;// 既往史类型（1：疾病 2：手术 3：外伤 4：输血）
	private String name;// 既往史名称
	private String date;// 操作日期（确诊时间、手术时间）
	private String subType;// 类型
	private String onsetDate;// 发病日期
	private String treatResult;// 治疗结果
	private String reason;// 手术原因

	public PastHistory() {
	}

	public PastHistory(int type, String name, String date, String subType, String onsetDate, String treatResult, String reason) {
		super();
		this.type = type;
		this.name = name;
		this.date = date;
		this.subType = subType;
		this.onsetDate = onsetDate;
		this.treatResult = treatResult;
		this.reason = reason;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getOnsetDate() {
		return onsetDate;
	}

	public void setOnsetDate(String onsetDate) {
		this.onsetDate = onsetDate;
	}

	public String getTreatResult() {
		return treatResult;
	}

	public void setTreatResult(String treatResult) {
		this.treatResult = treatResult;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "PastHistory [type=" + type + ", name=" + name + ", date=" + date + ", subType=" + subType + ", onsetDate=" + onsetDate + ", treatResult=" + treatResult + ", reason=" + reason + "]";
	}

}
