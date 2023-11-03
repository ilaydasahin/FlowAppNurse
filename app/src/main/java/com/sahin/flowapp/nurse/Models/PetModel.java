package com.sahin.flowapp.nurse.Models;

public class PetModel{
	private String petName;
	private boolean tf;
	private String petId;
	private String petKind;
	private String petGenus;
	private String petImage;

	public void setPetName(String petName){
		this.petName = petName;
	}

	public String getPetName(){
		return petName;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setPetId(String petId){
		this.petId = petId;
	}

	public String getPetId(){
		return petId;
	}

	public void setPetKind(String petKind){
		this.petKind = petKind;
	}

	public String getPetKind(){
		return petKind;
	}

	public void setPetGenus(String petGenus){
		this.petGenus = petGenus;
	}

	public String getPetGenus(){
		return petGenus;
	}

	public void setPetImage(String petImage){
		this.petImage = petImage;
	}

	public String getPetImage(){
		return petImage;
	}

	@Override
 	public String toString(){
		return 
			"PetModel{" + 
			"petName = '" + petName + '\'' + 
			",tf = '" + tf + '\'' + 
			",petId = '" + petId + '\'' + 
			",petKind = '" + petKind + '\'' + 
			",petGenus = '" + petGenus + '\'' + 
			",petImage = '" + petImage + '\'' + 
			"}";
		}
}
