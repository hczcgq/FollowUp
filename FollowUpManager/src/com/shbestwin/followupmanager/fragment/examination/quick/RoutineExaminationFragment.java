package com.shbestwin.followupmanager.fragment.examination.quick;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.manager.device.WaistlineManager;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;

/**
 * 
 * 常规体检
 * 
 * @version
 */
public class RoutineExaminationFragment extends BaseQuickExaminationFragment {
	private Button getWaistlineButton, getHiplineButton, getBustButton;
	private EditText waistlineEditText, hiplineEditText, bustEditText,
			waistToHipratioEditText, BWHConclusionEditText;

	private EditText temperatureEditText, temperatureConclusionEditText;
	private EditText heightEditText, weightEditText, BMIEditText,
			physiqueConclusionEditText;

	public static RoutineExaminationFragment newInstance() {
		RoutineExaminationFragment fragment = new RoutineExaminationFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_quick_examination_routine_examination,
				container, false);
		getWaistlineButton = (Button) rootView
				.findViewById(R.id.getWaistlineButton);
		getHiplineButton = (Button) rootView
				.findViewById(R.id.getHiplineButton);
		getBustButton = (Button) rootView.findViewById(R.id.getBustButton);
		waistlineEditText = (EditText) rootView
				.findViewById(R.id.waistlineEditText);
		hiplineEditText = (EditText) rootView
				.findViewById(R.id.hiplineEditText);
		bustEditText = (EditText) rootView.findViewById(R.id.bustEditText);
		waistToHipratioEditText = (EditText) rootView
				.findViewById(R.id.waistToHipratioEditText);
		BWHConclusionEditText = (EditText) rootView
				.findViewById(R.id.BWHConclusionEditText);
		temperatureEditText = (EditText) rootView
				.findViewById(R.id.temperatureEditText);
		temperatureConclusionEditText = (EditText) rootView
				.findViewById(R.id.temperatureConclusionEditText);
		heightEditText = (EditText) rootView.findViewById(R.id.heightEditText);
		weightEditText = (EditText) rootView.findViewById(R.id.weightEditText);
		BMIEditText = (EditText) rootView.findViewById(R.id.BMIEditText);
		physiqueConclusionEditText = (EditText) rootView
				.findViewById(R.id.physiqueConclusionEditText);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getWaistlineButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				readData(waistlineEditText);
			}
		});

		getHiplineButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				readData(hiplineEditText);
			}
		});
		getBustButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				readData(bustEditText);
			}
		});
	}

	private boolean dataReading = false;
	private ReadDataTask readDataTask;

	private void readData(EditText editText) {
		if (!dataReading) {
			dataReading = true;
			// new ReadDataTask(getActivity(), editText).execute();

			if (readDataTask != null
					&& readDataTask.getStatus() == AsyncTask.Status.RUNNING) {
				readDataTask.cancel(true); // 如果Task还在运行，则先取消它
			}
			// 启动新的任务
			readDataTask = new ReadDataTask(getActivity(), editText);
			readDataTask.execute();
		}
	}

	private class ReadDataTask extends AsyncTask<Void, Void, Integer> {
		private Activity activity;
		private WaistlineManager waistlineManager;
		private ProgressDialog progressDialog;
		private EditText editText;

		public ReadDataTask(Activity activity, EditText editText) {
			this.activity = activity;
			this.editText = editText;
			waistlineManager = new WaistlineManager(activity);
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(activity, "温馨提示", "获取中。。。",
					false, true);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if (waistlineManager != null) {
						waistlineManager.closeDevice();
						dataReading = false;
					}
				}
			});
		}

		@Override
		protected Integer doInBackground(Void... params) {
			if (waistlineManager.connectDevice()) {
				int result = waistlineManager.readData();
				waistlineManager.closeDevice();
				return result;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Integer result) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (result != null) {
				editText.setText(result + "");
				onConclusion();
			} else {
				ToastUtils.showToast(activity, waistlineManager.getTipsInfo());
			}
			waistlineManager = null;
			dataReading = false;
		}
	}

	@Override
	public void onConclusion() {
		// 体温结论
		// 正常体温为36-37 摄氏度， 36-37摄氏度以下为体温太低，
		// 37.3～38摄氏度为低热（体温升高），38.1～39摄氏度为中度发热（中度发烧），39.1～41摄氏度为高热（重度发烧），41摄氏度以上为超高热
		String temperatureStr = temperatureEditText.getText().toString();
		if (!TextUtils.isEmpty(temperatureStr)) {
			double temperature = Double.parseDouble(temperatureStr);
			String result = "";
			if (temperature < 36) {
				result = "体温太低";
			} else if (temperature < 37.3) {
				result = "正常体温";
			} else if (temperature < 38.1) {
				result = "低热";
			} else if (temperature < 39.1) {
				result = "中度发烧";
			} else if (temperature < 41.1) {
				result = "重度发烧";
			} else {
				result = "超高热";
			}
			temperatureConclusionEditText.setText(result);
		} else {
			temperatureConclusionEditText.setText("");
		}

		// 体质结论
		// 体质指数（BMI）=体重（kg）÷身高^2（m）
		String heightStr = heightEditText.getText().toString();
		String weightStr = weightEditText.getText().toString();
		if (!TextUtils.isEmpty(heightStr) && !TextUtils.isEmpty(weightStr)) {
			double height = Double.parseDouble(heightStr);
			double weight = Double.parseDouble(weightStr);
			double BMI = new BigDecimal(weight).divide(
					new BigDecimal(Math.pow(height / 100, 2)), 1,
					RoundingMode.DOWN).doubleValue();
			BMIEditText.setText("" + BMI);
			// BMI值 <18.5 18.5～23.9 24.0～27.9 ≥28
			// 体质 体重过低 体重正常 超重 肥胖
			String result = "";
			if (BMI < 18.5) {
				result = "体重过低";
			} else if (BMI < 24.0) {
				result = "体重正常";
			} else if (BMI < 28) {
				result = "超重";
			} else {
				result = "肥胖";
			}
			physiqueConclusionEditText.setText(result);
		} else {
			physiqueConclusionEditText.setText("");
		}
		// 三围结论
		String waistlineStr = waistlineEditText.getText().toString();// 腰围
		String hiplineStr = hiplineEditText.getText().toString();// 臀围
		if (!TextUtils.isEmpty(waistlineStr) && !TextUtils.isEmpty(hiplineStr)) {
			double waistline = Double.parseDouble(waistlineStr);
			double hipline = Double.parseDouble(hiplineStr);
			double waistToHipratio = new BigDecimal(waistline).divide(
					new BigDecimal(hipline), 2, RoundingMode.DOWN)
					.doubleValue();
			waistToHipratioEditText.setText("" + waistToHipratio);
			ArchiveInfo archiveInfo = MyApplication.getInstance()
					.getArchiveInfo();
			if (archiveInfo != null) {
				// 男士大于 1，女士大于 0.8，即被认为有腹部肥胖
				if ("男".equals(archiveInfo.getGender())) {
					if (waistToHipratio > 1) {
						BWHConclusionEditText.setText("有腹部肥胖");
					} else {
						BWHConclusionEditText.setText("没有腹部肥胖");
					}
				} else if ("女".equals(archiveInfo.getGender())) {
					if (waistToHipratio > 0.8) {
						BWHConclusionEditText.setText("有腹部肥胖");
					} else {
						BWHConclusionEditText.setText("没有腹部肥胖");
					}
				} else {
					ToastUtils.showToast(getActivity(), "没有性别信息，不能计算出腰臀比结论！");
					BWHConclusionEditText.setText("");
				}
			} else {
				ToastUtils.showToast(getActivity(), "没有性别信息，不能计算出腰臀比结论！");
				BWHConclusionEditText.setText("");
			}
		} else {
			BWHConclusionEditText.setText("");
		}

	}

	@Override
	public void onSave() {
	}

	@Override
	public void onUpload() {
	}

	@Override
	public String getPrintData(String examinationNo) {
		String printStr = ExaminationManager.getInstance(getActivity())
				.getPrintTemplate(R.raw.print_routine_examination_template,
						examinationNo);
		// 替换相关数据
		printStr = printStr
				.replace("{temperature}",
						temperatureEditText.getText().toString())// 体温
				.replace("{temperature_conclusion}",
						temperatureConclusionEditText.getText().toString())// 结论

				.replace("{height}", heightEditText.getText().toString())// 身高
				.replace("{weight}", weightEditText.getText().toString())// 体重
				.replace("{BMI}", BMIEditText.getText().toString())// BMI
				.replace("{constitutional_index_conclusion}",
						physiqueConclusionEditText.getText().toString())// 结论

				.replace("{waist}", waistlineEditText.getText().toString())// 腰围
				.replace("{hips}", hiplineEditText.getText().toString())// 臀围
				.replace("{bust}", bustEditText.getText().toString())// 胸围
				.replace("{waist_to_hipratio}",
						waistToHipratioEditText.getText().toString())// 腰臀比
				.replace("{BWH_conclusion}",
						BWHConclusionEditText.getText().toString());// 结论
		return printStr;
	}

	@Override
	public void getSaveData(ExaminationInfo examinationInfo) {

		String routineCheckupsStr = examinationInfo.getRoutineCheckups();
		JSONObject routineCheckups = null;
		try {
			if (TextUtils.isEmpty(routineCheckupsStr)) {
				routineCheckups = new JSONObject();
				String date = System.currentTimeMillis() + "";
				routineCheckups.put("createTime", date);
				routineCheckups.put("updateTime", date);
			} else {
				routineCheckups = new JSONObject(routineCheckupsStr);
				String date = System.currentTimeMillis() + "";
				routineCheckups.put("updateTime", date);
			}
			if (temperatureEditText != null) {
				routineCheckups.put("temperature", temperatureEditText
						.getText().toString());
			}
			if (temperatureConclusionEditText != null) {
				routineCheckups.put("temperatureConclusion",
						temperatureConclusionEditText.getText().toString());
			}
			if (heightEditText != null) {
				routineCheckups.put("height", heightEditText.getText()
						.toString());
			}
			if (weightEditText != null) {
				routineCheckups.put("weight", weightEditText.getText()
						.toString());
			}
			if (BMIEditText != null) {
				routineCheckups.put("BMI", BMIEditText.getText().toString());
			}
			if (physiqueConclusionEditText != null) {
				routineCheckups.put("constitutionalIndexConclusion",
						physiqueConclusionEditText.getText().toString());
			}
			if (waistlineEditText != null) {
				routineCheckups.put("waist", waistlineEditText.getText()
						.toString());
			}
			if (hiplineEditText != null) {
				routineCheckups.put("hips", hiplineEditText.getText()
						.toString());
			}
			if (bustEditText != null) {
				routineCheckups.put("bust", bustEditText.getText().toString());
			}
			if (waistToHipratioEditText != null) {
				routineCheckups.put("waistToHipratio", waistToHipratioEditText
						.getText().toString());
			}
			if (BWHConclusionEditText != null) {
				routineCheckups.put("BWHConclusion", BWHConclusionEditText
						.getText().toString());
			}
			examinationInfo.setRoutineCheckups(routineCheckups.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setSaveData(ExaminationInfo examinationInfo1) {
		ExaminationInfo examinationInfo = MyApplication.getInstance()
				.getExaminationInfo();
		if (examinationInfo != null) {
			String msg = examinationInfo.getRoutineCheckups();
			if (TextUtils.isEmpty(msg)) {
				// temperatureEditText.setText("");
				// temperatureConclusionEditText.setText("");
				// heightEditText.setText("");
				// weightEditText.setText("");
				// BMIEditText.setText("");
				// physiqueConclusionEditText.setText("");
				// waistlineEditText.setText("");
				// hiplineEditText.setText("");
				// bustEditText.setText("");
				// waistToHipratioEditText.setText("");
				// BWHConclusionEditText.setText("");
				return;
			}
			try {
				JSONObject jsonObject = new JSONObject(msg);
				temperatureEditText.setText(jsonObject.getString("temperature")
						.toString());
				temperatureConclusionEditText.setText(jsonObject
						.getString("temperatureConclusion"));
				heightEditText.setText(jsonObject.getString("height"));
				weightEditText.setText(jsonObject.getString("weight"));
				BMIEditText.setText(jsonObject.getString("BMI"));
				physiqueConclusionEditText.setText(jsonObject
						.getString("constitutionalIndexConclusion"));
				waistlineEditText.setText(jsonObject.getString("waist"));
				hiplineEditText.setText(jsonObject.getString("hips"));
				bustEditText.setText(jsonObject.getString("bust"));
				waistToHipratioEditText.setText(jsonObject
						.getString("waistToHipratio"));
				BWHConclusionEditText.setText(jsonObject
						.getString("BWHConclusion"));
				onConclusion();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onReset() {
		super.onReset();
		temperatureEditText.setText("");
		temperatureConclusionEditText.setText("");
		heightEditText.setText("");
		weightEditText.setText("");
		BMIEditText.setText("");
		physiqueConclusionEditText.setText("");
		waistlineEditText.setText("");
		hiplineEditText.setText("");
		bustEditText.setText("");
		waistToHipratioEditText.setText("");
		BWHConclusionEditText.setText("");
	}
}
