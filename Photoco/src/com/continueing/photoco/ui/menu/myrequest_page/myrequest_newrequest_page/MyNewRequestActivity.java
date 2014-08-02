package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.reuse.etc.ErrorCodeList;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.RequestsRequest;
import com.continueing.photoco.reuse.page.location_page.LocationActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page.MyNewRequestCategoryActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page.MyNewRequestDurationActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.MyNewRequestTagActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class MyNewRequestActivity extends ActionBarActivity implements ViewForMyNewRequestActivity.Controller{
	
	public static final int REQUEST_CODE_PICK_LOCATION = 0;
	public static final int REQUEST_CODE_PICK_IMAGE = 1;
	public static final int REQUEST_CODE_PICK_DURATION = 2;
	public static final int REQUEST_CODE_PICK_CATEGORY = 3;
	public static final int REQUEST_CODE_PICK_TAG = 4;
	
	private String locationId;
	private String categoryId;
	private String durationHour;
	private String description;
	private File filePath;
	private JSONArray tagJSONArray;
	
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
		addMyRequestItemToServer( );
	}

	@Override
	public void onLocationSelect() {
		Intent intent = new Intent(this, LocationActivity.class);
		startActivityForResult(intent, REQUEST_CODE_PICK_LOCATION); 	
	}

	@Override
	public void onSelectGallery() {
		Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(pickImageIntent, REQUEST_CODE_PICK_IMAGE);		
	}

	@Override
	public void onSelectDuration() {
		Intent intent = new Intent(this, MyNewRequestDurationActivity.class);
		startActivityForResult(intent, REQUEST_CODE_PICK_DURATION); 
	}
	
	@Override
	public void onSelectCategory() {
		Intent intent = new Intent(this, MyNewRequestCategoryActivity.class);
		startActivityForResult(intent, REQUEST_CODE_PICK_CATEGORY); 
	}
	
	@Override
	public void onSelectTag() {
		Intent intent = new Intent(this, MyNewRequestTagActivity.class);
		startActivityForResult(intent, REQUEST_CODE_PICK_TAG); 
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_PICK_LOCATION)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				view.selectedLocation(data.getStringExtra(LocationActivity.PARAM_LOCATIONACTIVITY_LOCATION_KEY));
				locationId = data.getStringExtra(LocationActivity.PARAM_PRIMARY_KEY);
			}
		}
		
		else if(requestCode == REQUEST_CODE_PICK_IMAGE)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				 Uri imageUri = data.getData();

				 String realpath = getRealPathFromUri(this, imageUri);
				 filePath = new File(realpath);
				 	 
                 try {
                	 Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
					view.selectedImage(bitmap);     
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}    
			}
		}
		
		else if (requestCode == REQUEST_CODE_PICK_DURATION)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				view.selectedDuration(data.getStringExtra(MyNewRequestDurationActivity.PARAM_HOUR_TEXT_KEY),
						data.getStringExtra(MyNewRequestDurationActivity.PARAM_END_DATE_KEY));
				durationHour = data.getStringExtra(MyNewRequestDurationActivity.PARAM_HOUR_KEY);
			}
		}
		
		else if(requestCode == REQUEST_CODE_PICK_CATEGORY)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				view.selectedCategory(data.getStringExtra(MyNewRequestCategoryActivity.PARAM_CATEGORY_ACTIVITY_KEY));
				categoryId = (data.getStringExtra(MyNewRequestCategoryActivity.PARAM_PRIMARY_KEY));
			}
		}

		else if(requestCode == REQUEST_CODE_PICK_TAG)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				tagJSONArray = new JSONArray( );
				
				@SuppressWarnings("unchecked")
				ArrayList<IMyRequestTagItem> tagArrayList = (ArrayList<IMyRequestTagItem>)data.getSerializableExtra(MyNewRequestTagActivity.PARAM_TAG_ARRAYLIST_KEY);
				for(int i = 0; i < tagArrayList.size(); i++)
				{
					tagJSONArray.put(tagArrayList.get(i).getTagText());
				}
				
				view.selectedTag(tagArrayList);
			}
		}	
	}
	
	public static String getRealPathFromUri(Context context, Uri contentUri) {
	    Cursor cursor = null;
	    try {
	        String[] proj = { MediaStore.Images.Media.DATA };
	        cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
	        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	        cursor.moveToFirst();
	        return cursor.getString(column_index);
	    } finally {
	        if (cursor != null) {
	            cursor.close();
	        }
	    }
	}
	
	private void addMyRequestItemToServer( )
	{
		RequestsRequest request = new RequestsRequest(getApplicationContext());
		description = view.getDescription();
		
		try {
			request.setMyRequestItem(locationId, categoryId, durationHour, tagJSONArray, description, filePath, submitListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener submitListener = new HttpRequester.NetworkResponseListener( ){

		@Override
		public void onSuccess(JSONObject jsonObject) {
			setResult(Activity.RESULT_OK);
			finish( );
		}
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) {
			switch(errorCode)
			{
				case 16:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_IMAGE_ABSENCE, Toast.LENGTH_SHORT).show();
					break;
				case 17:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_CATEGORY_ID_ABSENCE , Toast.LENGTH_SHORT).show();
					break;
				case 18:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_DURATION_HOUR_ABSENCE, Toast.LENGTH_SHORT).show();
					break;
				case 19:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_TAG_NAMES_ABSENCE, Toast.LENGTH_SHORT).show();
					break;
				case 20:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_DESCRIPTION_ABSENCE, Toast.LENGTH_SHORT).show();
					break;
				case 21:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_NONEXISTENT_CATEGORY_ID, Toast.LENGTH_SHORT).show();
					break;
				case 22:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_LOGIN_REQUIRED, Toast.LENGTH_SHORT).show();
					break;
				case 23:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_INVALID_JSON_STRING, Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
			}		
		}
	};
}