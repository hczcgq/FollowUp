package com.shbestwin.followupmanager.model.examination;

public class Question {
	private String question;
	private String type;

	public Question() {
	}

	public Question(String question, String type) {
		super();
		this.question = question;
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Test [question=" + question + ", type=" + type + "]";
	}

}
