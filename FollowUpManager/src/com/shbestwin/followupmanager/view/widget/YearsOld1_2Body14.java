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
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;

public class YearsOld1_2Body14 extends LinearLayout  implements IBaseYearsOld1_2Body{
	private RadioGroup rg_zz;
	private EditText et_yy,et_jgjks;
	private boolean isHas=false;
	private RadioButton rb_w,rb_y;
	public YearsOld1_2Body14(Context context) {
		this(context, null);
	}

	public YearsOld1_2Body14(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld1_2Body14(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_1_2_body14, this, true);
		rg_zz=(RadioGroup) rootView.findViewById(R.id.rg_zz);
		et_yy=(EditText) rootView.findViewById(R.id.et_yy);
		et_jgjks=(EditText) rootView.findViewById(R.id.et_jgjks);
		rb_w=(RadioButton) rootView.findViewById(R.id.rb_w);
		rb_y=(RadioButton) rootView.findViewById(R.id.rb_y);
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
	public void getData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		followUpOneTwoNewborn.setZz_sfzz(isHas);
		followUpOneTwoNewborn.setZz_yy(et_yy.getText().toString());
		followUpOneTwoNewborn.setZz_jgjks(et_jgjks.getText().toString());
	}

	@Override
	public void setData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
	    if(followUpOneTwoNewborn!=null) {
	        et_jgjks.setText(followUpOneTwoNewborn.getZz_jgjks());
	        et_yy.setText(followUpOneTwoNewborn.getZz_yy());
	        if(followUpOneTwoNewborn.getZz_sfzz()) {
	            rb_y.setChecked(true);
	            rb_w.setChecked(false);
	        }else {
	            rb_y.setChecked(false);
                rb_w.setChecked(true);
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
