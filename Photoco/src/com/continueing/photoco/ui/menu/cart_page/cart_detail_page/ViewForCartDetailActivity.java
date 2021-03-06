package com.continueing.photoco.ui.menu.cart_page.cart_detail_page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Cart;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.cart_page.CartFragment;
import com.loopj.android.image.SmartImageView;

public class ViewForCartDetailActivity extends AbstractViewForActivity{

	private Controller controller;
	private ImageButton ib_cartDetailRemove;
	private SmartImageView siv_cartDetailPhoto;
	private Button bt_cart_detail_info;
	private TextView tv_cart_detail_price;
	private TextView tv_cart_detail_size;
	private TextView tv_cart_detail_location;
	private Button bt_cart_detail_buy;
	
	public ViewForCartDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_cart_detail, null);
	}

	@Override
	protected void initViews() {
		siv_cartDetailPhoto = (SmartImageView)findViewById(R.id.siv_cart_detail_photo);
		bt_cart_detail_info = (Button)findViewById(R.id.bt_cart_detail_info);
		tv_cart_detail_price = (TextView)findViewById(R.id.tv_cart_detail_price);
		tv_cart_detail_size = (TextView)findViewById(R.id.tv_cart_detail_size);
		tv_cart_detail_location = (TextView)findViewById(R.id.tv_cart_detail_location);
		ib_cartDetailRemove = (ImageButton)findViewById(R.id.ib_cart_detail_remove);
		bt_cart_detail_buy= (Button)findViewById(R.id.bt_cart_detail_buy);
		tv_cart_detail_location = (TextView)findViewById(R.id.tv_cart_detail_location);
	}

	@Override
	protected void setEvent() {
		// 삭제 버튼을 눌렸을 때 해당 Cart의 아이템을 삭제한다.
		ib_cartDetailRemove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.removeCartItem();				
			}
		});
		
		// 구매 버튼을 눌렸을 때 해당 Cart의 아이템을 하나 구매한다.
		bt_cart_detail_buy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.buyCartItem();
			}
		});
	}
	
	public void initViewInfos(Intent anIntent) {
		Cart item = (Cart)anIntent.getSerializableExtra(CartFragment.PARAM_CART_DETAIL_ITEM_KEY);
		
		siv_cartDetailPhoto.setImageUrl(item.getImage().getUrl());
		bt_cart_detail_info.setText(item.getImage().getSize());
		tv_cart_detail_price.setText(item.getImage().getPrice());
		tv_cart_detail_size.setText(item.getImage().getWidth() + " X " + item.getImage().getHeight());
		tv_cart_detail_location.setText(item.getImage().getUserProfile().getLocation().getDescription());
	}
	
	public static interface Controller {
		public void removeCartItem( );
		public void buyCartItem( );		
	}
}
