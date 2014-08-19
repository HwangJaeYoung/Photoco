package com.continueing.photoco.ui.menu.joblist_page;

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
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.domain.FindingJobList;
import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.reuse.network.FindingJobRequest;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.ui.menu.joblist_page.joblist_detail_page.JobListDetailActivity;

public class JobListFragment extends Fragment implements ViewForJobListFragment.Controller{
	private ViewForJobListFragment view;
	private ActionBar actionBar;
	private ActionBar.Tab actionBarTab;
	private ArrayList<IFindingJobListItem> jobListItems;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarTab( );
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForJobListFragment(getActivity( ), inflater, container, this); // 뷰를 생성해 낸다.
        ArrayList<IFindingJobListItem> arrayList = new ArrayList<IFindingJobListItem>( );
   
        searchJobListItemFromServer("d");
        view.addJobListItemArrayList(arrayList);
        actionBar.setSelectedNavigationItem(0);
        return view.getRoot();
    }

	@Override
	public void onItemSelected(int aPositions) {
		Intent intent = new Intent(getActivity( ), JobListDetailActivity.class);
		startActivity(intent);
	}
	
	public void searchJobListItemFromServer(String aTabName) {
		FindingJobRequest findingJobRequest = new FindingJobRequest(getActivity( ));
	}
	
	HttpRequester.NetworkResponseListener getJobListListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;
			Log.i("find", jsonObject.toString());
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			jobListItems = new ArrayList<IFindingJobListItem>( );
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonRequestObject = null;
				
				try {
					jsonRequestObject = jsonArray.getJSONObject(i);
					FindingJobList request = new FindingJobList(jsonRequestObject);
					jobListItems.add(request);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			// ViewForFindingJobFragment(View)에 데이터(Model)를 FindingJobFragment(Controller)에서 넘겨준다.
			view.addJobListItemArrayList(jobListItems);
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	public void addActionBarTab( ) {
		actionBar = ((ActionBarActivity)getActivity( )).getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		
		TabListener findjobListener = new TabListener(null);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Time");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Category");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Distance");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);
	}
	
	private class TabListener implements ActionBar.TabListener {
		public TabListener(Fragment aFragment) { }

		@Override
		public void onTabSelected(Tab aTabName, FragmentTransaction arg1) {
			if(aTabName.getText().equals("Recommended"))
				searchJobListItemFromServer("recommended");
			else if(aTabName.getText().equals("Latest"))
				searchJobListItemFromServer("latest");
			else if(aTabName.getText().equals("Distance"))
				searchJobListItemFromServer("distance");
		}
		
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) { }

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) { }
	}
	
	// 프래그먼트가 replece에 의해서 지워질 때 액션바에 생성한 탭을 삭제한다.
	@Override
	public void onDetach( )
	{
		super.onDetach();
		actionBar.removeAllTabs(); // 생성된 모든 탭을 지운다.
		//removeTab(ActionBar.Tab tab)는 하나만 지운다
	}
}