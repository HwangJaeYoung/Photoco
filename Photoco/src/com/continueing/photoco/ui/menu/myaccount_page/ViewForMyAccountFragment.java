package com.continueing.photoco.ui.menu.myaccount_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Purchase;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.continueing.photoco.ui.menu.myaccount_page.listview.ArrayAdapterForMyAccountListView;

public class ViewForMyAccountFragment extends AbstractViewForFragment {
	
	private Button btMyaccountPurchase;
	private Controller controller;
	private ListView lv_myaccountPurchase;
	private ProgressBar progressBar;
	private ArrayAdapterForMyAccountListView arrayAdapterForMyAccountListView;
	private LinearLayout ll_myaccountEmpty;
	
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
		lv_myaccountPurchase = (ListView)findViewById(R.id.lv_myaccount_purchase);
		arrayAdapterForMyAccountListView = new ArrayAdapterForMyAccountListView(getContext(), 0);
		lv_myaccountPurchase.setAdapter(arrayAdapterForMyAccountListView);
		
		btMyaccountPurchase = (Button)findViewById(R.id.bt_myaccount_purchase);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section7);
		
		progressBar = (ProgressBar)findViewById(R.id.pb_myaccount);
		ll_myaccountEmpty = (LinearLayout)findViewById(R.id.ll_myaccount_empty);
	}

	public void addMyAccountPurchaseArrayList(ArrayList<Purchase> anArrayList)
	{
		if(anArrayList.size() != 0) // 초기에 하나라도 아이템이 있으면
			setInvisible( );
		else 
			setVisible( );
		
		arrayAdapterForMyAccountListView.clear();
		arrayAdapterForMyAccountListView.addAll(anArrayList);
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
	
	public void setInvisible( ) {
		ll_myaccountEmpty.setVisibility(View.INVISIBLE);
	}
	
	public void setVisible( ) {
		ll_myaccountEmpty.setVisibility(View.VISIBLE);
	}
	
	public void progresOff( ) {
		progressBar.setVisibility(View.INVISIBLE);
	}
	
	public void progressOn( ) {
		progressBar.setVisibility(View.VISIBLE);
	}
	
	public void listviewOff( ) {
		lv_myaccountPurchase.setVisibility(View.INVISIBLE);
	}
	
	public void listviewOn( ) {
		lv_myaccountPurchase.setVisibility(View.VISIBLE);
	}
	
	public static interface Controller {
		public void onPurchase( );
	}
}