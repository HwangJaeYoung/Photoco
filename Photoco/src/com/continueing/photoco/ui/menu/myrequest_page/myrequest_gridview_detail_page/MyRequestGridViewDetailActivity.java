package com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page;

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
import android.util.Log;
import android.view.WindowManager;

import com.continueing.photoco.domain.Image;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.RequestsRequest;
import com.continueing.photoco.reuse.page.location_page.LocationActivity;
import com.continueing.photoco.ui.menu.myrequest_page.MyRequestFragment;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.myrequest_detail_page.MyRequestDetailActivity;

public class MyRequestGridViewDetailActivity extends ActionBarActivity implements ViewForMyRequestGridViewDetailActivity.Controller{
	public static final String PARAM_MYREQUEST_DETAIL_ITEM_KEY = "myrequestdetailitem";
	private static final int REQUEST_ADD_TO_CART = 0;
	private ViewForMyRequestGridViewDetailActivity view;
	private ArrayList<Image> imageSet;
	private int clickedImagePosition;
	private String requestId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForMyRequestGridViewDetailActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		imageSet = new ArrayList<Image>( );
		setContentView(view.getRoot());
		requestId = getIntent( ).getStringExtra(MyRequestFragment.PARAM_REQUESTID_KEY);
		searchImageURLFromServer(requestId);
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
				
				for(int i = 0; i < jsonArray.length(); i++) {
					JSONObject tempJSONObject = jsonArray.getJSONObject(i).getJSONObject("image");
					Image imageObject = new Image(tempJSONObject);
					imageSet.add(imageObject);			
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			view.progresOff();
			view.gridviewOn();
			view.addRequestImages(imageSet);
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_ADD_TO_CART) {
			if(resultCode == Activity.RESULT_OK) {
				// AddToCart로 인해서 이미지가 없어졌으므로 GridView를 갱신한다.
				// 통신을 해버리면 더하는 결과가 발생하므로 그냥 갱신만 시킨다.
				imageSet.remove(clickedImagePosition);
				view.addRequestImages(imageSet);
				setResult(Activity.RESULT_OK); // AddToCart를 하였다는 것을 MyRequestFragment에게 알려준다.
			}
		}
	}

	@Override
	public void selectedGridViewItem(int aPosition) {
		clickedImagePosition = aPosition;
		Intent intent = new Intent(getApplicationContext(), MyRequestDetailActivity.class);
		intent.putExtra(PARAM_MYREQUEST_DETAIL_ITEM_KEY, imageSet.get(aPosition));
		startActivityForResult(intent, REQUEST_ADD_TO_CART);
	}
}