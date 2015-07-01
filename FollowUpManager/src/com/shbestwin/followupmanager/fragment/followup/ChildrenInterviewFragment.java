package com.shbestwin.followupmanager.fragment.followup;

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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.fragment.followup.children.NeonateFragment;
import com.shbestwin.followupmanager.fragment.followup.children.YearsOld0Fragment;
import com.shbestwin.followupmanager.fragment.followup.children.YearsOld1_2Fragment;
import com.shbestwin.followupmanager.fragment.followup.children.YearsOld3_6Fragment;
import com.shbestwin.followupmanager.view.widget.TabMenuLayout;

/**
 * 
 * 儿童访视
 *
 * @version
 */
public class ChildrenInterviewFragment extends BaseFragment {
	private TabMenuLayout tabMenuLayout;

	private ViewPager bodyViewPager;
	private List<BaseFragment> contentFragmentList;

	public static ChildrenInterviewFragment newInstance() {
		ChildrenInterviewFragment fragment = new ChildrenInterviewFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_follow_up_children_interview, container, false);
		tabMenuLayout = (TabMenuLayout) rootView.findViewById(R.id.tabMenuLayout);
		bodyViewPager = (ViewPager) rootView.findViewById(R.id.bodyViewPager);
		bodyViewPager.setOffscreenPageLimit(3);// 这个很重要，不然动态载入的视图会被清空
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initTabView();
		initBodyView();
	}

	private void initTabView() {
		String[] tabMenu = getResources().getStringArray(R.array.ChildrenInterviewMenuItem);
		tabMenuLayout.renderMenu(tabMenu);
		tabMenuLayout.checkItem(0);

		tabMenuLayout.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				bodyViewPager.setCurrentItem(checkedId);
				renderPage(checkedId);
			}
		});
	}

	private void renderPage(int position) {
		contentFragmentList.get(position).onRenderPage();
	}

	/**
	 * 初始化内容视图
	 *
	 */
	private void initBodyView() {
		contentFragmentList = new ArrayList<BaseFragment>();
		contentFragmentList.add(NeonateFragment.newInstance());// 新生儿
		contentFragmentList.add(YearsOld0Fragment.newInstance());// 1岁内
		contentFragmentList.add(YearsOld1_2Fragment.newInstance());// 1-2岁
		contentFragmentList.add(YearsOld3_6Fragment.newInstance());// 3-6岁

		// contentViewPager.setPageTransformer(true, new
		// RotateDownTransformer());
		// 给ViewPager设置适配器
		bodyViewPager.setAdapter(new ContentFragmentPagerAdapter(getChildFragmentManager()));
		bodyViewPager.setCurrentItem(0);// 设置当前显示标签页为第一页
		bodyViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				tabMenuLayout.checkItem(position);
				renderPage(position);
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

	@Override
	public void onReset() {
		BaseFragment baseFragment = contentFragmentList.get(bodyViewPager.getCurrentItem());
		baseFragment.onReset();
	}

	@Override
	public void onSave() {
		BaseFragment baseFragment = contentFragmentList.get(bodyViewPager.getCurrentItem());
		baseFragment.onSave();
	}

	@Override
	public void onUpload() {
		BaseFragment baseFragment = contentFragmentList.get(bodyViewPager.getCurrentItem());
		baseFragment.onUpload();
	}
}
