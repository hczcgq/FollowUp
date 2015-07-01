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
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;

public class HypertensionBody8 extends LinearLayout  implements IBaseHypertensionBody{
	
	private Spinner sn_ccsffl;
	public HypertensionBody8(Context context) {
		this(context, null);
	}

	public HypertensionBody8(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody8(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_hypertension_body8, this, true);
		sn_ccsffl=(Spinner) rootView.findViewById(R.id.sn_ccsffl);
	}
	@Override
	public void getData(FollowUpHypertension followUpHypertension) {
		followUpHypertension.setCcsffl(ViewDataUtil.getSpinnerData(sn_ccsffl));

	}

	@Override
	public void setData(FollowUpHypertension followUpHypertension) {

	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub
		
	}
}
