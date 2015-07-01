package com.shbestwin.followupmanager.model.followup;

public class Insulin {

    private String yds_ywzl;
    private String yds_sypl;
    private String yds_syjl;
    
    public Insulin() {
        super();
    }

    public Insulin(String yds_ywzl, String yds_sypl, String yds_syjl) {
        super();
        this.yds_ywzl = yds_ywzl;
        this.yds_sypl = yds_sypl;
        this.yds_syjl = yds_syjl;
    }

    public String getYds_ywzl() {
        return yds_ywzl;
    }

    public void setYds_ywzl(String yds_ywzl) {
        this.yds_ywzl = yds_ywzl;
    }

    public String getYds_sypl() {
        return yds_sypl;
    }

    public void setYds_sypl(String yds_sypl) {
        this.yds_sypl = yds_sypl;
    }

    public String getYds_syjl() {
        return yds_syjl;
    }

    public void setYds_syjl(String yds_syjl) {
        this.yds_syjl = yds_syjl;
    }
    @Override
    public String toString() {
        return "yds [yds_ywzl=" + yds_ywzl + ", yds_sypl=" + yds_sypl
                + ", yds_syjl=" + yds_syjl + "]";
    }
    
    
}
