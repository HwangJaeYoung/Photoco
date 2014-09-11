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
import android.widget.Toast;

import com.continueing.photoco.domain.Cart;
import com.continueing.photoco.reuse.network.CartRequest;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.PurchaseRequest;
import com.continueing.photoco.ui.menu.cart_page.cart_detail_page.CartDetailActivity;

public class CartFragment extends Fragment implements ViewForCartFragment.Controller{
	
	private int itemPosition;
	private int cartCallCounter;
	private ViewForCartFragment view;
	private ArrayList<Cart> cartSet;
	private ArrayList<String> cartId;
	private static int REQUEST_CODE_GET_REMOVE_BUY = 0;
	public static final String PARAM_SELECTED_POSITION = "position";
	public static final String PARAM_CART_DETAIL_ITEM_KEY = "cartdetailitem";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		cartCallCounter = 0;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForCartFragment(getActivity( ), inflater, container, this);      
        searchCartItemFromServer( );
        view.setInvisible(); // 처음에 Smile과 관련 문구를 보이지 않게한다.
        return view.getRoot();
    }
	
	// Cart아이템을 가지고 온다.
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
			cartId = new ArrayList<String>( ); // 아이템 구매에 필요한 Cart의 PrimaryKey를 저장한다.
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < 7; i++) {
				try {
					Cart cart = new Cart(jsonArray.getJSONObject(i));
					cartId.add(cart.getId());
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_GET_REMOVE_BUY) {
			if(resultCode == Activity.RESULT_OK) {
				cartSet.remove(itemPosition); // 삭제나 구매를 한후에 Cart아이템 삭제
				view.addCartItemArrayList(cartSet);
				view.modifyCartItemNumber();
			}
		}
	}

	@Override
	public void onShowDetailCart(int aPosition) {
		itemPosition = aPosition; // 삭제 혹은 구매를 했을 때 해당 Fragmnet에서 Cart아이템을 없애기 위해 사용한다.
		Intent intent = new Intent(getActivity( ), CartDetailActivity.class);
		intent.putExtra(PARAM_CART_DETAIL_ITEM_KEY, cartSet.get(aPosition));
		startActivityForResult(intent, REQUEST_CODE_GET_REMOVE_BUY);
	}

	// 카트에 있는 모든 아이템을 삭제한다.
	@Override
	public void onRemoveItems() {
		// 통신으로 해서도 지우고 arraylist의 번호를 모두 던저준다.
		// 사용자가 보이는 뷰에서도 지운다.
		cartSet.removeAll(cartSet);
		view.removeAllItems();
		view.modifyCartItemNumber();
	}
	
	// 카트에 있는 모든 아이템을 구매한다.
	@Override
	public void onBytItems() {
		PurchaseRequest purchaseRequest = new PurchaseRequest(getActivity());
		
		for(int i = 0; i < cartId.size(); i++) {
			try {
				purchaseRequest.purchaseItemFormCart(cartId.get(i), executePurchaseItemListener);
			} catch (JSONException e) {
				e.printStackTrace();
			}		
		}
	}
	
	HttpRequester.NetworkResponseListener executePurchaseItemListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			cartCallCounter++; // 밑의 조건 검사를 하기 위해서 만든 카운터이다.
			if (cartId.size() == cartCallCounter) {
				cartCallCounter = 0; // 최종적으로 성공하였으므로 카운터를 초기화한다.
				Toast.makeText(getActivity(), "To purchase items is complete", Toast.LENGTH_LONG).show();
				view.removeAllItems( ); // CartFragment에 보인는 모든 아이템을 없앤다 모두 구매하였으므로.
				view.modifyCartItemNumber();
			}
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
}