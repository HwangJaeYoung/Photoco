package com.continueing.photoco.domain;

import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page.listview.ViewForMyNewRequestCategoryListViewItem;

public class Category implements ViewForMyNewRequestCategoryListViewItem.IMyRequestCategoryItem{
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_NAME = "name";
	
	private String id;
	private String name;
	
	public Category(JSONObject aJsonObject) throws JSONException
	{
		id = aJsonObject.getString(JSON_KEY_ID);
		name =  aJsonObject.getString(JSON_KEY_NAME);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
