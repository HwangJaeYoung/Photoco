package com.continueing.photoco.domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile implements Serializable {
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_BASIC_INFO = "basic_info";
	private static final String JSON_KEY_NAME = "username";
	private static final String JSON_KEY_COIN = "coin";
	private static final String JSON_KEY_LOCATION = "location";
	
	private String id; // 유저의 PrimaryKey
	private String name; // 유저의 이름
	private String coin; // 유저의 보유액
	private Location location; // Location 객체
	
	public UserProfile(JSONObject aJsonObject) throws JSONException {
		id = aJsonObject.getString(JSON_KEY_ID);
		name = aJsonObject.getJSONObject(JSON_KEY_BASIC_INFO).getString(JSON_KEY_NAME);
		coin = aJsonObject.getString(JSON_KEY_COIN);
		location = new Location(aJsonObject.getJSONObject(JSON_KEY_LOCATION));
	}
	
	public String getId( ) {
		return id;
	}
	
	public String getUserName( ) {
		return name;
	}
	
	public String getCoin( ) {
		return coin;
	}
	
	public Location getLocation( ) {
		return location;
	}
}