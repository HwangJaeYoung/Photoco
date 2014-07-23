package com.continueing.photoco.ui.menu.myrequest_page;

import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItem;

public class Mockup implements IMyRequestItem{
	private String name;
	
	public Mockup(String name)
	{
		this.name = name;
	}
	
	public String getName( )
	{
		return this.name;
	}
}
