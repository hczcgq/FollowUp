package com.shbestwin.followupmanager.model;

public class VersionItem {
	
    private String downPath;
    private String desc;
    private String isoptional;
    private String success;
    private String msg;
    private String version;
    
    public VersionItem() {
        super();
    }

	public VersionItem(String downPath, String desc, String isoptional,
			String success, String msg, String version) {
		super();
		this.downPath = downPath;
		this.desc = desc;
		this.isoptional = isoptional;
		this.success = success;
		this.msg = msg;
		this.version = version;
	}

	public String getDownPath() {
		return downPath;
	}

	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIsoptional() {
		return isoptional;
	}

	public void setIsoptional(String isoptional) {
		this.isoptional = isoptional;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

    
}
