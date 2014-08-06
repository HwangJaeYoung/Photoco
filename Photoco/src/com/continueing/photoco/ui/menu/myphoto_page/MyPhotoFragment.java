package com.continueing.photoco.ui.menu.myphoto_page;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.reuse.girdview.staggered_grid_view.MockUp;
import com.continueing.photoco.reuse.girdview.staggered_grid_view.ViewForStaggeredGridViewListViewItem.IStaggredGridViewListItem;
import com.continueing.photoco.ui.menu.myphoto_page.myphoto_detail_page.MyPhotoDetailActivity;
import com.continueing.photoco.ui.menu.myphoto_page.myphoto_newphoto_page.MyPhotoNewPhotoActivity;

public class MyPhotoFragment extends Fragment implements ViewForMyPhotoFragment.Controller {
	private ViewForMyPhotoFragment view;
	private ActionBar actionBar;
	private ActionBar.Tab actionBarTab;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarTab( );
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForMyPhotoFragment(getActivity( ), inflater, container, this);
        
        ArrayList<IStaggredGridViewListItem> list = new ArrayList<IStaggredGridViewListItem>( );
        
        list.add(new MockUp("http://farm7.staticflickr.com/6101/6853156632_6374976d38_c.jpg", "M", "1", "blossom"));
        list.add(new MockUp("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg", "L", "2", "forest"));
        list.add(new MockUp("http://farm8.staticflickr.com/7232/6913504132_a0fce67a0e_c.jpg", "M", "3", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg", "S", "4", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg", "M", "5", "forest"));
        list.add(new MockUp("http://farm9.staticflickr.com/8208/8219397252_a04e2184b2.jpg", "S", "6", "blossom"));
        list.add(new MockUp("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg", "M", "7", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg", "L", "8", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8185/8081514424_270630b7a5.jpg", "M", "9", "blossom"));
        list.add(new MockUp("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg", "S", "10", "hankook"));
        list.add(new MockUp("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg", "S", "11", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg", "L", "12", "forest"));
        list.add(new MockUp("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg", "M", "34", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8185/8081514424_270630b7a5.jpg", "M", "23", "blossom"));
        list.add(new MockUp("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg", "L", "43", "forest"));
        list.add(new MockUp("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg", "M", "25", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg", "M", "22", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg", "L", "74", "blossom"));
        list.add(new MockUp("http://farm9.staticflickr.com/8185/8081514424_270630b7a5.jpg", "L", "24", "hankook"));
        list.add(new MockUp("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg", "M", "93", "forest"));
        list.add(new MockUp("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg", "S", "85", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg", "M", "12", "blossom"));
        list.add(new MockUp("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg", "L", "22", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8185/8081514424_270630b7a5.jpg", "L", "42", "blossom"));
        list.add(new MockUp("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg", "M", "22", "forest"));
        list.add(new MockUp("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg", "L", "32", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg", "S", "42", "hankook"));
        list.add(new MockUp("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg", "S", "62", "forest"));
        list.add(new MockUp("http://farm9.staticflickr.com/8185/8081514424_270630b7a5.jpg", "L", "92", "hankook"));
        list.add(new MockUp("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg", "M", "26", "hankook"));
        list.add(new MockUp("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg", "M", "25", "blossom"));
        view.addItem(list);
        
        return view.getRoot( );
    }
	
	@Override
	public void onPhotoSelected() {
		Intent intent = new Intent(getActivity( ), MyPhotoDetailActivity.class);
		startActivity(intent);
	}

	@Override
	public void onNewPhotoSelected() {
		Intent intent = new Intent(getActivity( ), MyPhotoNewPhotoActivity.class);
		startActivity(intent);
	}
	
	public void addActionBarTab( ) {
		actionBar = ((ActionBarActivity)getActivity( )).getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		
		TabListener findjobListener = new TabListener(null);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Bought");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);
		
		actionBarTab = actionBar.newTab();
		actionBarTab.setText("Uploaded");
		actionBarTab.setTabListener(findjobListener);
		actionBar.addTab(actionBarTab, false);
	}
	
	private class TabListener implements ActionBar.TabListener {
		public TabListener(Fragment aFragment) { }

		@Override
		public void onTabSelected(Tab aTabName, FragmentTransaction arg1) {

		}
		
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) { }

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) { }
	}
	
	@Override
	public void onDetach( )
	{
		super.onDetach();
		actionBar.removeAllTabs(); // 생성된 모든 탭을 지운다.
		//removeTab(ActionBar.Tab tab)는 하나만 지운다
	}
}