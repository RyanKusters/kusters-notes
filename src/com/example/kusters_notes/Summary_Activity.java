package com.example.kusters_notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Summary_Activity extends Activity
{
	Intent summaryintent;
	Bundle summarybundle;
	Summary current_stat;
	TextView totalall;
	TextView totalchecked;
	TextView totalunchecked;
	TextView totalarchived;
	TextView totalarchivedchecked;
	TextView totalarchivedunchecked;
	Button returntomain;
	Intent todo;
	Context context;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		context = this;
		summaryintent = this.getIntent();
		summarybundle = summaryintent.getExtras();
		
		current_stat = (Summary) summarybundle.getSerializable("summary_bag");
		
		totalall = (TextView) findViewById(R.id.totalall);
		totalchecked = (TextView) findViewById(R.id.totalchecked);
		totalunchecked = (TextView) findViewById(R.id.totalunchecked);
		totalarchived = (TextView) findViewById(R.id.totalarchieved);
		totalarchivedchecked = (TextView) findViewById(R.id.totalarchivedcheck);
		totalarchivedunchecked = (TextView) findViewById(R.id.totalarchiveduncheck);
		
		totalall.setText("Total Unarchived: "+String.valueOf(current_stat.getTotal_unarchived_checked()+current_stat.getTotal_unarchived_unchecked()));
		totalchecked.setText("Total Checked: "+String.valueOf(current_stat.getTotal_unarchived_checked()));
		totalunchecked.setText("Total Unchecked: "+String.valueOf(current_stat.getTotal_unarchived_unchecked()));
		totalarchived.setText("Total Archived:"+String.valueOf(current_stat.getTotal_archived_checked()+current_stat.getTotal_archived_unchecked()));
		totalarchivedchecked.setText("Total Checked: "+String.valueOf(current_stat.getTotal_archived_checked()));
		totalarchivedunchecked.setText("Total Unhecked: "+String.valueOf(current_stat.getTotal_archived_unchecked()));
		
		returntomain = (Button) findViewById(R.id.main_return);
		returntomain.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v)
			{

				todo =  new Intent(context,TODOMainActivity.class);
				startActivity(todo);
				
			}
			
		});
		
	}
}
