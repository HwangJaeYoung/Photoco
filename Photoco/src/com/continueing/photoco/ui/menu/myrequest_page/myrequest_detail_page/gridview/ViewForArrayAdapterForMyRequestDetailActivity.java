package com.continueing.photoco.ui.menu.myrequest_page.myrequest_detail_page.gridview;

import android.content.Context;
import android.view.View;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.loopj.android.image.SmartImageView;

public class ViewForArrayAdapterForMyRequestDetailActivity extends AbstractViewForListViewItem{
	
	private SmartImageView siv_myrequestDetailImage;
	
	public ViewForArrayAdapterForMyRequestDetailActivity(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_myrequest_detail, this);
	}

	@Override
	protected void initViews() {
		siv_myrequestDetailImage = (SmartImageView)findViewById(R.id.siv_myrequest_detail_image);		
	}

	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IMyRequestDetailItem iMyRequestDetailItem = (IMyRequestDetailItem)aIListViewItem;
		siv_myrequestDetailImage.setImageUrl(iMyRequestDetailItem.getImageURL());
	}
	
	public static interface IMyRequestDetailItem extends IListViewItem{
		public String getImageURL( );
	}
}
