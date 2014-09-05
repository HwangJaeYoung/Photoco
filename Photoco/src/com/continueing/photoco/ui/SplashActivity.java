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
	private static final int SPLASH_TIME_OUT = 1000;

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
				UserPreference userPreference = new UserPreference(getApplicationContext());
				if (userPreference.isLoggedIn() == false) {
					i = new Intent(SplashActivity.this, LoginActivity.class);
				} else {
					i = new Intent(SplashActivity.this, MainActivity.class);
				}
				startActivity(i);
				finish();
				SplashActivity.this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
			}

		}, SPLASH_TIME_OUT);
	}
}