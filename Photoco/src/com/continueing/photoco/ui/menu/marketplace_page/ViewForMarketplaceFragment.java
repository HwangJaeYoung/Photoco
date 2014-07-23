package com.continueing.photoco.ui.menu.marketplace_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.listview.Staggered.StaggeredAdapter;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;

public class ViewForMarketplaceFragment extends AbstractViewForFragment implements OnItemClickListener {
	private StaggeredAdapter adapter;
	private StaggeredGridView gridView;
	private Controller controller;
	
	public ViewForMarketplaceFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, Controller aController) {
		super(context, layoutInflater, container);
		controller = aController;
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_marketplace, container, false);
	}

	@Override
	protected void initViews() {
		gridView = (StaggeredGridView)findViewById(R.id.sgv_marketplace);	
		int margin = getContext().getResources().getDimensionPixelSize(R.dimen.margin);
		gridView.setItemMargin(margin); // set the GridView margin
		gridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well 
		adapter = new StaggeredAdapter(getContext( ), R.id.imageView1);
		gridView.setAdapter(adapter);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section5);
	}
	
	public void addItem(ArrayList<String> aList)
	{
		adapter.addAll(aList);
	}

	@Override
	protected void setEvents() {
		gridView.setOnItemClickListener(this);
	}
	
	public static interface Controller
	{
		public void onPhotoSelected( );
	}
	
	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position,long id) {
		controller.onPhotoSelected();	
	}
}
