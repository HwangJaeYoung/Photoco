package com.continueing.photoco.domain;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Image implements Serializable{

	private static final String JSON_KEY_url = "url";
	private static final String JSON_KEY_height = "height";
	private static final String JSON_KEY_width = "width";
	private static final String JSON_KEY_price = "price";
	private static final String JSON_KEY_size = "size";
	
	private String url;
	private String height;
	private String width;
	private String price;
	private String size;
	
	public Image(JSONObject jsonObject) throws JSONException {
		url = jsonObject.getString(JSON_KEY_url);
		height = jsonObject.getString(JSON_KEY_height);
		width = jsonObject.getString(JSON_KEY_width);
		price = jsonObject.getString(JSON_KEY_price);
		size = jsonObject.getString(JSON_KEY_size);
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

