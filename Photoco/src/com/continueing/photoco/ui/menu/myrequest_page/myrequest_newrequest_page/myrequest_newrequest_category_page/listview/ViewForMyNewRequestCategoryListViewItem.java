package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page.listview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForMyNewRequestCategoryListViewItem extends AbstractViewForListViewItem {

	private TextView tv_requestNewCategory;
	public ViewForMyNewRequestCategoryListViewItem(Context context) {
		super(context);
		
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_myrequest_category, this);
	}

	@Override
	protected void initViews() {
		tv_requestNewCategory = (TextView)findViewById(R.id.tv_request_new_category);
	}

	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IMyRequestCategoryItem iMyRequestCategoryItem = (IMyRequestCategoryItem)aIListViewItem;
		tv_requestNewCategory.setText(iMyRequestCategoryItem.getName());
	}
	
	public static interface IMyRequestCategoryItem extends IListViewItem
	{
		public String getId( );
		public String getName( );
	}
}