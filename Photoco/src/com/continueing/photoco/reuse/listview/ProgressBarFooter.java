package com.continueing.photoco.reuse.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.continueing.photoco.R;

public class ProgressBarFooter {
	private View footer;
	private ListView listView;
	private boolean visibility;

	public ProgressBarFooter(ListView aListView, LayoutInflater aInflater) {
		this(aListView, aInflater.inflate(R.layout.footer_progressbar, null, false));
	}

	public ProgressBarFooter(ListView aListView, View aFooterView) {
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