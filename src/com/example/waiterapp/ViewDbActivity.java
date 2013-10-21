package com.example.waiterapp;

/**
 * @author panfayang
 * @data Oct 20, 2013
 * @description This class enables viewing of the current database.
 */

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.waiterapp.WaiterAppContract.WaiterApp;

public class ViewDbActivity extends Activity {

	public StringBuilder results;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_db);
		viewDb(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_db, menu);
		return true;
	}

	public String printDb(){

		WaiterAppDbHelper myDbHelper = new WaiterAppDbHelper(this);
		SQLiteDatabase db = myDbHelper.getReadableDatabase();

		//Define which columns of the db you will actually use after query
		String[] projection = {
				WaiterApp.COLUMN_NAME_CT,
				WaiterApp.COLUMN_NAME_TABLE_ID,
				WaiterApp.COLUMN_NAME_PATRON_NUMBER,
				WaiterApp.COLUMN_NAME_MEAL_CHOICE
		};

		//How do you want the results to be sorted in the Cursor
		try{
			String sortOrder = 
					WaiterApp.COLUMN_NAME_CT + " DESC";

			Cursor c = db.query(
					WaiterApp.TABLE_NAME,
					projection, 
					null, null, null, null, sortOrder);

			StringBuilder sb = new StringBuilder();
			c.moveToFirst();
			for (String i:c.getColumnNames()){
				sb.append(i);
				sb.append("|");
			}
			sb.append("\n");

			while(!c.isAfterLast()){
				sb.append(c.getString(0));
				sb.append("|");
				for (int i = 1; i< 3; i++){
					sb.append(c.getInt(i));
					sb.append("|");
				}
				sb.append(c.getString(3));
				sb.append("\n");
				c.moveToNext();
			}
			return sb.toString();
		}
		catch(Exception e){
			return "There are no entries yet. Please add patrons in 'Enter Patrons'";
		}
	}

	public void viewDb(View view){
		((TextView) findViewById(R.id.viewAll)).setTextSize(15);
		((TextView) findViewById(R.id.viewAll)).setText(" "+ this.printDb());
	}

}
