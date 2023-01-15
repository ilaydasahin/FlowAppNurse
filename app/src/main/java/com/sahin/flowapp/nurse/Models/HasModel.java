package com.sahin.flowapp.nurse.Models;

public class HasModel {
	private String hasisim;
	private boolean tf;
	private String hasid;
	private String hastur;
	private String hascins;
	private String hasresim;

	public void setHasisim(String hasisim){
		this.hasisim = hasisim;
	}

	public String getHasisim(){
		return hasisim;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setHasid(String hasid){
		this.hasid = hasid;
	}

	public String getHasid(){
		return hasid;
	}

	public void setHastur(String hastur){
		this.hastur = hastur;
	}

	public String getHastur(){
		return hastur;
	}

	public void setHascins(String hascins){
		this.hascins = hascins;
	}

	public String getHascins(){
		return hascins;
	}

	public void setHasresim(String hasresim){
		this.hasresim = hasresim;
	}

	public String getHasresim(){
		return hasresim;
	}

	@Override
 	public String toString(){
		return 
			"HasModel{" +
			"hasisim = '" + hasisim + '\'' +
			",tf = '" + tf + '\'' + 
			",hasid = '" + hasid + '\'' +
			",hastur = '" + hastur + '\'' +
			",hascins = '" + hascins + '\'' +
			",hasresim = '" + hasresim + '\'' +
			"}";
		}
}
