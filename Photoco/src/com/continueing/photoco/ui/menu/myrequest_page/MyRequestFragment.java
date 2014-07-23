package com.continueing.photoco.ui.menu.myrequest_page;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItem;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.MyNewRequestActivity;

public class MyRequestFragment extends Fragment implements ViewForMyRequestFragment.Controller{
	private ViewForMyRequestFragment view;
	private int i;
	public static final int REQUEST_CODE_GET_QUERY = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = new ViewForMyRequestFragment(getActivity( ), inflater, container, this); // 뷰를 생성해 낸다.
		
		 /*ArrayList<IMyRequestItem>  arrayList= new ArrayList<IMyRequestItem>( );
		
		arrayList.add(new Mockup("Mock" + i++ ));
		arrayList.add(new Mockup("Mock" + i++ ));
		arrayList.add(new Mockup("Mock" + i++ ));
		arrayList.add(new Mockup("Mock" + i++ ));
		arrayList.add(new Mockup("Mock" + i++ ));
		arrayList.add(new Mockup( "Mock" + i++));
		arrayList.add(new Mockup( "Mock" + i++));
		arrayList.add(new Mockup("Mock" + i++ ));
		arrayList.add(new Mockup("Mock" + i++)); */

		return view.getRoot(); 
    }

	@Override
	public void onNewRequest() {
		Intent intent = new Intent(getActivity( ), MyNewRequestActivity.class);
		startActivityForResult(intent, REQUEST_CODE_GET_QUERY);
	}
	
	// NewRequest에서 Submit버튼을 클릭하였을 때 동작한다. 즉 하나의 요청을 만들어 낸다.
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_GET_QUERY)
			if(resultCode == Activity.RESULT_OK)
				view.addMyRequestObject(new Mockup("Mock" + i++));
	}
}