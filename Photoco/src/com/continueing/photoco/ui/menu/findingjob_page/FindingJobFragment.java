package com.continueing.photoco.ui.menu.findingjob_page;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.continueing.photoco.domain.FindingJobList;
import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.reuse.network.FindingJobRequest;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.ui.menu.findingjob_page.findingjob_detail_page.FindingJobDetailActivity;

public class FindingJobFragment extends Fragment implements ViewForFindingJobFragment.Controller {

	private ActionBar actionBar;
	private ActionBar.Tab actionBarTab;
	private ViewForFindingJobFragment view;
	private ArrayList<IFindingJobListItem> findingJobItems;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarTab( );
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// this는 Controller를 위해서 넣어주는 것이다.
        view = new ViewForFindingJobFragment(getActivity( ), inflater, container, this); // 뷰를 생성해 낸다.
        searchFindingJobItemFromServer("recommended");
        return view.getRoot();
    }

	// ListView에서 특정한 항목이 눌렸을 때 호출되는 메소드
	@Override
	public void onRequestSeleted(int aPosition) {
		// 디테일한 정보를 보여주는 새로운 액티비티를 띄운다.
		Intent intent = new Intent(getActivity( ), FindingJobDetailActivity.class);
		startActivity(intent);
	}
	
	public void searchFindingJobItemFromServer(String aTabName) {
		FindingJobRequest findingJobRequest = new FindingJobRequest(getActivity( ));
		try {
			findingJobRequest.getFindingJobItem(aTabName, getFindingJobListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener getFindingJobListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			findingJobItems = new ArrayList<IFindingJobListItem>( );
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonRequestObject = null;
				
				try {
					jsonRequestObject = jsonArray.getJSONObject(i);
					FindingJobList request = new FindingJobList(jsonRequestObject);
					findingJobItems.add(request);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			// ViewForFindingJobFragment(View)에 데이터(Model)를 FindingJobFragment(Controller)에서 넘겨준다.
			view.addFindjobItemArrayList(findingJobItems);
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
		actionBarTab.setText("Recommended");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Latest");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Distance");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab);
	}
	
	private class TabListener implements ActionBar.TabListener {
		public TabListener(Fragment aFragment) { }

		@Override
		public void onTabSelected(Tab aTabName, FragmentTransaction arg1) {
			if(aTabName.getText().equals("Recommended"))
				searchFindingJobItemFromServer("recommended");
			else if(aTabName.getText().equals("Latest"))
				searchFindingJobItemFromServer("latest");
			else if(aTabName.getText().equals("Distance"))
				searchFindingJobItemFromServer("distance");
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