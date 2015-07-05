package com.shbestwin.followupmanager.view.widget;

import java.util.Date;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class YearsOld1_2Body15 extends LinearLayout  implements IBaseYearsOld1_2Body{
	private EditText et_yzjjy,et_xcsfrq,et_sfysqm;
	private FragmentManager fragmentManager;
	private RelativeLayout guideLabelLayout;
	public YearsOld1_2Body15(Context context) {
		this(context, null);
	}

	public YearsOld1_2Body15(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld1_2Body15(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_1_2_body15, this, true);
		et_yzjjy=(EditText) rootView.findViewById(R.id.et_yzjjy);
		et_xcsfrq=(EditText) rootView.findViewById(R.id.et_xcsfrq);
		et_sfysqm=(EditText) rootView.findViewById(R.id.et_sfysqm);
		guideLabelLayout = (RelativeLayout) rootView
				.findViewById(R.id.guideLabelLayout);
		et_xcsfrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_xcsfrq.setOnClickListener(new OnClickListener() {
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
								et_xcsfrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		

		
	}
	@Override
	public void getData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		
		
		followUpOneTwoNewborn.setZd_yzhjy(et_yzjjy.getText().toString());
		followUpOneTwoNewborn.setZd_xcsfrq(et_xcsfrq.getText().toString());
		followUpOneTwoNewborn.setZd_sfysqm(et_sfysqm.getText().toString());
		followUpOneTwoNewborn.setZd_zd(ViewDataUtil.getCheckboxData(
				guideLabelLayout, null));
		
	}

	@Override
	public void setData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		if (followUpOneTwoNewborn != null) {
			et_sfysqm.setText(followUpOneTwoNewborn.getZd_sfysqm());
			et_xcsfrq.setText(followUpOneTwoNewborn.getZd_xcsfrq());
			et_yzjjy.setText(followUpOneTwoNewborn.getZd_yzhjy());
			ViewDataUtil.setCheckboxData(guideLabelLayout, null,
					followUpOneTwoNewborn.getZd_zd());
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
