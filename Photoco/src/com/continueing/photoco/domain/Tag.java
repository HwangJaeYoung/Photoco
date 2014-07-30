package com.continueing.photoco.domain;

import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class Tag implements IMyRequestTagItem{
	
	private String tagText;
	
	public Tag(String atagText) {
		tagText = atagText;
	}

	@Override
	public String getTagText() {
		// TODO Auto-generated method stub
		return tagText;
	}

	@Override
	public void setTagText(String aTagText) {
		tagText = aTagText;
	}
}
