package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForMyNewRequestDurationListViewItem extends AbstractViewForListViewItem{

	TextView tv_requestNewDuration;
	public ViewForMyNewRequestDurationListViewItem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_myrequest_duration, this);
	}

	@Override
	protected void initViews() {
		tv_requestNewDuration = (TextView)findViewById(R.id.tv_request_new_duration);		
	}

	@Override
	protected void setEvents() {
	
		
	}

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IMyRequestDurationItem iMyRequestDurationItem = (IMyRequestDurationItem)aIListViewItem;
		tv_requestNewDuration.setText(iMyRequestDurationItem.getHourText());		
	}
	
	public static interface IMyRequestDurationItem extends IListViewItem
	{
		public int getHour( );
		public String getHourText( );
		public String getEndDate( );
	}
}
