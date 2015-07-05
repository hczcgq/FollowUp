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
import android.widget.RelativeLayout;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class NeonateBody7 extends LinearLayout  implements IBaseNeonateBody{
	
	private EditText et_tsyzjcl,et_xcsfrq,et_xcsfdd,et_sfysqm;
	private FragmentManager fragmentManager;
	private RelativeLayout guideLabelLayout;
	public NeonateBody7(Context context) {
		this(context, null);
	}

	public NeonateBody7(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NeonateBody7(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_neonate_body7, this, true);
		et_tsyzjcl=(EditText) rootView.findViewById(R.id.et_tsyzjcl);
		et_xcsfrq=(EditText) rootView.findViewById(R.id.et_xcsfrq);
		et_xcsfdd=(EditText) rootView.findViewById(R.id.et_tsyzjcl);
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
	public void getData(FollowUpNewborn followUpNewborn) {
		
		followUpNewborn.setZd_xcsfrq(et_xcsfrq.getText().toString());
		followUpNewborn.setZd_xcfsdd(et_xcsfdd.getText().toString());
		followUpNewborn.setZd_sfysqm(et_sfysqm.getText().toString());
		followUpNewborn.setZd_tsyzjcl(et_tsyzjcl.getText().toString());
		followUpNewborn.setZd_zdmc(ViewDataUtil.getCheckboxData(
				guideLabelLayout, null));
	}

	@Override
	public void setData(FollowUpNewborn followUpNewborn) {
		if (followUpNewborn != null) {
			et_tsyzjcl.setText(followUpNewborn.getZd_tsyzjcl());
			et_sfysqm.setText(followUpNewborn.getZd_sfysqm());
			et_xcsfrq.setText(followUpNewborn.getZd_xcsfrq());
			et_xcsfdd.setText(followUpNewborn.getZd_xcfsdd());
			ViewDataUtil.setCheckboxData(guideLabelLayout, null,
					followUpNewborn.getZd_zdmc());
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
