package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class MyPhotoRequest {
	private Context context;
	private static final String URL_BASE = "/me";
	private static final String PARAM_IMAGES = "/images";
	private static final String PARAM_UPLOADED = "/uploaded";
	private static final String PARAM_BOUGHT = "/bought";
	
	public MyPhotoRequest(Context aContext) {
		this.context = aContext;
	}

	public void getMyPhotoUploadedImageItems(final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.get(URL_BASE + PARAM_IMAGES + PARAM_UPLOADED + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
	
	public void getMyPhotoBoughtImageItems(final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.get(URL_BASE + PARAM_IMAGES + PARAM_BOUGHT + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}