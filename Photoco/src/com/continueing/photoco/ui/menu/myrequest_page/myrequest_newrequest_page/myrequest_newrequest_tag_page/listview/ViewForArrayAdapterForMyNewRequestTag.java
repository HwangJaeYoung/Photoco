package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForArrayAdapterForMyNewRequestTag extends AbstractViewForListViewItem {

	private ImageView iv_myrequestTagCancel;
	private TextView tv_myrequestTag;
	private Controller controller;
	
	public ViewForArrayAdapterForMyNewRequestTag(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_request_new_tag, this);
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
				// 여기서 리스트뷰의  onitemclicklistener를 촉발시켜야한다는 건데?
				// positino사용?
				Toast.makeText(getContext(), "a", Toast.LENGTH_SHORT).show();
				return false;
			}
		});	
	}

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IMyRequestTagItem iMyRequestTagItem = (IMyRequestTagItem)aIListViewItem;
		tv_myrequestTag.setText(iMyRequestTagItem.getTagText());
	}
	
	// Tag를 삭제 할 때 사용하는 메소드
	public static interface Controller
	{
		public void removeTag(int position);
	}
	
	// Tag의 모델을 정의한다.
	public static interface IMyRequestTagItem extends IListViewItem
	{
		public String getTagText();
		public void setTagText(String aTagText);
	}
}