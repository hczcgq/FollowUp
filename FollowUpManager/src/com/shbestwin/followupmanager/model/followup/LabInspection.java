package com.shbestwin.followupmanager.model.followup;

/**
 * 实验室检查
 * @author chenguoquan
 *
 */
public class LabInspection {

    private String sysjc_sysjcxm;

    private String sysjc_jcjg;

    private String sysjc_jcr;

    private String sysjc_jcsj;

    public LabInspection() {
        super();
    }

    public LabInspection(String sysjc_sysjcxm, String sysjc_jcjg,
            String sysjc_jcr, String sysjc_jcsj) {
        super();
        this.sysjc_sysjcxm = sysjc_sysjcxm;
        this.sysjc_jcjg = sysjc_jcjg;
        this.sysjc_jcr = sysjc_jcr;
        this.sysjc_jcsj = sysjc_jcsj;
    }



    public String getSysjc_sysjcxm() {
        return sysjc_sysjcxm;
    }



    public void setSysjc_sysjcxm(String sysjc_sysjcxm) {
        this.sysjc_sysjcxm = sysjc_sysjcxm;
    }



    public String getSysjc_jcjg() {
        return sysjc_jcjg;
    }



    public void setSysjc_jcjg(String sysjc_jcjg) {
        this.sysjc_jcjg = sysjc_jcjg;
    }



    public String getSysjc_jcr() {
        return sysjc_jcr;
    }



    public void setSysjc_jcr(String sysjc_jcr) {
        this.sysjc_jcr = sysjc_jcr;
    }



    public String getSysjc_jcsj() {
        return sysjc_jcsj;
    }



    public void setSysjc_jcsj(String sysjc_jcsj) {
        this.sysjc_jcsj = sysjc_jcsj;
    }



    @Override
    public String toString() {
        return "sysjc [sysjc_sysjcxm=" + sysjc_sysjcxm + ", sysjc_jcjg=" + sysjc_jcjg
                + ", sysjc_jcr=" + sysjc_jcr + ", sysjc_jcsj=" + sysjc_jcsj + "]";
    }

}
