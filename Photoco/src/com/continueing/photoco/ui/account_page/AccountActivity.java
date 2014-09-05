package com.continueing.photoco.ui.account_page;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.reuse.etc.ErrorCodeList;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.UsersRequest;
import com.continueing.photoco.reuse.page.location_page.LocationActivity;


public class AccountActivity extends Activity implements ViewForAccountActivity.Controller{
	private static final int REQUEST_CODE_GET_QUERY = 0;
	private ViewForAccountActivity view;
	private String primaryKey;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		view = new ViewForAccountActivity(getApplicationContext(), this);
		setContentView(view.getRoot());
	}
	
	// 지역검색을 하기 위한 액티비티를 호출한다.
	@Override
	public void onLocationSelect() {
		Intent intent = new Intent(this, LocationActivity.class);
		startActivityForResult(intent, REQUEST_CODE_GET_QUERY); 	
	}
	
	// 지역검색 액티비티가 종료되고 거기서 사용자가 선택한 지역을 받기위한 콜백 메소드
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_GET_QUERY)
			if(resultCode == Activity.RESULT_OK) {
				view.selectedLocation(data.getStringExtra(LocationActivity.PARAM_LOCATIONACTIVITY_LOCATION_KEY));
				primaryKey = data.getStringExtra(LocationActivity.PARAM_PRIMARY_KEY); // DB에서 primaryKey로 사용함.
			}
	}

	@Override
	public void onSignUp(String aUserName, String aEmail, String aPassword, String aConfirmPassword, boolean aChecked) {
		// 계정을 생성한다.
		submitAccountInfo(aUserName, aEmail, aPassword, aConfirmPassword, aChecked);	
	}
	
	// 서버와 통신을 하기 위해서 구성한 메소드
	public void submitAccountInfo(String aUserName, String aEmail, String aPassword, String aConfirmPassword, boolean aChecked) {
		UsersRequest signUpRequest = new UsersRequest(getApplicationContext());
		
		if(aChecked == true) { // 최종적으로 약관에 동의 했을시에
			try {
				signUpRequest.signUp(aUserName, aEmail, aPassword, aConfirmPassword, primaryKey, userSignUpRequestListener);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_CHECKBOTTUN_NOT_SELECTED, Toast.LENGTH_SHORT).show( );
			view.releaseSubmitButton();
		}
	}
	
	HttpRequester.NetworkResponseListener userSignUpRequestListener = new HttpRequester.NetworkResponseListener() {
		// Location객체를 만들고 리스트 뷰에 주기위해 합쳐놓는 과정
		@Override
		public void onSuccess(JSONObject jsonObject) {
			view.releaseSubmitButton();
			finish( );
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { 
			switch(errorCode) {
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
			view.releaseSubmitButton();
		}
	};
}