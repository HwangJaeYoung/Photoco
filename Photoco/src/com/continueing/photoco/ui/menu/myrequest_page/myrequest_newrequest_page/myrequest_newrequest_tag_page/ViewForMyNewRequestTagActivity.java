package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ArrayAdapterForMyRequestTag;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class ViewForMyNewRequestTagActivity extends AbstractViewForActivity{
	
	private Controller controller;
	private ArrayAdapterForMyRequestTag arrayAdapterForMyRequestTag;
	private ListView lv_requestNewTag;
	private ImageButton ib_myrequesteNewAddTag;
	
	
	public ViewForMyNewRequestTagActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_request_new_tag, null);
	}

	@Override
	protected void initViews() {
		arrayAdapterForMyRequestTag = new ArrayAdapterForMyRequestTag(getContext( ), 0);
		lv_requestNewTag = (ListView)findViewById(R.id.lv_request_new_tag);
		lv_requestNewTag.setAdapter(arrayAdapterForMyRequestTag);
		ib_myrequesteNewAddTag =(ImageButton)findViewById(R.id.ib_myrequest_new_add_tag);
	}

	@Override
	protected void setEvent() {
		ib_myrequesteNewAddTag.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				controller.addTagItem();
				
			}
		});
	}
	
	public void addItem(IMyRequestTagItem item)
	{
		arrayAdapterForMyRequestTag.add(item);
	}
	
	public static interface Controller
	{
		public void addTagItem( );
	}

}
