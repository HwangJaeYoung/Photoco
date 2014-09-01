package com.continueing.photoco.ui.menu.cart_page;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.domain.Cart;
import com.continueing.photoco.reuse.network.CartRequest;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.ui.menu.cart_page.cart_detail_page.CartDetailActivity;
import com.continueing.photoco.ui.menu.cart_page.listview.ViewForCartListViewItem.ICartItem;

public class CartFragment extends Fragment implements ViewForCartFragment.Controller{
	
	private ViewForCartFragment view;
	private ArrayList<Cart> cartSet;
	public static int REQUEST_CODE_GET_REMOVE = 0;
	public static int REQUEST_CODE_GET_BUY = 1;
	public static final String PARAM_SELECTED_POSITION = "position";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForCartFragment(getActivity( ), inflater, container, this);      
        searchCartItemFromServer( );
        return view.getRoot();
    }
	
	public void searchCartItemFromServer( ) {
		view.listviewOff();
		view.progressOn();
		
		CartRequest cartRequest = new CartRequest(getActivity( ));
		
		try {
			cartRequest.getCartItem(getCartItemListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener getCartItemListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null; 
			cartSet = new ArrayList<Cart>( );
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < jsonArray.length(); i++) {
				try {
					Cart cart = new Cart(jsonArray.getJSONObject(i));
					cartSet.add(cart);				
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			view.addCartItemArrayList(cartSet);
			view.progresOff();
			view.listviewOn();
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	@Override
	public void onRemoveItems() {
		// 통신으로 해서도 지우고 arraylist의 번호를 모두 던저준다.

		// 사용자가 보이는 뷰에서도 지운다.
		view.removeAllItems();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_GET_REMOVE)
			if(resultCode == Activity.RESULT_OK) {
				int position = data.getIntExtra(PARAM_SELECTED_POSITION, -1);
				view.removeSelectedItem(cartSet.get(position));
				cartSet.remove(position);
			}
	}

	@Override
	public void onShowDetailCart(int aPosition) {
		Intent intent = new Intent(getActivity( ), CartDetailActivity.class);
		intent.putExtra(PARAM_SELECTED_POSITION, aPosition);
		startActivityForResult(intent, REQUEST_CODE_GET_REMOVE);
	}
}