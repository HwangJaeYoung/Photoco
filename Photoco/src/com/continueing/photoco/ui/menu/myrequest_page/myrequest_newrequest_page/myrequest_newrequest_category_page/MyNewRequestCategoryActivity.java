package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import com.continueing.photoco.domain.Category;
import com.continueing.photoco.reuse.network.CategoryRequest;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.MyNewRequestActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_category_page.listview.ViewForMyNewRequestCategoryListViewItem.IMyRequestCategoryItem;

public class MyNewRequestCategoryActivity extends ActionBarActivity implements ViewForMyRequestCategoryActivity.Controller{
	
	private ViewForMyRequestCategoryActivity view;
	private ArrayList<IMyRequestCategoryItem> categorys;
	private Handler mHandler;
	private Runnable mRunnable;
	public static final String PARAM_CATEGORY_ACTIVITY_KEY ="location";
	public static final String PARAM_PRIMARY_KEY = "primaryKey";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		view = new ViewForMyRequestCategoryActivity(getApplicationContext(), this);
		setContentView(view.getRoot());
		searchCategoryFromServer( );
	}

	public void searchCategoryFromServer( ) {
		CategoryRequest categoryRequest = new CategoryRequest(getApplicationContext());
		try {
			categoryRequest.getCategory(categoryListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onCategorySelected(int aPosition) {
		IMyRequestCategoryItem item = categorys.get(aPosition);
		Intent intent = new Intent( );
		intent.putExtra(MyNewRequestActivity.PARAM_CATEGORY_CHECKED_KEY, aPosition);
		intent.putExtra(PARAM_CATEGORY_ACTIVITY_KEY, item.getName());
		intent.putExtra(PARAM_PRIMARY_KEY, item.getId());
		setResult(Activity.RESULT_OK, intent);
		finish( );	
	}
	
	HttpRequester.NetworkResponseListener categoryListener = new HttpRequester.NetworkResponseListener( ){

		@Override
		public void onSuccess(JSONObject jsonObject) {
			JSONArray jsonArray = null;
			
			try {
				jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			categorys = new ArrayList<IMyRequestCategoryItem>();
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonCategoryObject = null;
				
				try {
					jsonCategoryObject = jsonArray.getJSONObject(i);
					
					Category category = new Category(jsonCategoryObject);
					categorys.add(category);
				} catch (JSONException e) {
					e.printStackTrace();
				}				
			}	
			view.resetCategory(categorys);
			
			// 액티비티를 실행하였을 때, 체크표시 때문에 Handler를 사용하였다.
			mRunnable = new Runnable() {
				@Override
				public void run() {
					view.checkedCategory(getIntent().getIntExtra(MyNewRequestActivity.PARAM_CATEGORY_CHECKED_KEY, 0));
				}
			};

			mHandler = new Handler();
			mHandler.postDelayed(mRunnable, 500);
		}

		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};	
}