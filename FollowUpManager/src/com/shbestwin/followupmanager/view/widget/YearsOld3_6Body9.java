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
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;

public class YearsOld3_6Body9 extends LinearLayout  implements IBaseYearsOld3_6Body{
	private RadioGroup rg_zz;
	private EditText et_yy,et_jgjks;
	private RadioButton rb_w,rb_y;
	private boolean isHas=false;
	public YearsOld3_6Body9(Context context) {
		this(context, null);
	}

	public YearsOld3_6Body9(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld3_6Body9(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_3_6_body9, this, true);
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
	public void getData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		followUpThreeSixNewborn.setZz_sfzz(isHas);
		followUpThreeSixNewborn.setZz_yy(et_yy.getText().toString());
		followUpThreeSixNewborn.setZz_jgjks(et_jgjks.getText().toString());
	}

	@Override
	public void setData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
	    if(followUpThreeSixNewborn!=null) {
	        et_jgjks.setText(followUpThreeSixNewborn.getZz_jgjks());
	        et_yy.setText(followUpThreeSixNewborn.getZz_yy());
	        if(followUpThreeSixNewborn.getZz_sfzz()) {
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
