package com.continueing.photoco.domain;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.continueing.photoco.reuse.girdview.tag_gridview.ViewForArrayAdapterForTagGridView.ITagItem;

public class Tag  implements ITagItem, Serializable {
	private String tagText;
	
	public Tag( ) {}
	
	public Tag(JSONArray aTags) {
		tagText = aTags.toString();
	}
	
	@Override
	public String getTagText() {
		return tagText;
	}

	@Override
	public void setTagText(String aTagText) {
		tagText = aTagText;
	}
	
	@Override
	public ArrayList<Tag> getTagSet() {
		JSONArray tagSet = null;
		try {
			tagSet = new JSONArray(tagText);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		 
		ArrayList<Tag> tags = new ArrayList<Tag>( );
		
		for(int i = 0; i < tagSet.length(); i++) {
			try {
				String temp = tagSet.getJSONObject(i).getString("name");
				Tag tag = new Tag( );
				tag.setTagText(temp);
				tags.add(tag);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return tags;
	}
}