package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview;

import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class MockUp implements IMyRequestTagItem{
	
	private String tagText = "";
	
	@Override
	public String getTagText() {
		return tagText;
	}

	@Override
	public void setTagText(String aTagText) {
		tagText = aTagText;
	}
}
