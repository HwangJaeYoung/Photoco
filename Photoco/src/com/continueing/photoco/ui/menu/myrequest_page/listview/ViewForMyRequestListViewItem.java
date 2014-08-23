package com.continueing.photoco.ui.menu.myrequest_page.listview;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Tag;
import com.continueing.photoco.domain.URL;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.loopj.android.image.SmartImageView;

public class ViewForMyRequestListViewItem extends AbstractViewForListViewItem {

	public static boolean isDeleteButtonClicked = false;
	
	private TextView tv_requestName;
	private TextView tv_requestDescDetail;
	private TextView tv_requestLocationDetail;
	private TextView tv_requestTimeleftDetail;
	private Button bt_listTagFirst;
	private Button bt_listTagSecond;
	private Button bt_listTagThird;
	private SmartImageView iv_requestPhotoLeft;
	private SmartImageView iv_requestPhotoMid;
	private SmartImageView iv_requestPhotoRight;
	private ImageView iv_requestRemove;
	
	public ViewForMyRequestListViewItem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_myrequest, this);
	}

	@Override
	protected void initViews() {
		tv_requestName = (TextView)findViewById(R.id.tv_request_name);
		bt_listTagFirst = (Button)findViewById(R.id.bt_list_tag_first);
		bt_listTagSecond = (Button)findViewById(R.id.bt_list_tag_second);
		bt_listTagThird = (Button)findViewById(R.id.bt_list_tag_third);
		
		iv_requestRemove = (ImageView)findViewById(R.id.iv_request_remove);
		
		bt_listTagFirst.setFocusable(false);
		bt_listTagSecond.setFocusable(false);
		bt_listTagThird.setFocusable(false);
		
		iv_requestPhotoLeft = (SmartImageView)findViewById(R.id.siv_request_photo_left);
		iv_requestPhotoMid = (SmartImageView)findViewById(R.id.siv_request_photo_mid);
		iv_requestPhotoRight = (SmartImageView)findViewById(R.id.siv_request_photo_right);
		
		tv_requestTimeleftDetail = (TextView)findViewById(R.id.tv_request_timeleft_detail);
		tv_requestDescDetail = (TextView)findViewById(R.id.tv_request_desc_detail);
		tv_requestLocationDetail = (TextView)findViewById(R.id.tv_request_location_detail);
	}

	@Override
	protected void setEvents() {
		iv_requestRemove.setOnTouchListener(new View.OnTouchListener() {	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				isDeleteButtonClicked = true;
				return false;
			}
		});
	}

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		// 데이터를 삽입한다.
		IMyRequestItem iMyRequestItem = (IMyRequestItem)aIListViewItem;	
		tv_requestName.setText(iMyRequestItem.getCategory());	
		tv_requestDescDetail.setText(iMyRequestItem.getDescription());
		
		int imageURLCounting = iMyRequestItem.getImageURLSet().size();
		
		Log.i("attach", imageURLCounting+"");
		
		if(imageURLCounting == 1) {
			iv_requestPhotoLeft.setImageUrl((iMyRequestItem.getImageURLSet()).get(0).getURL());
		}
		
		else if(imageURLCounting == 2) {
			iv_requestPhotoLeft.setImageUrl((iMyRequestItem.getImageURLSet()).get(0).getURL());
			iv_requestPhotoMid.setImageUrl((iMyRequestItem.getImageURLSet()).get(1).getURL());
		}
		else if(imageURLCounting > 2) {
			iv_requestPhotoLeft.setImageUrl((iMyRequestItem.getImageURLSet()).get(0).getURL());
			iv_requestPhotoMid.setImageUrl((iMyRequestItem.getImageURLSet()).get(1).getURL());
			iv_requestPhotoRight.setImageUrl((iMyRequestItem.getImageURLSet()).get(2).getURL());
		}
		
		int counting = iMyRequestItem.getTag().size();

		if(counting == 1) {
			bt_listTagFirst.setVisibility(View.VISIBLE);
			bt_listTagSecond.setVisibility(View.INVISIBLE);
			bt_listTagThird.setVisibility(View.INVISIBLE);
		}
		else if(counting == 2) {
			bt_listTagFirst.setVisibility(View.VISIBLE);
			bt_listTagSecond.setVisibility(View.VISIBLE);
			bt_listTagThird.setVisibility(View.INVISIBLE);
		}
		else {
			bt_listTagFirst.setVisibility(View.VISIBLE);
			bt_listTagSecond.setVisibility(View.VISIBLE);
			bt_listTagThird.setVisibility(View.VISIBLE);
		}
		
		bt_listTagFirst.setText((iMyRequestItem.getTag()).get(0).getTagText());
		
		if(counting == 2)
			bt_listTagSecond.setText((iMyRequestItem.getTag()).get(1).getTagText());
		else if (counting > 2){
			bt_listTagSecond.setText((iMyRequestItem.getTag()).get(1).getTagText());
			bt_listTagThird.setText((iMyRequestItem.getTag()).get(2).getTagText());
		}
		
		tv_requestLocationDetail.setText(iMyRequestItem.getLocation());
		tv_requestTimeleftDetail.setText(iMyRequestItem.getLeftTime() +
				"(" + iMyRequestItem.getEndDate() + ")");
	}
	
	public static interface IMyRequestItem extends IListViewItem {
		// 뽑아낼 데이터의 메소드를 정의
		public String getName( ); 
		public String getDescription( ); 
		public String getImageURL( ); 
		public String getCategory( ); 
		public String getLocation( ); 
		public String getLeftTime( );
		public String getEndDate( );	
		public ArrayList<Tag> getTag( ); 
		public ArrayList<URL> getImageURLSet( ); 
	}
}