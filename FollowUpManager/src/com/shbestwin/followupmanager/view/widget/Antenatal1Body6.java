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
import com.shbestwin.followupmanager.model.followup.FimalyHistoryCa;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;

public class Antenatal1Body6 extends LinearLayout  implements IBaseAntenatal1Body {
	private RelativeLayout familyHistoryCategoryLayout;
	private CheckBox familyHistoryCategoryNone ,familyHistoryCategoryOther;
	private EditText familyHistoryCategoryEditext;
	private boolean isPastHistory=false;
	public Antenatal1Body6(Context context) {
		this(context, null);
	}

	public Antenatal1Body6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body6, this, true);
		familyHistoryCategoryLayout = (RelativeLayout) rootView.findViewById(R.id.familyHistoryCategoryLayout);
		familyHistoryCategoryNone = (CheckBox) rootView.findViewById(R.id.familyHistoryCategoryNone);
		familyHistoryCategoryOther = (CheckBox) rootView.findViewById(R.id.familyHistoryCategoryOther);
		familyHistoryCategoryEditext = (EditText) rootView.findViewById(R.id.familyHistoryCategoryEditext);
		ViewDataUtil.initOtherCheckboxConstraint(familyHistoryCategoryOther,
				familyHistoryCategoryEditext);

		familyHistoryCategoryNone
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						isPastHistory = !isChecked;
						setCheckBoxStatus(familyHistoryCategoryLayout, isChecked);
					}
				});
		setCheckBoxStatus(familyHistoryCategoryLayout, true);
	}
	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		followUpFirstPregnancy.setJzszl(getCheckBoxText());
	}
	
	private String getCheckBoxText() {
		List<FimalyHistoryCa> allergyHistoriesList = new ArrayList<FimalyHistoryCa>();
		for (int i = 0; i < familyHistoryCategoryLayout.getChildCount(); i++) {
			View item = familyHistoryCategoryLayout.getChildAt(i);
			FimalyHistoryCa allergyHistories = new FimalyHistoryCa();
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					allergyHistories.setJzszl_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText())) {
						allergyHistories.setJzszl_name(familyHistoryCategoryEditext.getText().toString());
					} else {
						allergyHistories.setJzszl_name(checkBox.getText().toString());
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
		if (!ViewDataUtil.validateOtherCheckbox(familyHistoryCategoryOther,
				familyHistoryCategoryEditext)) {
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
		// TODO Auto-generated method stub
		
	}
}