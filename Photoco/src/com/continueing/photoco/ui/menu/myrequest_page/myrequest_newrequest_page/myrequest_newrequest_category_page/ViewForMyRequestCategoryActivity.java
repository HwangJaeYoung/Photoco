package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page.listview.ArrayAdapterForMyNewRequestCategory;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page.listview.ViewForMyNewRequestCategoryListViewItem.IMyRequestCategoryItem;

public class ViewForMyRequestCategoryActivity extends AbstractViewForActivity {

	private Controller controller;
	private ListView lv_requestNewCategory;
	private ArrayAdapterForMyNewRequestCategory arrayAdapterForMyNewRequestCategory;
	
	public ViewForMyRequestCategoryActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_request_new_category, null);	
	}

	@Override
	protected void initViews() {
		lv_requestNewCategory = (ListView)findViewById(R.id.lv_request_new_category);
		arrayAdapterForMyNewRequestCategory = new ArrayAdapterForMyNewRequestCategory(getContext( ), 0);
		lv_requestNewCategory.setAdapter(arrayAdapterForMyNewRequestCategory);
	}

	@Override
	protected void setEvent() {
		lv_requestNewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				controller.onCategorySelected(position);
			}
		});
	}

	public void resetCategory(ArrayList<IMyRequestCategoryItem> aCategorys)
	{
		arrayAdapterForMyNewRequestCategory.addAll(aCategorys);
	}
	
	public static interface Controller
	{
		public void onCategorySelected(int aPosition);
	}
}
