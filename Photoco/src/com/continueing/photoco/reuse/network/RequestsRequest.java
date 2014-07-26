package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;
import android.graphics.Bitmap;

import com.loopj.android.http.RequestParams;

public class RequestsRequest {
	private Context context;
	private static String URL_BASE = "/requests";
	private static String PARAM_LOCATION_ID = "location_id";
	private static String PARAM_CATEGORY_ID = "category_id";
	private static String PARAM_DURATION_HOUR = "duration_hour";
	private static String PARAM_TAG_NAMES = "tag_names";
	private static String PARAM_DESCRIPTION = "description";
	private static String PARAM_IMAGE = "image";
	
	public RequestsRequest(Context aContext)
	{
		this.context = aContext;
	}
	
	public void setMyRequestItem(String aLocationId, String aCategoryId, String aDurationHour, String[] aTag, String aDescription, Bitmap aBitmap, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException
	{
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARAM_LOCATION_ID, aLocationId);
		requestParams.put(PARAM_CATEGORY_ID, aCategoryId);
		requestParams.put(PARAM_DURATION_HOUR, aDurationHour);
		requestParams.put(PARAM_TAG_NAMES, aTag);
		requestParams.put(PARAM_DESCRIPTION, aDescription);
		requestParams.put(PARAM_IMAGE, "asdf");
		HttpRequester.post(URL_BASE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}
