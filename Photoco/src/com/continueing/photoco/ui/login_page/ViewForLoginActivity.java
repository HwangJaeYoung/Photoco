package com.continueing.photoco.ui.login_page;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.reuse.widget.SubmitButton;

public class ViewForLoginActivity extends AbstractViewForActivity {
	private Controller controller;
	private SubmitButton bt_signIn;
	private Button bt_createAccount;
	private Button bt_forgotPassword;
	private EditText et_username;
	private EditText et_password;

	public ViewForLoginActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	// this.root = inflate()의 실질적인 호출부분(상속)
	// Fragment같은 경우는 onCreateView에서 inflate객체를 넘겨주기 때문에 inflate를
	// 사용하면 되지만 여기서는 그 것이 안 되므로 LayouInflater.form을 사용한다.
	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_login, null);
	}

	@Override
	protected void initViews() {
		TextView tv_photoco = (TextView)findViewById(R.id.tv_photoco);
		TextView tv_photocoDetail = (TextView)findViewById(R.id.tv_photoco_detail);
		et_username = (EditText)findViewById(R.id.et_login_username);
		et_password = (EditText)findViewById(R.id.et_login_password);
		bt_signIn = (SubmitButton)findViewById(R.id.bt_sign_in);
		bt_createAccount = (Button)findViewById(R.id.bt_create_ac);
		bt_forgotPassword = (Button)findViewById(R.id.bt_forgot_pw);
		
		Typeface type = Typeface.createFromAsset(getContext().getAssets(), "BreeSerif_Reg.otf");
		
		tv_photoco.setTypeface(type);
		tv_photocoDetail.setTypeface(type);
		et_username.setTypeface(type);
		et_password.setTypeface(type);
		bt_signIn.setTypeface(type);
		bt_createAccount.setTypeface(type);
		bt_forgotPassword.setTypeface(type);
		
		bt_signIn.init((ProgressBar)findViewById(R.id.pg_sign_up));
		bt_signIn.addViewToHold(et_username);
		bt_signIn.addViewToHold(et_password);
		bt_signIn.addViewToHold(bt_createAccount);
		bt_signIn.addViewToHold(bt_forgotPassword);
		bt_signIn.setBackgroundColor(Color.parseColor("#dde6f2"));
	}

	@Override
	protected void setEvent() {
		// username, password길이의 변화를 확인하기 위해서 선언
		et_username.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				controller.onEditTextsLengthChanged(et_username.getText().toString().length(), et_password.getText().toString().length());	
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			
			@Override
			public void afterTextChanged(Editable s) { }
		});
		
		et_password.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				controller.onEditTextsLengthChanged(et_username.getText().toString().length(), et_password.getText().toString().length());			
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) { }
			
			@Override
			public void afterTextChanged(Editable s) { }
		});
		
		// 계정 로그인 할 때 사용하는 버튼
		bt_signIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onSingIn(et_username.getText().toString(), et_password.getText().toString());
			}
		});
		
		// 계정을 만들 때 사용하는 버튼
		bt_createAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onCreateAccount();
			}
		});
	}

	// LoginActvity에 대한 Controller
	// 즉, Controller에 값을 넘겨주는 역할만 하면된다 여기서는
	// 나머지 처리는 Activity에서 할 것이기 때문이다.
	// interface가 아닌 class로 하면 비효율적.
	public static interface Controller {
		public void onEditTextsLengthChanged(int aUserNameLength, int aPasswordLength);
		public void onSingIn(String aUserNameLength, String aPassword);
		public void onCreateAccount();
		public void onForgotPassword();
	}
	
	public void makeLoginDisable() // signIn버튼을 비활성화 시킨다.
	{	
		bt_signIn.setEnabled(false);
		bt_signIn.setBackgroundColor(Color.parseColor("#dde6f2"));
	}
	
	public void makeLoginEnable() // signIn버튼을 활성화 시킨다.
	{
		// 활성화 되었을 때 버튼의 색을 바꾸고 클릭 가능한 상태로 한다.
		bt_signIn.setEnabled(true);
		bt_signIn.setBackgroundColor(Color.parseColor("#62caf7"));
	}
	
	public void releaseSubmitButton()
    {
        this.bt_signIn.release();
    }	
}