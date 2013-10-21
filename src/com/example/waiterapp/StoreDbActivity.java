package com.example.waiterapp;
/**
 * @author panfayang
 * @data Oct 20, 2013
 * @description This class is responsible for saving database. It will provide 
 * a EditText box and a button for the user to input the filename and save it 
 * as a file with the current database.
 */

import java.io.File;
import java.io.PrintWriter;

import com.example.waiterapp.WaiterAppContract.WaiterApp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StoreDbActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_db);
		storeDb(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store_db, menu);
		return true;
	}
	
	/*
	 *  Identical as the printDb method in ViewDbActivity class... I tried to pull 
	 *  it over from there but got errors.
	 */
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

			while(!c.isAfterLast()){
				sb.append(c.getString(0));
				sb.append(",");
				for (int i = 1; i< 3; i++){
					sb.append(c.getInt(i));
					sb.append(",");
				}
				sb.append(c.getString(3));
				sb.append("\n");
				c.moveToNext();
			}
			return sb.toString();
		}
		catch(Exception e){return null;}
	}
	
	
	public void storeDb(View view){
		String filename = ((EditText) findViewById(R.id.dbName)).getText().toString();
		File file = new File(this.getFilesDir()+"/"+filename);		
		try{
			PrintWriter printWriter = new PrintWriter(file);
	    	String dbString = this.printDb();
	    	printWriter.write(dbString);
	    	printWriter.close();
	    	Toast toast = Toast.makeText(getApplicationContext(), "file "+filename+" is now in database", Toast.LENGTH_SHORT);
	    	toast.show();		
		}
		catch(Exception e){}
	}

}
