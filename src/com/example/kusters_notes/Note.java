package com.example.kusters_notes;


public class Note
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
	
	public String toString(){
		if (this.check){return "\u2611" + this.usertext;}
		else {return "\u2610" + this.usertext;}
	}
	
}

