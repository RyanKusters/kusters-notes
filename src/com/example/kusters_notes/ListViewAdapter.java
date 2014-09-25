package com.example.kusters_notes;


import java.util.ArrayList;


import android.content.Context;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


//MultiChoiceMode code set adapted from www.androidbegin.com
		//This project has code adapted from the above website under Creative Commons
		//This Attribution is intended to conform to the Attribution Clause of the CC License
		//www.androidbegin.com/tutorial/android-delet-multiple-selected-items-listview-tutorial/
		//creativecommons.org/licenses/by-sa/3.0/deed.en_GB
		//And Share Alike

public class ListViewAdapter extends ArrayAdapter<MyNote>
{

	Context context;
	LayoutInflater inflater;
	ArrayList<MyNote> NoteList;
	private SparseBooleanArray mySelectedItemIds;
	
	public ListViewAdapter(Context context, int resourceId, ArrayList<MyNote> noteList2){
		super(context,resourceId, noteList2);
		mySelectedItemIds = new SparseBooleanArray();
		this.context = context;
		this.NoteList = noteList2;
		inflater = LayoutInflater.from(context);
	}
	
	private class ViewHolder {
		//CheckBox icon;
		ImageView icon;
		TextView notetext;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent){
		final ViewHolder holder;
		if (view == null){
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item,null);
			//holder.icon = (CheckBox) view.findViewById(R.id.icon);
			holder.icon = (ImageView) view.findViewById(R.id.icon);
			holder.notetext = (TextView) view.findViewById(R.id.notetext);
			
			view.setTag(holder);
		} else{
			holder = (ViewHolder) view.getTag();
		}
		//holder.icon.setChecked(NoteList.get(position).GetCheck());
		holder.icon.setImageResource(NoteList.get(position).getIcon());
		//holder.icon.setText(NoteList.get(position).toString());
	    holder.notetext.setText(NoteList.get(position).toString());
		
		return view;
	}
	
	@Override
	public void remove(MyNote object){
		NoteList.remove(object);
		notifyDataSetChanged();
	}
	
	public ArrayList<MyNote> getNote(){
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
