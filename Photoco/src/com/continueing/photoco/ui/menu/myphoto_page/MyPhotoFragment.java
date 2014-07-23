package com.continueing.photoco.ui.menu.myphoto_page;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.ui.menu.myphoto_page.myphoto_detail_page.MyPhotoDetailActivity;
import com.continueing.photoco.ui.menu.myphoto_page.myphoto_newphoto_page.MyPhotoNewPhotoActivity;

public class MyPhotoFragment extends Fragment implements ViewForMyPhotoFragment.Controller {
	private ViewForMyPhotoFragment view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForMyPhotoFragment(getActivity( ), inflater, container, this);
        
        ArrayList<String> list = new ArrayList<String>( );
        
        list.add("http://farm7.staticflickr.com/6101/6853156632_6374976d38_c.jpg");
        list.add("http://farm8.staticflickr.com/7232/6913504132_a0fce67a0e_c.jpg");
        list.add("http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg");
        list.add("http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg");
        list.add("http://farm9.staticflickr.com/8208/8219397252_a04e2184b2.jpg");
        list.add("http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg");
        list.add("http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg");
        list.add("http://farm9.staticflickr.com/8060/8173387478_a117990661.jpg");
		list.add("http://farm9.staticflickr.com/8183/8088373701_c9281fc202.jpg");
		list.add("http://farm9.staticflickr.com/8185/8081514424_270630b7a5.jpg");
		list.add("http://farm9.staticflickr.com/8462/8005636463_0cb4ea6be2.jpg");
		list.add("http://farm9.staticflickr.com/8306/7987149886_6535bf7055.jpg");
		list.add("http://farm9.staticflickr.com/8444/79F47923460_18ffdce3a5.jpg");
		list.add("http://farm9.staticflickr.com/8182/7941954368_3c88ba4a28.jpg");
		list.add("http://farm9.staticflickr.com/8304/7832284992_244762c43d.jpg");
		list.add("http://farm9.staticflickr.com/8163/7709112696_3c7149a90a.jpg");
		list.add("http://farm8.staticflickr.com/7127/7675112872_e92b1dbe35.jpg");
		list.add("http://farm8.staticflickr.com/7111/7429651528_a23ebb0b8c.jpg");
		list.add("http://farm9.staticflickr.com/8288/7525381378_aa2917fa0e.jpg");
		list.add("http://farm6.staticflickr.com/5336/7384863678_5ef87814fe.jpg");
		list.add("http://farm8.staticflickr.com/7102/7179457127_36e1cbaab7.jpg");
		list.add("http://farm8.staticflickr.com/7086/7238812536_1334d78c05.jpg");
		list.add("http://farm8.staticflickr.com/7243/7193236466_33a37765a4.jpg");
		list.add("http://farm8.staticflickr.com/7251/7059629417_e0e96a4c46.jpg");
		list.add("http://farm8.staticflickr.com/7084/6885444694_6272874cfc.jpg");  
        list.add("http://farm7.staticflickr.com/6101/6853156632_6374976d38_c.jpg");
        
        view.addItem(list);
        
        return view.getRoot( );
    }
	
	@Override
	public void onPhotoSelected() {
		Intent intent = new Intent(getActivity( ), MyPhotoDetailActivity.class);
		startActivity(intent);
	}

	@Override
	public void onNewPhotoSelected() {
		Intent intent = new Intent(getActivity( ), MyPhotoNewPhotoActivity.class);
		startActivity(intent);
	}}