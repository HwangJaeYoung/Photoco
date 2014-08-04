package com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.gridview;

import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.gridview.ViewForArrayAdapterForMyRequestDetailActivity.IMyRequestDetailItem;

public class Images implements IMyRequestDetailItem{

	private String url;
	public Images(String url)
	{
		this.url = url;
	}
	
	@Override
	public String getImageURL() {
		return url;
	}
}
