package com.continueing.photoco.ui.menu.cart_page.listview;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.cart_page.listview.ViewForCartListViewItem.ICartItem;

public class ArrayAdapterForCartListView extends AbstractArrayAdapter<ICartItem>{

	public ArrayAdapterForCartListView(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForCartListViewItem(getContext( ));
	}
}