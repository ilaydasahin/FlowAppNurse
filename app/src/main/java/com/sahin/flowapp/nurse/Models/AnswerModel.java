package com.sahin.flowapp.nurse.Models;

public class AnswerModel{
	private String answerid;
	private boolean tf;
	private String questionid;
	private String question;
	private String answer;

	public void setAnswerid(String answerid){
		this.answerid = answerid;
	}

	public String getAnswerid(){
		return answerid;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setQuestionid(String questionid){
		this.questionid = questionid;
	}

	public String getQuestionid(){
		return questionid;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getAnswer(){
		return answer;
	}

	@Override
 	public String toString(){
		return 
			"AnswerModel{" + 
			"answerid = '" + answerid + '\'' + 
			",tf = '" + tf + '\'' + 
			",questionid = '" + questionid + '\'' + 
			",question = '" + question + '\'' + 
			",answer = '" + answer + '\'' + 
			"}";
		}
}
