package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;

public class YearsOld3_6Body6 extends LinearLayout implements
		IBaseYearsOld3_6Body {
	private EditText et_fy, et_fx, et_ws, et_qt;
	private RelativeLayout relativeLayout;
	private CheckBox illness0;

	public YearsOld3_6Body6(Context context) {
		this(context, null);
	}

	public YearsOld3_6Body6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld3_6Body6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_years_old_3_6_body6, this, true);
		relativeLayout = (RelativeLayout) rootView
				.findViewById(R.id.relativeLayout);
		illness0 = (CheckBox) rootView.findViewById(R.id.illness0);
		et_fy = (EditText) rootView.findViewById(R.id.et_fy);
		et_fx = (EditText) rootView.findViewById(R.id.et_fx);
		et_ws = (EditText) rootView.findViewById(R.id.et_ws);
		et_qt = (EditText) rootView.findViewById(R.id.et_qt);

		// ViewDataUtil.initOtherCheckboxConstraint(symptomOther,
		// symptomEdittext);

		illness0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				setCheckBoxStatus(relativeLayout, isChecked);
			}
		});

	}

	@Override
	public void getData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		followUpThreeSixNewborn.setHbqk(ViewDataUtil.getCheckboxData(
				relativeLayout, null));
		followUpThreeSixNewborn.setHbqk_fy(et_fy.getText().toString());
		followUpThreeSixNewborn.setHbqk_fx(et_fx.getText().toString());
		followUpThreeSixNewborn.setHbqk_ws(et_ws.getText().toString());
		followUpThreeSixNewborn.setHbqk_qt(et_qt.getText().toString());
	}

	@Override
	public void setData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		if (followUpThreeSixNewborn != null) {
			ViewDataUtil.setCheckboxData(relativeLayout, null,
					followUpThreeSixNewborn.getHbqk());
			et_fx.setText(followUpThreeSixNewborn.getHbqk_fx());
			et_fy.setText(followUpThreeSixNewborn.getHbqk_fy());
			et_ws.setText(followUpThreeSixNewborn.getHbqk_ws());
			et_qt.setText(followUpThreeSixNewborn.getHbqk_qt());
		}
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
	}

	private void setCheckBoxStatus(RelativeLayout familyHistory,
			boolean isChecked) {
		for (int i = 2; i < familyHistory.getChildCount(); i++) {
			View item = familyHistory.getChildAt(i);
			if (item instanceof CheckBox) {
				((CheckBox) item).setEnabled(!isChecked);
				if (isChecked) {
					((CheckBox) item).setChecked(!isChecked);
				}
			}
		}
	}

}
