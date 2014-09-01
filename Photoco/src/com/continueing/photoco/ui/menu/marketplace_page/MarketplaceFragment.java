package com.continueing.photoco.ui.menu.marketplace_page;

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

import com.continueing.photoco.domain.Image;
import com.continueing.photoco.domain.Purchase;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.MarketpaceRequest;
import com.continueing.photoco.ui.menu.marketplace_page.marketplace_detail_page.MarketplaceDetailActivity;

public class MarketplaceFragment extends Fragment implements ViewForMarketplaceFragment.Controller{
	private ViewForMarketplaceFragment view;
	private ActionBar actionBar;
	private ActionBar.Tab actionBarTab;
	private boolean tabRestrict = true;
	private ArrayList<Image> imageSet;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarTab( );
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForMarketplaceFragment(getActivity( ), inflater, container, this);
        
        return view.getRoot( );
    }
	
	@Override
	public void onResume( ) {
		super.onResume();
		actionBar.setSelectedNavigationItem(0);
	}
	
	public void searchMarketplaceItemFromServer(String aTabName) {
		MarketpaceRequest marketpaceRequest = new MarketpaceRequest(getActivity( ));
		try {
			marketpaceRequest.getMarketplaceImageItems(aTabName, getMarketplaceItemListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener getMarketplaceItemListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) { 
			JSONArray jsonArray = new JSONArray( ); 
			imageSet = new ArrayList<Image>( );
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		
			for(int i = 0; i < 10; i++) {
				try {
					Image image = new Image(jsonArray.getJSONObject(i));
					imageSet.add(image);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}			
			view.addMarketplaceImageSetArrayList(imageSet);
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};

	@Override
	public void onPhotoSelected() {
		Intent intent = new Intent(getActivity( ), MarketplaceDetailActivity.class);
		startActivity(intent);
	}
	
	public void addActionBarTab( ) {
		actionBar = ((ActionBarActivity)getActivity( )).getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		
		TabListener findjobListener = new TabListener(null);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("BestSeller");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("MostViewed");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Latest");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab);
	}
	
	private class TabListener implements ActionBar.TabListener {
		public TabListener(Fragment aFragment) { }

		@Override
		public void onTabSelected(Tab aTabName, FragmentTransaction arg1) {
			if(aTabName.getText().toString().equals("BestSeller") && tabRestrict == true) {
				searchMarketplaceItemFromServer("bestseller");
				tabRestrict = false;
			}
			else if(aTabName.getText().equals("MostViewed")) {
				searchMarketplaceItemFromServer("mostviewd");
				tabRestrict = true;
			}
			else if(aTabName.getText().equals("Latest")) {	
				searchMarketplaceItemFromServer("latest");
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
}