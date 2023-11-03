package com.sahin.flowapp.nurse.Models;

public class CampaignsModel{
	private String image;
	private boolean tf;
	private String text;
	private String title;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"CampaignsModel{" + 
			"image = '" + image + '\'' + 
			",tf = '" + tf + '\'' + 
			",text = '" + text + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}
