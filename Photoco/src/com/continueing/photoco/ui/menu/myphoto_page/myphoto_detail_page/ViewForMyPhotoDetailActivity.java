package com.continueing.photoco.ui.menu.myphoto_page.myphoto_detail_page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Image;
import com.continueing.photoco.reuse.girdview.tag_gridview.ArrayAdapterForTagGridView;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.myphoto_page.MyPhotoFragment;
import com.loopj.android.image.SmartImageView;

public class ViewForMyPhotoDetailActivity extends AbstractViewForActivity {

	private Controller controller;
	private SmartImageView siv_myphoto_detail_image;
	private TextView tv_myphoto_detail_category;
	private TextView tv_myphoto_detail_location;
	private Button iv_myphoto_detail_size;
	private GridView gv_myPhotoTag;
	private ArrayAdapterForTagGridView arrayAdapterForMyRequestTagGrid;
	
	public ViewForMyPhotoDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_myphoto_detail, null);
	}

	@Override
	protected void initViews() {
		siv_myphoto_detail_image = (SmartImageView)findViewById(R.id.siv_myphoto_detail_image);
		tv_myphoto_detail_category = (TextView)findViewById(R.id.tv_myphoto_detail_category);
		tv_myphoto_detail_location = (TextView)findViewById(R.id.tv_myphoto_detail_location);
		iv_myphoto_detail_size = (Button)findViewById(R.id.bt_myphoto_detail_size);
		
		gv_myPhotoTag = (GridView)findViewById(R.id.gv_request_tag);	
		arrayAdapterForMyRequestTagGrid = new ArrayAdapterForTagGridView(getContext( ), 0);
		gv_myPhotoTag.setAdapter(arrayAdapterForMyRequestTagGrid);	
	}

	@Override
	protected void setEvent() { }
	
	public void initViewInfos(Intent anIntent) {
		Image item = (Image)anIntent.getSerializableExtra(MyPhotoFragment.PARAM_MYPHOTO_IMAGE_ITEM);
		
		siv_myphoto_detail_image.setImageUrl(item.getMiddleUrl());
		tv_myphoto_detail_category.setText(item.getCategory().getName());
		iv_myphoto_detail_size.setText(item.getSize());
		tv_myphoto_detail_location.setText(item.getUserProfile().getLocation().getDescription());
		
		arrayAdapterForMyRequestTagGrid.addAll(item.getTag());
	}
	
	public static interface Controller {
		
	}
}
