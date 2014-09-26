package com.example.kusters_notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class Summary implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer total_unarchived_checked;
	private Integer total_unarchived_unchecked;
	private Integer total_archived_checked;
	private Integer total_archived_unchecked;

	public Summary(ArrayList<MyNote> NoteList){
		total_unarchived_checked = 0;
		total_unarchived_unchecked = 0;
		total_archived_checked = 0;
		total_archived_unchecked = 0;
		Iterator<MyNote> notes = NoteList.iterator();
		while(notes.hasNext()){
			MyNote note = notes.next();
			if(note.GetArchive() & note.GetCheck()){total_archived_checked++;}
			else if(note.GetArchive() & !note.GetCheck()){total_archived_unchecked++;}
			else if(!note.GetArchive() & note.GetCheck()){total_unarchived_checked++;}
			else if(!note.GetArchive() & !note.GetCheck()){total_unarchived_unchecked++;}
		}
		
	}

	
	public Integer getTotal_unarchived_checked()
	{
	
		return total_unarchived_checked;
	}

	
	public void setTotal_unarchived_checked(Integer total_unarchived_checked)
	{
	
		this.total_unarchived_checked = total_unarchived_checked;
	}

	
	public Integer getTotal_unarchived_unchecked()
	{
	
		return total_unarchived_unchecked;
	}

	
	public void setTotal_unarchived_unchecked(Integer total_unarchived_unchecked)
	{
	
		this.total_unarchived_unchecked = total_unarchived_unchecked;
	}

	
	public Integer getTotal_archived_checked()
	{
	
		return total_archived_checked;
	}

	
	public void setTotal_archived_checked(Integer total_archived_checked)
	{
	
		this.total_archived_checked = total_archived_checked;
	}

	
	public Integer getTotal_archived_unchecked()
	{
	
		return total_archived_unchecked;
	}

	
	public void setTotal_archived_unchecked(Integer total_archived_unchecked)
	{
	
		this.total_archived_unchecked = total_archived_unchecked;
	}
	
}
