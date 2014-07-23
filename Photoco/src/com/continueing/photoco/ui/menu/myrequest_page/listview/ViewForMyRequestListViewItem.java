package com.continueing.photoco.ui.menu.myrequest_page.listview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForMyRequestListViewItem extends AbstractViewForListViewItem {
	
	private Controller controller;
	private TextView tv_requestName;
	private int index;
	
	public ViewForMyRequestListViewItem(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_request, this);
	}

	@Override
	protected void initViews() {
		tv_requestName = (TextView)findViewById(R.id.tv_request_name);
	}

	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		// 데이터를 삽입한다.
		IMyRequestItem iMyRequestItem = (IMyRequestItem)aIListViewItem;
		tv_requestName.setText(iMyRequestItem.getName());
	}
	
	public static interface IMyRequestItem extends IListViewItem
	{
		// 뽑아낼 데이터의 메소드를 정의
		public String getName( );
	}
	 
	public static interface Controller
	{
		public void onRemoveClicked();
	}
}