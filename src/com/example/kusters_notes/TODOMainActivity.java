package com.example.kusters_notes;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TODOMainActivity extends Activity
{
	
	private ArrayList<Note> NoteList;
	private ArrayAdapter<Note> NoteListAdapter; 
	private EditText notetext;
	private ListView NoteListView;
	private Integer total_num_notes;
	private Integer total_num_checked;
	private Integer total_num_unchecked;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todomain);
		notetext = (EditText) findViewById(R.id.usertext);
		NoteListView = (ListView) findViewById(R.id.notelist);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todomain, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		NoteListAdapter = new ArrayAdapter<Note>(this, R.layout.note_display, NoteList);
		NoteListView.setAdapter(NoteListAdapter);
		
	}
	public void AddNote(){
		String text = notetext.getText().toString();
		Note newNote = new Note(text);
		NoteList.add(newNote);
		
	}
	
}
