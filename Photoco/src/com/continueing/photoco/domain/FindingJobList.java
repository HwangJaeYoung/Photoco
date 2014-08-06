package com.continueing.photoco.domain;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem;

public class FindingJobList implements ViewForFindingJobListViewItem.IFindingJobListItem, Serializable{
	public static final String JSON_KEY_USERNAME = "username";
	public static final String JSON_KEY_DESCRIPTION = "description";
	public static final String JSON_KEY_TAG = "tags";
	public static final String JSON_KEY_CATEGORY = "category";
	public static final String JSON_KEY_LOCATION = "location";
	public static final String JSON_KEY_LEFTITME = "time_left";
	public static final String JSON_KEY_ENDTIME = "end_date_time";
	public static final String JSON_KEY_IMAGEURL = "sample_image";

	private String userName;
	private String description;
	private JSONArray tags;
	private String imageURL;
	private String leftTime;
	private String endTime;
	private JSONObject location;
	private JSONObject category;
	
	public FindingJobList(JSONObject aJsonObject) throws JSONException
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
		String[] tag = new String[9];
			
		for(int i = 0; i < 9; i++)
		{
			try {
				JSONObject temp = tags.getJSONObject(i);
				if(temp != null)
					tag[i] = temp.getString("name");
			} catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		return tag;
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