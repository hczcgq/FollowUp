package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;

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
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;
import com.shbestwin.followupmanager.model.followup.PastHistory;

public class Antenatal1Body4 extends LinearLayout implements
		IBaseAntenatal1Body {
	private RelativeLayout pastHistoryLayout;
	private EditText pastHistoryEditTextOther;
	private CheckBox pastHistoryNone,pastHistoryOther;
	private boolean isPastHistory=false;

	public Antenatal1Body4(Context context) {
		this(context, null);
	}

	public Antenatal1Body4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_antenatal1_body4, this, true);
		pastHistoryLayout=(RelativeLayout) rootView.findViewById(R.id.pastHistoryLayout);
		pastHistoryEditTextOther = (EditText) rootView.findViewById(R.id.pastHistoryEditTextOther);
		pastHistoryNone = (CheckBox) rootView.findViewById(R.id.pastHistoryNone);
		pastHistoryOther = (CheckBox) rootView.findViewById(R.id.pastHistoryOther);
		
		ViewDataUtil.initOtherCheckboxConstraint(pastHistoryOther,
				pastHistoryEditTextOther);

		pastHistoryNone
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						isPastHistory = !isChecked;
						setCheckBoxStatus(pastHistoryLayout, isChecked);
					}
				});
		setCheckBoxStatus(pastHistoryLayout, true);


	}

	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		followUpFirstPregnancy.setJws(getCheckBoxText());

	}
	
	private String getCheckBoxText() {
		List<PastHistory> allergyHistoriesList = new ArrayList<PastHistory>();
		for (int i = 0; i < pastHistoryLayout.getChildCount(); i++) {
			View item = pastHistoryLayout.getChildAt(i);
			PastHistory allergyHistories = new PastHistory();
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					allergyHistories.setJws_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText())) {
						allergyHistories.setJws_name(pastHistoryEditTextOther.getText().toString());
					} else {
						allergyHistories.setJws_name(checkBox.getText().toString());
					}
					allergyHistoriesList.add(allergyHistories);
				}
				
			}
			
		}
		try {
			return JsonUtil.objectsToJson(allergyHistoriesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validate() {
		if (!ViewDataUtil.validateOtherCheckbox(pastHistoryOther,
				pastHistoryEditTextOther)) {
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

	@Override
	public void setFragment(FragmentManager fragmentManager) {
	}
}
