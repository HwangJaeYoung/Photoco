package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForArrayAdapterForMyNewRequestTag extends AbstractViewForListViewItem {

	private Button bt_myrequestTagCancel;
	private EditText et_myrequestTag;
	private IMyRequestTagItem items;
	private Controller controller;
	private int position;
	
	public ViewForArrayAdapterForMyNewRequestTag(Context context, Controller aController, int position) {
		super(context);
		controller = aController;
		this.position = position;
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_myrequest_tag, this);
	}

	@Override
	protected void initViews() {
		bt_myrequestTagCancel = (Button)findViewById(R.id.bt_myrequest_tag_cancel);
		et_myrequestTag = (EditText)findViewById(R.id.et_myrequest_tag);
	}

	@Override
	protected void setEvents() {
		bt_myrequestTagCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.removeTag(position);
			}
		});	
	}

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IMyRequestTagItem iMyRequestTagItem = (IMyRequestTagItem)aIListViewItem;
		et_myrequestTag.setText(iMyRequestTagItem.getTagText());
	}
	
	public void removeSetPosition(int aposition)
	{
		position = aposition;
	}
	
	public void removeSetItem( IMyRequestTagItem item)
	{
		items = item;
	}
	
	
	public static interface Controller
	{
		public void removeTag(int position);
	}
	
	public static interface IMyRequestTagItem extends IListViewItem
	{
		public String getTagText();
		public void setTagText(String aTagText);
	}
}