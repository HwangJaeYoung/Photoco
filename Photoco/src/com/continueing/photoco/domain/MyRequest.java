package com.continueing.photoco.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItem;

public class MyRequest implements IMyRequestItem{
	private static final String JSON_KEY_USERNAME = "username";
	private static final String JSON_KEY_DESCRIPTION = "description";
	private static final String JSON_KEY_TAG = "tags";
	private static final String JSON_KEY_CATEGORY = "category";
	private static final String JSON_KEY_LOCATION = "location";
	private static final String JSON_KEY_LEFTITME = "time_left";
	private static final String JSON_KEY_ENDTIME = "end_date_time";
	private static final String JSON_KEY_IMAGEURL = "sample_image";
	
	private String userName;
	private String description;
	private JSONArray tags;
	private String imageURL;
	private String leftTime;
	private String endTime;
	private JSONObject location;
	private JSONObject category;
	
	public MyRequest(JSONObject aJsonObject) throws JSONException
	{
		userName = aJsonObject.getString(JSON_KEY_USERNAME);
		description = aJsonObject.getString(JSON_KEY_DESCRIPTION);
		tags = aJsonObject.getJSONArray(JSON_KEY_TAG);
		leftTime = aJsonObject.getString(JSON_KEY_LEFTITME);
		endTime = aJsonObject.getString(JSON_KEY_ENDTIME);
		location = aJsonObject.getJSONObject(JSON_KEY_LOCATION);
		imageURL= aJsonObject.getString(JSON_KEY_IMAGEURL);
		category = aJsonObject.getJSONObject(JSON_KEY_CATEGORY);
	}
	
	@Override
	public String getName() {
		return userName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getImageURL() {
		return imageURL;
	}

	@Override
	public String[] getTag() {
		
		String tag1 = null;
		String tag2 = null;
		String tag3 = null;
		
		try {
			tag1 = (tags.getJSONObject(0)).getString("name");
			tag2 = (tags.getJSONObject(1)).getString("name");
			tag3 = (tags.getJSONObject(2)).getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		String[] temp = new String[3];
		temp[0] = tag1; temp[1] = tag2; temp[2] = tag3;
		return temp;
	}

	@Override
	public String getCategory() {
		String category = null;
		try {
			category = this.category.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public String getLocation() {
		String location = null;
		try {
			location = this.location.getString("description");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return location;
	}

	@Override
	public String getLeftTime() {
		return leftTime;
	}

	@Override
	public String getEndDate() {
		return endTime;
	}
}
