package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody12 extends LinearLayout implements IBaseGeneralExaminationBody{
	private RadioGroup rg_jpkg;
	private RadioButton rb_jpkg_tjwyc,rb_jpkg_yyc;
	private EditText et_yc_1,et_yc_2,et_yc_3;
	private boolean isYc=false;
	public GeneralExaminationBody12(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody12(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody12(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_general_examination_body12, this, true);
		
		et_yc_1=(EditText) rootView.findViewById(R.id.et_yc_1);
		et_yc_2=(EditText) rootView.findViewById(R.id.et_yc_2);
		et_yc_3=(EditText) rootView.findViewById(R.id.et_yc_3);
		rg_jpkg=(RadioGroup) rootView.findViewById(R.id.rg_jpkg);
		rb_jpkg_tjwyc=(RadioButton) rootView.findViewById(R.id.rb_jpkg_tjwyc);
		rb_jpkg_yyc=(RadioButton) rootView.findViewById(R.id.rb_jpkg_yyc);
		et_yc_1.setEnabled(false);
		et_yc_2.setEnabled(false);
		et_yc_3.setEnabled(false);
		rg_jpkg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_jpkg_tjwyc){
					isYc=false;
					et_yc_1.setEnabled(false);
					et_yc_2.setEnabled(false);
					et_yc_3.setEnabled(false);
				}else {
					isYc=true;
					et_yc_1.setEnabled(true);
					et_yc_2.setEnabled(true);
					et_yc_3.setEnabled(true);
				}
			}
		});
		
	}

	@Override
	public void getData(GeneralExamination generalExamination) {
		generalExamination.setJkpj_sftjyc(isYc);
		generalExamination.setJkpj_yc1(et_yc_1.getText().toString());
		generalExamination.setJkpj_yc2(et_yc_2.getText().toString());
		generalExamination.setJkpj_yc3(et_yc_3.getText().toString());
	}

	@Override
	public void setData(GeneralExamination generalExamination) {
		if(generalExamination!=null) {
		    et_yc_1.setText(generalExamination.getJkpj_yc1());
		    et_yc_2.setText(generalExamination.getJkpj_yc2());
		    et_yc_3.setText(generalExamination.getJkpj_yc3());
		    
		    if(generalExamination.getJkpj_sftjyc()) {
		        rb_jpkg_tjwyc.setChecked(false);
		        rb_jpkg_yyc.setChecked(true);
		    }else {
		        rb_jpkg_tjwyc.setChecked(true);
                rb_jpkg_yyc.setChecked(false);
            }
		}
	}

	@Override
	public boolean validate() {
		return true;
	}
}
