package com.continueing.photoco.domain;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IImageURL;

public class Image implements IImageURL, Serializable{
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_URL = "url";
	private static final String JSON_KEY_HEIGHT = "height";
	private static final String JSON_KEY_WIDTH = "width";
	private static final String JSON_KEY_PRICE = "price";
	private static final String JSON_KEY_SIZE = "size";
	private static final String JSON_KEY_MIDDLE_IMAGE_URL = "middle_image_url";
	private static final String JSON_KEY_THUMNAIL_IMAGE = "thumbnail_url";
	private static final String JSON_KEY_STAGGERED_IMAGE = "staggered_image_url";
	
	private static final String JSON_KEY_USERPROFILE = "userProfile";
	private static final String JSON_KEY_CATEGORY = "category";
	private static final String JSON_KEY_TAG = "tags";
	
	private String id;
	private String height;
	private String width;
	private String price;
	private String size;
	private String url;
	private String thumbnailUrl;
	private String middleUrl;
	private String staggeredUrl;
	
	private String tagJSONArray;
	private Category category;
	private UserProfile userProfile;
	private ArrayList<Tag> tagSet;
	
	public Image(JSONObject aJsonObject) throws JSONException {
		id = aJsonObject.getString(JSON_KEY_ID);
		height = aJsonObject.getString(JSON_KEY_HEIGHT);
		width = aJsonObject.getString(JSON_KEY_WIDTH);
		price = aJsonObject.getString(JSON_KEY_PRICE);
		size = aJsonObject.getString(JSON_KEY_SIZE);
		
		url = aJsonObject.getString(JSON_KEY_URL);
		thumbnailUrl = aJsonObject.getString(JSON_KEY_THUMNAIL_IMAGE);
		middleUrl = aJsonObject.getString(JSON_KEY_MIDDLE_IMAGE_URL);
		staggeredUrl = aJsonObject.getString(JSON_KEY_STAGGERED_IMAGE);
		userProfile = new UserProfile(aJsonObject.getJSONObject(JSON_KEY_USERPROFILE));
		category = new Category(aJsonObject.getJSONObject(JSON_KEY_CATEGORY));
		tagJSONArray = aJsonObject.getJSONArray(JSON_KEY_TAG).toString();
	}
	
	@Override
	public String getId( ) {
		return id;
	}

	@Override
	public String getHeight() {
		return height;
	}

	@Override
	public String getWidth() {
		return width;
	}

	@Override
	public String getPrice() {
		return price;
	}

	@Override
	public String getSize() {
		return size;
	}
	
	@Override
	public UserProfile getUserProfile( ) {
		return userProfile;
	}
	
	@Override
	public Category getCategory( ) {
		return category;
	}
	
	@Override
	public String getUrl() {
		return url;
	}
	
	@Override
	public String getThumnailUrl( ) {
		return thumbnailUrl;
	}
	
	@Override
	public String getMiddleUrl( ) {
		return middleUrl;
	}
	
	@Override
	public String getStaggeredUrl( ) {
		return staggeredUrl;
	}
	
	@Override
	public ArrayList<Tag> getTag() {

		tagSet = new ArrayList<Tag>( );
		
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