package com.continueing.photoco.ui.login_page;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.reuse.etc.BackPressCloseHandler;
import com.continueing.photoco.reuse.etc.ErrorCodeList;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.UsersRequest;
import com.continueing.photoco.reuse.reference.UserPreference;
import com.continueing.photoco.ui.MainActivity;
import com.continueing.photoco.ui.account_page.AccountActivity;

public class LoginActivity extends Activity implements ViewForLoginActivity.Controller {
	private ViewForLoginActivity view;
	private BackPressCloseHandler backPressCloseHandler;
	private String userName;
	private String userPassword;
	
	public static final String PARAM_LOGINACTIVITY_USERNAME_KEY ="username";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		backPressCloseHandler = new BackPressCloseHandler(this);
		view = new ViewForLoginActivity(getApplicationContext(), this); // 뷰를 생성해 낸다.
		setContentView(view.getRoot());
		// senContentView의 디폴트 레이아웃 파라미터는 match_parent이다.
		// 최종적인 match_parent 요청은 윈도우에게 한다.(안드로이드 정복 P.808 참고)
	}
	
	// 자동로그인 할 때 Login Activity를 종료한다.
	@Override
	protected void onResume( ) {
		super.onResume();
		
		UserPreference userPreference = new UserPreference(getApplicationContext());
		
		if(userPreference.isLoggedIn() == true) {
			String userName = userPreference.getString(UserPreference.KEY_USERNAME, null);
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			intent.putExtra(PARAM_LOGINACTIVITY_USERNAME_KEY, userName); // 사용자의 이름을 전달한다.
			startActivity(intent);
			finish( ); // 자동로그인이므로 바로 종료한다.
		}
	}

	@Override
	public void onEditTextsLengthChanged(int aUserNameLength, int aPasswordLength) {
		if ((aUserNameLength > 2 && aUserNameLength < 16) && (aPasswordLength > 5 && aPasswordLength < 21)) {
			view.makeLoginEnable(); // 뷰에 관한것은 뷰에서 해아한다.
		} else {
			view.makeLoginDisable();
		}
	}

	// 로그인을 했을 때 서버와 통신하기 위해 만든 부분이다.
	@Override
	public void onSingIn(String aUserName, String aPassword) {
		UsersRequest userLoginRequest = new UsersRequest(getApplicationContext());
		userName = aUserName; // Setting Activity로 보낼 유저 이름
		userPassword = aPassword;
		
		try {
			userLoginRequest.login(aUserName, aPassword, loginListener);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
	}
	
	// LoginActivity의 SignIn버튼에서 수행 할 네트워크 리스너
	// JsonHttpResponseHandler콜백에서 처리하기 위해서 넘겨준다.
	HttpRequester.NetworkResponseListener loginListener = new HttpRequester.NetworkResponseListener() {
		
		@Override
		public void onSuccess(JSONObject jsonObject) {
			view.releaseSubmitButton();
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			intent.putExtra(PARAM_LOGINACTIVITY_USERNAME_KEY, userName);
			UserPreference userPreference = new UserPreference(getApplicationContext());
			userPreference.login(userName, userPassword);
			startActivity(intent);
			finish( );
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) {
			if(errorCode == 15) {
				Toast.makeText(getApplicationContext(), ErrorCodeList.ERROR_MESSAGE_USERNAME_PASSWORD_MISMATCH, Toast.LENGTH_SHORT).show();
				view.releaseSubmitButton();
			}
		}
	};
	
	// 계정을 만들기 위해서 만든메소드
	@Override
	public void onCreateAccount() {
		Intent intent = new Intent(this, AccountActivity.class);
		startActivity(intent);
	}
	
	// 비밀번호를 잃어버렸을 때 찾기위한 메소드
	@Override
	public void onForgotPassword() { }
	
	@Override
	public void onBackPressed() {
		backPressCloseHandler.onBackPressed();
	}
}