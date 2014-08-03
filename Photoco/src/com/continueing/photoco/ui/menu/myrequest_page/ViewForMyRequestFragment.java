package com.continueing.photoco.ui.menu.myrequest_page;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForFragment;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ArrayAdapterForMyRequestListView;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem;
import com.continueing.photoco.ui.menu.myrequest_page.listview.ViewForMyRequestListViewItem.IMyRequestItem;

public class ViewForMyRequestFragment extends AbstractViewForFragment {

	private Controller controller;
	private Button bt_createRequest;
	private ListView lv_myRequest;
	private ImageView iv_myrequestEmptyFace;
	private TextView tv_myrequestEmpty1;
	private TextView tv_myrequestEmpty2;
	private ImageView iv_myrequestEmptyArrow;
	
	private ArrayAdapterForMyRequestListView arrayAdapterForMyRequestListView;
	private ArrayList<IMyRequestItem> arrayList = new ArrayList<IMyRequestItem>( ); 

	public ViewForMyRequestFragment(Context context, LayoutInflater layoutInflater, ViewGroup container, Controller aController, ArrayList<IMyRequestItem> anArrayList) {
		super(context, layoutInflater, container);
		controller = aController;
		arrayList = anArrayList;
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_myrequest, container, false);
	}

	@Override
	protected void initViews() {
		iv_myrequestEmptyFace = (ImageView)findViewById(R.id.iv_myrequest_empty_face);
		tv_myrequestEmpty1 = (TextView)findViewById(R.id.tv_myrequest_empty1);
		tv_myrequestEmpty2 = (TextView)findViewById(R.id.tv_myrequest_empty2);
		iv_myrequestEmptyArrow = (ImageView)findViewById(R.id.iv_myrequest_empty_arrow);
		
		bt_createRequest = (Button)findViewById(R.id.bt_create_request);	
		lv_myRequest = (ListView)findViewById(R.id.lv_myrequest_info);
		arrayAdapterForMyRequestListView = new ArrayAdapterForMyRequestListView(getContext( ), 0);
		lv_myRequest.setAdapter(arrayAdapterForMyRequestListView);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext( ));
		prefs.edit().putBoolean("actionBar", false).apply();
		((FragmentActivity)getContext( )).getActionBar().setTitle(R.string.title_section2);
	}

	// NewRequest버튼을 클릭하였을 때
	@Override
	protected void setEvents() {
		bt_createRequest.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				controller.onNewRequest();				
			}
		});
		
		lv_myRequest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(ViewForMyRequestListViewItem.isDeleteButtonClicked == true){
					Log.i("position", ""+position);
					arrayList.remove(position);
					arrayAdapterForMyRequestListView.clear();
					arrayAdapterForMyRequestListView.addAll(arrayList);
					ViewForMyRequestListViewItem.isDeleteButtonClicked = false;
				}
				else if(ViewForMyRequestListViewItem.isDeleteButtonClicked == false)
				{
					controller.showRequestDetail( );
				}
			}
		});
	}

	// 사용자가 NewRequest한 모든 항목을 보여주기 위해서 만들어 놓음
	public void addMyRequestArrayList(ArrayList<IMyRequestItem> anArrayList)
	{
		if(anArrayList.size() != 0) // 초기에 하나라도 아이템이 있으면
			setInvisible( );
		
		arrayAdapterForMyRequestListView.clear();
		arrayAdapterForMyRequestListView.addAll(anArrayList);
	}

	// 추가 할 때 Empty일 때의 상태를 숨긴다.(스마일, 텍스트)
	public void setInvisible( )
	{
		iv_myrequestEmptyFace.setVisibility(ImageView.INVISIBLE);
		tv_myrequestEmpty1.setVisibility(TextView.INVISIBLE);
		tv_myrequestEmpty2.setVisibility(TextView.INVISIBLE);
		iv_myrequestEmptyArrow.setVisibility(ImageView.INVISIBLE);
	}
	
	public static interface Controller {
		public void onNewRequest( );
		public void showRequestDetail( );
	}
}