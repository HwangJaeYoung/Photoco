package com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.gridview;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IImageURL;

public class ArrayAdapterForMyRequestDetailActivity extends AbstractArrayAdapter<IImageURL>{

	public ArrayAdapterForMyRequestDetailActivity(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForArrayAdapterForMyRequestDetailActivity(getContext( ));
	}
}