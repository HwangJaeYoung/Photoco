package com.continueing.photoco.domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IImageURL;

public class Image implements IImageURL, Serializable{
	private static final String JSON_KEY_ID = "id";
	private static final String JSON_KEY_URL = "url";
	private static final String JSON_KEY_HEIGHT = "height";
	private static final String JSON_KEY_WIDTH = "width";
	private static final String JSON_KEY_PRICE = "price";
	private static final String JSON_KEY_SIZE = "size";
	
	private String id;
	private String url;
	private String height;
	private String width;
	private String price;
	private String size;
	
	public Image(JSONObject jsonObject) throws JSONException {
		id = jsonObject.getString(JSON_KEY_ID);
		url = jsonObject.getString(JSON_KEY_URL);
		height = jsonObject.getString(JSON_KEY_HEIGHT);
		width = jsonObject.getString(JSON_KEY_WIDTH);
		price = jsonObject.getString(JSON_KEY_PRICE);
		size = jsonObject.getString(JSON_KEY_SIZE);
	}

	public String getId( ) {
		return id;
	}
	
	public String getUrl() {
		return url;
	}

	public String getHeight() {
		return height;
	}

	public String getWidth() {
		return width;
	}

	public String getPrice() {
		return price;
	}

	public String getSize() {
		return size;
	}
}