package com.continueing.photoco.reuse.listview.Staggered;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.Staggered.loader.ImageLoader;

public class StaggeredAdapter extends ArrayAdapter<String> {

	private ImageLoader mLoader;

	public StaggeredAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		mLoader = new ImageLoader(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater layoutInflator = LayoutInflater.from(getContext());
			convertView = layoutInflator.inflate(R.layout.item_myphoto, null);
			holder = new ViewHolder();
			holder.imageView = (ScaleImageView) convertView .findViewById(R.id.imageView1);
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		mLoader.DisplayImage(getItem(position), holder.imageView);

		return convertView;
	}

	static class ViewHolder {
		ScaleImageView imageView;
	}
}
