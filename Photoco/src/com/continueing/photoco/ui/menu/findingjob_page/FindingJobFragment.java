package com.continueing.photoco.ui.menu.findingjob_page;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.continueing.photoco.reuse.listview.findingjoblist.MockRequestAbstractItem;
import com.continueing.photoco.reuse.listview.findingjoblist.ViewForFindingJobListViewItem.IFindingJobListItem;
import com.continueing.photoco.ui.menu.findingjob_page.findingjob_detail_page.FindingJobDetailActivity;

public class FindingJobFragment extends Fragment implements ViewForFindingJobFragment.Controller {
	private ViewForFindingJobFragment view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// this는 Controller를 위해서 넣어주는 것이다.
        view = new ViewForFindingJobFragment(getActivity( ), inflater, container, this); // 뷰를 생성해 낸다.
        view.addActionBarTab(getActivity()); // 액션바에 탭을 생성한다.

        ArrayList<IFindingJobListItem> arrayList = new ArrayList<IFindingJobListItem>( );
       
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
        arrayList.add(new MockRequestAbstractItem());
      
        // ViewForFindingJobFragment(View)에 데이터(Model)를 FindingJobFragment(Controller)에서 넘겨준다.
        view.addFindjobItemArrayList(arrayList);
        return view.getRoot();
    }

	// ListView에서 특정한 항목이 눌렸을 때 호출되는 메소드
	@Override
	public void onRequestSeleted(int aPosition) {
		// 디테일한 정보를 보여주는 새로운 액티비티를 띄운다.
		Intent intent = new Intent(getActivity( ), FindingJobDetailActivity.class);
		startActivity(intent);
	}
	
	// 프래그먼트가 replece에 의해서 지워질 때 액션바에 생성한 탭을 삭제한다.
	@Override
	public void onDetach( )
	{
		super.onDetach();
		view.removeActionBarTab();
	}
}