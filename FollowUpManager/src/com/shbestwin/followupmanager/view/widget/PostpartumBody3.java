package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpPostpartum;

public class PostpartumBody3 extends LinearLayout  implements IBasePostpartumBody{
	private EditText et_xlzk,et_smzk,et_jkzk;
	public PostpartumBody3(Context context) {
		this(context, null);
	}

	public PostpartumBody3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PostpartumBody3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_postpartum_body3, this, true);
		et_xlzk=(EditText) rootView.findViewById(R.id.et_xlzk);
		et_smzk=(EditText) rootView.findViewById(R.id.et_smzk);
		et_jkzk=(EditText) rootView.findViewById(R.id.et_jkzk);
	}
	@Override
	public void getData(FollowUpPostpartum followUpPostpartum) {
		followUpPostpartum.setXljkzk_xlzk(et_xlzk.getText().toString());
		followUpPostpartum.setXljkzk_smzk(et_smzk.getText().toString());
		followUpPostpartum.setXljkzk_jkzk(et_jkzk.getText().toString());

	}

	@Override
	public void setData(FollowUpPostpartum followUpPostpartum) {
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
