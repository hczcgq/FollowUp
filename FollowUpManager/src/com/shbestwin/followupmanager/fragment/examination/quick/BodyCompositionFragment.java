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
import com.shbestwin.followupmanager.manager.device.BodyCompositionManager;
import com.shbestwin.followupmanager.model.device.BodyComposition;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.view.widget.MeasureTipsLayout;

/**
 * 
 * 人体成分
 *
 * @version
 */
public class BodyCompositionFragment extends BaseQuickExaminationFragment {
	private MeasureTipsLayout measureTipsLayuout;
	private Button getDataButton;

	private EditText heightEditText, weightEditText, BMIEditText, bodyImpedanceEditText, KCALEditText, RKCALEditText, fatEditText, visceralFatEditText, muscleEditText, bodyWaterEditText, boneMassEditText, conclusionEditText;

	public static BodyCompositionFragment newInstance() {
		BodyCompositionFragment fragment = new BodyCompositionFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_quick_examination_body_composition, container, false);
		measureTipsLayuout = (MeasureTipsLayout) rootView.findViewById(R.id.measureTipsLayuout);
		getDataButton = (Button) rootView.findViewById(R.id.getDataButton);
		heightEditText = (EditText) rootView.findViewById(R.id.heightEditText);
		weightEditText = (EditText) rootView.findViewById(R.id.weightEditText);
		BMIEditText = (EditText) rootView.findViewById(R.id.BMIEditText);
		bodyImpedanceEditText = (EditText) rootView.findViewById(R.id.bodyImpedanceEditText);
		KCALEditText = (EditText) rootView.findViewById(R.id.KCALEditText);
		RKCALEditText = (EditText) rootView.findViewById(R.id.RKCALEditText);
		fatEditText = (EditText) rootView.findViewById(R.id.fatEditText);
		visceralFatEditText = (EditText) rootView.findViewById(R.id.visceralFatEditText);
		muscleEditText = (EditText) rootView.findViewById(R.id.muscleEditText);
		bodyWaterEditText = (EditText) rootView.findViewById(R.id.bodyWaterEditText);
		boneMassEditText = (EditText) rootView.findViewById(R.id.boneMassEditText);
		conclusionEditText = (EditText) rootView.findViewById(R.id.conclusionEditText);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		measureTipsLayuout.setTips(R.string.jktj_measure_tips_rentichengfen);
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

	private class ReadDataTask extends AsyncTask<Void, Void, BodyComposition> {
		private Activity activity;
		private BodyCompositionManager bodyCompositionManager;
		private ProgressDialog progressDialog;

		public ReadDataTask(Activity activity) {
			this.activity = activity;
			bodyCompositionManager = new BodyCompositionManager(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "获取中。。。", false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if (bodyCompositionManager != null) {
						bodyCompositionManager.closeDevice();
						dataReading=false;
					}
				}
			});
		}

		@Override
		protected BodyComposition doInBackground(Void... params) {
			if (bodyCompositionManager.connectDevice()) {
				BodyComposition bodyComposition = bodyCompositionManager.readData();
				bodyCompositionManager.closeDevice();
				return bodyComposition;
			}
			return null;
		}

		@Override
		protected void onPostExecute(BodyComposition result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (result != null) {
				DecimalFormat df = new DecimalFormat("0.0");
				heightEditText.setText(result.getHeight() + "");
				weightEditText.setText(df.format(result.getWeight()));
				BMIEditText.setText(df.format(result.getBMI()));
				bodyImpedanceEditText.setText(result.getBodyImpedance() + "");
				KCALEditText.setText(result.getKCAL() + "");
				fatEditText.setText(df.format(result.getFat()));
				visceralFatEditText.setText(result.getVisceralFat() + "");
				muscleEditText.setText(df.format(result.getMuscle()));
				bodyWaterEditText.setText(df.format(result.getBodyWater()));
				boneMassEditText.setText(df.format(result.getBoneMass()));
			} else {
				ToastUtils.showToast(activity, bodyCompositionManager.getTipsInfo());
			}
			bodyCompositionManager = null;
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

	}

	@Override
	public String getPrintData(String examinationNo) {
		String printStr = ExaminationManager.getInstance(getActivity()).getPrintTemplate(R.raw.print_body_composition_template, examinationNo);
		// 替换相关数据
		printStr = printStr.replace("{height}", heightEditText.getText().toString())// 身高
						   .replace("{weight}", weightEditText.getText().toString())// 体重
						   .replace("{BMI}", BMIEditText.getText().toString())// BMI
						   .replace("{body_impedance}", bodyImpedanceEditText.getText().toString())// 阻抗
						   .replace("{KCAL}", KCALEditText.getText().toString())// 基础代谢
						   .replace("{RKCAL}", RKCALEditText.getText().toString())// 相对基础代谢
						   .replace("{fat}", fatEditText.getText().toString())// 体脂肪率
						   .replace("{visceral_fat}", visceralFatEditText.getText().toString())// 内脏脂肪
						   .replace("{muscle}", muscleEditText.getText().toString())// 肌肉含量
						   .replace("{body_water}", bodyWaterEditText.getText().toString())// 水分含量
						   .replace("{bone_mass}", boneMassEditText.getText().toString())// 骨含量
						   .replace("{conclusion}", conclusionEditText.getText().toString());// 结论
		return printStr;
	}

	@Override
	public void getSaveData(ExaminationInfo examinationInfo) {
		String bodyCompositionStr = examinationInfo.getBodyComposition();
		JSONObject bodyComposition = null;
		try {
			if (TextUtils.isEmpty(bodyCompositionStr)) {
				bodyComposition = new JSONObject();
				String date = System.currentTimeMillis() + "";
				bodyComposition.put("createTime", date);
				bodyComposition.put("updateTime", date);
			} else {
				bodyComposition = new JSONObject(bodyCompositionStr);
				String date = System.currentTimeMillis() + "";
				bodyComposition.put("updateTime", date);
			}

			bodyComposition.put("height", heightEditText.getText().toString());
			bodyComposition.put("weight", weightEditText.getText().toString());
			bodyComposition.put("BMI", BMIEditText.getText().toString());
			bodyComposition.put("bodyImpedance", bodyImpedanceEditText.getText().toString());
			bodyComposition.put("KCAL", KCALEditText.getText().toString());
			bodyComposition.put("RKCAL", RKCALEditText.getText().toString());
			bodyComposition.put("fat", fatEditText.getText().toString());
			bodyComposition.put("visceralFat", visceralFatEditText.getText().toString());
			bodyComposition.put("muscle", muscleEditText.getText().toString());
			bodyComposition.put("bodyWater", bodyWaterEditText.getText().toString());
			bodyComposition.put("boneMass", boneMassEditText.getText().toString());
			bodyComposition.put("conclusion", conclusionEditText.getText().toString());
			examinationInfo.setBodyComposition(bodyComposition.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void setSaveData(ExaminationInfo examinationInfo) {
        // TODO Auto-generated method stub
        
    }
}
