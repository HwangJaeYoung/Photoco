package com.continueing.photoco.reuse.girdview.tag_gridview;

import android.content.Context;

import com.continueing.photoco.reuse.girdview.tag_gridview.ViewForArrayAdapterForTagGridView.ITagItem;
import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;

public class ArrayAdapterForTagGridView extends AbstractArrayAdapter<ITagItem> {

	public ArrayAdapterForTagGridView(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForArrayAdapterForTagGridView(getContext( ));
	}
}