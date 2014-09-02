package com.continueing.photoco.ui.menu.marketplace_page.marketplace_detail_page;

import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.reuse.network.CartRequest;
import com.continueing.photoco.reuse.network.HttpRequester;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

public class MarketplaceDetailActivity extends ActionBarActivity implements ViewForMarketplaceDetailActivity.Controller{
	private ViewForMarketplaceDetailActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForMarketplaceDetailActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
		view.initViewInfos(getIntent());
	}

	@Override
	public void addToCart(String anImageId) {
		CartRequest cartRequest = new CartRequest(getApplicationContext());
		
		try {
			cartRequest.addToCart(anImageId, addToCartListener);
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