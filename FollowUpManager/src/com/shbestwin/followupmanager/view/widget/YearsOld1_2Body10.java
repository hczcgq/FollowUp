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
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;

public class YearsOld1_2Body10 extends LinearLayout  implements IBaseYearsOld1_2Body{
	private EditText et_hbqk;
	private RadioGroup rg_hbqk;
	private boolean isHas=false;
	public YearsOld1_2Body10(Context context) {
		this(context, null);
	}

	public YearsOld1_2Body10(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld1_2Body10(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_1_2_body10, this, true);
		et_hbqk=(EditText) rootView.findViewById(R.id.et_hbqk);
		rg_hbqk=(RadioGroup) rootView.findViewById(R.id.rg_hbqk);
		
		rg_hbqk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_hb){
					isHas=true;
				}else if(checkedId==R.id.rb_whb){
					isHas=false;
				}
			}
		});
	}
	@Override
	public void getData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		followUpOneTwoNewborn.setHbqk_sfhb(isHas);
		followUpOneTwoNewborn.setHbqk_sfhbms(et_hbqk.getText().toString());
		
	}

	@Override
	public void setData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
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
