package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;

public class MentalDiseaseBody3 extends LinearLayout  implements IBaseMentalDiseaseBody{
	private Spinner sn_smqk,sn_ysqk;
	private RadioGroup rg_zzl;
	private String zzl="自知力完全";
	
	private EditText symptom_other;
	private RelativeLayout symptomRelativeLayout;
	private CheckBox symptom11;
	public MentalDiseaseBody3(Context context) {
		this(context, null);
	}

	public MentalDiseaseBody3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MentalDiseaseBody3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_mental_disease_body3, this, true);
		sn_smqk=(Spinner) rootView.findViewById(R.id.sn_smqk);
		sn_ysqk=(Spinner) rootView.findViewById(R.id.sn_ysqk);
		rg_zzl=(RadioGroup) rootView.findViewById(R.id.rg_zzl);
		
		rg_zzl.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_wq){
						zzl="自知力完全";
				}else if(checkedId==R.id.rb_bwq){
					zzl="自知力不全";
				}else if(checkedId==R.id.rb_qs){
					zzl="自知力缺失";
				}
			}
		});
		
		
		symptomRelativeLayout=(RelativeLayout) rootView.findViewById(R.id.symptomRelativeLayout);
		symptom_other=(EditText) rootView.findViewById(R.id.et_other);
		symptom11 = (CheckBox) rootView.findViewById(R.id.symptom11);
		ViewDataUtil.initOtherCheckboxConstraint(symptom11,
				symptom_other);

		
		
		
	}
	@Override
	public void getData(FollowUpMentalDisease followUpMentalDisease) {
		
		followUpMentalDisease.setZz_zzmc(ViewDataUtil.getCheckboxData(symptomRelativeLayout,
				symptom_other));
		followUpMentalDisease.setZz_zzl(zzl);
		followUpMentalDisease.setZz_smqk(ViewDataUtil.getSpinnerData(sn_smqk));
		followUpMentalDisease.setZz_ysqk(ViewDataUtil.getSpinnerData(sn_ysqk));
		
	}

	@Override
	public void setData(FollowUpMentalDisease followUpMentalDisease) {
		// symptomO Auto-generated method stub
		
	}

	@Override
	public boolean validate() {
		// symptomO Auto-generated method stub
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		// symptomO Auto-generated method stub
		
	}
}
