package com.continueing.photoco.domain;

import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.reuse.page.location_page.listview.ViewForLocationListViewItem;


public class Location implements ViewForLocationListViewItem.ILocationItem{
	public static final String JSON_KEY_ID = "id";
	public static final String JSON_KEY_DESCRIPTION = "description";
	
	private String id;
	private String description;
	
	public Location(JSONObject aJsonObject) throws JSONException
	{
		id = aJsonObject.getString(JSON_KEY_ID);
		description = aJsonObject.getString(JSON_KEY_DESCRIPTION);
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getId() {
		return id;
	}
}