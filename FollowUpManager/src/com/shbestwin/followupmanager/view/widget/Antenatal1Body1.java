package com.shbestwin.followupmanager.view.widget;

import java.util.Date;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class Antenatal1Body1 extends LinearLayout implements IBaseAntenatal1Body {
	private EditText et_tbrq,et_yz,et_yc,et_ydfmcs,et_pgccs,et_txrq,et_ycq;
	private RadioGroup rg_mcyj;
	private FragmentManager fragmentManager;
	private boolean isMoci=false;
	private RadioButton rb_bx,rb_txrq;
	public Antenatal1Body1(Context context) {
		this(context, null);
	}

	public Antenatal1Body1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body1, this, true);
		et_tbrq=(EditText) rootView.findViewById(R.id.et_tbrq);
		et_yz=(EditText) rootView.findViewById(R.id.et_yz);
		et_yc=(EditText) rootView.findViewById(R.id.et_yc);
		et_ydfmcs=(EditText) rootView.findViewById(R.id.et_ydfmcs);
		et_pgccs=(EditText) rootView.findViewById(R.id.et_pgccs);
		et_txrq=(EditText) rootView.findViewById(R.id.et_txrq);
		et_ycq=(EditText) rootView.findViewById(R.id.et_ycq);
		rg_mcyj=(RadioGroup) rootView.findViewById(R.id.rg_mcyj);
		rb_bx=(RadioButton) rootView.findViewById(R.id.rb_bx);
		rb_txrq=(RadioButton) rootView.findViewById(R.id.rb_txrq);
		
		et_tbrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_tbrq.setOnClickListener(new OnClickListener() {
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
								et_tbrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		
		et_txrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_txrq.setOnClickListener(new OnClickListener() {
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
								et_txrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		
		et_ycq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_ycq.setOnClickListener(new OnClickListener() {
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
								et_ycq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		
		rg_mcyj.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_bx){
					isMoci=false;
				}else if(checkedId==R.id.rb_txrq){
					isMoci=true;
				}
			}
		});
	}

	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {

		followUpFirstPregnancy.setGrxx_tbrq(et_tbrq.getText().toString());
		followUpFirstPregnancy.setGrxx_yz(et_yz.getText().toString());
		followUpFirstPregnancy.setGrxx_yc(et_yc.getText().toString());
		followUpFirstPregnancy.setGrxx_ydfmcs(et_ydfmcs.getText().toString());
		followUpFirstPregnancy.setGrxx_pgccs(et_pgccs.getText().toString());
		followUpFirstPregnancy.setGrxx_sfmcyj(isMoci);
		followUpFirstPregnancy.setGrxx_sfmcyjms(et_txrq.getText().toString());
		followUpFirstPregnancy.setGrxx_ycq(et_ycq.getText().toString());
	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {
	    if(followUpFirstPregnancy!=null) {
	        et_tbrq.setText(followUpFirstPregnancy.getGrxx_tbrq());
	        et_yz.setText(followUpFirstPregnancy.getGrxx_yz());
	        et_yc.setText(followUpFirstPregnancy.getGrxx_yc());
	        et_ydfmcs.setText(followUpFirstPregnancy.getGrxx_ydfmcs());
	        et_pgccs.setText(followUpFirstPregnancy.getGrxx_pgccs());
	        et_txrq.setText(followUpFirstPregnancy.getGrxx_sfmcyjms());
	        et_yc.setText(followUpFirstPregnancy.getGrxx_yc());
	        if(followUpFirstPregnancy.getGrxx_sfmcyj()) {
	            rb_bx.setChecked(false);
	            rb_txrq.setChecked(true);
	        }else {
	            rb_bx.setChecked(true);
                rb_txrq.setChecked(false);
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
		this.fragmentManager=fragmentManager;
	}

}
