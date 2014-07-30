package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview.ViewForMyNewRequestDurationListViewItem.IMyRequestDurationItem;

public class ArrayAdapterForMyRequestDuration extends AbstractArrayAdapter<IMyRequestDurationItem>{

	public ArrayAdapterForMyRequestDuration(Context context, int resource) {
		super(context, resource);
	}
 
	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForMyNewRequestDurationListViewItem(getContext( ));
	}
}