package com.shbestwin.followupmanager.model.examination;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table EXAMINATION_INFO.
 */
public class ExaminationInfo {

	/** Not-null value. */
	private String examinationNo;
	/** Not-null value. */
	private String idcard;
	private String routineCheckups;
	private String bloodPressure;
	private String bloodSugar;
	private String bloodFat;
	private String bloodOxygen;
	private String ecgAnalysis;
	private String bodyComposition;
	private String physiqueIdentify;
	private String psychologicaAssessmentSDS;
	private String psychologicaAssessmentSAS;
	private String psychologicaAssessmentPSQI;
	private String psychologicaAssessmentSAQ;
	private String psychologicaAssessmentUCLA;
	private String psychologicaAssessmentGCQ;
	private String psychologicaAssessmentSCL90;
	private String psychologicaAssessmentQLSCA;
	private String agednessSelfcare;
	private String agednessDepression;
	private String agednessIntelligence;
	private String createTime;
	private String updateTime;

	// KEEP FIELDS - put your custom fields here
	// KEEP FIELDS END

	public ExaminationInfo() {
	}

	public ExaminationInfo(String examinationNo) {
		this.examinationNo = examinationNo;
	}

	public ExaminationInfo(String examinationNo, String idcard, String routineCheckups, String bloodPressure, String bloodSugar, String bloodFat, String bloodOxygen, String ecgAnalysis, String bodyComposition, String physiqueIdentify,
			String psychologicaAssessmentSDS, String psychologicaAssessmentSAS, String psychologicaAssessmentPSQI, String psychologicaAssessmentSAQ, String psychologicaAssessmentUCLA, String psychologicaAssessmentGCQ,
			String psychologicaAssessmentSCL90, String psychologicaAssessmentQLSCA, String agednessSelfcare, String agednessDepression, String agednessIntelligence, String createTime, String updateTime) {
		this.examinationNo = examinationNo;
		this.idcard = idcard;
		this.routineCheckups = routineCheckups;
		this.bloodPressure = bloodPressure;
		this.bloodSugar = bloodSugar;
		this.bloodFat = bloodFat;
		this.bloodOxygen = bloodOxygen;
		this.ecgAnalysis = ecgAnalysis;
		this.bodyComposition = bodyComposition;
		this.physiqueIdentify = physiqueIdentify;
		this.psychologicaAssessmentSDS = psychologicaAssessmentSDS;
		this.psychologicaAssessmentSAS = psychologicaAssessmentSAS;
		this.psychologicaAssessmentPSQI = psychologicaAssessmentPSQI;
		this.psychologicaAssessmentSAQ = psychologicaAssessmentSAQ;
		this.psychologicaAssessmentUCLA = psychologicaAssessmentUCLA;
		this.psychologicaAssessmentGCQ = psychologicaAssessmentGCQ;
		this.psychologicaAssessmentSCL90 = psychologicaAssessmentSCL90;
		this.psychologicaAssessmentQLSCA = psychologicaAssessmentQLSCA;
		this.agednessSelfcare = agednessSelfcare;
		this.agednessDepression = agednessDepression;
		this.agednessIntelligence = agednessIntelligence;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** Not-null value. */
	public String getExaminationNo() {
		return examinationNo;
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setExaminationNo(String examinationNo) {
		this.examinationNo = examinationNo;
	}

	/** Not-null value. */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getRoutineCheckups() {
		return routineCheckups;
	}

	public void setRoutineCheckups(String routineCheckups) {
		this.routineCheckups = routineCheckups;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getBloodSugar() {
		return bloodSugar;
	}

	public void setBloodSugar(String bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	public String getBloodFat() {
		return bloodFat;
	}

	public void setBloodFat(String bloodFat) {
		this.bloodFat = bloodFat;
	}

	public String getBloodOxygen() {
		return bloodOxygen;
	}

	public void setBloodOxygen(String bloodOxygen) {
		this.bloodOxygen = bloodOxygen;
	}

	public String getEcgAnalysis() {
		return ecgAnalysis;
	}

	public void setEcgAnalysis(String ecgAnalysis) {
		this.ecgAnalysis = ecgAnalysis;
	}

	public String getBodyComposition() {
		return bodyComposition;
	}

	public void setBodyComposition(String bodyComposition) {
		this.bodyComposition = bodyComposition;
	}

	public String getPhysiqueIdentify() {
		return physiqueIdentify;
	}

	public void setPhysiqueIdentify(String physiqueIdentify) {
		this.physiqueIdentify = physiqueIdentify;
	}

	public String getPsychologicaAssessmentSDS() {
		return psychologicaAssessmentSDS;
	}

	public void setPsychologicaAssessmentSDS(String psychologicaAssessmentSDS) {
		this.psychologicaAssessmentSDS = psychologicaAssessmentSDS;
	}

	public String getPsychologicaAssessmentSAS() {
		return psychologicaAssessmentSAS;
	}

	public void setPsychologicaAssessmentSAS(String psychologicaAssessmentSAS) {
		this.psychologicaAssessmentSAS = psychologicaAssessmentSAS;
	}

	public String getPsychologicaAssessmentPSQI() {
		return psychologicaAssessmentPSQI;
	}

	public void setPsychologicaAssessmentPSQI(String psychologicaAssessmentPSQI) {
		this.psychologicaAssessmentPSQI = psychologicaAssessmentPSQI;
	}

	public String getPsychologicaAssessmentSAQ() {
		return psychologicaAssessmentSAQ;
	}

	public void setPsychologicaAssessmentSAQ(String psychologicaAssessmentSAQ) {
		this.psychologicaAssessmentSAQ = psychologicaAssessmentSAQ;
	}

	public String getPsychologicaAssessmentUCLA() {
		return psychologicaAssessmentUCLA;
	}

	public void setPsychologicaAssessmentUCLA(String psychologicaAssessmentUCLA) {
		this.psychologicaAssessmentUCLA = psychologicaAssessmentUCLA;
	}

	public String getPsychologicaAssessmentGCQ() {
		return psychologicaAssessmentGCQ;
	}

	public void setPsychologicaAssessmentGCQ(String psychologicaAssessmentGCQ) {
		this.psychologicaAssessmentGCQ = psychologicaAssessmentGCQ;
	}

	public String getPsychologicaAssessmentSCL90() {
		return psychologicaAssessmentSCL90;
	}

	public void setPsychologicaAssessmentSCL90(String psychologicaAssessmentSCL90) {
		this.psychologicaAssessmentSCL90 = psychologicaAssessmentSCL90;
	}

	public String getPsychologicaAssessmentQLSCA() {
		return psychologicaAssessmentQLSCA;
	}

	public void setPsychologicaAssessmentQLSCA(String psychologicaAssessmentQLSCA) {
		this.psychologicaAssessmentQLSCA = psychologicaAssessmentQLSCA;
	}

	public String getAgednessSelfcare() {
		return agednessSelfcare;
	}

	public void setAgednessSelfcare(String agednessSelfcare) {
		this.agednessSelfcare = agednessSelfcare;
	}

	public String getAgednessDepression() {
		return agednessDepression;
	}

	public void setAgednessDepression(String agednessDepression) {
		this.agednessDepression = agednessDepression;
	}

	public String getAgednessIntelligence() {
		return agednessIntelligence;
	}

	public void setAgednessIntelligence(String agednessIntelligence) {
		this.agednessIntelligence = agednessIntelligence;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	// KEEP METHODS - put your custom methods here

	@Override
	public String toString() {
		return "ExaminationInfo [examinationNo=" + examinationNo + ", idcard=" + idcard + ", routineCheckups=" + routineCheckups + ", bloodPressure=" + bloodPressure + ", bloodSugar=" + bloodSugar + ", bloodFat=" + bloodFat
				+ ", bloodOxygen=" + bloodOxygen + ", ecgAnalysis=" + ecgAnalysis + ", bodyComposition=" + bodyComposition + ", physiqueIdentify=" + physiqueIdentify + ", psychologicaAssessmentSDS=" + psychologicaAssessmentSDS
				+ ", psychologicaAssessmentSAS=" + psychologicaAssessmentSAS + ", psychologicaAssessmentPSQI=" + psychologicaAssessmentPSQI + ", psychologicaAssessmentSAQ=" + psychologicaAssessmentSAQ + ", psychologicaAssessmentUCLA="
				+ psychologicaAssessmentUCLA + ", psychologicaAssessmentGCQ=" + psychologicaAssessmentGCQ + ", psychologicaAssessmentSCL90=" + psychologicaAssessmentSCL90 + ", psychologicaAssessmentQLSCA=" + psychologicaAssessmentQLSCA
				+ ", agednessSelfcare=" + agednessSelfcare + ", agednessDepression=" + agednessDepression + ", agednessIntelligence=" + agednessIntelligence + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	// KEEP METHODS END

}