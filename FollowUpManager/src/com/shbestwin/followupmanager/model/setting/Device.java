package com.shbestwin.followupmanager.model.setting;

import com.google.gson.annotations.Expose;

public class Device {
	public static final String TYPE_ID_IDCARD = "02";//身份证阅读器
	public static final String TYPE_ID_WAISTLINE = "03";//腰围尺
	public static final String TYPE_ID_BLOOD_PRESSURE = "04";//血压计
	public static final String TYPE_ID_BLOOD_GLUCOSE = "05";//血糖仪
	public static final String TYPE_ID_BLOOD_FAT = "06";//干式血尿生化分析仪
	public static final String TYPE_ID_FINGER_OXIMETER = "07";//手指血氧仪
	public static final String TYPE_ID_ELECTROCARDIOGRAM = "08";//心电仪
	public static final String TYPE_ID_BODY_COMPOSITION = "09";//脂肪秤
	public static final String TYPE_ID_PRINTER = "10";//蓝牙打印机
	
	@Expose
	private String code;
	@Expose
	private String name;
	@Expose
	private String bluetoothName;
	@Expose
	private String brand;
	@Expose
	private boolean isUsing;
	@Expose
	private String model;
	@Expose
	private String typeName;
	@Expose
	private String typeId;

	public Device() {
	}

	public Device(String code, String name, String bluetoothName, String brand, boolean isUsing, String model, String typeName, String typeId) {
		super();
		this.code = code;
		this.name = name;
		this.bluetoothName = bluetoothName;
		this.brand = brand;
		this.isUsing = isUsing;
		this.model = model;
		this.typeName = typeName;
		this.typeId = typeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBluetoothName() {
		return bluetoothName;
	}

	public void setBluetoothName(String bluetoothName) {
		this.bluetoothName = bluetoothName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isUsing() {
		return isUsing;
	}

	public void setUsing(boolean isUsing) {
		this.isUsing = isUsing;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Device [code=" + code + ", name=" + name + ", bluetoothName=" + bluetoothName + ", brand=" + brand + ", isUsing=" + isUsing + ", model=" + model + ", typeName=" + typeName + ", typeId=" + typeId + "]";
	}

}
