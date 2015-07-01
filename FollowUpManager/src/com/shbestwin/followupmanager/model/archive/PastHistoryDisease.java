package com.shbestwin.followupmanager.model.archive;

/**
 * 
 * @ClassName: PastHistoryDisease
 * @Description: 既往史-疾病
 * @author junbin.he
 * @date 2015年1月22日 下午1:36:07
 *
 */
public class PastHistoryDisease {
	private String name;// 疾病名称
	private String type;// 疾病类型
	private String date;// 疾病日期 既是 确诊日期
	private String onsetDate;// 发病日期
	private String treatResult;// 治疗结果

	public PastHistoryDisease() {
	}

	public PastHistoryDisease(String name, String type, String date, String onsetDate, String treatResult) {
		super();
		this.name = name;
		this.type = type;
		this.date = date;
		this.onsetDate = onsetDate;
		this.treatResult = treatResult;
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

	@Override
	public String toString() {
		return "PastHistoryDisease [name=" + name + "name, type=" + type + "type, date=" + date + "date, onsetDate=" + onsetDate + "onsetDate, treatResult=" + treatResult + "treatResult,]";
	}
}
