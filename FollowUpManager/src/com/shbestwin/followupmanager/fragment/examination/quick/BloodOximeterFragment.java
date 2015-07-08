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
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.manager.device.FingerOximeterManager;
import com.shbestwin.followupmanager.model.device.BloodOximeter;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.view.widget.MeasureTipsLayout;

/**
 * 
 * 血氧
 *
 * @version
 */
public class BloodOximeterFragment extends BaseQuickExaminationFragment {
	private MeasureTipsLayout measureTipsLayuout;
	private Button getDataButton;
	private EditText bloodOxygenEditText, pulseRateEditText, conclusionEditText;

	public static BloodOximeterFragment newInstance() {
		BloodOximeterFragment fragment = new BloodOximeterFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_quick_examination_blood_oximeter, container, false);
		measureTipsLayuout = (MeasureTipsLayout) rootView.findViewById(R.id.measureTipsLayuout);
		getDataButton = (Button) rootView.findViewById(R.id.getDataButton);
		bloodOxygenEditText = (EditText) rootView.findViewById(R.id.bloodOxygenEditText);
		pulseRateEditText = (EditText) rootView.findViewById(R.id.pulseRateEditText);
		conclusionEditText = (EditText) rootView.findViewById(R.id.conclusionEditText);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		measureTipsLayuout.setTips(R.string.jktj_measure_tips_xueyang);

		getDataButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				readData();
			}
		});
	}

	private boolean dataReading = false;
	private ReadDataTask readDataTask;
	private void readData() {
		if (!dataReading) {
			dataReading = true;
//			new ReadDataTask(getActivity()).execute();
			
			if (readDataTask != null
                    && readDataTask.getStatus() == AsyncTask.Status.RUNNING) {
                readDataTask.cancel(true); // 如果Task还在运行，则先取消它
            }
            // 启动新的任务
            readDataTask = new ReadDataTask(getActivity());
            readDataTask.execute();
		}
	}

	private class ReadDataTask extends AsyncTask<Void, Void, BloodOximeter> {
		private Activity activity;
		private FingerOximeterManager fingerOximeterManager;
		private ProgressDialog progressDialog;

		public ReadDataTask(Activity activity) {
			this.activity = activity;
			fingerOximeterManager = new FingerOximeterManager(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "获取中。。。", false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if (fingerOximeterManager != null) {
						fingerOximeterManager.closeDevice();
						dataReading=false;
					}
				}
			});
		}

		@Override
		protected BloodOximeter doInBackground(Void... params) {
			if (fingerOximeterManager.connectDevice()) {
				BloodOximeter bloodOximeter = fingerOximeterManager.readData();
				fingerOximeterManager.closeDevice();
				return bloodOximeter;
			}
			return null;
		}

		@Override
		protected void onPostExecute(BloodOximeter result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (result != null) {
				bloodOxygenEditText.setText(result.getBloodOxygen() + "");
				pulseRateEditText.setText(result.getPulseRate() + "");
				// conclusionEditText.setText(result.getConclusion());
			} else {
				ToastUtils.showToast(activity, fingerOximeterManager.getTipsInfo());
			}
			fingerOximeterManager = null;
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
		String bloodOxygenStr = bloodOxygenEditText.getText().toString();
		if (!TextUtils.isEmpty(bloodOxygenStr)) {
			conclusionEditText.setText(BloodOximeter.getConclusion(Integer.parseInt(bloodOxygenStr)));
		} else {
			conclusionEditText.setText("");
		}
	}

	@Override
	public String getPrintData(String examinationNo) {
		String printStr = ExaminationManager.getInstance(getActivity()).getPrintTemplate(R.raw.print_finger_oximeter_template, examinationNo);
		// 替换相关数据
		printStr=printStr.replace("{saturation}", bloodOxygenEditText.getText().toString())// 饱和度
						 .replace("{pulse_rate}", pulseRateEditText.getText().toString())// 脉率
						 .replace("{conclusion}", conclusionEditText.getText().toString());// 血氧结论
		return printStr;
	}

	@Override
	public void getSaveData(ExaminationInfo examinationInfo) {
		String bloodOxygenStr = examinationInfo.getBloodOxygen();
		JSONObject bloodOxygen = null;
		try {
			if (TextUtils.isEmpty(bloodOxygenStr)) {
				bloodOxygen = new JSONObject();
				String date = System.currentTimeMillis() + "";
				bloodOxygen.put("createTime", date);
				bloodOxygen.put("updateTime", date);
			} else {
				bloodOxygen = new JSONObject(bloodOxygenStr);
				String date = System.currentTimeMillis() + "";
				bloodOxygen.put("updateTime", date);
			}

			bloodOxygen.put("saturation", bloodOxygenEditText.getText().toString());
			bloodOxygen.put("pulseRate", pulseRateEditText.getText().toString());
			bloodOxygen.put("conclusion", conclusionEditText.getText().toString());
			examinationInfo.setBloodOxygen(bloodOxygen.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void setSaveData(ExaminationInfo examinationInfo) {
        // TODO Auto-generated method stub
        
    }
}
