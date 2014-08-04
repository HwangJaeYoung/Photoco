package com.continueing.photoco.reuse.girdview.staggered_grid_view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.continueing.photoco.reuse.widget.CustomSmartImageView;

public class ViewForStaggeredGridViewListViewItem extends AbstractViewForListViewItem{
	private TextView tv_category;
	private TextView tv_size;
	private TextView tv_price;
	private CustomSmartImageView csivImage;
	
	public ViewForStaggeredGridViewListViewItem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		 return inflate(getContext( ), R.layout.item_myphoto, this);
	}

	@Override
	protected void initViews() {
		csivImage = (CustomSmartImageView)findViewById(R.id.csiv_image);
		tv_category = (TextView)findViewById(R.id.tv_category);
		tv_size = (TextView)findViewById(R.id.tv_size);
		tv_price = (TextView)findViewById(R.id.tv_price);
	}
	
	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IStaggredGridViewListItem iStaggredGridViewListItem = (IStaggredGridViewListItem)aIListViewItem;
		tv_category.setText(iStaggredGridViewListItem.getCategory());
		tv_size.setText(iStaggredGridViewListItem.getSize());
		tv_price.setText(iStaggredGridViewListItem.getPrice());
		csivImage.setImageUrl(iStaggredGridViewListItem.getURL());
	}
	
	public CustomSmartImageView returnSmartImageView( )
	{
		return csivImage;
	}
	
	public static interface IStaggredGridViewListItem extends IListViewItem
	{
		public String getCategory( );
		public String getSize( );
		public String getPrice( );
		public String getURL( );
	}
}