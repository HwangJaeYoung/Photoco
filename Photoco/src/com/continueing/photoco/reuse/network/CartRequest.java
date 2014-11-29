package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class CartRequest {
	private Context context;
	private static final String URL_BASE = "/images";
	private static final String URL_BASE_SECOND = "/me";	
	private static final String PARAM_ADD_TO_CART = "/add_to_cart";
	
	public CartRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void addToCart(String anImageId, final HttpRequester.NetworkResponseListener aNetworkListener)  throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.post(URL_BASE + "/" + anImageId + PARAM_ADD_TO_CART + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
	
	public void getCartItem(final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.get(URL_BASE_SECOND + "/" + "cart" + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}