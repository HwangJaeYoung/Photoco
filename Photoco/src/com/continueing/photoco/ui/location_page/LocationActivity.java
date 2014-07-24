package com.continueing.photoco.ui.location_page;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;

import com.continueing.photoco.domain.Location;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.LocationRequest;
import com.continueing.photoco.ui.location_page.listview.ViewForLocationListViewItem.ILocationItem;

public class LocationActivity extends ActionBarActivity implements ViewForLocationActivity.Controller {
	private ViewForLocationActivity view;
	private ArrayList<ILocationItem> locations;
	public static String PARAM_LOCATION_ACTIVITY_KEY ="location";
	public static final String PARAM_PRIMARY_KEY = "primaryKey";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForLocationActivity(getApplicationContext(), this);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
		searchLocationFromServer(""); // 아무것도 없을시 모든 지역을 리스트뷰에 보여준다.
	}

	// 글자가 입력될 때 마다 통신을 하여 일치하는 정보를 가지고 온다.
	@Override
	public void onSearchStringDelivered(String aSearchString) {
		searchLocationFromServer(aSearchString);
	}

	// 서버와 통신을 하기 위해서 구성한 메소드
	public void searchLocationFromServer(String aSearchString) {
		LocationRequest searchLocation = new LocationRequest(getApplicationContext());
		try {
			searchLocation.searchLocation(aSearchString, searchLocationListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	HttpRequester.NetworkResponseListener searchLocationListener = new HttpRequester.NetworkResponseListener() {
		// Location객체를 만들고 리스트 뷰에 주기위해 합쳐놓는 과정
		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;

			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			locations = new ArrayList<ILocationItem>();

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonLocationObject = null;
				
				try {
					jsonLocationObject = jsonArray.getJSONObject(i);
					Location location = new Location(jsonLocationObject);
					locations.add(location);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			view.resetLocations(locations);
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};

	// 지역을 선택하였을 때 AccountActivity로 넘겨주기 위한 구성
	@Override
	public void onLocationSelected(int aPosition) {
		ILocationItem item = locations.get(aPosition);
		Intent intent = new Intent( );
		intent.putExtra(PARAM_LOCATION_ACTIVITY_KEY, item.getDescription());
		intent.putExtra(PARAM_PRIMARY_KEY, item.getId());
		setResult(Activity.RESULT_OK, intent);
		finish( );	
	}
}