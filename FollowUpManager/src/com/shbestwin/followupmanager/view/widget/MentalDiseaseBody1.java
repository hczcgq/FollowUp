package com.shbestwin.followupmanager.view.widget;

import java.util.Date;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class MentalDiseaseBody1 extends LinearLayout implements
        IBaseMentalDiseaseBody {
    private EditText et_zrys, et_sfrq;

    private FragmentManager fragment;

    public MentalDiseaseBody1(Context context) {
        this(context, null);
    }

    public MentalDiseaseBody1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MentalDiseaseBody1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_mental_disease_body1, this, true);
        et_zrys = (EditText) rootView.findViewById(R.id.et_zrys);
        et_sfrq = (EditText) rootView.findViewById(R.id.et_sfrq);
        et_sfrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
        et_sfrq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragment, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_sfrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
    }

    @Override
    public void getData(FollowUpMentalDisease followUpMentalDisease) {
        followUpMentalDisease.setGrxx_zrys(et_zrys.getText().toString());
        followUpMentalDisease.setGrxx_sfrq(et_sfrq.getText().toString());
    }

    @Override
    public void setData(FollowUpMentalDisease followUpMentalDisease) {
        if (followUpMentalDisease != null) {
            et_zrys.setText(followUpMentalDisease.getGrxx_zrys());
            et_sfrq.setText(followUpMentalDisease.getGrxx_sfrq());
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void setFragment(FragmentManager fragment) {
    	this.fragment = fragment;
    }

}
