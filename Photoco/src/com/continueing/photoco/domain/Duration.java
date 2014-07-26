package com.continueing.photoco.domain;

import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview.ViewForMyNewRequestDurationListViewItem.IMyRequestDurationItem;

public class Duration implements IMyRequestDurationItem{

	public static final String JSON_KEY_HOUR = "hours";
	public static final String JSON_KEY_HOUR_TEXT = "text";
	public static final String JSON_KEY_END_DATE = "end_date";
	
	
	private int hour;
	private String hourText;
	private String endDate;
	
	public Duration(JSONObject aJsonObject) throws JSONException
	{
		hour = aJsonObject.getInt(JSON_KEY_HOUR);
		hourText = aJsonObject.getString(JSON_KEY_HOUR_TEXT);
		endDate = aJsonObject.getString(JSON_KEY_END_DATE);
	}

	@Override
	public int getHour() {
		return hour;
	}

	@Override
	public String getHourText() {
		return hourText;
	}

	@Override
	public String getEndDate() {
		return endDate;
	}
}