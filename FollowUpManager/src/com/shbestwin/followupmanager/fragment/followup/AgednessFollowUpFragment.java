package com.shbestwin.followupmanager.fragment.followup;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Adapter;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.manager.FollowUpManager;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;
import com.shbestwin.followupmanager.view.widget.IBaseAgednessBody;

/**
 * 
 * 老年随访
 *
 * @version
 */
public class AgednessFollowUpFragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseAgednessBody> agednessBodyList = null;

	public static AgednessFollowUpFragment newInstance() {
		AgednessFollowUpFragment fragment = new AgednessFollowUpFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_follow_up_agedness_follow_up, container, false);
		viewStub = (ViewStub) rootView.findViewById(R.id.viewStub);
		return rootView;
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
		agednessBodyList = new ArrayList<IBaseAgednessBody>();
		agednessBodyList.add((IBaseAgednessBody) rootView.findViewById(R.id.agednessBody1));
		agednessBodyList.add((IBaseAgednessBody) rootView.findViewById(R.id.agednessBody2));
		agednessBodyList.add((IBaseAgednessBody) rootView.findViewById(R.id.agednessBody3));
		agednessBodyList.add((IBaseAgednessBody) rootView.findViewById(R.id.agednessBody4));

		initData();
	}

	@Override
	public void onReset() {
		initData();
	}

	private void initData() {
		FollowUpAged followUpAged = MyApplication.getInstance().getFollowUpAged();

		for (IBaseAgednessBody agednessBody : agednessBodyList) {
			agednessBody.setData(followUpAged);
			agednessBody.setFragment(getChildFragmentManager());
		}
	}

	@Override
	public void onSave() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先到档案信息中选择随访人！");
			return;
		}

		FollowUpAged followUpAged = MyApplication.getInstance().getFollowUpAged();
		if (followUpAged == null) {
			followUpAged = new FollowUpAged();

			// 设置体检编号
			followUpAged.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpAged.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpAged.setCreateTime(date);
			followUpAged.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpAged.setUpdateTime(date);
		}

		for (IBaseAgednessBody agednessBody : agednessBodyList) {
			if (!agednessBody.validate()) {
				return;
			}
			agednessBody.getData(followUpAged);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpAged(followUpAged);
		ToastUtils.showToast(getActivity(), "保存老年随访数据成功！");
	}

	@Override
	public void onUpload() {
	}
}
