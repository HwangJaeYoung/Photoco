package com.continueing.photoco.ui.menu.myrequest_page.listview;

import java.util.ArrayList;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class ViewForMyRequestListViewItem extends AbstractViewForListViewItem {

	public static boolean isDeleteButtonClicked = false;
	
	private Controller controller;
	private TextView tv_requestName;
	private Button bt_listTagFirst;
	private Button bt_listTagSecond;
	private Button bt_listTagThird;
	private ImageView iv_requestRemove;
	private ArrayList<IMyRequestTagItem> arrayList;
	
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
		bt_listTagFirst = (Button)findViewById(R.id.bt_list_tag_first);
		bt_listTagSecond = (Button)findViewById(R.id.bt_list_tag_second);
		bt_listTagThird = (Button)findViewById(R.id.bt_list_tag_third);
		
		bt_listTagFirst.setFocusable(false);
		bt_listTagSecond.setFocusable(false);
		bt_listTagThird.setFocusable(false);
		
		iv_requestRemove = (ImageView)findViewById(R.id.iv_request_remove);
	}

	@Override
	protected void setEvents() {
		iv_requestRemove.setOnTouchListener(new View.OnTouchListener() {	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				isDeleteButtonClicked = true;
				return false;
			}
		});
	}

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