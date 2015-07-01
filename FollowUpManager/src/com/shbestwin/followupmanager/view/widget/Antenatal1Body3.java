package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;

public class Antenatal1Body3 extends LinearLayout  implements IBaseAntenatal1Body {
	private EditText et_sg,et_tz,et_tzzs,et_xy_ssy,et_xy_szy;
	public Antenatal1Body3(Context context) {
		this(context, null);
	}

	public Antenatal1Body3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body3, this, true);
		et_sg=(EditText) rootView.findViewById(R.id.et_sg);
		et_tz=(EditText) rootView.findViewById(R.id.et_tz);
		et_tzzs=(EditText) rootView.findViewById(R.id.et_tzzs);
		et_xy_ssy=(EditText) rootView.findViewById(R.id.et_xy_ssy);
		et_xy_szy=(EditText) rootView.findViewById(R.id.et_xy_szy);
	}
	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {

		followUpFirstPregnancy.setTz_sg(et_sg.getText().toString());
		followUpFirstPregnancy.setTz_tz(et_tz.getText().toString());
		followUpFirstPregnancy.setTz_tzzs(et_tzzs.getText().toString());
		followUpFirstPregnancy.setTz_xy(et_xy_ssy.getText().toString()+"/"+et_xy_szy.getText().toString());
	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {
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
