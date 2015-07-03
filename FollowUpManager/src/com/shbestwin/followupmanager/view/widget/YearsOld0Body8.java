package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body8 extends LinearLayout  implements IBaseYearsOld0Body {
	private EditText et_hwhd;
	public YearsOld0Body8(Context context) {
		this(context, null);
	}

	public YearsOld0Body8(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body8(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_0_body8, this, true);
		et_hwhd=(EditText) rootView.findViewById(R.id.et_hwhd);
	}

	@Override
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
	followUpOneNewborn.setHwhd(et_hwhd.getText().toString());
	}

	@Override
	public void setData(FollowUpOneNewborn followUpOneNewborn) {
		if(followUpOneNewborn!=null) {
		    et_hwhd.setText(followUpOneNewborn.getHwhd());
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
