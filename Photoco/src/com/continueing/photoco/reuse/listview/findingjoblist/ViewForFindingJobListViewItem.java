package com.continueing.photoco.reuse.listview.findingjoblist;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForFindingJobListViewItem extends AbstractViewForListViewItem{
	private IFindingJobListItem iFindingJobListItem;
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
		tv_category.setText(iFindingJobListItem.getCategory());	
		tv_name.setText(iFindingJobListItem.getName( ));
		
		int counting = 0;
		for(int i = 0; i < 3; i++)
		{
			if(iFindingJobListItem.getTag()[i] == null)
						counting++;			
		}
		
		Log.i("counting",counting+"");
		
		if(counting == 1)
		{
			bt_tagFirst.setVisibility(View.VISIBLE);
			bt_tagSecond.setVisibility(View.VISIBLE);
			bt_tagThird.setVisibility(View.INVISIBLE);
		}
		else if(counting == 2){
			bt_tagFirst.setVisibility(View.VISIBLE);
			bt_tagSecond.setVisibility(View.INVISIBLE);
			bt_tagThird.setVisibility(View.INVISIBLE);
		}
		else
		{
			bt_tagFirst.setVisibility(View.VISIBLE);
			bt_tagSecond.setVisibility(View.VISIBLE);
			bt_tagThird.setVisibility(View.VISIBLE);
		}
		
		bt_tagFirst.setText((iFindingJobListItem.getTag())[0]);
		bt_tagSecond.setText((iFindingJobListItem.getTag())[1]);
		bt_tagThird.setText((iFindingJobListItem.getTag())[2]);
		tv_leftTime.setText(iFindingJobListItem.getLeftTime());;	
	}
	
	/* IRequestAbstractInfoItem를 사용하여 Model에서 이 인터페이스를 구현한 객체와 통신한다.
	 * 한 번더 IRequestAbstractInfoItem의 구현을 강요하여 IListViewItem을 직접 구현 한것 보다 더 유연성을 준다.
	 */
	public static interface IFindingJobListItem extends IListViewItem {
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