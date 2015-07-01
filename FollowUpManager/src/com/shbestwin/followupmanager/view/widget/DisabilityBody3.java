package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpDisabledPerson;

public class DisabilityBody3 extends LinearLayout  implements IBaseDisabilityBody {
	private EditText et_zz,et_tz,et_xy_ssy,et_xy_szy,et_xl,et_qt;
	public DisabilityBody3(Context context) {
		this(context, null);
	}

	public DisabilityBody3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DisabilityBody3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_disability_body3, this, true);
		et_zz=(EditText) rootView.findViewById(R.id.et_zz);
		et_tz=(EditText) rootView.findViewById(R.id.et_tz);
		et_xy_ssy=(EditText) rootView.findViewById(R.id.et_xy_ssy);
		et_xy_szy=(EditText) rootView.findViewById(R.id.et_xy_szy);
		et_xl=(EditText) rootView.findViewById(R.id.et_xl);
		et_qt=(EditText) rootView.findViewById(R.id.et_qt);
	}
	@Override
	public void getData(FollowUpDisabledPerson followUpDisabledPerson) {
		followUpDisabledPerson.setYbqk_zz(et_zz.getText().toString());
		followUpDisabledPerson.setYbqk_tz(et_tz.getText().toString());
		followUpDisabledPerson.setYbqk_xy(et_xy_ssy.getText().toString()+"/"+et_xy_szy.getText().toString());
		followUpDisabledPerson.setYbqk_xl(et_xl.getText().toString());
		followUpDisabledPerson.setYbqk_qt(et_qt.getText().toString());
	}

	@Override
	public void setData(FollowUpDisabledPerson followUpDisabledPerson) {
		if(followUpDisabledPerson!=null) {
		    et_zz.setText(followUpDisabledPerson.getYbqk_zz());
		    et_tz.setText(followUpDisabledPerson.getYbqk_tz());
		    et_xl.setText(followUpDisabledPerson.getYbqk_xl());
		    et_qt.setText(followUpDisabledPerson.getYbqk_qt());
		    String xy=followUpDisabledPerson.getYbqk_xy();
		    if(xy.split("/").length!=0) {
		        et_xy_ssy.setText(xy.split("/")[0]);
	            et_xy_szy.setText(xy.split("/")[1]);
		    }
		}
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
