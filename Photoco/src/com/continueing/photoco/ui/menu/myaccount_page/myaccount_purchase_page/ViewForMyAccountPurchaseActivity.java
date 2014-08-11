package com.continueing.photoco.ui.menu.myaccount_page.myaccount_purchase_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForMyAccountPurchaseActivity extends AbstractViewForActivity {
	
	private Controller controller;
	
	public ViewForMyAccountPurchaseActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_myaccount_purchase, null);
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
