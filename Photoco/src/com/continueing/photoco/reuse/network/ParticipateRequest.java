package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class ParticipateRequest {
	private Context context;
	private static String URL_BASE = "/requests";
	
	public ParticipateRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void submitFindingJobInfo(final HttpRequester.NetworkResponseListener aNetworkListener, String aRequestId)  throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.post(URL_BASE + "/" + aRequestId + "/participate/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}
