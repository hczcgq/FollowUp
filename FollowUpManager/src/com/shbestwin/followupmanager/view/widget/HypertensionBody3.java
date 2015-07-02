package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;

public class HypertensionBody3 extends LinearLayout implements
        IBaseHypertensionBody {

    private EditText et_xy_ssy, et_xy_szy, et_xl, et_tz_dqz, et_tz_mbz, et_sg,
            et_tzzs_dqz, et_tzzs_mbz, et_qt;

    public HypertensionBody3(Context context) {
        this(context, null);
    }

    public HypertensionBody3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HypertensionBody3(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_hypertension_body3, this, true);
        et_xy_ssy = (EditText) rootView.findViewById(R.id.et_xy_ssy);
        et_xy_szy = (EditText) rootView.findViewById(R.id.et_xy_szy);
        et_xl = (EditText) rootView.findViewById(R.id.et_xl);
        et_tz_dqz = (EditText) rootView.findViewById(R.id.et_tz_dqz);
        et_tz_mbz = (EditText) rootView.findViewById(R.id.et_tz_mbz);
        et_sg = (EditText) rootView.findViewById(R.id.et_sg);
        et_tzzs_dqz = (EditText) rootView.findViewById(R.id.et_tzzs_dqz);
        et_tzzs_mbz = (EditText) rootView.findViewById(R.id.et_tzzs_mbz);
        et_qt = (EditText) rootView.findViewById(R.id.et_qt);
    }

    @Override
    public void getData(FollowUpHypertension followUpHypertension) {
        followUpHypertension.setTz_xy(et_xy_ssy.getText().toString() + "/"
                + et_xy_szy.getText().toString());
        followUpHypertension.setTz_xl(et_xl.getText().toString());
        followUpHypertension.setTz_tz(et_tz_dqz.getText().toString() + "/"
                + et_tz_mbz.getText().toString());
        followUpHypertension.setTz_sg(et_sg.getText().toString());
        followUpHypertension.setTz_tzzs(et_tzzs_dqz.getText().toString() + "/"
                + et_tzzs_mbz.getText().toString());
        followUpHypertension.setTz_qt(et_qt.getText().toString());
    }

    @Override
    public void setData(FollowUpHypertension followUpHypertension) {
        if (followUpHypertension != null) {
            String xy = followUpHypertension.getTz_xy();
            if (xy.split("/").length == 1) {
                et_xy_ssy.setText(xy.split("/")[0]);
            } else if (xy.split("/").length == 2) {
                et_xy_ssy.setText(xy.split("/")[0]);
                et_xy_szy.setText(xy.split("/")[1]);
            }
            et_xl.setText(followUpHypertension.getTz_xl());
            String tz = followUpHypertension.getTz_tz();
            if (tz.split("/").length == 1) {
                et_tz_dqz.setText(tz.split("/")[0]);
            } else if (tz.split("/").length == 2) {
                et_tz_dqz.setText(tz.split("/")[0]);
                et_tz_mbz.setText(tz.split("/")[1]);
            }
            et_sg.setText(followUpHypertension.getTz_sg());
            String tzzs = followUpHypertension.getTz_tzzs();
            if (tzzs.split("/").length == 1) {
                et_tzzs_dqz.setText(tzzs.split("/")[0]);
            } else if (tzzs.split("/").length == 2) {
                et_tzzs_dqz.setText(tzzs.split("/")[0]);
                et_tzzs_mbz.setText(tzzs.split("/")[1]);
            }
            et_qt.setText(followUpHypertension.getTz_qt());
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void setFragment(FragmentManager fragmentManager) {
    }
}
