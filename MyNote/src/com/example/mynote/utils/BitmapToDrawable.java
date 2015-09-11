package com.example.mynote.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


public class BitmapToDrawable {

	public BitmapToDrawable() {
		// TODO Auto-generated constructor stub
	}
	
	public static Drawable getDrawable(Bitmap bitmap){
		Drawable drawable = new BitmapDrawable(bitmap);
		return drawable;
	}

}
