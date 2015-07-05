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
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;

public class Antenatal1Body9 extends LinearLayout implements IBaseAntenatal1Body {
	private Spinner sn_ycs;
	public Antenatal1Body9(Context context) {
		this(context, null);
	}

	public Antenatal1Body9(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body9(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body9, this, true);
		sn_ycs=(Spinner) rootView.findViewById(R.id.sn_ycs);
	}
	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		followUpFirstPregnancy.setYcs_ycs(ViewDataUtil.getSpinnerData(sn_ycs));
	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {

		if(followUpFirstPregnancy!=null){
			ViewDataUtil.setSpinnerData(sn_ycs, followUpFirstPregnancy.getYcs_ycs());
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
