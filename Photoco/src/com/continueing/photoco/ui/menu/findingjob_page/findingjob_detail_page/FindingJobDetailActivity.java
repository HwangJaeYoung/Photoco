package com.continueing.photoco.ui.menu.findingjob_page.findingjob_detail_page;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.domain.FindingJobList;
import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
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
		submitFindingJobItemInfoToServer(aRequestId);
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
			finish( );
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
}