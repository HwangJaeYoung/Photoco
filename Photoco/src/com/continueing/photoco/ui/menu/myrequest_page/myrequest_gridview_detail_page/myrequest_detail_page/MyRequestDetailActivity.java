package com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.myrequest_detail_page;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.continueing.photoco.reuse.network.CartRequest;
import com.continueing.photoco.reuse.network.HttpRequester;

public class MyRequestDetailActivity extends Activity implements ViewForMyRequestDetailActivity.Controller{
	private ViewForMyRequestDetailActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForMyRequestDetailActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		setContentView(view.getRoot());
		view.initViewInfos(getIntent());
	}

	@Override
	public void addToCart(String anId) {
		CartRequest cartRequest = new CartRequest(getApplicationContext());
		
		try {
			cartRequest.addToCart(anId, addToCartListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener addToCartListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			setResult(Activity.RESULT_OK);
			finish( ); // 이미지를 카트에 추가했으므로 액티비티를 종료한다.
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
}
