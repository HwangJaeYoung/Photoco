package com.continueing.photoco.ui.menu.myphoto_page.StaggeredGridView;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.loopj.android.image.SmartImageView;

public class ViewForStaggeredGridViewListViewItem extends AbstractViewForListViewItem{
	private TextView tv_category;
	private TextView tv_size;
	private TextView tv_price;
	private CustomSmartImageView sivImage;
	
	public ViewForStaggeredGridViewListViewItem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		 return inflate(getContext( ), R.layout.item_myphoto_new, this);
	}

	@Override
	protected void initViews() {
		sivImage = (CustomSmartImageView)findViewById(R.id.siv_image);
		tv_category = (TextView)findViewById(R.id.tv_category);
		tv_size = (TextView)findViewById(R.id.tv_size);
		tv_price = (TextView)findViewById(R.id.tv_price);
	}
	
	@Override
	protected void setEvents() {		
		
	}

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IStaggredGridViewListItem iStaggredGridViewListItem = (IStaggredGridViewListItem)aIListViewItem;
		tv_category.setText(iStaggredGridViewListItem.getCategory());
		tv_size.setText(iStaggredGridViewListItem.getSize());
		tv_price.setText(iStaggredGridViewListItem.getPrice());
		sivImage.setImageUrl(iStaggredGridViewListItem.getURL());
	}
	
	public CustomSmartImageView returnView( )
	{
		return sivImage;
	}
	
	public static interface IStaggredGridViewListItem extends IListViewItem
	{
		public String getCategory( );
		public String getSize( );
		public String getPrice( );
		public String getURL( );
	}
}