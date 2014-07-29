package com.continueing.photoco.ui.setting_page;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.reuse.page.location_page.LocationActivity;
import com.continueing.photoco.ui.MainActivity;

public class SettingActivity extends ActionBarActivity implements ViewForSettingActivity.Controller{
	
	private ViewForSettingActivity view;
	private String userName;
	public static final int REQUEST_CODE_GET_QUERY = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		userName = getIntent( ).getStringExtra(MainActivity.PARAM_MAINACTIVITY_USERNAME_KEY);
		view = new ViewForSettingActivity(getApplicationContext( ), userName, this);
		setContentView(view.getRoot());
	}

	@Override
	public void onSettingClicked(String aPassword, String aConfirmPassword, String aLocation) {
		// 업데이트한 비밀번호 또는 지역을 업데이트하고 뷰 종료
		if(aPassword.length() > 5 && aConfirmPassword.length() > 5 && aLocation.length() > 0)
		{
			if(aPassword.equals(aConfirmPassword))
			{
				// 통신을 한다.
				finish( );	
			}
		}
		else
		{
			Toast.makeText(this, "pass오류", Toast.LENGTH_SHORT).show();
		}
	}
	
	// 지역검색 액티비티가 종료되고 거기서 사용자가 선택한 지역을 받기위한 콜백 메소드
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_GET_QUERY)
			if(resultCode == Activity.RESULT_OK)
			{
				view.selectedLocation(data.getStringExtra(LocationActivity.PARAM_LOCATIONACTIVITY_LOCATION_KEY));
			}
	}

	@Override
	public void onLocationSelect() {
		Intent intent = new Intent(this, LocationActivity.class);
		startActivityForResult(intent, REQUEST_CODE_GET_QUERY); 		
	}
}