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
import com.shbestwin.followupmanager.model.followup.FollowUpTwoToFivePregnancy;

public class Antenatal2_5Body4 extends LinearLayout  implements IBaseAntenatal2_5Body{
	private RadioGroup rg_bc,rg_xtsc;
	private EditText et_bc,et_xtsc;
	private boolean isBcYc=false,isXtjcYc=false;
	private RadioButton rb_bc_w,rb_bc_y,rb_xtsc_w,rb_xtsc_y;
	
	public Antenatal2_5Body4(Context context) {
		this(context, null);
	}

	public Antenatal2_5Body4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal2_5Body4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal2_5_body4, this, true);
		rg_bc=(RadioGroup) rootView.findViewById(R.id.rg_bc);
		rg_xtsc=(RadioGroup) rootView.findViewById(R.id.rg_xtsc);
		et_bc=(EditText) rootView.findViewById(R.id.et_bc);
		et_xtsc=(EditText) rootView.findViewById(R.id.et_xtsc);
		rb_bc_w=(RadioButton) rootView.findViewById(R.id.rb_bc_w);
		rb_bc_y=(RadioButton) rootView.findViewById(R.id.rb_bc_y);
		rb_xtsc_w=(RadioButton) rootView.findViewById(R.id.rb_xtsc_w);
		rb_xtsc_y=(RadioButton) rootView.findViewById(R.id.rb_xtsc_y);
		
		rg_bc.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_bc_w){
					isBcYc=false;
				}else if(checkedId==R.id.rb_bc_y){
					isBcYc=true;
				}
			}
		});
		
		rg_xtsc.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_xtsc_w){
					isXtjcYc=false;
				}else if(checkedId==R.id.rb_xtsc_y){
					isXtjcYc=true;
				}
			}
		});
	}
	@Override
	public void getData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
		followUpTwoToFivePregnancy.setQtjc_sfbc(isBcYc);
		followUpTwoToFivePregnancy.setQtjc_sfbcms(et_bc.getText().toString());
		followUpTwoToFivePregnancy.setQtjc_sfxtsc(isXtjcYc);
		followUpTwoToFivePregnancy.setQtjc_sfxtscms(et_xtsc.getText().toString());

	}

	@Override
	public void setData(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
		if(followUpTwoToFivePregnancy!=null){
			et_bc.setText(followUpTwoToFivePregnancy.getQtjc_sfbcms());
			et_xtsc.setText(followUpTwoToFivePregnancy.getQtjc_sfxtscms());
			
			if(followUpTwoToFivePregnancy.getQtjc_sfbc()){
				rb_bc_y.setChecked(true);
				rb_bc_w.setChecked(false);
			}else{
				rb_bc_y.setChecked(false);
				rb_bc_w.setChecked(true);
			}
			if(followUpTwoToFivePregnancy.getQtjc_sfxtsc()){
				rb_xtsc_y.setChecked(true);
				rb_xtsc_w.setChecked(false);
			}else{
				rb_xtsc_y.setChecked(false);
				rb_xtsc_w.setChecked(true);
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
