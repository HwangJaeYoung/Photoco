package com.continueing.photoco.reuse.listview.mvc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.continueing.photoco.R;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem;
import com.loopj.android.image.SmartImageView;

public abstract class AbstractArrayImageViewAdapter<T extends IListViewItem> extends ArrayAdapter<T>{
	public AbstractArrayImageViewAdapter(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AbstractViewForListViewItem abstractViewForListViewItem = (AbstractViewForListViewItem) convertView;

		if (abstractViewForListViewItem == null) {
			abstractViewForListViewItem = getInstance();
		}

		ViewForMyRequestListViewItem view = (ViewForMyRequestListViewItem)abstractViewForListViewItem;
		
		SmartImageView sImageView = view.returnLeftView();
		sImageView.setBackgroundResource(R.drawable.textlogo_small);
		sImageView = view.returnMidView();
		sImageView.setBackgroundResource(R.drawable.textlogo_small);
		sImageView = view.returnRightView();
		sImageView.setBackgroundResource(R.drawable.textlogo_small);
		
		// 새로운 뷰이던 기존 뷰이던 간에 뷰에 데이터를 바꾸거나 채워야 하기 때문에 구현 해 놓음.
		abstractViewForListViewItem.setData(getItem(position));
	
		return abstractViewForListViewItem; // 새로 구성된 뷰를 리턴한다.
	}

	public abstract AbstractViewForListViewItem getInstance();
}
