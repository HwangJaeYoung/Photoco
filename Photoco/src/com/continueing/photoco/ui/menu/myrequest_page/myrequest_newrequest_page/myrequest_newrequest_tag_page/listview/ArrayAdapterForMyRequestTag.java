package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class ArrayAdapterForMyRequestTag extends AbstractArrayAdapter<IMyRequestTagItem> implements ViewForArrayAdapterForMyNewRequestTag.Controller{

	private ArrayList<IMyRequestTagItem> items;
	
	public ArrayAdapterForMyRequestTag(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return  new ViewForArrayAdapterForMyNewRequestTag(getContext(), this, position);
	}
	
	public void setArrayList(ArrayList<IMyRequestTagItem> item)
	{
		items = item;
	}

	@Override
	public void removeTag(int aPosition) {
			items.remove(aPosition);
			clear();
			addAll(items);
	}
}