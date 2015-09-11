package com.example.mynote.utils;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class BitmapCompressUtil {

	public BitmapCompressUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Bitmap getImageThumbnail(String path, int width,int height){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;  //返回的bitmap对象为空，不分配内存空间，但是可以得到图片大小等信息
		Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		options.inSampleSize = calculateInSampleSize(options, width, height);
		options.inJustDecodeBounds = false;  //返回缩放后的实际的bitmap对象
		bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}
	
	public static int calculateInSampleSize(BitmapFactory.Options options, int width, int height){
		int oldHeight = options.outHeight;
		int oldWidth = options.outWidth;
		int rate;
		if(oldHeight > height || oldWidth > width){
			int heightRatio = Math.round((float)oldHeight / height);
			int widthRatio = Math.round((float)oldWidth / width);
			rate = heightRatio<=widthRatio?heightRatio:widthRatio;
		}else{
			rate = 1;
		}
		return rate;
	}
	
	
	/*	public static Bitmap getImageThumbnail(InputStream inputStream, int width,int height){
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true; //返回的bitmap对象为空，不分配内存空间，但是可以得到图片大小等信息
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
			options.inSampleSize = calculateInSampleSize(options, width, height);
			options.inJustDecodeBounds = false; //返回缩放后的实际的bitmap对象
			bitmap = BitmapFactory.decodeStream(inputStream, null, options);
			return bitmap;
		}
		
		public static Bitmap getImageThumbnail(Uri uri, int width,int height){
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;  //返回的bitmap对象为空，不分配内存空间，但是可以得到图片大小等信息
			Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath(), options);
			options.inSampleSize = calculateInSampleSize(options, width, height);
			options.inJustDecodeBounds = false;  //返回缩放后的实际的bitmap对象
			bitmap = BitmapFactory.decodeFile(uri.getPath(), options);
			return bitmap;
		}*/
}
