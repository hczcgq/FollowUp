package com.shbestwin.followupmanager.model.followup;

public class Disability {
    private String cjnx_dccj_num;// 编号

    private String cjnx_dccj_name;// 过敏史名称

    public Disability() {
        super();
    }

    public Disability(String cjnx_dccj_num, String cjnx_dccj_name) {
        super();
        this.cjnx_dccj_num = cjnx_dccj_num;
        this.cjnx_dccj_name = cjnx_dccj_name;
    }

    public String getCjnx_dccj_num() {
        return cjnx_dccj_num;
    }

    public void setCjnx_dccj_num(String cjnx_dccj_num) {
        this.cjnx_dccj_num = cjnx_dccj_num;
    }

    public String getCjnx_dccj_name() {
        return cjnx_dccj_name;
    }

    public void setCjnx_dccj_name(String cjnx_dccj_name) {
        this.cjnx_dccj_name = cjnx_dccj_name;
    }

    @Override
    public String toString() {
        return "jws [cjnx_dccj_num=" + cjnx_dccj_num + ", cjnx_dccj_name="
                + cjnx_dccj_name + "]";
    }

}
