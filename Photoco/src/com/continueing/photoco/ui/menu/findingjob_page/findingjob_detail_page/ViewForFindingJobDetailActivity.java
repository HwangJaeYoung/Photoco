package com.continueing.photoco.ui.menu.findingjob_page.findingjob_detail_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.FindingJobList;
import com.continueing.photoco.domain.Tag;
import com.continueing.photoco.reuse.etc.ReturnDurationColor;
import com.continueing.photoco.reuse.girdview.tag_gridview.ArrayAdapterForTagGridView;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.findingjob_page.FindingJobFragment;
import com.loopj.android.image.SmartImageView;

public class ViewForFindingJobDetailActivity extends AbstractViewForActivity {

	private Controller controller;
	private TextView tv_userRequest;
	private TextView tv_findingJobListDescription;
	private SmartImageView siv_findingJobListDetailImage;
	private TextView tv_detailCategory;
	private TextView tv_location;
	private TextView tv_timeLeftDetailDay;
	private TextView tv_timeLeftDetailCalendar;
	private GridView gv_findingjobTag;
	private ArrayAdapterForTagGridView arrayAdapterForTagGridView;
	private Button bt_participate;
	private String requestId;
	
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
		tv_findingJobListDescription = (TextView)findViewById(R.id.tv_finding_job_list_detail_description);
		siv_findingJobListDetailImage = (SmartImageView)findViewById(R.id.siv_finding_job_list_detail_image);
		tv_detailCategory = (TextView)findViewById(R.id.tv_finding_job_detail_category);
		tv_location = (TextView)findViewById(R.id.tv_finding_job_detail_location);
		tv_timeLeftDetailDay = (TextView)findViewById(R.id.tv_finding_job_detail_timeleft_detail_day);
		tv_timeLeftDetailCalendar = (TextView)findViewById(R.id.tv_finding_job_detail_timeleft_detail_calendar);
	    bt_participate = (Button)findViewById(R.id.bt_finding_job_detail_participate);
	    
	    gv_findingjobTag = (GridView)findViewById(R.id.gv_finding_job_tag);
		arrayAdapterForTagGridView = new ArrayAdapterForTagGridView(getContext( ), 0);
	    gv_findingjobTag.setAdapter(arrayAdapterForTagGridView);
	}

	@Override
	protected void setEvent() { 
		bt_participate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onParticipate(requestId);
			}
		});	
	}
	
	public void initViewInfos(Intent anIntent)
	{
		FindingJobList item = (FindingJobList)anIntent.getSerializableExtra(FindingJobFragment.PARAM_FINDINGJOB_ITEM_KEY);
		
		tv_userRequest.setText(item.getName().getUserName() + "'s Request");
		tv_findingJobListDescription.setText(item.getDescription());
		siv_findingJobListDetailImage.setImageUrl(item.getImageURL().getUrl());
		tv_detailCategory.setText(item.getCategory().getName());
		tv_location.setText(item.getLocation().getDescription());
		tv_timeLeftDetailDay.setText(item.getLeftTime());
		tv_timeLeftDetailCalendar.setText(item.getEndDate());
		
		ArrayList<Tag> tags = new ArrayList<Tag>( );
		tags = item.getTag();
		arrayAdapterForTagGridView.addAll(tags);	
		
		tv_timeLeftDetailDay.setTextColor(Color.parseColor(ReturnDurationColor.returnColor(item.getRemainMinutes())));
		requestId = item.getId();
	}
	
	public static interface Controller {
		public void onParticipate(String aRequestId);		
	}
}