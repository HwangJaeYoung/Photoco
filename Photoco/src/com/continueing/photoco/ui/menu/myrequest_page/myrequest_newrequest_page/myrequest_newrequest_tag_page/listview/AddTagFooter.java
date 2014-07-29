package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.continueing.photoco.R;

public class AddTagFooter {
	private View footer;
	private ListView listView;
	private boolean visibility;

	public AddTagFooter(ListView aListView, LayoutInflater aInflater) {
		this(aListView, aInflater.inflate(R.layout.footer_addtag, null, false));
	}

	public AddTagFooter(ListView aListView, View aFooterView) {
		this.listView = aListView;
		this.footer = aFooterView;
		this.visibility = false;
	}

	public boolean getVisibility() {
		return this.visibility;
	}

	public void setVisibility(boolean aVisibility) {

		if (aVisibility) {
			if (!this.visibility) {
				this.listView.addFooterView(this.footer, null, false);
				this.visibility = aVisibility;
			}
		} else {
			if (this.visibility) {
				this.listView.removeFooterView(this.footer);
				this.visibility = aVisibility;
			}
		}
	}
}
