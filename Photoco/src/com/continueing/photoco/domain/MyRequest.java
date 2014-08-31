package com.continueing.photoco.domain;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItem;

public class MyRequest implements IMyRequestItem {
	private static final String JSON_KEY_USERPROFILE = "userProfile";
	private static final String JSON_KEY_DESCRIPTION = "description";
	private static final String JSON_KEY_IMAGE = "sample_image";
	private static final String JSON_KEY_IMAGE_SET = "urlset";
	private static final String JSON_KEY_TAG = "tags";
	private static final String JSON_KEY_CATEGORY = "category";
	private static final String JSON_KEY_LOCATION = "location";
	private static final String JSON_KEY_LEFTITME = "time_left";
	private static final String JSON_KEY_ENDTIME = "end_date_time";
	private static final String JSON_KEY_REMAIN_MINUTES = "remained_minutes_before_expired";
	
	private JSONObject savedJSONObject; // 합치기 전에 저장할 JSONObject
	private UserProfile userProfile;
	private Category category;
	private Location location;
	private String imageJSONArray;
	private String tagJSONArray; // JSONArray Serializable 문제 때문에 String으로 변환
	private String description;
	private String remainMunutes;
	private String leftTime;
	private String endTime;
	
	public MyRequest() { }
	
	public MyRequest(JSONObject aJsonObject) throws JSONException {
		userProfile = new UserProfile(aJsonObject.getJSONObject(JSON_KEY_USERPROFILE));
		description = aJsonObject.getString(JSON_KEY_DESCRIPTION);
		category = new Category(aJsonObject.getJSONObject(JSON_KEY_CATEGORY));
		location = new Location(aJsonObject.getJSONObject(JSON_KEY_LOCATION));
		leftTime = aJsonObject.getString(JSON_KEY_LEFTITME);
		endTime = aJsonObject.getString(JSON_KEY_ENDTIME);
		remainMunutes = aJsonObject.getString(JSON_KEY_REMAIN_MINUTES);
		tagJSONArray = aJsonObject.getJSONArray(JSON_KEY_TAG).toString();
		imageJSONArray = aJsonObject.getJSONArray(JSON_KEY_IMAGE_SET).toString();
	}
	
	public void setSaveJSONOjbect(JSONObject aJSONObject) {
		savedJSONObject = aJSONObject;
	}
	
	public JSONObject getSavedJSONObject( ) {
		return savedJSONObject;
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
	public ArrayList<Image> getImageURLSet() {
		ArrayList<Image> imageSet = new ArrayList<Image>( );
		
		try {
			JSONArray tempJSONArray = new JSONArray(imageJSONArray);
			
			for(int i = 0; i < tempJSONArray.length(); i++) {
				JSONObject tempJSONObject = tempJSONArray.getJSONObject(i).getJSONObject("image");
				Image imageObject = new Image(tempJSONObject);
				imageSet.add(imageObject);
			}
		} catch (JSONException e) {	
			e.printStackTrace();
		}
		
		return imageSet;
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
