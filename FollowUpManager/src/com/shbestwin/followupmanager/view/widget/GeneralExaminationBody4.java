package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody4 extends LinearLayout implements
        IBaseGeneralExaminationBody {

    private RadioGroup lipRadioGroup, pharynxRadioGroup;

    private EditText et_lysl_zy, et_lysl_yy, et_jzsl_zy, et_jzsl_yy;

    private Spinner sn_tl, sn_ydnl;

    private String lip = "红润", pharynx = "无充血";

    public GeneralExaminationBody4(Context context) {
        this(context, null);
    }

    public GeneralExaminationBody4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GeneralExaminationBody4(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_general_examination_body4, this, true);
        sn_tl = (Spinner) rootView.findViewById(R.id.sn_tl);
        sn_ydnl = (Spinner) rootView.findViewById(R.id.sn_ydnl);
        et_lysl_zy = (EditText) rootView.findViewById(R.id.et_lysl_zy);
        et_lysl_yy = (EditText) rootView.findViewById(R.id.et_lysl_yy);
        et_jzsl_zy = (EditText) rootView.findViewById(R.id.et_jzsl_zy);
        et_jzsl_yy = (EditText) rootView.findViewById(R.id.et_jzsl_yy);
        lipRadioGroup = (RadioGroup) rootView.findViewById(R.id.lipRadioGroup);
        pharynxRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.pharynxRadioGroup);

        lipRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int checkid) {
                if (checkid == R.id.lipRadioButton0) {
                    lip = "红润";
                } else if (checkid == R.id.lipRadioButton1) {
                    lip = "苍白";
                } else if (checkid == R.id.lipRadioButton2) {
                    lip = "发绀";
                } else if (checkid == R.id.lipRadioButton3) {
                    lip = "皲裂";
                } else if (checkid == R.id.lipRadioButton4) {
                    lip = "疱疹";
                }
            }
        });

        pharynxRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.pharynxRadioButton0) {
                            pharynx = "无充血";
                        } else if (checkid == R.id.pharynxRadioButton1) {
                            pharynx = "充血";
                        } else if (checkid == R.id.pharynxRadioButton2) {
                            pharynx = "淋巴滤泡增生";
                        }
                    }
                });
    }

    @Override
    public void getData(GeneralExamination generalExamination) {
        generalExamination.setZqgn_kc(lip);
        generalExamination.setZqgn_yb(pharynx);
        generalExamination.setZqgn_lysl(et_lysl_zy.getText().toString() + "/"
                + et_lysl_yy.getText().toString());
        generalExamination.setZqgn_jzsl(et_jzsl_zy.getText().toString() + "/"
                + et_jzsl_yy.getText().toString());
        generalExamination.setZqgn_tl(ViewDataUtil.getSpinnerData(sn_tl));
        generalExamination.setZqgn_ydnl(ViewDataUtil.getSpinnerData(sn_ydnl));
    }

    @Override
    public void setData(GeneralExamination generalExamination) {
        if (generalExamination != null) {
            if (generalExamination.getZqgn_kc() != null
                    && generalExamination.getZqgn_kc() != "") {
                if (generalExamination.getZqgn_kc().equals("红润")) {
                    lipRadioGroup.check(R.id.lipRadioButton0);
                } else if (generalExamination.getZqgn_kc().equals("苍白")) {
                    lipRadioGroup.check(R.id.lipRadioButton1);
                } else if (generalExamination.getZqgn_kc().equals("发绀")) {
                    lipRadioGroup.check(R.id.lipRadioButton2);
                } else if (generalExamination.getZqgn_kc().equals("皲裂")) {
                    lipRadioGroup.check(R.id.lipRadioButton3);
                } else if (generalExamination.getZqgn_kc().equals("疱疹")) {
                    lipRadioGroup.check(R.id.lipRadioButton4);
                }
            }

            if (generalExamination.getZqgn_yb() != null
                    && generalExamination.getZqgn_yb() != "") {
                if (generalExamination.getZqgn_yb().equals("无充血")) {
                    pharynxRadioGroup.check(R.id.pharynxRadioButton0);
                } else if (generalExamination.getZqgn_yb().equals("充血")) {
                    pharynxRadioGroup.check(R.id.pharynxRadioButton1);
                } else if (generalExamination.getZqgn_yb().equals("淋巴滤泡增生")) {
                    pharynxRadioGroup.check(R.id.pharynxRadioButton2);
                }
            }

            String lysl = generalExamination.getZqgn_lysl();
            if (lysl.split("/").length == 1) {
                et_lysl_zy.setText(lysl.split("/")[0]);
            } else if (lysl.split("/").length == 2) {
                et_lysl_zy.setText(lysl.split("/")[0]);
                et_lysl_yy.setText(lysl.split("/")[1]);
            }

            String jzsl = generalExamination.getZqgn_jzsl();
            if (jzsl.split("/").length == 1) {
                et_jzsl_zy.setText(jzsl.split("/")[0]);
            } else if (jzsl.split("/").length == 2) {
                et_jzsl_zy.setText(jzsl.split("/")[0]);
                et_jzsl_yy.setText(jzsl.split("/")[1]);
            }
        }
    }

    @Override
    public boolean validate() {
        return true;
    }
}
