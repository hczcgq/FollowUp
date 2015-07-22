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
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;
import com.shbestwin.followupmanager.model.report.ReportDiabetesMellitus;
import com.shbestwin.followupmanager.view.dialog.followup.FollowupDiabetesMellitusReportDialog;
import com.shbestwin.followupmanager.view.widget.IBaseDiabetesMellitusBody;

/**
 * 
 * 糖尿病
 * 
 * @version
 */
public class DiabetesMellitusFragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseDiabetesMellitusBody> diabetesMellitusBodiesList;

	public static DiabetesMellitusFragment newInstance() {
		DiabetesMellitusFragment fragment = new DiabetesMellitusFragment();

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater
				.inflate(R.layout.fragment_follow_up_diabetes_mellitus,
						container, false);
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
		diabetesMellitusBodiesList = new ArrayList<IBaseDiabetesMellitusBody>();
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody1));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody2));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody3));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody4));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody5));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody6));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody7));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody8));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody9));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody10));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody11));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody12));
		diabetesMellitusBodiesList.add((IBaseDiabetesMellitusBody) rootView
				.findViewById(R.id.diabetesMellitusBody13));

		initData();

	}

	private void initData() {
		FollowUpDiabetesMellitus followUpDiabetesMellitus = MyApplication
				.getInstance().getFollowUpDiabetesMellitus();
		for (IBaseDiabetesMellitusBody diabetesMellitusBody : diabetesMellitusBodiesList) {
			diabetesMellitusBody.setData(followUpDiabetesMellitus);
			diabetesMellitusBody.setFragment(getChildFragmentManager());
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

		FollowUpDiabetesMellitus followUpDiabetesMellitus = MyApplication
				.getInstance().getFollowUpDiabetesMellitus();
		if (followUpDiabetesMellitus == null) {
			followUpDiabetesMellitus = new FollowUpDiabetesMellitus();

			// 设置体检编号
			followUpDiabetesMellitus.setFollowUpNo(MyApplication.getInstance()
					.getFollowUpNo());
			// 设置身份证号
			followUpDiabetesMellitus.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpDiabetesMellitus.setCreateTime(date);
			followUpDiabetesMellitus.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpDiabetesMellitus.setUpdateTime(date);
		}

		for (IBaseDiabetesMellitusBody diabetesMellitusBody : diabetesMellitusBodiesList) {
			if (!diabetesMellitusBody.validate()) {
				return;
			}
			diabetesMellitusBody.getData(followUpDiabetesMellitus);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity())
				.saveOrUpdateFollowUpDiabetesMellitus(followUpDiabetesMellitus);
		ToastUtils.showToast(getActivity(), "保存糖尿病数据成功！");
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
		FollowUpDiabetesMellitus followUpHypertension = MyApplication
				.getInstance().getFollowUpDiabetesMellitus();
		if (followUpHypertension == null) {
			ToastUtils.showToast(getActivity(), "糖尿病信息为空！");
			return;
		}
		final FollowupDiabetesMellitusReportDialog reportDialog = FollowupDiabetesMellitusReportDialog
				.newInstance();
		reportDialog.show(
				((FragmentActivity) getActivity()).getSupportFragmentManager(),
				"DiabetesMellitusReportDialog");
		reportDialog
				.setOnConfirmClickListener(new FollowupDiabetesMellitusReportDialog.OnConfirmClickListener() {

					@Override
					public void onConfirmClick() {
						ReportDiabetesMellitus entity = reportDialog
								.getReportDiabetesMellitus();
						FollowUpManager.getInstance(getActivity()).saveOrUpdateReportDiabetesMellitus(entity);
						reportDialog.hide();
					}
				});
	}
}
