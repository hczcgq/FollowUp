package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody10 extends LinearLayout implements IBaseGeneralExaminationBody {
	public GeneralExaminationBody10(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody10(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody10(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_general_examination_body10, this, true);
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
