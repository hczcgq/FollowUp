package com.shbestwin.followupmanager.fragment.examination.quick;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.manager.DeviceManager;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.manager.device.BloodPressureManager;
import com.shbestwin.followupmanager.manager.device.BloodPressureManager1;
import com.shbestwin.followupmanager.manager.device.BloodPressureManager1.BloodPressurePairManager;
import com.shbestwin.followupmanager.model.device.BloodPressure;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.model.setting.Device;
import com.shbestwin.followupmanager.view.widget.MeasureTipsLayout;

/**
 * 
 * 血压
 *
 * @version
 */
public class BloodPressureFragment extends BaseQuickExaminationFragment {
	private MeasureTipsLayout measureTipsLayuout;
	private Button getBloodPressureButton;
	private EditText systolicPressureEditText, diastolicPressureEditText, pulseRateEditText;
	private EditText conclusionEditText;

	public static BloodPressureFragment newInstance() {
		BloodPressureFragment fragment = new BloodPressureFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_quick_examination_blood_pressure, container, false);
		measureTipsLayuout = (MeasureTipsLayout) rootView.findViewById(R.id.measureTipsLayuout);
		getBloodPressureButton = (Button) rootView.findViewById(R.id.getBloodPressureButton);
		systolicPressureEditText = (EditText) rootView.findViewById(R.id.systolicPressureEditText);
		diastolicPressureEditText = (EditText) rootView.findViewById(R.id.diastolicPressureEditText);
		pulseRateEditText = (EditText) rootView.findViewById(R.id.pulseRateEditText);
		conclusionEditText = (EditText) rootView.findViewById(R.id.conclusionEditText);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		measureTipsLayuout.setTips(R.string.jktj_measure_tips_xueya);

		getBloodPressureButton.setOnClickListener(new OnClickListener() {
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
			Device device = DeviceManager.getInstance(getActivity()).getDeviceByTypeId(Device.TYPE_ID_BLOOD_PRESSURE);
			if (device.getCode().equals("0401")) {
				dataReading = true;
//				new ReadDataTask(getActivity()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			
				if (readDataTask != null
	                    && readDataTask.getStatus() == AsyncTask.Status.RUNNING) {
	                readDataTask.cancel(true); // 如果Task还在运行，则先取消它
	            }
	            // 启动新的任务
	            readDataTask = new ReadDataTask(getActivity());
	            readDataTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			} else if (device.getCode().equals("0402")) {
				dataReading = true;
//				new ReadDataTask1(getActivity()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				
				if (readDataTask1 != null
                        && readDataTask1.getStatus() == AsyncTask.Status.RUNNING) {
				    readDataTask1.cancel(true); // 如果Task还在运行，则先取消它
                }
                // 启动新的任务
				readDataTask1 = new ReadDataTask1(getActivity());
				readDataTask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}

		}
	}

	private class ReadDataTask extends AsyncTask<Void, Void, BloodPressure> {
		private Activity activity;
		private BloodPressureManager bloodPressureManager;
		private ProgressDialog progressDialog;

