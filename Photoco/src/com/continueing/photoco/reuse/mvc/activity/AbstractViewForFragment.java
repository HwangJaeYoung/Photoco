package com.continueing.photoco.reuse.mvc.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// 전체적인 구성은 AbstractViewForFragment와 비슷하므로 그 것들을 참고하자.
public abstract class AbstractViewForFragment {
	private Context context;
	private View root;

	public AbstractViewForFragment(Context context,LayoutInflater layoutInflater, ViewGroup container) {
		this.context = context;
		root = inflate(layoutInflater, container);
		initViews();
		setEvents();
	}

	abstract protected View inflate(LayoutInflater inflater, ViewGroup container);

	abstract protected void initViews();

	abstract protected void setEvents();
	
	// 액티비티를 포함 한 뷰가 아닌 프래그먼트에 인플레이트 된 뷰 안에서 찾는다.
	protected View findViewById(int aResourceId) {
		return root.findViewById(aResourceId);
	}

	public Context getContext() {
		return this.context;
	}

	public View getRoot() {
		return root;
	}
}