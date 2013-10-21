package com.example.waiterapp;

/**
 * @author panfayang
 * @data Oct 20, 2013
 * @description This file is responsible for taking user input and putting it
 * into the current database. 
 */

import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.waiterapp.WaiterAppContract.WaiterApp;

public class EnterPatronsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_patrons);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_patrons, menu);
		return true;
	}

	public void clear(View view){
		((EditText) findViewById(R.id.patronNo)).setText("");
		((EditText) findViewById(R.id.tableNo)).setText("");
		((Spinner) findViewById(R.id.food)).setSelection(0);
	}

	public void insertIntoDb(View view) {
		
		// The current time.
		String currentTime = (new GregorianCalendar().getTime()).toString();	
		try{
			// Grab the three user inputs.
			int patronId = Integer.parseInt(((EditText) findViewById(R.id.patronNo)).getText().toString());
			int tableId = Integer.parseInt(((EditText) findViewById(R.id.tableNo)).getText().toString());
			String foodChoice = ((Spinner) findViewById(R.id.food)).getSelectedItem().toString();
			
			WaiterAppDbHelper myDbHelper = new WaiterAppDbHelper(this);
			SQLiteDatabase db = myDbHelper.getWritableDatabase();

			//Create a new map of values where column names are the keys.
			ContentValues values = new ContentValues();
			if (patronId>0 && tableId>0){
				values.put(WaiterApp.COLUMN_NAME_CT, currentTime);
				values.put(WaiterApp.COLUMN_NAME_TABLE_ID, tableId);
				values.put(WaiterApp.COLUMN_NAME_PATRON_NUMBER, patronId);
				values.put(WaiterApp.COLUMN_NAME_MEAL_CHOICE,foodChoice);

				//Insert new row, returning the primary key value of the new row
				db.insert(WaiterApp.TABLE_NAME, WaiterApp.COLUMN_NAME_NULLABLE, values);
				Toast toast = Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT);
				toast.show();
				((EditText) findViewById(R.id.patronNo)).setText("");
				((EditText) findViewById(R.id.tableNo)).setText("");
				((Spinner) findViewById(R.id.food)).setSelection(0);
			}
		}
		catch (Exception e){}
	}



}
