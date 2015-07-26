package com.shbestwin.followupmanager.fragment.examination;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BaseQuickExaminationFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BloodFatFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BloodGlucoseFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BloodOximeterFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BloodPressureFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BodyCompositionFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.ElectrocardiogramAnalyzerFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.RoutineExaminationFragment;
import com.shbestwin.followupmanager.manager.AccompanyManager;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.manager.FollowUpManager;
import com.shbestwin.followupmanager.manager.device.PrintManager;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.device.BloodGlucose;
import com.shbestwin.followupmanager.model.device.BloodPressure;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.model.report.ReportDiabetesMellitus;
import com.shbestwin.followupmanager.model.report.ReportHyoertension;
import com.shbestwin.followupmanager.view.dialog.ReportConfirmDialog;
import com.shbestwin.followupmanager.view.dialog.ReportConfirmDialog.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.FollowupDiabetesMellitusReportDialog;
import com.shbestwin.followupmanager.view.dialog.followup.FollowupHypertensionReportDialog;
import com.shbestwin.followupmanager.view.widget.TabMenuLayout;

/**
 * 
 * 快速体检
 * 
 * @version
 */
public class QuickExaminationFragment extends BaseFragment {
	private TabMenuLayout tabMenuLayout;

	private ViewPager bodyViewPager;

	private List<BaseQuickExaminationFragment> contentFragmentList;

	private boolean isFirst = true;

	private ExaminationInfo generalExamination = null;

