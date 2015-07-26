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
import com.shbestwin.followupmanager.manager.AccompanyManager;
import com.shbestwin.followupmanager.manager.FollowUpManager;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;
import com.shbestwin.followupmanager.view.widget.IBaseYearsOld0Body;

/**
 * 
 * @ClassName: YearsOld1Fragment
 * @Description: 1岁内
 *
 */
public class YearsOld0Fragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseYearsOld0Body> yearsOld0BodyList;

	public static YearsOld0Fragment newInstance() {
		YearsOld0Fragment fragment = new YearsOld0Fragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_children_years_old_0, container, false);
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
		yearsOld0BodyList = new ArrayList<IBaseYearsOld0Body>();
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body1));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body2));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body3));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body4));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body5));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body6));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body7));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body8));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body9));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body10));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body11));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body12));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body13));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body14));
		yearsOld0BodyList.add((IBaseYearsOld0Body) rootView.findViewById(R.id.yearsOld0Body15));
		initData();
	}

	private void initData() {
		FollowUpOneNewborn followUpOneNewborn = MyApplication.getInstance().getFollowUpOneNewborn();
		if(followUpOneNewborn!=null){
			String numberNo=new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
			if(numberNo.equals(followUpOneNewborn.getFollowUpNo())){
				 MyApplication.getInstance().setFollowUpNo(numberNo);
			}
		}
		for (IBaseYearsOld0Body yearsOld0Body : yearsOld0BodyList) {
			yearsOld0Body.setData(followUpOneNewborn);
			yearsOld0Body.setFragment(getChildFragmentManager());
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

		FollowUpOneNewborn followUpOneNewborn = MyApplication.getInstance().getFollowUpOneNewborn();
		if (followUpOneNewborn == null) {
			followUpOneNewborn = new FollowUpOneNewborn();

			// 设置体检编号
			followUpOneNewborn.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpOneNewborn.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpOneNewborn.setCreateTime(date);
			followUpOneNewborn.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpOneNewborn.setUpdateTime(date);
		}

		for (IBaseYearsOld0Body yearsOld0Body : yearsOld0BodyList) {
			if (!yearsOld0Body.validate()) {
				return;
			}
			yearsOld0Body.getData(followUpOneNewborn);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpOneNewborn(followUpOneNewborn);
		ToastUtils.showToast(getActivity(), "保存儿童访视1岁内数据成功！");
		
		AccompanyManager.getInstance(getActivity()).addAccompany(followUpOneNewborn.getGrxx_fsrq(),followUpOneNewborn.getZd_xcsfrq(), Accompany.ACNO_YEAESONLD0);
	}

	@Override
	public void onUpload() {
	}
}
