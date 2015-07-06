package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody6 extends LinearLayout implements IBaseGeneralExaminationBody{
	
    private EditText et_xhdb,et_bxb,et_xxb,et_xcg_qt,et_ndb,et_nt,et_nbz,et_nysjd,et_ncg_qt
                    ,et_kfxt,et_xdt,et_nwlbdb,et_thxhdb;
    private Spinner sn_ndb,sn_nt,sn_ntt,sn_nqx,sn_yxgybmky,sn_yxgyhxkt,sn_yxgyeky,sn_yxgyekt;
    
    private RadioGroup rg_xdt,rg_dbqx;
    private RadioButton rb_xdt_zc,rb_xdt_yc,rb_dbqx_yinx,rb_dbqx_yangx;
    
    public GeneralExaminationBody6(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_general_examination_body6, this, true);
	}

	@Override
	public void getData(GeneralExamination generalExamination) {
		// TODO Auto-generated method stub
		
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
