package com.continueing.photoco.ui.menu.myphoto_page.myphoto_newphoto_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForMyPhotoNewPhotoActivity extends AbstractViewForActivity{

	private Controller controller;
	
	public ViewForMyPhotoNewPhotoActivity(Context context, Controller aController) {
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