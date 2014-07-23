package com.continueing.photoco.ui.menu.cart_page.cart_detail_page;

import com.continueing.photoco.ui.menu.cart_page.CartFragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class CartDetailActivity extends ActionBarActivity implements ViewForCartDetailActivity.Controller {
	
	ViewForCartDetailActivity view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new ViewForCartDetailActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
	}
	
	@Override
	public void removeCartItem() {
		int position = getIntent( ).getIntExtra(CartFragment.PARAM_SELECTED_POSITION, 0);
		// 통신을 해서 해당 아이템을 삭제한다.
		Intent intent = new Intent( );
		intent.putExtra(CartFragment.PARAM_SELECTED_POSITION, position);
		setResult(Activity.RESULT_OK, intent);
		finish( );
	}
}