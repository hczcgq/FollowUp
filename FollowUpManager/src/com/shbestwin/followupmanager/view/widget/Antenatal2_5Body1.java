package com.shbestwin.followupmanager.view.widget;

import java.util.Date;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.model.followup.FollowUpTwoToFivePregnancy;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class Antenatal2_5Body1 extends LinearLayout implements IBaseAntenatal2_5Body {
	private EditText et_fsrq,et_yz,et_cqfscs,et_zs;
private FragmentManager fragmentManager;
	public Antenatal2_5Body1(Context context) {
		this(context, null);
	}

	public Antenatal2_5Body1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal2_5Body1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal2_5_body1, this, true);
		et_fsrq=(EditText) rootView.findViewById(R.id.et_fsrq);
		et_yz=(EditText) rootView.findViewById(R.id.et_yz);
		et_cqfscs=(EditText) rootView.findViewById(R.id.et_cqfscs);
		et_zs=(EditText) rootView.findViewById(R.id.et_zs);
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
	public void getData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
		followUpTwoToFivePregnancy.setGrxx_fsrq(et_fsrq.getText().toString());
		followUpTwoToFivePregnancy.setGrxx_yz(et_yz.getText().toString());
		followUpTwoToFivePregnancy.setGrxx_cqfscs(et_cqfscs.getText().toString());
		followUpTwoToFivePregnancy.setGrxx_zs(et_zs.getText().toString());
	}

	@Override
	public void setData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
		if(followUpTwoToFivePregnancy!=null){
			et_fsrq.setText(followUpTwoToFivePregnancy.getGrxx_fsrq());
			et_yz.setText(followUpTwoToFivePregnancy.getGrxx_yz());
			et_cqfscs.setText(followUpTwoToFivePregnancy.getGrxx_cqfscs());
			et_zs.setText(followUpTwoToFivePregnancy.getGrxx_zs());
		}
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
	this.fragmentManager=fragmentManager;
	}

}
