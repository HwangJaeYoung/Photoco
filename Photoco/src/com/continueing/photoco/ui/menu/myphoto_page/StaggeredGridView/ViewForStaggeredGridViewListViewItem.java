package com.continueing.photoco.ui.menu.myphoto_page.StaggeredGridView;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
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
	private SmartImageView sivImage;
	
	public ViewForStaggeredGridViewListViewItem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_myphoto_new, this);
	}

	@Override
	protected void initViews() {
		sivImage = (SmartImageView)findViewById(R.id.siv_image);
		tv_category = (TextView)findViewById(R.id.tv_category);
		tv_size = (TextView)findViewById(R.id.tv_size);
		tv_price = (TextView)findViewById(R.id.tv_price);
	}

	@Override
	protected void setEvents() {		
		this.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getContext(), "a", Toast.LENGTH_SHORT).show();
				return false;
			}
		});	
	}

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IStaggredGridViewListItem iStaggredGridViewListItem = (IStaggredGridViewListItem)aIListViewItem;
		tv_category.setText(iStaggredGridViewListItem.getCategory());
		tv_size.setText(iStaggredGridViewListItem.getSize());
		tv_price.setText(iStaggredGridViewListItem.getPrice());
		sivImage.setImageUrl(iStaggredGridViewListItem.getURL());
	}
	
	public static interface IStaggredGridViewListItem extends IListViewItem
	{
		public String getCategory( );
		public String getSize( );
		public String getPrice( );
		public String getURL( );
	}
}