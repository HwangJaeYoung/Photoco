package com.continueing.photoco.ui.menu.myphoto_page.StaggeredGridView;

import com.continueing.photoco.ui.menu.myphoto_page.StaggeredGridView.ViewForStaggeredGridViewListViewItem.IStaggredGridViewListItem;

public class MockUp implements IStaggredGridViewListItem {

	private String url;
	
	public MockUp(String anURL)
	{
		url = anURL;
	}
	
	@Override
	public String getCategory() {
		return "LandScapte";
	}

	@Override
	public String getSize() {
		
		return "L";
	}

	@Override
	public String getPrice() {
		
		return "25";
	}

	@Override
	public String getURL() {
		
		return url;
	}
}
