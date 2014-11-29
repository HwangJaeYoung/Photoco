package com.continueing.photoco.ui.setting_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.MyInformation;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForSettingActivity extends AbstractViewForActivity{

	private Button bt_settingAccount;
	private Controller controller;
	private TextView tv_settingLocation;
	private TextView tv_settingUsername;
	private TextView tv_settingEmail;	
	private LinearLayout ll_settingLocation;
	private EditText et_settingPassword;
	private EditText et_settingConfrimPassword;
	private MyInformation myInformation;
	
	public ViewForSettingActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {	
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_setting, null);
	}

	@Override
	protected void initViews() {
		bt_settingAccount = (Button)findViewById(R.id.bt_setting_ac);
		ll_settingLocation = (LinearLayout)findViewById(R.id.layout_setting_location);
		tv_settingLocation = (TextView)findViewById(R.id.tv_setting_location);
		tv_settingUsername = (TextView)findViewById(R.id.tv_setting_username);
		tv_settingEmail = (TextView)findViewById(R.id.tv_setting_email);
		et_settingPassword = (EditText)findViewById(R.id.et_setting_password);
		et_settingConfrimPassword = (EditText)findViewById(R.id.et_setting_confrim_password);
	}
	
	@Override
	protected void setEvent() {
		// 비밀번호, 지역변경을 위해서 Update버튼을 클릭
		bt_settingAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onSettingClicked(myInformation.getId(),
						myInformation.getUserName(), myInformation.geteMail(),
						et_settingPassword.getText().toString(),
						et_settingConfrimPassword.getText().toString(), myInformation
								.getLocation().getId());
			}
		});
	
		// 지역을 변경하기 위해서 버튼을 클릭했을 때
		ll_settingLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onLocationSelect();				
			}
		});
	}
	
	// 지역검색 후 검색한 지역을 표시하기 위한 메소드
	public void selectedLocation(String aLocation) {
		tv_settingLocation.setText(aLocation);
	}
		
	public void setMyInformationData(MyInformation aMyInformation) {
		myInformation = aMyInformation; // onSettingClicked에서 사용자 정보를 가져오기위해 미리 저장한다.
		tv_settingUsername.setText(myInformation.getUserName());
		tv_settingEmail.setText(myInformation.geteMail());
		tv_settingLocation.setText(myInformation.getLocation().getDescription());
	}
	
	public static interface Controller {
		public void onSettingClicked(String anUserId, String anUserName, String anEmail, String aPassword, String aConfirmPassword, String anLocationID);
		public void onLocationSelect( );
	}
}
