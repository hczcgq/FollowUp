package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpStroke;

public class CerebralApoplexyBody2 extends LinearLayout implements
        IBaseCerebralApoplexyBody {
    private RadioGroup rg_smoking, rg_drinking;

    private Spinner sn_ys;

    private EditText et_bhj, et_pj, et_yd, et_yd_sj, et_kfdl, et_kfdl_sj;

    private RadioButton rb_yj_w, rb_yj_y, rb_xy_w, rb_xy_y;

    private boolean isSmoking = false, isDrinking = false;

    public CerebralApoplexyBody2(Context context) {
        this(context, null);
    }

    public CerebralApoplexyBody2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CerebralApoplexyBody2(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_cerebral_apoplexy_body2, this, true);
        rg_smoking = (RadioGroup) rootView.findViewById(R.id.rg_smoking);
        rg_drinking = (RadioGroup) rootView.findViewById(R.id.rg_drinking);
        sn_ys = (Spinner) rootView.findViewById(R.id.sn_ys);
        et_bhj = (EditText) rootView.findViewById(R.id.et_bhj);
        et_pj = (EditText) rootView.findViewById(R.id.et_pj);
        et_yd = (EditText) rootView.findViewById(R.id.et_yd);
        et_yd_sj = (EditText) rootView.findViewById(R.id.et_yd_sj);
        et_kfdl = (EditText) rootView.findViewById(R.id.et_kfdl);
        et_kfdl_sj = (EditText) rootView.findViewById(R.id.et_kfdl_sj);

        rb_xy_w = (RadioButton) rootView.findViewById(R.id.rb_xy_w);
        rb_xy_y = (RadioButton) rootView.findViewById(R.id.rb_xy_y);
        rb_yj_w = (RadioButton) rootView.findViewById(R.id.rb_yj_w);
        rb_yj_y = (RadioButton) rootView.findViewById(R.id.rb_yj_y);

        rg_drinking.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_yj_w) {
                    isDrinking = false;
                } else if (checkedId == R.id.rb_yj_y) {
                    isDrinking = true;
                }
            }
        });
        rg_smoking.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_xy_w) {
                    isSmoking = false;
                } else if (checkedId == R.id.rb_xy_y) {
                    isSmoking = true;
                }
            }
        });
    }

    @Override
    public void getData(FollowUpStroke followUpStroke) {

        followUpStroke.setShxggb_sfxy(isSmoking);
        followUpStroke.setShxggb_sfxyms(ViewDataUtil.getSpinnerData(sn_ys));
        followUpStroke.setShxggb_sfyj(isDrinking);
        followUpStroke.setShxggb_sfyjms(et_bhj.getText().toString() + "/"
                + et_pj.getText().toString());
        followUpStroke.setShxggb_ydmc(et_yd.getText().toString());
        followUpStroke.setShxggb_ydsj(et_yd_sj.getText().toString());
        followUpStroke.setShxggb_kfdlmc(et_kfdl.getText().toString());
        followUpStroke.setShxggb_kfdlsj(et_kfdl_sj.getText().toString());
    }

    @Override
    public void setData(FollowUpStroke followUpStroke) {
        if (followUpStroke != null) {
            ViewDataUtil.setSpinnerData(sn_ys,
                    followUpStroke.getShxggb_sfxyms());
            String drinking = followUpStroke.getShxggb_sfyjms();
            if (drinking.split("/").length == 1) {
                et_bhj.setText(drinking.split("/")[0]);
            } else if (drinking.split("/").length == 2) {
                et_bhj.setText(drinking.split("/")[0]);
                et_pj.setText(drinking.split("/")[1]);
            }

            et_yd.setText(followUpStroke.getShxggb_ydmc());
            et_yd_sj.setText(followUpStroke.getShxggb_ydsj());
            et_kfdl.setText(followUpStroke.getShxggb_kfdlmc());
            et_kfdl_sj.setText(followUpStroke.getShxggb_kfdlsj());

            if (followUpStroke.getShxggb_sfxy()) {
                rb_xy_y.setChecked(true);
                rb_xy_w.setChecked(false);
            } else {
                rb_xy_y.setChecked(false);
                rb_xy_w.setChecked(true);
            }
            if (followUpStroke.getShxggb_sfyj()) {
                rb_yj_y.setChecked(true);
                rb_yj_w.setChecked(false);
            } else {
                rb_yj_y.setChecked(false);
                rb_yj_w.setChecked(true);
            }
        }
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void setFragment(FragmentManager fragmentManager) {
        // TODO Auto-generated method stub

    }
}
