package com.shbestwin.followupmanager.fragment.followup.children;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.manager.FollowUpManager;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;
import com.shbestwin.followupmanager.view.widget.IBaseYearsOld1_2Body;

/**
 * 
 * @ClassName: YearsOld1_2Fragment
 * @Description: 1-2岁
 *
 */
public class YearsOld1_2Fragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseYearsOld1_2Body> yearsOld1_2BodyList;

	public static YearsOld1_2Fragment newInstance() {
		YearsOld1_2Fragment fragment = new YearsOld1_2Fragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_children_years_old_1_2, container, false);
		viewStub = (ViewStub) rootView.findViewById(R.id.viewStub);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onRenderPage() {
		super.onRenderPage();
		if (!isRenderedPage) {
			isRenderedPage = true;
			viewStub.inflate();
			// 初始化视图
			initView();
		}
	}

	private void initView() {
		yearsOld1_2BodyList = new ArrayList<IBaseYearsOld1_2Body>();
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body1));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body2));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body3));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body4));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body5));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body6));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body7));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body8));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body9));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body10));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body11));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body12));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body13));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body14));
		yearsOld1_2BodyList.add((IBaseYearsOld1_2Body) rootView.findViewById(R.id.yearsOld1_2Body15));
		initData();
	}

	private void initData() {
		FollowUpOneTwoNewborn followUpOneTwoNewborn = MyApplication.getInstance().getFollowUpOneTwoNewborn();
		if(followUpOneTwoNewborn!=null){
			String numberNo=new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
			if(numberNo.equals(followUpOneTwoNewborn.getFollowUpNo())){
				 MyApplication.getInstance().setFollowUpNo(numberNo);
			}
		}
		for (IBaseYearsOld1_2Body yearsOld1_2Body : yearsOld1_2BodyList) {
			yearsOld1_2Body.setData(followUpOneTwoNewborn);
			yearsOld1_2Body.setFragment(getChildFragmentManager());
		}
		
		
	}

	@Override
	public void onReset() {
		initData();
	}

	@Override
	public void onSave() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先到档案信息中选择随访人！");
			return;
		}

		FollowUpOneTwoNewborn followUpOneTwoNewborn = MyApplication.getInstance().getFollowUpOneTwoNewborn();
		if (followUpOneTwoNewborn == null) {
			followUpOneTwoNewborn = new FollowUpOneTwoNewborn();

			// 设置体检编号
			followUpOneTwoNewborn.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpOneTwoNewborn.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpOneTwoNewborn.setCreateTime(date);
			followUpOneTwoNewborn.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpOneTwoNewborn.setUpdateTime(date);
		}

		for (IBaseYearsOld1_2Body yearsOld1_2Body : yearsOld1_2BodyList) {
			if (!yearsOld1_2Body.validate()) {
				return;
			}
			yearsOld1_2Body.getData(followUpOneTwoNewborn);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpOneTwoNewborn(followUpOneTwoNewborn);
		ToastUtils.showToast(getActivity(), "保存儿童访视1-2岁数据成功！");
	}

	@Override
	public void onUpload() {
	}
}
