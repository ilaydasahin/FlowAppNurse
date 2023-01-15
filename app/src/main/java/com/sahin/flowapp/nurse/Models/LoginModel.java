package com.sahin.flowapp.nurse.Models;

public class LoginModel{
	private boolean tf;
	private String mailadres;
	private String parola;
	private String id;
	private String text;
	private String username;

	public void setTf(boolean tf){
		this.tf = tf;
	}
	public boolean isTf(){
		return tf;
	}
	public void setMailadres(String mailadres){
		this.mailadres = mailadres;
	}
	public String getMailadres(){
		return mailadres;
	}

	public String getParola(){
		return parola;
	}
	public void setParola(String parola){
		this.parola = parola;
	}

	public String getId(){
		return id;
	}

	public String getText(){
		return text;
	}
	public void setId(String id){
		this.id = id;
	}
	public void setText(String text){
		this.text = text;
	}

	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}

	@Override
	public String toString(){
		return
				"LoginModel{" +
						"parola = '" + parola + '\'' +
						",tf = '" + tf + '\'' +
						",id = '" + id + '\'' +
						",text = '" + text + '\'' +
						",mailadres = '" + mailadres + '\'' +
						",username = '" + username + '\'' +
						"}";
	}
}
