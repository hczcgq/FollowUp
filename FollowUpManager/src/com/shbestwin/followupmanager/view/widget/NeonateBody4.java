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
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;

public class NeonateBody4 extends LinearLayout  implements IBaseNeonateBody{
	private RadioGroup rg_gwe;
	private RadioButton rb_s,rb_f;
	private boolean isHas=false;
	public NeonateBody4(Context context) {
		this(context, null);
	}

	public NeonateBody4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NeonateBody4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_neonate_body4, this, true);
		rg_gwe=(RadioGroup) rootView.findViewById(R.id.rg_gwe);
		rb_s=(RadioButton) rootView.findViewById(R.id.rb_s);
        rb_f=(RadioButton) rootView.findViewById(R.id.rb_f);
		rg_gwe.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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
	public void getData(FollowUpNewborn followUpNewborn) {
		followUpNewborn.setGwe_sfgwe(isHas);
		
	}

	@Override
	public void setData(FollowUpNewborn followUpNewborn) {
		 if(followUpNewborn!=null) {
	            if(followUpNewborn.getGwe_sfgwe()) {
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
