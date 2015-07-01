package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;

public class DiabetesMellitusBody3 extends LinearLayout  implements IBaseDiabetesMellitusBody{
	private EditText et_xy_ssy,et_xy_szy,et_xl,et_sg,et_tz_dqz,et_tz_mbz,et_tzzs_dqz,et_tzzs_mbz,et_yw_dqz,et_yw_mbz,et_qt;
	private Spinner sn_zbdmbd;
	public DiabetesMellitusBody3(Context context) {
		this(context, null);
	}

	public DiabetesMellitusBody3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DiabetesMellitusBody3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_diabetes_mellitus_body3, this, true);
		et_xy_ssy=(EditText) rootView.findViewById(R.id.et_xy_ssy);
		et_xy_szy=(EditText) rootView.findViewById(R.id.et_xy_szy);
		et_xl=(EditText) rootView.findViewById(R.id.et_xl);
		et_sg=(EditText) rootView.findViewById(R.id.et_sg);
		et_tz_dqz=(EditText) rootView.findViewById(R.id.et_tz_dqz);
		et_tz_mbz=(EditText) rootView.findViewById(R.id.et_tz_mbz);
		et_tzzs_dqz=(EditText) rootView.findViewById(R.id.et_tzzs_dqz);
		et_tzzs_mbz=(EditText) rootView.findViewById(R.id.et_tzzs_mbz);
		et_yw_dqz=(EditText) rootView.findViewById(R.id.et_yw_dqz);
		et_yw_mbz=(EditText) rootView.findViewById(R.id.et_yw_mbz);
		et_qt=(EditText) rootView.findViewById(R.id.et_qt);
		sn_zbdmbd=(Spinner) rootView.findViewById(R.id.sn_zbdmbd);
		
	}
	@Override
	public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		followUpDiabetesMellitus.setTz_xy(et_xy_ssy.getText().toString()+"/"+et_xy_szy.getText().toString());
		followUpDiabetesMellitus.setTz_xl(et_xl.getText().toString());
		followUpDiabetesMellitus.setTz_sg(et_sg.getText().toString());
		followUpDiabetesMellitus.setTz_tz(et_tz_dqz.getText().toString()+"/"+et_tz_mbz.getText().toString());
		followUpDiabetesMellitus.setTz_tzzs(et_tzzs_dqz.getText().toString()+"/"+et_tzzs_mbz.getText().toString());
		followUpDiabetesMellitus.setTz_zbdmbd(ViewDataUtil.getSpinnerData(sn_zbdmbd));
		followUpDiabetesMellitus.setTz_xy(et_yw_dqz.getText().toString()+"/"+et_yw_mbz.getText().toString());
		followUpDiabetesMellitus.setTz_qt(et_qt.getText().toString());
		
	}

	@Override
	public void setData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub
		
	}

}
