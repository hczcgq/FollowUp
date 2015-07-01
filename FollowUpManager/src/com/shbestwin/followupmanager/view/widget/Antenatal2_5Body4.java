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
import com.shbestwin.followupmanager.model.followup.FollowUpTwoToFivePregnancy;

public class Antenatal2_5Body4 extends LinearLayout  implements IBaseAntenatal2_5Body{
	private RadioGroup rg_bc,rg_xtsc;
	private EditText et_bc,et_xtsc;
	private boolean isBcYc=false,isXtjcYc=false;
	
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
