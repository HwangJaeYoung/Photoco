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
	
	private String tagText="";	
	private Controller controller;
	private ListView lv_requestNewTag;	
	private EditText et_myrequestEdittextTagFooter;
	private ImageButton ib_myrequestNewAddTagFooter;
	private ArrayAdapterForMyRequestTag arrayAdapterForMyRequestTag;
	
	public ViewForMyNewRequestTagActivity(Context context, Controller aController, ArrayList<IMyRequestTagItem> item) {
		super(context);
		controller = aController;
		arrayAdapterForMyRequestTag.setArrayList(item);
		// 삭제연산을 하기 위해서 ArrayAdapter에 추가 된 ArrayList 항목을 전달한다. 
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_request_new_tag, null);
	}

	@Override
	protected void initViews() {
		arrayAdapterForMyRequestTag = new ArrayAdapterForMyRequestTag(getContext( ), 0);
		lv_requestNewTag = (ListView)findViewById(R.id.lv_request_new_tag);
		
		// 태그추가와 EditText Footer를 생성한다.
		LayoutInflater inflater  =  (LayoutInflater)getContext( ).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View addTagFooter = inflater.inflate(R.layout.footer_add_tag, null, false);
		View editTextFooter = inflater.inflate(R.layout.footer_edittext_tag, null, false);
		
		// 태그추가와 EditText Footer를 추가한다.
		lv_requestNewTag.addFooterView(editTextFooter, null, false);
		lv_requestNewTag.addFooterView(addTagFooter, null, false);
		
		ib_myrequestNewAddTagFooter = (ImageButton)addTagFooter.findViewById(R.id.ib_myrequest_new_add_tag_footer);
		et_myrequestEdittextTagFooter = (EditText)findViewById(R.id.et_myrequest_edittext_tag_footer);
		
		lv_requestNewTag.setAdapter(arrayAdapterForMyRequestTag);
	}
	
	public void resetEditText( )
	{
		et_myrequestEdittextTagFooter.setText("");
	}
	
	@Override
	protected void setEvent() {
		ib_myrequestNewAddTagFooter.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				controller.addTagItem(tagText);
			}
		});
		
		et_myrequestEdittextTagFooter.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				tagText = et_myrequestEdittextTagFooter.getText().toString();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			
			@Override
			public void afterTextChanged(Editable s) { }
		});
	}
	
	public void addItems(ArrayList<IMyRequestTagItem> item)
	{
		arrayAdapterForMyRequestTag.clear();
		arrayAdapterForMyRequestTag.addAll(item);
	}
	
	public static interface Controller
	{
		public void addTagItem(String aTagText);
	}
}