package com.continueing.photoco.domain;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem;

public class FindingJobList implements ViewForFindingJobListViewItem.IFindingJobListItem, Serializable{
	private static final String JSON_KEY_USERPROFILE = "userProfile";
	private static final String JSON_KEY_DESCRIPTION = "description";
	private static final String JSON_KEY_IMAGE = "sample_image";
	private static final String JSON_KEY_TAG = "tags";
	private static final String JSON_KEY_CATEGORY = "category";
	private static final String JSON_KEY_LOCATION = "location";
	private static final String JSON_KEY_LEFTITME = "time_left";
	private static final String JSON_KEY_ENDTIME = "end_date_time";
	private static final String JSON_KEY_REMAIN_MINUTES = "remained_minutes_before_expired";
	
	private String jobId;
	private UserProfile userProfile;
	private String description;
	private Image image;
	private String tags;
	private Category category;
	private Location location;
	private String remainMunutes;
	private String leftTime;
	private String endTime;

	public FindingJobList(JSONObject aJsonObject, String aJobId) throws JSONException {
		jobId = aJobId;
		userProfile = new UserProfile(aJsonObject.getJSONObject(JSON_KEY_USERPROFILE));
		description = aJsonObject.getString(JSON_KEY_DESCRIPTION);
		image = new Image(aJsonObject.getJSONObject(JSON_KEY_IMAGE));
		tags = aJsonObject.getJSONArray(JSON_KEY_TAG).toString();
		category = new Category(aJsonObject.getJSONObject(JSON_KEY_CATEGORY));
		location = new Location(aJsonObject.getJSONObject(JSON_KEY_LOCATION));
		leftTime = aJsonObject.getString(JSON_KEY_LEFTITME);
		endTime = aJsonObject.getString(JSON_KEY_ENDTIME);
		remainMunutes = aJsonObject.getString(JSON_KEY_REMAIN_MINUTES);
	}

	@Override
	public String getName() {
		return userProfile.getUserName();
	}

	@Override
	public String getDescription() {
		return description;
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
		return category.getName();
	}
	
	@Override
	public String getLocation() {
		return location.getDescription();
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
	public String getRemainMinutes() {
		return remainMunutes;
	}
	
	@Override
	public String getImageURL() {
		return image.getUrl();
	}
	
	@Override
	public String getId() {
		return null;
	}

	@Override
	public String getDistance() {
		return null;
	}

	@Override
	public String getJobId() {
		return jobId;
	}
}