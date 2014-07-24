package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

public class MyNewRequestDurationActivity extends ActionBarActivity implements ViewForMyNewRequestDurationActivity.Controller{
	private ViewForMyNewRequestDurationActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		view = new ViewForMyNewRequestDurationActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		setContentView(view.getRoot());
	}

	@Override
	public void onDurationSelected(int aDuration) {
		Intent intent = new Intent( );
		intent.putExtra("duration", aDuration);
		setResult(Activity.RESULT_OK, intent);
		finish( );
	}
}
