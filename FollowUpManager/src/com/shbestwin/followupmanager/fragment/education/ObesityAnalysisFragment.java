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
 * 肥胖分析
 *
 * @version
 */
public class ObesityAnalysisFragment extends BaseFragment {
	private TextView analysisResultTextView, adviseTextView;
	private TextView heightTextView, weightTextView, BMITextView;
	private TextView waistTextView, hipsTextView, waistToHipratioTextView;
	private TextView bodyImpedanceTextView, fatTextView, visceralFatTextView, KCALTextView;

	public static ObesityAnalysisFragment newInstance() {
		ObesityAnalysisFragment fragment = new ObesityAnalysisFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_education_obesity_analysis, container, false);
		analysisResultTextView = (TextView) rootView.findViewById(R.id.analysisResultTextView);
		adviseTextView = (TextView) rootView.findViewById(R.id.adviseTextView);

		heightTextView = (TextView) rootView.findViewById(R.id.heightTextView);
		weightTextView = (TextView) rootView.findViewById(R.id.weightTextView);
		BMITextView = (TextView) rootView.findViewById(R.id.BMITextView);

		waistTextView = (TextView) rootView.findViewById(R.id.waistTextView);
		hipsTextView = (TextView) rootView.findViewById(R.id.hipsTextView);
		waistToHipratioTextView = (TextView) rootView.findViewById(R.id.waistToHipratioTextView);

		bodyImpedanceTextView = (TextView) rootView.findViewById(R.id.bodyImpedanceTextView);
		fatTextView = (TextView) rootView.findViewById(R.id.fatTextView);
		visceralFatTextView = (TextView) rootView.findViewById(R.id.visceralFatTextView);
		KCALTextView = (TextView) rootView.findViewById(R.id.KCALTextView);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		heightTextView.setText("");
		weightTextView.setText("");
		BMITextView.setText("");
		waistTextView.setText("");
		hipsTextView.setText("");
		waistToHipratioTextView.setText("");
		bodyImpedanceTextView.setText("");
		fatTextView.setText("");
		visceralFatTextView.setText("");
		KCALTextView.setText("");

		double BMI = 28.5;
		// 过轻：低于18.5
		// 正常：18.5-24.99
		// 过重：25-28
		// 肥胖：28-32
		// 非常肥胖, 高于32
		String result = "体重正常";
		String advise = "";
		if (BMI < 18.5) {
			result = "低体重";
			advise = getString(R.string.jkjy_fpfx_tips_low_weight);
		} else if (BMI < 24) {// 体重正常
			result = "体重正常";
			advise = "";
		} else if (BMI <= 28) {// 超重
			result = "超重";
			advise = getString(R.string.jkjy_fpfx_tips_over_weight);
		} else if (BMI <= 32) {// 肥胖
			result = "肥胖";
			advise = getString(R.string.jkjy_fpfx_tips_over_weight);
		} else {
			result = "高度肥胖";
			advise = getString(R.string.jkjy_fpfx_tips_over_weight);
		}
		analysisResultTextView.setText(result);
		adviseTextView.setText(advise);

	}
}
