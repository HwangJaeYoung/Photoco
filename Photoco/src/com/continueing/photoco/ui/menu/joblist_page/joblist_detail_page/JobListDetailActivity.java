package com.continueing.photoco.ui.menu.joblist_page.joblist_detail_page;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.SubmitPhotoRequest;

public class JobListDetailActivity extends ActionBarActivity implements ViewForJobListDetailActivity.Controller{
	private ViewForJobListDetailActivity view;
	private static final int REQUEST_CODE_PICK_IMAGE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForJobListDetailActivity(getApplicationContext( ), this);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
	}

	@Override
	public void onPhotoSubmit() {
		Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(pickImageIntent, REQUEST_CODE_PICK_IMAGE);		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_PICK_IMAGE) {
			if(resultCode == Activity.RESULT_OK) {
				 Uri imageUri = data.getData();
				 String realpath = getRealPathFromUri(getApplicationContext(), imageUri);
				 File filePath = new File(realpath);
				 submitPhotoToServer(filePath);
			}
		}
	}
	
	public void submitPhotoToServer(File aFilePath) {
		SubmitPhotoRequest submitPhotoRequest = new SubmitPhotoRequest(getApplicationContext());
		try {
			submitPhotoRequest.submitPhoto(aFilePath, submitPhotoListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener submitPhotoListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) { 
			Toast.makeText(getApplicationContext(), "To submit photo to server is successful", Toast.LENGTH_SHORT).show();
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	public String getRealPathFromUri(Context context, Uri contentUri) {
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
}