package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import com.loopj.android.http.RequestParams;

import android.content.Context;

public class UsersRequest {
	
	private Context context;
	private static String URL_BASE = "/users";
	private static String PARM_NAME = "username";
	private static String PARM_PASSWORD = "password";
	private static String PARM_EMAIL = "email";
	private static String PARM_CONFIRMPASSWORD = "password_confirmation";
	private static String PARM_PRIMARYKEY = "location_id";
	
	public UsersRequest(Context aContext)
	{
		this.context = aContext;
	}
	
	public void login(String aUserName, String aPassword, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException
	{
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARM_NAME, aUserName);
		requestParams.put(PARM_PASSWORD, aPassword);
		HttpRequester.post(URL_BASE + "/login/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
	
	public void signUp(String aUserName, String aEmail, String aPassword, String aConfirmPassword, String aPrimaryKey, final HttpRequester.NetworkResponseListener aNetworkListener)  throws JSONException
	{
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARM_NAME, aUserName);
		requestParams.put(PARM_EMAIL, aEmail);
		requestParams.put(PARM_PASSWORD, aPassword);
		requestParams.put(PARM_CONFIRMPASSWORD, aConfirmPassword);
		requestParams.put(PARM_PRIMARYKEY, aPrimaryKey);
		HttpRequester.post(URL_BASE + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}