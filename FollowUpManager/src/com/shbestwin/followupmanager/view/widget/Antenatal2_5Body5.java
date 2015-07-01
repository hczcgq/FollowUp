package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpTwoToFivePregnancy;

public class Antenatal2_5Body5 extends LinearLayout implements IBaseAntenatal2_5Body {
	private RadioGroup rg_zd;
	private String zd_name="个人卫生";
	public Antenatal2_5Body5(Context context) {
		this(context, null);
	}

	public Antenatal2_5Body5(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal2_5Body5(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal2_5_body5, this, true);
		rg_zd=(RadioGroup) rootView.findViewById(R.id.rg_zd);
		rg_zd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_grws){
					zd_name="个人卫生";
				}else if(checkedId==R.id.rb_ss){
					zd_name="膳食";
				}else if(checkedId==R.id.rb_xl){
					zd_name="心理";
				}else if(checkedId==R.id.rb_yd){
					zd_name="运动";
				}
			}
		});
	}
	@Override
	public void getData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {

		followUpTwoToFivePregnancy.setZd_name(zd_name);
	}

	@Override
	public void setData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
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
