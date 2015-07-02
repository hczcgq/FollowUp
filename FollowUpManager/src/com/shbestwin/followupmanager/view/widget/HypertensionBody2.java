package com.shbestwin.followupmanager.view.widget;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;

public class HypertensionBody2 extends LinearLayout implements
		IBaseHypertensionBody {
	private EditText hypertension_other;
	private RelativeLayout hypertensionRelativeLayout;
	private CheckBox  hypertension0,hypertension13;
	

	public HypertensionBody2(Context context) {
		this(context, null);
	}

	public HypertensionBody2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_hypertension_body2, this, true);
		
		hypertensionRelativeLayout=(RelativeLayout) rootView.findViewById(R.id.hypertensionRelativeLayout);
		hypertension_other=(EditText) rootView.findViewById(R.id.et_other);
		hypertension0 = (CheckBox) rootView.findViewById(R.id.TOD0);
		hypertension13 = (CheckBox) rootView.findViewById(R.id.TOD13);
		ViewDataUtil.initOtherCheckboxConstraint(hypertension13,
				hypertension_other);

		hypertension0
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						setCheckBoxStatus(hypertensionRelativeLayout, isChecked);
					}
				});
		setCheckBoxStatus(hypertensionRelativeLayout, true);
	}

	@Override
	public void getData(FollowUpHypertension followUpHypertension) {
		followUpHypertension.setZz(ViewDataUtil.getCheckboxData(hypertensionRelativeLayout,
				hypertension_other));
	}

	@Override
	public void setData(FollowUpHypertension followUpHypertension) {
	    if(followUpHypertension!=null) {
	        ViewDataUtil.setCheckboxData(hypertensionRelativeLayout, hypertension_other, followUpHypertension.getZz());
	    }
	}

	@Override
	public boolean validate() {
		return true;
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
	public void setFragment(FragmentManager fragmentManager) {
	}

}
