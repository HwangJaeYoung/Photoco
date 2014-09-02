package com.continueing.photoco.ui.menu.cart_page.cart_detail_page;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.domain.Cart;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.PurchaseRequest;
import com.continueing.photoco.ui.menu.cart_page.CartFragment;

public class CartDetailActivity extends ActionBarActivity implements ViewForCartDetailActivity.Controller {
	
	ViewForCartDetailActivity view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForCartDetailActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
		view.initViewInfos(getIntent());
	}
	
	@Override
	public void removeCartItem() {
		// 통신을 해서 해당 아이템을 삭제한다.
		setResult(Activity.RESULT_OK);
		finish( );
	}

	@Override
	public void buyCartItem() {
		Cart item = (Cart)getIntent().getSerializableExtra(CartFragment.PARAM_CART_DETAIL_ITEM_KEY);
		PurchaseRequest purchaseRequest = new PurchaseRequest(getApplicationContext());
		
		try {
			purchaseRequest.purchaseItemFormCart(item.getId(), executePurchaseItemListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	HttpRequester.NetworkResponseListener executePurchaseItemListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			setResult(Activity.RESULT_OK);
			finish( );
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
}