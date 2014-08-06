package com.continueing.photoco.reuse.girdview.tag_gridview;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ArrayAdapterForTagGridView extends AbstractArrayAdapter<IListViewItem> {

	public ArrayAdapterForTagGridView(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForArrayAdapterForTagGridView(getContext( ));
	}
}