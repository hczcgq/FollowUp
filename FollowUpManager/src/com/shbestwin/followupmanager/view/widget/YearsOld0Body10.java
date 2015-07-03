package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body10 extends LinearLayout  implements IBaseYearsOld0Body {
	private EditText et_hbqk;
	private RadioGroup rg_hbqk;
	private RadioButton rb_hb,rb_whb;
	private boolean isHas=false;
	public YearsOld0Body10(Context context) {
		this(context, null);
	}

	public YearsOld0Body10(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body10(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_0_body10, this, true);
		et_hbqk=(EditText) rootView.findViewById(R.id.et_hbqk);
		rg_hbqk=(RadioGroup) rootView.findViewById(R.id.rg_hbqk);
		rb_hb=(RadioButton) rootView.findViewById(R.id.rb_hb);
		rb_whb=(RadioButton) rootView.findViewById(R.id.rb_whb);
		
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
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		followUpOneNewborn.setHbqk_sfhb(isHas);
		followUpOneNewborn.setHbqk_sfhbms(et_hbqk.getText().toString());
	}

	@Override
	public void setData(FollowUpOneNewborn followUpOneNewborn) {
		if(followUpOneNewborn!=null) {
		    et_hbqk.setText(followUpOneNewborn.getHbqk_sfhbms());
		    if(followUpOneNewborn.getHbqk_sfhb()) {
		        rb_hb.setChecked(true);
		        rb_whb.setChecked(false);
		    }else {
		        rb_hb.setChecked(false);
                rb_whb.setChecked(true);
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
