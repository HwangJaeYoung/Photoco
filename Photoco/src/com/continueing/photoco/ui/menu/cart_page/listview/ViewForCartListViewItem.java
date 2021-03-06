package com.continueing.photoco.ui.menu.cart_page.listview;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Image;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.loopj.android.image.SmartImageView;

public class ViewForCartListViewItem extends AbstractViewForListViewItem {
	
	private SmartImageView siv_listCartImage;
	private TextView tv_listCartCategory;
	private TextView tv_listCartUsername;
	private Button bt_listCartTagFirst;
	private Button bt_listCartTagSecond;
	private Button bt_listCartTagThird;
	private Button bt_listCartSize;
	private TextView tv_listCartPrice;
	
	public ViewForCartListViewItem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_cart, this);
	}

	@Override
	protected void initViews() {
		siv_listCartImage = (SmartImageView)findViewById(R.id.siv_list_cart_image);
		tv_listCartCategory = (TextView)findViewById(R.id.tv_list_cart_category);
		tv_listCartUsername = (TextView)findViewById(R.id.tv_list_cart_username);
		bt_listCartTagFirst = (Button)findViewById(R.id.bt_list_cart_tag_first);
		bt_listCartTagSecond = (Button)findViewById(R.id.bt_list_cart_tag_second);
		bt_listCartTagThird = (Button)findViewById(R.id.bt_list_cart_tag_third);
		bt_listCartSize = (Button)findViewById(R.id.bt_list_cart_size);
		tv_listCartPrice = (TextView)findViewById(R.id.tv_list_cart_price);
	}

	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) { 
		ICartItem iCartItem = (ICartItem)aIListViewItem;
		
		siv_listCartImage.setImageUrl(iCartItem.getImage().getThumnailUrl());
		tv_listCartCategory.setText(iCartItem.getImage().getCategory().getName());
		tv_listCartUsername.setText(iCartItem.getImage().getUserProfile().getUserName() + "'s Request");
		
		// 태그의 수를 계산하여 표시의 범위를 제한한다.
		int counting = iCartItem.getImage().getTag().size();

		// 버튼의 제한
		if(counting == 1) {
			bt_listCartTagFirst.setVisibility(View.VISIBLE);
			bt_listCartTagSecond.setVisibility(View.INVISIBLE);
			bt_listCartTagThird.setVisibility(View.INVISIBLE);
		}
		else if(counting == 2) {
			bt_listCartTagFirst.setVisibility(View.VISIBLE);
			bt_listCartTagSecond.setVisibility(View.VISIBLE);
			bt_listCartTagThird.setVisibility(View.INVISIBLE);
		}
		else {
			bt_listCartTagFirst.setVisibility(View.VISIBLE);
			bt_listCartTagSecond.setVisibility(View.VISIBLE);
			bt_listCartTagThird.setVisibility(View.VISIBLE);
		}
		
		// 표시하는 내용의 제한
		bt_listCartTagFirst.setText((iCartItem.getImage().getTag()).get(0).getTagText());
		
		if(counting == 2)
			bt_listCartTagSecond.setText((iCartItem.getImage().getTag()).get(1).getTagText());
		else if (counting > 2){
			bt_listCartTagSecond.setText((iCartItem.getImage().getTag()).get(1).getTagText());
			bt_listCartTagThird.setText((iCartItem.getImage().getTag()).get(2).getTagText());
		}
		
		bt_listCartSize.setText(iCartItem.getImage().getSize());
		tv_listCartPrice.setText(iCartItem.getImage().getPrice());
	}
	
	public static interface ICartItem extends IListViewItem {
		public String getId();
		public Image getImage();
	}
}