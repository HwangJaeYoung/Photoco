package com.continueing.photoco.reuse.listview.mvc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.continueing.photoco.reuse.listview.Staggered.loader.ImageLoader;
import com.continueing.photoco.ui.menu.myphoto_page.StaggeredGridView.ViewForStaggeredGridViewListViewItem;
import com.continueing.photoco.ui.menu.myphoto_page.StaggeredGridView.ViewForStaggeredGridViewListViewItem.IStaggredGridViewListItem;

public abstract class AbstarctArrayImageAdapter <T extends IListViewItem> extends ArrayAdapter<T>{
	
	private ImageLoader mLoader;
	
	public AbstarctArrayImageAdapter(Context context, int resource) {
		super(context, resource);
		mLoader = new ImageLoader(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AbstractViewForListViewItem abstractViewForListViewItem = (AbstractViewForListViewItem) convertView;
		
		if (abstractViewForListViewItem == null)
		{
			abstractViewForListViewItem = getInstance();
		}

		// 새로운 뷰이던 기존 뷰이던 간에 뷰에 데이터를 바꾸거나 채워야 하기 때문에 구현 해 놓음.
		abstractViewForListViewItem.setData(getItem(position));
		
		ViewForStaggeredGridViewListViewItem temp = (ViewForStaggeredGridViewListViewItem)abstractViewForListViewItem;
		IStaggredGridViewListItem item = (IStaggredGridViewListItem)getItem(position);
		
		mLoader.DisplayImage(item.getURL(), temp.returnView());
	
		return abstractViewForListViewItem; // 새로 구성된 뷰를 리턴한다.
	}
	
	public abstract AbstractViewForListViewItem getInstance();
}