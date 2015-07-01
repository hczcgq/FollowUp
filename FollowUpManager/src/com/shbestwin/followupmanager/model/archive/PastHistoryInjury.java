package com.shbestwin.followupmanager.model.archive;

/**
 * 
 * @ClassName: PastHistoryInjury
 * @Description: 既往史-外伤
 * @author junbin.he
 * @date 2015年1月22日 下午1:36:26
 *
 */
public class PastHistoryInjury {
	private String name;
	private String type;
	private String date;// 手术日期
	private String onsetDate;// 发病日期
	private String treatResult;// 治疗结果
	private String reason;// 手术原因

	public PastHistoryInjury() {
	}

	public PastHistoryInjury(String name, String type, String date, String onsetDate, String treatResult, String reason) {
		super();
		this.name = name;
		this.type = type;
		this.date = date;
		this.onsetDate = onsetDate;
		this.treatResult = treatResult;
		this.reason = reason;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
		return "PastHistoryInjury [name=" + name + "name, type=" + type + "type, date=" + date + "date, onsetDate=" + onsetDate + "onsetDate, treatResult=" + treatResult + "treatResult, reason=" + reason + "reason,]";
	}
}
