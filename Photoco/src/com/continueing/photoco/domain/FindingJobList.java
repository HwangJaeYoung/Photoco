package com.continueing.photoco.domain;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem;

public class FindingJobList implements ViewForFindingJobListViewItem.IFindingJobListItem, Serializable{
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_USERNAME = "username";
	private static final String JSON_KEY_DESCRIPTION = "description";
	private static final String JSON_KEY_TAG = "tags";
	private static final String JSON_KEY_CATEGORY = "category";
	private static final String JSON_KEY_LOCATION = "location";
	private static final String JSON_KEY_LEFTITME = "time_left";
	private static final String JSON_KEY_ENDTIME = "end_date_time";
	private static final String JSON_KEY_IMAGEURL = "sample_image";
	private static final String JSON_KEY_REMAIN_MINUTES = "remained_minutes_before_expired";
	private static final String JSON_KEY_DISTANCE = "remained_minutes_before_expired";
	
	private String id;
	private String userName;
	private String description;
	private String imageURL;
	private String leftTime;
	private String endTime;
	private String locations;
	private String categorys;
	private String tags;
	private String remainMunutes;
	private String distance;
	
	public FindingJobList(JSONObject aJsonObject) throws JSONException
	{
		id = aJsonObject.getString(JSON_KEY_ID);
		userName = aJsonObject.getString(JSON_KEY_USERNAME);
		description = aJsonObject.getString(JSON_KEY_DESCRIPTION);
		leftTime = aJsonObject.getString(JSON_KEY_LEFTITME);
		endTime = aJsonObject.getString(JSON_KEY_ENDTIME);
		imageURL= aJsonObject.getString(JSON_KEY_IMAGEURL);
		tags = aJsonObject.getJSONArray(JSON_KEY_TAG).toString();
		locations = aJsonObject.getJSONObject(JSON_KEY_LOCATION).toString();
		categorys = aJsonObject.getJSONObject(JSON_KEY_CATEGORY).toString();
		remainMunutes = aJsonObject.getString(JSON_KEY_REMAIN_MINUTES);
		distance = aJsonObject.getString(JSON_KEY_DISTANCE);
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
		
		try {
			JSONArray tempArray = new JSONArray(tags);
			for(int i = 0; i < 9; i++) {
				JSONObject temp = tempArray.getJSONObject(i);
				if(temp != null)
					tag[i] = temp.getString("name");
				else
					tag[i] = null;
			}
		}
		catch( JSONException e) {
			e.printStackTrace();
		}
		return tag;
	}

	@Override
	public String getCategory() {
		String category = null;
		try {
			JSONObject temp = new JSONObject(categorys);
			category = temp.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public String getLocation() {
		String location = null;
		try {
			JSONObject temp = new JSONObject(locations);
			location = temp.getString("description");
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


	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getRemainMinutes() {
		return remainMunutes;
	}

	@Override
	public String getDistance() {
		return distance;
	}
}