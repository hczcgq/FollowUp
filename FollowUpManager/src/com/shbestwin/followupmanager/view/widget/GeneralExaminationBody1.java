package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody1 extends LinearLayout implements
		IBaseGeneralExaminationBody {
	private RelativeLayout symptomLayout;
	private EditText otherSymptomEditText;
	private CheckBox noneSymptomCheckBox, otherSymptomCheckBox;
	private boolean isSysmptomNone=false;

	public GeneralExaminationBody1(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody1(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_general_examination_body1, this, true);
		symptomLayout = (RelativeLayout) rootView
				.findViewById(R.id.symptomLayout);
		noneSymptomCheckBox = (CheckBox) rootView.findViewById(R.id.symptom0);
		otherSymptomCheckBox = (CheckBox) rootView.findViewById(R.id.symptom24);
		otherSymptomEditText = (EditText) rootView
				.findViewById(R.id.otherSymptomEditText);
		ViewDataUtil.initOtherCheckboxConstraint(otherSymptomCheckBox,
				otherSymptomEditText);

		noneSymptomCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						isSysmptomNone = !isChecked;
						setCheckBoxStatus(symptomLayout, isChecked);
					}
				});
		setCheckBoxStatus(symptomLayout, true);

	}

	@Override
	public void getData(GeneralExamination generalExamination) {
		generalExamination.setZz(ViewDataUtil.getCheckboxData(symptomLayout,
				otherSymptomEditText));
	}

	@Override
	public void setData(GeneralExamination generalExamination) {
		ViewDataUtil.setCheckboxData(symptomLayout, otherSymptomEditText,
				generalExamination.getZz());
	}

	@Override
	public boolean validate() {
		if (!ViewDataUtil.validateOtherCheckbox(otherSymptomCheckBox,
				otherSymptomEditText)) {
			ToastUtils.showToast(getContext(), "请输入其他症状信息！");
			return false;
		}
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

}
