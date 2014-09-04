package com.continueing.photoco.ui.menu.myphoto_page;

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
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;

public class ViewForMyPhotoFragment extends AbstractViewForFragment implements OnItemClickListener {

	private ArrayAdapterStaggeredGridView arrayAdapterStaggeredGridView;
	private StaggeredGridView gridView;
	private Controller controller;
	private ProgressBar progressBar;
	private LinearLayout ll_myphoto_empty;
	
	public ViewForMyPhotoFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, Controller aController) {
		super(context, layoutInflater, container);
		controller = aController;
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_myphoto, container, false);
	}

	@Override
	protected void initViews() {
		progressBar = (ProgressBar)findViewById(R.id.pb_myphoto);
		
		gridView = (StaggeredGridView)findViewById(R.id.sgv_myphoto);	
		int margin = getContext().getResources().getDimensionPixelSize(R.dimen.margin);
		gridView.setItemMargin(margin); // set the GridView margin
		gridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well 
		arrayAdapterStaggeredGridView = new ArrayAdapterStaggeredGridView(getContext( ), 0);
		gridView.setAdapter(arrayAdapterStaggeredGridView);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section4);
		
		ll_myphoto_empty = (LinearLayout)findViewById(R.id.ll_myphoto_empty);
	}

	@Override
	protected void setEvents() {
		gridView.setOnItemClickListener(this);
	}
	
	public void addMyPhotoImageSetArrayList(ArrayList<Image> aList){
		
		if(aList.size() != 0) // 초기에 하나라도 아이템이 있으면
			setInvisible( );
		else 
			setVisible( );
		
		arrayAdapterStaggeredGridView.clear();
		arrayAdapterStaggeredGridView.addAll(aList);
	}
	
	public void setInvisible( ) {
		ll_myphoto_empty.setVisibility(View.INVISIBLE);
	}
	
	public void setVisible( ) {
		ll_myphoto_empty.setVisibility(View.VISIBLE);
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
	
    public static interface Controller {
    	public void onPhotoSelected(int aPosition);
    }

	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position,long id) {
		controller.onPhotoSelected(position);	
	}
}