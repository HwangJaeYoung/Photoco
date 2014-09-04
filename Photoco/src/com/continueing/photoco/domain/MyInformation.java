package com.continueing.photoco.domain;

import org.json.JSONException;
import org.json.JSONObject;

// 드로워 박스에서 로그인한 사용자의 정보를 보여줄 때 사용하는 도멘인
public class MyInformation {
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_LOCATION = "location";
	private static final String JSON_KEY_BASIC_INFO = "basic_info";
	private static final String JSON_KEY_USERNAME = "username";
	private static final String JSON_KEY_EMAIL = "email";
	private static final String JSON_KEY_COIN = "coin";
	private static final String JSON_KEY_NUMBER_OF_JOBS = "number_of_jobs";
	private static final String JSON_KEY_NUMBER_OF_REQUESTS = "number_of_requests";
	
	private String id;
	private Location location;
	private String userName;
	private String eMail;
	private String coin;
	private String numberOfJobs;
	private String numberOfRequests;
	
	public MyInformation(JSONObject aJsonObject) throws JSONException {
		id = aJsonObject.getString(JSON_KEY_ID);
		location = new Location(aJsonObject.getJSONObject(JSON_KEY_LOCATION));
		userName = aJsonObject.getJSONObject(JSON_KEY_BASIC_INFO).getString(JSON_KEY_USERNAME);
		eMail = aJsonObject.getJSONObject(JSON_KEY_BASIC_INFO).getString(JSON_KEY_EMAIL);
		coin = aJsonObject.getString(JSON_KEY_COIN);
		numberOfJobs = aJsonObject.getString(JSON_KEY_NUMBER_OF_JOBS);
		numberOfRequests = aJsonObject.getString(JSON_KEY_NUMBER_OF_REQUESTS);
	}

	public String getId() {
		return id;
	}

	public Location getLocation() {
		return location;
	}

	public String getUserName() {
		return userName;
	}

	public String geteMail() {
		return eMail;
	}

	public String getCoin() {
		return coin;
	}

	public String getNumberOfJobs() {
		return numberOfJobs;
	}

	public String getNumberOfRequests() {
		return numberOfRequests;
	}
}