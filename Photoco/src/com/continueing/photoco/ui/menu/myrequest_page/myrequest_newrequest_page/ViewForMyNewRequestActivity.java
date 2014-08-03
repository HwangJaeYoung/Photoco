package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.etc.ReturnDurationColor;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.reuse.widget.SubmitButton;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.gridview.ArrayAdapterForMyRequestTagGrid;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class ViewForMyNewRequestActivity extends AbstractViewForActivity {

	private Controller controller;
	private SubmitButton sb_requestNewSubmit;
	private Button bt_requestNewTagDetail;
	private ImageView bt_imageView;
	private TextView tv_requestNewLocationDetail;
	private RelativeLayout rl_requestLocation;
	private RelativeLayout rl_selectDuration;
	private RelativeLayout rl_selectCategory;
	private RelativeLayout rl_requestTag;
	private TextView tv_requestNewDurationDetailDay;
	private TextView tv_requestNewDurationDetailCalendar;
	private TextView tv_requestNewCategoryDetail;
	private TextView tv_requestNewTagDetail;
	private TextView tv_requestNewAdd;
	private EditText et_requestNew;
	private GridView gv_requestTag;
	private ArrayAdapterForMyRequestTagGrid arrayAdapterForMyRequestTagGrid;
	
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
		sb_requestNewSubmit = (SubmitButton)findViewById(R.id.sb_request_new_submit);
		tv_requestNewLocationDetail = (TextView)findViewById(R.id.tv_request_new_location_detail);
		bt_imageView = (ImageView)findViewById(R.id.bt_image_view);
		
		rl_selectCategory = (RelativeLayout)findViewById(R.id.rl_request_cateory);
		rl_requestLocation = (RelativeLayout)findViewById(R.id.rl_request_location);
		rl_selectDuration = (RelativeLayout)findViewById(R.id.rl_select_duration);
		rl_requestTag = (RelativeLayout)findViewById(R.id.rl_request_tag);
		
		tv_requestNewDurationDetailDay = (TextView)findViewById(R.id.tv_request_new_duration_detail_day);
		tv_requestNewDurationDetailCalendar = (TextView)findViewById(R.id.tv_request_new_duration_detail_calendar);
		tv_requestNewCategoryDetail = (TextView)findViewById(R.id.tv_request_new_category_detail);
	
		et_requestNew = (EditText)findViewById(R.id.et_request_new);
		
		bt_requestNewTagDetail = (Button)findViewById(R.id.bt_request_new_tag_detail);
		tv_requestNewTagDetail = (TextView)findViewById(R.id.tv_request_new_tag_detail);
		
		tv_requestNewAdd = (TextView)findViewById(R.id.tv_request_new_add);
		
		
		sb_requestNewSubmit.init((ProgressBar)findViewById(R.id.pg_request_submit));
		sb_requestNewSubmit.addViewToHold(et_requestNew);
		sb_requestNewSubmit.addViewToHold(bt_imageView);
		sb_requestNewSubmit.addViewToHold(rl_requestTag);
		sb_requestNewSubmit.addViewToHold(rl_selectCategory);
		sb_requestNewSubmit.addViewToHold(rl_requestLocation);
		sb_requestNewSubmit.addViewToHold(rl_selectDuration);
		
		gv_requestTag = (GridView)findViewById(R.id.gv_request_tag);	
		arrayAdapterForMyRequestTagGrid = new ArrayAdapterForMyRequestTagGrid(getContext( ), 0);
		gv_requestTag.setAdapter(arrayAdapterForMyRequestTagGrid);	
	}

	@Override
	protected void setEvent() {
		sb_requestNewSubmit.setOnClickListener(new View.OnClickListener() {
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
		
		rl_selectCategory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onSelectCategory();
			}
		});
		
		rl_requestTag.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				controller.onSelectTag();
			}
		});
		
		et_requestNew.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) { }
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			
			@Override
			public void afterTextChanged(Editable s) { }
		});
	}
	
	public void selectedLocation(String aLocation)
	{
		tv_requestNewLocationDetail.setText(aLocation);
	}
	
	public void selectedCategory(String aCategory)
	{
		tv_requestNewCategoryDetail.setText(aCategory);
	}
	
	public void selectedImage(Bitmap aBitmap)
	{
		tv_requestNewAdd.setVisibility(View.INVISIBLE);
		bt_imageView.setImageBitmap(aBitmap);
	}
	
	public String getDescription( )
	{
		return et_requestNew.getText().toString();
	}
	
	public void selectedDuration(String aDuration, String aEndDate, String aDurationHour)
	{
		tv_requestNewDurationDetailDay.setText(aDuration);
		tv_requestNewDurationDetailDay.setTextColor(Color.parseColor(ReturnDurationColor.returnColor(aDurationHour)));
		tv_requestNewDurationDetailCalendar.setText(aEndDate);
	}
	
	public void selectedTag(ArrayList<IMyRequestTagItem> items)
	{
		bt_requestNewTagDetail.setVisibility(View.INVISIBLE);
		tv_requestNewTagDetail.setVisibility(View.INVISIBLE);
		arrayAdapterForMyRequestTagGrid.clear();
		arrayAdapterForMyRequestTagGrid.addAll(items);
	}
	
	public void releaseSubmitButton()
    {
        this.sb_requestNewSubmit.release();
    }	
	
	public static interface Controller
	{
		public void onSubmit( );
		public void onLocationSelect( );
		public void onSelectGallery( );
		public void onSelectTag( );
		public void onSelectCategory( );
		public void onSelectDuration();
	}
}