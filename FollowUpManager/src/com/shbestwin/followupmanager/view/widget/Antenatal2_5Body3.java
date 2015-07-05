package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpTwoToFivePregnancy;

public class Antenatal2_5Body3 extends LinearLayout  implements IBaseAntenatal2_5Body{
	private EditText et_gdgd,et_fw,et_txl;
	public Antenatal2_5Body3(Context context) {
		this(context, null);
	}

	public Antenatal2_5Body3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal2_5Body3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal2_5_body3, this, true);
		et_gdgd=(EditText) rootView.findViewById(R.id.et_gdgd);
		et_fw=(EditText) rootView.findViewById(R.id.et_fw);
		et_txl=(EditText) rootView.findViewById(R.id.et_txl);
	}
	@Override
	public void getData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
		followUpTwoToFivePregnancy.setCqjc_gdgd(et_gdgd.getText().toString());
		followUpTwoToFivePregnancy.setCqjc_fw(et_fw.getText().toString());
		followUpTwoToFivePregnancy.setCqjc_txl(et_txl.getText().toString());
	}

	@Override
	public void setData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
		if(followUpTwoToFivePregnancy!=null){
			et_gdgd.setText(followUpTwoToFivePregnancy.getCqjc_gdgd());
			et_fw.setText(followUpTwoToFivePregnancy.getCqjc_fw());
			et_txl.setText(followUpTwoToFivePregnancy.getCqjc_txl());
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
