package com.continueing.photoco.reuse.network;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class FindingJobListRequest {
	private Context context;
	private static final String URL_BASE = "/me";
	private static final String PARM_TABNAME= "sortBy";
	
	public FindingJobListRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void getFindingJobItem(String aTabName, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARM_TABNAME, aTabName);
		HttpRequester.get(URL_BASE + "/candidateJobs/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
	
	public void getJobListItem(String aTabName, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARM_TABNAME, aTabName);
		HttpRequester.get(URL_BASE + "/jobs/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}