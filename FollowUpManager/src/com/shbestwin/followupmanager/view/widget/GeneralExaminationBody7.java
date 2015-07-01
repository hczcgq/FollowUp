package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody7 extends LinearLayout implements IBaseGeneralExaminationBody{
	private Spinner sn_phz,sn_qxz,sn_yangxz,sn_yinxz,sn_tsz,sn_srz,sn_xyz,sn_qyz,sn_tbz;
	public GeneralExaminationBody7(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody7(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody7(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_general_examination_body7, this, true);
		sn_phz=(Spinner) rootView.findViewById(R.id.sn_phz);
		sn_qxz=(Spinner) rootView.findViewById(R.id.sn_qxz);
		sn_yangxz=(Spinner) rootView.findViewById(R.id.sn_yangxz);
		sn_yinxz=(Spinner) rootView.findViewById(R.id.sn_yinxz);
		sn_tsz=(Spinner) rootView.findViewById(R.id.sn_tsz);
		sn_srz=(Spinner) rootView.findViewById(R.id.sn_srz);
		sn_xyz=(Spinner) rootView.findViewById(R.id.sn_xyz);
		sn_qyz=(Spinner) rootView.findViewById(R.id.sn_qyz);
		sn_tbz=(Spinner) rootView.findViewById(R.id.sn_tbz);
	}

	@Override
	public void getData(GeneralExamination generalExamination) {
		generalExamination.setZytzbs_phz(ViewDataUtil.getSpinnerData(sn_phz));
		generalExamination.setZytzbs_qxz(ViewDataUtil.getSpinnerData(sn_qxz));
		generalExamination.setZytzbs_yangxz(ViewDataUtil.getSpinnerData(sn_yangxz));
		generalExamination.setZytzbs_yinxz(ViewDataUtil.getSpinnerData(sn_yinxz));
		generalExamination.setZytzbs_tsz(ViewDataUtil.getSpinnerData(sn_tsz));
		generalExamination.setZytzbs_srz(ViewDataUtil.getSpinnerData(sn_srz));
		generalExamination.setZytzbs_xyz(ViewDataUtil.getSpinnerData(sn_xyz));
		generalExamination.setZytzbs_qyz(ViewDataUtil.getSpinnerData(sn_qyz));
		generalExamination.setZytzbs_tbz(ViewDataUtil.getSpinnerData(sn_tbz));
		
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
