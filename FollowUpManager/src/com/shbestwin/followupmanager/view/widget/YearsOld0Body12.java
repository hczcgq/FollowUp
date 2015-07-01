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
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body12 extends LinearLayout  implements IBaseYearsOld0Body {
	private RadioGroup jb_gl;
	private boolean isHas=false;
	public YearsOld0Body12(Context context) {
		this(context, null);
	}

	public YearsOld0Body12(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body12(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_0_body12, this, true);
		jb_gl=(RadioGroup) rootView.findViewById(R.id.jb_gl);
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
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		followUpOneNewborn.setYyxjbgl_sfyyxjb(isHas);
	}

	@Override
	public void setData(FollowUpOneNewborn followUpOneNewborn) {
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
