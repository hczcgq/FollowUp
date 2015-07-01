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
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;

public class NeonateBody2 extends LinearLayout  implements IBaseNeonateBody{
	private Spinner sn_wyfs;
	private EditText et_cnl,et_cncs;
	private RadioGroup rg_ot;
	private boolean isHas=false;
	public NeonateBody2(Context context) {
		this(context, null);
	}

	public NeonateBody2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NeonateBody2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_neonate_body2, this, true);
		sn_wyfs=(Spinner) rootView.findViewById(R.id.sn_wyfs);
		et_cnl=(EditText) rootView.findViewById(R.id.et_cnl);
		et_cncs=(EditText) rootView.findViewById(R.id.et_cncs);
		rg_ot=(RadioGroup) rootView.findViewById(R.id.rg_ot);
		
		rg_ot.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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

		followUpNewborn.setWyqk_sfot(isHas);
		followUpNewborn.setWyqk_wyfs(ViewDataUtil.getSpinnerData(sn_wyfs));
		followUpNewborn.setWyqk_cnl(et_cnl.getText().toString());
		followUpNewborn.setWyqk_cncs(et_cncs.getText().toString());
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
