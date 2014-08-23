package com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.URL;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.reuse.widget.ExpandableHeightGridView;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.gridview.ArrayAdapterForMyRequestDetailActivity;

public class ViewForMyRequestGridViewDetailActivity extends AbstractViewForActivity{

	private Controller controller;
	private ExpandableHeightGridView gv_requestDetail;
	private ArrayAdapterForMyRequestDetailActivity arrayAdapterForMyRequestDetailActivity;
	
	public ViewForMyRequestGridViewDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_myrequest_gridview_datail, null);
	}

	@Override
	protected void initViews() {
		gv_requestDetail = (ExpandableHeightGridView)findViewById(R.id.gv_request_detail);
		gv_requestDetail.setExpanded(true);
		arrayAdapterForMyRequestDetailActivity = new ArrayAdapterForMyRequestDetailActivity(getContext(), 0);
		gv_requestDetail.setAdapter(arrayAdapterForMyRequestDetailActivity);
	}

	public void addRequestImages(ArrayList<URL> items) {
		arrayAdapterForMyRequestDetailActivity.clear();
		arrayAdapterForMyRequestDetailActivity.addAll(items);
	}
	
	@Override
	protected void setEvent() {
		gv_requestDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				controller.selectedGridViewItem();
			}
		});
	}
	
	public static interface Controller
	{
		public void selectedGridViewItem( );
	}
}