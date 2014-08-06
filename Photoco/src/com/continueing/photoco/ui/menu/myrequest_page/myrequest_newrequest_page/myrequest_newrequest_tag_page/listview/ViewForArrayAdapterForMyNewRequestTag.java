package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.girdview.tag_gridview.ViewForArrayAdapterForTagGridView.IMyRequestTagItem;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForArrayAdapterForMyNewRequestTag extends AbstractViewForListViewItem {

	private ImageView iv_myrequestTagCancel;
	private TextView tv_myrequestTag;
	public static boolean isDeleteButtonClicked = false;
	
	public ViewForArrayAdapterForMyNewRequestTag(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_myrequest_new_tag, this);
	}

	@Override
	protected void initViews() {
		iv_myrequestTagCancel = (ImageView)findViewById(R.id.iv_myrequest_tag_cancel);
		tv_myrequestTag = (TextView)findViewById(R.id.tv_myrequest_tag);
	}

	@Override
	protected void setEvents() {
		iv_myrequestTagCancel.setOnTouchListener(new OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
					isDeleteButtonClicked = true;
					return false;
			}
		});
	}

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IMyRequestTagItem iMyRequestTagItem = (IMyRequestTagItem)aIListViewItem;
		tv_myrequestTag.setText(iMyRequestTagItem.getTagText());
	}
}