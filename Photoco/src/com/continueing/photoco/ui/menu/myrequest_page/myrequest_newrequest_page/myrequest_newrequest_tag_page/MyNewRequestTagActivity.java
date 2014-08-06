package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Tag;
import com.continueing.photoco.reuse.girdview.tag_gridview.ViewForArrayAdapterForTagGridView.IMyRequestTagItem;

public class MyNewRequestTagActivity extends ActionBarActivity implements ViewForMyNewRequestTagActivity.Controller{
	private ViewForMyNewRequestTagActivity view;
	private ArrayList<IMyRequestTagItem> myrequestTagArrayList;
	public static final String PARAM_TAG_ARRAYLIST_KEY = "tagArrayList";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		myrequestTagArrayList = new ArrayList<IMyRequestTagItem>( );
		view = new ViewForMyNewRequestTagActivity(getApplicationContext(), this, myrequestTagArrayList);
		setContentView(view.getRoot());		
	}

	@Override
	public void addTagItem(String aTagText) {
		if(myrequestTagArrayList.size() < 9)
  		{
  			if(aTagText.equals(""))
  				Toast.makeText(getApplicationContext(), "insert tag to the textbox", Toast.LENGTH_SHORT).show( );
  			else
  			{
  				// 태그의 생성
  				Tag tag = new Tag( );
  				tag.setTagText(aTagText);
  				
  				myrequestTagArrayList.add(tag);
  				view.addItems(myrequestTagArrayList);
  				view.resetEditText( ); // EditText에 입력 후 입력창 초기화 하기위해서
  			}
  		}
  		else  // Tag 9개 초과
  			Toast.makeText(getApplicationContext(), "can't add tag because you exceed 9 tags", Toast.LENGTH_SHORT).show( );
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater menuInFlater = getMenuInflater();
		menuInFlater.inflate(R.menu.menu_myrequest_tag, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.item_menu_ok:
				Intent intent = new Intent( );
				intent.putExtra(PARAM_TAG_ARRAYLIST_KEY, myrequestTagArrayList);
				setResult(Activity.RESULT_OK, intent);
				finish( );
				return true;
			default :
				return super.onOptionsItemSelected(item);
		}
	}
}