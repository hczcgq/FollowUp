package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpFortyTwo;

public class Inspect42Body3 extends LinearLayout  implements IBaseInspect42Body{
	private EditText et_xlzk,et_smzk,et_jkzk;
	public Inspect42Body3(Context context) {
		this(context, null);
	}

	public Inspect42Body3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Inspect42Body3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_inspect_42_body3, this, true);
		et_xlzk=(EditText) rootView.findViewById(R.id.et_xlzk);
		et_smzk=(EditText) rootView.findViewById(R.id.et_smzk);
		et_jkzk=(EditText) rootView.findViewById(R.id.et_jkzk);
	}
	@Override
	public void getData(FollowUpFortyTwo followUpFortyTwo) {
		followUpFortyTwo.setXljkzk_xlzk(et_xlzk.getText().toString());
		followUpFortyTwo.setXljkzk_smzk(et_smzk.getText().toString());
		followUpFortyTwo.setXljkzk_jkzk(et_jkzk.getText().toString());
	}

	@Override
	public void setData(FollowUpFortyTwo followUpFortyTwo) {
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
