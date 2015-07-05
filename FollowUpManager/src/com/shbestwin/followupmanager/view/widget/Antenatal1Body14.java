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
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class Antenatal1Body14 extends LinearLayout  implements IBaseAntenatal1Body {
	private FragmentManager fragmentManager;
	private RadioGroup rg_zz;
	private EditText et_yy,et_jgjks,et_xcsfrq,et_sfysqm;
	private RadioButton rb_w,rb_y;
	private boolean isHas=false;
	public Antenatal1Body14(Context context) {
		this(context, null);
	}

	public Antenatal1Body14(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body14(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body14, this, true);
		rg_zz=(RadioGroup) rootView.findViewById(R.id.rg_zz);
		et_yy=(EditText) rootView.findViewById(R.id.et_yy);
		et_jgjks=(EditText) rootView.findViewById(R.id.et_jgjks);
		et_xcsfrq=(EditText) rootView.findViewById(R.id.et_xcsfrq);
		et_sfysqm=(EditText) rootView.findViewById(R.id.et_sfysqm);
		rb_w=(RadioButton) rootView.findViewById(R.id.rb_w);
		rb_y=(RadioButton) rootView.findViewById(R.id.rb_y);
		
		rg_zz.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_w){
					isHas=false;
				}else if(checkedId==R.id.rb_y){
					isHas=true;
				}
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
	}
	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		// TODO Auto-generated method stub
		followUpFirstPregnancy.setZz_sfygzz(isHas);
		followUpFirstPregnancy.setZz_yy(et_yy.getText().toString());
		followUpFirstPregnancy.setZz_jgjks(et_jgjks.getText().toString());
		followUpFirstPregnancy.setZz_xcsfrq(et_xcsfrq.getText().toString());
		followUpFirstPregnancy.setZtpg_sfysqm(et_sfysqm.getText().toString());

	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		if(followUpFirstPregnancy!=null){
			et_jgjks.setText(followUpFirstPregnancy.getZz_jgjks());
			et_yy.setText(followUpFirstPregnancy.getZz_yy());
			et_xcsfrq.setText(followUpFirstPregnancy.getZz_xcsfrq());
			et_sfysqm.setText(followUpFirstPregnancy.getZtpg_sfysqm());
			
			if(followUpFirstPregnancy.getZz_sfygzz()){
				rb_y.setChecked(true);
				rb_w.setChecked(false);
			}else {
				rb_y.setChecked(false);
				rb_w.setChecked(true);
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
		// TODO Auto-generated method stub
		this.fragmentManager=fragmentManager;
	}
}
