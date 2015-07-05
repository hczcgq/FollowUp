package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;

public class YearsOld3_6Body7 extends LinearLayout  implements IBaseYearsOld3_6Body{
	private RadioGroup jb_gl;
	private RadioButton rb_s,rb_f;
	private boolean isHas=false;
	public YearsOld3_6Body7(Context context) {
		this(context, null);
	}

	public YearsOld3_6Body7(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld3_6Body7(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_3_6_body7, this, true);
		jb_gl=(RadioGroup) rootView.findViewById(R.id.jb_gl);
		rb_s=(RadioButton) rootView.findViewById(R.id.rb_s);
		rb_f=(RadioButton) rootView.findViewById(R.id.rb_f);
		jb_gl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_s){
					isHas=true;
				}else if(checkedId==R.id.rb_f){
					isHas=false;
				}
			}
		});	
	}
	@Override
	public void getData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		followUpThreeSixNewborn.setYyxjbgl_sfyyxjb(isHas);
	}

	@Override
	public void setData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		if(followUpThreeSixNewborn!=null){
			if(followUpThreeSixNewborn.getYyxjbgl_sfyyxjb()) {
			    rb_s.setChecked(true);
			    rb_f.setChecked(false);
			}else {
			    rb_s.setChecked(false);
	            rb_f.setChecked(true);
	        }
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
