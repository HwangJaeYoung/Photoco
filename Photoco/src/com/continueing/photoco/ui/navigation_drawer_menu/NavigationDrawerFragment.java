package com.continueing.photoco.ui.navigation_drawer_menu;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.continueing.photoco.R;
import com.continueing.photoco.domain.MyInformation;
import com.continueing.photoco.reuse.network.HttpRequester;
import com.continueing.photoco.reuse.network.JsonResponseHandler;
import com.continueing.photoco.reuse.network.MyInformationRequest;

public class NavigationDrawerFragment extends Fragment {

	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
	private NavigationDrawerCallbacks mCallbacks;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerListView;
	private View mFragmentContainerView;
	private int mCurrentSelectedPosition = 0;
	private Button bt_setting;
	private MyInformation myInformation;
	private View root;
	
	// 드로우워 메뉴 상단에 변하는 데이터들을 기록하기 위한 선언들
	private TextView tv_drawableName;
	private TextView tv_request;
	private TextView tv_job;
	private TextView tv_credits;

	public NavigationDrawerFragment() { }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null) // 저장되어 있던 선택된 항목의 번호를 줌. 
			mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);

		// 첫번째 또는 마지막에 선택된 항목을 보여준다.
		selectItem(mCurrentSelectedPosition);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true); // 메뉴를 사용한다는 것을 알려준다.
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 해당 프레그먼트의 뷰를 inflate한다.(드로워 메뉴)
		root = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
		mDrawerListView = (ListView) root.findViewById(R.id.lv_navigation_drawer_menu);
		
		tv_drawableName = (TextView)root.findViewById(R.id.tv_drawable_name);
		tv_request = (TextView)root.findViewById(R.id.tv_request);
		tv_job = (TextView)root.findViewById(R.id.tv_job);
		tv_credits = (TextView)root.findViewById(R.id.tv_credits);
		
		mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem(position);
			}
		});
		
		ArrayAdapterForNavigationDrawerMenuListView arrayAdapter = new ArrayAdapterForNavigationDrawerMenuListView(getActivity(), 0);

		arrayAdapter.add(new ViewForNavigationDrawerMenuListViewItem.DrawerMenuItem(R.drawable.search_ic, "FindingJobs"));
		arrayAdapter.add(new ViewForNavigationDrawerMenuListViewItem.DrawerMenuItem(R.drawable.request_ic, "MyRequests"));
		arrayAdapter.add(new ViewForNavigationDrawerMenuListViewItem.DrawerMenuItem(R.drawable.job_ic, "Joblist"));
		arrayAdapter.add(new ViewForNavigationDrawerMenuListViewItem.DrawerMenuItem(R.drawable.myphoto_ic, "MyPhoto"));
		arrayAdapter.add(new ViewForNavigationDrawerMenuListViewItem.DrawerMenuItem(R.drawable.marketplace_ic, "Marketplace"));
		arrayAdapter.add(new ViewForNavigationDrawerMenuListViewItem.DrawerMenuItem(R.drawable.cart_ic, "Cart"));
		arrayAdapter.add(new ViewForNavigationDrawerMenuListViewItem.DrawerMenuItem(R.drawable.account_ic, "MyAccount"));
		mDrawerListView.setAdapter(arrayAdapter);
		return root;
	}

	// 사용자가 드로워 메뉴 중 하나를 선택 하였을 때
	private void selectItem(int position) {
		mCurrentSelectedPosition = position;

		if (mDrawerLayout != null) { // 열려있는 드로워를 닫아 버린다.
			mDrawerLayout.closeDrawer(mFragmentContainerView);
		}
		// 처음에는 index(0)에 해당하는 findingjob을 켜 놓는다.
		if (mCallbacks != null) { // 클릭시 수행 할 행동
			mCallbacks.onNavigationDrawerItemSelected(position);
		}
	}

	public void setUp(int fragmentId, DrawerLayout drawerLayout) {
		// 프래그먼트의 컨테이너뷰(부모 뷰)를 찾는다고 생각하면 된다.
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setIcon(android.R.color.transparent);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#323a45")));
		
		// ActionBarDrawerToggle은 navigation drawer와 action bar app icon을 연결해 서로 상호 작용이 가능하게 한다.
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), // host Activity
		mDrawerLayout, // DrawerLayout 객체
		R.drawable.bt_menu, // 네비게이션 이미지 
		R.string.title_section1, // 열었을 때 설명 
		R.string.title_section1 // 닫았을 때 설명 
		) {
			// 드로워를 닫았을 때 동작하는 방식
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) 
                    return;

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                
                boolean actionExist = prefs.getBoolean("actionBar", false);

                if(actionExist == true) {
                	ActionBar actionBar = getActionBar();
               		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
                }
            }

            // 드로워를 열었을 때 동작하는 방식
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded())
                    return;
                
                searchMyInformationFromServer( ); // 열었을때 사용자의 아이템정보를 갱신한다.
                
                ActionBar actionBar = getActionBar();
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD); 
            }
        };

		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		// Setting버튼을 위해서 구성해 놓음
		bt_setting = (Button)root.findViewById(R.id.bt_setting);
		bt_setting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallbacks.onSettingButtonSelected();
			}
		});
	}
	
	// 사용자의 정보를 갱신하는 통신을 하기 위한 메소드
	public void searchMyInformationFromServer( ) {
		MyInformationRequest myInformationRequest = new MyInformationRequest(getActivity());
		
		try {
			myInformationRequest.getMyInformation(getMyInformationListener);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	HttpRequester.NetworkResponseListener getMyInformationListener = new HttpRequester.NetworkResponseListener() {
		@Override
		public void onSuccess(JSONObject jsonObject) { 
			JSONObject tempJSONObject = null;
			
			try {
				tempJSONObject = jsonObject.getJSONObject(JsonResponseHandler.PARM_DATA);
				myInformation = new MyInformation(tempJSONObject);			
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			// 사용자 아이템정보 갱신부분
			tv_drawableName.setText(myInformation.getUserName()); // 현재 로그인한 사용자의 이름을 기입한다.
			tv_request.setText(myInformation.getNumberOfRequests());
			tv_job.setText(myInformation.getNumberOfJobs());
			tv_credits.setText(myInformation.getCoin());
		}	
		
		@Override
		public void onFail(JSONObject jsonObject, int errorCode) { }
	};
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			// NavigationDrawerCallbacks를 구현하고 있는 액티비티를 가지고 온다.
			mCallbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	// 현재 어떤 메뉴를 눌렸는지에 대한 상태를 저장한다.
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	// 메뉴버튼을 생성한다.(여기에서는 "Photoco"버튼)
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	} 
	
	// 메뉴버튼이 클릭 되었을 때 할 행동정의("Photoco"버튼을 눌렸을 때)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item))
			return true;
		return super.onOptionsItemSelected(item);
	}
	
	// 변화가 일어났을 때 해줘야하는 작업에 대해 기술한다.
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

	// 현재 액티비티의 ActionBar를 알려준다.
	private ActionBar getActionBar() {
		return ((ActionBarActivity) getActivity()).getSupportActionBar();
	}

	// 이 프래그먼트를 사용하는 액티비티들은 무조건 이 인터페이스를 구현해야 한다.
	public static interface NavigationDrawerCallbacks {
		// 네비게이션 드로워의 항목이 선택되었을 때 호출 되는 메소드
		void onNavigationDrawerItemSelected(int position);
		void onSettingButtonSelected( );
	}
}