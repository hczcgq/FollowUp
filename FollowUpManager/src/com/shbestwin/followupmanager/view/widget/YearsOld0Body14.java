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

public class YearsOld0Body14 extends LinearLayout  implements IBaseYearsOld0Body {
	private RadioGroup rg_zz;
	private EditText et_yy,et_jgjks;
	private RadioButton rb_w,rb_y;
	private boolean isHas=false;
	public YearsOld0Body14(Context context) {
		this(context, null);
	}

	public YearsOld0Body14(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body14(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_0_body14, this, true);
		rg_zz=(RadioGroup) rootView.findViewById(R.id.rg_zz);
		et_yy=(EditText) rootView.findViewById(R.id.et_yy);
		et_jgjks=(EditText) rootView.findViewById(R.id.et_jgjks);
		rb_y=(RadioButton) rootView.findViewById(R.id.rb_y);
        rb_w=(RadioButton) rootView.findViewById(R.id.rb_w);
		rg_zz.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		followUpOneNewborn.setZz_sfzz(isHas);
		followUpOneNewborn.setZz_yy(et_yy.getText().toString());
		followUpOneNewborn.setZz_jgjks(et_jgjks.getText().toString());
	}

	@Override
	public void setData(FollowUpOneNewborn followUpOneNewborn) {
	    if(followUpOneNewborn!=null) {
            if(followUpOneNewborn.getZz_sfzz()) {
                rb_y.setChecked(true);
                rb_w.setChecked(false);
            }else {
                rb_y.setChecked(false);
                rb_w.setChecked(true);
            }
            et_jgjks.setText(followUpOneNewborn.getZz_jgjks());
            et_yy.setText(followUpOneNewborn.getZz_yy());
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
