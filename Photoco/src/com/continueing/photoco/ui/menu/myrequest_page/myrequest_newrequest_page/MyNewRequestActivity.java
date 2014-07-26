package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import com.continueing.photoco.ui.location_page.LocationActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page.MyNewRequestCategoryActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.MyNewRequestDurationActivity;

public class MyNewRequestActivity extends ActionBarActivity implements ViewForMyNewRequestActivity.Controller{
	
	public static final int REQUEST_CODE_GET_QUERY = 0;
	public static final int REQUEST_PICK_IMAGE = 1;
	public static final int REQUEST_PICK_DURATION = 2;
	public static final int REQUEST_PICK_CATEGORY = 3;
	
	private ViewForMyNewRequestActivity view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		view = new ViewForMyNewRequestActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		setContentView(view.getRoot());
	}

	@Override
	public void onSubmit() {
		// 여기서 tag, category, locatino같은것을 인텐트로 던져주고
		setResult(Activity.RESULT_OK);
		finish( );
	}

	@Override
	public void onLocationSelect() {
		Intent intent = new Intent(this, LocationActivity.class);
		startActivityForResult(intent, REQUEST_CODE_GET_QUERY); 	
	}

	@Override
	public void onSelectGallery() {
		Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(pickImageIntent, REQUEST_PICK_IMAGE);		
	}

	@Override
	public void onSelectDuration() {
		Intent intent = new Intent(this, MyNewRequestDurationActivity.class);
		startActivityForResult(intent, REQUEST_PICK_DURATION); 
	}
	
	@Override
	public void onSelectCategory() {
		Intent intent = new Intent(this, MyNewRequestCategoryActivity.class);
		startActivityForResult(intent, REQUEST_PICK_CATEGORY); 
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_GET_QUERY)
		{
			if(resultCode == Activity.RESULT_OK)
				view.selectedLocation(data.getStringExtra(LocationActivity.PARAM_LOCATION_ACTIVITY_KEY));
		}
		
		else if(requestCode == REQUEST_PICK_IMAGE)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				 Uri imageUri = data.getData();
                 Bitmap bitmap;
                 try {
					bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
					view.selectedImage(bitmap);     
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}    
			}
		}
		
		else if (requestCode == REQUEST_PICK_DURATION)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				view.selectedDuration(data.getStringExtra(MyNewRequestDurationActivity.PARAM_HOUR_TEXT_KEY),
						data.getStringExtra(MyNewRequestDurationActivity.PARAM_END_DATE_KEY));
			}
		}
		
		else if(requestCode == REQUEST_PICK_CATEGORY)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				view.selectedCategory(data.getStringExtra(MyNewRequestCategoryActivity.PARAM_CATEGORY_ACTIVITY_KEY));
			}
		}
		
	}
}
