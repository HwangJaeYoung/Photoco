package com.continueing.photoco.ui.menu.cart_page.cart_detail_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForCartDetailActivity extends AbstractViewForActivity{

	private Controller controller;
	private ImageButton ib_cartDetailRemove;
	
	public ViewForCartDetailActivity(Context context, Controller aController) {
		super(context);
		controller = aController;
	}

	@Override
	protected View inflate() {
		return LayoutInflater.from(getContext()).inflate(R.layout.activity_cart_detail, null);
	}

	@Override
	protected void initViews() {
		ib_cartDetailRemove = (ImageButton)findViewById(R.id.ib_cart_detail_remove);
	}

	@Override
	protected void setEvent() {
		ib_cartDetailRemove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.removeCartItem();				
			}
		});
	}
	
	public static interface Controller
	{
		public void removeCartItem( );
	}
}
