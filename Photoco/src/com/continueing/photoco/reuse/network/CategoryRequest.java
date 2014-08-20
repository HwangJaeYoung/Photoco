package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import com.loopj.android.http.RequestParams;

import android.content.Context;

public class CategoryRequest {
	private Context context;
	private static final String URL_BASE = "/categories";
	
	public CategoryRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void getCategory(final HttpRequester.NetworkResponseListener aNetworkListener)  throws JSONException {
		RequestParams requestParams = new RequestParams( );
		HttpRequester.get(URL_BASE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}