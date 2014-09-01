package com.continueing.photoco.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class BuyerSeller {
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_BASICINFO = "basic_info";
	private static final String JSON_KEY_USERNAME = "username";
	private static final String JSON_KEY_COIN = "coin";
	
	private String id;
	private String coin;
	private String userName;
	
	public BuyerSeller(JSONObject aJsonObject) throws JSONException {
		id = aJsonObject.getString(JSON_KEY_ID);
		coin = aJsonObject.getString(JSON_KEY_COIN);
		userName = aJsonObject.getJSONObject(JSON_KEY_BASICINFO).getString(JSON_KEY_USERNAME);
	}
	
	public String getId( ) {
		return id;
	}
	
	public String getCoint( ) {
		return coin;
	}
	
	public String getUserName( ) {
		return userName;
	}
}