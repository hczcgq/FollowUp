package com.shbestwin.followupmanager.fragment.examination;

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
import com.shbestwin.followupmanager.view.widget.TabMenuLayout;

/**
 * 
 * 老年评估
 *
 * @version
 */
public class AgednessAssessmentFragment extends BaseFragment {

	private TabMenuLayout tabMenuLayout;
	
	private ViewPager bodyViewPager;
	private List<Fragment> contentFragmentList;

	public static AgednessAssessmentFragment newInstance() {
		AgednessAssessmentFragment fragment = new AgednessAssessmentFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_examination_agedness_assessment, container, false);
		tabMenuLayout = (TabMenuLayout) rootView.findViewById(R.id.tabMenuLayout);
		bodyViewPager = (ViewPager) rootView.findViewById(R.id.bodyViewPager);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initTabView();
		initBodyView();
	}

	private void initTabView() {
		String[] tabMenu = getResources().getStringArray(R.array.jktjAgednessAssessmentTabMenu);
		tabMenuLayout.renderMenu(tabMenu);
		tabMenuLayout.checkItem(0);

		tabMenuLayout.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				bodyViewPager.setCurrentItem(checkedId);
			}
		});
	}
	
	/**
	 * 初始化内容视图
	 *
	 */
	private void initBodyView() {
		contentFragmentList = new ArrayList<Fragment>();
		contentFragmentList.add(AgednessTestFragment.newInstance(AgednessTestFragment.TYPE_SELF_CARE));// 自理评估
		contentFragmentList.add(AgednessTestFragment.newInstance(AgednessTestFragment.TYPE_DEPRESSION));// 抑郁评估
		contentFragmentList.add(AgednessTestFragment.newInstance(AgednessTestFragment.TYPE_INTELLIGENCE));// 智力评估

		// contentViewPager.setPageTransformer(true, new
		// RotateDownTransformer());
		// 给ViewPager设置适配器
		bodyViewPager.setAdapter(new ContentFragmentPagerAdapter(getChildFragmentManager()));
		bodyViewPager.setCurrentItem(0);// 设置当前显示标签页为第一页
		bodyViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				tabMenuLayout.checkItem(position);
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
	public void onSave() {
		super.onSave();
	}
}
