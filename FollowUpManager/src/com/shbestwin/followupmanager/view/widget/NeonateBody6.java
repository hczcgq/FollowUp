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
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;

public class NeonateBody6 extends LinearLayout  implements IBaseNeonateBody{
	private RadioGroup rg_zz;
	private EditText et_yy,et_jkjks;
	private boolean isHas=false;
	public NeonateBody6(Context context) {
		this(context, null);
	}

	public NeonateBody6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NeonateBody6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_neonate_body6, this, true);
		rg_zz=(RadioGroup) rootView.findViewById(R.id.rg_zz);
		et_yy=(EditText) rootView.findViewById(R.id.et_yy);
		et_jkjks=(EditText) rootView.findViewById(R.id.et_jgjks);
		
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
	public void getData(FollowUpNewborn followUpNewborn) {

		followUpNewborn.setZzjy_sfzz(isHas);
		followUpNewborn.setZzjy_yy(et_yy.getText().toString());
		followUpNewborn.setZzjy_jgjks(et_jkjks.getText().toString());
	}

	@Override
	public void setData(FollowUpNewborn followUpNewborn) {
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
