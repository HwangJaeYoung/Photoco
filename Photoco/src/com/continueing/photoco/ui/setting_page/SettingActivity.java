package com.continueing.photoco.ui.setting_page;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.domain.MyInformation;
import com.continueing.photoco.reuse.etc.ErrorCodeList;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.MyInformationRequest;
import com.continueing.photoco.reuse.network.UsersRequest;
import com.continueing.photoco.reuse.page.location_page.LocationActivity;

public class SettingActivity extends ActionBarActivity implements ViewForSettingActivity.Controller {
	
	private ViewForSettingActivity view;
	private MyInformation myInformation;
	private static final int REQUEST_CODE_PICK_LOCATION = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		view = new ViewForSettingActivity(getApplicationContext( ), this);
		setContentView(view.getRoot());
		searchMyInformationFromServer( );
	}

	@Override
	public void onSettingClicked(String anUserId, String anUserName, String anEmail, String aPassword, String aConfirmPassword, String anLocationID) {
		// 업데이트한 비밀번호 또는 지역을 업데이트하고 뷰 종료
		UsersRequest usersRequest = new UsersRequest(getApplicationContext());
		try {
			usersRequest.updateUserInformation(anUserId, anUserName, anEmail,
			aPassword, aConfirmPassword, anLocationID, userInformationUpdateListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void searchMyInformationFromServer( ) {
		MyInformationRequest myInformationRequest = new MyInformationRequest(getApplicationContext());
		
		try {
			myInformationRequest.getMyInformation(getMyInformationListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener getMyInformationListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) { 
			JSONObject tempJSONObject = null;
			
			try {
				tempJSONObject = jsonObject.getJSONObject(JsonResponseHandler.PARM_DATA);
				myInformation = new MyInformation(tempJSONObject); // 유저의 정보를 가진 객체를 생성한다.		
			} catch (JSONException e) {
				e.printStackTrace();
			}
			view.setMyInformationData(myInformation);
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	HttpRequester.NetworkResponseListener userInformationUpdateListener = new HttpRequester.NetworkResponseListener() {
		// Location객체를 만들고 리스트 뷰에 주기위해 합쳐놓는 과정
		@Override
		public void onSuccess(JSONObject jsonObject) {
			finish( );
			Toast.makeText(getApplicationContext(), "To update user information is success", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { // 정확한 포맷으로 오지않았을때 발생하는 에러코드
			switch(errorCode)
			{
				case 0:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_UNKNOWN, Toast.LENGTH_SHORT).show();
					break;
				case 1:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_USERNAME_ABSENCE, Toast.LENGTH_SHORT).show();
					break;
				case 2:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_EMAIL_ABSENCE, Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_PASSWORD_ABSENCE, Toast.LENGTH_SHORT).show();
					break;
				case 4:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_PASSWORD_CONFIRMATION_ABSENCE, Toast.LENGTH_SHORT).show();
					break;
				case 5:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_LOCATION_ID_ABSENCE, Toast.LENGTH_SHORT).show();
					break;
				case 6:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_TOO_SHORT_USERNAME, Toast.LENGTH_SHORT).show();
					break;
				case 7:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_TOO_LONG_USERNAME, Toast.LENGTH_SHORT).show();
					break;
				case 8:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_TOO_SHORT_PASSWORD, Toast.LENGTH_SHORT).show();
					break;
				case 9:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_TOO_LONG_PASSWORD, Toast.LENGTH_SHORT).show();
					break;
				case 10:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_DIFFERENT_PASSWORDS, Toast.LENGTH_SHORT).show();
					break;
				case 11:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_INVALID_EMAIL, Toast.LENGTH_SHORT).show();
					break;
				case 12:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_INVALID_PRIMARY_KEY, Toast.LENGTH_SHORT).show();
					break;
				case 13:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_NONEXISTENT_LOCATION_ID, Toast.LENGTH_SHORT).show();
					break;
				case 14:
					Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_ALREADY_EXISTING_USER, Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
			}	
		}
	};
	
	// 지역검색 액티비티가 종료되고 거기서 사용자가 선택한 지역을 받기위한 콜백 메소드
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_PICK_LOCATION)
			if(resultCode == Activity.RESULT_OK){
				view.selectedLocation(data.getStringExtra(LocationActivity.PARAM_LOCATIONACTIVITY_LOCATION_KEY));
			}
	}

	// 지역검색을 하기위해 눌렸을 때
	@Override
	public void onLocationSelect() {
		Intent intent = new Intent(this, LocationActivity.class);
		startActivityForResult(intent, REQUEST_CODE_PICK_LOCATION); 		
	}
}