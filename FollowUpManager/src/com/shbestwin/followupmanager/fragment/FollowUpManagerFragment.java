package com.shbestwin.followupmanager.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.followup.AgednessFollowUpFragment;
import com.shbestwin.followupmanager.fragment.followup.CerebralApoplexyFragment;
import com.shbestwin.followupmanager.fragment.followup.ChildrenInterviewFragment;
import com.shbestwin.followupmanager.fragment.followup.DiabetesMellitusFragment;
import com.shbestwin.followupmanager.fragment.followup.DisabilityFollowUpFragment;
import com.shbestwin.followupmanager.fragment.followup.HypertensionFragment;
import com.shbestwin.followupmanager.fragment.followup.MaternalInterviewFragment;
import com.shbestwin.followupmanager.fragment.followup.MentalDiseaseFragment;
import com.shbestwin.followupmanager.model.MenuItem;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout.OnMenuItemClickListener;

/**
 * 
 * @ClassName: FollowUpManagerFragment
 * @Description: 随访管理
 *
 */
public class FollowUpManagerFragment extends BaseIDCardInfoFragment {
	private LeftMenuLayout leftMenuLayout;
	private View marginView;

	private ViewPager contentViewPager;
	private List<BaseFragment> contentFragmentList;

	private TextView addTextView, uploadTextView, saveTextView, reportCardTextView;

	public static FollowUpManagerFragment newInstance() {
		FollowUpManagerFragment fragment = new FollowUpManagerFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_follow_up_manager, container, false);
		leftMenuLayout = (LeftMenuLayout) rootView.findViewById(R.id.leftMenuLayout);
		marginView = rootView.findViewById(R.id.marginView);
		contentViewPager = (ViewPager) rootView.findViewById(R.id.contentViewPager);
		contentViewPager.setOffscreenPageLimit(7);// 这个很重要，不然动态载入的视图会被清空

		addTextView = (TextView) rootView.findViewById(R.id.addTextView);
		uploadTextView = (TextView) rootView.findViewById(R.id.uploadTextView);
		saveTextView = (TextView) rootView.findViewById(R.id.saveTextView);
		reportCardTextView = (TextView) rootView.findViewById(R.id.reportCardTextView);
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

		menuItems.add(new MenuItem(R.drawable.sfgl_menu_gxy_selector, getString(R.string.sfgl_menu_item_gxy)));// 高血压
		menuItems.add(new MenuItem(R.drawable.sfgl_menu_tnb_selector, getString(R.string.sfgl_menu_item_tnb)));// 糖尿病
		menuItems.add(new MenuItem(R.drawable.sfgl_menu_ncz_selector, getString(R.string.sfgl_menu_item_ncz)));// 脑卒中
		menuItems.add(new MenuItem(R.drawable.sfgl_menu_jsb_selector, getString(R.string.sfgl_menu_item_jsb)));// 精神病
		menuItems.add(new MenuItem(R.drawable.sfgl_menu_ycfs_selector, getString(R.string.sfgl_menu_item_ycfs)));// 孕产访视
		menuItems.add(new MenuItem(R.drawable.sfgl_menu_etfs_selector, getString(R.string.sfgl_menu_item_etfs)));// 儿童访视
		menuItems.add(new MenuItem(R.drawable.sfgl_menu_lnsf_selector, getString(R.string.sfgl_menu_item_lnsf)));// 老年随访
		menuItems.add(new MenuItem(R.drawable.sfgl_menu_cjsf_selector, getString(R.string.sfgl_menu_item_cjsf)));// 残疾随访
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
		reportCardTextView.setEnabled(position < 4 ? true : false);
		contentFragmentList.get(position).onRenderPage();
	}

	/**
	 * 初始化内容视图
	 *
	 */
	private void initContentView() {
		contentFragmentList = new ArrayList<BaseFragment>();
		contentFragmentList.add(HypertensionFragment.newInstance());// 高血压
		contentFragmentList.add(DiabetesMellitusFragment.newInstance());// 糖尿病
		contentFragmentList.add(CerebralApoplexyFragment.newInstance());// 脑卒中
		contentFragmentList.add(MentalDiseaseFragment.newInstance());// 精神病
		contentFragmentList.add(MaternalInterviewFragment.newInstance());// 孕产访视
		contentFragmentList.add(ChildrenInterviewFragment.newInstance());// 儿童访视
		contentFragmentList.add(AgednessFollowUpFragment.newInstance());// 老年随访
		contentFragmentList.add(DisabilityFollowUpFragment.newInstance());// 残疾随访

		// contentViewPager.setPageTransformer(true, new
		// RotateDownTransformer());
		// 给ViewPager设置适配器
		contentViewPager.setAdapter(new ContentFragmentPagerAdapter(getChildFragmentManager()));
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
		contentViewPager.setCurrentItem(0);// 设置当前显示标签页为第一页
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
		addTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (MyApplication.getInstance().getArchiveInfo() == null) {
					ToastUtils.showToast(getActivity(), "请先到档案信息中选择随访人！");
					return;
				}
				// TODO
				// 重新生成随访编号，暂时使用System.currentTimeMillis()作为编号，以后最好使用服务器获取唯一ID
				String followUpNo = new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
				MyApplication.getInstance().setFollowUpNo(followUpNo);
				BaseFragment baseFragment = contentFragmentList.get(contentViewPager.getCurrentItem());
				baseFragment.onReset();
				
				ToastUtils.showToast(getActivity(), "新增随访成功！");
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
				// 如果没有随访编号，需要先生成随访编号
				if (TextUtils.isEmpty(MyApplication.getInstance().getFollowUpNo())) {
					ToastUtils.showToast(getActivity(), "请先点击新增生成随访编号！");
					return;
				}
				BaseFragment baseFragment = contentFragmentList.get(contentViewPager.getCurrentItem());
				baseFragment.onSave();
			}
		});

		// 报卡
		reportCardTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 如果没有随访编号，需要先生成随访编号
				BaseFragment baseFragment = contentFragmentList.get(contentViewPager.getCurrentItem());
				baseFragment.onReport();
			}
		});
	}

}
