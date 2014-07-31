package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.Tag;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class MyNewRequestTagActivity extends ActionBarActivity implements ViewForMyNewRequestTagActivity.Controller{
	private ViewForMyNewRequestTagActivity view;
	private ArrayList<IMyRequestTagItem> myrequestTagArrayList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		myrequestTagArrayList = new ArrayList<IMyRequestTagItem>( );
		view = new ViewForMyNewRequestTagActivity(getApplicationContext(), this);
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
}