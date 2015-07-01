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

public class MentalDiseaseBody4 extends LinearLayout  implements IBaseMentalDiseaseBody{
	private Spinner sn_grshll,sn_jwhd,sn_xxnl,sn_scndjgz,sn_shrjjw;
	public MentalDiseaseBody4(Context context) {
		this(context, null);
	}

	public MentalDiseaseBody4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MentalDiseaseBody4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_mental_disease_body4, this, true);
		sn_grshll=(Spinner) rootView.findViewById(R.id.sn_grshll);
		sn_jwhd=(Spinner) rootView.findViewById(R.id.sn_jwhd);
		sn_xxnl=(Spinner) rootView.findViewById(R.id.sn_xxnl);
		sn_scndjgz=(Spinner) rootView.findViewById(R.id.sn_scndjgz);
		sn_shrjjw=(Spinner) rootView.findViewById(R.id.sn_shrjjw);
	}
	@Override
	public void getData(FollowUpMentalDisease followUpMentalDisease) {

		followUpMentalDisease.setShgnqk_grshll(ViewDataUtil.getSpinnerData(sn_grshll));
		followUpMentalDisease.setShgnqk_jwhd(ViewDataUtil.getSpinnerData(sn_jwhd));
		followUpMentalDisease.setShgnqk_xxnl(ViewDataUtil.getSpinnerData(sn_xxnl));
		followUpMentalDisease.setShgnqk_shldjgz(ViewDataUtil.getSpinnerData(sn_scndjgz));
		followUpMentalDisease.setShgnqk_shrjjw(ViewDataUtil.getSpinnerData(sn_shrjjw));
	}

	@Override
	public void setData(FollowUpMentalDisease followUpMentalDisease) {
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
