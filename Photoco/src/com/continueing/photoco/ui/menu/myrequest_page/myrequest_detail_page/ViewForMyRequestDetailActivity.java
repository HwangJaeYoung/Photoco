package com.continueing.photoco.ui.menu.myrequest_page.myrequest_detail_page;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_detail_page.gridview.ArrayAdapterForMyRequestDetailActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_detail_page.gridview.ViewForArrayAdapterForMyRequestDetailActivity.IMyRequestDetailItem;

public class ViewForMyRequestDetailActivity extends AbstractViewForActivity{

	private Controller controller;
	private GridView gv_requestDetail;
	private ArrayAdapterForMyRequestDetailActivity arrayAdapterForMyRequestDetailActivity;
	
	public ViewForMyRequestDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_request_datail, null);
	}

	@Override
	protected void initViews() {
		gv_requestDetail = (GridView)findViewById(R.id.gv_request_detail);
		arrayAdapterForMyRequestDetailActivity = new ArrayAdapterForMyRequestDetailActivity(getContext(), 0);
		gv_requestDetail.setAdapter(arrayAdapterForMyRequestDetailActivity);
	}

	public void addRequestImages(ArrayList<IMyRequestDetailItem> items)
	{
		arrayAdapterForMyRequestDetailActivity.clear();
		arrayAdapterForMyRequestDetailActivity.addAll(items);
	}
	
	@Override
	protected void setEvent() {
	
		
	}
	
	public static interface Controller
	{
		
	}
}