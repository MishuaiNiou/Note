package com.example.mynote.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynote.R;

public class MyAdapter extends BaseAdapter {
	
	private Context context;
	private Cursor cursor;

	public MyAdapter(Context context , Cursor cursor) {
		this.context = context;
		this.cursor = cursor;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cursor.getPosition();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		View view = null;
		if(view != null){
			view = convertView;
		}else{
			view = LayoutInflater.from(context).inflate(R.layout.item, null);
			TextView text = (TextView) view.findViewById(R.id.text);
			TextView time = (TextView) view.findViewById(R.id.time);
			ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
			cursor.moveToPosition(position);
			String text1 = cursor.getString(cursor.getColumnIndex("text"));
			String time1 = cursor.getString(cursor.getColumnIndex("time"));
			String url = cursor.getString(cursor.getColumnIndex("path"));
			text.setText(text1);
			time.setText(time1);
		}
		return view;
	}

}
