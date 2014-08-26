package com.continueing.photoco.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class Image {

	private String url;
	private String height;
	private String width;
	private String price;
	private String size;
	
	public Image(JSONObject jsonObject) throws JSONException {
		url = jsonObject.getString("url");
		height = jsonObject.getString("height");
		width = jsonObject.getString("width");
		price = jsonObject.getString("price");
		size = jsonObject.getString("size");
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

