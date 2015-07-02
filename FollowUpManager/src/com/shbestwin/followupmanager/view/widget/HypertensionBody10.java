package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;

public class HypertensionBody10 extends LinearLayout implements IBaseHypertensionBody {
	private EditText et_jyzl;
	public HypertensionBody10(Context context) {
		this(context, null);
	}

	public HypertensionBody10(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody10(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_hypertension_body10, this, true);
		et_jyzl=(EditText) rootView.findViewById(R.id.et_jyzl);
	}

	@Override
	public void getData(FollowUpHypertension followUpHypertension) {

		followUpHypertension.setZljy(et_jyzl.getText().toString());
	}

	@Override
	public void setData(FollowUpHypertension followUpHypertension) {
	    if(followUpHypertension!=null) {
	        et_jyzl.setText(followUpHypertension.getZljy());
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
