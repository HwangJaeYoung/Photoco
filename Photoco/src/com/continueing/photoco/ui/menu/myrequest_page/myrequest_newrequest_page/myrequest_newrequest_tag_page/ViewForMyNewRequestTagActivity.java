package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ArrayAdapterForMyRequestTag;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag.IMyRequestTagItem;

public class ViewForMyNewRequestTagActivity extends AbstractViewForActivity{
	
	private Controller controller;
	private ArrayAdapterForMyRequestTag arrayAdapterForMyRequestTag;
	private ListView lv_requestNewTag;
	private EditText examTag;
	private String tagText="";
	
	private ImageButton ib;
	
	public ViewForMyNewRequestTagActivity(Context context, Controller aController, ArrayList<IMyRequestTagItem> item) {
		super(context);
		controller = aController;
		arrayAdapterForMyRequestTag.setArrayList(item);
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_request_new_tag, null);
	}

	@Override
	protected void initViews() {
		arrayAdapterForMyRequestTag = new ArrayAdapterForMyRequestTag(getContext( ), 0);
		lv_requestNewTag = (ListView)findViewById(R.id.lv_request_new_tag);
		
		LayoutInflater inflater  =  (LayoutInflater)getContext( ).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.footer_addtag, null, false);
		View view2 = inflater.inflate(R.layout.examedittext, null, false);
		
		ib = (ImageButton)view.findViewById(R.id.ib_myrequest_new_add_tag);
		
		lv_requestNewTag.addFooterView(view2, null, false);
		lv_requestNewTag.addFooterView(view, null, false);
		
		examTag = (EditText)findViewById(R.id.exam_tag);
		
		lv_requestNewTag.setAdapter(arrayAdapterForMyRequestTag);
	}
	
	public void resetEditText( )
	{
		examTag.setText("");
	}
	@Override
	protected void setEvent() {
		ib.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				controller.addTagItem(tagText);
			}
		});
		
		examTag.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				tagText = examTag.getText().toString();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {		
				
			}
		});
	}
	
	public void addItems(ArrayList<IMyRequestTagItem> item)
	{
		arrayAdapterForMyRequestTag.clear();
		arrayAdapterForMyRequestTag.addAll(item);
	}
	
	public static interface Controller
	{
		public void addTagItem(String tagText);
	}
}