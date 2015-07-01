package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpTwoToFivePregnancy;

public class Antenatal2_5Body2 extends LinearLayout  implements IBaseAntenatal2_5Body{
	private EditText et_tz,et_xy_ssy,et_xy_szy,et_xhdb,et_ndb;
	public Antenatal2_5Body2(Context context) {
		this(context, null);
	}

	public Antenatal2_5Body2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal2_5Body2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal2_5_body2, this, true);
		et_tz=(EditText) rootView.findViewById(R.id.et_tz);
		et_xy_ssy=(EditText) rootView.findViewById(R.id.et_xy_ssy);
		et_xy_szy=(EditText) rootView.findViewById(R.id.et_xy_szy);
		et_xhdb=(EditText) rootView.findViewById(R.id.et_xhdb);
		et_ndb=(EditText) rootView.findViewById(R.id.et_ndb);
	}
	@Override
	public void getData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {

		followUpTwoToFivePregnancy.setTz_tz(et_tz.getText().toString());
		followUpTwoToFivePregnancy.setTz_xy(et_xy_ssy.getText().toString()+"/"+et_xy_szy.getText().toString());
		followUpTwoToFivePregnancy.setTz_xhdb(et_xhdb.getText().toString());
		followUpTwoToFivePregnancy.setTz_ndb(et_ndb.getText().toString());
	}

	@Override
	public void setData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
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
