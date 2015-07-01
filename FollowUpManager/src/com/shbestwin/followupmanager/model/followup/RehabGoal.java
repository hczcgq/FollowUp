package com.shbestwin.followupmanager.model.followup;

public class RehabGoal {
	private String kffw_kfmb_num;// 编号
	private String kffw_kfmb_name;//名称

	public RehabGoal() {
		super();
	}

	public RehabGoal(String kffw_kfmb_num, String kffw_kfmb_name) {
        super();
        this.kffw_kfmb_num = kffw_kfmb_num;
        this.kffw_kfmb_name = kffw_kfmb_name;
    }

    public String getKffw_kfmb_num() {
        return kffw_kfmb_num;
    }

    public void setKffw_kfmb_num(String kffw_kfmb_num) {
        this.kffw_kfmb_num = kffw_kfmb_num;
    }

    public String getKffw_kfmb_name() {
        return kffw_kfmb_name;
    }

    public void setKffw_kfmb_name(String kffw_kfmb_name) {
        this.kffw_kfmb_name = kffw_kfmb_name;
    }

    @Override
	public String toString() {
		return "kffw_kfmb [kffw_kfmb_num=" + kffw_kfmb_num + ", kffw_kfmb_name=" + kffw_kfmb_name + "]";
	}

}
