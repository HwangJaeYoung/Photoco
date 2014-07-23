package com.continueing.photoco.ui.menu.myrequest_page.myrequest_detail_page;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class MyRequestDetailActivity extends ActionBarActivity implements ViewForMyRequestDetailActivity.Controller{

	private ViewForMyRequestDetailActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new ViewForMyRequestDetailActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		setContentView(view.getRoot());
	}
}
