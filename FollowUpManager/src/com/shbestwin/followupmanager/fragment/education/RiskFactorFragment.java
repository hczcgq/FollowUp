package com.shbestwin.followupmanager.fragment.education;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.model.device.BloodGlucose;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.view.widget.CardHeaderLayout;
import com.shbestwin.followupmanager.view.widget.CardLayout;

/**
 * 
 * 风险因素
 *
 * @version
 */
public class RiskFactorFragment extends BaseFragment {
    private CardLayout cardLayout;

    public static RiskFactorFragment newInstance() {
        RiskFactorFragment fragment = new RiskFactorFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_education_risk_factor, container, false);
        cardLayout = (CardLayout) rootView.findViewById(R.id.cardLayout);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cardLayout.removeAllViews();
        // 1、血压信息
        double systolicPressure = 123;// 收缩压
        double diastolicPressure = 85;// 舒张压
        // 2、血糖信息
        int bloodGlucoseType = 0;
        double bloodGlucose = 6.5;

        // 3、肥胖
        double BMI = 28.5;
        
        // 4、甘油三酯
        double bloodTg = 1.7;
        
        // 5、高密度脂蛋白
        double bloodHdl = 1.43;
        
        // 6、低密度脂蛋白
        double bloodLdl = 2.0;
        
        // 7、血总胆固醇
        double bloodChol = 2.81;
        

