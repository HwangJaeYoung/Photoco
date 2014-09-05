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

import com.continueing.photoco.reuse.network.FindingJobListRequest;
import com.continueing.photoco.reuse.network.HttpRequester;

public class JobListDetailActivity extends ActionBarActivity implements ViewForJobListDetailActivity.Controller{
	private String jobId; // 사진을 제출할 때 사용하는 JobItem의 PrimaryKey이다.
	private ViewForJobListDetailActivity view;
	private static final int REQUEST_CODE_PICK_IMAGE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new ViewForJobListDetailActivity(getApplicationContext( ), this);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		setContentView(view.getRoot());
		view.initViewInfos(getIntent( ));
	}

	@Override
	public void onPhotoSubmit(String aJobId) {
		jobId = aJobId; // 사진을 제출할 때 사용하는 JobItem의 PrimaryKey이다.
		Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(pickImageIntent, REQUEST_CODE_PICK_IMAGE);		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_PICK_IMAGE) {
			if(resultCode == Activity.RESULT_OK) {
				// 사진의 경로를 가지고 온다.
				Uri imageUri = data.getData();
				String realpath = getRealPathFromUri(getApplicationContext(), imageUri);
				File filePath = new File(realpath);
				submitPhotoToServer(filePath);
			}
		}
	}
	
	// 리퀘스트에 자신이 보내고 싶은 사진을 제출한다.
	public void submitPhotoToServer(File aFilePath) {
		FindingJobListRequest submitPhotoRequest = new FindingJobListRequest(getApplicationContext());
		try {
			submitPhotoRequest.submitPhoto(aFilePath, jobId, submitPhotoListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener submitPhotoListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) { 
			Toast.makeText(getApplicationContext(), "To submit photo to server is successful", Toast.LENGTH_LONG).show();
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	// URI를 풀 Path로 변환하여 준다. c://....
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