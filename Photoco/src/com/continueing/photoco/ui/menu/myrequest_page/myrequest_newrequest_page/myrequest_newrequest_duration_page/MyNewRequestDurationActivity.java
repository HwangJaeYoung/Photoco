package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import com.continueing.photoco.domain.Duration;
import com.continueing.photoco.reuse.network.DurationRequest;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.MyNewRequestActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.listview.ViewForArrayAdapterForMyRequesttDuration.IMyRequestDurationItem;

public class MyNewRequestDurationActivity extends ActionBarActivity implements ViewForMyNewRequestDurationActivity.Controller{
	private ViewForMyNewRequestDurationActivity view;
	private ArrayList<IMyRequestDurationItem> durations;
	private Handler mHandler;
	private Runnable mRunnable;
	
	public static final String PARAM_HOUR_KEY ="hours";
	public static final String PARAM_HOUR_TEXT_KEY = "hourtext";
	public static final String PARAM_END_DATE_KEY = "enddate";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		view = new ViewForMyNewRequestDurationActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		setContentView(view.getRoot());
		searchDurationFromServer( );
	}

	public void searchDurationFromServer( )
	{
		DurationRequest categoryRequest = new DurationRequest(getApplicationContext());
		try {
			categoryRequest.getDuration(durationListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener durationListener = new HttpRequester.NetworkResponseListener( ){

		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			durations = new ArrayList<IMyRequestDurationItem>();
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonDurationObject = null;
				
				try {
					jsonDurationObject = jsonArray.getJSONObject(i);
					
					Duration duration = new Duration(jsonDurationObject);
					durations.add(duration);
				} catch (JSONException e) {
					e.printStackTrace();
				}				
			}		
			view.resetDuration(durations);

			mRunnable = new Runnable() {
				@Override
				public void run() {
					view.checkedDuration(getIntent().getIntExtra(MyNewRequestActivity.PARAM_DURATION_CHECKED_KEY, 0));
				}
			};

			mHandler = new Handler();
			mHandler.postDelayed(mRunnable, 500);
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};	
	
	@Override
	public void onDurationSelected(int aPosition) {
		IMyRequestDurationItem item = durations.get(aPosition);
		Intent intent = new Intent( );
		intent.putExtra(PARAM_HOUR_KEY, item.getHour());
		intent.putExtra(PARAM_HOUR_TEXT_KEY, item.getHourText());
		intent.putExtra(PARAM_END_DATE_KEY, item.getEndDate());
		intent.putExtra(MyNewRequestActivity.PARAM_DURATION_CHECKED_KEY, aPosition);
		setResult(Activity.RESULT_OK, intent);
		finish( );	
	}
}
