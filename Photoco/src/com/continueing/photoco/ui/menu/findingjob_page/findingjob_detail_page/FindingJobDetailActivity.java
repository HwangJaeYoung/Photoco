package com.continueing.photoco.ui.menu.findingjob_page.findingjob_detail_page;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.ParticipateRequest;

public class FindingJobDetailActivity extends ActionBarActivity implements ViewForFindingJobDetailActivity.Controller {	
	private ViewForFindingJobDetailActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForFindingJobDetailActivity(getApplicationContext( ), this);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
		view.initViewInfos(getIntent( ));
	}

	@Override
	public void onParticipate(String aRequestId) {
		submitFindingJobItemInfoToServer(aRequestId); // Participate할 때 사용하는 Request의 PrimaryKey이다.
	}
	
	public void submitFindingJobItemInfoToServer(String aRequestId) {
		ParticipateRequest participateRequest = new ParticipateRequest(getApplicationContext());
		try {
			participateRequest.submitFindingJobInfo(submitFindingJobListener, aRequestId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener submitFindingJobListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) { 
			setResult(Activity.RESULT_OK); // Partticipate하였을 경우에 그냥 끄면 아무것도 안한다.
			finish( );
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
}