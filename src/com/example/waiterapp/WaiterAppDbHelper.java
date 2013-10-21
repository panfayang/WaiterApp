package com.example.waiterapp;
/**
 * @author panfayang
 * @data Oct 20, 2013
 * @description This class is the DbHelper class.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.waiterapp.WaiterAppContract.WaiterApp;

public class WaiterAppDbHelper extends SQLiteOpenHelper{
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES = 
			"CREATE TABLE " + WaiterApp.TABLE_NAME + " (" +
							WaiterApp._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
							WaiterApp.COLUMN_NAME_CT + TEXT_TYPE + COMMA_SEP +
							WaiterApp.COLUMN_NAME_TABLE_ID + INT_TYPE + COMMA_SEP +
							WaiterApp.COLUMN_NAME_PATRON_NUMBER + INT_TYPE + COMMA_SEP +
							WaiterApp.COLUMN_NAME_MEAL_CHOICE + TEXT_TYPE + ")";
	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + WaiterApp.TABLE_NAME;
	
	// If you change your schema you must increment the DB version
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "WaiterApp.db";
	
	public WaiterAppDbHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){ 
		db.execSQL(SQL_CREATE_ENTRIES);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}
	
	public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion){
		onUpgrade(db, oldVersion, newVersion);
	}
	
	

}
