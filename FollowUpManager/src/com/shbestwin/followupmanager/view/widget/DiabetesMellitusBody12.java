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
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;

public class DiabetesMellitusBody12 extends LinearLayout  implements IBaseDiabetesMellitusBody{
	private RadioGroup rg_check;
	private EditText et_reason,et_jgjks;
	private boolean isHas=false;
	public DiabetesMellitusBody12(Context context) {
		this(context, null);
	}

	public DiabetesMellitusBody12(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DiabetesMellitusBody12(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_diabetes_mellitus_body12, this, true);
		rg_check=(RadioGroup) rootView.findViewById(R.id.rg_check);
		et_reason=(EditText) rootView.findViewById(R.id.et_reason);
		et_jgjks=(EditText) rootView.findViewById(R.id.et_jgjks);
		rg_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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
	public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {

		followUpDiabetesMellitus.setZz_sfzz(isHas);
		followUpDiabetesMellitus.setZz_jgjks(et_jgjks.getText().toString());
		followUpDiabetesMellitus.setZz_sfzzms(et_reason.getText().toString());
	}

	@Override
	public void setData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
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
