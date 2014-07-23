package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_duration_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForMyNewRequestDurationActivity extends AbstractViewForActivity {

	private Controller controller;
	private RelativeLayout rl_selcet12hour;
	private RelativeLayout rl_selcet1day;
	private RelativeLayout rl_selcet3day;
	private RelativeLayout rl_selcet7day;
	private ImageView iv_requestNewDurationTwelve;
	private ImageView iv_requestNewDurationOneDay;
	private ImageView iv_requestNewDurationThreeDay;
	private ImageView iv_requestNewDurationSevenDay;

	public ViewForMyNewRequestDurationActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_request_new_duration, null);
	}

	@Override
	protected void initViews() {
		rl_selcet12hour = (RelativeLayout)findViewById(R.id.rl_selcet_12hour);
		rl_selcet1day = (RelativeLayout)findViewById(R.id.rl_selcet_1day);
		rl_selcet3day = (RelativeLayout)findViewById(R.id.rl_selcet_3day);
		rl_selcet7day = (RelativeLayout)findViewById(R.id.rl_selcet_7day);
		
		iv_requestNewDurationTwelve = (ImageView)findViewById(R.id.iv_request_new_duration_twelve);
		iv_requestNewDurationOneDay = (ImageView)findViewById(R.id.iv_request_new_duration_one_day);
		iv_requestNewDurationThreeDay = (ImageView)findViewById(R.id.iv_request_new_duration_three_day);
		iv_requestNewDurationSevenDay = (ImageView)findViewById(R.id.iv_request_new_duration_seven_day);
		
		iv_requestNewDurationTwelve.setVisibility(ImageView.VISIBLE);
		iv_requestNewDurationOneDay.setVisibility(ImageView.INVISIBLE);
		iv_requestNewDurationThreeDay.setVisibility(ImageView.INVISIBLE);
		iv_requestNewDurationSevenDay.setVisibility(ImageView.INVISIBLE);
	}

	@Override
	protected void setEvent() {
		rl_selcet12hour.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				lockCheckButton( );
				releseCheckButton(12);
				controller.onDurationSelected(12);
			}
		});
		
		rl_selcet1day.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
						
				lockCheckButton( );
				releseCheckButton(1);
				controller.onDurationSelected(1);
			}
		});
		
		rl_selcet3day.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				lockCheckButton( );
				releseCheckButton(3);
				controller.onDurationSelected(3);
			}
		});
		
		rl_selcet7day.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				lockCheckButton( );
				releseCheckButton(7);
				controller.onDurationSelected(7);
			}
		});
	}
	
	private void lockCheckButton( )
	{
		iv_requestNewDurationTwelve.setVisibility(ImageView.INVISIBLE);
		iv_requestNewDurationOneDay.setVisibility(ImageView.INVISIBLE);
		iv_requestNewDurationThreeDay.setVisibility(ImageView.INVISIBLE);
		iv_requestNewDurationSevenDay.setVisibility(ImageView.INVISIBLE);
	}
	
	private void releseCheckButton(int aDuration)
	{
		switch(aDuration)
		{
			case 12 :
				iv_requestNewDurationTwelve.setVisibility(ImageView.VISIBLE);
				break;
			case 1 :
				iv_requestNewDurationOneDay.setVisibility(ImageView.VISIBLE);
				break;
			case 3 :
				iv_requestNewDurationThreeDay.setVisibility(ImageView.VISIBLE);
				break;
			case 7 :
				iv_requestNewDurationSevenDay.setVisibility(ImageView.VISIBLE);
				break;
			default :
				break;
		}
	}
	
	public static interface Controller
	{
		public void onDurationSelected(int aDuration);
	}
}
