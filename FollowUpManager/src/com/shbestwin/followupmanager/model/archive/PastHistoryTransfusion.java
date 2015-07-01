package com.shbestwin.followupmanager.model.archive;

/**
 * 
 * @ClassName: PastHistoryTransfusion
 * @Description: 既往史-输血
 * @author junbin.he
 * @date 2015年1月22日 下午1:37:04
 *
 */
public class PastHistoryTransfusion {
	private String name;
	private String type;
	private String date;// 输血日期
	private String onsetDate;// 发病日期
	private String treatResult;// 治疗结果
	private String reason;// 输血原因
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
	public PastHistoryTransfusion(String name, String type, String date,
			String onsetDate, String treatResult, String reason) {
		super();
		this.name = name;
		this.type = type;
		this.date = date;
		this.onsetDate = onsetDate;
		this.treatResult = treatResult;
		this.reason = reason;
	}
	
	public PastHistoryTransfusion() {
		
	}
	@Override
	public String toString() {
		return "PastHistoryTransfusion [name=" + name + "name, type=" + type
				+ "type, date=" + date + "date, onsetDate=" + onsetDate
				+ "onsetDate, treatResult=" + treatResult + "treatResult, reason=" + reason + "reason,]";
	}
}
