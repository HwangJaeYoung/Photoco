package com.continueing.photoco.ui.menu.myphoto_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.continueing.photoco.ui.menu.myphoto_page.StaggeredGridView.ArrayAdapterStaggeredGridView;
import com.continueing.photoco.ui.menu.myphoto_page.StaggeredGridView.ViewForStaggeredGridViewListViewItem.IStaggredGridViewListItem;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;

public class ViewForMyPhotoFragment extends AbstractViewForFragment implements OnItemClickListener {

	private ArrayAdapterStaggeredGridView adapter;
	private StaggeredGridView gridView;
	private Controller controller;
	private Button bt_myphotoNew;
	
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
		bt_myphotoNew = (Button)findViewById(R.id.bt_myphoto_new);
		
		gridView = (StaggeredGridView)findViewById(R.id.sgv_myphoto);	
		int margin = getContext().getResources().getDimensionPixelSize(R.dimen.margin);
		gridView.setItemMargin(margin); // set the GridView margin
		gridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well 
		// adapter = new StaggeredAdapter(getContext( ), R.id.imageView1);
		adapter = new ArrayAdapterStaggeredGridView(getContext( ), 0);
		
		gridView.setAdapter(adapter);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", true).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section4);
	}
	
	public void addItem(ArrayList<IStaggredGridViewListItem> aList)
	{
		adapter.addAll(aList);
	}

	@Override
	protected void setEvents()
	{
		gridView.setOnItemClickListener(this);
		bt_myphotoNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onNewPhotoSelected( );
			}
		});
	}

    public static interface Controller
    {
    	public void onPhotoSelected( );
    	public void onNewPhotoSelected( );
    }

	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position,long id) {
		controller.onPhotoSelected();	
	}
}