package com.continueing.photoco.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import com.continueing.photoco.R;
import com.continueing.photoco.reuse.etc.BackPressCloseHandler;
import com.continueing.photoco.ui.menu.cart_page.CartFragment;
import com.continueing.photoco.ui.menu.findingjob_page.FindingJobFragment;
import com.continueing.photoco.ui.menu.joblist_page.JobListFragment;
import com.continueing.photoco.ui.menu.marketplace_page.MarketplaceFragment;
import com.continueing.photoco.ui.menu.myaccount_page.MyAccountFragment;
import com.continueing.photoco.ui.menu.myphoto_page.MyPhotoFragment;
import com.continueing.photoco.ui.menu.myrequest_page.MyRequestFragment;
import com.continueing.photoco.ui.navigation_drawer_menu.NavigationDrawerFragment;
import com.continueing.photoco.ui.setting_page.SettingActivity;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private BackPressCloseHandler backPressCloseHandler;
	private int currentMenuIndex=-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		backPressCloseHandler = new BackPressCloseHandler(this);
		setContentView(R.layout.activity_main);
		// 정적으로 정의된 프래그먼트이므로 여기서는 라이프사이클을 수행한 프래그먼트가 넘어오게 된다.
		mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager( ).findFragmentById(R.id.navigation_drawer);
	    mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout)findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
        int previousMenuIndex = currentMenuIndex;
        currentMenuIndex = position; // 현재 사용자가 클릭한 메뉴의 번호, 처음에는 0이 넘어온다.
        if(currentMenuIndex != previousMenuIndex) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (position) {
            	// add는 기존의 것을 그대로 놔두며 추가하고, replace는 기존의 것을 제거하고 추가한다.
            	// 동적으로 프래그먼트를 정의하며, 레이아웃에 추가 될 때 라이프사이클을 돈다.
                case 0: // FindingJob Menu(Home)
                    transaction.replace(R.id.container, new FindingJobFragment()).commit();
                    break;
                case 1: // MyRequest
                	transaction.replace(R.id.container, new MyRequestFragment()).commit();
                    break;
                case 2: // JobList
                	transaction.replace(R.id.container, new JobListFragment()).commit();
                    break;
                case 3: // MyPhoto
                	transaction.replace(R.id.container, new MyPhotoFragment()).commit();
                    break;
                case 4: // Marketplace
                	transaction.replace(R.id.container, new MarketplaceFragment()).commit();
                    break;
                case 5: // Cart
                	transaction.replace(R.id.container, new CartFragment()).commit();
                    break;
                case 6: // MyAccount
                	transaction.replace(R.id.container, new MyAccountFragment()).commit();
                    break;
                default: // etc...
                    break;
            }
        }
	}

	// 설정 버튼을 눌렸을 때 동작하는 메소드
	@Override
	public void onSettingButtonSelected() {
		Intent intent = new Intent(this, SettingActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		backPressCloseHandler.onBackPressed();
	}
}