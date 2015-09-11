package com.example.mynote.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
	
	public static final String TABLE_NAME = "Note";
	public static final String ID = "pid";
	public static final String TEXT = "text";
	public static final String IMAGE_PATH = "path";
	public static final String TIME = "time";

	public DataBase(Context context) {
		super(context, "MyNote.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE " +TABLE_NAME +"("+ 
		                  ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
					      TEXT +" TEXT ," +
						  IMAGE_PATH +" TEXT ," +
					      TIME + " TEXT NOT NULL)";
		
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
