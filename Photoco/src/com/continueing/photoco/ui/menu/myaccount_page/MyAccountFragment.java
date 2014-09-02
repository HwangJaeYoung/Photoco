package com.continueing.photoco.ui.menu.myaccount_page;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.domain.Purchase;
import com.continueing.photoco.reuse.network.AccountRequest;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.ui.menu.myaccount_page.myaccount_purchase_page.MyAccountPurchaseActivity;

public class MyAccountFragment extends Fragment implements ViewForMyAccountFragment.Controller{
	
	private ViewForMyAccountFragment view;
	private ActionBar actionBar;
	private ActionBar.Tab actionBarTab;
	private ArrayList<Purchase> purchaseSet;
	private int savedTabIndex;
	private boolean tabRestrict = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarTab( );
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForMyAccountFragment(getActivity( ), inflater, container, this);
        actionBar.setSelectedNavigationItem(0);
        return view.getRoot();
	}
	
	// 내가 요청한 것들에서 다른 사용자가 등록한 이미지를 가져오기 위한 통신
	public void searchPurchaseItemFromServer(String aTabName) {
		view.progressOn();
		view.listviewOff();
		AccountRequest accountRequest = new AccountRequest(getActivity( ));
		try {
			accountRequest.getPurchaseItems(aTabName, getPurchasesItemListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	HttpRequester.NetworkResponseListener getPurchasesItemListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) { 
			JSONArray jsonArray = new JSONArray( ); 
			purchaseSet = new ArrayList<Purchase>( );
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < 7; i++) {
				try {
					Purchase purchse = new Purchase(jsonArray.getJSONObject(i), savedTabIndex);
					purchaseSet.add(purchse);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}			
			view.progresOff();
			view.listviewOn();
			view.addMyAccountPurchaseArrayList(purchaseSet);
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	
	public void addActionBarTab( ) {
		actionBar = ((ActionBarActivity)getActivity( )).getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		
		TabListener myaccountListener = new TabListener(null);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Sold");
		actionBarTab.setTabListener(myaccountListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Bought");
		actionBarTab.setTabListener(myaccountListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("All");
		actionBarTab.setTabListener(myaccountListener);
		actionBar.addTab(actionBarTab, false);
	}
	
	private class TabListener implements ActionBar.TabListener {
		public TabListener(Fragment aFragment) { }

		@Override
		public void onTabSelected(Tab aTabName, FragmentTransaction arg1) {
			if(aTabName.getText().toString().equals("Sold") && tabRestrict == true) {
				searchPurchaseItemFromServer("sold");
				savedTabIndex = 0;
				tabRestrict = false;
			}
			else if(aTabName.getText().equals("Bought")) {
				searchPurchaseItemFromServer("bought");
				savedTabIndex = 1;
				tabRestrict = true;
			}
			else if(aTabName.getText().equals("All")) {	
				searchPurchaseItemFromServer("bought");
				savedTabIndex = 2;
				tabRestrict = true;
			}
		}
		
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) { }

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) { }
	}
	
	@Override
	public void onDetach( )
	{
		super.onDetach();
		actionBar.removeAllTabs(); // 생성된 모든 탭을 지운다.
		//removeTab(ActionBar.Tab tab)는 하나만 지운다
	}

	@Override
	public void onPurchase() {
		Intent intent = new Intent(getActivity( ), MyAccountPurchaseActivity.class);
		startActivity(intent);		
	}
}
