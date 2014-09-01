package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class PurchaseRequest {
	private Context context;
	private static final String URL_BASE = "/commerce";
	private static final String PARAM_CART = "/cart";
	private static final String PARAM_PURCHASE = "/purchases";
	
	public PurchaseRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void purchaseItemFormCart(String anCartId, final HttpRequester.NetworkResponseListener aNetworkListener)  throws JSONException {
		RequestParams requestParams = new RequestParams( );
		HttpRequester.post(URL_BASE + PARAM_CART + "/" + anCartId + PARAM_PURCHASE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}
