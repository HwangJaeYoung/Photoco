package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview.ArrayAdapterForMyRequestDuration;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview.ViewForMyNewRequestDurationListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview.ViewForMyNewRequestDurationListViewItem.IMyRequestDurationItem;

public class ViewForMyNewRequestDurationActivity extends AbstractViewForActivity {

	private Controller controller;
	private ListView lv_requestNewDuration;
	private int itemLength;
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
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ViewForMyNewRequestDurationListViewItem viewItem = null;
				for(int i = 0; i < itemLength; i++)
				{
					viewItem = (ViewForMyNewRequestDurationListViewItem)lv_requestNewDuration.getChildAt(i);
					ImageView checkImage = (ImageView)viewItem.findViewById(R.id.iv_request_new_duration_twelve);
					checkImage.setVisibility(View.INVISIBLE);
				}
				viewItem = (ViewForMyNewRequestDurationListViewItem)lv_requestNewDuration.getChildAt(position);
				ImageView checkImage = (ImageView)viewItem.findViewById(R.id.iv_request_new_duration_twelve);
				checkImage.setVisibility(View.VISIBLE);
				
				controller.onDurationSelected(position);
			}
		});
	}
	
	public void checkedDuration(int aCheckedPosition)
	{
		ViewForMyNewRequestDurationListViewItem viewItem = (ViewForMyNewRequestDurationListViewItem)lv_requestNewDuration.getChildAt(aCheckedPosition);
		ImageView checkImage = (ImageView)viewItem.findViewById(R.id.iv_request_new_duration_twelve);
		checkImage.setVisibility(View.VISIBLE);
	}

	public void resetDuration(ArrayList<IMyRequestDurationItem> aDurations)
	{
		itemLength = aDurations.size();
		arrayAdapterForMyRequestDuration.addAll(aDurations);
	}
	
	public static interface Controller
	{
		public void onDurationSelected(int aPosition);
	}
}
