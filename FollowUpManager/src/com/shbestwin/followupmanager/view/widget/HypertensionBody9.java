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

public class HypertensionBody9 extends LinearLayout  implements IBaseHypertensionBody{
	
	private RadioGroup rg_check;
	private EditText et_other;
	private boolean isHas=false;
	
	public HypertensionBody9(Context context) {
		this(context, null);
	}

	public HypertensionBody9(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody9(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_hypertension_body9, this, true);
		rg_check=(RadioGroup) rootView.findViewById(R.id.rg_check);
		et_other=(EditText) rootView.findViewById(R.id.et_other);
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
		followUpHypertension.setYyqk_yy(isHas);
		followUpHypertension.setYyqk_yyms(et_other.getText().toString());

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
