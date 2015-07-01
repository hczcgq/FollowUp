package com.shbestwin.followupmanager.model.followup;

public class RehabProject {
	private String kffw_kfxm_num;// 编号
	private String kffw_kfxm_name;// 过敏史名称

	public RehabProject() {
		super();
	}

	

	public RehabProject(String kffw_kfxm_num, String kffw_kfxm_name) {
        super();
        this.kffw_kfxm_num = kffw_kfxm_num;
        this.kffw_kfxm_name = kffw_kfxm_name;
    }



    public String getKffw_kfxm_num() {
        return kffw_kfxm_num;
    }



    public void setKffw_kfxm_num(String kffw_kfxm_num) {
        this.kffw_kfxm_num = kffw_kfxm_num;
    }



    public String getKffw_kfxm_name() {
        return kffw_kfxm_name;
    }



    public void setKffw_kfxm_name(String kffw_kfxm_name) {
        this.kffw_kfxm_name = kffw_kfxm_name;
    }



    @Override
	public String toString() {
		return "kffw_kfxm [kffw_kfxm_num=" + kffw_kfxm_num + ", kffw_kfxm_name=" + kffw_kfxm_name + "]";
	}

}
