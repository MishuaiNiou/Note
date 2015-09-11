package com.example.mynote.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.mynote.data.DBDao;
import com.example.mynote.data.DataBase;
import com.example.mynote.utils.TimeFormatUtil;

public class test extends AndroidTestCase {

	public test() {
		
	}
	
	public void test1(){
		DBDao dao = new DBDao(getContext());
//		boolean flag = dao.addNote("hhhhfsdvfshh", "jjvsvsj", TimeFormatUtil.getTime());
//		boolean flag = dao.updateNote("1", "1", "1", new String[]{"1"});
//		boolean flag = dao.deleteNote(new String[]{"1"});
//		Log.i("------>>", "----->>"+flag);
		
		Cursor cursor = dao.listNote();
		int count = cursor.getColumnCount();
		while(cursor.moveToNext()){
			for(int i=0;i<count;i++){
				String key = cursor.getColumnName(i);
				String value = cursor.getString(cursor.getColumnIndex(key));
				Log.i("---->>>", "--->>"+key+","+value);
			}
		}
		
		
	}

}
