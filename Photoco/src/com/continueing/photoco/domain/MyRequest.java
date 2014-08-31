package com.continueing.photoco.domain;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItem;

public class MyRequest implements IMyRequestItem {
	private static final String JSON_KEY_USERNAME = "username";
	private static final String JSON_KEY_DESCRIPTION = "description";
	private static final String JSON_KEY_TAG = "tags";
	private static final String JSON_KEY_CATEGORY = "category";
	private static final String JSON_KEY_LOCATION = "location";
	private static final String JSON_KEY_LEFTITME = "time_left";
	private static final String JSON_KEY_ENDTIME = "end_date_time";
	private static final String JSON_KEY_IMAGEURL_SET = "urlset";
	private static final String JSON_KEY_SAMPLEIMAGE_URL = "sample_image";
	
	private JSONObject savedJSONObject;
	private String userName;
	private String description;
	private JSONArray tags;
	private String leftTime;
	private String endTime;
	private JSONObject location;
	private JSONObject category;
	private JSONArray imageURLSet;
	private JSONObject sampleObject;
	
	public MyRequest() { }
	
	public MyRequest(JSONObject aJsonObject) throws JSONException {
		userName = aJsonObject.getString(JSON_KEY_USERNAME);
		description = aJsonObject.getString(JSON_KEY_DESCRIPTION);
		tags = aJsonObject.getJSONArray(JSON_KEY_TAG);
		leftTime = aJsonObject.getString(JSON_KEY_LEFTITME);
		endTime = aJsonObject.getString(JSON_KEY_ENDTIME);
		location = aJsonObject.getJSONObject(JSON_KEY_LOCATION);
		category = aJsonObject.getJSONObject(JSON_KEY_CATEGORY);
		imageURLSet = aJsonObject.getJSONArray(JSON_KEY_IMAGEURL_SET);
		sampleObject = aJsonObject.getJSONObject(JSON_KEY_SAMPLEIMAGE_URL);
	}
	
	public void setSaveJSONOjbect(JSONObject aJSONObject) {
		savedJSONObject = aJSONObject;
	}
	
	public JSONObject getSavedJSONObject( ) {
		return savedJSONObject;
	}
	
	public ArrayList<URL> getImageURLSet( ) {
		URL url = new URL(imageURLSet);
		return url.getURLSet();
	}
	
	public URL getSampleImageURL( ) {
		URL sampleImageURL = null;
		try {
			sampleImageURL = new URL(sampleObject.getString("url"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return sampleImageURL;
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
	public ArrayList<Tag> getTag() {
		return null;
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
