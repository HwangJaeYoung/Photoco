package com.continueing.photoco.ui.menu.joblist_page.joblist_detail_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForJobListDetailActivity extends AbstractViewForActivity {

	private Controller controller;
	
	public ViewForJobListDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(
				R.layout.activity_job_list_job_detail, null);
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