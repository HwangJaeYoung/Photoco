package com.continueing.photoco.ui.menu.myphoto_page.myphoto_detail_page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Image;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.marketplace_page.MarketplaceFragment;

public class ViewForMyPhotoDetailActivity extends AbstractViewForActivity {

	private Controller controller;
	
	public ViewForMyPhotoDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_myphoto_detail, null);
	}

	@Override
	protected void initViews() {
	
		
	}

	@Override
	protected void setEvent() {
	
		
	}
	
	public void initViewInfos(Intent anIntent) {
	
	
	}
	
	public static interface Controller {
		
	}
}
