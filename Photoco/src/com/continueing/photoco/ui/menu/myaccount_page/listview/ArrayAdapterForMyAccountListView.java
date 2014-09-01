package com.continueing.photoco.ui.menu.myaccount_page.listview;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.myaccount_page.listview.ViewForMyAccountListViewitem.IMyAccountItem;

public class ArrayAdapterForMyAccountListView extends AbstractArrayAdapter<IMyAccountItem>{

	public ArrayAdapterForMyAccountListView(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForMyAccountListViewitem(getContext());
	}
}