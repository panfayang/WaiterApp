package com.example.waiterapp;
/**
 * @author panfayang
 * @data Oct 20, 2013
 * @description This class defines the column names of the database.
 */

import android.provider.BaseColumns;

public final class WaiterAppContract {
	
	public WaiterAppContract(){}
	
	public static abstract class WaiterApp implements BaseColumns{
		public static final String TABLE_NAME = "waiterapp";
		public static final String COLUMN_NAME_CT = "Entry_Time";
		public static final String COLUMN_NAME_TABLE_ID = "Table_Id";
		public static final String COLUMN_NAME_PATRON_NUMBER = "Patron_No";
		public static final String COLUMN_NAME_MEAL_CHOICE = "Food_Choice";
		public static final String COLUMN_NAME_NULLABLE = null;
		
	}

}
