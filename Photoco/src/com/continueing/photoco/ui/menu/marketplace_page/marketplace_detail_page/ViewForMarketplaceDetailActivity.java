package com.continueing.photoco.ui.menu.marketplace_page.marketplace_detail_page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Image;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.marketplace_page.MarketplaceFragment;
import com.loopj.android.image.SmartImageView;

public class ViewForMarketplaceDetailActivity extends AbstractViewForActivity{

	private Controller controller;
	private SmartImageView siv_marketplaceDetailImage;
	private TextView tv_marketplace_photoDetailPrice;
	private Button bt_marketplacePhotoDetailInfo;
	private Button bt_marketplaceAddToCart;
	private TextView tv_marketplacePhotoDetailSize;
	private TextView tv_marketplacePhotoDetailLocation;
	private String imageId;
	
	public ViewForMarketplaceDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_marketplace_detail, null);
	}

	@Override
	protected void initViews() {
		siv_marketplaceDetailImage = (SmartImageView)findViewById(R.id.siv_myrequest_detail_image);
		tv_marketplace_photoDetailPrice = (TextView)findViewById(R.id.tv_marketplace_photo_detail_price);
		bt_marketplacePhotoDetailInfo = (Button)findViewById(R.id.bt_marketplace_photo_detail_info);
		tv_marketplacePhotoDetailSize = (TextView)findViewById(R.id.tv_marketplace_photo_detail_size);
		bt_marketplaceAddToCart = (Button)findViewById(R.id.bt_marketplace_add_to_cart);
		tv_marketplacePhotoDetailLocation = (TextView)findViewById(R.id.tv_marketplace_photo_detail_location);
	}

	@Override
	protected void setEvent() {
		bt_marketplaceAddToCart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.addToCart(imageId);
			}
		});
	}
	
	public void initViewInfos(Intent anIntent) {
		Image item = (Image)anIntent.getSerializableExtra(MarketplaceFragment.PARAM_MARKETPLACE_IMAGE_ITEM);
		
		imageId = item.getId(); // AddToCart때 사용하는 이미지의 id이다.
		bt_marketplacePhotoDetailInfo.setText(item.getSize());
		tv_marketplace_photoDetailPrice.setText(item.getPrice());
		tv_marketplacePhotoDetailSize.setText(item.getWidth() + " X " + item.getHeight());
		siv_marketplaceDetailImage.setImageUrl(item.getUrl());
		tv_marketplacePhotoDetailLocation.setText(item.getUserProfile().getLocation().getDescription());
	}
	
	public static interface Controller {
		public void addToCart(String anImageId);
	}
}