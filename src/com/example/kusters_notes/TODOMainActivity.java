package com.example.kusters_notes;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TODOMainActivity extends Activity
{
	
	private ArrayList<MyNote> NoteList;
	private EditText notetext;
	private ListView NoteListView;
	private ListViewAdapter NoteListViewAdapter;
	private GsonDataManager gson;
	private Intent itentSummary;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todomain);
		
		notetext = (EditText) findViewById(R.id.newtext);
		NoteList = new ArrayList<MyNote>();
		
		
		NoteListView = (ListView) findViewById(R.id.notelist);
        NoteListViewAdapter = new ListViewAdapter(this, R.layout.listview_item, NoteList);
		
		NoteListView.setAdapter(NoteListViewAdapter);
		gson = new GsonDataManager(this);
	    itentSummary = new Intent(this,Summary_Activity.class);
		
		
		
		//MultiChoiceMode code set adapted from www.androidbegin.com
		//This project has code adapted from the above website under Creative Commons
		//This Attribution is intended to conform to the Attribution Clause of the CC License
		//www.androidbegin.com/tutorial/android-delet-multiple-selected-items-listview-tutorial/
		//creativecommons.org/licenses/by-sa/3.0/deed.en_GB
		//And Share Alike
		
		Button summary = (Button) findViewById(R.id.summary);
		summary.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
			
				Summary current_stat = new Summary(NoteList);
				Bundle current_bag = new Bundle();
				current_bag.putSerializable("summary_bag", current_stat);
				itentSummary.putExtras(current_bag);
				startActivity(itentSummary);
			}
		});
		
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
				notetext.setText("");
				gson.saveTweets(NoteList);		
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
						gson.saveTweets(NoteList);
						mode.finish();
						return true;
					case R.id.check:
						SparseBooleanArray selected1 = NoteListViewAdapter.getSelectedIds();
						for (int i = (selected1.size()-1); i >= 0; i-- ){
							if (selected1.valueAt(i)){
								MyNote selecteditem = NoteListViewAdapter.getItem(selected1.keyAt(i));
								selecteditem.SetCheck(true);
								
							}
						}
						NoteListViewAdapter.notifyDataSetChanged();
						gson.saveTweets(NoteList);
						mode.finish();
						return true;
					case R.id.uncheck:
						SparseBooleanArray selected2 = NoteListViewAdapter.getSelectedIds();
						for (int i = (selected2.size()-1); i >= 0; i-- ){
							if (selected2.valueAt(i)){
								MyNote selecteditem = NoteListViewAdapter.getItem(selected2.keyAt(i));
								selecteditem.SetCheck(false);
								
							}
						}
						NoteListViewAdapter.notifyDataSetChanged();
						gson.saveTweets(NoteList);
						mode.finish();
						return true;
					case R.id.archive:
						SparseBooleanArray selected3 = NoteListViewAdapter.getSelectedIds();
						for (int i = (selected3.size()-1); i >= 0; i-- ){
							if (selected3.valueAt(i)){
								MyNote selecteditem = NoteListViewAdapter.getItem(selected3.keyAt(i));
								selecteditem.SetArchive(true);
								
							}
						}
						NoteListViewAdapter.notifyDataSetChanged();
						gson.saveTweets(NoteList);
						mode.finish();
						return true;
					case R.id.unarchive:
						SparseBooleanArray selected4 = NoteListViewAdapter.getSelectedIds();
						for (int i = (selected4.size()-1); i >= 0; i-- ){
							if (selected4.valueAt(i)){
								MyNote selecteditem = NoteListViewAdapter.getItem(selected4.keyAt(i));
								selecteditem.SetArchive(false);
								
							}
						}
						NoteListViewAdapter.notifyDataSetChanged();
						gson.saveTweets(NoteList);
						mode.finish();
						return true;
					case R.id.email:
						//http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
						Intent emailIntent = new Intent(Intent.ACTION_SEND);
						emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Notes Delivered!");
						emailIntent.putExtra(Intent.EXTRA_TEXT, "Body of Email");
						try {
							startActivity(emailIntent.createChooser(emailIntent,"Select Mailer"));
						}
						catch(android.content.ActivityNotFoundException e){
							Toast.makeText(TODOMainActivity.this, "Email clients not found.", Toast.LENGTH_SHORT).show();
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
		});		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todomain, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		gson.saveTweets(NoteList);
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		NoteList = gson.loadNotes();
		NoteListViewAdapter = new ListViewAdapter(this, R.layout.listview_item, NoteList);
		NoteListView.setAdapter(NoteListViewAdapter);
	}	
}