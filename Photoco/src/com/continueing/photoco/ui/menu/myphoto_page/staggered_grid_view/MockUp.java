package com.continueing.photoco.ui.menu.myphoto_page.staggered_grid_view;

import com.continueing.photoco.ui.menu.myphoto_page.staggered_grid_view.ViewForStaggeredGridViewListViewItem.IStaggredGridViewListItem;

public class MockUp implements IStaggredGridViewListItem {

	private String url;
	private String size;
	private String price;
	private String category;
	
	public MockUp(String anURL, String size, String price, String category)
	{
		url = anURL;
		this.size = size;
		this.price = price;
		this.category = category;
	}
	
	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public String getSize() {
		
		return size;
	}

	@Override
	public String getPrice() {
		
		return price;
	}

	@Override
	public String getURL() {
		
		return url;
	}
}
