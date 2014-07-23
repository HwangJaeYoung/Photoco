package com.continueing.photoco.ui.menu.cart_page.listview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForCartListViewItem extends AbstractViewForListViewItem {
	
	private TextView tvListCartGroup;
	private ICartItem iCartItem;
	
	public ViewForCartListViewItem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_list_cart, this);
	}

	@Override
	protected void initViews() {
		tvListCartGroup = (TextView)findViewById(R.id.tv_list_cart_group);
	}

	@Override
	protected void setEvents() {
		
	
	}

	@Override
	protected void setData(IListViewItem aIListViewItem) { 
		iCartItem = (ICartItem) aIListViewItem;
		tvListCartGroup.setText(iCartItem.getTitle());
	}
	
	public static interface ICartItem extends IListViewItem {
		public String getTitle( );
	}
}