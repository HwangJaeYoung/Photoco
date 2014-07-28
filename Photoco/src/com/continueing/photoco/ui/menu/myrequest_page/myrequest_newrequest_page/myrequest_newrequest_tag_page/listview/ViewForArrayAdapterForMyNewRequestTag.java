package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForArrayAdapterForMyNewRequestTag extends AbstractViewForListViewItem {

	private Button bt_myrequestTagCancel;
	public ViewForArrayAdapterForMyNewRequestTag(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_myrequest_tag, this);
	}

	@Override
	protected void initViews() {
		bt_myrequestTagCancel = (Button)findViewById(R.id.bt_myrequest_tag_cancel);
		
	}

	@Override
	protected void setEvents() {
		bt_myrequestTagCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});	
	}

	@Override
	protected void setData(IListViewItem aIListViewItem) {
	
		
	}
	
	public static interface IMyRequestTagItem extends IListViewItem
	{
		public String getHour( );
		public String getHourText( );
		public String getEndDate( );
	}
}