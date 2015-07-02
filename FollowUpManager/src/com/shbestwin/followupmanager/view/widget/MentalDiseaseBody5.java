package com.shbestwin.followupmanager.view.widget;

import java.util.Date;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class MentalDiseaseBody5 extends LinearLayout implements
        IBaseMentalDiseaseBody {
    private EditText et_qdzs, et_zs, et_zis, et_zsws, et_mccysj;

    private Spinner sn_gsqk, sn_zyqk;

    private FragmentManager fragmentManager;

    public MentalDiseaseBody5(Context context) {
        this(context, null);
    }

    public MentalDiseaseBody5(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MentalDiseaseBody5(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_mental_disease_body5, this, true);
        et_qdzs = (EditText) rootView.findViewById(R.id.et_qdzs);
        et_zs = (EditText) rootView.findViewById(R.id.et_zs);
        et_zis = (EditText) rootView.findViewById(R.id.et_zis);
        et_zsws = (EditText) rootView.findViewById(R.id.et_zsws);
        et_mccysj = (EditText) rootView.findViewById(R.id.et_mccysj);
        sn_gsqk = (Spinner) rootView.findViewById(R.id.sn_gsqk);
        sn_zyqk = (Spinner) rootView.findViewById(R.id.sn_zyqk);

        et_mccysj.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
        et_mccysj.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = DatePickerDialog
                        .newInstance();
                datePickerDialog.show(fragmentManager, "datePickerDialog");
                datePickerDialog
                        .setOnDatePickerListener(new OnDatePickerListener() {
                            @Override
                            public void onConfirmClick(long timeInMillis,
                                    String formatDate) {
                                et_mccysj.setText(formatDate);
                                datePickerDialog.hide();
                            }
                        });
            }
        });
    }

    @Override
    public void getData(FollowUpMentalDisease followUpMentalDisease) {
        followUpMentalDisease.setHzdjtshdyx_qdzs(et_qdzs.getText().toString());
        followUpMentalDisease.setHzdjtshdyx_zhaos(et_zs.getText().toString());
        followUpMentalDisease.setHzdjtshdyx_zs(et_zis.getText().toString());
        followUpMentalDisease.setHzdjtshdyx_zsws(et_zsws.getText().toString());
        followUpMentalDisease.setHzdjtshdyx_gsqk(ViewDataUtil
                .getSpinnerData(sn_gsqk));
        followUpMentalDisease.setHzdjtshdyx_mccysj(et_mccysj.getText()
                .toString());
        followUpMentalDisease.setHzdjtshdyx_zyqk(ViewDataUtil
                .getSpinnerData(sn_zyqk));
    }

    @Override
    public void setData(FollowUpMentalDisease followUpMentalDisease) {
        if (followUpMentalDisease != null) {
            et_qdzs.setText(followUpMentalDisease.getHzdjtshdyx_qdzs());
            et_zs.setText(followUpMentalDisease.getHzdjtshdyx_zhaos());
            et_zis.setText(followUpMentalDisease.getHzdjtshdyx_zs());
            et_zsws.setText(followUpMentalDisease.getHzdjtshdyx_zsws());
            et_mccysj.setText(followUpMentalDisease.getHzdjtshdyx_mccysj());
            ViewDataUtil.setSpinnerData(sn_gsqk,
                    followUpMentalDisease.getHzdjtshdyx_gsqk());
            ViewDataUtil.setSpinnerData(sn_zyqk,
                    followUpMentalDisease.getHzdjtshdyx_zyqk());
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void setFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
}
