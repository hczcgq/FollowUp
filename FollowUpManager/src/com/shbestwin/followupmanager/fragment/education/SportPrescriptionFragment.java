package com.shbestwin.followupmanager.fragment.education;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.BaseFragment;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_education_sport_prescription, container, false);
		adviseTextView = (TextView) rootView.findViewById(R.id.adviseTextView);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		BMI   18.5-24.9   消耗卡路里100-150
//		BMI   25-28   消耗卡路里250-300
//		BMI   >28   消耗卡路里300以上
//		BMI   <18.5   消耗卡路里200
		double BMI = 27;
		String advise = "";
		if (BMI < 18.5) {
			advise = "消耗卡路里200";
		} else if (BMI < 25) {
			advise = "消耗卡路里100-150";
		} else if(BMI < 29) {
			advise = "消耗卡路里250-300";
		} else {
			advise = "消耗卡路里300以上";
		}
		adviseTextView.setText("鉴于您的体质信息，建议您每天运动"+advise+"。根据上图所示每项运动所消耗的能量值，请您选择合理的运动方式和运动时间，并加强平常的锻炼。");
	}
}
