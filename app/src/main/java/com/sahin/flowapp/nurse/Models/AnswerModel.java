package com.sahin.flowapp.nurse.Models;

public class AnswerModel{
	private String cevapid;
	private boolean tf;
	private String soruid;
	private String soru;
	private String cevap;

	public void setCevapid(String cevapid){
		this.cevapid = cevapid;
	}

	public String getCevapid(){
		return cevapid;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setSoruid(String soruid){
		this.soruid = soruid;
	}

	public String getSoruid(){
		return soruid;
	}

	public void setSoru(String soru){
		this.soru = soru;
	}

	public String getSoru(){
		return soru;
	}

	public void setCevap(String cevap){
		this.cevap = cevap;
	}

	public String getCevap(){
		return cevap;
	}

	@Override
 	public String toString(){
		return 
			"AnswerModel{" + 
			"cevapid = '" + cevapid + '\'' +
			",tf = '" + tf + '\'' + 
			",soruid = '" + soruid + '\'' +
			",soru = '" + soru + '\'' +
			",cevap = '" + cevap + '\'' +
			"}";
		}
}
