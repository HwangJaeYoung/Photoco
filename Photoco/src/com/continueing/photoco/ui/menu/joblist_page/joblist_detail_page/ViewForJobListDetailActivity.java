package com.continueing.photoco.ui.menu.joblist_page.joblist_detail_page;


import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.FindingJobList;
import com.continueing.photoco.domain.Tag;
import com.continueing.photoco.reuse.etc.ReturnDurationColor;
import com.continueing.photoco.reuse.girdview.tag_gridview.ArrayAdapterForTagGridView;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.joblist_page.JobListFragment;
import com.loopj.android.image.SmartImageView;

public class ViewForJobListDetailActivity extends AbstractViewForActivity {
	private Controller controller;
	private TextView tv_joblistDetailUser;
	private TextView tv_joblistDetailUserDescription;
	private SmartImageView siv_joblistDetailUserImage;
	private TextView tv_joblistDetailCategory;
	private GridView gv_joblistDetailTag;
	private TextView tv_joblistDetailLocation;
	private TextView tv_joblistDetailLeftday;
	private TextView tv_joblistDetailCalendar;
	private ImageButton ib_submitPhoto;
	
	private String jobId;
	private ArrayAdapterForTagGridView arrayAdapterForTagGridView;
	
	public ViewForJobListDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_joblist_detail, null);
	}

	@Override
	protected void initViews() {
		tv_joblistDetailUser = (TextView)findViewById(R.id.tv_joblist_detail_user_request);
		tv_joblistDetailUserDescription = (TextView)findViewById(R.id.tv_joblist_detail_user_description);
		siv_joblistDetailUserImage = (SmartImageView)findViewById(R.id.siv_joblist_detail_user_image);
		tv_joblistDetailCategory = (TextView)findViewById(R.id.tv_joblist_detail_category);
		tv_joblistDetailLocation = (TextView)findViewById(R.id.tv_joblist_detail_location);
		tv_joblistDetailLeftday = (TextView)findViewById(R.id.tv_joblist_detail_leftday);
		tv_joblistDetailCalendar = (TextView)findViewById(R.id.tv_joblist_detail_calendar);
		ib_submitPhoto = (ImageButton)findViewById(R.id.ib_submit_photo);
		
		gv_joblistDetailTag = (GridView)findViewById(R.id.gv_joblist_detail_tag);
		arrayAdapterForTagGridView = new ArrayAdapterForTagGridView(getContext( ), 0);
		gv_joblistDetailTag.setAdapter(arrayAdapterForTagGridView);
	}
	
	public void initViewInfos(Intent anIntent) {
		FindingJobList item = (FindingJobList)anIntent.getSerializableExtra(JobListFragment.PARAM_JOBLIST_ITEM_KEY);
		
		tv_joblistDetailUser.setText(item.getName().getUserName() + "'s Request");
		tv_joblistDetailUserDescription.setText(item.getDescription());
		siv_joblistDetailUserImage.setImageUrl(item.getImageURL().getUrl());
		tv_joblistDetailCategory.setText(item.getCategory().getName());
		tv_joblistDetailLocation.setText(item.getLocation().getDescription());
		tv_joblistDetailLeftday.setText(item.getLeftTime());
		tv_joblistDetailCalendar.setText(item.getEndDate());
		ib_submitPhoto = (ImageButton)findViewById(R.id.ib_submit_photo);
		
		ArrayList<Tag> tags = new ArrayList<Tag>( );
		tags = item.getTag();
		Log.i("tags", tags.size()+"");
		arrayAdapterForTagGridView.addAll(tags);	
		
		jobId = item.getJobId();
		
		tv_joblistDetailLeftday.setTextColor(Color.parseColor(ReturnDurationColor.returnColor(item.getRemainMinutes())));
	}

	@Override
	protected void setEvent() {
		ib_submitPhoto.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Log.i("attach", jobId+"");
				controller.onPhotoSubmit(jobId);
			}
		});
	}
	
	public static interface Controller {
		public void onPhotoSubmit(String aJobId);
	}
}