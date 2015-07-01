package com.shbestwin.followupmanager.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.setting.AboutFragment;
import com.shbestwin.followupmanager.fragment.setting.DeviceFragment;
import com.shbestwin.followupmanager.fragment.setting.FunctionFragment;
import com.shbestwin.followupmanager.fragment.setting.HelpFragment;
import com.shbestwin.followupmanager.fragment.setting.UserFragment;
import com.shbestwin.followupmanager.fragment.setting.VersionFragment;
import com.shbestwin.followupmanager.model.MenuItem;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout.OnMenuItemClickListener;
import com.shbestwin.followupmanager.view.widget.viewpager.transforms.FlipHorizontalTransformer;

/**
 * 
 * @ClassName: SettingFragment
 * @Description: 设置页
 *
 */
public class SettingFragment extends BaseFragment {

	private LeftMenuLayout leftMenuLayout;
	private View marginView;

	private ViewPager contentViewPager;
	private List<Fragment> contentFragmentList;

	public static SettingFragment newInstance() {
		SettingFragment fragment = new SettingFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_settting, container, false);
		leftMenuLayout = (LeftMenuLayout) rootView.findViewById(R.id.leftMenuLayout);
		marginView = rootView.findViewById(R.id.marginView);
		contentViewPager = (ViewPager) rootView.findViewById(R.id.contentViewPager);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initLeftMenu();
		initContentView();
	}

	/**
	 * 初始化左菜单
	 */
	private void initLeftMenu() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem(R.drawable.xtsz_menu_sbgl_selector, getString(R.string.xtsz_menu_item_sbgl)));// 设备管理
		menuItems.add(new MenuItem(R.drawable.xtsz_menu_yhgl_selector, getString(R.string.xtsz_menu_item_yhgl)));// 用户管理
		menuItems.add(new MenuItem(R.drawable.xtsz_menu_gnjs_selector, getString(R.string.xtsz_menu_item_gnjs)));// 功能介绍
		menuItems.add(new MenuItem(R.drawable.xtsz_menu_bbgx_selector, getString(R.string.xtsz_menu_item_bbgx)));// 版本更新
		menuItems.add(new MenuItem(R.drawable.xtsz_menu_bz_selector, getString(R.string.xtsz_menu_item_bz)));// 帮助
		menuItems.add(new MenuItem(R.drawable.xtsz_menu_gy_selector, getString(R.string.xtsz_menu_item_gy)));// 关于
		leftMenuLayout.renderMenu(menuItems);
		leftMenuLayout.setMarginView(marginView);

		leftMenuLayout.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public void onMenuItemClick(int position) {
				contentViewPager.setCurrentItem(position);
			}
		});
	}

	/**
	 * 初始化内容视图
	 *
	 */
	private void initContentView() {
		contentFragmentList = new ArrayList<Fragment>();
		contentFragmentList.add(DeviceFragment.newInstance());// 设备管理
		contentFragmentList.add(UserFragment.newInstance());// 用户管理
		contentFragmentList.add(FunctionFragment.newInstance());// 功能介绍
		contentFragmentList.add(VersionFragment.newInstance());// 版本更新
		contentFragmentList.add(HelpFragment.newInstance());// 帮助
		contentFragmentList.add(AboutFragment.newInstance());// 关于

		contentViewPager.setPageTransformer(true, new FlipHorizontalTransformer());
		// 给ViewPager设置适配器
		contentViewPager.setAdapter(new ContentFragmentPagerAdapter(getChildFragmentManager()));
		contentViewPager.setCurrentItem(0);// 设置当前显示标签页为第一页
		contentViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				leftMenuLayout.checkItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});// 页面变化时的监听器
	}

	private class ContentFragmentPagerAdapter extends FragmentPagerAdapter {

		public ContentFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return contentFragmentList.get(position);
		}

		@Override
		public int getCount() {
			return contentFragmentList.size();
		}
	}
}
