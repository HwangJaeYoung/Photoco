package com.continueing.photoco.ui.menu.cart_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Cart;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.continueing.photoco.ui.menu.cart_page.listview.ArrayAdapterForCartListView;
import com.continueing.photoco.ui.menu.cart_page.listview.ViewForCartListViewItem.ICartItem;

public class ViewForCartFragment extends AbstractViewForFragment{

	private Controller controller;
	private ListView lv_cart;
	private ProgressBar pb_cart;
	private ImageButton ib_cartRemoveItem;
	private ArrayAdapterForCartListView arrayAdapterForCartListView;
	
	public ViewForCartFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, Controller aController) {
		super(context, layoutInflater, container);
		controller = aController;
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_cart, container, false);
	}

	@Override
	protected void initViews() {
		ib_cartRemoveItem = (ImageButton)findViewById(R.id.ib_cart_remove_item);
		
		lv_cart = (ListView)findViewById(R.id.list_view_left);
		arrayAdapterForCartListView = new ArrayAdapterForCartListView(getContext( ), R.layout.item_cart);
		lv_cart.setAdapter(arrayAdapterForCartListView);
		lv_cart.setItemsCanFocus(false);
		lv_cart.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", false).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section6);
		
		pb_cart = (ProgressBar)findViewById(R.id.pb_cart);
	}

	@Override
	protected void setEvents() {
		lv_cart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				controller.onShowDetailCart(position);				
			}
		});
		
		ib_cartRemoveItem.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {		
				controller.onRemoveItems();
			}
		});
	}
	
	public void addCartItemArrayList(ArrayList<Cart> anArrayList) {
		arrayAdapterForCartListView.clear();
		arrayAdapterForCartListView.addAll(anArrayList);
	}
	
	public void removeAllItems( ) {
		arrayAdapterForCartListView.clear();
	}
	
	public void removeSelectedItem(ICartItem anItem) {
		arrayAdapterForCartListView.remove(anItem);
	}
	
	public void progresOff( ) {
		pb_cart.setVisibility(View.INVISIBLE);
	}
	
	public void progressOn( ) {
		pb_cart.setVisibility(View.VISIBLE);
	}
	
	public void listviewOff( ) {
		lv_cart.setVisibility(View.INVISIBLE);
	}
	
	public void listviewOn( ) {
		lv_cart.setVisibility(View.VISIBLE);
	}
	
	public static interface Controller {
		public void onShowDetailCart(int aPosition);
		public void onRemoveItems( );
	}
}