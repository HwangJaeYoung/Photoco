package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForMyNewRequestActivity extends AbstractViewForActivity {

	private Controller controller;
	private Button bt_requestNewSubmit;
	private ImageView bt_imageView;
	private RelativeLayout rl_requestLocation;
	private TextView tv_requestNewLocationDetail;
	private RelativeLayout rl_selectDuration;
	private TextView tv_requestNewDurationDetailDay;
	private TextView tv_requestNewDurationDetailCalendar;
	
	public ViewForMyNewRequestActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_request_new, null);
	}

	@Override
	protected void initViews() {
		bt_requestNewSubmit = (Button)findViewById(R.id.bt_request_new_submit);
		tv_requestNewLocationDetail = (TextView)findViewById(R.id.tv_request_new_location_detail);
		bt_imageView = (ImageView)findViewById(R.id.bt_image_view);
		
		rl_requestLocation = (RelativeLayout)findViewById(R.id.rl_request_location);
		rl_selectDuration = (RelativeLayout)findViewById(R.id.rl_select_duration);
		tv_requestNewDurationDetailDay = (TextView)findViewById(R.id.tv_request_new_duration_detail_day);
		tv_requestNewDurationDetailCalendar = (TextView)findViewById(R.id.tv_request_new_duration_detail_calendar);
	}

	@Override
	protected void setEvent() {
		bt_requestNewSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onSubmit();
			}
		});
		
		rl_requestLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onLocationSelect();
			}
		});
		
		bt_imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onSelectGallery();				
			}
		});
		
		rl_selectDuration.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onSelectDuration();				
			}
		});
	}
	
	public void selectedLocation(String aLocation)
	{
		tv_requestNewLocationDetail.setText(aLocation);
	}
	
	public void selectedImage(Bitmap aBitmap)
	{
		bt_imageView.setImageBitmap(aBitmap);
	}
	
	public void selectedDuration(int aDuration)
	{
		if(aDuration == 12)
			tv_requestNewDurationDetailDay.setText(aDuration + "Hours");
		else
			tv_requestNewDurationDetailDay.setText(aDuration + "Day");
		
		Calendar currentDate = Calendar.getInstance();
		int year = currentDate.get(Calendar.YEAR);
		int month = currentDate.get(Calendar.MONTH) + 1;
		int date = currentDate.get(Calendar.DATE);
		
		tv_requestNewDurationDetailCalendar.setText(year+"/"+month+"/"+date);
	}
	
	public static interface Controller
	{
		public void onSubmit( );
		public void onLocationSelect( );
		public void onSelectGallery( );
		public void onSelectDuration();
	}
}
