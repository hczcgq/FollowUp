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
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;
import com.shbestwin.followupmanager.view.widget.IBaseYearsOld3_6Body;

/**
 * 
 * @ClassName: YearsOld3_6Fragment
 * @Description: 3-6岁
 * 
 */
public class YearsOld3_6Fragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseYearsOld3_6Body> yearsOld3_6BodyList;

	public static YearsOld3_6Fragment newInstance() {
		YearsOld3_6Fragment fragment = new YearsOld3_6Fragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_children_years_old_3_6, container, false);
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
		yearsOld3_6BodyList = new ArrayList<IBaseYearsOld3_6Body>();
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body1));
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body2));
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body3));
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body4));
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body5));
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body6));
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body7));
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body8));
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body9));
		yearsOld3_6BodyList.add((IBaseYearsOld3_6Body) rootView.findViewById(R.id.yearsOld3_6Body10));
		initData();
	}

	private void initData() {
		FollowUpThreeSixNewborn followUpThreeSixNewborn = MyApplication.getInstance().getFollowUpThreeSixNewborn();
		if(followUpThreeSixNewborn!=null){
			String numberNo=new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
			if(numberNo.equals(followUpThreeSixNewborn.getFollowUpNo())){
				 MyApplication.getInstance().setFollowUpNo(numberNo);
			}
		}
		for (IBaseYearsOld3_6Body yearsOld3_6Body : yearsOld3_6BodyList) {
			yearsOld3_6Body.setData(followUpThreeSixNewborn);
			yearsOld3_6Body.setFragment(getChildFragmentManager());
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

		FollowUpThreeSixNewborn followUpThreeSixNewborn = MyApplication.getInstance().getFollowUpThreeSixNewborn();
		if (followUpThreeSixNewborn == null) {
			followUpThreeSixNewborn = new FollowUpThreeSixNewborn();

			// 设置体检编号
			followUpThreeSixNewborn.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpThreeSixNewborn.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpThreeSixNewborn.setCreateTime(date);
			followUpThreeSixNewborn.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpThreeSixNewborn.setUpdateTime(date);
		}

		for (IBaseYearsOld3_6Body yearsOld3_6Body : yearsOld3_6BodyList) {
			if (!yearsOld3_6Body.validate()) {
				return;
			}
			yearsOld3_6Body.getData(followUpThreeSixNewborn);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpThreeSixNewborn(followUpThreeSixNewborn);
		ToastUtils.showToast(getActivity(), "保存儿童访视3-6岁数据成功！");
	}

	@Override
	public void onUpload() {
	}

}
