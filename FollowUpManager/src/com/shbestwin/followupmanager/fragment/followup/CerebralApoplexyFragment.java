package com.shbestwin.followupmanager.fragment.followup;

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
import com.shbestwin.followupmanager.model.followup.FollowUpStroke;
import com.shbestwin.followupmanager.view.widget.IBaseCerebralApoplexyBody;

/**
 * 
 * 脑卒中
 *
 * @version
 */
public class CerebralApoplexyFragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseCerebralApoplexyBody> cerebralApoplexyBodyList;

	public static CerebralApoplexyFragment newInstance() {
		CerebralApoplexyFragment fragment = new CerebralApoplexyFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_follow_up_cerebral_apoplexy, container, false);
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
		cerebralApoplexyBodyList = new ArrayList<IBaseCerebralApoplexyBody>();
		cerebralApoplexyBodyList.add((IBaseCerebralApoplexyBody) rootView.findViewById(R.id.cerebralApoplexyBody1));
		cerebralApoplexyBodyList.add((IBaseCerebralApoplexyBody) rootView.findViewById(R.id.cerebralApoplexyBody2));
		cerebralApoplexyBodyList.add((IBaseCerebralApoplexyBody) rootView.findViewById(R.id.cerebralApoplexyBody3));
		cerebralApoplexyBodyList.add((IBaseCerebralApoplexyBody) rootView.findViewById(R.id.cerebralApoplexyBody4));
		cerebralApoplexyBodyList.add((IBaseCerebralApoplexyBody) rootView.findViewById(R.id.cerebralApoplexyBody5));
		cerebralApoplexyBodyList.add((IBaseCerebralApoplexyBody) rootView.findViewById(R.id.cerebralApoplexyBody6));

		initData();
	}

	private void initData() {

		FollowUpStroke followUpStroke = MyApplication.getInstance().getFollowUpStroke();

		for (IBaseCerebralApoplexyBody cerebralApoplexyBody : cerebralApoplexyBodyList) {
			cerebralApoplexyBody.setData(followUpStroke);
			cerebralApoplexyBody.setFragment(getChildFragmentManager());
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

		FollowUpStroke followUpStroke = MyApplication.getInstance().getFollowUpStroke();
		if (followUpStroke == null) {
			followUpStroke = new FollowUpStroke();

			// 设置体检编号
			followUpStroke.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpStroke.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpStroke.setCreateTime(date);
			followUpStroke.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpStroke.setUpdateTime(date);
		}

		for (IBaseCerebralApoplexyBody cerebralApoplexyBody : cerebralApoplexyBodyList) {
			if (!cerebralApoplexyBody.validate()) {
				return;
			}
			cerebralApoplexyBody.getData(followUpStroke);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpStroke(followUpStroke);
		ToastUtils.showToast(getActivity(), "保存脑卒中数据成功！");
	}

	@Override
	public void onUpload() {
	}
}
