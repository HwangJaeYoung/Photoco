package com.continueing.photoco.domain;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class URL {
	
	private String URL;
	private JSONArray url;
	
	public URL(String anURL) {
		URL = anURL;
	}
	
	public URL(JSONArray anURLs) {
		url = anURLs;
	}
	
	public ArrayList<URL> getURLSet() {
		ArrayList<URL> urls = new ArrayList<URL>( );
		for(int i = 0; i < url.length(); i++) {
			try {
				String temp = url.getJSONObject(i).getString("url");
				URL url = new URL(temp);
				urls.add(url);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return urls;
	}
	
	public String getURL( ) {
		return URL;
	}
}