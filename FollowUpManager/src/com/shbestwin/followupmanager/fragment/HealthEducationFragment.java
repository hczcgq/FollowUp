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
import com.shbestwin.followupmanager.fragment.education.ComprehensiveEvaluationFragment;
import com.shbestwin.followupmanager.fragment.education.DietGuidanceFragment;
import com.shbestwin.followupmanager.fragment.education.HealthKnowledgeFragment;
import com.shbestwin.followupmanager.fragment.education.ObesityAnalysisFragment;
import com.shbestwin.followupmanager.fragment.education.RiskFactorFragment;
import com.shbestwin.followupmanager.fragment.education.SportPrescriptionFragment;
import com.shbestwin.followupmanager.model.MenuItem;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout.OnMenuItemClickListener;

/**
 * 
 * @ClassName: HealthEducationFragment
 * @Description: 健康教育
 *
 */
public class HealthEducationFragment extends BaseIDCardInfoFragment {
	private LeftMenuLayout leftMenuLayout;
	private View marginView;

	private ViewPager contentViewPager;
	private List<Fragment> contentFragmentList;

	public static HealthEducationFragment newInstance() {
		HealthEducationFragment fragment = new HealthEducationFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_health_education, container, false);
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
		initOperateViews();
	}

	/**
	 * 初始化左菜单
	 */
	private void initLeftMenu() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem(R.drawable.jkjy_menu_fpfx_selector, getString(R.string.jkjy_menu_item_fpfx))); //肥胖分析
		menuItems.add(new MenuItem(R.drawable.jkjy_menu_fxys_selector, getString(R.string.jkjy_menu_item_fxys)));// 风险因素
		menuItems.add(new MenuItem(R.drawable.jkjy_menu_sszd_selector, getString(R.string.jkjy_menu_item_sszd)));// 膳食指导
		menuItems.add(new MenuItem(R.drawable.jkjy_menu_ydcf_selector, getString(R.string.jkjy_menu_item_ydcf)));// 运动处方
		menuItems.add(new MenuItem(R.drawable.jkjy_menu_zhpg_selector, getString(R.string.jkjy_menu_item_zhpg)));// 综合评估
		menuItems.add(new MenuItem(R.drawable.jkjy_menu_jkzs_selector, getString(R.string.jkjy_menu_item_jkzs)));// 健康知识
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
		contentFragmentList.add(ObesityAnalysisFragment.newInstance());//肥胖分析
		contentFragmentList.add(RiskFactorFragment.newInstance());// 风险因素
		contentFragmentList.add(DietGuidanceFragment.newInstance());// 膳食指导
		contentFragmentList.add(SportPrescriptionFragment.newInstance());// 运动处方
		contentFragmentList.add(ComprehensiveEvaluationFragment.newInstance());// 综合评估
		contentFragmentList.add(HealthKnowledgeFragment.newInstance());// 健康知识

		// contentViewPager.setPageTransformer(true, new
		// RotateDownTransformer());
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

	private void initOperateViews() {

	}

}
