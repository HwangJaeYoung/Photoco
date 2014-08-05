package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class FindingJobRequest {
	private Context context;
	private static String URL_BASE = "/me";
	private static String PARM_TABNAME= "sortBy";
	
	public FindingJobRequest(Context aContext)
	{
		this.context = aContext;
	}
	
	public void getFindingJobItem(String aTabName, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException
	{
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARM_TABNAME, aTabName);
		HttpRequester.get(URL_BASE + "/candidatedJobs/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}