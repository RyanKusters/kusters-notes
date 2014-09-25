package com.example.kusters_notes;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class TODOMainActivity extends Activity
{
	
	private ArrayList<MyNote> NoteList;
	//private ArrayAdapter<MyNote> NoteListAdapter; 
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
		NoteList = new ArrayList<MyNote>();
		//NoteListAdapter = new ArrayAdapter<MyNote>(this,R.layout.listview_item,NoteList);
		
		NoteListView = (ListView) findViewById(R.id.notelist);
        NoteListViewAdapter = new ListViewAdapter(this, R.layout.listview_item, NoteList);
		
		NoteListView.setAdapter(NoteListViewAdapter);
		
		
		
		//MultiChoiceMode code set adapted from www.androidbegin.com
		//This project has code adapted from the above website under Creative Commons
		//This Attribution is intended to conform to the Attribution Clause of the CC License
		//www.androidbegin.com/tutorial/android-delet-multiple-selected-items-listview-tutorial/
		//creativecommons.org/licenses/by-sa/3.0/deed.en_GB
		//And Share Alike
		
		/*NoteListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{

				addNote();
				
			}
		});*/
		
		Button addNote = (Button) findViewById(R.id.add);
		addNote.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
			
				String text = notetext.getText().toString();
				MyNote newNote = new MyNote(text);
				NoteList.add(newNote);
				NoteListViewAdapter.notifyDataSetChanged();
				
				
			}
		});
		
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
								MyNote selecteditem = NoteListViewAdapter.getItem(selected.keyAt(i));
								NoteListViewAdapter.remove(selecteditem);
							}
						}
						mode.finish();
						return true;
					case R.id.check:
						SparseBooleanArray selected1 = NoteListViewAdapter.getSelectedIds();
						for (int i = (selected1.size()-1); i >= 0; i-- ){
							if (selected1.valueAt(i)){
								MyNote selecteditem = NoteListViewAdapter.getItem(selected1.keyAt(i));
								selecteditem.SetCheck(true);
								NoteListViewAdapter.notifyDataSetChanged();
							}
						}
						mode.finish();
						return true;
					case R.id.uncheck:
						SparseBooleanArray selected2 = NoteListViewAdapter.getSelectedIds();
						for (int i = (selected2.size()-1); i >= 0; i-- ){
							if (selected2.valueAt(i)){
								MyNote selecteditem = NoteListViewAdapter.getItem(selected2.keyAt(i));
								selecteditem.SetCheck(false);
								NoteListViewAdapter.notifyDataSetChanged();
							}
						}
						mode.finish();
						return true;
					case R.id.archive:
						SparseBooleanArray selected3 = NoteListViewAdapter.getSelectedIds();
						for (int i = (selected3.size()-1); i >= 0; i-- ){
							if (selected3.valueAt(i)){
								MyNote selecteditem = NoteListViewAdapter.getItem(selected3.keyAt(i));
								selecteditem.SetArchive(true);
								NoteListViewAdapter.notifyDataSetChanged();
							}
						}
						mode.finish();
						return true;
					case R.id.unarchive:
						SparseBooleanArray selected4 = NoteListViewAdapter.getSelectedIds();
						for (int i = (selected4.size()-1); i >= 0; i-- ){
							if (selected4.valueAt(i)){
								MyNote selecteditem = NoteListViewAdapter.getItem(selected4.keyAt(i));
								selecteditem.SetArchive(false);
								NoteListViewAdapter.notifyDataSetChanged();
							}
						}
						mode.finish();
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
			
			/*@Override
			public void onSaveInstanceState(Bundle savedInstanceState){
				super.onSaveInstanceState(savedInstanceState);
				savedInstanceState.pu
			}*/
			
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
	
	
	
	
	
	
}
