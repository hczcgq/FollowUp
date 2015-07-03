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
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body13 extends LinearLayout  implements IBaseYearsOld0Body {
	private RadioGroup rg_gwegl;
	private boolean isHas=false;
	private RadioButton rb_s,rb_f;
	public YearsOld0Body13(Context context) {
		this(context, null);
	}

	public YearsOld0Body13(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body13(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_0_body13, this, true);
		rg_gwegl=(RadioGroup) rootView.findViewById(R.id.rg_gwegl);
		rb_s=(RadioButton) rootView.findViewById(R.id.rb_s);
        rb_f=(RadioButton) rootView.findViewById(R.id.rb_f);
		rg_gwegl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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
		followUpOneNewborn.setYyxjbgl_sfgwe(isHas);
	}

	@Override
	public void setData(FollowUpOneNewborn followUpOneNewborn) {
	    if(followUpOneNewborn!=null) {
            if(followUpOneNewborn.getYyxjbgl_sfgwe()) {
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
