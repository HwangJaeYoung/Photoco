package com.continueing.photoco.ui.menu.myphoto_page;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.ui.menu.myphoto_page.myphoto_detail_page.MyPhotoDetailActivity;
import com.continueing.photoco.ui.menu.myphoto_page.myphoto_newphoto_page.MyPhotoNewPhotoActivity;

public class MyPhotoFragment extends Fragment implements ViewForMyPhotoFragment.Controller {
	private ViewForMyPhotoFragment view;
	private ActionBar actionBar;
	private ActionBar.Tab actionBarTab;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarTab( );
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForMyPhotoFragment(getActivity( ), inflater, container, this);        
        return view.getRoot( );
    }
	
	@Override
	public void onPhotoSelected() {
		Intent intent = new Intent(getActivity( ), MyPhotoDetailActivity.class);
		startActivity(intent);
	}

	@Override
	public void onNewPhotoSelected() {
		Intent intent = new Intent(getActivity( ), MyPhotoNewPhotoActivity.class);
		startActivity(intent);
	}
	
	public void addActionBarTab( ) {
		actionBar = ((ActionBarActivity)getActivity( )).getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		
		TabListener findjobListener = new TabListener(null);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Bought");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Uploaded");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);
	}
	
	private class TabListener implements ActionBar.TabListener {
		public TabListener(Fragment aFragment) { }

		@Override
		public void onTabSelected(Tab aTabName, FragmentTransaction arg1) {

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