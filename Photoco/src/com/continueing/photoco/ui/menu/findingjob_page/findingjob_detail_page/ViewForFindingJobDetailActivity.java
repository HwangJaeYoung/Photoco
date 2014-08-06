package com.continueing.photoco.ui.menu.findingjob_page.findingjob_detail_page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.FindingJobList;
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
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_findingjob_detail, null);
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
	}

	@Override
	protected void setEvent() { }
	
	public void initViewInfos(Intent anIntent)
	{
		FindingJobList item = (FindingJobList)anIntent.getSerializableExtra("itemfileds");
		
		tv_timeLeft.setText(item.getLeftTime());
	}
	
	public static interface Controller {

		
	}
}