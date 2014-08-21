package com.continueing.photoco.reuse.girdview.tag_gridview;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Tag;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForArrayAdapterForTagGridView extends AbstractViewForListViewItem {
	
	private TextView tv_TagItem;
	public ViewForArrayAdapterForTagGridView(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext( ), R.layout.item_gridview_tag, this);
	}

	@Override
	protected void initViews() {
		tv_TagItem = (TextView)findViewById(R.id.tv_gridview_tag_item);	
	}

	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		ITagItem iMyRequestTagItem = (ITagItem)aIListViewItem;
		tv_TagItem.setText(iMyRequestTagItem.getTagText());
	}
	
	// Tag의 모델을 정의한다.
	public static interface ITagItem extends IListViewItem {
		public String getTagText( );
		public void setTagText(String aTagText);
		public ArrayList<Tag> getTagSet( );
	}
}