        ExaminationInfo generalExamination = MyApplication.getInstance()
                .getExaminationInfo();
        if (generalExamination != null) {
            String msg = generalExamination.getBloodPressure();
            if (!TextUtils.isEmpty(msg)) {
                try {
                    JSONObject json = new JSONObject(msg);
                    systolicPressure = Double.parseDouble(json
                            .getString("systolicPressure"));// 收缩压
                    diastolicPressure = Double.parseDouble(json
                            .getString("diastolicPressure"));// 舒张压
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            String msg1 = generalExamination.getBloodSugar();
            if (!TextUtils.isEmpty(msg1)) {
                try {
                    JSONObject json = new JSONObject(msg1);
                    if (json.getString("type").equals(
                            BloodGlucose.TYPE_EMPTY_STOMACH)) {
                        bloodGlucoseType = 0;
                    } else {
                        bloodGlucoseType = 1;
                    }
                    bloodGlucose = Double.parseDouble(json.getString("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            
            String msg2 = generalExamination.getBodyComposition();
            if (!TextUtils.isEmpty(msg2)) {
                try {
                    JSONObject json = new JSONObject(msg2);
                    BMI = Double.parseDouble(json.getString("BMI"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            
            String msg3 = generalExamination.getBloodFat();
            if (!TextUtils.isEmpty(msg2)) {
                try {
                    JSONObject json = new JSONObject(msg3);
                    bloodChol = Float.parseFloat(json.getString("CHOL"));// 胆固醇
                    bloodTg = Float.parseFloat(json.getString("HDL"));// 甘油三脂
                    bloodHdl= Float.parseFloat(json.getString("LDL"));// 高密度脂蛋白
                    bloodLdl = bloodChol - bloodHdl - bloodLdl / 2.2F;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        
        
        

        // 1、血压信息
        if ((systolicPressure < 120) && (diastolicPressure < 80)) {
            addItem("低血压", R.string.jkjy_fxys_tips_hypopiesia);
        } else if (((120 < systolicPressure) && (systolicPressure < 139))
                || ((diastolicPressure > 80) && (diastolicPressure < 89))) {
        } else if (((140 < systolicPressure) && (systolicPressure < 159))
                || ((diastolicPressure > 90) && (diastolicPressure < 99))) {
            addItem("1级高血压【轻度】", R.string.jkjy_fxys_tips_hypertension);
        } else if (((160 < systolicPressure) && (systolicPressure < 179))
                || ((diastolicPressure > 100) && (diastolicPressure < 109))) {
            addItem("2级高血压【中度】", R.string.jkjy_fxys_tips_hypertension);
        } else if ((systolicPressure > 160) || (diastolicPressure > 110)) {
            addItem("3级高血压【高度】", R.string.jkjy_fxys_tips_hypertension);
        } else if ((systolicPressure >= 140) && (diastolicPressure < 90)) {
            addItem("单纯收缩期高血压", R.string.jkjy_fxys_tips_ISH);
        }

        // 2、血糖信息
        switch (bloodGlucoseType) {
        // 空腹血糖:正常为3.9～6.1毫摩尔/升。小于3.9毫摩尔/升为低血糖，大于6.1-6.9mmol/L为血糖偏高，大于6.9mmol/L为高血糖
        case BloodGlucose.TYPE_EMPTY_STOMACH:
            if (bloodGlucose < 3.9) {
                addItem("血糖偏低", R.string.jkjy_fxys_tips_blood_glucose_low);
            } else if (bloodGlucose >= 6.1) {
                addItem("血糖偏高", R.string.jkjy_fxys_tips_blood_glucose_over);
            }
            // 餐后2小时血糖：餐后2小时：正常为3.9～7.8毫摩尔/升，小于3.9毫摩尔/升为低血糖，,大于7.8-11mmol/L为血糖偏高，大于11.1mmol/L为高血糖
        case BloodGlucose.TYPE_AFTER_2HOUR:
            if (bloodGlucose < 3.9) {
                addItem("血糖偏低", R.string.jkjy_fxys_tips_blood_glucose_low);
            } else if (bloodGlucose >= 7.9) {
                addItem("血糖偏高", R.string.jkjy_fxys_tips_blood_glucose_over);
            }
        }

        // 3、肥胖
        // 过轻：低于18.5
        // 正常：18.5-24.99
        // 过重：25-28
        // 肥胖：28-32
        // 非常肥胖, 高于32
        if (BMI < 18.5) {
            addItem("低体重", R.string.jkjy_fxys_tips_low_weight);
        } else if (BMI < 24) {// 体重正常
        } else if (BMI <= 28) {// 超重
            addItem("超重", R.string.jkjy_fxys_tips_over_weight);
        } else if (BMI <= 32) {// 肥胖
            addItem("肥胖", R.string.jkjy_fxys_tips_obesity);
        } else {
            addItem("高度肥胖", R.string.jkjy_fxys_tips_obesity_over);
        }

        // 4、甘油三酯
        // 0.56-1.70mmol/L
        if (bloodTg < 0.56) {
            addItem("甘油三酯偏少", R.string.jkjy_fxys_tips_glycerin_trilaurate_low);
        } else if (bloodTg > 1.70) {
            addItem("甘油三酯偏高", R.string.jkjy_fxys_tips_glycerin_trilaurate_over);
        }

        // 5、高密度脂蛋白
        // 1.16-1.42mmol/L
        if (bloodHdl < 1.16) {
            addItem("高密度脂蛋白偏低",
                    R.string.jkjy_fxys_tips_high_density_lipoprotein_low);
        } else if (bloodHdl > 1.42) {
            addItem("高密度脂蛋白偏高",
                    R.string.jkjy_fxys_tips_high_density_lipoprotein_over);
        }

        // 6、低密度脂蛋白
        // 低密度脂蛋白胆固醇=总胆固醇-高密度脂蛋白胆固醇-（甘油三酯/2.2）
        // 正常参考范围：2.1-3.1。
        if (bloodLdl < 2.1) {
            addItem("低密度脂蛋白偏低",
                    R.string.jkjy_fxys_tips_low_density_lipoprotein_low);
        } else if (bloodLdl > 3.1) {
            addItem("低密度脂蛋白偏高",
                    R.string.jkjy_fxys_tips_low_density_lipoprotein_over);
        }

        // 7、血总胆固醇
        // 2.82-5.95mmol/L
        if (bloodChol < 2.82) {
            addItem("血总胆固醇偏少", R.string.jkjy_fxys_tips_cholesterin_low);
        } else if (bloodChol > 5.95) {
            addItem("血总胆固醇偏多", R.string.jkjy_fxys_tips_cholesterin_over);
        }

        cardLayout.reload();

    }

    private void addItem(String title, int contentResId) {
        ViewGroup view = (ViewGroup) View.inflate(getActivity(),
                R.layout.view_risk_factor_item, null);
        ((CardHeaderLayout) view.getChildAt(0)).setTitleText(title);
        ((TextView) view.getChildAt(1)).setText(Html
                .fromHtml(getString(contentResId)));
        cardLayout.addView(view);
    }
}
