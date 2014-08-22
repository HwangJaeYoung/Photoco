package com.continueing.photoco.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class URL {
	
	private String URL;
	
	public URL(JSONObject aJsonObject) throws JSONException {
		URL = aJsonObject.getString("url");
	}
	
	public String getImageURL( ) {
		return URL;
	}
}