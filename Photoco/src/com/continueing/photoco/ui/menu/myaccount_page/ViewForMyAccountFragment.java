package com.continueing.photoco.ui.menu.myaccount_page;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;

public class ViewForMyAccountFragment extends AbstractViewForFragment {

	public ViewForMyAccountFragment(Context context, LayoutInflater layoutInflater, ViewGroup container) {
		super(context, layoutInflater, container);
		
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_account, container, false);
	}

	@Override
	protected void initViews() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section7);
		
	}

	@Override
	protected void setEvents() {
		
		
	}
}