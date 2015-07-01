package com.shbestwin.followupmanager.model.followup;

public class Adedness {

    private String zz_num;
    private String zz_name;
    
    public Adedness() {
        super();
    }
    
    


    public Adedness(String zz_num, String zz_name) {
		super();
		this.zz_num = zz_num;
		this.zz_name = zz_name;
	}




	public String getZz_num() {
		return zz_num;
	}


	public void setZz_num(String zz_num) {
		this.zz_num = zz_num;
	}


	public String getZz_name() {
		return zz_name;
	}


	public void setZz_name(String zz_name) {
		this.zz_name = zz_name;
	}


	@Override
    public String toString() {
        return "zz [zz_num=" + zz_num + ", zz_name=" + zz_name
                +  "]";
    }
    
    
}
