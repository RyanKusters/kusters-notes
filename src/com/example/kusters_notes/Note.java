package com.example.kusters_notes;

import java.io.Serializable;


public class Note implements Serializable
{

	private String usertext;
	private Boolean check;
	private Boolean archive;
	
	public Note(String newtext){
		super();
		this.usertext = newtext;
		this.check = false;
		this.archive = false;
		
	}
	
	public void SetNote(String newtext){
		this.usertext = newtext;
	}
	
	public void SetCheck(Boolean newcheck){
		this.check = newcheck;
	}
	
	public void SetArchive(Boolean newarchive){
		this.archive = newarchive;
	}
	
	public Boolean GetCheck(){
		return this.check;
	}
	
	public Boolean GetArchive(){
		return this.archive;
	}
	
	@Override
	public String toString(){
		return  this.usertext;
	}
	
	public int getIcon(){
		if (this.check){return R.drawable.checkedbox;}
		else {return R.drawable.checkbox;
		}
	}
	
}

