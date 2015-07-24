package com.shbestwin.followupmanager.fragment.followup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.manager.ArchiveInfoManager;
import com.shbestwin.followupmanager.manager.FollowUpManager;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;
import com.shbestwin.followupmanager.view.widget.IBaseHypertensionBody;

/**
 * 
 * 高血压
 * 
 * @version
 */
public class HypertensionFragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseHypertensionBody> hypertensionBodyList;

	public static HypertensionFragment newInstance() {
		HypertensionFragment fragment = new HypertensionFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_follow_up_hypertension,
				container, false);
		viewStub = (ViewStub) rootView.findViewById(R.id.viewStub);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		onRenderPage();
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
		hypertensionBodyList = new ArrayList<IBaseHypertensionBody>();
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody1));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody2));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody3));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody4));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody5));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody6));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody7));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody8));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody9));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody10));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody11));
		hypertensionBodyList.add((IBaseHypertensionBody) rootView
				.findViewById(R.id.hypertensionBody12));

		initData();
	}

	@Override
	public void onReset() {
		initData();
	}

	private void initData() {
		FollowUpHypertension followUpHypertension = MyApplication.getInstance()
				.getFollowUpHypertension();
		if(followUpHypertension!=null){
			String numberNo=new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
			if(numberNo.equals(followUpHypertension.getFollowUpNo())){
				 MyApplication.getInstance().setFollowUpNo(numberNo);
			}
		}
		for (IBaseHypertensionBody hypertensionBody : hypertensionBodyList) {
			hypertensionBody.setData(followUpHypertension);
			hypertensionBody.setFragment(getChildFragmentManager());
		}
		
		
		List<ArchiveInfo> archiveInfoList=ArchiveInfoManager.getInstance(getActivity()).getArchiveInfoList();
		List<FollowUpHypertension> followUpHypertensionList=FollowUpManager.getInstance(getActivity()).getFollowUpHypertensionList();
		
		if(archiveInfoList!=null) {
		    System.out.println("本月应随访："+archiveInfoList.size());
		}
		if(followUpHypertensionList!=null) {
		    System.out.println("已随访："+followUpHypertensionList.size());
		}
		
	}

	@Override
	public void onSave() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先到档案信息中选择随访人！");
			return;
		}

		FollowUpHypertension followUpHypertension = MyApplication.getInstance()
				.getFollowUpHypertension();
		if (followUpHypertension == null) {
			followUpHypertension = new FollowUpHypertension();
			// 设置体检编号
			followUpHypertension.setFollowUpNo(MyApplication.getInstance()
					.getFollowUpNo());
			// 设置身份证号
			followUpHypertension.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpHypertension.setCreateTime(date);
			followUpHypertension.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpHypertension.setUpdateTime(date);
		}

		for (IBaseHypertensionBody hypertensionBody : hypertensionBodyList) {
			if (!hypertensionBody.validate()) {
				return;
			}
			hypertensionBody.getData(followUpHypertension);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity())
				.saveOrUpdateFollowUpHypertension(followUpHypertension);
		ToastUtils.showToast(getActivity(), "保存高血压数据成功！");
	}

	@Override
	public void onUpload() {
	}

	@Override
	public void onReport() {
		super.onReport();
	}
}
