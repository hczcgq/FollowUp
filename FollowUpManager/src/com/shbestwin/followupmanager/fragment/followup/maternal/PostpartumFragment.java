package com.shbestwin.followupmanager.fragment.followup.maternal;

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
import com.shbestwin.followupmanager.model.followup.FollowUpPostpartum;
import com.shbestwin.followupmanager.view.widget.IBasePostpartumBody;

/**
 * 
 * @ClassName: PostpartumFragment
 * @Description: 产后访视
 *
 */
public class PostpartumFragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBasePostpartumBody> postpartumBodyList;

	public static PostpartumFragment newInstance() {
		PostpartumFragment fragment = new PostpartumFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_maternal_postpartum, container, false);
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
		postpartumBodyList = new ArrayList<IBasePostpartumBody>();
		postpartumBodyList.add((IBasePostpartumBody) rootView.findViewById(R.id.postpartumBody1));
		postpartumBodyList.add((IBasePostpartumBody) rootView.findViewById(R.id.postpartumBody2));
		postpartumBodyList.add((IBasePostpartumBody) rootView.findViewById(R.id.postpartumBody3));
		postpartumBodyList.add((IBasePostpartumBody) rootView.findViewById(R.id.postpartumBody4));
		postpartumBodyList.add((IBasePostpartumBody) rootView.findViewById(R.id.postpartumBody5));
		postpartumBodyList.add((IBasePostpartumBody) rootView.findViewById(R.id.postpartumBody6));
		postpartumBodyList.add((IBasePostpartumBody) rootView.findViewById(R.id.postpartumBody7));
		initData();
	}

	private void initData() {
		FollowUpPostpartum followUpPostpartum = MyApplication.getInstance().getFollowUpPostpartum();
		if(followUpPostpartum!=null){
			String numberNo=new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
			if(numberNo.equals(followUpPostpartum.getFollowUpNo())){
				 MyApplication.getInstance().setFollowUpNo(numberNo);
			}
		}
		for (IBasePostpartumBody postpartumBody : postpartumBodyList) {
			postpartumBody.setData(followUpPostpartum);
			postpartumBody.setFragment(getChildFragmentManager());
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

		FollowUpPostpartum followUpPostpartum = MyApplication.getInstance().getFollowUpPostpartum();
		if (followUpPostpartum == null) {
			followUpPostpartum = new FollowUpPostpartum();

			// 设置体检编号
			followUpPostpartum.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpPostpartum.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpPostpartum.setCreateTime(date);
			followUpPostpartum.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpPostpartum.setUpdateTime(date);
		}

		for (IBasePostpartumBody postpartumBody : postpartumBodyList) {
			if (!postpartumBody.validate()) {
				return;
			}
			postpartumBody.getData(followUpPostpartum);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpPostpartum(followUpPostpartum);
		ToastUtils.showToast(getActivity(), "保存孕产访视产后访视前数据成功！");
	}

	@Override
	public void onUpload() {
	}
}
