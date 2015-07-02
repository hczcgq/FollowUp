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
import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;

public class MentalDiseaseBody2 extends LinearLayout  implements IBaseMentalDiseaseBody{
	private Spinner sn_wxx;
	public MentalDiseaseBody2(Context context) {
		this(context, null);
	}

	public MentalDiseaseBody2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MentalDiseaseBody2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_mental_disease_body2, this, true);
		sn_wxx=(Spinner) rootView.findViewById(R.id.sn_wxx);
	}
	@Override
	public void getData(FollowUpMentalDisease followUpMentalDisease) {
		followUpMentalDisease.setWxx(ViewDataUtil.getSpinnerData(sn_wxx));
	}

	@Override
	public void setData(FollowUpMentalDisease followUpMentalDisease) {
		if(followUpMentalDisease!=null) {
		    ViewDataUtil.setSpinnerData(sn_wxx, followUpMentalDisease.getWxx());
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
