package com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.gridview.Images;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.gridview.ViewForArrayAdapterForMyRequestDetailActivity.IMyRequestDetailItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.myrequest_detail_page.MyRequestDetailActivity;

public class MyRequestGridViewDetailActivity extends ActionBarActivity implements ViewForMyRequestGridViewDetailActivity.Controller{

	private ViewForMyRequestGridViewDetailActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForMyRequestGridViewDetailActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
		
		ArrayList<IMyRequestDetailItem> list = new ArrayList<IMyRequestDetailItem>( );
		list.add(new Images("http://farm7.staticflickr.com/6101/6853156632_6374976d38_c.jpg"));
        list.add(new Images("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg"));
        list.add(new Images("http://farm8.staticflickr.com/7232/6913504132_a0fce67a0e_c.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8208/8219397252_a04e2184b2.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8185/8081514424_270630b7a5.jpg"));
        list.add(new Images("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg"));
        list.add(new Images("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg"));
        list.add(new Images("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg"));
        list.add(new Images("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg"));
        list.add(new Images("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg"));
        list.add(new Images("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg"));
        list.add(new Images("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg"));
        list.add(new Images("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg"));
        list.add(new Images("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg"));
		
        view.addRequestImages(list);
	}

	@Override
	public void selectedGridViewItem() {
		Intent intent = new Intent(getApplicationContext(), MyRequestDetailActivity.class);
		startActivity(intent);
	}
}