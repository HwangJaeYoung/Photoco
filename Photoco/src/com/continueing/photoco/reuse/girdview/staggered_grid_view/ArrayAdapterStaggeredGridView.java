package com.continueing.photoco.reuse.girdview.staggered_grid_view;

import android.content.Context;

import com.continueing.photoco.reuse.girdview.staggered_grid_view.ViewForStaggeredGridViewListViewItem.IStaggredGridViewListItem;
import com.continueing.photoco.reuse.listview.mvc.AbstarctArrayImageAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;

public class ArrayAdapterStaggeredGridView extends AbstarctArrayImageAdapter<IStaggredGridViewListItem> {

	public ArrayAdapterStaggeredGridView(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForStaggeredGridViewListViewItem(getContext( ));
	}
}