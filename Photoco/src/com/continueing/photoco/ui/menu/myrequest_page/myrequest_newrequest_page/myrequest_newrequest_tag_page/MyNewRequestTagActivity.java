package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.MockUp;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class MyNewRequestTagActivity extends ActionBarActivity implements ViewForMyNewRequestTagActivity.Controller{
	private ViewForMyNewRequestTagActivity view;
	private ArrayList<IMyRequestTagItem> myrequestTagItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar( ).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		myrequestTagItem = new ArrayList<IMyRequestTagItem>( );
		view = new ViewForMyNewRequestTagActivity(getApplicationContext(), this, myrequestTagItem); // 뷰를 생성해 낸다.
		setContentView(view.getRoot());		
		myrequestTagItem.add(new MockUp( ));
		view.addItem(myrequestTagItem);
	}

	@Override
	public void addTagItem() {
		if(myrequestTagItem.size() < 9)
		{
			if(myrequestTagItem.size() == 1)
			{
				if(myrequestTagItem.get(0).getTagText() != "")
				{
					myrequestTagItem.add(new MockUp( ));
					view.addItem(myrequestTagItem);
				}
				else
					Toast.makeText(getApplicationContext(), "fill the aobve items", Toast.LENGTH_SHORT).show( );
			}
			
			else
			{
				int counting = 0;
				for (int i = 0; i < myrequestTagItem.size(); i++) {
					if (myrequestTagItem.get(i).getTagText().equals("")) {
						counting++;
					}
				}
				
				if(counting == 0)
				{
					myrequestTagItem.add(new MockUp( ));
					view.addItem(myrequestTagItem);
				}
				
				else 
					Toast.makeText(getApplicationContext(), "채워라...", Toast.LENGTH_SHORT).show( );
			}
		}
		
		else
			Toast.makeText(getApplicationContext(), "can't make any tag items", Toast.LENGTH_SHORT).show( );
	}
}