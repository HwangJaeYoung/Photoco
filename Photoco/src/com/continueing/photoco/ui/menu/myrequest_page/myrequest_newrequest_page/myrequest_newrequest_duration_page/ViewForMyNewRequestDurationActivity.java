package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview.ArrayAdapterForMyRequestDuration;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview.ViewForMyNewRequestDurationListViewItem.IMyRequestDurationItem;

public class ViewForMyNewRequestDurationActivity extends AbstractViewForActivity {

	private Controller controller;
	private ListView lv_requestNewDuration;
	private ArrayAdapterForMyRequestDuration arrayAdapterForMyRequestDuration;


	public ViewForMyNewRequestDurationActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_myrequest_new_duration, null);
	}

	@Override
	protected void initViews() {
		lv_requestNewDuration = (ListView)findViewById(R.id.lv_request_new_duration);
		arrayAdapterForMyRequestDuration = new ArrayAdapterForMyRequestDuration(getContext( ), 0);
		lv_requestNewDuration.setAdapter(arrayAdapterForMyRequestDuration);
	}

	@Override
	protected void setEvent() {
		lv_requestNewDuration.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				controller.onDurationSelected(position);
			}
		});
	}

	public void resetDuration(ArrayList<IMyRequestDurationItem> aDurations)
	{
		arrayAdapterForMyRequestDuration.addAll(aDurations);
	}
	
	public static interface Controller
	{
		public void onDurationSelected(int aPosition);
	}
}
