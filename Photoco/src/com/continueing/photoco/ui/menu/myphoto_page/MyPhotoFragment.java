package com.continueing.photoco.ui.menu.myphoto_page;

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
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.MyPhotoRequest;
import com.continueing.photoco.ui.menu.myphoto_page.myphoto_detail_page.MyPhotoDetailActivity;
import com.continueing.photoco.ui.menu.myphoto_page.myphoto_newphoto_page.MyPhotoNewPhotoActivity;

public class MyPhotoFragment extends Fragment implements ViewForMyPhotoFragment.Controller {
	private ViewForMyPhotoFragment view;
	private ActionBar actionBar;
	private boolean tabRestrict = true;
	private ActionBar.Tab actionBarTab;
	private ArrayList<Image> imageSet;
	public static final String PARAM_MYPHOTO_IMAGE_ITEM ="myphotoimageitem";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarTab( );
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForMyPhotoFragment(getActivity( ), inflater, container, this); 
        actionBar.setSelectedNavigationItem(0);
        return view.getRoot( );
    }
	
	public void searchMyPhotoItemFromServer(String aTabName) {
		MyPhotoRequest myPhotoRequest = new MyPhotoRequest(getActivity( ));
		
		if(aTabName.equals("bought")) {
			try {
				myPhotoRequest.getMyPhotoBoughtImageItems(getMyPhotoItemListener);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else if(aTabName.equals("uploaded")) { 
			try {
				myPhotoRequest.getMyPhotoUploadedImageItems(getMyPhotoItemListener);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	HttpRequester.NetworkResponseListener getMyPhotoItemListener = new HttpRequester.NetworkResponseListener() {
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
			view.addMyPhotoImageSetArrayList(imageSet);
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	@Override
	public void onPhotoSelected(int aPosition) {
		Intent intent = new Intent(getActivity( ), MyPhotoDetailActivity.class);
		intent.putExtra(PARAM_MYPHOTO_IMAGE_ITEM, imageSet.get(aPosition));
		startActivity(intent);
	}

	public void addActionBarTab( ) {
		actionBar = ((ActionBarActivity)getActivity( )).getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		
		TabListener myPhotoListener = new TabListener(null);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Bought");
		actionBarTab.setTabListener(myPhotoListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Uploaded");
		actionBarTab.setTabListener(myPhotoListener);
		actionBar.addTab(actionBarTab, false);
	}
	
	private class TabListener implements ActionBar.TabListener {
		public TabListener(Fragment aFragment) { }

		@Override
		public void onTabSelected(Tab aTabName, FragmentTransaction arg1) {
			if(aTabName.getText().toString().equals("Bought") && tabRestrict == true) {
				searchMyPhotoItemFromServer("bought");
				tabRestrict = false;
			}
			else if(aTabName.getText().equals("Uploaded")) {
				searchMyPhotoItemFromServer("uploaded");
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