package com.continueing.photoco.reuse.listview.mvc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.continueing.photoco.reuse.girdview.staggered.loader.ImageLoader;
import com.continueing.photoco.reuse.girdview.staggered_grid_view.ViewForStaggeredGridViewListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IImageURL;


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
			abstractViewForListViewItem = getInstance();

		// 새로운 뷰이던 기존 뷰이던 간에 뷰에 데이터를 바꾸거나 채워야 하기 때문에 구현 해 놓음.
		abstractViewForListViewItem.setData(getItem(position));
		
		ViewForStaggeredGridViewListViewItem view = (ViewForStaggeredGridViewListViewItem)abstractViewForListViewItem;
		// 이미지 리사이즈는 CustomerSmartImageView에서 되고, 여기서는 리사이즈 된 이미지를 스크롤 할 때 이미지가 움직이지 않도록 해주는것 같다.
		IImageURL item = (IImageURL)getItem(position);
		mLoader.DisplayImage(item.getUrl(), view.returnSmartImageView());
	
		return abstractViewForListViewItem; // 새로 구성된 뷰를 리턴한다.
	}
	public abstract AbstractViewForListViewItem getInstance();
}
