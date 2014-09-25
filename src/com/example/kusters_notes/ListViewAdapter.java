package com.example.kusters_notes;


import java.util.ArrayList;


import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


//MultiChoiceMode code set adapted from www.androidbegin.com
		//This project has code adapted from the above website under Creative Commons
		//This Attribution is intended to conform to the Attribution Clause of the CC License
		//www.androidbegin.com/tutorial/android-delet-multiple-selected-items-listview-tutorial/
		//creativecommons.org/licenses/by-sa/3.0/deed.en_GB
		//And Share Alike

public class ListViewAdapter extends ArrayAdapter<Note>
{

	Context context;
	LayoutInflater inflater;
	ArrayList<com.example.kusters_notes.Note> NoteList;
	private SparseBooleanArray mySelectedItemIds;
	
	public ListViewAdapter(Context context, int resourceId, ArrayList<com.example.kusters_notes.Note> NoteList){
		super(context,resourceId);
		mySelectedItemIds = new SparseBooleanArray();
		this.context = context;
		this.NoteList = NoteList;
		inflater = LayoutInflater.from(context);
	}
	
	private class ViewHolder {
		ImageView icon;
		TextView notetext;
	}
	
	public View getView(int position, android.view.View view, ViewGroup parent){
		final ViewHolder holder;
		if (view == null){
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item,null);
			holder.notetext = (TextView) view.findViewById(R.id.notetext);
			holder.icon = (ImageView) view.findViewById(R.id.icon);
			view.setTag(holder);
		} else{
			holder = (ViewHolder) view.getTag();
		}
		
		holder.icon.setImageResource(NoteList.get(position).getIcon());
		holder.icon.setVisibility(0);
		holder.notetext.setText(NoteList.get(position).toString());
		
		return view;
	}
	
	@Override
	public void remove(Note object){
		NoteList.remove(object);
		notifyDataSetChanged();
	}
	
	public ArrayList<com.example.kusters_notes.Note> getNote(){
		return NoteList;
	}
	
	public void toggleSelection(int position){
		selectView(position,!mySelectedItemIds.get(position));
	}
	
	public void removeselection(){
		mySelectedItemIds = new SparseBooleanArray();
		notifyDataSetChanged();
	}
	
	public void selectView(int position, boolean value){
		if(value){
			mySelectedItemIds.put(position, value);
		} else {
			mySelectedItemIds.delete(position);
		} notifyDataSetChanged();
	}
	
	public int getSelectedCount(){
		return mySelectedItemIds.size();
	}
	
	public SparseBooleanArray getSelectedIds(){
		return mySelectedItemIds;
	}
}
