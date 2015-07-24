package com.shbestwin.followupmanager.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.archive.AllergyHistoryFragment;
import com.shbestwin.followupmanager.fragment.archive.BaseArchiveFragment;
import com.shbestwin.followupmanager.fragment.archive.BearHistoryFragment;
import com.shbestwin.followupmanager.fragment.archive.DisabilityFragment;
import com.shbestwin.followupmanager.fragment.archive.EnvironmentFragment;
import com.shbestwin.followupmanager.fragment.archive.ExposureHistoryFragment;
import com.shbestwin.followupmanager.fragment.archive.FamilyHistoryFragment;
import com.shbestwin.followupmanager.fragment.archive.FamilyInfoFragment;
import com.shbestwin.followupmanager.fragment.archive.FamilyMemberFragment;
import com.shbestwin.followupmanager.fragment.archive.FamilyProblemFragment;
import com.shbestwin.followupmanager.fragment.archive.InsuranceCategoryFragment;
import com.shbestwin.followupmanager.fragment.archive.MensesHistoryFragment;
import com.shbestwin.followupmanager.fragment.archive.PastHistoryFragment;
import com.shbestwin.followupmanager.fragment.archive.PersonalInfoFragment;
import com.shbestwin.followupmanager.fragment.archive.SignUpInfoFragment;
import com.shbestwin.followupmanager.fragment.archive.SyntrophusHistoryFragment;
import com.shbestwin.followupmanager.manager.ArchiveInfoManager;
import com.shbestwin.followupmanager.manager.device.ReadIDCardManager;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.MenuItem;
import com.shbestwin.followupmanager.model.device.IDCardInfo;
import com.shbestwin.followupmanager.view.adapter.archive.ArchiveInfoQueryAdapter;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout;
import com.shbestwin.followupmanager.view.widget.LeftMenuLayout.OnMenuItemClickListener;

public class ArchiveInfoFragment extends BaseFragment {
	private LeftMenuLayout leftMenuLayout;
	private View marginView;

	private ViewPager contentViewPager;
	private List<BaseArchiveFragment> contentFragmentList;

	private ImageView avatarImageView;
	private AutoCompleteTextView nameACEditText, idcardACEditText, cardNoACEditText;
	private TextView gendarTextView, ageTextView, archiveNoTextView;
	private TextView readIDCardTextView, saveTextView, uploadTextView, printTextView;

	private ArchiveInfo archiveInfo;

