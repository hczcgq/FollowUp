package com.shbestwin.followupmanager.model.examination;

/**
 * 
 * @ClassName: PhysiqueType
 * @Description: 体质类型
 *
 */
public class PhysiqueType {
	private String typeCode;// 类型编码
	private String typeName;// 类型名称
	private int score;// 得分
	private int questionCount;// 题目数
	private int result;// 结果：1-是， 2-基本是， 3-否

	public PhysiqueType() {
	}

	public PhysiqueType(String typeCode, String typeName) {
		super();
		this.typeCode = typeCode;
		this.typeName = typeName;
	}

	public PhysiqueType(String typeCode, String typeName, int questionCount) {
		super();
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.questionCount = questionCount;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "PhysiqueType [typeCode=" + typeCode + ", typeName=" + typeName + ", score=" + score + ", questionCount=" + questionCount + ", result=" + result + "]";
	}

}
