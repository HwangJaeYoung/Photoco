package com.continueing.photoco.ui.menu.joblist_page.joblist_detail_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForJobListDetailActivity extends AbstractViewForActivity {

	private Controller controller;
	private ImageButton ib_submitPhoto;
	
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
		ib_submitPhoto = (ImageButton)findViewById(R.id.ib_submit_photo);
		
	}

	@Override
	protected void setEvent() {
		ib_submitPhoto.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				controller.onPhotoSubmit( );
			}
		});
	}
	
	public static interface Controller {
		public void onPhotoSubmit( );
	}
}