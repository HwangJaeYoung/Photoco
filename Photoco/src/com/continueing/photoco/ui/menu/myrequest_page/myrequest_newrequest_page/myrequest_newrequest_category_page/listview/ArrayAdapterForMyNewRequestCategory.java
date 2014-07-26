package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page.listview;


import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page.listview.ViewForMyNewRequestCategoryListViewItem.IMyRequestCategoryItem;

public class ArrayAdapterForMyNewRequestCategory extends AbstractArrayAdapter<IMyRequestCategoryItem>{

	public ArrayAdapterForMyNewRequestCategory(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForMyNewRequestCategoryListViewItem(getContext( ));
	}
}
