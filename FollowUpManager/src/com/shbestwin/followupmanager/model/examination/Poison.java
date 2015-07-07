package com.shbestwin.followupmanager.model.examination;

public class Poison {

    private String name;

    private boolean measures;

    private String measuresName;
    
    

    public Poison() {
        super();
    }

    public Poison(String name, boolean measures, String measuresName) {
        super();
        this.name = name;
        this.measures = measures;
        this.measuresName = measuresName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMeasures() {
        return measures;
    }

    public void setMeasures(boolean measures) {
        this.measures = measures;
    }

    public String getMeasuresName() {
        return measuresName;
    }

    public void setMeasuresName(String measuresName) {
        this.measuresName = measuresName;
    }

}
