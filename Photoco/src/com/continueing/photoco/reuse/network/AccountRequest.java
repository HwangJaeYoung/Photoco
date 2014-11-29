package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class AccountRequest {
	private Context context;
	private static final String URL_BASE = "/me";
	private static final String PARAM_TABNAME = "sortBy";
	private static final String PARAM_PURCHASES = "/purchases";
	
	public AccountRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void getPurchaseItems(String aTabName, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARAM_TABNAME, aTabName);
		
		if(context != null)
			HttpRequester.get(URL_BASE + PARAM_PURCHASES + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}
