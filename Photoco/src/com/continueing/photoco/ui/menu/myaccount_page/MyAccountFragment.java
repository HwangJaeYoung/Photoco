package com.continueing.photoco.ui.menu.myaccount_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyAccountFragment extends Fragment{
	
	private ViewForMyAccountFragment view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForMyAccountFragment(getActivity( ), inflater, container);
        return view.getRoot();
	}
}