		public ReadDataTask(Activity activity) {
			this.activity = activity;
			bloodPressureManager = new BloodPressureManager(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "获取中。。。", false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if (bloodPressureManager != null) {
						bloodPressureManager.closeDevice();
						dataReading=false;
					}
				}
			});
		}

		@Override
		protected BloodPressure doInBackground(Void... params) {
			if (bloodPressureManager.connectDevice()) {
				BloodPressure bloodPressure = bloodPressureManager.readData();
				bloodPressureManager.closeDevice();
				return bloodPressure;
			}
			return null;
		}

		@Override
		protected void onPostExecute(BloodPressure result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (result != null) {
				systolicPressureEditText.setText(result.getSystolicPressure() + "");
				diastolicPressureEditText.setText(result.getDiastolicPressure() + "");
				pulseRateEditText.setText(result.getPulseRate() + "");
				// conclusionEditText.setText(result.getConclusion());
			} else {
				ToastUtils.showToast(activity, bloodPressureManager.getTipsInfo());
			}
			bloodPressureManager = null;
			dataReading = false;
		}
	}

	private class ReadDataTask1 extends AsyncTask<Void, Void, BloodPressure> {
		private Activity activity;
		private BloodPressureManager1 bloodPressureManager;
		private ProgressDialog progressDialog;

		public ReadDataTask1(Activity activity) {
			this.activity = activity;
			bloodPressureManager = new BloodPressureManager1(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "获取中。。。", false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					ReadDataTask1.this.cancel(true);
					dataReading=false;
				}
			});
		}

		@Override
		protected BloodPressure doInBackground(Void... params) {
			if (bloodPressureManager.connectDevice()) {
				BloodPressure bloodPressure = bloodPressureManager.readData();
				bloodPressureManager.closeDevice();
				return bloodPressure;
			}
			return null;
		}

		@Override
		protected void onPostExecute(BloodPressure result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (result != null) {
				systolicPressureEditText.setText(result.getSystolicPressure() + "");
				diastolicPressureEditText.setText(result.getDiastolicPressure() + "");
				pulseRateEditText.setText(result.getPulseRate() + "");
				// conclusionEditText.setText(result.getConclusion());
			} else {
				if (bloodPressureManager.isPaired()) {
					ToastUtils.showToast(activity, bloodPressureManager.getTipsInfo());
				} else {
					showPairDialog();
				}

			}
			bloodPressureManager = null;
			dataReading = false;
		}

		@Override
		protected void onCancelled(BloodPressure result) {
			ToastUtils.showToast(activity, "onCancelled");
			if (bloodPressureManager != null) {
				bloodPressureManager.closeDevice();
				bloodPressureManager = null;
			}
		}
	}

	private void showPairDialog() {
		AlertDialog.Builder builder = new Builder(getActivity());
		builder.setMessage("当前设备没有跟血压计进行授权配对或配对无效，是否现在配对？\n\n操作方式：\n1、长按3秒血压计中的【发送】键，出现可授权配对状态；\n2、点击当前对话框中的【配对】按钮进行配对。\n");

		builder.setTitle("血压计授权配对提示");

		builder.setPositiveButton("配对", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				pair();
			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	private boolean pairing = false;

	private void pair() {
		if (!pairing) {
			pairing = true;
			new PairTask(getActivity()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
	}

	private class PairTask extends AsyncTask<Void, Void, Boolean> {
		private Activity activity;
		private BloodPressurePairManager bloodPressurePairManager;
		private ProgressDialog progressDialog;

		public PairTask(Activity activity) {
			this.activity = activity;
			bloodPressurePairManager = new BloodPressurePairManager(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "配对中。。。", false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					PairTask.this.cancel(true);
				}
			});
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			return bloodPressurePairManager.pairDevice();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			ToastUtils.showToast(activity, bloodPressurePairManager.getTipsInfo());
			bloodPressurePairManager = null;
			pairing = false;
		}

		@Override
		protected void onCancelled(Boolean result) {
			ToastUtils.showToast(activity, "onCancelled");
			if (bloodPressurePairManager != null) {
				bloodPressurePairManager.closeDevice();
				bloodPressurePairManager = null;
			}
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
		String systolicPressureStr = systolicPressureEditText.getText().toString();// 收缩压
		String diastolicPressureStr = diastolicPressureEditText.getText().toString(); // 舒张压
		if (!TextUtils.isEmpty(systolicPressureStr) && !TextUtils.isEmpty(diastolicPressureStr)) {
			double systolicPressure = Double.parseDouble(systolicPressureStr);// 收缩压
			double diastolicPressure = Double.parseDouble(diastolicPressureStr);// 舒张压
			String result = "";
			if ((systolicPressure < 120) && (diastolicPressure < 80)) {
				result = "血压偏低";
			} else if (((120 < systolicPressure) && (systolicPressure < 139)) || ((diastolicPressure > 80) && (diastolicPressure < 89))) {
				result = "血压正常";
			} else if (((140 < systolicPressure) && (systolicPressure < 159)) || ((diastolicPressure > 90) && (diastolicPressure < 99))) {
				result = "1级高血压（轻度）";
			} else if (((160 < systolicPressure) && (systolicPressure < 179)) || ((diastolicPressure > 100) && (diastolicPressure < 109))) {
				result = "2级高血压（中度）";
			} else if ((systolicPressure > 160) || (diastolicPressure > 110)) {
				result = "3级高血压（重度）";
			} else if ((systolicPressure >= 140) && (diastolicPressure < 90)) {
				result = "单纯收缩期高血压";
			}
			conclusionEditText.setText(result);
		} else {
			conclusionEditText.setText("");
		}
	}

	@Override
	public String getPrintData(String examinationNo) {
		String printStr = ExaminationManager.getInstance(getActivity()).getPrintTemplate(R.raw.print_blood_pressure_template, examinationNo);
		// 替换相关数据
		printStr=printStr.replace("{systolic_pressure}", systolicPressureEditText.getText().toString())// 收缩压
						 .replace("{diastolic_pressure}", diastolicPressureEditText.getText().toString())// 舒张压
						 .replace("{pulse}", pulseRateEditText.getText().toString())// 周期脉搏
						 .replace("{conclusion}", conclusionEditText.getText().toString());// 结论
		return printStr;
	}

	@Override
	public void getSaveData(ExaminationInfo examinationInfo) {
		String bloodPressureStr = examinationInfo.getBloodPressure();
		JSONObject bloodPressure = null;
		try {
			if (TextUtils.isEmpty(bloodPressureStr)) {
				bloodPressure = new JSONObject();
				String date = System.currentTimeMillis() + "";
				bloodPressure.put("createTime", date);
				bloodPressure.put("updateTime", date);
			} else {
				bloodPressure = new JSONObject(bloodPressureStr);
				String date = System.currentTimeMillis() + "";
				bloodPressure.put("updateTime", date);
			}

			bloodPressure.put("systolicPressure", systolicPressureEditText.getText().toString());
			bloodPressure.put("diastolicPressure", diastolicPressureEditText.getText().toString());
			bloodPressure.put("pulse", pulseRateEditText.getText().toString());
			bloodPressure.put("conclusion", conclusionEditText.getText().toString());
			examinationInfo.setBloodPressure(bloodPressure.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void setSaveData(ExaminationInfo examinationInfo) {
       if(examinationInfo!=null){
    	   String msg=examinationInfo.getBloodPressure();
    	   if(TextUtils.isEmpty(msg)){
    		   return;
    	   }
    	   try {
			JSONObject json=new JSONObject(msg);
			systolicPressureEditText.setText(json.getString("systolicPressure"));
			diastolicPressureEditText.setText(json.getString("diastolicPressure"));
			pulseRateEditText.setText(json.getString("pulse"));
			conclusionEditText.setText(json.getString("conclusion"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	   
       }
    }
}
