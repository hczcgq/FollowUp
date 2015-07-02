package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;

public class DiabetesMellitusBody2 extends LinearLayout implements  IBaseDiabetesMellitusBody{
	private EditText diabetesMellitus_other;
	private RelativeLayout diabetesMellitusRelativeLayout;
	private CheckBox  diabetesMellitus0,diabetesMellitus14;

	public DiabetesMellitusBody2(Context context) {
		this(context, null);
	}

	public DiabetesMellitusBody2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DiabetesMellitusBody2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_diabetes_mellitus_body2, this, true);
		
		diabetesMellitusRelativeLayout=(RelativeLayout) rootView.findViewById(R.id.diabetesMellitusRelativeLayout);
		diabetesMellitus_other=(EditText) rootView.findViewById(R.id.et_other);
		diabetesMellitus0 = (CheckBox) rootView.findViewById(R.id.TOD0);
		diabetesMellitus14 = (CheckBox) rootView.findViewById(R.id.TOD14);
		ViewDataUtil.initOtherCheckboxConstraint(diabetesMellitus14,
				diabetesMellitus_other);

		diabetesMellitus0
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						setCheckBoxStatus(diabetesMellitusRelativeLayout, isChecked);
					}
				});
		setCheckBoxStatus(diabetesMellitusRelativeLayout, true);
	}


	@Override
	public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		followUpDiabetesMellitus.setZz(ViewDataUtil.getCheckboxData(diabetesMellitusRelativeLayout,
				diabetesMellitus_other));
	}

	@Override
	public void setData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
	    if(followUpDiabetesMellitus!=null) {
	        ViewDataUtil.setCheckboxData(diabetesMellitusRelativeLayout, diabetesMellitus_other, followUpDiabetesMellitus.getZz());
	    }
	}

	private void setCheckBoxStatus(RelativeLayout familyHistory,
			boolean isChecked) {
		for (int i = 1; i < familyHistory.getChildCount(); i++) {
			View item = familyHistory.getChildAt(i);
			if (item instanceof CheckBox) {
				((CheckBox) item).setEnabled(!isChecked);
				if (isChecked) {
					((CheckBox) item).setChecked(!isChecked);
				}
			}
		}
	}
	
	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
	}

}
