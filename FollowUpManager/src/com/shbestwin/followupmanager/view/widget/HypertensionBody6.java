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

public class HypertensionBody6 extends LinearLayout  implements IBaseHypertensionBody{
	private Spinner sn_fyycx;
	public HypertensionBody6(Context context) {
		this(context, null);
	}

	public HypertensionBody6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_hypertension_body6, this, true);
		sn_fyycx=(Spinner) rootView.findViewById(R.id.sn_fyycx);
	}
	@Override
	public void getData(FollowUpHypertension followUpHypertension) {

		followUpHypertension.setFyycx(ViewDataUtil.getSpinnerData(sn_fyycx));
	}

	@Override
	public void setData(FollowUpHypertension followUpHypertension) {
	    if(followUpHypertension!=null) {
	        ViewDataUtil.setSpinnerData(sn_fyycx, followUpHypertension.getFyycx());
	    }
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
