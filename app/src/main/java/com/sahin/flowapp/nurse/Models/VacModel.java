package com.sahin.flowapp.nurse.Models;

public class VacModel{
	private String vacdate;
	private boolean tf;
	private String petname;
	private String vacname;
	private String petgenus;
	private String petimage;
	private String petkind;

	public void setVacdate(String vacdate){
		this.vacdate = vacdate;
	}

	public String getVacdate(){
		return vacdate;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setPetname(String petname){
		this.petname = petname;
	}

	public String getPetname(){
		return petname;
	}

	public void setVacname(String vacname){
		this.vacname = vacname;
	}

	public String getVacname(){
		return vacname;
	}

	public void setPetgenus(String petgenus){
		this.petgenus = petgenus;
	}

	public String getPetgenus(){
		return petgenus;
	}

	public void setPetimage(String petimage){
		this.petimage = petimage;
	}

	public String getPetimage(){
		return petimage;
	}

	public void setPetkind(String petkind){
		this.petkind = petkind;
	}

	public String getPetkind(){
		return petkind;
	}

	@Override
 	public String toString(){
		return 
			"VacModel{" + 
			"vacdate = '" + vacdate + '\'' + 
			",tf = '" + tf + '\'' + 
			",petname = '" + petname + '\'' + 
			",vacname = '" + vacname + '\'' + 
			",petgenus = '" + petgenus + '\'' + 
			",petimage = '" + petimage + '\'' + 
			",petkind = '" + petkind + '\'' + 
			"}";
		}
}
