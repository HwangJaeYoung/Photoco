package com.continueing.photoco.ui.location_page.listview;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.location_page.listview.ViewForLocationListViewItem.ILocationItem;

public class ArrayAdapterForLocationListView extends AbstractArrayAdapter<ILocationItem>{

	public ArrayAdapterForLocationListView(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForLocationListViewItem(getContext( ));
	}
}