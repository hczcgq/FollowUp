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

public class YearsOld0Body5 extends LinearLayout  implements IBaseYearsOld0Body {
	private EditText et_xhdb;
	public YearsOld0Body5(Context context) {
		this(context, null);
	}

	public YearsOld0Body5(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body5(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_0_body5, this, true);
		et_xhdb=(EditText) rootView.findViewById(R.id.et_xhdb);
	}

	@Override
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		followUpOneNewborn.setXhdbz(et_xhdb.getText().toString());
	}

	@Override
	public void setData(FollowUpOneNewborn followUpOneNewborn) {
		if(followUpOneNewborn!=null) {
		    et_xhdb.setText(followUpOneNewborn.getXhdbz());
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
