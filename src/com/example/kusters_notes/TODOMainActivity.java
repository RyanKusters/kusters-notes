package com.example.kusters_notes;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TODOMainActivity extends Activity
{
	
	private ArrayList<Note> NoteList;
	private ArrayAdapter<Note> NoteListAdapter; 
	private EditText notetext;
	private ListView NoteListView;
	private ListViewAdapter NoteListViewAdapter;
	private Integer total_num_notes;
	private Integer total_num_checked;
	private Integer total_num_unchecked;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todomain);
		
		notetext = (EditText) findViewById(R.id.newtext);
		total_num_notes = 0;
		total_num_checked = 0;
		total_num_unchecked = 0;
		
		NoteListView = (ListView) findViewById(R.id.notelist);
        NoteListViewAdapter = new ListViewAdapter(this, R.layout.note_display, NoteList);
		
		NoteListView.setAdapter(NoteListAdapter);
		
		
		
		//MultiChoiceMode code set adapted from www.androidbegin.com
		//This project has code adapted from the above website under Creative Commons
		//This Attribution is intended to conform to the Attribution Clause of the CC License
		//www.androidbegin.com/tutorial/android-delet-multiple-selected-items-listview-tutorial/
		//creativecommons.org/licenses/by-sa/3.0/deed.en_GB
		//And Share Alike
		
		NoteListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		NoteListView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item)
			{

				switch(item.getItemId()){
					case R.id.delete:
						SparseBooleanArray selected = NoteListViewAdapter.getSelectedIds();
						for (int i = (selected.size()-1); i >= 0; i-- ){
							if (selected.valueAt(i)){
								Note selecteditem = NoteListAdapter.getItem(selected.keyAt(i));
								NoteListAdapter.remove(selecteditem);
							}
						}
						mode.finish();
						return true;
					case R.id.add:
						return true;
					default:
						return false;
				}
				
			
			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu)
			{

				mode.getMenuInflater().inflate(R.menu.todomain, menu);
				return true;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode)
			{

				NoteListViewAdapter.removeselection();
				
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu)
			{

				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked)
			{

				NoteListViewAdapter.toggleSelection(position);
				
				
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todomain, menu);
		return true;
	}
	
	/*@Override
	protected void onStart() {
		super.onStart();
		
	}*/
	
	public void AddNote(){
		String text = notetext.getText().toString();
		Note newNote = new Note(text);
		NoteList.add(newNote);
		total_num_notes = total_num_notes + 1;
		total_num_unchecked = total_num_unchecked + 1;
		
		
	}
	
	
}
