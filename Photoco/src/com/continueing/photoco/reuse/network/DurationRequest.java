package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class DurationRequest {
	private Context context;
	private static String URL_BASE = "/durations";
	
	public DurationRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void getDuration(final HttpRequester.NetworkResponseListener aNetworkListener)  throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.get(URL_BASE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}
