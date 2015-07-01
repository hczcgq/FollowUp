package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;

public class DiabetesMellitusBody11 extends LinearLayout  implements IBaseDiabetesMellitusBody{
	private Spinner sn_ccsffl;
	public DiabetesMellitusBody11(Context context) {
		this(context, null);
	}

	public DiabetesMellitusBody11(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DiabetesMellitusBody11(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_diabetes_mellitus_body11, this, true);
		sn_ccsffl=(Spinner) rootView.findViewById(R.id.sn_ccsffl);
	}
	@Override
	public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		followUpDiabetesMellitus.setCcsffl(ViewDataUtil.getSpinnerData(sn_ccsffl));
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
