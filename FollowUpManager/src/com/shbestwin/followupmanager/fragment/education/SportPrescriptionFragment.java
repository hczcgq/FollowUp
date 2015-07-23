package com.shbestwin.followupmanager.fragment.education;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;

/**
 * 
 * 运动处方
 * 
 * @version
 */
public class SportPrescriptionFragment extends BaseFragment {
	private TextView adviseTextView;

	public static SportPrescriptionFragment newInstance() {
		SportPrescriptionFragment fragment = new SportPrescriptionFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_education_sport_prescription, container,
				false);
		adviseTextView = (TextView) rootView.findViewById(R.id.adviseTextView);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// BMI 18.5-24.9 消耗卡路里100-150
		// BMI 25-28 消耗卡路里250-300
		// BMI >28 消耗卡路里300以上
		// BMI <18.5 消耗卡路里200
		double BMI = 27;
		ExaminationInfo generalExamination = MyApplication.getInstance()
				.getExaminationInfo();
		if (generalExamination != null) {
			String msg = generalExamination.getBodyComposition();
			if (!TextUtils.isEmpty(msg)) {
				try {
					JSONObject jsonObject = new JSONObject(msg);
					if (!TextUtils.isEmpty(jsonObject.getString("BMI"))) {
						BMI = Double.parseDouble(jsonObject.getString("BMI"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}

		}

		String advise = "";
		if (BMI < 18.5) {
			advise = "消耗卡路里200";
		} else if (BMI < 25) {
			advise = "消耗卡路里100-150";
		} else if (BMI < 29) {
			advise = "消耗卡路里250-300";
		} else {
			advise = "消耗卡路里300以上";
		}
		adviseTextView.setText("鉴于您的体质信息，建议您每天运动" + advise
				+ "。根据上图所示每项运动所消耗的能量值，请您选择合理的运动方式和运动时间，并加强平常的锻炼。");
	}
}
