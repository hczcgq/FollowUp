package com.shbestwin.followupmanager.fragment.education;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.model.device.BloodGlucose;

/**
 * 
 * 综合评估
 *
 * @version
 */
public class ComprehensiveEvaluationFragment extends BaseFragment {
	private TextView obesityAnalysisTextView, riskFactorTextView;

	public static ComprehensiveEvaluationFragment newInstance() {
		ComprehensiveEvaluationFragment fragment = new ComprehensiveEvaluationFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_education_comprehensive_evaluation, container, false);
		obesityAnalysisTextView = (TextView) rootView.findViewById(R.id.obesityAnalysisTextView);
		riskFactorTextView = (TextView) rootView.findViewById(R.id.riskFactorTextView);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		double BMI = 28.5;
		// 过轻：低于18.5
		// 正常：18.5-24.99
		// 过重：25-28
		// 肥胖：28-32
		// 非常肥胖, 高于32
		String advise = "无";
		if (BMI < 18.5) {// 低体重
			advise = getString(R.string.jkjy_fpfx_tips_low_weight);
		} else if (BMI < 24) {// 体重正常
			advise = "无";
		} else if (BMI <= 28) {// 超重
			advise = getString(R.string.jkjy_fpfx_tips_over_weight);
		} else if (BMI <= 32) {// 肥胖
			advise = getString(R.string.jkjy_fpfx_tips_over_weight);
		} else {
			advise = getString(R.string.jkjy_fpfx_tips_over_weight);
		}
		obesityAnalysisTextView.setText(advise);

		StringBuilder riskFactor = new StringBuilder();

		int index = 0;
		// 1、血压信息
		double systolicPressure = 123;// 收缩压
		double diastolicPressure = 85;// 舒张压
		if ((systolicPressure < 120) && (diastolicPressure < 80)) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_hypopiesia)).append("<br/>");
		} else if (((120 < systolicPressure) && (systolicPressure < 139)) || ((diastolicPressure > 80) && (diastolicPressure < 89))) {
		} else if (((140 < systolicPressure) && (systolicPressure < 159)) || ((diastolicPressure > 90) && (diastolicPressure < 99))) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_hypertension)).append("<br/>");
		} else if (((160 < systolicPressure) && (systolicPressure < 179)) || ((diastolicPressure > 100) && (diastolicPressure < 109))) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_hypertension)).append("<br/>");
		} else if ((systolicPressure > 160) || (diastolicPressure > 110)) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_hypertension)).append("<br/>");
		} else if ((systolicPressure >= 140) && (diastolicPressure < 90)) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_ISH)).append("<br/>");
		}

		// 2、血糖信息
		int bloodGlucoseType = 0;
		double bloodGlucose = 6.5;
		switch (bloodGlucoseType) {
		// 空腹血糖:正常为3.9～6.1毫摩尔/升。小于3.9毫摩尔/升为低血糖，大于6.1-6.9mmol/L为血糖偏高，大于6.9mmol/L为高血糖
		case BloodGlucose.TYPE_EMPTY_STOMACH:
			if (bloodGlucose < 3.9) {
				index++;
				riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_blood_glucose_low)).append("<br/>");
			} else if (bloodGlucose >= 6.1) {
				index++;
				riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_blood_glucose_over)).append("<br/>");
			}
			// 餐后2小时血糖：餐后2小时：正常为3.9～7.8毫摩尔/升，小于3.9毫摩尔/升为低血糖，,大于7.8-11mmol/L为血糖偏高，大于11.1mmol/L为高血糖
		case BloodGlucose.TYPE_AFTER_2HOUR:
			if (bloodGlucose < 3.9) {
				index++;
				riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_blood_glucose_low)).append("<br/>");
			} else if (bloodGlucose >= 7.9) {
				index++;
				riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_blood_glucose_over)).append("<br/>");
			}
		}

		// 3、肥胖
		// 过轻：低于18.5
		// 正常：18.5-24.99
		// 过重：25-28
		// 肥胖：28-32
		// 非常肥胖, 高于32
		if (BMI < 18.5) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_low_weight)).append("<br/>");
		} else if (BMI < 24) {// 体重正常
		} else if (BMI <= 28) {// 超重
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_over_weight)).append("<br/>");
		} else if (BMI <= 32) {// 肥胖
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_obesity)).append("<br/>");
		} else {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_obesity_over)).append("<br/>");
		}

		// 4、甘油三酯
		double bloodTg = 1.7;
		// 0.56-1.70mmol/L
		if (bloodTg < 0.56) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_glycerin_trilaurate_low)).append("<br/>");
		} else if (bloodTg > 1.70) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_glycerin_trilaurate_over)).append("<br/>");
		}

		// 5、高密度脂蛋白
		double bloodHdl = 1.43;
		// 1.16-1.42mmol/L
		if (bloodHdl < 1.16) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_high_density_lipoprotein_low)).append("<br/>");
		} else if (bloodHdl > 1.42) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_high_density_lipoprotein_over)).append("<br/>");
		}

		// 6、低密度脂蛋白
		double bloodLdl = 2.0;
		// 低密度脂蛋白胆固醇=总胆固醇-高密度脂蛋白胆固醇-（甘油三酯/2.2）
		// 正常参考范围：2.1-3.1。
		if (bloodLdl < 2.1) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_low_density_lipoprotein_low)).append("<br/>");
		} else if (bloodLdl > 3.1) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_low_density_lipoprotein_over)).append("<br/>");
		}

		// 7、血总胆固醇
		double bloodChol = 2.81;
		// 2.82-5.95mmol/L
		if (bloodChol < 2.82) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_cholesterin_low)).append("<br/>");
		} else if (bloodChol > 5.95) {
			index++;
			riskFactor.append("（" + index + "）、").append(getString(R.string.jkjy_fxys_tips_cholesterin_over)).append("<br/>");
		}

		if (index > 0) {
			riskFactorTextView.setText(Html.fromHtml(riskFactor.toString()));
		} else {
			riskFactorTextView.setText("无");
		}
	}
}
