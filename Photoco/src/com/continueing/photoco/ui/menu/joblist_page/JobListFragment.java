package com.continueing.photoco.ui.menu.joblist_page;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.reuse.listview.findingjoblist.MockRequestAbstractItem;
import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.ui.menu.joblist_page.joblist_detail_page.JobListDetailActivity;

public class JobListFragment extends Fragment implements ViewForJobListFragment.Controller{
	private ViewForJobListFragment view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ViewForJobListFragment(getActivity( ), inflater, container, this); // 뷰를 생성해 낸다.
     
        ArrayList<IFindingJobListItem> arrayList = new ArrayList<IFindingJobListItem>( );
       
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        
        view.addJobListItemArrayList(arrayList);
        return view.getRoot();
    }

	// 프래그먼트가 replece에 의해서 지워질 때 액션바에 생성한 탭을 삭제한다.
	@Override
	public void onDetach( )
	{
		super.onDetach();
	}

	@Override
	public void onItemSelected(int aPositions) {
		Intent intent = new Intent(getActivity( ), JobListDetailActivity.class);
		startActivity(intent);
	}
}