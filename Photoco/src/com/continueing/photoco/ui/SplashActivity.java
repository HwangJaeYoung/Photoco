package com.continueing.photoco.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.reference.UserPreference;
import com.continueing.photoco.ui.login_page.LoginActivity;

public class SplashActivity extends Activity {
	private static final int SPLASH_TIME_OUT = 1000; // Splash가 보여지는 시간

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent i = null;
				
				// 유저의 사용정보를 가져온다.
				UserPreference userPreference = new UserPreference(getApplicationContext());
				
				if (userPreference.isLoggedIn() == false) { // 유저가 로그인을 한 번도 하지 않았으면
					i = new Intent(SplashActivity.this, LoginActivity.class); // 로그인 페이지로 이동한다.
				} else { // 로그인을 한 번이라도 수행하였다면
					i = new Intent(SplashActivity.this, MainActivity.class); // 메인액티비티로 이동한다.
				}
				
				startActivity(i);
				finish();
				// Splash에 애니메이션을 적용하여 동적으로 보이게 한다.
				SplashActivity.this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
			}

		}, SPLASH_TIME_OUT);
	}
}