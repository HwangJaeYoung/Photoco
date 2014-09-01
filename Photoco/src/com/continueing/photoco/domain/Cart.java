package com.continueing.photoco.domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.ui.menu.cart_page.listview.ViewForCartListViewItem.ICartItem;

public class Cart implements ICartItem, Serializable {
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_IMAGE = "image";

	private String id;
	private Image image;

	public Cart(JSONObject jsonObject) throws JSONException {
		id = jsonObject.getString(JSON_KEY_ID);
		image = new Image(jsonObject.getJSONObject(JSON_KEY_IMAGE));
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public Image getImage() {
		return image;
	}
}