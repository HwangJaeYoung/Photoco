package com.continueing.photoco.domain;

import java.io.Serializable;

import com.continueing.photoco.reuse.girdview.tag_gridview.ViewForArrayAdapterForTagGridView.ITagItem;

// 하나의 Tag객체를 구성한다.
public class Tag implements ITagItem, Serializable {
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