package com.continueing.photoco.ui.menu.myrequest_page.listview;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItem;

public class ArrayAdapterForMyRequestListView extends AbstractArrayAdapter<IMyRequestItem> implements ViewForMyRequestListViewItem.Controller{
	
	public ArrayAdapterForMyRequestListView(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForMyRequestListViewItem(getContext(), this);
	}

	@Override
	public void onRemoveClicked() {
		// TODO Auto-generated method stub
		
	}

}