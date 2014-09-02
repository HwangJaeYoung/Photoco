package com.continueing.photoco.ui.navigation_drawer_menu;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.mvc.AbstractViewForListViewItem;
import com.continueing.photoco.reuse.listview.mvc.IListViewItem;

public class ViewForNavigationDrawerMenuListViewItem extends AbstractViewForListViewItem {
	TextView tv_contents;
	ImageView iv_leftIcon;

	public ViewForNavigationDrawerMenuListViewItem(Context context) {
		super(context);
	}

	@Override
	protected View inflate() {
		return inflate(getContext(), R.layout.item_navigation_drawer_menu, this);
	}

	@Override
	protected void initViews() {
		tv_contents = (TextView)findViewById_(R.id.tv_list_item);
		iv_leftIcon = (ImageView)findViewById_(R.id.iv_list_item_left);
	}

	@Override
	protected void setEvents() { }

	@Override
	protected void setData(IListViewItem aIListViewItem) {
		DrawerMenuItem drawerMenuItem = (DrawerMenuItem) aIListViewItem;
		tv_contents.setText(drawerMenuItem.getText());
		iv_leftIcon.setImageResource(((DrawerMenuItem) aIListViewItem).getImageId());
	}

	// 여기서의 item은 다른 것들과 다르게 데이터가 변하지 않는다.
	public static class DrawerMenuItem implements IListViewItem {
		private int imageId;
		private String text;
		private boolean viewRedCircleDecision;

		public DrawerMenuItem(int anImageId, String aText, boolean aBoolean) {
			imageId = anImageId;
			text = aText;
			viewRedCircleDecision = aBoolean;
		}

		public String getText() {
			return text;
		}

		public int getImageId() {
			return imageId;
		}
	}
}