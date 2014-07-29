package com.continueing.photoco.reuse.listview.FindingJobList;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForFindingJobListViewItem extends AbstractViewForListViewItem{
	private IFindingJobListItem iFindingJobListItem;
	private TextView tv_category;
	private TextView tv_name;
	private ImageView bt_tagFirst;
	private ImageView bt_tagSecond;
	private ImageView bt_tagThird;
	private TextView tv_leftTime;
	
	public ViewForFindingJobListViewItem(Context context) {
		super(context);
	}

	// ListView의 아이템들을 inflate한다.
	@Override
	protected View inflate() {
		/* 여기에서는 this를 인자로 주면서 인플레이트한 item_request_abstract_info를 뷰 그룹에 붙이게 된다. */
		return inflate(getContext( ), R.layout.item_finding_job_list, this);
	}

	@Override
	protected void initViews() {
		tv_category = (TextView)findViewById_(R.id.tv_listv_group);
		tv_name = (TextView)findViewById_(R.id.tv_list_name);
		bt_tagFirst = (ImageView)findViewById_(R.id.bt_list_tag_first);
		bt_tagSecond =(ImageView)findViewById_(R.id.bt_list_tag_second);
		bt_tagThird =  (ImageView)findViewById_(R.id.bt_list_tag_third);
		tv_leftTime = (TextView)findViewById_(R.id.tv_left_time);
	}
	
	// 여기서는 아이템내에서 따로 발생하는 이벤트가 없다.
	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		iFindingJobListItem = (IFindingJobListItem)aIListViewItem;
		tv_category.setText(iFindingJobListItem.getCategory());	
	}
	
	/* IRequestAbstractInfoItem를 사용하여 Model에서 이 인터페이스를 구현한 객체와 통신한다.
	 * 한 번더 IRequestAbstractInfoItem의 구현을 강요하여 IListViewItem을 직접 구현 한것 보다 더 유연성을 준다.
	 */
	public static interface IFindingJobListItem extends IListViewItem {
		public String getCategory( ); // 카데고리 이름
		public String getName( ); // 사용자 이름
		public String getTagFirst( ); // 첫번째 태그
		public String getTagSecond( ); // 두번째 태그
		public String getTagThird( ); // 세번째 태그
		public int getLeftTime( ); // 남은 시간
	}
}