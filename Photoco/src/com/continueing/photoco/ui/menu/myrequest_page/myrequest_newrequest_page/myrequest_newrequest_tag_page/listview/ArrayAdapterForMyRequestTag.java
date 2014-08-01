package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview;

import android.content.Context;

import com.continueing.photoco.reuse.listview.mvc.AbstractArrayAdapter;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class ArrayAdapterForMyRequestTag extends AbstractArrayAdapter<IMyRequestTagItem> {
	
	public ArrayAdapterForMyRequestTag(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public AbstractViewForListViewItem getInstance() {
		// getView에서 한번 만들어진 뷰는 재활용 되기 때문에
		// clear이후에 다시 add나 addAll을 한다고 해도 새로운 뷰가 만들어지는 것이아니라
		// 기존에 만들어졌던 위치의 뷰를 주기 때문에 getInstance가 호출이 되지 않는다.
		// ex) position 0번째가 만들어졌다가 clear->addAll과정을 수행하면 그 다음에는 position 0번째 것을 만들지 않고 재활용 한다.
		return new ViewForArrayAdapterForMyNewRequestTag(getContext());
	}
}