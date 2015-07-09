package com.shbestwin.followupmanager.fragment.examination.quick;

import java.util.ArrayList;
import java.util.Date;

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
import com.shbestwin.followupmanager.common.util.CollectionUtils;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.manager.device.ElectrocardiogramManager;
import com.shbestwin.followupmanager.model.device.Electrocardiogram;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;
import com.shbestwin.followupmanager.view.dialog.examination.ElectrocardiogramChartDialog;
import com.shbestwin.followupmanager.view.widget.MeasureTipsLayout;

/**
 * 
 * 心电分析
 *
 * @version
 */
public class ElectrocardiogramAnalyzerFragment extends
        BaseQuickExaminationFragment {
    private MeasureTipsLayout measureTipsLayuout;

    private Button getDataButton, showWaveformButton;

    private EditText checkTimeEditText;

    private EditText heartRateEditText, PROfAVREditText, QTOfAVREditText,
            RvoltOfAVREditText, PvoltOfAVREditText, TvoltOfAVREditText,
            STvoltOfAVREditText;

    private EditText conclusionEditText;

    private Electrocardiogram electrocardiogram;

    private ReadDataTask readDataTask;

    public static ElectrocardiogramAnalyzerFragment newInstance() {
        ElectrocardiogramAnalyzerFragment fragment = new ElectrocardiogramAnalyzerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_quick_examination_electrocardiogram_analyzer,
                container, false);
        measureTipsLayuout = (MeasureTipsLayout) rootView
                .findViewById(R.id.measureTipsLayuout);
        getDataButton = (Button) rootView.findViewById(R.id.getDataButton);
        showWaveformButton = (Button) rootView
                .findViewById(R.id.showWaveformButton);
        checkTimeEditText = (EditText) rootView
                .findViewById(R.id.checkTimeEditText);
        heartRateEditText = (EditText) rootView
                .findViewById(R.id.heartRateEditText);
        PROfAVREditText = (EditText) rootView
                .findViewById(R.id.PROfAVREditText);
        QTOfAVREditText = (EditText) rootView
                .findViewById(R.id.QTOfAVREditText);
        RvoltOfAVREditText = (EditText) rootView
                .findViewById(R.id.RvoltOfAVREditText);
        PvoltOfAVREditText = (EditText) rootView
                .findViewById(R.id.PvoltOfAVREditText);
        TvoltOfAVREditText = (EditText) rootView
                .findViewById(R.id.TvoltOfAVREditText);
        STvoltOfAVREditText = (EditText) rootView
                .findViewById(R.id.STvoltOfAVREditText);
        conclusionEditText = (EditText) rootView
                .findViewById(R.id.conclusionEditText);
        electrocardiogram = null;

        // 使用当前日期
        checkTimeEditText.setText(DateUtils
                .formatDate(new Date(), "yyyy-MM-dd"));
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        measureTipsLayuout.setTips(R.string.jktj_measure_tips_xindianfenxi);
        getDataButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

        showWaveformButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CollectionUtils.isEmpty(points)) {
                    ToastUtils.showToast(getActivity(), "抱歉，没有心电图波形数据!");
                    return;
                }
                showWaveformDialog();
            }
        });

        checkTimeEditText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = DatePickerDialog
                        .newInstance();
                datePickerDialog.show(getChildFragmentManager(),
                        "datePickerDialog");
                datePickerDialog
                        .setOnDatePickerListener(new OnDatePickerListener() {
                            @Override
                            public void onConfirmClick(long timeInMillis,
                                    String formatDate) {
                                checkTimeEditText.setText(formatDate);
                                datePickerDialog.hide();
                            }
                        });
            }
        });
    }

    private void showWaveformDialog() {
        final ElectrocardiogramChartDialog electrocardiogramChartDialog = ElectrocardiogramChartDialog
                .newInstance(points);
        electrocardiogramChartDialog.show(getChildFragmentManager(),
                "electrocardiogramChartDialog");
        electrocardiogramChartDialog
                .setOnConfirmClickListener(new OnConfirmClickListener() {

                    @Override
                    public void onConfirmClick() {
                        electrocardiogramChartDialog.hide();
                    }
                });
    }

    private ArrayList<Integer> points = null;

    private boolean dataReading = false;

    private void readData() {
        if (!dataReading) {
            dataReading = true;
            // new ReadDataTask(getActivity()).execute();
            
            
            if (readDataTask != null
                    && readDataTask.getStatus() == AsyncTask.Status.RUNNING) {
                readDataTask.cancel(true); // 如果Task还在运行，则先取消它
            }
            // 启动新的任务
            readDataTask = new ReadDataTask(getActivity());
            readDataTask.execute();
        }
    }

    private class ReadDataTask extends AsyncTask<Void, Void, Electrocardiogram> {
        private Activity activity;

        private ElectrocardiogramManager electrocardiogramManager;

        private ProgressDialog progressDialog;

        public ReadDataTask(Activity activity) {
            this.activity = activity;
            electrocardiogramManager = new ElectrocardiogramManager(activity);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            if (isCancelled())
                return;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(activity, "温馨提示", "获取中。。。",
                    false, true);
            progressDialog.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (electrocardiogramManager != null) {
                        electrocardiogramManager.closeDevice();
                        dataReading = false;
                    }
                }
            });

        }

        @Override
        protected Electrocardiogram doInBackground(Void... params) {
            if (electrocardiogramManager.connectDevice()) {
                Electrocardiogram electrocardiogram = electrocardiogramManager
                        .readData();
                electrocardiogramManager.closeDevice();
                return electrocardiogram;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Electrocardiogram result) {
            electrocardiogram = result;
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            if (result != null) {
                showWaveformButton.setEnabled(true);
                heartRateEditText.setText(result.getHeartRate() + "");
                PROfAVREditText.setText(result.getPROfAVR() + "");
                QTOfAVREditText.setText(result.getQTOfAVR() + "");
                RvoltOfAVREditText.setText(result.getRvoltOfAVR() + "");
                PvoltOfAVREditText.setText(result.getPvoltOfAVR() + "");
                TvoltOfAVREditText.setText(result.getTvoltOfAVR() + "");
                STvoltOfAVREditText.setText(result.getSTvoltOfAVR() + "");
                points = result.getPoints();
                onConclusion();
            } else {
                showWaveformButton.setEnabled(false);
                ToastUtils.showToast(activity,
                        electrocardiogramManager.getTipsInfo());
            }
            electrocardiogramManager = null;
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
        if (electrocardiogram != null) {
            StringBuilder result = new StringBuilder();
            // STStatus：ST段是否正常,0-正常，1-ST段抬高，2-ST段压低
            // ST段正常，
            switch (electrocardiogram.getSTStatus()) {
            case 0:
                result.append("ST段正常，");
                break;
            case 1:
                result.append("ST段抬高，");
                break;
            case 2:
                result.append("ST段压低，");
                break;
            }
            // heartRhythmStatus：0-没有发现心律失常，1-心律失常
            // 没有发现心律失常
            switch (electrocardiogram.getHeartRhythmStatus()) {
            case 0:
                result.append("没有发现心律失常，");
                break;
            case 1:
                result.append("心律失常，");
                break;
            }

            // waveformQuality： 0-波形质量正常，1-波形质量过差
            // 波形质量过差，
            switch (electrocardiogram.getWaveformQuality()) {
            case 0:
                result.append("波形质量正常，");
                break;
            case 1:
                result.append("波形质量过差，");
                break;
            }

            // heartRateStatus：
            // 000(0)-心率正常，001(1)-心率稍慢，010(2)-心律过慢，011(3)-心律稍快，100(4)-心律过快，101(5)-导连脱落
            // 心率过慢
            if ("000".equals(electrocardiogram.getHeartRateStatus())) {
                result.append("心率正常，");
            } else if ("001".equals(electrocardiogram.getHeartRateStatus())) {
                result.append("心率稍慢，");
            } else if ("010".equals(electrocardiogram.getHeartRateStatus())) {
                result.append("心律过慢，");
            } else if ("011".equals(electrocardiogram.getHeartRateStatus())) {
                result.append("心律稍快，");
            } else if ("100".equals(electrocardiogram.getHeartRateStatus())) {
                result.append("心律过快，");
            } else if ("101".equals(electrocardiogram.getHeartRateStatus())) {
                result.append("导连脱落，");
            }

            // wholeWaveform：整体波形是否正常，如果bit5-bit1代表的意义都正常，此位为0，否则为1
            // 整体波形异常
            switch (electrocardiogram.getWholeWaveform()) {
            case 0:
                result.append("整体波形正常");
                break;
            case 1:
                result.append("整体波形异常");
                break;
            }
            conclusionEditText.setText(result.toString());
        } else {
            conclusionEditText.setText("");
        }
    }

    @Override
    public String getPrintData(String examinationNo) {
        String printStr = ExaminationManager.getInstance(getActivity())
                .getPrintTemplate(R.raw.print_electrocardiogram_template,
                        examinationNo);
        // 替换相关数据
        printStr = printStr
                .replace("{check_time}", checkTimeEditText.getText().toString())// 检查时间
                .replace("{pulse_rate}", heartRateEditText.getText().toString())// 心率
                .replace("{PR_of_AVR}", PROfAVREditText.getText().toString())// 全局PR间期
                .replace("{QT_of_AVR}", QTOfAVREditText.getText().toString())// 平均QT间期
                .replace("{Rvolt_of_AVR}",
                        RvoltOfAVREditText.getText().toString())// 平均R波电压
                .replace("{Pvolt_of_AVR}",
                        PvoltOfAVREditText.getText().toString())// 平均P波电压
                .replace("{Tvolt_of_AVR}",
                        TvoltOfAVREditText.getText().toString())// 平均T波电压
                .replace("{STvolt_of_AVR}",
                        STvoltOfAVREditText.getText().toString())// ST段平均电压
                .replace("{conclusion}",
                        conclusionEditText.getText().toString());// 心电图结果
        return printStr;
    }

    @Override
    public void getSaveData(ExaminationInfo examinationInfo) {
        String ecgAnalysisStr = examinationInfo.getEcgAnalysis();
        JSONObject ecgAnalysis = null;
        try {
            if (TextUtils.isEmpty(ecgAnalysisStr)) {
                ecgAnalysis = new JSONObject();
                String date = System.currentTimeMillis() + "";
                ecgAnalysis.put("createTime", date);
                ecgAnalysis.put("updateTime", date);
            } else {
                ecgAnalysis = new JSONObject(ecgAnalysisStr);
                String date = System.currentTimeMillis() + "";
                ecgAnalysis.put("updateTime", date);
            }
            ecgAnalysis
                    .put("checkTime", checkTimeEditText.getText().toString());
            ecgAnalysis
                    .put("pulseRate", heartRateEditText.getText().toString());
            ecgAnalysis.put("PROfAVR", PROfAVREditText.getText().toString());
            ecgAnalysis.put("QTOfAVR", QTOfAVREditText.getText().toString());
            ecgAnalysis.put("RvoltOfAVR", RvoltOfAVREditText.getText()
                    .toString());
            ecgAnalysis.put("PvoltOfAVR", PvoltOfAVREditText.getText()
                    .toString());
            ecgAnalysis.put("TvoltOfAVR", TvoltOfAVREditText.getText()
                    .toString());
            ecgAnalysis.put("STvoltOfAVR", STvoltOfAVREditText.getText()
                    .toString());
            ecgAnalysis.put("conclusion", conclusionEditText.getText()
                    .toString());
//            ecgAnalysis.put("point", points);
            examinationInfo.setEcgAnalysis(ecgAnalysis.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSaveData(ExaminationInfo examinationInfo) {
    	if (examinationInfo != null) {
			String msg=examinationInfo.getEcgAnalysis();
			if(TextUtils.isEmpty(msg)){
				return;
			}
			try {
				JSONObject jsonObject =new JSONObject(msg);
				checkTimeEditText.setText(jsonObject.getString("checkTime"));
				heartRateEditText.setText(jsonObject.getString("pulseRate"));
				PROfAVREditText.setText(jsonObject.getString("PROfAVR"));
				QTOfAVREditText.setText(jsonObject.getString("QTOfAVR"));
				RvoltOfAVREditText.setText(jsonObject.getString("RvoltOfAVR"));
				PvoltOfAVREditText.setText(jsonObject.getString("PvoltOfAVR"));
				TvoltOfAVREditText.setText(jsonObject.getString("TvoltOfAVR"));
				STvoltOfAVREditText.setText(jsonObject.getString("STvoltOfAVR"));
				conclusionEditText.setText(jsonObject.getString("conclusion"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
    }
}