	public static QuickExaminationFragment newInstance() {
		QuickExaminationFragment fragment = new QuickExaminationFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_examination_quick_examination, container,
				false);
		tabMenuLayout = (TabMenuLayout) rootView
				.findViewById(R.id.tabMenuLayout);
		bodyViewPager = (ViewPager) rootView.findViewById(R.id.bodyViewPager);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initTabView();
		initBodyView();
	}

	private void initTabView() {
		String[] tabMenu = getResources().getStringArray(
				R.array.jktjQuickExaminationTabMenu);
		tabMenuLayout.renderMenu(tabMenu);
		tabMenuLayout.checkItem(0);

		tabMenuLayout.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				bodyViewPager.setCurrentItem(checkedId);
			}
		});
	}

	/**
	 * 初始化内容视图
	 * 
	 */
	private void initBodyView() {
		contentFragmentList = new ArrayList<BaseQuickExaminationFragment>();
		contentFragmentList.add(RoutineExaminationFragment.newInstance());// 常规体检
		contentFragmentList.add(BloodPressureFragment.newInstance());// 血压
		contentFragmentList.add(BloodGlucoseFragment.newInstance());// 血糖
		contentFragmentList.add(BloodFatFragment.newInstance());// 血脂
		contentFragmentList.add(BloodOximeterFragment.newInstance());// 血氧
		contentFragmentList
				.add(ElectrocardiogramAnalyzerFragment.newInstance());// 心电分析
		contentFragmentList.add(BodyCompositionFragment.newInstance());// 人体成分
		// contentFragmentList.add(HumorFragment.newInstance());// 体液

		// contentViewPager.setPageTransformer(true, new
		// RotateDownTransformer());
		// 给ViewPager设置适配器
		bodyViewPager.setAdapter(new ContentFragmentPagerAdapter(
				getChildFragmentManager()));
		bodyViewPager.setCurrentItem(0);// 设置当前显示标签页为第一页

		generalExamination = MyApplication.getInstance().getExaminationInfo();
		if (generalExamination != null) {
			MyApplication.getInstance().setExaminationInfo(generalExamination);
		}

		bodyViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				tabMenuLayout.checkItem(position);
				if (generalExamination != null) {
					BaseQuickExaminationFragment baseFragment = contentFragmentList
							.get(position);
					baseFragment.setSaveData(generalExamination);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				if (isFirst) {
					isFirst = false;
					if (generalExamination != null) {
						BaseQuickExaminationFragment baseFragment = contentFragmentList
								.get(bodyViewPager.getCurrentItem());
						baseFragment.setSaveData(generalExamination);
					}
				}
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});// 页面变化时的监听器
	}

	private class ContentFragmentPagerAdapter extends FragmentPagerAdapter {

		public ContentFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return contentFragmentList.get(position);
		}

		@Override
		public int getCount() {
			return contentFragmentList.size();
		}
	}

	@Override
	public void onConclusion() {
		BaseFragment baseFragment = contentFragmentList.get(bodyViewPager
				.getCurrentItem());
		baseFragment.onConclusion();
	}

	@Override
	public void onPrint() {
		ExaminationInfo examinationInfo = MyApplication.getInstance()
				.getExaminationInfo();
		BaseQuickExaminationFragment baseFragment = contentFragmentList
				.get(bodyViewPager.getCurrentItem());
		String printData = baseFragment.getPrintData(examinationInfo
				.getExaminationNo());
		// PrintManager printManager = new PrintManager(getActivity());
		// if (printManager.connectDevice()) {
		// printManager.print(printData);
		// printManager.closeDevice();
		// }
		printDataMethod(printData);
	}

	@Override
	public void onSave() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先到档案信息中选择体检人！");
			return;
		}

		ExaminationInfo examinationInfo = MyApplication.getInstance()
				.getExaminationInfo();
		if (examinationInfo == null) {
			examinationInfo = new ExaminationInfo();
			// 身份证号，标示用户的唯一ID
			examinationInfo.setIdcard(archiveInfo.getIdcard());
			// TODO 体检编号,暂时使用System.currentTimeMillis(),后面最好是服务器生成
			examinationInfo.setExaminationNo(new SimpleDateFormat("yyyyMMdd")
					.format(new Date()) + archiveInfo.getIdcard());
			long date = System.currentTimeMillis();
			examinationInfo.setCreateTime(date + "");
			examinationInfo.setUpdateTime(date + "");
		} else {
			String date = System.currentTimeMillis() + "";
			examinationInfo.setUpdateTime(date);
		}
		for (BaseQuickExaminationFragment baseFragment : contentFragmentList) {
			baseFragment.getSaveData(examinationInfo);
		}

		try {
			ShowMessage(examinationInfo);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		ExaminationManager.getInstance(getActivity())
				.saveOrUpdateExaminationInfo(examinationInfo);
		ToastUtils.showToast(getActivity(), "保存数据成功！");
		MyApplication.getInstance().setExaminationInfo(examinationInfo);

	}

	@Override
	public void onUpload() {
		BaseFragment baseFragment = contentFragmentList.get(bodyViewPager
				.getCurrentItem());
		baseFragment.onUpload();
	}

	@Override
	public void onReset() {
		super.onReset();
		generalExamination = MyApplication.getInstance().getExaminationInfo();
		BaseFragment baseFragment = contentFragmentList.get(bodyViewPager
				.getCurrentItem());
		baseFragment.onReset();
	}

	private boolean IsPrinting = false;

	private void printDataMethod(String printData) {
		if (!IsPrinting) {
			IsPrinting = true;
			new PrintTask(getActivity(), printData)
					.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
	}

	private class PrintTask extends AsyncTask<Void, Void, String> {
		private Activity activity;
		private PrintManager printManager;
		private ProgressDialog progressDialog;
		private String printData;

		public PrintTask(Activity activity, String printData) {
			this.activity = activity;
			printManager = new PrintManager(activity);
			this.printData = printData;
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "正在打印。。。",
					false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					PrintTask.this.cancel(true);
				}
			});
		}

		@Override
		protected String doInBackground(Void... params) {
			if (printManager.connectDevice()) {
				printManager.print(printData);
				printManager.closeDevice();
				return "true";
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (result != null) {

			} else {
				ToastUtils.showToast(activity, printManager.getTipsInfo());
			}
			printManager = null;
			IsPrinting = false;
		}

		@Override
		protected void onCancelled(String result) {
			super.onCancelled(result);
			if (printManager != null) {
				printManager.closeDevice();
				printManager = null;
			}
		}
	}

	private void ShowMessage(ExaminationInfo info) throws JSONException {
		String msgBloodPressure = info.getBloodPressure();
		if (!TextUtils.isEmpty(msgBloodPressure)) {
			BloodPressure bloodPressure = new BloodPressure();
			JSONObject json = new JSONObject(msgBloodPressure);
			if (!TextUtils.isEmpty(json.getString("systolicPressure"))) {
				bloodPressure.setSystolicPressure(Integer.parseInt(json
						.getString("systolicPressure")));
			}
			if (!TextUtils.isEmpty(json.getString("diastolicPressure"))) {
				bloodPressure.setDiastolicPressure(Integer.parseInt(json
						.getString("diastolicPressure")));
			}
			if (!TextUtils.isEmpty(json.getString("pulse"))) {
				bloodPressure.setPulseRate(Integer.parseInt(json
						.getString("pulse")));
			}
			System.out.println(bloodPressure.getSystolicPressure()+"---"+bloodPressure.getSystolicPressure());
			if ((bloodPressure.getSystolicPressure() >= 140)
					|| (bloodPressure.getSystolicPressure() >= 90)) {
				showBloodPress(bloodPressure);
			}
		}

		String msgBloodSugar = info.getBloodSugar();
		if (!TextUtils.isEmpty(msgBloodSugar)) {
			BloodGlucose bloodGlucose = new BloodGlucose();
			JSONObject json = new JSONObject(msgBloodSugar);
			if (!TextUtils.isEmpty(json.getString("type"))) {

				if (json.getString("type").equals("空腹血糖")) {
					bloodGlucose.setType(0);
				} else {
					bloodGlucose.setType(1);
				}
			}
			if (!TextUtils.isEmpty(json.getString("value"))) {
				bloodGlucose.setBloodGlucose(Float.parseFloat(json
						.getString("value")));// 舒张压
			}
			if ((bloodGlucose.getType() == 0 && (bloodGlucose.getBloodGlucose() >= 7.0))
					|| (bloodGlucose.getType() == 1 && (bloodGlucose
							.getBloodGlucose() >= 11.1))) {
				showBloodSugar(bloodGlucose);
			}
		}

	}

	private void showBloodPress(final BloodPressure bloodPressure) {
		final ReportConfirmDialog medicationDialog = new ReportConfirmDialog(
				"血压");
		medicationDialog.show(
				((FragmentActivity) getActivity()).getSupportFragmentManager(),
				"medicationDialog");
		medicationDialog
				.setOnConfirmClickListener(new OnConfirmClickListener() {

					@Override
					public void onConfirmClick() {
						final FollowupHypertensionReportDialog reportDialog = new FollowupHypertensionReportDialog(
								bloodPressure);
						reportDialog.show(((FragmentActivity) getActivity())
								.getSupportFragmentManager(),
								"HypertensionReportDialog");
						reportDialog
								.setOnConfirmClickListener(new FollowupHypertensionReportDialog.OnConfirmClickListener() {

									@Override
									public void onConfirmClick() {
										ReportHyoertension entity = reportDialog
												.getReportHyoertension();
										FollowUpManager
												.getInstance(getActivity())
												.saveOrUpdateReportHyoertension(
														entity);
										AccompanyManager.getInstance(getActivity()).addAccompany(null,entity.getNext_followup_date(), Accompany.ACNO_REPORTHYPERTENSION);
										reportDialog.hide();
									}
								});
						medicationDialog.hide();
					}
				});
	}

	private void showBloodSugar(final BloodGlucose bloodPressure) {
		final ReportConfirmDialog medicationDialog = new ReportConfirmDialog(
				"血糖");
		medicationDialog.show(
				((FragmentActivity) getActivity()).getSupportFragmentManager(),
				"medicationDialog");
		medicationDialog
				.setOnConfirmClickListener(new OnConfirmClickListener() {

					@Override
					public void onConfirmClick() {
						final FollowupDiabetesMellitusReportDialog reportDialog = FollowupDiabetesMellitusReportDialog
								.newInstance();
						reportDialog.show(((FragmentActivity) getActivity())
								.getSupportFragmentManager(),
								"DiabetesMellitusReportDialog");
						reportDialog
								.setOnConfirmClickListener(new FollowupDiabetesMellitusReportDialog.OnConfirmClickListener() {

									@Override
									public void onConfirmClick() {
										ReportDiabetesMellitus entity = reportDialog
												.getReportDiabetesMellitus();
										FollowUpManager
												.getInstance(getActivity())
												.saveOrUpdateReportDiabetesMellitus(
														entity);
										AccompanyManager.getInstance(getActivity()).addAccompany(null,entity.getNext_followup_date(), Accompany.ACNO_REPORTDIABETESMELLITUS);
										reportDialog.hide();
									}
								});
						medicationDialog.hide();
					}
				});

	}
}
