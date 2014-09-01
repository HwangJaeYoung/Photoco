package com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.myrequest_detail_page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Image;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.MyRequestGridViewDetailActivity;
import com.loopj.android.image.SmartImageView;

public class ViewForMyRequestDetailActivity extends AbstractViewForActivity {

	private Button bt_requestDetailSizeInfo;
	private Button bt_requestDetailAddToCart;
	private TextView tv_requestDetailPrice;
	private TextView tv_requestDetailImageSize;
	private TextView tv_requestDetailLocation;
	private SmartImageView siv_requestDetailPhoto;
	private Controller controller;
	private String id; // 이미지를 Cart에 담는 통신을 할 때 사용할 이미지의 ID
	
	public ViewForMyRequestDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_myrequest_detail, null);
	}

	@Override
	protected void initViews() {
		bt_requestDetailSizeInfo = (Button)findViewById(R.id.bt_request_detail_size_info);
		bt_requestDetailAddToCart = (Button)findViewById(R.id.bt_request_detail_add_to_cart);
		tv_requestDetailPrice = (TextView)findViewById(R.id.tv_request_detail_price);
		tv_requestDetailImageSize = (TextView)findViewById(R.id.tv_request_detail_image_size);
		tv_requestDetailLocation = (TextView)findViewById(R.id.tv_request_detail_location);
		siv_requestDetailPhoto = (SmartImageView)findViewById(R.id.siv_request_detail_photo);		
	}

	@Override
	protected void setEvent() {
		bt_requestDetailAddToCart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.addToCart(id);				
			}
		});
	}
	
	public void initViewInfos(Intent anIntent) {
		Image item = (Image)anIntent.getSerializableExtra(MyRequestGridViewDetailActivity.PARAM_MYREQUEST_DETAIL_ITEM_KEY);
		
		id = item.getId(); // AddToCart때 사용하는 이미지의 id이다.
		bt_requestDetailSizeInfo.setText(item.getSize());
		tv_requestDetailPrice.setText(item.getPrice());
		tv_requestDetailImageSize.setText(item.getWidth() + " X " + item.getHeight());
		siv_requestDetailPhoto.setImageUrl(item.getUrl());
	}
	
	public static interface Controller
	{
		public void addToCart(String anId);
	}
}