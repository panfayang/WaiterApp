package com.example.waiterapp;

/**
 * @author panfayang
 * @data Oct 20, 2013
 * @description This class is responsible for showing the list of saved database files and 
 * by touching on any one of them, the method in this class will append the database to the 
 * current data entries.
 */

import java.io.File;
import java.util.Scanner;

import com.example.waiterapp.WaiterAppContract.WaiterApp;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoadDbActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_load_db);
		showListOfDb(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load_db, menu);
		return true;
	}

	/*
	 * This method shows the current list of saved database files. If any one of them is 
	 * touched, an onClick method will be called to append that database into the current
	 * database.
	 */
	public void showListOfDb(View view){

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
			final WaiterAppDbHelper myDbHelper = new WaiterAppDbHelper(this);
			et.setTextSize(40);
			et.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					SQLiteDatabase db = myDbHelper.getWritableDatabase();
					try{
						File temp = new File(tempFilename);
						Scanner scan = new Scanner(temp);
						ContentValues values = new ContentValues();
						do {
							try{
								String first = scan.nextLine();
								//Insert new row, returning the primary key value of the new row
								db.insert(WaiterApp.TABLE_NAME, WaiterApp.COLUMN_NAME_NULLABLE, values);
								String[] i = first.split(",");
								values.put(WaiterApp.COLUMN_NAME_CT, i[0]);
								values.put(WaiterApp.COLUMN_NAME_TABLE_ID, i[1]);
								values.put(WaiterApp.COLUMN_NAME_PATRON_NUMBER, i[2]);
								values.put(WaiterApp.COLUMN_NAME_MEAL_CHOICE,i[3]);
							}
							catch (Exception e){}
						}while (scan.hasNext());
						db.insert(WaiterApp.TABLE_NAME, WaiterApp.COLUMN_NAME_NULLABLE, values);
						Toast toast = Toast.makeText(getApplicationContext(), "Database file " + shortFilename + " was appended to the current database", Toast.LENGTH_SHORT);
						toast.show();
					}
					catch(Exception e){}
				}
			});
			ll.addView(et);
		}
		this.setContentView(sv);

	}

}
