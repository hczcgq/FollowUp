package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body9 extends LinearLayout implements IBaseYearsOld0Body {
    private RadioGroup rg_fypg;

    private boolean isHas = true;

    private RadioButton rb_tg, rb_wtg;

    public YearsOld0Body9(Context context) {
        this(context, null);
    }

    public YearsOld0Body9(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YearsOld0Body9(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_years_old_0_body9, this, true);
        rg_fypg = (RadioGroup) rootView.findViewById(R.id.rg_fypg);
        rb_tg = (RadioButton) rootView.findViewById(R.id.rb_tg);
        rb_wtg = (RadioButton) rootView.findViewById(R.id.rg_wtg);

        rg_fypg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_tg) {
                    isHas = true;
                } else if (checkedId == R.id.rg_wtg) {
                    isHas = false;
                }
            }
        });
    }

    @Override
    public void getData(FollowUpOneNewborn followUpOneNewborn) {
        followUpOneNewborn.setFypg_sftg(isHas);
    }

    @Override
    public void setData(FollowUpOneNewborn followUpOneNewborn) {
        if (followUpOneNewborn != null) {
            if (followUpOneNewborn.getFypg_sftg()) {
                rb_tg.setChecked(true);
                rb_wtg.setChecked(false);
            } else {
                rb_tg.setChecked(true);
                rb_wtg.setChecked(true);
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
