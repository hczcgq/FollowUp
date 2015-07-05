package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody14 extends LinearLayout implements
		IBaseGeneralExaminationBody {

	private RelativeLayout relativeLayout;
	private EditText et_mb, et_jyjzym, et_qt;

	public GeneralExaminationBody14(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody14(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody14(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_general_examination_body14, this, true);
		relativeLayout = (RelativeLayout) rootView
				.findViewById(R.id.relativeLayout);
		et_mb = (EditText) rootView.findViewById(R.id.et_mb);
		et_jyjzym = (EditText) rootView.findViewById(R.id.et_jyjzym);
		et_qt = (EditText) rootView.findViewById(R.id.et_qt);
	}

	@Override
	public void getData(GeneralExamination generalExamination) {
		generalExamination.setWxyskz(ViewDataUtil.getCheckboxData(
				relativeLayout, null));
		generalExamination.setWxyskz_mb(et_mb.getText().toString());
		generalExamination.setWxyskz_jyjzym(et_jyjzym.getText().toString());
		generalExamination.setWxyskz_qt(et_qt.getText().toString());
	}

	@Override
	public void setData(GeneralExamination generalExamination) {
		if (generalExamination != null) {
			ViewDataUtil.setCheckboxData(relativeLayout, null,
					generalExamination.getWxyskz());
			et_mb.setText(generalExamination.getWxyskz_mb());
			et_jyjzym.setText(generalExamination.getWxyskz_jyjzym());
			et_qt.setText(generalExamination.getWxyskz_qt());
		}
	}

	@Override
	public boolean validate() {
		return true;
	}
}
