package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class LocationRequest {
	private Context context;
	private static String URL_BASE = "/location";
	private static String PARAM_LOCATION = "str";
	
	public LocationRequest(Context aContext)
	{
		this.context = aContext;
	}
	
	public void searchLocation(String aLocation, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException
	{
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARAM_LOCATION, aLocation); // ?str=aLocation
		HttpRequester.get(URL_BASE, requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}