package com.shbestwin.followupmanager.fragment.followup.maternal;

import java.util.ArrayList;

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
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;
import com.shbestwin.followupmanager.view.widget.IBaseAntenatal1Body;

/**
 * 
 * @ClassName: Antenatal1Fragment
 * @Description: 第1次产前
 * 
 */
public class Antenatal1Fragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseAntenatal1Body> antenatal1BodyList;

	public static Antenatal1Fragment newInstance() {
		Antenatal1Fragment fragment = new Antenatal1Fragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_maternal_antenatal_1, container, false);
		viewStub = (ViewStub) rootView.findViewById(R.id.viewStub);
		onRenderPage();
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
		antenatal1BodyList = new ArrayList<IBaseAntenatal1Body>();
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body1));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body2));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body3));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body4));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body5));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body6));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body7));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body8));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body9));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body10));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body11));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body12));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body13));
		antenatal1BodyList.add((IBaseAntenatal1Body) rootView.findViewById(R.id.antenatal1Body14));
		initData();
	}

	private void initData() {
		FollowUpFirstPregnancy followUpFirstPregnancy = MyApplication.getInstance().getFollowUpFirstPregnancy();
		for (IBaseAntenatal1Body antenatal1Body : antenatal1BodyList) {
			antenatal1Body.setData(followUpFirstPregnancy);
			antenatal1Body.setFragment(getChildFragmentManager());
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

		FollowUpFirstPregnancy followUpFirstPregnancy = MyApplication.getInstance().getFollowUpFirstPregnancy();
		if (followUpFirstPregnancy == null) {
			followUpFirstPregnancy = new FollowUpFirstPregnancy();

			// 设置体检编号
			followUpFirstPregnancy.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpFirstPregnancy.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpFirstPregnancy.setCreateTime(date);
			followUpFirstPregnancy.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpFirstPregnancy.setUpdateTime(date);
		}

		for (IBaseAntenatal1Body antenatal1Body : antenatal1BodyList) {
			if (!antenatal1Body.validate()) {
				return;
			}
			antenatal1Body.getData(followUpFirstPregnancy);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpFirstPregnancy(followUpFirstPregnancy);
		ToastUtils.showToast(getActivity(), "保存孕产访视第1次产前数据成功！");
	}

	@Override
	public void onUpload() {
	}

}
