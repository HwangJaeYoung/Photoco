package com.continueing.photoco.ui.menu.marketplace_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Image;
import com.continueing.photoco.reuse.girdview.staggered_grid_view.ArrayAdapterStaggeredGridView;
//import com.continueing.photoco.reuse.listview.Staggered.StaggeredAdapter;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;

public class ViewForMarketplaceFragment extends AbstractViewForFragment implements OnItemClickListener {
	private ArrayAdapterStaggeredGridView arrayAdapterStaggeredGridView;
	private StaggeredGridView gridView;
	private Controller controller;
	private ProgressBar progressBar;
	private LinearLayout ll_marketplaceEmpty;
	
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
		progressBar = (ProgressBar)findViewById(R.id.pb_marketplace);
		ll_marketplaceEmpty = (LinearLayout)findViewById(R.id.ll_marketplace_empty);
		
		gridView = (StaggeredGridView)findViewById(R.id.sgv_marketplace);	
		int margin = getContext().getResources().getDimensionPixelSize(R.dimen.margin);
		gridView.setItemMargin(margin);
		gridView.setPadding(margin, 0, margin, 0);
		arrayAdapterStaggeredGridView = new ArrayAdapterStaggeredGridView(getContext( ), 0);
		gridView.setAdapter(arrayAdapterStaggeredGridView);
		
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section5);
		
		
	}
	
	public void addMarketplaceImageSetArrayList(ArrayList<Image> aList){
	
		if(aList.size() != 0) // 초기에 하나라도 아이템이 있으면
			setInvisible( );
		else 
			setVisible( );
		
		arrayAdapterStaggeredGridView.clear();
		arrayAdapterStaggeredGridView.addAll(aList);
	}
	
	public void setInvisible( ) {
		ll_marketplaceEmpty.setVisibility(View.INVISIBLE);
	}
	
	public void setVisible( ) {
		ll_marketplaceEmpty.setVisibility(View.VISIBLE);
	}
	
	public void progresOff( ) {
		progressBar.setVisibility(View.INVISIBLE);
	}
	
	public void progressOn( ) {
		progressBar.setVisibility(View.VISIBLE);
	}
	
	public void gridviewOff( ) {
		gridView.setVisibility(View.INVISIBLE);
	}
	
	public void gridviewviewOn( ) {
		gridView.setVisibility(View.VISIBLE);
	}

	@Override
	protected void setEvents() {
		gridView.setOnItemClickListener(this);
	}
	
	public static interface Controller {
		public void onPhotoSelected(int aPosition);
	}
	
	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position,long id) {
		controller.onPhotoSelected(position);	
	}
} 
