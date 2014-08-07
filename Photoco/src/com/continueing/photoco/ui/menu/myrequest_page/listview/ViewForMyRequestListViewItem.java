package com.continueing.photoco.ui.menu.myrequest_page.listview;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForMyRequestListViewItem extends AbstractViewForListViewItem {

	public static boolean isDeleteButtonClicked = false;
	
	private TextView tv_requestName;
	private TextView tv_requestDescDetail;
	private TextView tv_requestLocationDetail;
	private TextView tv_requestTimeleftDetail;
	private Button bt_listTagFirst;
	private Button bt_listTagSecond;
	private Button bt_listTagThird;
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
		
		tv_requestTimeleftDetail = (TextView)findViewById(R.id.tv_request_timeleft_detail);
		tv_requestDescDetail = (TextView)findViewById(R.id.tv_request_desc_detail);
		tv_requestLocationDetail = (TextView)findViewById(R.id.tv_request_location_detail);
	}

	@Override
	protected void setEvents() {
		iv_requestRemove.setOnTouchListener(new View.OnTouchListener() {	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("listener", "touch");
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
		
		int counting = 0;
		for(int i = 0; i < 3; i++)
		{
			if(iMyRequestItem.getTag()[i] == null)
						counting++;			
		}
		
		if(counting == 1)
		{
			bt_listTagFirst.setVisibility(View.VISIBLE);
			bt_listTagSecond.setVisibility(View.VISIBLE);
			bt_listTagThird.setVisibility(View.INVISIBLE);
		}
		else if(counting == 2){
			bt_listTagFirst.setVisibility(View.VISIBLE);
			bt_listTagSecond.setVisibility(View.INVISIBLE);
			bt_listTagThird.setVisibility(View.INVISIBLE);
		}
		else
		{
			bt_listTagFirst.setVisibility(View.VISIBLE);
			bt_listTagSecond.setVisibility(View.VISIBLE);
			bt_listTagThird.setVisibility(View.VISIBLE);
		}
		
		bt_listTagFirst.setText((iMyRequestItem.getTag())[0]);
		bt_listTagSecond.setText((iMyRequestItem.getTag())[1]);
		bt_listTagThird.setText((iMyRequestItem.getTag())[2]);
		tv_requestLocationDetail.setText(iMyRequestItem.getLocation());
		tv_requestTimeleftDetail.setText(iMyRequestItem.getLeftTime() +
				"(" + iMyRequestItem.getEndDate() + ")");
	}
	
	public static interface IMyRequestItem extends IListViewItem
	{
		// 뽑아낼 데이터의 메소드를 정의
		public String getName( ); 
		public String getDescription( ); 
		public String getImageURL( ); 
		public String getCategory( ); 
		public String getLocation( ); 
		public String getLeftTime( );
		public String getEndDate( );	
		public String[] getTag( ); 
	}
}