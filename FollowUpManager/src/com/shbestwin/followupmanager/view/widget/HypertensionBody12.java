package com.shbestwin.followupmanager.view.widget;

import java.util.Date;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class HypertensionBody12 extends LinearLayout implements
		IBaseHypertensionBody {
	private FragmentManager fragmentManager;
	private EditText et_rq, et_qtjb, et_xcsfrq, et_sfysqm;
	private RelativeLayout bqgRelativeLayout,hbzRelativeLayout;

	public HypertensionBody12(Context context) {
		this(context, null);
	}

	public HypertensionBody12(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody12(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_hypertension_body12, this, true);
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
		
		bqgRelativeLayout= (RelativeLayout) rootView.findViewById(R.id.bqgRelativeLayout);
		hbzRelativeLayout= (RelativeLayout) rootView.findViewById(R.id.hbzRelativeLayout);
	}

	@Override
	public void getData(FollowUpHypertension followUpHypertension) {
		
		followUpHypertension.setZzhf_rq(et_rq.getText().toString());
		followUpHypertension.setZzhf_qtjb(et_qtjb.getText().toString());
		followUpHypertension.setZzhf_xcsfrq(et_xcsfrq.getText().toString());
		followUpHypertension.setZzhf_sfysqm(et_sfysqm.getText().toString());
		
		followUpHypertension.setZzhf_hbz(ViewDataUtil.getCheckboxData(hbzRelativeLayout,
				null));
		followUpHypertension.setZzhf_bqgsh(ViewDataUtil.getCheckboxData(bqgRelativeLayout,
				null));
	}

	@Override
	public void setData(FollowUpHypertension followUpHypertension) {
	    if(followUpHypertension!=null) {
	        et_rq.setText(followUpHypertension.getZzhf_rq());
	        et_qtjb.setText(followUpHypertension.getZzhf_qtjb());
	        et_xcsfrq.setText(followUpHypertension.getZzhf_xcsfrq());
	        et_sfysqm.setText(followUpHypertension.getZzhf_sfysqm());
	        ViewDataUtil.setCheckboxData(hbzRelativeLayout, null, followUpHypertension.getZzhf_hbz());
	        ViewDataUtil.setCheckboxData(bqgRelativeLayout, null, followUpHypertension.getZzhf_bqgsh());
	        
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
