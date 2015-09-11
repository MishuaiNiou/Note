package com.example.mynote;

import java.io.File;
import java.io.FileNotFoundException;

import com.example.mynote.data.DBDao;
import com.example.mynote.utils.BitmapCompressUtil;
import com.example.mynote.utils.BitmapToDrawable;
import com.example.mynote.utils.TimeFormatUtil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseActivity extends Activity implements OnClickListener{
	
	protected TextView head;
	protected ImageView back;
	
	protected ImageView photo;
	protected ImageView camera;
	protected ImageView save;
	protected ImageView delete;
	
	protected DBDao dao;
	protected EditText contentText;
	protected LinearLayout linearLayout;
	protected LinearLayout.LayoutParams layoutParams;
	protected String fileName;
	protected String path;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.base_activity);
		
		head = (TextView) findViewById(R.id.head);
		back = (ImageView) findViewById(R.id.back);
		back.setVisibility(View.VISIBLE);
		
		photo = (ImageView) findViewById(R.id.photo);
		camera = (ImageView) findViewById(R.id.camera);
		save = (ImageView) findViewById(R.id.save);
		delete = (ImageView) findViewById(R.id.delete);
		
//		contentText = new EditText(getApplicationContext());
		
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		
		dao = new DBDao(getApplicationContext());
		
		photo.setOnClickListener(this);
		camera.setOnClickListener(this);
		save.setOnClickListener(this);
		delete.setOnClickListener(this);
		
	}



	@Override
	public void onClick(View v) {
		
		Intent intent;
		switch (v.getId()) {
		case R.id.photo:
		  /*ACTION_PICK          Android.intent.action.PICK 从列表中选择某项并返回所选数据
			ACTION_GET_CONTENT   Android.intent.action.GET_CONTENT 让用户选择数据，并返回所选数据*/
			
			intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, 1);
			break;
			
		case R.id.camera:
			fileName = TimeFormatUtil.getTimeName() + ".png";
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File root = Environment.getExternalStorageDirectory().getAbsoluteFile();
				File dir = new File(root.getAbsolutePath()+"/imgs");
				if(!dir.exists()){
					dir.mkdirs();
				}
				intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				File f = new File(dir, fileName);
				Uri u = Uri.fromFile(f);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
				startActivityForResult(intent, 2);
			}
			break;
			
		case R.id.save:
			dao.addNote(contentText.getText().toString(), path, TimeFormatUtil.getTime());
			finish();
			break;
		case R.id.delete:
			
			break;

		}
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if(resultCode==RESULT_OK){
				Uri uri = data.getData();
				String[] proj = {MediaStore.Images.Media.DATA};
				Cursor cursor = managedQuery(uri, proj, null, null, null);
				int col_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				if(cursor.moveToFirst()){
					path = cursor.getString(col_index);
					Log.i("--->>", path);
					Bitmap bitmap = BitmapCompressUtil.getImageThumbnail(path, 1000, 1000);
					Log.i("-->>", bitmap.getWidth()+","+bitmap.getHeight());
//					iv.setImageBitmap(bitmap);
					
					Drawable d = BitmapToDrawable.getDrawable(bitmap);
					SpannableString ss = new SpannableString("photo");
					d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
					Log.i("<<--->>", d.getIntrinsicWidth()+","+d.getIntrinsicHeight());
//					d.setBounds(0, 0, 200, 200);
					ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
					ss.setSpan(span, 0, "photo".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					

					Editable et = contentText.getText();
					int start = contentText.getSelectionStart();
					et.insert(start, ss);
					contentText.setText(et);
					contentText.setSelection(start+ss.length());
					contentText.append("\n");
				}
			}	
				
			break;

		case 2:
			if(resultCode==RESULT_OK){
			/*	Bundle bundle = data.getExtras();
				Bitmap bm = (Bitmap) bundle.get("data"); //获取的是缩略图*/
//				Bitmap bm = data.getParcelableExtra("data")  //获取的也是缩略图;
				
/*				String fileName = TimeFormatUtil.getTimeName()+".png";
				
				try {
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
					byte[] content = byteArrayOutputStream.toByteArray();
					
					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//						File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
						File root = Environment.getExternalStorageDirectory();
						File dir = new File(root.getAbsolutePath()+"/imgs");
						if(!dir.exists()){
							dir.mkdirs();
						}
						FileOutputStream outputStream = new FileOutputStream(new File(dir, fileName));
						outputStream.write(content, 0, content.length);
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				
				File f = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/imgs/"+fileName);
					try {
						String s = android.provider.MediaStore.Images.Media.insertImage(getContentResolver(), f.getAbsolutePath(), null, null);
						Log.i("INSERT PATH", s);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/imgs/"+fileName;
				Log.i("PATH", path);
				Bitmap bitmap = BitmapCompressUtil.getImageThumbnail(path, 1000, 1000);
				Log.i("BITMAP SIZE", bitmap.getWidth()+","+bitmap.getHeight());
//				iv.setImageBitmap(bitmap);
				
				Drawable d = BitmapToDrawable.getDrawable(bitmap);
				SpannableString ss = new SpannableString("photo");
				d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
				Log.i("Drawable Size", d.getIntrinsicWidth()+","+d.getIntrinsicHeight());
//				d.setBounds(0, 0, 200, 200);
				ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
				ss.setSpan(span, 0, "photo".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
				

				Editable et = contentText.getText();
				int start = contentText.getSelectionStart();
				et.insert(start, ss);
				contentText.setText(et);
				contentText.setSelection(start+ss.length());
				contentText.append("\n");	
				
		
			}
			break;
		}
	}

}
