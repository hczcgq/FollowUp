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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.examination.AgednessAssessmentFragment;
import com.shbestwin.followupmanager.fragment.examination.GeneralExaminationFragment;
import com.shbestwin.followupmanager.fragment.examination.PhysiqueIdentifyFragment;
import com.shbestwin.followupmanager.fragment.examination.PsychologicaAssessmentFragment;
import com.shbestwin.followupmanager.fragment.examination.QuickExaminationFragment;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.MenuItem;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout.OnMenuItemClickListener;

/**
 * 
 * @ClassName: HealthExaminationFragment
 * @Description: 健康体检
 *
 */
public class HealthExaminationFragment extends BaseIDCardInfoFragment {
	private LeftMenuLayout leftMenuLayout;
	private View marginView;

	private ViewPager contentViewPager;
	private List<BaseFragment> contentFragmentList;

	private TextView examinationRegisterTextView, uploadTextView, saveTextView, conclusionTextView, printTextView;

	public static HealthExaminationFragment newInstance() {
		HealthExaminationFragment fragment = new HealthExaminationFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_health_examination, container, false);
		leftMenuLayout = (LeftMenuLayout) rootView.findViewById(R.id.leftMenuLayout);
		marginView = rootView.findViewById(R.id.marginView);
		contentViewPager = (ViewPager) rootView.findViewById(R.id.contentViewPager);
		contentViewPager.setOffscreenPageLimit(4);// 这个很重要，不然动态载入的视图会被清空

		examinationRegisterTextView = (TextView) rootView.findViewById(R.id.examinationRegisterTextView);
		uploadTextView = (TextView) rootView.findViewById(R.id.uploadTextView);
		saveTextView = (TextView) rootView.findViewById(R.id.saveTextView);
		conclusionTextView = (TextView) rootView.findViewById(R.id.conclusionTextView);
		printTextView = (TextView) rootView.findViewById(R.id.printTextView);
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
		menuItems.add(new MenuItem(R.drawable.jktj_menu_kstj_selector, getString(R.string.jktj_menu_item_kstj)));// 快速体检
		menuItems.add(new MenuItem(R.drawable.jktj_menu_pttj_selector, getString(R.string.jktj_menu_item_pttj)));// 普通体检
		menuItems.add(new MenuItem(R.drawable.jktj_menu_tzbs_selector, getString(R.string.jktj_menu_item_tzbs)));// 体质辨识
		menuItems.add(new MenuItem(R.drawable.jktj_menu_xlpg_selector, getString(R.string.jktj_menu_item_xlpg)));// 心理评估
		menuItems.add(new MenuItem(R.drawable.jktj_menu_lnpg_selector, getString(R.string.jktj_menu_item_lnpg)));// 老年评估
		leftMenuLayout.renderMenu(menuItems);
		leftMenuLayout.setMarginView(marginView);

		leftMenuLayout.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public void onMenuItemClick(int position) {
				contentViewPager.setCurrentItem(position);
				renderPage(position);
			}
		});
	}

	private void renderPage(int position) {
		contentFragmentList.get(position).onRenderPage();
		conclusionTextView.setEnabled(position == 0 ? true : false);
		printTextView.setEnabled(position == 0 ? true : false);
	}

	/**
	 * 初始化内容视图
	 *
	 */
	private void initContentView() {
		contentFragmentList = new ArrayList<BaseFragment>();
		contentFragmentList.add(QuickExaminationFragment.newInstance());// 快速体检
		contentFragmentList.add(GeneralExaminationFragment.newInstance());// 普通体检
		contentFragmentList.add(PhysiqueIdentifyFragment.newInstance());// 体质辨识
		contentFragmentList.add(PsychologicaAssessmentFragment.newInstance());// 心理评估
		contentFragmentList.add(AgednessAssessmentFragment.newInstance());// 老年评估

		// contentViewPager.setPageTransformer(true, new
		// RotateDownTransformer());
		// 给ViewPager设置适配器
		contentViewPager.setAdapter(new ContentFragmentPagerAdapter(getChildFragmentManager()));
		contentViewPager.setCurrentItem(0);// 设置当前显示标签页为第一页
		contentViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				leftMenuLayout.checkItem(position);
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

	private void initOperateViews() {
		// 体检登记，创建一条新记录
		examinationRegisterTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				examinationRegister();
				BaseFragment baseFragment = contentFragmentList.get(contentViewPager.getCurrentItem());
				baseFragment.onReset();
			}
		});
		uploadTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BaseFragment baseFragment = contentFragmentList.get(contentViewPager.getCurrentItem());
				baseFragment.onUpload();
			}
		});

		saveTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BaseFragment baseFragment = contentFragmentList.get(contentViewPager.getCurrentItem());
				baseFragment.onSave();
			}
		});
		conclusionTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BaseFragment baseFragment = contentFragmentList.get(contentViewPager.getCurrentItem());
				baseFragment.onConclusion();
			}
		});
		printTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
				if (archiveInfo == null) {
					ToastUtils.showToast(getActivity(), "请先到档案信息中选择体检人！");
					return;
				}

				ExaminationInfo examinationInfo = MyApplication.getInstance().getExaminationInfo();
				if (examinationInfo == null) {
					ToastUtils.showToast(getActivity(), "请先点击体检登记！");
					return;
				}
				printTextView.setEnabled(false);
				BaseFragment baseFragment = contentFragmentList.get(contentViewPager.getCurrentItem());
				baseFragment.onPrint();
				printTextView.setEnabled(true);
			}
		});
	}

	private void examinationRegister() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先到档案信息中选择体检人！");
			return;
		}

		ExaminationInfo examinationInfo = new ExaminationInfo();
		// 身份证号，标示用户的唯一ID
		examinationInfo.setIdcard(archiveInfo.getIdcard());
		// TODO 体检编号,暂时使用System.currentTimeMillis(),后面最好是服务器生成
		examinationInfo.setExaminationNo(System.currentTimeMillis() + "");
		long date = System.currentTimeMillis();
		examinationInfo.setCreateTime(date + "");
		examinationInfo.setUpdateTime(date + "");
		// 保存数据
		ExaminationManager.getInstance(getActivity()).saveOrUpdateExaminationInfo(examinationInfo);
		ToastUtils.showToast(getActivity(), "体检登记成功！");
		// 添加到Application中
		MyApplication.getInstance().setExaminationInfo(examinationInfo);
	}

}
