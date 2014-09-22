package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import com.loopj.android.http.RequestParams;

import android.content.Context;

public class UsersRequest {
	
	private Context context;
	private static final String URL_BASE = "/users";
	private static final String PARM_UPDATE = "update";
	private static final String PARM_NAME = "username";
	private static final String PARM_EMAIL = "email";
	private static final String PARM_PASSWORD = "password";
	private static final String PARM_CONFIRMPASSWORD = "password_confirmation";
	private static final String PARM_PRIMARYKEY = "location_id";
	
	public UsersRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void login(String aUserName, String aPassword, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARM_NAME, aUserName);
		requestParams.put(PARM_PASSWORD, aPassword);
		HttpRequester.post(URL_BASE + "/login/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
	
	public void signUp(String aUserName, String aEmail, String aPassword, String aConfirmPassword, String aPrimaryKey, final HttpRequester.NetworkResponseListener aNetworkListener)  throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARM_NAME, aUserName);
		requestParams.put(PARM_EMAIL, aEmail);
		requestParams.put(PARM_PASSWORD, aPassword);
		requestParams.put(PARM_CONFIRMPASSWORD, aConfirmPassword);
		requestParams.put(PARM_PRIMARYKEY, aPrimaryKey);
		HttpRequester.post(URL_BASE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
	
	public void updateUserInformation(String userId, String aUserName, String anEmail, String aPassword, String aConfirmPassword, String aLocationId, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARM_NAME, aUserName);
		requestParams.put(PARM_EMAIL, anEmail);
		requestParams.put(PARM_PASSWORD, aPassword);
		requestParams.put(PARM_CONFIRMPASSWORD, aConfirmPassword);
		requestParams.put(PARM_PRIMARYKEY, aLocationId);
		HttpRequester.post(URL_BASE + "/" + userId + "/" + PARM_UPDATE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}