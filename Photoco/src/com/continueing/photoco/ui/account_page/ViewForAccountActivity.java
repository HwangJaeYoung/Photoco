package com.continueing.photoco.ui.account_page;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.reuse.widget.SubmitButton;

public class ViewForAccountActivity extends AbstractViewForActivity {
	private Controller controller;
	private TextView tv_signUpAgree;
	private EditText et_signUpUserName;
	private EditText et_signEmail;
	private EditText et_signUpPassword;
	private EditText et_signUpConfirmPassword;
	private LinearLayout llSearchLocation;
	private TextView tv_signUpLocation;
	private CheckBox cb_singnUpAgree;
	private SubmitButton bt_submit;
	
	public ViewForAccountActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}
	
	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(
				R.layout.activity_signup, null);
	}

	@Override
	protected void initViews() {		
		tv_signUpAgree = (TextView)findViewById(R.id.tv_signup_agree);
		et_signUpUserName = (EditText)findViewById(R.id.et_signup_username);
		et_signEmail = (EditText)findViewById(R.id.et_signup_email);
		et_signUpPassword = (EditText)findViewById(R.id.et_signup_password);
		et_signUpConfirmPassword = (EditText)findViewById(R.id.et_signup_confirm_password);
		llSearchLocation = (LinearLayout)findViewById(R.id.ll_search_location);
		tv_signUpLocation = (TextView)findViewById(R.id.tv_signup_location);
		cb_singnUpAgree = (CheckBox)findViewById(R.id.cb_signup_agree);
		bt_submit = (SubmitButton)findViewById(R.id.bt_create_ac);
		
		bt_submit.init((ProgressBar)findViewById(R.id.pg_sign_up));
		bt_submit.addViewToHold(et_signUpUserName);
		bt_submit.addViewToHold(et_signEmail);
		bt_submit.addViewToHold(et_signUpPassword);
		bt_submit.addViewToHold(et_signUpConfirmPassword);
		bt_submit.addViewToHold(llSearchLocation);
		bt_submit.addViewToHold(cb_singnUpAgree);
	}

	@Override
	protected void setEvent() {
		// 사용자가 등록할 때 사용할 버튼
		bt_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onSignUp(et_signUpUserName.getText().toString(), 
						et_signEmail.getText().toString(),
						et_signUpPassword.getText().toString(),
						et_signUpConfirmPassword.getText().toString(),
						cb_singnUpAgree.isChecked()
						);
			}
		});	
		
		// 지역검색 버튼(레이아웃으르 버튼으로 사용)
		llSearchLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				controller.onLocationSelect( );		
			}
		});
		
		// 체크버튼이 눌렸을 때, UnderLine처리
		cb_singnUpAgree.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(buttonView.getId() == R.id.cb_signup_agree)
				{
					if(isChecked)
						tv_signUpAgree.setText(Html.fromHtml("<u>" + "Agree to terms and conditions" + "</u>"));
					else
						tv_signUpAgree.setText("Agree to terms and conditions");
				}
			}
		});
	}
	
	// 지역검색 후 검색한 지역을 표시하기 위한 메소드
	public void selectedLocation(String aLocation)
	{
		tv_signUpLocation.setText(aLocation);
	}
	
	public void releaseSubmitButton()
    {
        this.bt_submit.release();
    }

	// AccountActvity에 대한 Controller
	public static interface Controller {
		// 입력된 데이터를 통신할 때 사용한다.
		public void onSignUp(String aUserName, String aEmail, String aPassword, String aConfirmPassword, boolean aChecked);
		public void onLocationSelect( );
	}
}