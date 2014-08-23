package com.continueing.photoco.domain;

import java.io.Serializable;
import java.util.ArrayList;

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
	
	private String jobId;
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
	
	public FindingJobList(JSONObject aJsonObject, String aJobId) throws JSONException {
		jobId = aJobId;
		id = aJsonObject.getString(JSON_KEY_ID);
		userName = aJsonObject.getString(JSON_KEY_USERNAME);
		description = aJsonObject.getString(JSON_KEY_DESCRIPTION);
		leftTime = aJsonObject.getString(JSON_KEY_LEFTITME);
		endTime = aJsonObject.getString(JSON_KEY_ENDTIME);
		imageURL= aJsonObject.getJSONObject(JSON_KEY_IMAGEURL).toString();
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
		try {
			JSONObject temp = new JSONObject(imageURL);
			imageURL = temp.getString("url");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return imageURL;
	}

	@Override
	public ArrayList<Tag> getTag() {		
		Tag tagSet = null;
		try {
			JSONArray tempArray = new JSONArray(tags);
			tagSet = new Tag(tempArray);	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tagSet.getTagSet();
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

	@Override
	public String getJobId() {
		return jobId;
	}
}