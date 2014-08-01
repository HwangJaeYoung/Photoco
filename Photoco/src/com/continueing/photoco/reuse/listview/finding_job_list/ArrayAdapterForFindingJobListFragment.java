package com.continueing.photoco.reuse.listview.finding_job_list;

import android.content.Context;

import com.continueing.photoco.reuse.listview.finding_job_list.ViewForFindingJobListViewItem.IFindingJobListItem;
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