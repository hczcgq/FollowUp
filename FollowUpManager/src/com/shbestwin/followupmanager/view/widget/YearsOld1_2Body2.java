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
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;

public class YearsOld1_2Body2 extends LinearLayout  implements IBaseYearsOld1_2Body{
	private RadioGroup rg_mrwy;
	private EditText et_mrwy,et_zccs,et_nnnf,et_d,et_fywssd;
	private Spinner sn_rlhyx,sn_sghsc,sn_sy;
	private boolean isHas=false;
	public YearsOld1_2Body2(Context context) {
		this(context, null);
	}

	public YearsOld1_2Body2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld1_2Body2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_1_2_body2, this, true);
		rg_mrwy=(RadioGroup) rootView.findViewById(R.id.rg_mrwy);
		et_mrwy=(EditText) rootView.findViewById(R.id.et_mrwy);
		et_zccs=(EditText) rootView.findViewById(R.id.et_zccs);
		et_nnnf=(EditText) rootView.findViewById(R.id.et_nnnf);
		et_d=(EditText) rootView.findViewById(R.id.et_d);
		et_fywssd=(EditText) rootView.findViewById(R.id.et_fywssd);
		sn_rlhyx=(Spinner) rootView.findViewById(R.id.sn_rlhyx);
		sn_sghsc=(Spinner) rootView.findViewById(R.id.sn_sghsc);
		sn_sy=(Spinner) rootView.findViewById(R.id.sn_sy);
		
		rg_mrwy.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_f){
					isHas=false;
				}else if(checkedId==R.id.rb_s){
					isHas=true;
				}
			}
		});
	}
	@Override
	public void getData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		followUpOneTwoNewborn.setWyqk_sfmryy(isHas);
		followUpOneTwoNewborn.setWyqk_sfmryyms(et_mrwy.getText().toString());
		followUpOneTwoNewborn.setWyqk_zccs(et_zccs.getText().toString());
		followUpOneTwoNewborn.setWyqk_nnnf(et_nnnf.getText().toString());
		followUpOneTwoNewborn.setWyqk_d(et_d.getText().toString());
		followUpOneTwoNewborn.setWyqk_fywssd(et_fywssd.getText().toString());
		followUpOneTwoNewborn.setWyqk_rlhyx(ViewDataUtil.getSpinnerData(sn_rlhyx));
		followUpOneTwoNewborn.setWyqk_sghsc(ViewDataUtil.getSpinnerData(sn_sghsc));
		followUpOneTwoNewborn.setWyqk_sy(ViewDataUtil.getSpinnerData(sn_sy));
	}

	@Override
	public void setData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
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
