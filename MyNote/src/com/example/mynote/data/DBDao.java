package com.example.mynote.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBDao {

	private DBManager dbManager;
	
	public DBDao(Context context) {
		dbManager = new DBManager(context);
	}
	
	public Cursor listNote(){
		Cursor cursor = dbManager.query(DataBase.TABLE_NAME, null, null, null, null, null, DataBase.ID +" DESC");
		return cursor;
	}
	
	public boolean addNote(String text,String path,String time){
		boolean flag = false;
		ContentValues values = new ContentValues();
		values.put("text", text);
		values.put("path", path);
		values.put("time", time);
		flag = dbManager.insert(DataBase.TABLE_NAME, null, values);
		return flag;
	}
	
	public boolean deleteNote(String[] id){
		boolean flag = false;
		flag = dbManager.delete(DataBase.TABLE_NAME, "pid = ?", id);
		return flag;
	}
	
	public boolean deleteNote(int id){
		boolean flag = false;
		flag = dbManager.delete(DataBase.TABLE_NAME, "pid="+ id, null);
		return flag;
	}
	
	public boolean updateNote(String text,String path,String time,String[] id){
		boolean flag = false;
		ContentValues values = new ContentValues();
		values.put("text", text);
		values.put("path", path);
		values.put("time", time);
		flag = dbManager.update(DataBase.TABLE_NAME, values, "pid = ?", id);
		return flag;
	}

}
