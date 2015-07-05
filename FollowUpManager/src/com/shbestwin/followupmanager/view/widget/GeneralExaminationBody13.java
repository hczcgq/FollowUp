package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody13 extends LinearLayout implements IBaseGeneralExaminationBody{
	private LinearLayout raleLayout;
	public GeneralExaminationBody13(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody13(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody13(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_general_examination_body13, this, true);
		raleLayout=(LinearLayout) rootView.findViewById(R.id.raleLayout);
		
		
	}

	@Override
	public void getData(GeneralExamination generalExamination) {
		generalExamination.setJkzd(ViewDataUtil.getCheckboxData(raleLayout,
				null));
		
	}

	@Override
	public void setData(GeneralExamination generalExamination) {
		if(generalExamination!=null){
			ViewDataUtil.setCheckboxData(raleLayout, null, generalExamination.getJkzd());	
		}
	}

	@Override
	public boolean validate() {
		return true;
	}
}
