package com.shbestwin.followupmanager.model;

public class UploadManageItem {
    private String name;

    private String state;

    private String create_time;
    
    public UploadManageItem() {
        super();
    }

    public UploadManageItem(String name, String state, String create_time) {
        super();
        this.name = name;
        this.state = state;
        this.create_time = create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
