package com.continueing.photoco.ui.menu.myphoto_page.staggered_grid_view;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstarctArrayImageAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.myphoto_page.staggered_grid_view.ViewForStaggeredGridViewListViewItem.IStaggredGridViewListItem;

public class ArrayAdapterStaggeredGridView extends AbstarctArrayImageAdapter<IStaggredGridViewListItem> {

	public ArrayAdapterStaggeredGridView(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForStaggeredGridViewListViewItem(getContext( ));
	}
}