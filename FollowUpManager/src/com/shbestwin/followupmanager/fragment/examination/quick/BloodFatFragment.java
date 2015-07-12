package com.shbestwin.followupmanager.fragment.examination.quick;

import java.text.DecimalFormat;
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
import com.shbestwin.followupmanager.manager.device.BloodFatManager;
import com.shbestwin.followupmanager.model.device.BloodFat;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.view.widget.MeasureTipsLayout;

/**
 * 
 * 血脂
 *
 * @version
 */
public class BloodFatFragment extends BaseQuickExaminationFragment {
	private MeasureTipsLayout measureTipsLayuout;

	private Button getDataButton;
	private EditText bloodCHOLEditText, bloodTGEditText, bloodHDLEditText, bloodLDLEditText;
	private EditText bloodCHOLConclusionEditText, bloodTGConclusionEditText, bloodHDLConclusionEditText, bloodLDLConclusionEditText;

	private BloodFat bloodFat = new BloodFat();

	public static BloodFatFragment newInstance() {
		BloodFatFragment fragment = new BloodFatFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_quick_examination_blood_fat, container, false);
		measureTipsLayuout = (MeasureTipsLayout) rootView.findViewById(R.id.measureTipsLayuout);
		getDataButton = (Button) rootView.findViewById(R.id.getDataButton);
		bloodCHOLEditText = (EditText) rootView.findViewById(R.id.bloodCHOLEditText);
		bloodTGEditText = (EditText) rootView.findViewById(R.id.bloodTGEditText);
		bloodHDLEditText = (EditText) rootView.findViewById(R.id.bloodHDLEditText);
		bloodLDLEditText = (EditText) rootView.findViewById(R.id.bloodLDLEditText);

		bloodCHOLConclusionEditText = (EditText) rootView.findViewById(R.id.bloodCHOLConclusionEditText);
		bloodTGConclusionEditText = (EditText) rootView.findViewById(R.id.bloodTGConclusionEditText);
		bloodHDLConclusionEditText = (EditText) rootView.findViewById(R.id.bloodHDLConclusionEditText);
		bloodLDLConclusionEditText = (EditText) rootView.findViewById(R.id.bloodLDLConclusionEditText);

		bloodFat.setType(BloodFat.TYPE_BLOOD_LDL);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		measureTipsLayuout.setTips(R.string.jktj_measure_tips_xuezhi);

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

	private class ReadDataTask extends AsyncTask<Void, Void, BloodFat> {
		private Activity activity;
		private BloodFatManager bloodFatManager;
		private ProgressDialog progressDialog;

