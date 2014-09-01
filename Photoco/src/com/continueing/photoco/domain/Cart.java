package com.continueing.photoco.domain;

import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.ui.menu.cart_page.listview.ViewForCartListViewItem.ICartItem;

public class Cart implements ICartItem {
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_IMAGE = "image";

	private String id;
	private Image image;

	public Cart(JSONObject jsonObject) throws JSONException {
		id = jsonObject.getString(JSON_KEY_ID);
		image = new Image(jsonObject.getJSONObject(JSON_KEY_IMAGE));
	}

	public String getId() {
		return id;
	}

	public Image getImage() {
		return image;
	}
}