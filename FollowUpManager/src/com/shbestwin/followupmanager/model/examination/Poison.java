package com.shbestwin.followupmanager.model.examination;

public class Poison {

	private String name;

	private String ismeasure;

	private String measurename;

	public Poison() {
		super();
	}

	public Poison(String name, String ismeasure, String measurename) {
		super();
		this.name = name;
		this.ismeasure = ismeasure;
		this.measurename = measurename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsmeasure() {
		return ismeasure;
	}

	public void setIsmeasure(String ismeasure) {
		this.ismeasure = ismeasure;
	}

	public String getMeasurename() {
		return measurename;
	}

	public void setMeasurename(String measurename) {
		this.measurename = measurename;
	}

	
}
