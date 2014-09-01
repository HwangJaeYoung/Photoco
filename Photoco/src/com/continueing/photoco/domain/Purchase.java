package com.continueing.photoco.domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.ui.menu.myaccount_page.listview.ViewForMyAccountListViewitem.IMyAccountItem;

public class Purchase implements IMyAccountItem, Serializable{
	private static final String JSON_KEY_SELLER = "seller";
	private static final String JSON_KEY_BUYER = "buyer";
	private static final String JSON_KEY_IMAGE = "image";
	private static final String JSON_KEY_CREATE_TIME = "created_date_time";
	
	private int savedTabIndex;
	private BuyerSeller seller;
	private BuyerSeller buyer;
	private Image image;
	private String createTime;

	public Purchase(JSONObject aJsonObject, int aSavedTabIndex) throws JSONException {
		savedTabIndex = aSavedTabIndex;
		image = new Image(aJsonObject.getJSONObject(JSON_KEY_IMAGE));
		seller = new BuyerSeller(aJsonObject.getJSONObject(JSON_KEY_SELLER));
		buyer = new BuyerSeller(aJsonObject.getJSONObject(JSON_KEY_BUYER));
		createTime = aJsonObject.getString(JSON_KEY_CREATE_TIME);
	}

	@Override
	public int getTabIndex( ) {
		return savedTabIndex;
	}
	
	@Override
	public Image getImage() {
		return image;
	}
	
	@Override
	public BuyerSeller getSeller() {
		return seller;
	}
	
	@Override
	public BuyerSeller getBuyer() {
		return buyer;
	}
	
	@Override
	public String getCreateTime( ) {
		return createTime;
	}
}