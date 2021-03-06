package com.continueing.photoco.reuse.network;

import java.io.File;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class SubmitPhotoRequest {
	private Context context;
	private static final String URL_BASE = "/me";
	
	public SubmitPhotoRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void submitPhoto(File aFilePath, String aJobId, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.get(URL_BASE + "/candidateJobs/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}
