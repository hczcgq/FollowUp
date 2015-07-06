package com.shbestwin.followupmanager.view.widget;

import java.io.File;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;

public class GeneralExaminationBody3 extends LinearLayout implements IBaseGeneralExaminationBody{
	
    private Spinner sn_dlpl,sn_yjpl,sn_yjzl;
    private EditText et_mcdlsj,et_jcdlsj,et_dlfs,et_rxyl,et_ksxyln,et_jyln,et_ryjl,et_ksyjln,et_jjln
                    ,et_gz,et_cysj;
    private LinearLayout dietLayout;
    private RadioGroup rg_xyzk,rg_sfjj,rg_sfzj,rg_zybwhysjcs;
    private RadioButton rb_xyzk_cbxy,rb_xyzk_yjy,rb_xyzk_xy,rb_sfjj_yjj,rb_sfjj_wjj,rb_sfzj_s,rb_sfzj_f,rb_zybwhysjcs_w,rb_zybwhysjcs_y;
    public GeneralExaminationBody3(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_general_examination_body3, this, true);
		
		et_mcdlsj=(EditText) rootView.findViewById(R.id.et_mcdlsj);
		et_jcdlsj=(EditText) rootView.findViewById(R.id.et_jcdlsj);
		et_dlfs=(EditText) rootView.findViewById(R.id.et_dlfs);
		et_rxyl=(EditText) rootView.findViewById(R.id.et_rxyl);
		et_ksxyln=(EditText) rootView.findViewById(R.id.et_ksxyln);
		et_jyln=(EditText) rootView.findViewById(R.id.et_jyln);
		et_ryjl=(EditText) rootView.findViewById(R.id.et_ryjl);
		et_ksyjln=(EditText) rootView.findViewById(R.id.et_ksyjln);
		et_jjln=(EditText) rootView.findViewById(R.id.et_jjln);
		et_gz=(EditText) rootView.findViewById(R.id.et_gz);
		et_cysj=(EditText) rootView.findViewById(R.id.et_cysj);
		
		
		sn_dlpl=(Spinner) rootView.findViewById(R.id.sn_dlpl);
		sn_yjpl=(Spinner) rootView.findViewById(R.id.sn_yjpl);
		sn_yjzl=(Spinner) rootView.findViewById(R.id.sn_yjzl);
		
		dietLayout=(LinearLayout) rootView.findViewById(R.id.dietLayout);
	}

	@Override
	public void getData(GeneralExamination generalExamination) {
	    generalExamination.setShfs_dlpl(ViewDataUtil.getSpinnerData(sn_dlpl));
	    generalExamination.setShfs_yjpl(ViewDataUtil.getSpinnerData(sn_yjpl));
	    generalExamination.setShfs_yjzl(ViewDataUtil.getSpinnerData(sn_yjzl));
	    generalExamination.setShfs_ysxg(ViewDataUtil.getCheckboxData(dietLayout, null));
	    generalExamination.setShfs_mcdlsj(et_mcdlsj.getText().toString());
	    generalExamination.setShfs_jcdlsj(et_jcdlsj.getText().toString());
	    generalExamination.setShfs_dlfs(et_dlfs.getText().toString());
	    generalExamination.setShfs_rxyl(et_rxyl.getText().toString());
	    generalExamination.setShfs_ksxynl(et_ksxyln.getText().toString());
	    generalExamination.setShfs_jynl(et_jyln.getText().toString());
	    generalExamination.setShfs_ryjl(et_ryjl.getText().toString());
	    generalExamination.setShfs_ksyjnl(et_ksyjln.getText().toString());
	    generalExamination.setShfs_jjnl(et_jjln.getText().toString());
	    generalExamination.setShfs_gz(et_gz.getText().toString());
	    generalExamination.setShfs_cysj(et_cysj.getText().toString());
	}

	@Override
	public void setData(GeneralExamination generalExamination) {
		if(generalExamination!=null) {
		    ViewDataUtil.setSpinnerData(sn_dlpl, generalExamination.getShfs_dlpl());
		    ViewDataUtil.setSpinnerData(sn_yjpl, generalExamination.getShfs_yjpl());
		    ViewDataUtil.setSpinnerData(sn_yjzl, generalExamination.getShfs_yjzl());
		    ViewDataUtil.setCheckboxData(dietLayout, null, generalExamination.getShfs_ysxg());
		    et_mcdlsj.setText(generalExamination.getShfs_mcdlsj());
		    et_jcdlsj.setText(generalExamination.getShfs_jcdlsj());
		    et_dlfs.setText(generalExamination.getShfs_dlfs());
		    et_rxyl.setText(generalExamination.getShfs_rxyl());
		    et_ksxyln.setText(generalExamination.getShfs_ksxynl());
		    et_jyln.setText(generalExamination.getShfs_jynl());
		    et_ryjl.setText(generalExamination.getShfs_ryjl());
		    et_ksyjln.setText(generalExamination.getShfs_ksyjnl());
		    et_jjln.setText(generalExamination.getShfs_jjnl());
		    et_gz.setText(generalExamination.getShfs_gz());
		    et_cysj.setText(generalExamination.getShfs_cysj());
		}
	}

	@Override
	public boolean validate() {
		return true;
	}
}
