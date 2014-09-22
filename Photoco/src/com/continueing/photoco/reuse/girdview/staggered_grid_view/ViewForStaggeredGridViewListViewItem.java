package com.continueing.photoco.reuse.girdview.staggered_grid_view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.continueing.photoco.reuse.widget.CustomSmartImageView;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IImageURL;

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
		IImageURL iImageURL = (IImageURL)aIListViewItem;
		tv_category.setText(iImageURL.getCategory().getName());
		tv_size.setText(iImageURL.getSize());
		tv_price.setText(iImageURL.getPrice() + "P");
		csivImage.setImageUrl(iImageURL.getStaggeredUrl());
	}
	
	public CustomSmartImageView returnSmartImageView( ) {
		return csivImage;
	}
}