		public ReadDataTask(Activity activity) {
			this.activity = activity;
			bloodFatManager = new BloodFatManager(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "获取中。。。", false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if (bloodFatManager != null) {
						bloodFatManager.closeDevice();
						dataReading=false;
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
				case BloodFat.TYPE_BLOOD_CHOL:
					bloodCHOLEditText.setText(result.getBloodCHOL() + "");
					// bloodCHOLConclusionEditText.setText(result.getConclusion());
					bloodFat.setBloodCHOL(result.getBloodCHOL());
					onConclusion();
					// setLDL();
					break;
				case BloodFat.TYPE_BLOOD_TG:
					bloodTGEditText.setText(result.getBloodTG() + "");
					// bloodTGConclusionEditText.setText(result.getConclusion());
					bloodFat.setBloodTG(result.getBloodTG());
					onConclusion();
					// setLDL();
					break;
				case BloodFat.TYPE_BLOOD_HDL:
					bloodHDLEditText.setText(result.getBloodHDL() + "");
					// bloodHDLConclusionEditText.setText(result.getConclusion());
					bloodFat.setBloodHDL(result.getBloodHDL());
					onConclusion();
					// setLDL();
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
		String bloodCHOLStr = bloodCHOLEditText.getText().toString();// 胆固醇
		String bloodTGStr = bloodTGEditText.getText().toString();// 甘油三脂
		String bloodHDLStr = bloodHDLEditText.getText().toString();// 高密度脂蛋白
		int flag = 0;

		// 胆固醇
		if (!TextUtils.isEmpty(bloodCHOLStr)) {
			flag++;
			bloodCHOLConclusionEditText.setText(BloodFat.getConclusion(BloodFat.TYPE_BLOOD_CHOL, Float.parseFloat(bloodCHOLStr)));
		} else {
			bloodCHOLConclusionEditText.setText("");
		}

		// 甘油三脂
		if (!TextUtils.isEmpty(bloodTGStr)) {
			flag++;
			bloodTGConclusionEditText.setText(BloodFat.getConclusion(BloodFat.TYPE_BLOOD_TG, Float.parseFloat(bloodTGStr)));
		} else {
			bloodTGConclusionEditText.setText("");
		}

		// 高密度脂蛋白
		if (!TextUtils.isEmpty(bloodHDLStr)) {
			flag++;
			bloodHDLConclusionEditText.setText(BloodFat.getConclusion(BloodFat.TYPE_BLOOD_HDL, Float.parseFloat(bloodHDLStr)));
		} else {
			bloodHDLConclusionEditText.setText("");
		}

		// 低密度脂蛋白
		if (flag == 3) {
			// 低密度脂蛋白胆固醇=总胆固醇-高密度脂蛋白胆固醇-（甘油三酯/2.2）
			float bloodCHOL = Float.parseFloat(bloodCHOLStr);// 胆固醇
			float bloodTG = Float.parseFloat(bloodTGStr);// 甘油三脂
			float bloodHDL = Float.parseFloat(bloodHDLStr);// 高密度脂蛋白
			float bloodLDL = bloodCHOL - bloodHDL - bloodTG / 2.2F;
			String format = "0.00";
			DecimalFormat df = new DecimalFormat(format);
			bloodLDLEditText.setText(df.format(bloodLDL));// 低密度脂蛋白
			bloodLDLConclusionEditText.setText(BloodFat.getConclusion(BloodFat.TYPE_BLOOD_LDL, bloodLDL));
		} else {
			bloodLDLConclusionEditText.setText("");
		}

	}

	@Override
	public String getPrintData(String examinationNo) {
		String printStr = ExaminationManager.getInstance(getActivity()).getPrintTemplate(R.raw.print_blood_fat_template, examinationNo);
		// 替换相关数据
		printStr=printStr.replace("{cholesterin}", bloodCHOLEditText.getText().toString())// 胆固醇
						 .replace("{cholesterin_conclusion}", bloodCHOLConclusionEditText.getText().toString())// 胆固醇结论
						 .replace("{triglyceride}", bloodTGEditText.getText().toString())// 甘油三酯
						 .replace("{triglyceride_conclusion}", bloodTGConclusionEditText.getText().toString())// 甘油三酯结论
						 .replace("{HDL}", bloodHDLEditText.getText().toString())// 高密度脂蛋白
						 .replace("{HDL_conclusion}", bloodHDLConclusionEditText.getText().toString())// 高密度脂蛋白结论
						 .replace("{LDL}", bloodLDLEditText.getText().toString())// 低密度脂蛋白
						 .replace("{LDL_conclusion}", bloodLDLConclusionEditText.getText().toString());// 低密度脂蛋白结论
		return printStr;
	}

	@Override
	public void getSaveData(ExaminationInfo examinationInfo) {
		String bloodFatStr = examinationInfo.getBloodFat();
		JSONObject bloodFat = null;
		try {
			if (TextUtils.isEmpty(bloodFatStr)) {
				bloodFat = new JSONObject();
				String date = System.currentTimeMillis() + "";
				bloodFat.put("createTime", date);
				bloodFat.put("updateTime", date);
			} else {
				bloodFat = new JSONObject(bloodFatStr);
				String date = System.currentTimeMillis() + "";
				bloodFat.put("updateTime", date);
			}

			bloodFat.put("CHOL", bloodCHOLEditText.getText().toString());
			bloodFat.put("CHOLConclusion", bloodCHOLConclusionEditText.getText().toString());
			bloodFat.put("TG", bloodTGEditText.getText().toString());
			bloodFat.put("TGConclusion", bloodTGConclusionEditText.getText().toString());
			bloodFat.put("HDL", bloodHDLEditText.getText().toString());
			bloodFat.put("HDLConclusion", bloodHDLConclusionEditText.getText().toString());
			bloodFat.put("LDL", bloodLDLEditText.getText().toString());
			bloodFat.put("LDLConclusion", bloodLDLConclusionEditText.getText().toString());
			
			examinationInfo.setBloodFat(bloodFat.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void setSaveData(ExaminationInfo examinationInfo) {
    	if (examinationInfo != null) {
			String msg=examinationInfo.getBloodFat();
			if(TextUtils.isEmpty(msg)){
				bloodCHOLEditText.setText("");
				bloodCHOLConclusionEditText.setText("");
				bloodTGEditText.setText("");
				bloodTGConclusionEditText.setText("");
				bloodHDLEditText.setText("");
				bloodHDLConclusionEditText.setText("");
				bloodLDLEditText.setText("");
				bloodLDLConclusionEditText.setText("");
				return;
			}
			try {
				JSONObject jsonObject =new JSONObject(msg);
				bloodCHOLEditText.setText(jsonObject.getString("CHOL"));
				bloodCHOLConclusionEditText.setText(jsonObject.getString("CHOLConclusion"));
				bloodTGEditText.setText(jsonObject.getString("TG"));
				bloodTGConclusionEditText.setText(jsonObject.getString("TGConclusion"));
				bloodHDLEditText.setText(jsonObject.getString("HDL"));
				bloodHDLConclusionEditText.setText(jsonObject.getString("HDLConclusion"));
				bloodLDLEditText.setText(jsonObject.getString("LDL"));
				bloodLDLConclusionEditText.setText(jsonObject.getString("LDLConclusion"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
    }
    
    @Override
    public void onReset() {
    	super.onReset();
    	bloodCHOLEditText.setText("");
		bloodCHOLConclusionEditText.setText("");
		bloodTGEditText.setText("");
		bloodTGConclusionEditText.setText("");
		bloodHDLEditText.setText("");
		bloodHDLConclusionEditText.setText("");
		bloodLDLEditText.setText("");
		bloodLDLConclusionEditText.setText("");
    }
}
