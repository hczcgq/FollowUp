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
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class YearsOld0Body1 extends LinearLayout implements IBaseYearsOld0Body {
	
	private EditText et_fsrq,et_zrys;
	private Spinner sn_yn;
	private FragmentManager fragmentManager;
	
	public YearsOld0Body1(Context context) {
		this(context, null);
	}

	public YearsOld0Body1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_0_body1, this, true);
		et_fsrq=(EditText) rootView.findViewById(R.id.et_fsrq);
		et_zrys=(EditText) rootView.findViewById(R.id.et_zrys);
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
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		followUpOneNewborn.setGrxx_fsrq(et_fsrq.getText().toString());
		followUpOneNewborn.setGrxx_yl(ViewDataUtil.getSpinnerData(sn_yn));
		followUpOneNewborn.setGrxx_zrys(et_zrys.getText().toString());
	}

	@Override
	public void setData(FollowUpOneNewborn followUpOneNewborn) {
		if(followUpOneNewborn!=null) {
		    et_fsrq.setText(followUpOneNewborn.getGrxx_fsrq());
		    et_zrys.setText(followUpOneNewborn.getGrxx_zrys());
		    ViewDataUtil.setSpinnerData(sn_yn, followUpOneNewborn.getGrxx_yl());
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
