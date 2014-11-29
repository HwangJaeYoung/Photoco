package com.continueing.photoco.reuse.page.location_page;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.reuse.page.location_page.listview.ArrayAdapterForLocationListView;
import com.continueing.photoco.reuse.page.location_page.listview.ViewForLocationListViewItem.ILocationItem;

public class ViewForLocationActivity extends AbstractViewForActivity {

	private EditText et_location;
	private Controller controller;
	private ListView lv_location_search;
	private ArrayAdapterForLocationListView arrayListAdapter;
	
	public ViewForLocationActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected void initViews() {
		et_location = (EditText)findViewById(R.id.et_request_new_location);
		lv_location_search = (ListView)findViewById(R.id.listview_request_new_location);
		arrayListAdapter = new ArrayAdapterForLocationListView(getContext( ), R.layout.item_location);
		lv_location_search.setAdapter(arrayListAdapter);
	}

	// 기존에 리스트뷰에 보이던 아이템들을 초기화하고 다시 보여준다.
	public void resetLocations(ArrayList<ILocationItem> aArrayList) {
		arrayListAdapter.clear();
		arrayListAdapter.addAll(aArrayList);
	}
	
	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(
				R.layout.activity_myrequest_new_location, null);
	}
	
	@Override
	protected void setEvent() {
		// 변화하는 문자에 대해 반응한다.
		et_location.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				controller.onSearchStringDelivered(et_location.getText().toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			
			@Override
			public void afterTextChanged(Editable s) { }
		});
		
		// 사용자가 원하는 지역을 선택하였을 때
		lv_location_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				controller.onLocationSelected(position);
			}
		});
	}
	
	public static interface Controller {
		public void onSearchStringDelivered(String aSearchString); // 사용자가 검색한 문자
		public void onLocationSelected(int aPosition); // 선택된 지역의 항목 번호
	}
}