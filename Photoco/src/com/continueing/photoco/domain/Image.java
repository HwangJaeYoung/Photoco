package com.continueing.photoco.domain;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Image implements Serializable{

	private static final String JSON_KEY_URL = "url";
	private static final String JSON_KEY_HEIGHT = "height";
	private static final String JSON_KEY_WIDTH = "width";
	private static final String JSON_KEY_PRICE = "price";
	private static final String JSON_KEY_SIZE = "size";
	
	private String url;
	private String height;
	private String width;
	private String price;
	private String size;
	
	public Image(JSONObject jsonObject) throws JSONException {
		url = jsonObject.getString(JSON_KEY_URL);
		height = jsonObject.getString(JSON_KEY_HEIGHT);
		width = jsonObject.getString(JSON_KEY_WIDTH);
		price = jsonObject.getString(JSON_KEY_PRICE);
		size = jsonObject.getString(JSON_KEY_SIZE);
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
	
	public ArrayList<URL> getURLSet() {
		JSONArray tempURL = null;
		try {
			tempURL = new JSONArray(url);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		ArrayList<URL> urls = new ArrayList<URL>( );
		for(int i = 0; i < tempURL.length(); i++) {
			try {
				String temp = tempURL.getJSONObject(i).getString("url");
				URL url = new URL(temp);
				urls.add(url);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return urls;
	}
}