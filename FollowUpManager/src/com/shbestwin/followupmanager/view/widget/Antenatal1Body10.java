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
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;

public class Antenatal1Body10 extends LinearLayout  implements IBaseAntenatal1Body {
	private RadioGroup rg_tz,rg_fb;
	private EditText et_tz,et_fb;
	private boolean isTzYc=false,isFbYc=false;
	public Antenatal1Body10(Context context) {
		this(context, null);
	}

	public Antenatal1Body10(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body10(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body10, this, true);
		rg_tz=(RadioGroup) rootView.findViewById(R.id.rg_tz);
		rg_fb=(RadioGroup) rootView.findViewById(R.id.rg_fb);
		et_tz=(EditText) rootView.findViewById(R.id.et_tz);
		et_fb=(EditText) rootView.findViewById(R.id.et_fb);
		
		rg_tz.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_tz_w){
					isTzYc=false;
				}else if(checkedId==R.id.rb_tz_y){
					isTzYc=true;
				}
			}
		});
		
		rg_fb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_fb_w){
					isFbYc=false;
				}else if(checkedId==R.id.rb_fb_y){
					isFbYc=true;
				}
			}
		});
	}
	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		followUpFirstPregnancy.setTingz_sffbyc(isFbYc);
		followUpFirstPregnancy.setTingz_sffbycms(et_fb.getText().toString());
		followUpFirstPregnancy.setTingz_sfxzyc(isTzYc);
		followUpFirstPregnancy.setTingz_sfxzycms(et_tz.getText().toString());
	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {
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
