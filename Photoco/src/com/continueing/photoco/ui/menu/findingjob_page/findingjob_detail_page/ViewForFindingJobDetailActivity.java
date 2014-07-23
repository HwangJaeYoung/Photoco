package com.continueing.photoco.ui.menu.findingjob_page.findingjob_detail_page;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForFindingJobDetailActivity extends AbstractViewForActivity {

	private Controller controller;
	private TextView tv_userRequest;
	private TextView tv_detailCategory;
	private TextView tv_tag;
	private TextView tv_location;
	private TextView tv_locationDetail;
	private TextView tv_timeLeft;
	private TextView tv_timeLeftDetailDay;
	private TextView tv_timeLeftDetailCalendar;
	private Button bt_hide;
	private Button bt_participate;
	
	public ViewForFindingJobDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(
				R.layout.activity_finding_job_detail, null);
	}

	@Override
	protected void initViews() {
		tv_userRequest = (TextView)findViewById(R.id.tv_finding_job_detail_user_request);
		tv_detailCategory = (TextView)findViewById(R.id.tv_finding_job_detail_category);
		tv_tag = (TextView)findViewById(R.id.tv_finding_job_detail_tag);
		tv_location = (TextView)findViewById(R.id.tv_finding_job_detail_location);
		tv_locationDetail = (TextView)findViewById(R.id.tv_finding_job_detail_location_detail);
		tv_timeLeft = (TextView)findViewById(R.id.tv_finding_job_detail_timeleft);
		tv_timeLeftDetailDay = (TextView)findViewById(R.id.tv_finding_job_detail_timeleft_detail_day);
		tv_timeLeftDetailCalendar = (TextView)findViewById(R.id.tv_finding_job_detail_timeleft_detail_calendar);
		bt_hide = (Button)findViewById(R.id.bt_finding_job_detail_hide);
	    bt_participate = (Button)findViewById(R.id.bt_finding_job_detail_participate);

		Typeface type = Typeface.createFromAsset(getContext().getAssets(), "BreeSerif_Reg.otf");
		
		tv_userRequest.setTypeface(type);
		tv_detailCategory.setTypeface(type);
		tv_tag.setTypeface(type);
		tv_location.setTypeface(type);
		tv_locationDetail.setTypeface(type);
		tv_timeLeft.setTypeface(type);
		tv_timeLeftDetailDay.setTypeface(type);
		tv_timeLeftDetailCalendar.setTypeface(type);
		bt_hide.setTypeface(type);
		bt_participate.setTypeface(type);
	}

	@Override
	protected void setEvent() {

		

	}
	
	public static interface Controller {

		
	}
}