package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class MyRequestItemRequest {
	private Context context;
	private static String URL_BASE = "/me";
	
	public MyRequestItemRequest(Context aContext)
	{
		this.context = aContext;
	}

	public void getItems(final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException
	{
		RequestParams requestParams = new RequestParams( );
		HttpRequester.get(URL_BASE + "/requests/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}