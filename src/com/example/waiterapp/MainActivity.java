/**
 * Welcome to WaiterApp! This app enables a waiter to log in and manage 
 * information about the patrons.
 * 
 * Help received from Kyle, Lucas, Android website, and StackOverflow.
 * 
 * A project from Fall 2013 Comp 489/490, Kalamazoo College
 */

package com.example.waiterapp;

/**
 * @author panfayang
 * @data Oct 20, 2013
 * @description This class is the main activity class.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.waiterapp.WaiterAppContract.WaiterApp;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void enterPatrons(View view){
    	Intent intent = new Intent(this, EnterPatronsActivity.class);
    	startActivity(intent);
	}
	
	public void viewDb(View view){
    	Intent intent = new Intent(this, ViewDbActivity.class);
    	startActivity(intent);
	}
	
	public void loadDb(View view){
    	Intent intent = new Intent(this, LoadDbActivity.class);
    	startActivity(intent);
	}
	
	public void clearDb(View view){
		Intent intent = new Intent(this, ClearDbActivity.class);
		startActivity(intent);
	}
	
	public void storeDb(View view){
    	Intent intent = new Intent(this, StoreDbActivity.class);
    	startActivity(intent);	
	}
	
	public void exit(View view){
    	finish();
    	System.exit(0);
	}
	
	/*
	 * Clears the current saved database 
	 */
	public void clearData(View view){
		WaiterAppDbHelper myDbHelper = new WaiterAppDbHelper(this);
		SQLiteDatabase db = myDbHelper.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + WaiterApp.TABLE_NAME);
		myDbHelper.onCreate(db);
		Toast toast = Toast.makeText(getApplicationContext(), "Now the current database is empty", Toast.LENGTH_SHORT);
		toast.show();

	}
	

}
