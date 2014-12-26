package com.continueing.photoco.reuse.network;

import java.io.File;
import java.io.FileNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class RequestsRequest {
	private Context context;
	private static final String URL_BASE = "/requests";
	private static final String PARAM_LOCATION_ID = "location_id";
	private static final String PARAM_CATEGORY_ID = "category_id";
	private static final String PARAM_DURATION_HOUR = "duration_hour";
	private static final String PARAM_TAG_NAMES = "tag_names";
	private static final String PARAM_DESCRIPTION = "description";
	private static final String PARAM_IMAGE = "image";
	
	public RequestsRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void setMyRequestItem(String aLocationId, String aCategoryId, String aDurationHour, JSONArray aTag, String aDescription, File aFile, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARAM_LOCATION_ID, aLocationId);
		requestParams.put(PARAM_CATEGORY_ID, aCategoryId);
		requestParams.put(PARAM_DURATION_HOUR, aDurationHour);
		requestParams.put(PARAM_TAG_NAMES, aTag);
		requestParams.put(PARAM_DESCRIPTION, aDescription);
		try {
			requestParams.put(PARAM_IMAGE, aFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(context != null)
			HttpRequester.post(URL_BASE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
	
	public void getImageURL(final HttpRequester.NetworkResponseListener aNetworkListener, String aRequestId) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.get(URL_BASE + "/" + aRequestId + "/attachments/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}
