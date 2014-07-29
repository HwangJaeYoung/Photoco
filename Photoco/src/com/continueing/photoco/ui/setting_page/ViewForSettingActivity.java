package com.continueing.photoco.ui.setting_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForSettingActivity extends AbstractViewForActivity{

	private Button bt_settingAccount;
	private Controller controller;
	private TextView tv_settingLocation;
	private LinearLayout ll_settingLocation;
	private EditText et_settingPassword;
	private EditText et_settingConfrimPassword;
	private String location;
	private String userName;
	
	public ViewForSettingActivity(Context context, String aUserName, Controller aController) {
		super(context);
		controller = aController;
		userName = aUserName;
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
		et_settingPassword = (EditText)findViewById(R.id.et_setting_password);
		et_settingConfrimPassword = (EditText)findViewById(R.id.et_setting_confrim_password);
	}

	@Override
	protected void setEvent() {
		bt_settingAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onSettingClicked(et_settingPassword.toString(), et_settingConfrimPassword.toString(), location);
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
	public void selectedLocation(String aLocation)
	{
		location = aLocation;
		tv_settingLocation.setText(aLocation);
	}
		
	public static interface Controller
	{
		public void onSettingClicked(String aPassword, String aConfirmPassword, String aLocation);
		public void onLocationSelect( );
	}
}
