package com.continueing.photoco.ui.menu.myrequest_page.myrequest_detail_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForMyRequestDetailActivity extends AbstractViewForActivity{

	private Controller controller;
	
	public ViewForMyRequestDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_myphoto_upload, null);
		
	}

	@Override
	protected void initViews() {
		
		
	}
	

	@Override
	protected void setEvent() {
	
		
	}
	
	public static interface Controller
	{
		
	}
}