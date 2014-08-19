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

import com.continueing.photoco.domain.FindingJobList;
import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.reuse.network.FindingJobRequest;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.ui.menu.findingjob_page.findingjob_detail_page.FindingJobDetailActivity;

public class FindingJobFragment extends Fragment implements ViewForFindingJobFragment.Controller {

	private ActionBar actionBar;
	private ActionBar.Tab actionBarTab;
	private boolean tabRestrict = true;
	private ViewForFindingJobFragment view;
	private ArrayList<IFindingJobListItem> findingJobItems;
	public static final String PARAM_TAG_ITEM_KEY = "tagitemfileds";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarTab( );
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// this는 Controller를 위해서 넣어주는 것이다.
        view = new ViewForFindingJobFragment(getActivity( ), inflater, container, this); // 뷰를 생성해 낸다.
        actionBar.setSelectedNavigationItem(0);
        return view.getRoot();
    }

	// ListView에서 특정한 항목이 눌렸을 때 호출되는 메소드
	@Override
	public void onRequestSeleted(int aPosition) {
		// 디테일한 정보를 보여주는 새로운 액티비티를 띄운다.
		FindingJobList item= (FindingJobList)findingJobItems.get(aPosition);
		Intent intent = new Intent(getActivity( ), FindingJobDetailActivity.class);
		intent.putExtra(PARAM_TAG_ITEM_KEY, item);
		startActivity(intent);
	}
	
	public void searchFindingJobItemFromServer(String aTabName) {
		view.progressOn();
		view.listviewOff();
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
			Log.i("js","why");
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
			view.progresOff();
			view.listviewOn();
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { 
			Log.i("onnet", "success");
		}
	};
	
	public void addActionBarTab( ) {
		actionBar = ((ActionBarActivity)getActivity( )).getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		
		TabListener findjobListener = new TabListener(null);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Recommended");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);

		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Latest");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Distance");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);
	}
	
	private class TabListener implements ActionBar.TabListener {
		public TabListener(Fragment aFragment) { }
		
		/* 눌려진 액션바의 탭에 따라서 통신을 한 다음에 하나의 프레그먼트에서 데이터를 바꾼다.
		joblist->findingjob으로 갈 경우 onTabSelected가 두 번 콜 되어
		server와 통신을 두번하게 되므로 tabRestrict로 제한을 두었다.
		onTabUnselected 여기서 값을 바꾸는 것은 여러 예외상황 때문에 좋지못한듯하여
		else if안에서 수행하기로 한다. */
		@Override
		public void onTabSelected(Tab aTabName, FragmentTransaction arg1) {
			if(aTabName.getText().toString().equals("Recommended") && tabRestrict == true) {
				searchFindingJobItemFromServer("recommended");
				tabRestrict = false;
			}
			else if(aTabName.getText().equals("Latest")) {
				searchFindingJobItemFromServer("latest");
				tabRestrict = true;
			}
			else if(aTabName.getText().equals("Distance")) {	
				searchFindingJobItemFromServer("distance");
				tabRestrict = true;
			}
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