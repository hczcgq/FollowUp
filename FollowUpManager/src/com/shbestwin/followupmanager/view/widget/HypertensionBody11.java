package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;

public class HypertensionBody11 extends LinearLayout  implements IBaseHypertensionBody{
	private RadioGroup rg_check;
	private EditText et_reason,et_group;
	private boolean isHas=false;
	
	public HypertensionBody11(Context context) {
		this(context, null);
	}

	public HypertensionBody11(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody11(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_hypertension_body11, this, true);
		rg_check=(RadioGroup) rootView.findViewById(R.id.rg_check);
		et_reason=(EditText) rootView.findViewById(R.id.et_reason);
		et_group=(EditText) rootView.findViewById(R.id.et_group);
		
		rg_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_w){
					isHas=false;
				}else if(checkedId==R.id.rb_y){
					isHas=true;
				}
			}
		});
	}
	@Override
	public void getData(FollowUpHypertension followUpHypertension) {
		followUpHypertension.setZz_sfzz(isHas);
		followUpHypertension.setZz_jgjks(et_group.getText().toString());
		followUpHypertension.setZz_sfzzms(et_reason.getText().toString());
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
