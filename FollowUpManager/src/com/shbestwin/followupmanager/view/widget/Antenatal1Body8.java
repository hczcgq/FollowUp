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
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;

public class Antenatal1Body8 extends LinearLayout  implements IBaseAntenatal1Body {
	private RadioGroup rg_fksss;
	private EditText et_fksss;
	private boolean isHas=false;
	private RadioButton rb_w,rb_y;
	public Antenatal1Body8(Context context) {
		this(context, null);
	}

	public Antenatal1Body8(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body8(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body8, this, true);
		rg_fksss=(RadioGroup) rootView.findViewById(R.id.rg_fksss);
		et_fksss=(EditText) rootView.findViewById(R.id.et_fksss);
		rb_w=(RadioButton) rootView.findViewById(R.id.rb_w);
		rb_y=(RadioButton) rootView.findViewById(R.id.rb_y);
		
		rg_fksss.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		followUpFirstPregnancy.setFksss_sfygss(isHas);
		followUpFirstPregnancy.setFksss_sfygssms(et_fksss.getText().toString());
	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		if(followUpFirstPregnancy!=null){
			et_fksss.setText(followUpFirstPregnancy.getFksss_sfygssms());
			if(followUpFirstPregnancy.getFksss_sfygss()){
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
