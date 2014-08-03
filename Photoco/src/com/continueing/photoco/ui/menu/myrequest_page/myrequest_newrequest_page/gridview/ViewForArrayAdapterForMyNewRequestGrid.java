package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.gridview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class ViewForArrayAdapterForMyNewRequestGrid extends AbstractViewForListViewItem {
	
	private TextView tv_requestTagItem;
	public ViewForArrayAdapterForMyNewRequestGrid(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_myrequest_new_gridview_tag, this);
	}

	@Override
	protected void initViews() {
		tv_requestTagItem = (TextView)findViewById(R.id.tv_request_tag_item);	
	}

	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IMyRequestTagItem iMyRequestTagItem = (IMyRequestTagItem)aIListViewItem;
		tv_requestTagItem.setText(iMyRequestTagItem.getTagText());
	}
}