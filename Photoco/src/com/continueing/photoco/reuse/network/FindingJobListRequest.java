package com.continueing.photoco.reuse.network;

import java.io.File;
import java.io.FileNotFoundException;

import org.json.JSONException;

import android.content.Context;

import com.loopj.android.http.RequestParams;

public class FindingJobListRequest {
	private Context context;
	private static final String URL_BASE = "/me";
	private static final String PARAM_TABNAME = "sortBy";
	private static final String PARAM_IMAGE = "image";
	
	public FindingJobListRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void getFindingJobItem(String aTabName, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARAM_TABNAME, aTabName);
		HttpRequester.get(URL_BASE + "/candidateJobs/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
	
	public void getJobListItem(String aTabName, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		requestParams.put(PARAM_TABNAME, aTabName);
		HttpRequester.get(URL_BASE + "/jobs/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
	
	public void submitPhoto(File aFilePath, String aJobId, final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		try {
			requestParams.put(PARAM_IMAGE, aFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HttpRequester.post(URL_BASE + "/jobs/" + aJobId + "/", requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}