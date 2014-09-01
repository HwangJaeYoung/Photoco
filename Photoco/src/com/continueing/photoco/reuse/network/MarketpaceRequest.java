package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class MarketpaceRequest {
	private Context context;
	private static final String URL_BASE = "/me";
	private static final String PARAM_MARKETPLACE = "/marketplace";
	private static final String PARAM_TABNAME = "sortBy";
	
	public MarketpaceRequest(Context aContext) {
		this.context = aContext;
	}

	public void getMarketplaceImageItems(String aTabName, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARAM_TABNAME, aTabName);
		HttpRequester.get(URL_BASE + PARAM_MARKETPLACE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}