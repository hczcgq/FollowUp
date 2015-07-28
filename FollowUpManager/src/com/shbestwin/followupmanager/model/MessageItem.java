package com.shbestwin.followupmanager.model;

public class MessageItem {
	private String success;
	private String msg;

	public MessageItem() {
	}

	public MessageItem(String success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
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
}
