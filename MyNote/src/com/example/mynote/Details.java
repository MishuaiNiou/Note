package com.example.mynote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

import com.example.mynote.data.DataBase;
import com.example.mynote.utils.BitmapCompressUtil;
import com.example.mynote.utils.BitmapToDrawable;

public class Details extends BaseActivity {
	
	private ScrollView scrollView;
	
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		head.setText("ÏêÇé");
		
		String text = getIntent().getStringExtra(DataBase.TEXT);
		String path = getIntent().getStringExtra(DataBase.IMAGE_PATH);
		String time = getIntent().getStringExtra(DataBase.TIME);
//		String id = getIntent().getStringExtra(DataBase.ID);
		
		contentText = new EditText(this);
		contentText.setGravity(Gravity.TOP);
		contentText.setBackground(null);
		
		if(contentText!=null && path==null){
			contentText.setText(text);
			
		}else if(contentText==null && path!=null){
			Bitmap bitmap = BitmapCompressUtil.getImageThumbnail(path, 1000, 1000);
			Log.i("-->>", bitmap.getWidth()+","+bitmap.getHeight());
			Drawable d = BitmapToDrawable.getDrawable(bitmap);
			
			SpannableString ss = new SpannableString("photo");
			d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
			Log.i("<<--->>", d.getIntrinsicWidth()+","+d.getIntrinsicHeight());
			ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
			ss.setSpan(span, 0, "photo".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			
			Editable et = contentText.getText();
			int start = contentText.getSelectionStart();
			et.insert(start, ss);
			contentText.setText(et);
			contentText.setSelection(start+ss.length());
			contentText.append("\n");
			
		}else if(contentText!=null && path!=null){
			
		}else{
			
		}
		


		layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(20, 20, 20, 20);
		linearLayout.addView(contentText, layoutParams);
		
		contentText.setCursorVisible(false); //Òþ²Ø¹â±ê
//		contentText.setCursorVisible(true);
		
//		contentText.clearFocus();
//		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//		inputMethodManager.hideSoftInputFromWindow(contentText.getWindowToken(), 0);   //Òþ²ØÈí¼üÅÌ
//		inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		
		scrollView = (ScrollView) findViewById(R.id.sv);
		scrollView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				// TODO Auto-generated method stub
				contentText.setCursorVisible(true);
				return false;
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		 switch (v.getId()) {
		case R.id.delete:
			int id = getIntent().getIntExtra(DataBase.ID,0);
			dao.deleteNote(id);
			finish();
			break;

		}
	}
	
/*	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return super.dispatchTouchEvent(ev);
		
	}*/
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
//		contentText.setCursorVisible(true);
		return super.onTouchEvent(event);
	}
	

}
