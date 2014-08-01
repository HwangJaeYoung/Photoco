package com.continueing.photoco.domain;

import java.io.Serializable;

import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class Tag  implements IMyRequestTagItem, Serializable {
	
	private String tagText;
	
	@Override
	public String getTagText() {
		return tagText;
	}

	@Override
	public void setTagText(String aTagText) {
		tagText = aTagText;
	}
}