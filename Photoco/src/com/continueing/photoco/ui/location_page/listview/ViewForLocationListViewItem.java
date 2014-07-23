package com.continueing.photoco.ui.location_page.listview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForLocationListViewItem extends AbstractViewForListViewItem {
	
	private TextView tv_location;
	private ILocationItem iLocationItem;
	
	public ViewForLocationListViewItem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_location, this);
	}

	@Override
	protected void initViews() {
		tv_location = (TextView)findViewById_(R.id.tv_item_location);
	}

	@Override
	protected void setEvents() { }

	// 리스트뷰에 보여줄 데이터를 채움
	@Override
	protected void setData(IListViewItem aIListViewItem) {
		iLocationItem = (ILocationItem)aIListViewItem;
		tv_location.setText(iLocationItem.getDescription( )); 
	}
	
	public static interface ILocationItem extends IListViewItem {
		public String getDescription( );
		public String getId( );
	}
}