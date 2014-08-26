package com.continueing.photoco.domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.reuse.page.location_page.listview.ViewForLocationListViewItem;


public class Location implements ViewForLocationListViewItem.ILocationItem, Serializable {
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_DESCRIPTION = "description";
	
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