package com.continueing.photoco.ui.menu.myaccount_page.listview;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.BuyerSeller;
import com.continueing.photoco.domain.Image;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;
import com.loopj.android.image.SmartImageView;

public class ViewForMyAccountListViewitem extends AbstractViewForListViewItem{

	SmartImageView siv_listAccount;
	TextView tv_listAccountName;
	TextView tv_listAccountDate;
	Button bt_listCartUnit;
	
	public ViewForMyAccountListViewitem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_account, this);
	}

	@Override
	protected void initViews() {
		siv_listAccount = (SmartImageView)findViewById(R.id.siv_list_account);
		tv_listAccountName = (TextView)findViewById(R.id.tv_list_account_name);
		tv_listAccountDate = (TextView)findViewById(R.id.tv_list_account_date);
		bt_listCartUnit = (Button)findViewById(R.id.bt_list_cart_unit);
	}

	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		IMyAccountItem iMyAccountItem = (IMyAccountItem)aIListViewItem;
		
		int tabIndex = iMyAccountItem.getTabIndex();
		
		BuyerSeller itemBuyerSeller = null;
		
		if(tabIndex == 0) {
			itemBuyerSeller = iMyAccountItem.getBuyer();
			bt_listCartUnit.setText("Sold");
		}
		else if(tabIndex == 1) {
			itemBuyerSeller = iMyAccountItem.getSeller();
			bt_listCartUnit.setText("Buy");
		}
		else if(tabIndex == 3) {
			
		}
		
		siv_listAccount.setImageUrl(iMyAccountItem.getImage().getUrl());
		tv_listAccountName.setText(itemBuyerSeller.getUserName());
		tv_listAccountDate.setText(iMyAccountItem.getCreateTime());
	}
	
	public static interface IMyAccountItem extends IListViewItem {
		public int getTabIndex( );
		public Image getImage();
		public BuyerSeller getSeller();
		public BuyerSeller getBuyer();
		public String getCreateTime( );
	}
}
