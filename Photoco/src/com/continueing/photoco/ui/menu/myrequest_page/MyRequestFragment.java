package com.continueing.photoco.ui.menu.myrequest_page;

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

import com.continueing.photoco.domain.MyRequest;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.MyRequestItemRequest;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.MyRequestGridViewDetailActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.MyNewRequestActivity;

public class MyRequestFragment extends Fragment implements ViewForMyRequestFragment.Controller{
	private ViewForMyRequestFragment view;
	private ArrayList<IMyRequestItem> myrequestItems;
	public static final int REQUEST_CODE_GET_REQUEST_ITEM = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = new ViewForMyRequestFragment(getActivity( ), inflater, container, this); // 뷰를 생성해 낸다.
		searchMyRequestItemFromServer( );
		return view.getRoot(); 
    }

	@Override
	public void onNewRequest() {
		Intent intent = new Intent(getActivity( ), MyNewRequestActivity.class);
		startActivityForResult(intent, REQUEST_CODE_GET_REQUEST_ITEM);
	}
	
	// NewRequest에서 Submit버튼을 클릭하였을 때 동작한다. 즉 하나의 요청을 만들어 낸다.
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_GET_REQUEST_ITEM)
			if(resultCode == Activity.RESULT_OK) {
				searchMyRequestItemFromServer( );
			}
	}

	@Override
	public void showRequestDetail() {
		Intent intent = new Intent(getActivity( ), MyRequestGridViewDetailActivity.class);
		startActivity(intent);
	}
	
	public void searchMyRequestItemFromServer( ) {
		view.setInvisible();
		view.listviewOff();
		view.progressOn();
		MyRequestItemRequest getMyrequestItems = new MyRequestItemRequest(getActivity( ));
		try {
			getMyrequestItems.getItems(getMyrequestItemListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener getMyrequestItemListener = new HttpRequester.NetworkResponseListener() {
		
		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			myrequestItems = new ArrayList<IMyRequestItem>( );
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonRequestObject = null;
				
				try {
					jsonRequestObject = jsonArray.getJSONObject(i);
					MyRequest request = new MyRequest(jsonRequestObject);
					myrequestItems.add(request);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			view.addMyRequestArrayList(myrequestItems);
			view.listviewOn();
			view.progresOff();
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
}