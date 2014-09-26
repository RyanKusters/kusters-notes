package com.example.kusters_notes;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonDataManager  {

	private static final String FILENAME = "gson_mynotes.sav";

	private Gson gson;
	private Context context;

	public GsonDataManager(Context context1) {
		this.context = context1;
		gson = new Gson();
	}

	public ArrayList<MyNote> loadNotes() {
		ArrayList<MyNote> lts = new ArrayList<MyNote>();

		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					context.openFileInput(FILENAME)));
			String line;
			StringBuffer fileContent = new StringBuffer();

			while ((line = input.readLine()) != null) {
				fileContent.append(line);
			}

			Type collectionType = new TypeToken<Collection<MyNote>>() {}.getType();
			lts = gson.fromJson(fileContent.toString(), collectionType);

		} catch (Exception e) {
			Log.i("kusters-notes", "Error loading notes");
			e.printStackTrace();
		}

		return lts;
	}

	public void saveTweets(List<MyNote> lts) {
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

			String jsonMyNoteList = gson.toJson(lts); 
			fos.write(jsonMyNoteList.getBytes());
			fos.close();
			
			Log.i("Persistence", "Saved: " + jsonMyNoteList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
