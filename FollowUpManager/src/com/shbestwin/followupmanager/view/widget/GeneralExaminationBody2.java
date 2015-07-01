package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody2 extends LinearLayout implements IBaseGeneralExaminationBody{
	private EditText et_tw,et_mb,et_hxpl,et_zcxy_1,et_zcxy_2,et_sg,et_bmi,et_ycxy_1,et_ycxy_2,et_tz,et_yw,et_lnrrzgn_zf,et_lnrqgzt_zf;
	private Spinner sn_lnrjkzkzwpg,sn_lnrshzlnl;
	private RadioGroup rg_lnrrzgn,rg_lnrqgzt;
	private String name_lnrrzgn="粗筛阴性",name_lnrqgzt="粗筛阴性";
	public GeneralExaminationBody2(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_general_examination_body2, this, true);
		et_tw=(EditText) rootView.findViewById(R.id.et_tw);
		et_mb=(EditText) rootView.findViewById(R.id.et_mb);
		et_hxpl=(EditText) rootView.findViewById(R.id.et_hxpl);
		et_zcxy_1=(EditText) rootView.findViewById(R.id.et_zcxy_1);
		et_zcxy_2=(EditText) rootView.findViewById(R.id.et_zcxy_2);
		et_sg=(EditText) rootView.findViewById(R.id.et_sg);
		et_bmi=(EditText) rootView.findViewById(R.id.et_bmi);
		et_ycxy_1=(EditText) rootView.findViewById(R.id.et_ycxy_1);
		et_ycxy_2=(EditText) rootView.findViewById(R.id.et_ycxy_2);
		et_tz=(EditText) rootView.findViewById(R.id.et_tz);
		et_yw=(EditText) rootView.findViewById(R.id.et_yw);
		et_lnrrzgn_zf=(EditText) rootView.findViewById(R.id.et_lnrrzgn_zf);
		et_lnrqgzt_zf=(EditText) rootView.findViewById(R.id.et_lnrqgzt_zf);
		
		sn_lnrjkzkzwpg=(Spinner) rootView.findViewById(R.id.sn_lnrjkzkzwpg);
		sn_lnrshzlnl=(Spinner) rootView.findViewById(R.id.sn_lnrshzlnl);
		
		rg_lnrrzgn=(RadioGroup) rootView.findViewById(R.id.rg_lnrrzgn);
		rg_lnrqgzt=(RadioGroup) rootView.findViewById(R.id.rg_lnrqgzt);
		
		rg_lnrrzgn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_lnrrzgn_csyinx){
					name_lnrrzgn="粗筛阴性";
				}else {
					name_lnrrzgn="粗筛阳性";
				}
			}
		});
		rg_lnrqgzt.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_lnrqgzt_csyinx){
					name_lnrqgzt="粗筛阴性";
				}else {
					name_lnrqgzt="粗筛阳性";
				}
			}
		});
	}

	@Override
	public void getData(GeneralExamination generalExamination) {
		generalExamination.setYbqk_tw(et_tw.getText().toString());
		generalExamination.setYbqk_mb(et_mb.getText().toString());
		generalExamination.setYbqk_hxpl(et_hxpl.getText().toString());
		generalExamination.setYbqk_zcxy(et_zcxy_1.getText().toString()+"/"+et_zcxy_2.getText().toString());
		generalExamination.setYbqk_sg(et_sg.getText().toString());
		generalExamination.setYbqk_bmi(et_bmi.getText().toString());
		generalExamination.setYbqk_ycxy(et_ycxy_1.getText().toString()+"/"+et_ycxy_2.getText().toString());
		generalExamination.setYbqk_tz(et_tz.getText().toString());
		generalExamination.setYbqk_yw(et_yw.getText().toString());
		generalExamination.setYbqk_jkzk(ViewDataUtil.getSpinnerData(sn_lnrjkzkzwpg));
		generalExamination.setYbqk_shzlnl(ViewDataUtil.getSpinnerData(sn_lnrshzlnl));
		generalExamination.setYbqk_rzgn(name_lnrrzgn);
		generalExamination.setYbqk_zljczf(et_lnrrzgn_zf.getText().toString());
		generalExamination.setYbqk_qgzt(name_lnrqgzt);
		generalExamination.setYbqk_yyztjczf(et_lnrqgzt_zf.getText().toString());
	}

	@Override
	public void setData(GeneralExamination generalExamination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate() {
		return true;
	}
}
