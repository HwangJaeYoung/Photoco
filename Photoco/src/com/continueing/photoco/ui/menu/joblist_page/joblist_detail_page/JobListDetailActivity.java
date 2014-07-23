package com.continueing.photoco.ui.menu.joblist_page.joblist_detail_page;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class JobListDetailActivity extends ActionBarActivity implements ViewForJobListDetailActivity.Controller{
	private ViewForJobListDetailActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new ViewForJobListDetailActivity(getApplicationContext( ), this);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
	}
}