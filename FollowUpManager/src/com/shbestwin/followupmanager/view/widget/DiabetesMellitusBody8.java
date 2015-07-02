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

public class DiabetesMellitusBody8 extends LinearLayout implements  IBaseDiabetesMellitusBody{
	private Spinner sn_fyycx;
	public DiabetesMellitusBody8(Context context) {
		this(context, null);
	}

	public DiabetesMellitusBody8(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DiabetesMellitusBody8(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_diabetes_mellitus_body8, this, true);
		sn_fyycx=(Spinner) rootView.findViewById(R.id.sn_fyycx);
	}
	@Override
	public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		followUpDiabetesMellitus.setFyycx(ViewDataUtil.getSpinnerData(sn_fyycx));
	}

	@Override
	public void setData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		if(followUpDiabetesMellitus!=null) {
		    ViewDataUtil.setSpinnerData(sn_fyycx, followUpDiabetesMellitus.getFyycx());
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
