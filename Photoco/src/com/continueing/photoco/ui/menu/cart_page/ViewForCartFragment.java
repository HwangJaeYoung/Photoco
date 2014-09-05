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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Cart;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.continueing.photoco.ui.menu.cart_page.listview.ArrayAdapterForCartListView;

public class ViewForCartFragment extends AbstractViewForFragment{

	private Controller controller;
	private ListView lv_cart;
	private ProgressBar pb_cart;
	private ImageButton ib_cartRemoveItem;
	private Button bt_cartBuyItem;
	private ArrayAdapterForCartListView arrayAdapterForCartListView;
	private LinearLayout ll_cartEmpty;
	
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
		pb_cart = (ProgressBar)findViewById(R.id.pb_cart);
		lv_cart = (ListView)findViewById(R.id.list_view_left);
		bt_cartBuyItem = (Button)findViewById(R.id.bt_cart_buy_item);
		ll_cartEmpty = (LinearLayout)findViewById(R.id.ll_cart_empty);
		ib_cartRemoveItem = (ImageButton)findViewById(R.id.ib_cart_remove_item);

		arrayAdapterForCartListView = new ArrayAdapterForCartListView(getContext( ), R.layout.item_cart);
		lv_cart.setAdapter(arrayAdapterForCartListView);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", false).apply();
		
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section6);
	}

	@Override
	protected void setEvents() {
		// Cart Detail로 접근
		lv_cart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				controller.onShowDetailCart(position);				
			}
		});
		
		// 모든  Cart의 아이템을 삭제
		ib_cartRemoveItem.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {		
				controller.onRemoveItems();
			}
		});
		
		// 모든 Cart의 아이템을 구매
		bt_cartBuyItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onBytItems();
			}
		});
	}
	
	public void addCartItemArrayList(ArrayList<Cart> anArrayList) {
		
		if(anArrayList.size() != 0) // 초기에 하나라도 아이템이 있으면
			setInvisible( );
		else 
			setVisible( );
		
		arrayAdapterForCartListView.clear();
		arrayAdapterForCartListView.addAll(anArrayList);
	}
	
	public void setInvisible( ) {
		ll_cartEmpty.setVisibility(View.INVISIBLE);
	}
	
	public void setVisible( ) {
		ll_cartEmpty.setVisibility(View.VISIBLE);
	}
	
	// Cart의 모든 아이템을 삭제한다.
	public void removeAllItems( ) {
		arrayAdapterForCartListView.clear();
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
		public void onBytItems ();
	}
}