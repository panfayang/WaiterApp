package com.example.waiterapp;

/**
 * @author panfayang
 * @data Oct 20, 2013
 * @description This file is responsible for removing database files. 
 * It will lead the user into a scrollable Textview, and by touching 
 * on any of the file names, if there are any, the user will delete
 * that database file.
 */

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ClearDbActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clear_db);
		clearDb(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clear_db, menu);
		return true;
	}

	/*
	 * This method is the main method. It reads the current database files dynamically,
	 * display them in a Textview, and provide an onClickListener for each to be deleted. 
	 */
	public void clearDb(View view){

		ScrollView sv = new ScrollView(this);
		TableLayout ll = new TableLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		sv.addView(ll);

		final File[] files = this.getFilesDir().listFiles();

		for (int i = 0; i< files.length; i++){
			TextView et = new TextView(this);
			et.setText(files[i].getName());
			final String tempFilename = this.getFilesDir() + "/" + files[i].getName();
			final String shortFilename = files[i].getName();
			et.setTextSize(40);
			et.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					try{
						((TableLayout)v.getParent()).removeView(v);
						File temp = new File(tempFilename);
						temp.delete();
						Toast toast = Toast.makeText(getApplicationContext(), "Database file " + shortFilename + " deleted.", Toast.LENGTH_SHORT);
						toast.show();
					}catch (Exception e){}
				}
			});
			ll.addView(et);
		}
		this.setContentView(sv);

	}

}
