package com.continueing.photoco.ui.menu.joblist_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.FindingJobList.ArrayAdapterForFindingJobListFragment;
import com.continueing.photoco.reuse.listview.FindingJobList.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;

public class ViewForJobListFragment extends AbstractViewForFragment{

	private Controller controller;
	private ArrayAdapterForFindingJobListFragment arrayAdapterForFindingJobListFragment;
	private ListView lv_requestFindingJobList;
	
	public ViewForJobListFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, Controller aController) {
		super(context, layoutInflater, container);
		controller = aController;
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_finding_job_list, container, false);
	}

	@Override
	protected void initViews() {
		lv_requestFindingJobList = (ListView)findViewById(R.id.lv_request_finding_job_list);
		arrayAdapterForFindingJobListFragment = new ArrayAdapterForFindingJobListFragment(getContext( ), 0);
		lv_requestFindingJobList.setAdapter(arrayAdapterForFindingJobListFragment);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section3);
	}

	@Override
	protected void setEvents() {
		lv_requestFindingJobList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				controller.onItemSelected(position);
			}
		});
	}
	
	public void addJobListItemArrayList(ArrayList<IFindingJobListItem> anArrayList)
	{
		arrayAdapterForFindingJobListFragment.addAll(anArrayList);
		//setProgressbarVisibility(false);
	}
	
	public static interface Controller extends IListViewItem
	{
		public void onItemSelected(int aPositions);
	}
}