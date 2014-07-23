package com.continueing.photoco.ui.menu.myphoto_page.myphoto_newphoto_page;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MyPhotoNewPhotoActivity extends ActionBarActivity implements ViewForMyPhotoNewPhotoActivity.Controller{
private ViewForMyPhotoNewPhotoActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new ViewForMyPhotoNewPhotoActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
	}
}