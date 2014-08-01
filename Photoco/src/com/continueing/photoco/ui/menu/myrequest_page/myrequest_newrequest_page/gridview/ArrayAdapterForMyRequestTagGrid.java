package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.gridview;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class ArrayAdapterForMyRequestTagGrid extends AbstractArrayAdapter<IMyRequestTagItem> {

	public ArrayAdapterForMyRequestTagGrid(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForArrayAdapterForMyNewRequestGrid(getContext( ));
	}
}