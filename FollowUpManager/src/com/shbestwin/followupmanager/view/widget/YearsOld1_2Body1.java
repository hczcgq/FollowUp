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
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class YearsOld1_2Body1 extends LinearLayout implements IBaseYearsOld1_2Body{
	private EditText et_fsrq,et_zrys,et_csrl;
	private Spinner sn_yn;
	private FragmentManager fragmentManager;
	public YearsOld1_2Body1(Context context) {
		this(context, null);
	}

	public YearsOld1_2Body1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld1_2Body1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_1_2_body1, this, true);
		et_fsrq=(EditText) rootView.findViewById(R.id.et_fsrq);
		et_zrys=(EditText) rootView.findViewById(R.id.et_zrys);
		et_csrl=(EditText) rootView.findViewById(R.id.et_csrl);
		sn_yn=(Spinner) rootView.findViewById(R.id.sn_yn);
		
		et_fsrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_fsrq.setOnClickListener(new OnClickListener() {
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
								et_fsrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
	}

	@Override
	public void getData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		
		followUpOneTwoNewborn.setGrxx_csrl(et_csrl.getText().toString());
		followUpOneTwoNewborn.setGrxx_fsrq(et_fsrq.getText().toString());
		followUpOneTwoNewborn.setGrxx_zrys(et_zrys.getText().toString());
		followUpOneTwoNewborn.setGrxx_ynl(ViewDataUtil.getSpinnerData(sn_yn));
		
	}

	@Override
	public void setData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		if(followUpOneTwoNewborn!=null){
			et_csrl.setText(followUpOneTwoNewborn.getGrxx_csrl());
			et_fsrq.setText(followUpOneTwoNewborn.getGrxx_fsrq());
			et_zrys.setText(followUpOneTwoNewborn.getGrxx_zrys());
			ViewDataUtil.setSpinnerData(sn_yn, followUpOneTwoNewborn.getGrxx_ynl());
		}
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
	this.fragmentManager=fragmentManager;
	}

}
