package com.continueing.photoco.domain;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItemImageURL;

public class URL implements IMyRequestItemImageURL, Serializable{
	
	private String URL;
	private JSONArray url;
	
	public URL(String anURL) {
		URL = anURL;
	}
	
	public URL(JSONArray anURLs) {
		url = anURLs;
	}
	
	@Override
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
	
	@Override
	public String getURL( ) {
		return URL;
	}
}