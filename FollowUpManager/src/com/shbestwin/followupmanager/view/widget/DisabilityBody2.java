package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.Disability;
import com.shbestwin.followupmanager.model.followup.FollowUpDisabledPerson;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class DisabilityBody2 extends LinearLayout implements
        IBaseDisabilityBody {
    private FragmentManager fragmentManager;

    private EditText et_cjzbh, et_cjrq;

    private Spinner sn_zycj, sn_cjcd, sn_cjyy;

    private RelativeLayout disabilitiesLayout;

    private CheckBox disabilitiesNone;

    public DisabilityBody2(Context context) {
        this(context, null);
    }

    public DisabilityBody2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DisabilityBody2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_disability_body2, this, true);
        et_cjzbh = (EditText) rootView.findViewById(R.id.et_cjzbh);
        et_cjrq = (EditText) rootView.findViewById(R.id.et_cjrq);

        et_cjrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
        et_cjrq.setOnClickListener(new OnClickListener() {
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
                                et_cjrq.setText(formatDate);
                                datePickerDialog.hide();
                            }
                        });
            }
        });

        sn_zycj = (Spinner) rootView.findViewById(R.id.sn_zycj);
        sn_cjcd = (Spinner) rootView.findViewById(R.id.sn_cjcd);
        sn_cjyy = (Spinner) rootView.findViewById(R.id.sn_cjyy);

        disabilitiesLayout = (RelativeLayout) rootView
                .findViewById(R.id.disabilitiesLayout);
        disabilitiesNone = (CheckBox) rootView
                .findViewById(R.id.disabilitiesNone);
        disabilitiesNone
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                            boolean isChecked) {
                        setCheckBoxStatus(disabilitiesLayout, isChecked);

                    }
                });
    }

    @Override
    public void getData(FollowUpDisabledPerson followUpDisabledPerson) {
        followUpDisabledPerson.setCjnx_cjzbh(et_cjzbh.getText().toString());
        followUpDisabledPerson.setCjnx_zycj(ViewDataUtil
                .getSpinnerData(sn_zycj));
        followUpDisabledPerson.setCjnx_dccj(getCheckBoxText());
        followUpDisabledPerson.setCjnx_cjrq(et_cjrq.getText().toString());
        followUpDisabledPerson.setCjnx_cjcd(ViewDataUtil
                .getSpinnerData(sn_cjcd));
        followUpDisabledPerson.setCjnx_cjyy(ViewDataUtil
                .getSpinnerData(sn_cjyy));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setData(FollowUpDisabledPerson followUpDisabledPerson) {
        if (followUpDisabledPerson != null) {
            et_cjzbh.setText(followUpDisabledPerson.getCjnx_cjzbh());
            et_cjrq.setText(followUpDisabledPerson.getCjnx_cjrq());
            ViewDataUtil.setSpinnerData(sn_zycj,
                    followUpDisabledPerson.getCjnx_zycj());
            ViewDataUtil.setSpinnerData(sn_cjcd,
                    followUpDisabledPerson.getCjnx_cjcd());
            ViewDataUtil.setSpinnerData(sn_cjyy,
                    followUpDisabledPerson.getCjnx_cjyy());
            try {
                setCheckBoxText(disabilitiesLayout,
                        JsonUtil.jsonToObjects(
                                followUpDisabledPerson.getCjnx_dccj(),
                                Disability.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    private void setCheckBoxText(View layout, List<Disability> mList) {
        RelativeLayout myLayout = (RelativeLayout) layout;
        for (int i = 0; i < mList.size(); i++) {
            int Num = Integer.valueOf(mList.get(i).getCjnx_dccj_num());
            if ((View) (myLayout).getChildAt(Num) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) (View) (myLayout)
                        .getChildAt(Num);
                if (Num == 1) {
                    setCheckBoxStatus(disabilitiesLayout, false);
                    return;
                } else {
                    checkBox.setChecked(true);
                }
            }
        }
    }

    private String getCheckBoxText() {
        List<Disability> allergyHistoriesList = new ArrayList<Disability>();
        for (int i = 0; i < disabilitiesLayout.getChildCount(); i++) {
            View item = disabilitiesLayout.getChildAt(i);
            if (item instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) item;
                if (checkBox.isChecked()) {
                    Disability entity = new Disability();
                    entity.setCjnx_dccj_num(String.valueOf(i));
                    entity.setCjnx_dccj_name(checkBox.getText().toString());
                    allergyHistoriesList.add(entity);
                }
            }
        }
        try {
            return JsonUtil.objectsToJson(allergyHistoriesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void setFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private void setCheckBoxStatus(RelativeLayout familyHistory,
            boolean isChecked) {
        for (int i = 2; i < familyHistory.getChildCount(); i++) {
            View item = familyHistory.getChildAt(i);
            if (item instanceof CheckBox) {
                ((CheckBox) item).setEnabled(!isChecked);
                if (isChecked) {
                    ((CheckBox) item).setChecked(!isChecked);
                }
            }
        }
    }

}
