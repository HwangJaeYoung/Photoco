package com.continueing.photoco.reuse.listview.findingjoblist;

import android.content.Context;

import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;

public class ArrayAdapterForFindingJobListFragment extends AbstractArrayAdapter<IFindingJobListItem> {

	public ArrayAdapterForFindingJobListFragment(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		return new ViewForFindingJobListViewItem(getContext( ));
	}
}