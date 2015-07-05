package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;

public class YearsOld1_2Body5 extends LinearLayout  implements IBaseYearsOld1_2Body{
	private EditText et_xhdb;
	public YearsOld1_2Body5(Context context) {
		this(context, null);
	}

	public YearsOld1_2Body5(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld1_2Body5(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_1_2_body5, this, true);
		et_xhdb=(EditText) rootView.findViewById(R.id.et_xhdb);
	}
	@Override
	public void getData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		followUpOneTwoNewborn.setXhdbz(et_xhdb.getText().toString());
	}

	@Override
	public void setData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		if(followUpOneTwoNewborn!=null){
			et_xhdb.setText(followUpOneTwoNewborn.getXhdbz());
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
