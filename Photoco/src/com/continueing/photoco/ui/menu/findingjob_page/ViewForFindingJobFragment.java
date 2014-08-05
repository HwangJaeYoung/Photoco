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
import android.widget.Toast;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.findingjoblist.ArrayAdapterForFindingJobListFragment;
import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.continueing.photoco.reuse.widget.ProgressBarFooter;

public class ViewForFindingJobFragment extends AbstractViewForFragment {
	private ListView lv_requestFindingJobList;
	private Controller controller;
	private ArrayAdapterForFindingJobListFragment arrayAdapterForFindingJobListFragment;
	
	public ViewForFindingJobFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, Controller aController) {
		super(context, layoutInflater, container);
		controller = aController;
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_findingjoblist, container, false);
	}

	@Override
	protected void initViews() {
		arrayAdapterForFindingJobListFragment = new ArrayAdapterForFindingJobListFragment(getContext( ), 0);
		lv_requestFindingJobList = (ListView)findViewById(R.id.lv_request_finding_job_list);
		lv_requestFindingJobList.setAdapter(arrayAdapterForFindingJobListFragment);
		
		// NavigationDrawerFragment에서 드로워를 열고 닫을 때 액션바 사용여부 확인용
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section1);
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
		arrayAdapterForFindingJobListFragment.clear();
		arrayAdapterForFindingJobListFragment.addAll(anArrayList);
	}
	
	public static interface Controller {
		public void onRequestSeleted(int aPosition);		
	}
}