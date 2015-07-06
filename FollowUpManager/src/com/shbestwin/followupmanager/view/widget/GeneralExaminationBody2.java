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
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody2 extends LinearLayout implements IBaseGeneralExaminationBody{
	private EditText et_tw,et_mb,et_hxpl,et_zcxy_1,et_zcxy_2,et_sg,et_bmi,et_ycxy_1,et_ycxy_2,et_tz,et_yw,et_zlztjc_zf,et_yyztjc_zf;
	private Spinner sn_lnrjkzkzwpg,sn_lnrshzlnl;
	private RadioGroup rg_lnrrzgn,rg_lnrqgzt;
	private String name_lnrrzgn="粗筛阴性",name_lnrqgzt="粗筛阴性";
	private RadioButton rb_lnrrzgn_csyinx,rb_lnrrzgn_csyangx,rb_lnrqgzt_csyinx,rb_lnrqgzt_csyangx;
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
		et_zlztjc_zf=(EditText) rootView.findViewById(R.id.et_zlztjc_zf);
		et_yyztjc_zf=(EditText) rootView.findViewById(R.id.et_yyztjc_zf);
		
		sn_lnrjkzkzwpg=(Spinner) rootView.findViewById(R.id.sn_lnrjkzkzwpg);
		sn_lnrshzlnl=(Spinner) rootView.findViewById(R.id.sn_lnrshzlnl);
		
		rg_lnrrzgn=(RadioGroup) rootView.findViewById(R.id.rg_lnrrzgn);
		rg_lnrqgzt=(RadioGroup) rootView.findViewById(R.id.rg_lnrqgzt);
		
		rb_lnrrzgn_csyinx=(RadioButton) rootView.findViewById(R.id.rb_lnrrzgn_csyinx);
		rb_lnrrzgn_csyangx=(RadioButton) rootView.findViewById(R.id.rb_lnrrzgn_csyangx);
		rb_lnrqgzt_csyinx=(RadioButton) rootView.findViewById(R.id.rb_lnrqgzt_csyinx);
		rb_lnrqgzt_csyangx=(RadioButton) rootView.findViewById(R.id.rb_lnrqgzt_csyangx);
		
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
		generalExamination.setYbqk_zljczf(et_zlztjc_zf.getText().toString());
		generalExamination.setYbqk_qgzt(name_lnrqgzt);
		generalExamination.setYbqk_yyztjczf(et_yyztjc_zf.getText().toString());
	}

	@Override
	public void setData(GeneralExamination generalExamination) {
		if(generalExamination!=null) {
		    et_tw.setText(generalExamination.getYbqk_tw());
		    et_mb.setText(generalExamination.getYbqk_mb());
		    et_hxpl.setText(generalExamination.getYbqk_hxpl());
		    String zcxy=generalExamination.getYbqk_zcxy();
		    if(zcxy.split("/").length==1) {
		        et_zcxy_1.setText(zcxy.split("/")[0]);
		    }else if(zcxy.split("/").length==2) {
		        et_zcxy_1.setText(zcxy.split("/")[0]);
		        et_zcxy_2.setText(zcxy.split("/")[1]);
            }
		    String ycxy=generalExamination.getYbqk_ycxy();
            if(ycxy.split("/").length==1) {
                et_ycxy_1.setText(ycxy.split("/")[0]);
            }else if(zcxy.split("/").length==2) {
                et_ycxy_1.setText(ycxy.split("/")[0]);
                et_ycxy_2.setText(ycxy.split("/")[1]);
            }
            et_sg.setText(generalExamination.getYbqk_sg());
            et_bmi.setText(generalExamination.getYbqk_bmi());
            et_tz.setText(generalExamination.getYbqk_tz());
            et_yw.setText(generalExamination.getYbqk_yw());
            ViewDataUtil.setSpinnerData(sn_lnrjkzkzwpg, generalExamination.getYbqk_jkzk());
            ViewDataUtil.setSpinnerData(sn_lnrshzlnl, generalExamination.getYbqk_shzlnl());
            et_zlztjc_zf.setText(generalExamination.getYbqk_zljczf());
            et_yyztjc_zf.setText(generalExamination.getYbqk_yyztjczf());
            
            if(generalExamination.getYbqk_rzgn().equals("粗筛阴性")) {
                rb_lnrrzgn_csyinx.setChecked(true);
                rb_lnrrzgn_csyangx.setChecked(false);
            }else {
                rb_lnrrzgn_csyinx.setChecked(false);
                rb_lnrrzgn_csyangx.setChecked(true);
            }
            
            if(generalExamination.getYbqk_qgzt().equals("粗筛阴性")) {
                rb_lnrqgzt_csyinx.setChecked(true);
                rb_lnrqgzt_csyangx.setChecked(false);
            }else {
                rb_lnrqgzt_csyinx.setChecked(false);
                rb_lnrqgzt_csyangx.setChecked(true);
            }
		}
	}

	@Override
	public boolean validate() {
		return false;
	}
}
