package com.shbestwin.followupmanager.fragment.followup;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;
import com.shbestwin.followupmanager.model.followup.Inspection;
import com.shbestwin.followupmanager.model.report.ReportDiabetesMellitus;
import com.shbestwin.followupmanager.model.report.ReportHyoertension;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.FollowupHypertensionReportDialog;
import com.shbestwin.followupmanager.view.dialog.followup.InspectionDialog;
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
		for (IBaseHypertensionBody hypertensionBody : hypertensionBodyList) {
			hypertensionBody.setData(followUpHypertension);
			hypertensionBody.setFragment(getChildFragmentManager());
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
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先到档案信息中选择随访人！");
			return;
		}
		FollowUpHypertension followUpHypertension = MyApplication.getInstance()
				.getFollowUpHypertension();
		if (followUpHypertension == null) {
			ToastUtils.showToast(getActivity(), "高血压信息为空！");
			return;
		}
		final FollowupHypertensionReportDialog reportDialog = FollowupHypertensionReportDialog
				.newInstance();
		reportDialog.show(
				((FragmentActivity) getActivity()).getSupportFragmentManager(),
				"HypertensionReportDialog");
		reportDialog
				.setOnConfirmClickListener(new FollowupHypertensionReportDialog.OnConfirmClickListener() {

					@Override
					public void onConfirmClick() {
						ReportHyoertension entity = reportDialog
								.getReportHyoertension();
						FollowUpManager.getInstance(getActivity())
								.saveOrUpdateReportHyoertension(entity);
						reportDialog.hide();
					}
				});
	}
}