	public static ArchiveInfoFragment newInstance() {
		ArchiveInfoFragment fragment = new ArchiveInfoFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_info, container, false);
		leftMenuLayout = (LeftMenuLayout) rootView.findViewById(R.id.leftMenuLayout);
		marginView = rootView.findViewById(R.id.marginView);
		contentViewPager = (ViewPager) rootView.findViewById(R.id.contentViewPager);
		avatarImageView = (ImageView) rootView.findViewById(R.id.avatarImageView);
		nameACEditText = (AutoCompleteTextView) rootView.findViewById(R.id.nameACEditText);
		idcardACEditText = (AutoCompleteTextView) rootView.findViewById(R.id.idcardACEditText);
		cardNoACEditText = (AutoCompleteTextView) rootView.findViewById(R.id.cardNoACEditText);
		gendarTextView = (TextView) rootView.findViewById(R.id.gendarTextView);
		ageTextView = (TextView) rootView.findViewById(R.id.ageTextView);
		archiveNoTextView = (TextView) rootView.findViewById(R.id.archiveNoTextView);
		readIDCardTextView = (TextView) rootView.findViewById(R.id.readIDCardTextView);
		saveTextView = (TextView) rootView.findViewById(R.id.saveTextView);
		uploadTextView = (TextView) rootView.findViewById(R.id.uploadTextView);
		printTextView = (TextView) rootView.findViewById(R.id.printTextView);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initLeftMenu();
		initContentView();
		initOperateViews();
		initAutoCompleteViews();
		refreshData();
	}
	
	public void refreshData() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			return;
		} else {
			nameACEditText.setText(archiveInfo.getName());
			idcardACEditText.setText(archiveInfo.getIdcard());
			if ("男".equals(archiveInfo.getGender())) {
				gendarTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_male, 0, 0, 0);
			} else if ("女".equals(archiveInfo.getGender())) {
				gendarTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_female, 0, 0, 0);
			}
			gendarTextView.setText(archiveInfo.getGender());
			ageTextView.setText(DateUtils.getAgeByBirthday(archiveInfo.getBirthday()) + "岁");
			avatarImageView.setImageBitmap(archiveInfo.getPicture());

			cardNoACEditText.setText(archiveInfo.getCardNo());
			archiveNoTextView.setText(archiveInfo.getArchiveNo());
		}
	}

	/**
	 * 初始化左菜单
	 */
	private void initLeftMenu() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem(R.drawable.daxx_menu_grxx_selector, getString(R.string.daxx_menu_item_grxx)));// 个人信息
		menuItems.add(new MenuItem(R.drawable.daxx_menu_bxlb_selector, getString(R.string.daxx_menu_item_bxlb)));// 保险类别
		menuItems.add(new MenuItem(R.drawable.daxx_menu_qyqk_selector, getString(R.string.daxx_menu_item_qyqk)));// 签约情况
		menuItems.add(new MenuItem(R.drawable.daxx_menu_bls_selector, getString(R.string.daxx_menu_item_bls)));// 暴露史
		menuItems.add(new MenuItem(R.drawable.daxx_menu_gms_selector, getString(R.string.daxx_menu_item_gms)));// 过敏史
		menuItems.add(new MenuItem(R.drawable.daxx_menu_jzs_selector, getString(R.string.daxx_menu_item_jzs)));// 家族史
		menuItems.add(new MenuItem(R.drawable.daxx_menu_jws_selector, getString(R.string.daxx_menu_item_jws)));// 既往史
		menuItems.add(new MenuItem(R.drawable.daxx_menu_ycbs_selector, getString(R.string.daxx_menu_item_ycbs)));// 遗传病史
		menuItems.add(new MenuItem(R.drawable.daxx_menu_cjqk_selector, getString(R.string.daxx_menu_item_cjqk)));// 残疾情况

		menuItems.add(new MenuItem(R.drawable.daxx_menu_sys_selector, getString(R.string.daxx_menu_item_sys)));// 生育史
		menuItems.add(new MenuItem(R.drawable.daxx_menu_yjs_selector, getString(R.string.daxx_menu_item_yjs)));// 月经史

		menuItems.add(new MenuItem(R.drawable.daxx_menu_jtxx_selector, getString(R.string.daxx_menu_item_jtxx)));// 家庭信息
		menuItems.add(new MenuItem(R.drawable.daxx_menu_jtcy_selector, getString(R.string.daxx_menu_item_jtcy)));// 家庭成员
		menuItems.add(new MenuItem(R.drawable.daxx_menu_jtwt_selector, getString(R.string.daxx_menu_item_jtwt)));// 家庭问题
		menuItems.add(new MenuItem(R.drawable.daxx_menu_shhj_selector, getString(R.string.daxx_menu_item_shhj)));// 生活环境
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
		contentFragmentList = new ArrayList<BaseArchiveFragment>();
		contentFragmentList.add(PersonalInfoFragment.newInstance());// 个人信息
		contentFragmentList.add(InsuranceCategoryFragment.newInstance());// 保险类别
		contentFragmentList.add(SignUpInfoFragment.newInstance());// 签约情况
		contentFragmentList.add(ExposureHistoryFragment.newInstance());// 暴露史
		contentFragmentList.add(AllergyHistoryFragment.newInstance());// 过敏史
		contentFragmentList.add(FamilyHistoryFragment.newInstance());// 家族史
		contentFragmentList.add(PastHistoryFragment.newInstance());// 既往史
		contentFragmentList.add(SyntrophusHistoryFragment.newInstance());// 遗传病史
		contentFragmentList.add(DisabilityFragment.newInstance());// 残疾情况

		contentFragmentList.add(BearHistoryFragment.newInstance());// 生育史
		contentFragmentList.add(MensesHistoryFragment.newInstance());// 月经史

		contentFragmentList.add(FamilyInfoFragment.newInstance());// 家庭信息
		contentFragmentList.add(FamilyMemberFragment.newInstance());// 家庭成员
		contentFragmentList.add(FamilyProblemFragment.newInstance());// 家庭问题
		contentFragmentList.add(EnvironmentFragment.newInstance());// 生活环境

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

	private boolean IDCardInfoReading = false;

	private void initOperateViews() {
		readIDCardTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				readIDCardInfo();
			}
		});

		saveTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveArchiveInfo();
			}

		});
		uploadTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		printTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	/**
	 * 
	 * @Title: saveArchiveInfo
	 * @Description: 保存档案信息
	 * @param
	 * @return void
	 * @throws
	 */
	private void saveArchiveInfo() {
		if (validate()) {
			BaseArchiveFragment archiveFragment = contentFragmentList.get(contentViewPager.getCurrentItem());
			if (archiveFragment.validate()) {
				ArchiveInfo archiveInfo = archiveFragment.getArchiveInfo();
				if (archiveInfo != null) {
					archiveInfo.setName(nameACEditText.getText().toString());
					archiveInfo.setIdcard(idcardACEditText.getText().toString());
					archiveInfo.setCardNo(cardNoACEditText.getText().toString());
					ArchiveInfoManager.getInstance(getActivity()).saveOrUpdateArchiveInfo(archiveInfo);
					ToastUtils.showToast(getActivity(), "保存成功！");
					// 刷新本地缓存的数据
					MyApplication.getInstance().setArchiveInfo(archiveInfo);
				}
			}
		}
	}

	private boolean validate() {
		if (TextUtils.isEmpty(nameACEditText.getText().toString())) {
			ToastUtils.showToast(getActivity(), "请填写姓名！");
			return false;
		}

		if (TextUtils.isEmpty(idcardACEditText.getText().toString())) {
			ToastUtils.showToast(getActivity(), "请填写身份证号！");
			return false;
		}
		
		ArchiveInfo info=MyApplication.getInstance().getArchiveInfo();
		if(info!=null&&!nameACEditText.getText().toString().equals(info.getName())&&!idcardACEditText.getText().toString().equals(info.getCardNo())){
			MyApplication.getInstance().setArchiveInfo(null);
		}
		return true;
	}

	private void readIDCardInfo() {
		if (!IDCardInfoReading) {
			IDCardInfoReading = true;
			new ReadIDCardInfoTask(getActivity()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
	}

	private class ReadIDCardInfoTask extends AsyncTask<Void, Void, IDCardInfo> {
		private Activity activity;
		private ReadIDCardManager readIDCardManager;
		private ProgressDialog progressDialog;

		public ReadIDCardInfoTask(Activity activity) {
			this.activity = activity;
			readIDCardManager = new ReadIDCardManager(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "读取中。。。", false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					ReadIDCardInfoTask.this.cancel(true);
				}
			});
		}

		@Override
		protected IDCardInfo doInBackground(Void... params) {
			if (readIDCardManager.connectDevice()) {
				IDCardInfo cardInfo = readIDCardManager.readCardInfo();
				readIDCardManager.closeDevice();
				return cardInfo;
			}
			return null;
		}

		@Override
		protected void onPostExecute(IDCardInfo result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (result != null) {
				// 查找本地库中是否存在该身份证号的档案信息，如果存在加载档案信息，如果不存在，新建当前值，放到Application中
				archiveInfo = ArchiveInfoManager.getInstance(getActivity()).getArchiveInfoById(result.getIdcard());
				if (archiveInfo == null) {
					archiveInfo = new ArchiveInfo();
					archiveInfo.render(result);
				}
				refushData(archiveInfo);

			} else {
				ToastUtils.showToast(activity, readIDCardManager.getTipsInfo());
			}
			readIDCardManager = null;
			IDCardInfoReading = false;
		}

		@Override
		protected void onCancelled(IDCardInfo result) {
			super.onCancelled(result);
			if (readIDCardManager != null) {
				readIDCardManager.closeDevice();
				readIDCardManager = null;
			}
		}
	}

	private ArchiveInfoQueryAdapter nameQueryAdapter;
	private ArchiveInfoQueryAdapter idcardQueryAdapter;
	private ArchiveInfoQueryAdapter cardNoQueryAdapter;

	private void initAutoCompleteViews() {
		nameQueryAdapter = new ArchiveInfoQueryAdapter(getActivity(), ArchiveInfoQueryAdapter.QUERY_BY_NAME);
		nameACEditText.setAdapter(nameQueryAdapter);

		idcardQueryAdapter = new ArchiveInfoQueryAdapter(getActivity(), ArchiveInfoQueryAdapter.QUERY_BY_IDCARD);
		idcardACEditText.setAdapter(idcardQueryAdapter);

		cardNoQueryAdapter = new ArchiveInfoQueryAdapter(getActivity(), ArchiveInfoQueryAdapter.QUERY_BY_CARDNO);
		cardNoACEditText.setAdapter(cardNoQueryAdapter);

		nameACEditText.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				refushData(nameQueryAdapter.getItem(position));
			}
		});

		idcardACEditText.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				refushData(idcardQueryAdapter.getItem(position));
			}
		});

		cardNoACEditText.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				refushData(cardNoQueryAdapter.getItem(position));
			}
		});
	}

	private void refushData(ArchiveInfo archiveInfo) {
		// 保存档案信息
		MyApplication.getInstance().setArchiveInfo(archiveInfo);

		nameACEditText.setText(archiveInfo.getName());
		idcardACEditText.setText(archiveInfo.getIdcard());
		if ("男".equals(archiveInfo.getGender())) {
			gendarTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_male, 0, 0, 0);
		} else if ("女".equals(archiveInfo.getGender())) {
			gendarTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_female, 0, 0, 0);
		}
		gendarTextView.setText(archiveInfo.getGender());
		ageTextView.setText(DateUtils.getAgeByBirthday(archiveInfo.getBirthday()) + "岁");
		avatarImageView.setImageBitmap(archiveInfo.getPicture());

		cardNoACEditText.setText(archiveInfo.getCardNo());
		archiveNoTextView.setText(archiveInfo.getArchiveNo());

		for (BaseArchiveFragment fragment : contentFragmentList) {
			fragment.refreshData();
		}
		
		
		MyApplication.getInstance().setFollowUpNo(null);
		MyApplication.getInstance().setExaminationInfo(null);
		MyApplication.getInstance().setGeneralExamination(null);
		MyApplication.getInstance().setFollowUpHypertension(null);
		MyApplication.getInstance().setFollowUpDiabetesMellitus(null);
		MyApplication.getInstance().setFollowUpAged(null);
		MyApplication.getInstance().setFollowUpMentalDisease(null);
		MyApplication.getInstance().setFollowUpDisabledPerson(null);
		MyApplication.getInstance().setFollowUpNewborn(null);
		MyApplication.getInstance().setFollowUpOneNewborn(null);
		MyApplication.getInstance().setFollowUpOneTwoNewborn(null);
		MyApplication.getInstance().setFollowUpThreeSixNewborn(null);
		MyApplication.getInstance().setFollowUpFirstPregnancy(null);
		MyApplication.getInstance().setFollowUpTwoToFivePregnancy(null);
		MyApplication.getInstance().setFollowUpFortyTwo(null);
		MyApplication.getInstance().setFollowUpPostpartum(null);
		MyApplication.getInstance().setFollowUpStroke(null);
		
		
		
	}
}
