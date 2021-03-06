package com.continueing.photoco.reuse.listview.findingjoblist;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Category;
import com.continueing.photoco.domain.Image;
import com.continueing.photoco.domain.Location;
import com.continueing.photoco.domain.Tag;
import com.continueing.photoco.domain.UserProfile;
import com.continueing.photoco.reuse.etc.ReturnDurationColor;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForFindingJobListViewItem extends AbstractViewForListViewItem{
	private IFindingJobListItem iFindingJobListItem;
	private LinearLayout ll_findjoblistSmileImage;
	private TextView tv_category;
	private TextView tv_name;
	private Button bt_tagFirst;
	private Button bt_tagSecond;
	private Button bt_tagThird;
	private TextView tv_leftTime;
	
	public ViewForFindingJobListViewItem(Context context) {
		super(context);
	}

	// ListView의 아이템들을 inflate한다.
	@Override
	protected View inflate() {
		/* 여기에서는 this를 인자로 주면서 인플레이트한 item_request_abstract_info를 뷰 그룹에 붙이게 된다. */
		return inflate(getContext( ), R.layout.item_findingjoblist, this);
	}

	@Override
	protected void initViews() {
		ll_findjoblistSmileImage = (LinearLayout)findViewById(R.id.ll_findjoblist_smile_image);
		tv_category = (TextView)findViewById_(R.id.tv_listv_group);
		tv_name = (TextView)findViewById_(R.id.tv_list_name);
		tv_leftTime = (TextView)findViewById_(R.id.tv_left_time);
		bt_tagFirst = (Button)findViewById_(R.id.bt_list_tag_first);
		bt_tagSecond =(Button)findViewById_(R.id.bt_list_tag_second);
		bt_tagThird =  (Button)findViewById_(R.id.bt_list_tag_third);
		
		bt_tagFirst.setFocusable(false);
		bt_tagSecond.setFocusable(false);
		bt_tagThird.setFocusable(false);
	}
	
	// 여기서는 아이템내에서 따로 발생하는 이벤트가 없다.
	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		iFindingJobListItem = (IFindingJobListItem)aIListViewItem;
		tv_category.setText(iFindingJobListItem.getCategory().getName());	
		tv_name.setText(iFindingJobListItem.getName( ).getUserName());
		
		int counting = iFindingJobListItem.getTag().size();

		if(counting == 1) {
			bt_tagFirst.setVisibility(View.VISIBLE);
			bt_tagSecond.setVisibility(View.INVISIBLE);
			bt_tagThird.setVisibility(View.INVISIBLE);
		}
		else if(counting == 2) {
			bt_tagFirst.setVisibility(View.VISIBLE);
			bt_tagSecond.setVisibility(View.VISIBLE);
			bt_tagThird.setVisibility(View.INVISIBLE);
		}
		else {
			bt_tagFirst.setVisibility(View.VISIBLE);
			bt_tagSecond.setVisibility(View.VISIBLE);
			bt_tagThird.setVisibility(View.VISIBLE);
		}
		
		bt_tagFirst.setText((iFindingJobListItem.getTag()).get(0).getTagText());
		
		if(counting == 2)
			bt_tagSecond.setText((iFindingJobListItem.getTag()).get(1).getTagText());
		else if (counting > 2){
			bt_tagSecond.setText((iFindingJobListItem.getTag()).get(1).getTagText());
			bt_tagThird.setText((iFindingJobListItem.getTag()).get(2).getTagText());
		}
		
		tv_leftTime.setText(iFindingJobListItem.getLeftTime() + " left");
		Log.i("obj", iFindingJobListItem.getRemainMinutes()+"");
		ll_findjoblistSmileImage.setBackgroundColor(Color.parseColor(ReturnDurationColor.returnColor(iFindingJobListItem.getRemainMinutes())));
	}
	
	/* IRequestAbstractInfoItem를 사용하여 Model에서 이 인터페이스를 구현한 객체와 통신한다.
	 * 한 번더 IRequestAbstractInfoItem의 구현을 강요하여 IListViewItem을 직접 구현 한것 보다 더 유연성을 준다.
	 */
	public static interface IFindingJobListItem extends IListViewItem {
		public String getJobId( );
		public String getId( );
		public UserProfile getName( ); 
		public String getDescription( ); 
		public Image getImageURL( ); 
		public Category getCategory( ); 
		public Location getLocation( ); 
		public String getLeftTime( );
		public String getEndDate( );	
		public ArrayList<Tag> getTag( ); 
		public String getRemainMinutes( );
	}
}