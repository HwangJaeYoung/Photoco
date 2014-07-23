package com.continueing.photoco.ui.menu.myphoto_page.myphoto_detail_page;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MyPhotoDetailActivity extends ActionBarActivity implements ViewForMyPhotoDetailActivity.Controller{
	
	private ViewForMyPhotoDetailActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new ViewForMyPhotoDetailActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
	}
}
