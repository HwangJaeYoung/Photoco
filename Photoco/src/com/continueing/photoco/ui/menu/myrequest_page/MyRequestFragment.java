package com.continueing.photoco.ui.menu.myrequest_page;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.domain.MyRequest;
import com.continueing.photoco.domain.URL;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.MyRequestItemRequest;
import com.continueing.photoco.reuse.network.RequestsRequest;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_gridview_detail_page.MyRequestGridViewDetailActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.MyNewRequestActivity;

public class MyRequestFragment extends Fragment implements ViewForMyRequestFragment.Controller{
	private ViewForMyRequestFragment view;
	private ArrayList<IMyRequestItem> myrequestItems;
	private ArrayList<String> requestIdSet;
	private int itemCounter = 0;
	public static final int REQUEST_CODE_GET_REQUEST_ITEM = 0;
	
	Handler mHandler = new Handler( ) {
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case 1:
				searchImageURLFromServer( );
				break;
			}
		}
	};

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
	
	public void searchImageURLFromServer( ) {
		RequestsRequest requestsRequest = new RequestsRequest(getActivity( ));
		
		for(int i = 0; i < requestIdSet.size(); i++) {
			try {
				requestsRequest.getImageURL(getImageURLListener, requestIdSet.get(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
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
			requestIdSet = new ArrayList<String>( );
			
			for(int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonRequestObject = null;
				
				try {
					jsonRequestObject = jsonArray.getJSONObject(i);
					requestIdSet.add(jsonRequestObject.getString("id"));

					MyRequest myrequest = new MyRequest( );
					myrequest.setSaveJSONOjbect(jsonRequestObject);
					myrequestItems.add(myrequest);	
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			Message message = new Message( );
			message.what = 1;
			mHandler.sendMessage((message));
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	HttpRequester.NetworkResponseListener getImageURLListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;
			JSONObject requestObject = null;
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
				requestObject = ((MyRequest)myrequestItems.get(itemCounter)).getSavedJSONObject();
				requestObject.put("urlset", jsonArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	
			try {
				MyRequest myrequest = new MyRequest(requestObject);
				myrequestItems.set(itemCounter, myrequest);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			itemCounter++;
			
			if(itemCounter == requestIdSet.size()) {
				view.progresOff();
				view.listviewOn();
				view.addMyRequestArrayList(myrequestItems);
			}
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
}