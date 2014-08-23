package com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import com.continueing.photoco.domain.URL;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.RequestsRequest;
import com.continueing.photoco.ui.menu.myrequest_page.MyRequestFragment;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.myrequest_detail_page.MyRequestDetailActivity;

public class MyRequestGridViewDetailActivity extends ActionBarActivity implements ViewForMyRequestGridViewDetailActivity.Controller{

	private ViewForMyRequestGridViewDetailActivity view;
	private ArrayList<URL> urlSet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForMyRequestGridViewDetailActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		urlSet = new ArrayList<URL>( );
		setContentView(view.getRoot());
		searchImageURLFromServer(getIntent( ).getStringExtra(MyRequestFragment.PARAM_REQUESTID_KEY));
	}
	
	// 내가 요청한 것들에서 다른 사용자가 등록한 이미지를 가져오기 위한 통신
	public void searchImageURLFromServer(String aRequestId) {
		view.gridviewOff();
		view.progressOn();
		RequestsRequest requestsRequest = new RequestsRequest(getApplicationContext( ));
		try {
			requestsRequest.getImageURL(getImageURLListener, aRequestId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener getImageURLListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;		
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
				URL urls = new URL(jsonArray);
				urlSet = urls.getURLSet();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			view.progresOff();
			view.gridviewOn();
			view.addRequestImages(urlSet);
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};

	@Override
	public void selectedGridViewItem() {
		Intent intent = new Intent(getApplicationContext(), MyRequestDetailActivity.class);
		startActivity(intent);
	}
}