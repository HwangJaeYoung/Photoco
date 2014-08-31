package com.continueing.photoco.domain;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem;

public class FindingJobList implements ViewForFindingJobListViewItem.IFindingJobListItem, Serializable {
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_USERPROFILE = "userProfile";
	private static final String JSON_KEY_DESCRIPTION = "description";
	private static final String JSON_KEY_IMAGE = "sample_image";
	private static final String JSON_KEY_TAG = "tags";
	private static final String JSON_KEY_CATEGORY = "category";
	private static final String JSON_KEY_LOCATION = "location";
	private static final String JSON_KEY_LEFTITME = "time_left";
	private static final String JSON_KEY_ENDTIME = "end_date_time";
	private static final String JSON_KEY_REMAIN_MINUTES = "remained_minutes_before_expired";
	
	private UserProfile userProfile;
	private Image image;
	private Category category;
	private Location location;
	private String tagJSONArray; // JSONArray Serializable 문제 때문에 String으로 변환
	private String jobId;
	private String requestID;
	private String description;
	private String remainMunutes;
	private String leftTime;
	private String endTime;
	
	public FindingJobList(JSONObject aJsonObject, String aJobId) throws JSONException {
		jobId = aJobId;
		requestID = aJsonObject.getString(JSON_KEY_ID);
		userProfile = new UserProfile(aJsonObject.getJSONObject(JSON_KEY_USERPROFILE));
		description = aJsonObject.getString(JSON_KEY_DESCRIPTION);
		image = new Image(aJsonObject.getJSONObject(JSON_KEY_IMAGE));
		category = new Category(aJsonObject.getJSONObject(JSON_KEY_CATEGORY));
		location = new Location(aJsonObject.getJSONObject(JSON_KEY_LOCATION));
		leftTime = aJsonObject.getString(JSON_KEY_LEFTITME);
		endTime = aJsonObject.getString(JSON_KEY_ENDTIME);
		remainMunutes = aJsonObject.getString(JSON_KEY_REMAIN_MINUTES);
		tagJSONArray = aJsonObject.getJSONArray(JSON_KEY_TAG).toString();
	}

	@Override
	public UserProfile getName() {
		return userProfile;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Category getCategory() {
		return category;
	}
	
	@Override
	public Location getLocation() {
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
	public String getRemainMinutes() {
		return remainMunutes;
	}
	
	@Override
	public String getId() {
		return requestID;
	}

	@Override
	public String getJobId() {
		return jobId;
	}
	
	@Override
	public Image getImageURL() {
		return image;
	}
	
	@Override
	public ArrayList<Tag> getTag() {
		ArrayList<Tag> tagSet = new ArrayList<Tag>( );
		
		try {
			JSONArray tempJSONArray = new JSONArray(tagJSONArray);
			
			for(int i = 0; i < tempJSONArray.length(); i++) {
				String tag = tempJSONArray.getJSONObject(i).getString("name");
				Tag tagObject = new Tag( );
				tagObject.setTagText(tag);
				tagSet.add(tagObject);				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return tagSet;
	}
}