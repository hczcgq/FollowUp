package com.shbestwin.followupmanager.fragment.examination.quick;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.manager.DeviceManager;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.manager.device.BloodFatManager;
import com.shbestwin.followupmanager.manager.device.BloodGlucoseManager1;
import com.shbestwin.followupmanager.model.device.BloodFat;
import com.shbestwin.followupmanager.model.device.BloodGlucose;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.model.setting.Device;
import com.shbestwin.followupmanager.view.widget.MeasureTipsLayout;

/**
 * 
 * 血糖
 * 
 * @version
 */
public class BloodGlucoseFragment extends BaseQuickExaminationFragment {
	private MeasureTipsLayout measureTipsLayuout;
	private Button getDataButton;
	private EditText bloodGlucoseEditText;
	private Spinner bloodGlucoseTypeSpinner;
	private EditText conclusionEditText;

	public static BloodGlucoseFragment newInstance() {
		BloodGlucoseFragment fragment = new BloodGlucoseFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_quick_examination_blood_glucose, container,
				false);
		measureTipsLayuout = (MeasureTipsLayout) rootView
				.findViewById(R.id.measureTipsLayuout);
		getDataButton = (Button) rootView.findViewById(R.id.getDataButton);
		bloodGlucoseEditText = (EditText) rootView
				.findViewById(R.id.bloodGlucoseEditText);
		bloodGlucoseTypeSpinner = (Spinner) rootView
				.findViewById(R.id.bloodGlucoseTypeSpinner);
		conclusionEditText = (EditText) rootView
				.findViewById(R.id.conclusionEditText);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		measureTipsLayuout.setTips(R.string.jktj_measure_tips_xuetang);
		getDataButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				readData();
			}
		});
	}

	private boolean dataReading = false;
	private ReadDataTask readDataTask;
	private ReadDataTask1 readDataTask1;

	private void readData() {
		if (!dataReading) {
			Device device = DeviceManager.getInstance(getActivity())
					.getDeviceByTypeId(Device.TYPE_ID_BLOOD_GLUCOSE);
			System.out.println(device.getCode());
			if (device.getCode().equals("0501")) {
				dataReading = true;
				// new ReadDataTask(getActivity()).execute();

				if (readDataTask != null
						&& readDataTask.getStatus() == AsyncTask.Status.RUNNING) {
					readDataTask.cancel(true); // 如果Task还在运行，则先取消它
				}
				// 启动新的任务
				readDataTask = new ReadDataTask(getActivity());
				readDataTask.execute();
			} else if (device.getCode().equals("0502")) {
				dataReading = true;
				// new ReadDataTask1(getActivity()).execute();

				if (readDataTask1 != null
						&& readDataTask1.getStatus() == AsyncTask.Status.RUNNING) {
					readDataTask1.cancel(true); // 如果Task还在运行，则先取消它
				}
				// 启动新的任务
				readDataTask1 = new ReadDataTask1(getActivity());
				readDataTask1.execute();
			}
		}
	}

	private class ReadDataTask extends AsyncTask<Void, Void, BloodGlucose> {
		private Activity activity;
		private BloodGlucoseManager1 bloodGlucoseManager;
		private ProgressDialog progressDialog;

		public ReadDataTask(Activity activity) {
			this.activity = activity;
			bloodGlucoseManager = new BloodGlucoseManager1(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "获取中。。。",
					false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if (bloodGlucoseManager != null) {
						bloodGlucoseManager.closeDevice();
						dataReading = false;
					}
				}
			});
		}

		@Override
		protected BloodGlucose doInBackground(Void... params) {
			if (bloodGlucoseManager.connectDevice()) {
				BloodGlucose bloodGlucose = bloodGlucoseManager.readData();
				bloodGlucoseManager.closeDevice();
				return bloodGlucose;
			}
			return null;

			// BloodGlucose bloodPressure = new BloodGlucose();
			// bloodPressure.setType(bloodGlucoseTypeSpinner
			// .getSelectedItemPosition());// 收缩压
			// bloodPressure.setBloodGlucose((float) 7.0);// 舒张压
			// return bloodPressure;
		}

		@Override
		protected void onPostExecute(BloodGlucose result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (result != null) {
				bloodGlucoseEditText.setText(result.getBloodGlucose() + "");
				onConclusion();
			} else {
				ToastUtils.showToast(activity,
						bloodGlucoseManager.getTipsInfo());
			}
			bloodGlucoseManager = null;
			dataReading = false;
		}
	}

	private class ReadDataTask1 extends AsyncTask<Void, Void, BloodFat> {
		private Activity activity;
		private BloodFatManager bloodFatManager;
		private ProgressDialog progressDialog;

		public ReadDataTask1(Activity activity) {
			this.activity = activity;
			bloodFatManager = new BloodFatManager(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "获取中。。。",
					false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if (bloodFatManager != null) {
						bloodFatManager.closeDevice();
						dataReading = false;
					}
				}
			});
		}

		@Override
		protected BloodFat doInBackground(Void... params) {
			if (bloodFatManager.connectDevice()) {
				BloodFat bloodFat = bloodFatManager.readData();
				bloodFatManager.closeDevice();
				return bloodFat;
			}
			return null;
		}

		@Override
		protected void onPostExecute(BloodFat result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (result != null) {
				switch (result.getType()) {
				case BloodFat.TYPE_BLOOD_GLUCOSE:
					bloodGlucoseEditText.setText(result.getBloodGlucose() + "");
					onConclusion();
					break;
				default:
					ToastUtils.showToast(activity, "获取数据失败！");
					break;
				}

			} else {
				ToastUtils.showToast(activity, bloodFatManager.getTipsInfo());
			}
			bloodFatManager = null;
			dataReading = false;
		}
	}

	@Override
	public void onPrint() {
	}

	@Override
	public void onSave() {
	}

	@Override
	public void onUpload() {
	}

	@Override
	public void onConclusion() {
		int bloodGlucoseType = bloodGlucoseTypeSpinner
				.getSelectedItemPosition();
		String bloodGlucoseStr = bloodGlucoseEditText.getText().toString();
		if (!TextUtils.isEmpty(bloodGlucoseStr)) {
			conclusionEditText.setText(BloodGlucose.getConclusion(
					bloodGlucoseType, Float.parseFloat(bloodGlucoseStr)));
		} else {
			conclusionEditText.setText("");
		}

		// if (!TextUtils.isEmpty(bloodGlucoseStr)) {
		// if ((bloodGlucoseType == 0 && (Float.parseFloat(bloodGlucoseStr) >=
		// 7.0))
		// || (bloodGlucoseType == 1 && (Float
		// .parseFloat(bloodGlucoseStr) >= 11.1))) {
		//
		// final ReportConfirmDialog medicationDialog = new ReportConfirmDialog(
		// "血糖");
		// medicationDialog.show(((FragmentActivity) getActivity())
		// .getSupportFragmentManager(), "medicationDialog");
		// medicationDialog
		// .setOnConfirmClickListener(new OnConfirmClickListener() {
		//
		// @Override
		// public void onConfirmClick() {
		// final FollowupDiabetesMellitusReportDialog reportDialog =
		// FollowupDiabetesMellitusReportDialog
		// .newInstance();
		// reportDialog.show(
		// ((FragmentActivity) getActivity())
		// .getSupportFragmentManager(),
		// "DiabetesMellitusReportDialog");
		// reportDialog
		// .setOnConfirmClickListener(new
		// FollowupDiabetesMellitusReportDialog.OnConfirmClickListener() {
		//
		// @Override
		// public void onConfirmClick() {
		// ReportDiabetesMellitus entity = reportDialog
		// .getReportDiabetesMellitus();
		// FollowUpManager
		// .getInstance(
		// getActivity())
		// .saveOrUpdateReportDiabetesMellitus(
		// entity);
		// reportDialog.hide();
		// }
		// });
		// medicationDialog.hide();
		// }
		// });
		// }
		// }

	}

	@Override
	public String getPrintData(String examinationNo) {
		String printStr = ExaminationManager.getInstance(getActivity())
				.getPrintTemplate(R.raw.print_blood_glucose_template,
						examinationNo);
		// 替换相关数据
		printStr = printStr
				.replace("{test_type}",
						ViewDataUtil.getSpinnerData(bloodGlucoseTypeSpinner))// 检测类型
				.replace("{blood_glucose}",
						bloodGlucoseEditText.getText().toString())// 血糖值
				.replace("{conclusion}",
						conclusionEditText.getText().toString());// 结论
		return printStr;
	}

	@Override
	public void getSaveData(ExaminationInfo examinationInfo) {
		String bloodSugarStr = examinationInfo.getBloodSugar();
		JSONObject bloodSugar = null;
		try {
			if (TextUtils.isEmpty(bloodSugarStr)) {
				bloodSugar = new JSONObject();
				String date = System.currentTimeMillis() + "";
				bloodSugar.put("createTime", date);
				bloodSugar.put("updateTime", date);
			} else {
				bloodSugar = new JSONObject(bloodSugarStr);
				String date = System.currentTimeMillis() + "";
				bloodSugar.put("updateTime", date);
			}

			if (bloodGlucoseTypeSpinner != null) {
				bloodSugar.put("type",
						ViewDataUtil.getSpinnerData(bloodGlucoseTypeSpinner));
			}
			if (bloodGlucoseEditText != null) {
				bloodSugar.put("value", bloodGlucoseEditText.getText()
						.toString());
			}
			if (conclusionEditText != null) {
				bloodSugar.put("conclusion", conclusionEditText.getText()
						.toString() + "");
			}
			examinationInfo.setBloodSugar(bloodSugar.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setSaveData(ExaminationInfo examinationInfo1) {
		ExaminationInfo examinationInfo = MyApplication.getInstance()
				.getExaminationInfo();
		if (examinationInfo != null) {
			String msg = examinationInfo.getBloodSugar();
			if (TextUtils.isEmpty(msg)) {
				return;
			}
			try {
				JSONObject json = new JSONObject(msg);
				if(!TextUtils.isEmpty(json.getString("type"))){
					ViewDataUtil.setSpinnerData(bloodGlucoseTypeSpinner,
							json.getString("type"));
				}
				if(!TextUtils.isEmpty(json.getString("value"))){
					bloodGlucoseEditText.setText(json.getString("value"));
				}
				if(!TextUtils.isEmpty(json.getString("conclusion"))){
					conclusionEditText.setText(json.getString("conclusion"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onReset() {
		super.onReset();
		bloodGlucoseEditText.setText("");
		conclusionEditText.setText("");
	}
}
