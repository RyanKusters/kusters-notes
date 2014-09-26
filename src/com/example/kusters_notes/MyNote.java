package com.example.kusters_notes;

import java.io.Serializable;


public class MyNote implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usertext;
	private Boolean check;
	private Boolean archive;
	
	public MyNote(String newtext){
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
		if(archive){
		return this.usertext + " Archived";	
		}
		else{
		return  this.usertext;
		}
	}
	
	public int getIcon(){
		if (this.check){return R.drawable.checkedbox;}
		else {return R.drawable.checkbox;
		}
	}
	
}

