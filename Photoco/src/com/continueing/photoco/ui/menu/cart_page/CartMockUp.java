package com.continueing.photoco.ui.menu.cart_page;

import com.continueing.photoco.ui.menu.cart_page.listview.ViewForCartListViewItem.ICartItem;

public class CartMockUp implements ICartItem{

	private int a;
	
	public CartMockUp(int a)
	{
		this.a = a;
	}
	
	@Override
	public String getTitle() {
		return "Cart" + a;
	}

}
