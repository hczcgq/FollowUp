package com.shbestwin.followupmanager.model.followup;

public class Medication {
    private String yyqk_xh; // 序号

    private String yyqk_ywmc; // 药物名称

    private String yyqk_ywlx; // 药物类型

    private String yyqk_yl; // 用量

    private String yyqk_dw; // 单位

    private String yyqk_yf; // 用法

    private String yyqk_syzjl; // 使用总剂量

    private String yyqk_gyfs; // 给药方式

    public Medication() {
        super();
    }

    public Medication(String yyqk_xh, String yyqk_ywmc, String yyqk_ywlx,
            String yyqk_yl, String yyqk_dw, String yyqk_yf, String yyqk_syzjl,
            String yyqk_gyfs) {
        super();
        this.yyqk_xh = yyqk_xh;
        this.yyqk_ywmc = yyqk_ywmc;
        this.yyqk_ywlx = yyqk_ywlx;
        this.yyqk_yl = yyqk_yl;
        this.yyqk_dw = yyqk_dw;
        this.yyqk_yf = yyqk_yf;
        this.yyqk_syzjl = yyqk_syzjl;
        this.yyqk_gyfs = yyqk_gyfs;
    }

    public String getYyqk_xh() {
        return yyqk_xh;
    }

    public void setYyqk_xh(String yyqk_xh) {
        this.yyqk_xh = yyqk_xh;
    }

    public String getYyqk_ywmc() {
        return yyqk_ywmc;
    }

    public void setYyqk_ywmc(String yyqk_ywmc) {
        this.yyqk_ywmc = yyqk_ywmc;
    }

    public String getYyqk_ywlx() {
        return yyqk_ywlx;
    }

    public void setYyqk_ywlx(String yyqk_ywlx) {
        this.yyqk_ywlx = yyqk_ywlx;
    }

    public String getYyqk_yl() {
        return yyqk_yl;
    }

    public void setYyqk_yl(String yyqk_yl) {
        this.yyqk_yl = yyqk_yl;
    }

    public String getYyqk_dw() {
        return yyqk_dw;
    }

    public void setYyqk_dw(String yyqk_dw) {
        this.yyqk_dw = yyqk_dw;
    }

    public String getYyqk_yf() {
        return yyqk_yf;
    }

    public void setYyqk_yf(String yyqk_yf) {
        this.yyqk_yf = yyqk_yf;
    }

    public String getYyqk_syzjl() {
        return yyqk_syzjl;
    }

    public void setYyqk_syzjl(String yyqk_syzjl) {
        this.yyqk_syzjl = yyqk_syzjl;
    }

    public String getYyqk_gyfs() {
        return yyqk_gyfs;
    }

    public void setYyqk_gyfs(String yyqk_gyfs) {
        this.yyqk_gyfs = yyqk_gyfs;
    }

    @Override
    public String toString() {
        return "yyqk [yyqk_xh=" + yyqk_xh + ", yyqk_ywmc=" + yyqk_ywmc
                + ", yyqk_ywlx=" + yyqk_ywlx + ", yyqk_yl=" + yyqk_yl
                + ", yyqk_dw=" + yyqk_dw + ", yyqk_yf=" + yyqk_yf
                + ", yyqk_syzjl=" + yyqk_syzjl + ", yyqk_gyfs=" + yyqk_gyfs
                + "]";
    }

}
