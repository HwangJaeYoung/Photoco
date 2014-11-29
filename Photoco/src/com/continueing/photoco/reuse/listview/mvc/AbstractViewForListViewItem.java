package com.continueing.photoco.reuse.listview.mvc;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/* 1. ListView의 아이템을 생성해내는 뷰이다. 일반적으로 뷰를 구성하는 부분은 
     어댑터 부분의 getView안에서 구성하지만 여기서는 mvc적용을 하기위해 분리하였다. */

/* LinearLayout의 layout_width, layout_height는 기본적으로 0으로 되어있다. 
   ListView에서 ViewForRequestAbstractInfoListViewItem라는 뷰를 추가 할 때
   addView나 setContentView에서 디폴트 레이아웃 파라미터를 주는 것 처럼 이 뷰에 
     디폴트 레이아웃 파라미터를 주기 때문에 여기서 굳이 xml에서 처럼 설정해 줄 필요가 없다. 
     아마 ListView는 디폴트 레이아웃 파라미터로 layout_height="wrap_content" 
   layout_width="match_parent"를 지원하는 것 같다. */

public abstract class AbstractViewForListViewItem extends LinearLayout {
    private View root;

    public AbstractViewForListViewItem(Context context) {
        super(context);
        root = inflate(); // 실질적인 뷰를 생성해내는 부분
        initViews();
        setEvents();
    }

    protected abstract View inflate(); // inflate
    protected abstract void initViews(); // findByViewId
    protected abstract void setEvents(); // click event etc...
    protected abstract void setData(IListViewItem aIListViewItem); // insert View info

    protected View findViewById_(int aResourceId) {
        return root.findViewById(aResourceId);
    }
}