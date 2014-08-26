package com.continueing.photoco.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile {
	
	private String id;
	private String name;
	private String coin;
	
	public UserProfile(JSONObject aJsonObject) throws JSONException {
		id = aJsonObject.getString("id");
		name =aJsonObject.getJSONObject("basic_info").getString("username");
		coin = aJsonObject.getString("coin");
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
