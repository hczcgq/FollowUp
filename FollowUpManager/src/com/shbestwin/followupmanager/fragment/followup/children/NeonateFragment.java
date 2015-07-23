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
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;
import com.shbestwin.followupmanager.view.widget.IBaseNeonateBody;

/**
 * 
 * @ClassName: NeonateFragment
 * @Description: 新生儿
 *
 */
public class NeonateFragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseNeonateBody> neonateBodyList;

	public static NeonateFragment newInstance() {
		NeonateFragment fragment = new NeonateFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_children_neonate, container, false);
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
		neonateBodyList = new ArrayList<IBaseNeonateBody>();
		neonateBodyList.add((IBaseNeonateBody) rootView.findViewById(R.id.neonateBody1));
		neonateBodyList.add((IBaseNeonateBody) rootView.findViewById(R.id.neonateBody2));
		neonateBodyList.add((IBaseNeonateBody) rootView.findViewById(R.id.neonateBody3));
		neonateBodyList.add((IBaseNeonateBody) rootView.findViewById(R.id.neonateBody4));
		neonateBodyList.add((IBaseNeonateBody) rootView.findViewById(R.id.neonateBody5));
		neonateBodyList.add((IBaseNeonateBody) rootView.findViewById(R.id.neonateBody6));
		neonateBodyList.add((IBaseNeonateBody) rootView.findViewById(R.id.neonateBody7));
		initData();
	}

	private void initData() {

		FollowUpNewborn followUpNewborn = MyApplication.getInstance().getFollowUpNewborn();
		if(followUpNewborn!=null){
			String numberNo=new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
			if(numberNo.equals(followUpNewborn.getFollowUpNo())){
				 MyApplication.getInstance().setFollowUpNo(numberNo);
			}
		}
		for (IBaseNeonateBody neonateBody : neonateBodyList) {
			neonateBody.setData(followUpNewborn);
			neonateBody.setFragment(getChildFragmentManager());
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

		FollowUpNewborn followUpNewborn = MyApplication.getInstance().getFollowUpNewborn();
		
		if (followUpNewborn == null) {
			followUpNewborn = new FollowUpNewborn();

			// 设置体检编号
			followUpNewborn.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpNewborn.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpNewborn.setCreateTime(date);
			followUpNewborn.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpNewborn.setUpdateTime(date);
		}

		for (IBaseNeonateBody neonateBody : neonateBodyList) {
			if (!neonateBody.validate()) {
				return;
			}
			neonateBody.getData(followUpNewborn);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpNewborn(followUpNewborn);
		ToastUtils.showToast(getActivity(), "保存儿童访视新生儿数据成功！");
	}

	@Override
	public void onUpload() {
	}
}
