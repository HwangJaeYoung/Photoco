package com.continueing.photoco.ui.menu.findingjob_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.FindingJobList.ArrayAdapterForFindingJobListFragment;
import com.continueing.photoco.reuse.listview.FindingJobList.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.continueing.photoco.reuse.widget.ProgressBarFooter;

public class ViewForFindingJobFragment extends AbstractViewForFragment {
	private ProgressBarFooter progressBarFooter;
	private ListView lv_requestFindingJobList;
	private Controller controller;
	private ArrayAdapterForFindingJobListFragment arrayAdapterForFindingJobListFragment;
	private LayoutInflater layoutInflater;
	private ActionBar acb;
	private ActionBar.Tab tab;
	
	public ViewForFindingJobFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, Controller aController) {
		super(context, layoutInflater, container);
		controller = aController;
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup container) {
		this.layoutInflater = inflater;
		return inflater.inflate(R.layout.fragment_finding_job_list, container, false);
	}

	@Override
	protected void initViews() {
		arrayAdapterForFindingJobListFragment = new ArrayAdapterForFindingJobListFragment(getContext( ), 0);
		lv_requestFindingJobList = (ListView)findViewById(R.id.lv_request_finding_job_list);
		progressBarFooter = new ProgressBarFooter(lv_requestFindingJobList, layoutInflater);
		setProgressbarVisibility(true);
		lv_requestFindingJobList.setAdapter(arrayAdapterForFindingJobListFragment);
		
		// NavigationDrawerFragment에서 드로워를 열고 닫을 때 액션바 사용여부 확인용
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section1);
	}
	
	public void setProgressbarVisibility(boolean aVisibility)
    {
        this.progressBarFooter.setVisibility(aVisibility);
    }

	// 리스트 뷰의 특정 항목을 눌렸을 때 해야 할 명령
	@Override
	protected void setEvents() {
		lv_requestFindingJobList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				controller.onRequestSeleted(position);
			}
		});
	}

	// FindingJobFragment(Controller)로 부터 리스트 뷰의 데이터(Model)를 가지고 온다.
	public void addFindjobItemArrayList(ArrayList<IFindingJobListItem> anArrayList)
	{
		arrayAdapterForFindingJobListFragment.addAll(anArrayList);
		//setProgressbarVisibility(false);
	}
	
	public static interface Controller {
		public void onRequestSeleted(int aPosition);		
	}
	
	public void removeActionBarTab( )
	{
		acb.removeAllTabs(); // 생성된 모든 탭을 지운다.
		//removeTab(ActionBar.Tab tab)는 하나만 지운다
	}
	
	public void addActionBarTab(FragmentActivity activity) {
			acb = ((ActionBarActivity) activity).getSupportActionBar();
			acb.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			acb.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
			
			for(int i = 0; i < 3; i++)
			{
				tab = acb.newTab();
				String temp = "action";
				tab.setText(temp + i);
				TabFragment frag = new TabFragment( );
				tab.setTabListener(new TabListener(frag));
				acb.addTab(tab);
		}
	}
	
	private class TabListener implements ActionBar.TabListener{
		
		public TabListener(Fragment fragment) {
	
		}

		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
			
		}

		@Override
		public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
			
		}

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		
		}
	}
	
	public class TabFragment extends Fragment
	{
		
	}
}