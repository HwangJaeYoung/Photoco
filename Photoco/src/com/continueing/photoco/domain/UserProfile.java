package com.continueing.photoco.domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile implements Serializable {
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_BASIC_INFO = "basic_info";
	private static final String JSON_KEY_NAME = "username";
	private static final String JSON_KEY_COIN = "coin";
	
	private String id;
	private String name;
	private String coin;
	
	public UserProfile(JSONObject aJsonObject) throws JSONException {
		id = aJsonObject.getString(JSON_KEY_ID);
		name = aJsonObject.getJSONObject(JSON_KEY_BASIC_INFO).getString(JSON_KEY_NAME);
		coin = aJsonObject.getString(JSON_KEY_COIN);
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
}