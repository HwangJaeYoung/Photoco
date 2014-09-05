package com.continueing.photoco.ui.menu.marketplace_page;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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
	private static final int REQUEST_ADD_TO_CART = 0;
	private int clickedImagePosition;
	public static final String PARAM_MARKETPLACE_IMAGE_ITEM ="marketplaceimageitem";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarTab( );
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForMarketplaceFragment(getActivity( ), inflater, container, this);
        actionBar.setSelectedNavigationItem(0);
        return view.getRoot( );
    }
	
	public void searchMarketplaceItemFromServer(String aTabName) {
		view.progressOn();
		view.gridviewOff();
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
			
			for(int i = 0; i < 7; i++) {
				try {
					Image image = new Image(jsonArray.getJSONObject(i));
					imageSet.add(image);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}			
			view.progresOff();
			view.gridviewviewOn();
			view.addMarketplaceImageSetArrayList(imageSet);
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};

	@Override
	public void onPhotoSelected(int aPosition) {
		clickedImagePosition = aPosition;
		Intent intent = new Intent(getActivity( ), MarketplaceDetailActivity.class);
		intent.putExtra(PARAM_MARKETPLACE_IMAGE_ITEM, imageSet.get(aPosition));
		startActivityForResult(intent, REQUEST_ADD_TO_CART);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_ADD_TO_CART) {
			if(resultCode == Activity.RESULT_OK) {
				imageSet.remove(clickedImagePosition);
				view.addMarketplaceImageSetArrayList(imageSet);
			}
		}
	}
	
	public void addActionBarTab( ) {
		actionBar = ((ActionBarActivity)getActivity( )).getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		
		TabListener marketplaceListener = new TabListener(null);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("BestSeller");
		actionBarTab.setTabListener(marketplaceListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("MostViewed");
		actionBarTab.setTabListener(marketplaceListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Latest");
		actionBarTab.setTabListener(marketplaceListener);
		actionBar.addTab(actionBarTab, false);
	}
	
	private class TabListener implements ActionBar.TabListener {
		public TabListener(Fragment aFragment) { }

		@Override
		public void onTabSelected(Tab aTabName, FragmentTransaction arg1) {
			if(aTabName.getText().toString().equals("BestSeller") && tabRestrict == true) {
				searchMarketplaceItemFromServer("bestseller");
				view.setInvisible();
				tabRestrict = false;
			}
			else if(aTabName.getText().equals("MostViewed")) {
				searchMarketplaceItemFromServer("mostviewd");
				view.setInvisible();
				tabRestrict = true;
			}
			else if(aTabName.getText().equals("Latest")) {	
				searchMarketplaceItemFromServer("latest");
				view.setInvisible();
				tabRestrict = true;
			}
		}
		
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) { }

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) { }
	}
	
	@Override
	public void onDetach( ) {
		super.onDetach();
		actionBar.removeAllTabs(); // 생성된 모든 탭을 지운다.
		//removeTab(ActionBar.Tab tab)는 하나만 지운다
	}
}