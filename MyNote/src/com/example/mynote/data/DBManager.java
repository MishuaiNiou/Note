package com.example.mynote.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

	private DataBase dataBase;
	private SQLiteDatabase sqLiteDatabase;
	
	public DBManager(Context context) {
		dataBase = new DataBase(context);
		sqLiteDatabase = dataBase.getWritableDatabase();
	}

	public Cursor query(String table, String[] columns, 
			              String selection, String[] selectionArgs, 
			              String groupBy, String having, String orderBy){
		
		Cursor cursor = sqLiteDatabase.query(
				table, columns, selection, selectionArgs, groupBy, having, orderBy);
		return cursor;
	}
	
	public boolean insert(String table, String nullColumnHack, ContentValues values){
		long count = sqLiteDatabase.insert(table, nullColumnHack, values);
		return count>0?true:false;
	}
	
	public boolean delete(String table, String whereClause, String[] whereArgs){
		int count = sqLiteDatabase.delete(table, whereClause, whereArgs);
		return count>0?true:false;
	}
	
	public boolean update(String table, ContentValues values, String whereClause, String[] whereArgs){
		int count = sqLiteDatabase.update(table, values, whereClause, whereArgs);
		return count>0?true:false;
	}
	
	public void closeConn(){
		if(sqLiteDatabase!=null){
			sqLiteDatabase.close();
		}
	}
	
}
