package com.example.mynote;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mynote.adapter.MyAdapter;
import com.example.mynote.data.DBDao;
import com.example.mynote.data.DataBase;

public class MainActivity extends Activity implements OnItemClickListener{

	private ListView listView;
	private TextView head;
	private ImageView add;
	private DBDao dao;
	private Cursor cursor;
	private MyAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        head = (TextView) findViewById(R.id.head);
        head.setText("±„«©");
        add = (ImageView) findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, AddNew.class);
				startActivity(intent);
			}
		});
        
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		cursor.moveToPosition(position);
		int pid = cursor.getInt(cursor.getColumnIndex(DataBase.ID));
		String text = cursor.getString(cursor.getColumnIndex(DataBase.TEXT));
		String time = cursor.getString(cursor.getColumnIndex(DataBase.TIME));
		String path = cursor.getString(cursor.getColumnIndex(DataBase.IMAGE_PATH));
		
		Intent i = new Intent(MainActivity.this, Details.class);
		i.putExtra(DataBase.ID, pid);
		i.putExtra(DataBase.TEXT, text);
		i.putExtra(DataBase.TIME, time);
		i.putExtra(DataBase.IMAGE_PATH, path);
		
		startActivity(i);
	}
    
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		 dao = new DBDao(getApplicationContext());
	        cursor = dao.listNote();
	        adapter = new MyAdapter(this,cursor);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(this);
		
	}
    
    
}
