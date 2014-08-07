package com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.girdview.tag_gridview.ViewForArrayAdapterForTagGridView.ITagItem;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ArrayAdapterForMyRequestTag;
import com.continueing.photoco.ui.menu.myrequest_page.myrequest_newrequest_page.myrequest_newrequest_tag_page.listview.ViewForArrayAdapterForMyNewRequestTag;

public class ViewForMyNewRequestTagActivity extends AbstractViewForActivity{
	
	private String tagText = "";
	private Controller controller;
	private ListView lv_requestNewTag;	
	private EditText et_requestNewTagInput;
	private RelativeLayout rl_requestNewTagAdd;
	private ArrayList<ITagItem> arrayList;
	private ArrayAdapterForMyRequestTag arrayAdapterForMyRequestTag;
	
	public ViewForMyNewRequestTagActivity(Context context, Controller aController,  ArrayList<ITagItem> aArrayList) {
		super(context);
		controller = aController;
		arrayList = aArrayList;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_myrequest_new_tag, null);
	}

	@Override
	protected void initViews() {
		lv_requestNewTag = (ListView)findViewById(R.id.lv_request_new_tag);
		arrayAdapterForMyRequestTag = new ArrayAdapterForMyRequestTag(getContext( ), 0);
		lv_requestNewTag.setAdapter(arrayAdapterForMyRequestTag);
		
		et_requestNewTagInput = (EditText)findViewById(R.id.et_request_new_tag_input);
		rl_requestNewTagAdd = (RelativeLayout)findViewById(R.id.rl_request_new_tag_add);
	}
	
	@Override
	protected void setEvent() {
		et_requestNewTagInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				tagText = et_requestNewTagInput.getText().toString();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			
			@Override
			public void afterTextChanged(Editable s) { }
		});
		
		rl_requestNewTagAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.addTagItem(tagText);
			}
		});
		
		lv_requestNewTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(ViewForArrayAdapterForMyNewRequestTag.isDeleteButtonClicked == true){
					arrayList.remove(position);
					arrayAdapterForMyRequestTag.clear();
					arrayAdapterForMyRequestTag.addAll(arrayList);
					ViewForArrayAdapterForMyNewRequestTag.isDeleteButtonClicked = false;
				}
			}
		});
	}
	
	public void resetEditText( )
	{
		et_requestNewTagInput.setText("");
	}
	
	public void addItems(ArrayList<ITagItem> item)
	{
		arrayAdapterForMyRequestTag.clear();
		arrayAdapterForMyRequestTag.addAll(item);
	}
	
	public static interface Controller
	{
		public void addTagItem(String aTagText);
	}
}