package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class MyInformationRequest {
	private Context context;
	private static final String URL_BASE = "/me";
	
	public MyInformationRequest(Context aContext) {
		this.context = aContext;
	}

	public void getMyInformation(final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.get(URL_BASE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}
