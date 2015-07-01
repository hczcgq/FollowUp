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
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class DiabetesMellitusBody13 extends LinearLayout implements
		IBaseDiabetesMellitusBody {
	private FragmentManager fragmentManager;
	private EditText et_rq, et_qtjb, et_xcsfrq, et_sfysqm;
	private RelativeLayout bfzRelativeLayout,hbzRelativeLayout;

	public DiabetesMellitusBody13(Context context) {
		this(context, null);
	}

	public DiabetesMellitusBody13(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DiabetesMellitusBody13(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_diabetes_mellitus_body13, this, true);
		et_rq = (EditText) rootView.findViewById(R.id.et_rq);
		et_qtjb = (EditText) rootView.findViewById(R.id.et_qtjb);
		et_xcsfrq = (EditText) rootView.findViewById(R.id.et_xcsfrq);
		et_sfysqm = (EditText) rootView.findViewById(R.id.et_sfysqm);

		et_rq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_rq.setOnClickListener(new OnClickListener() {
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
								et_rq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
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
		
		bfzRelativeLayout= (RelativeLayout) rootView.findViewById(R.id.bfzRelativeLayout);
		hbzRelativeLayout= (RelativeLayout) rootView.findViewById(R.id.hbzRelativeLayout);
		



	}

	@Override
	public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {

		followUpDiabetesMellitus.setZzhf_rq(et_rq.getText().toString());
		followUpDiabetesMellitus.setZzhf_qtjb(et_qtjb.getText().toString());
		followUpDiabetesMellitus.setZzhf_xcsfrq(et_xcsfrq.getText().toString());
		followUpDiabetesMellitus.setZzhf_sfysqm(et_sfysqm.getText().toString());
		followUpDiabetesMellitus.setZzhf_hbz(ViewDataUtil.getCheckboxData(hbzRelativeLayout,
				null));
		followUpDiabetesMellitus.setZzhf_bqgsh(ViewDataUtil.getCheckboxData(bfzRelativeLayout,
				null));
	}

	@Override
	public void setData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		// complicationO Auto-generated method stub

	}

	@Override
	public boolean validate() {
		// complicationO Auto-generated method stub
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;

	}

}
