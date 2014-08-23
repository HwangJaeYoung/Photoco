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
	private ArrayList<IMyRequestItem> myrequestItems; // 내가 생성한 요청을 저장하는 곳
	private ArrayList<String> requestIdSet; // 해당 요청에 대한 이미지들을 요청할 때 필요한 것
	private int itemCounter = 0; // 이것을 사용하여 searchImageURLFromServer의 동기화를 조정한다.
	public static final int REQUEST_CODE_GET_REQUEST_ITEM = 0;
	public static final String PARAM_REQUESTID_KEY ="requestID";
	
	/* 다른 방법이 있을 수 있겠으나 searchImageURLFromServer를 여러번 통신할 때 생기는
	    문제를 해결하기 위해서 Hadler를 사용하여 통신할 때 생기는 동기화 문제를 해결 하였다.
	    그러지 않으면 데이터가 이상하게 들어가거나 오류가 난다. */
	Handler mHandler = new Handler( ) {
		public void handleMessage(Message msg) {
			/* searchImageURLFromServer의 통신을 시작하는 곳이며 
			   itemCounter를 사용하여 searchImageURLFromServer의 호출 횟수를 조장한다 */
			if(msg.what == 1) {
				if(itemCounter < requestIdSet.size())
					searchImageURLFromServer(requestIdSet.get(itemCounter));
				else if(itemCounter == requestIdSet.size()) {
					itemCounter = 0; // searchImageURLFromServer 통신횟수 초기화
					view.progresOff();
					view.listviewOn();
					view.addMyRequestArrayList(myrequestItems);
				}
			}
			
			else if(msg.what == 2) { // 내가 요청한 것이 없을 때 동작
				view.progresOff();
				view.listviewOn();
				view.addMyRequestArrayList(myrequestItems);
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

	// 나의 요청을 등록하기 위해서 사용한다.
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

	// 내가 요청한 것에 다른 사용자들이 등록한 이미지들을 모두다 보여준다.
	@Override
	public void showRequestDetail(int aRequestIdIndex) {
		Intent intent = new Intent(getActivity( ), MyRequestGridViewDetailActivity.class);
		intent.putExtra(PARAM_REQUESTID_KEY, requestIdSet.get(aRequestIdIndex));
		startActivity(intent);
	}
	
	// 내가 요청한 것들을 가져오기 위한 통신
	public void searchMyRequestItemFromServer( ) {
		view.setInvisible(); // 스마일, 텍스트를 일단은 안보이게 한다.
		view.listviewOff();
		view.progressOn();
		
		MyRequestItemRequest getMyrequestItems = new MyRequestItemRequest(getActivity( ));
		try {
			getMyrequestItems.getItems(getMyrequestItemListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	// 내가 요청한 것들에서 다른 사용자가 등록한 이미지를 가져오기 위한 통신
	public void searchImageURLFromServer(String aRequestId) {
		RequestsRequest requestsRequest = new RequestsRequest(getActivity( ));
		try {
			requestsRequest.getImageURL(getImageURLListener, aRequestId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener getMyrequestItemListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;
			Message message = new Message( );
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			myrequestItems = new ArrayList<IMyRequestItem>();
			requestIdSet = new ArrayList<String>();
			
			if (jsonArray.length() != 0) {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonRequestObject = null;

					try {
						jsonRequestObject = jsonArray.getJSONObject(i);
						// 내가 요청한 것들의 PrimaryKey를 저장해서 이미지 url을 가져오는 통신을 할 때 사용한다.
						requestIdSet.add(jsonRequestObject.getString("id"));

						/* 여기서 빈 MyRequest를 만들어서 jsonRequestObject 넣은 이유는
						   searchImageURLFromServer에서 나온 결과물과 합치기 위해서이다. */
						MyRequest myrequest = new MyRequest();
						myrequest.setSaveJSONOjbect(jsonRequestObject);
						myrequestItems.add(myrequest);

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				/* getMyrequestItemListener통신이 끝났다는 것을 알린다음에
				   searchImageURLFromServer통신을 해야지 그렇지 않으면 꼬인다. 
				     이 문제를 해결하기 위해서 Handler를 사용하여 순서를 조정한다. */
				message.what = 1; // searchImageURLFromServer 통신시작!!
				mHandler.sendMessage((message));
			} else { 
				message.what = 2; // jsonArray가 없을때 동작한다.
				mHandler.sendMessage((message));
			}
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	HttpRequester.NetworkResponseListener getImageURLListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;
			JSONObject requestObject = null;
			Message message = new Message( );
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA); // 이미지 url 데이터들
				// 아까 저장해 두었던 JSONObject를 가지고 와서 통신을해서 가지고 온 url을 합친다.
				requestObject = ((MyRequest)myrequestItems.get(itemCounter)).getSavedJSONObject();
				requestObject.put("urlset", jsonArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	
			try {
				// 실제로 사용할 MyRequest객체를 생성한다. listview에서 사용하는 데이터는 여기서 만드는 데이터 들이다.
				MyRequest myrequest = new MyRequest(requestObject);
				myrequestItems.set(itemCounter, myrequest); // 해당 인덱스에 새로운 객체를 replace한다.
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			itemCounter++; // 통신을 한번했으니 itemCounter를 증가시켜서 통신 횟수에 제한을 준다.
			message.what = 1; // 한번 더 통신을 한다.
			mHandler.sendMessage((message));
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
}