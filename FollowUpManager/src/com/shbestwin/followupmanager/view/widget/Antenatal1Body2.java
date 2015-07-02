package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;

public class Antenatal1Body2 extends LinearLayout  implements IBaseAntenatal1Body {
	private EditText et_zfxm,et_zfnl,et_zfdh;
	public Antenatal1Body2(Context context) {
		this(context, null);
	}

	public Antenatal1Body2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body2, this, true);
		et_zfxm=(EditText) rootView.findViewById(R.id.et_zfxm);
		et_zfnl=(EditText) rootView.findViewById(R.id.et_zfnl);
		et_zfdh=(EditText) rootView.findViewById(R.id.et_zfdh);
	}
	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		followUpFirstPregnancy.setPoxx_zfxm(et_zfxm.getText().toString());
		followUpFirstPregnancy.setPoxx_zfnl(et_zfnl.getText().toString());
		followUpFirstPregnancy.setPoxx_zfdh(et_zfdh.getText().toString());
	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		if(followUpFirstPregnancy!=null) {
		    et_zfxm.setText(followUpFirstPregnancy.getPoxx_zfxm());
		    et_zfnl.setText(followUpFirstPregnancy.getPoxx_zfnl());
		    et_zfdh.setText(followUpFirstPregnancy.getPoxx_zfdh());
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
