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
import com.shbestwin.followupmanager.model.followup.FollowUpTwoToFivePregnancy;
import com.shbestwin.followupmanager.view.widget.IBaseAntenatal2_5Body;

/**
 * 
 * @ClassName: Antenatal2_5Fragment
 * @Description: 第2-5次产前
 * 
 */
public class Antenatal2_5Fragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseAntenatal2_5Body> antenatal2_5BodyList;

	public static Antenatal2_5Fragment newInstance() {
		Antenatal2_5Fragment fragment = new Antenatal2_5Fragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_maternal_antenatal_2_5, container, false);
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
		antenatal2_5BodyList = new ArrayList<IBaseAntenatal2_5Body>();
		antenatal2_5BodyList.add((IBaseAntenatal2_5Body) rootView.findViewById(R.id.antenatal2_5Body1));
		antenatal2_5BodyList.add((IBaseAntenatal2_5Body) rootView.findViewById(R.id.antenatal2_5Body2));
		antenatal2_5BodyList.add((IBaseAntenatal2_5Body) rootView.findViewById(R.id.antenatal2_5Body3));
		antenatal2_5BodyList.add((IBaseAntenatal2_5Body) rootView.findViewById(R.id.antenatal2_5Body4));
		antenatal2_5BodyList.add((IBaseAntenatal2_5Body) rootView.findViewById(R.id.antenatal2_5Body5));
		antenatal2_5BodyList.add((IBaseAntenatal2_5Body) rootView.findViewById(R.id.antenatal2_5Body6));
		initData();
	}

	private void initData() {
		FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy = MyApplication.getInstance().getFollowUpTwoToFivePregnancy();
		for (IBaseAntenatal2_5Body antenatal2_5Body : antenatal2_5BodyList) {
			antenatal2_5Body.setData(followUpTwoToFivePregnancy);
			antenatal2_5Body.setFragment(getChildFragmentManager());
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

		FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy = MyApplication.getInstance().getFollowUpTwoToFivePregnancy();
		if (followUpTwoToFivePregnancy == null) {
			followUpTwoToFivePregnancy = new FollowUpTwoToFivePregnancy();

			// 设置体检编号
			followUpTwoToFivePregnancy.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpTwoToFivePregnancy.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpTwoToFivePregnancy.setCreateTime(date);
			followUpTwoToFivePregnancy.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpTwoToFivePregnancy.setUpdateTime(date);
		}

		for (IBaseAntenatal2_5Body antenatal2_5Body : antenatal2_5BodyList) {
			if (!antenatal2_5Body.validate()) {
				return;
			}
			antenatal2_5Body.getData(followUpTwoToFivePregnancy);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpTwoToFivePregnancy(followUpTwoToFivePregnancy);
		ToastUtils.showToast(getActivity(), "保存孕产访视第2-5次产前数据成功！");
	}

	@Override
	public void onUpload() {
	}
}
