package com.shbestwin.followupmanager.model.followup;

/**
 * 辅助检查
 * @author chenguoquan
 *
 */
public class Inspection {

    private String fzjc_fzjcxm;

    private String fzjc_jcjg;

    private String fzjc_jcr;

    private String fzjc_jcsj;
    
    public Inspection() {
    }

    public Inspection(String fzjc_fzjcxm, String fzjc_jcjg, String fzjc_jcr,
            String fzjc_jcsj) {
        super();
        this.fzjc_fzjcxm = fzjc_fzjcxm;
        this.fzjc_jcjg = fzjc_jcjg;
        this.fzjc_jcr = fzjc_jcr;
        this.fzjc_jcsj = fzjc_jcsj;
    }

    public String getFzjc_fzjcxm() {
        return fzjc_fzjcxm;
    }

    public void setFzjc_fzjcxm(String fzjc_fzjcxm) {
        this.fzjc_fzjcxm = fzjc_fzjcxm;
    }

    public String getFzjc_jcjg() {
        return fzjc_jcjg;
    }

    public void setFzjc_jcjg(String fzjc_jcjg) {
        this.fzjc_jcjg = fzjc_jcjg;
    }

    public String getFzjc_jcr() {
        return fzjc_jcr;
    }

    public void setFzjc_jcr(String fzjc_jcr) {
        this.fzjc_jcr = fzjc_jcr;
    }

    public String getFzjc_jcsj() {
        return fzjc_jcsj;
    }

    public void setFzjc_jcsj(String fzjc_jcsj) {
        this.fzjc_jcsj = fzjc_jcsj;
    }

    @Override
    public String toString() {
        return "fzjc [fzjc_fzjcxm=" + fzjc_fzjcxm + ", fzjc_jcjg=" + fzjc_jcjg
                + ", fzjc_jcr=" + fzjc_jcr + ", fzjc_jcsj=" + fzjc_jcsj + "]";
    }

}
