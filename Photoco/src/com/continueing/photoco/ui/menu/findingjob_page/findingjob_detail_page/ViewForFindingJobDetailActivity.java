package com.continueing.photoco.ui.menu.findingjob_page.findingjob_detail_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.FindingJobList;
import com.continueing.photoco.domain.Tag;
import com.continueing.photoco.reuse.girdview.tag_gridview.ArrayAdapterForTagGridView;
import com.continueing.photoco.reuse.girdview.tag_gridview.ViewForArrayAdapterForTagGridView.IMyRequestTagItem;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
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
		tv_findingJobListDescription = (TextView)findViewById(R.id.tv_finding_job_list_detail_description);
		siv_findingJobListDetailImage = (SmartImageView)findViewById(R.id.siv_finding_job_list_detail_image);
		tv_detailCategory = (TextView)findViewById(R.id.tv_finding_job_detail_category);
		tv_location = (TextView)findViewById(R.id.tv_finding_job_detail_location);
		tv_timeLeftDetailDay = (TextView)findViewById(R.id.tv_finding_job_detail_timeleft_detail_day);
		tv_timeLeftDetailCalendar = (TextView)findViewById(R.id.tv_finding_job_detail_timeleft_detail_calendar);
		bt_hide = (Button)findViewById(R.id.bt_finding_job_detail_hide);
	    bt_participate = (Button)findViewById(R.id.bt_finding_job_detail_participate);
	    
	    gv_findingjobTag = (GridView)findViewById(R.id.gv_finding_job_tag);
		arrayAdapterForTagGridView = new ArrayAdapterForTagGridView(getContext( ), 0);
	    gv_findingjobTag.setAdapter(arrayAdapterForTagGridView);
	}

	@Override
	protected void setEvent() { }
	
	public void initViewInfos(Intent anIntent)
	{
		FindingJobList item = (FindingJobList)anIntent.getSerializableExtra("itemfileds");
		
		tv_userRequest.setText(item.getName()+"'s Request");
		tv_findingJobListDescription.setText(item.getDescription());
		siv_findingJobListDetailImage.setImageUrl(item.getImageURL());
		tv_detailCategory.setText(item.getCategory());
		tv_location.setText(item.getLocation());
		tv_timeLeftDetailDay.setText(item.getLeftTime());
		tv_timeLeftDetailCalendar.setText(item.getEndDate());
		
		ArrayList<IMyRequestTagItem> tags = new ArrayList<IMyRequestTagItem>( );
		for(int i = 0; i < 10; i++)
		{
			String temp = item.getTag()[i];
			if(temp != null)
			{
				Tag tag = new Tag( );
				tag.setTagText(temp);
				tags.add(tag);
			}
		}
		arrayAdapterForTagGridView.addAll(tags);	
	}
	
	public static interface Controller {

		
	}
}