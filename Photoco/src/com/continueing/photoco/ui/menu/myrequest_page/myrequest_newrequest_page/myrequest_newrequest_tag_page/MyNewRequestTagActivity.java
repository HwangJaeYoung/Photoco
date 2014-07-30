package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.Toast;

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
		view = new ViewForMyNewRequestTagActivity(getApplicationContext(), this, myrequestTagArrayList);
		setContentView(view.getRoot());		
	}

	@Override
	public void addTagItem(String aTagText) {
		if(myrequestTagArrayList.size() < 9)
		{
			if(aTagText.equals(""))
				Toast.makeText(getApplicationContext(), "insert tag text", Toast.LENGTH_SHORT).show( );
			else
			{
				myrequestTagArrayList.add(new Tag(aTagText));
				view.addItems(myrequestTagArrayList);
				view.resetEditText( );
			}
		}
		else
			Toast.makeText(getApplicationContext(), "can't add tags because exeed 9tags!!", Toast.LENGTH_SHORT).show( );
	}
}