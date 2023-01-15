package com.sahin.flowapp.nurse.Models;

public class IslemModel {
	private String islemtarih;
	private boolean tf;
	private String hasisim;
	private String islemisim;
	private String hascins;
	private String hasresim;
	private String hastur;

	public void setIslemtarih(String islemtarih){
		this.islemtarih = islemtarih;
	}

	public String getIslemtarih(){
		return islemtarih;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setHasisim(String hasisim){
		this.hasisim = hasisim;
	}

	public String getHasisim(){
		return hasisim;
	}

	public void setIslemisim(String islemisim){
		this.islemisim = islemisim;
	}

	public String getIslemisim(){
		return islemisim;
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

	public void setHastur(String hastur){
		this.hastur = hastur;
	}

	public String getHastur(){
		return hastur;
	}

	@Override
 	public String toString(){
		return 
			"IslemModel{" +
			"islemtarih = '" + islemtarih + '\'' +
			",tf = '" + tf + '\'' + 
			",hasisim = '" + hasisim + '\'' +
			",islemisim = '" + islemisim + '\'' +
			",hascins = '" + hascins + '\'' +
			",hasresim = '" + hasresim + '\'' +
			",hastur = '" + hastur + '\'' +
			"}";
		}
}
