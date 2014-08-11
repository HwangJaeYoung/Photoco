package com.continueing.photoco.ui.menu.myaccount_page;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;

public class ViewForMyAccountFragment extends AbstractViewForFragment {
	
	private Button btMyaccountPurchase;
	private Controller controller;
	
	public ViewForMyAccountFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, Controller aController) {
		super(context, layoutInflater, container);
		controller = aController;
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_myaccount, container, false);
	}

	@Override
	protected void initViews() {
		
		btMyaccountPurchase = (Button)findViewById(R.id.bt_myaccount_purchase);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section7);
	}

	@Override
	protected void setEvents() {
		btMyaccountPurchase.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onPurchase();				
			}
		});
	}
	
	public static interface Controller {
		public void onPurchase( );
	}
}