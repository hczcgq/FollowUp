package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.CheckBoxItem;
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;

public class YearsOld3_6Body8 extends LinearLayout implements
		IBaseYearsOld3_6Body {
	private EditText et_other;
	private CheckBox diagnose0, diagnose4;
	private LinearLayout diagnoseLayout;

	public YearsOld3_6Body8(Context context) {
		this(context, null);
	}

	public YearsOld3_6Body8(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld3_6Body8(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_years_old_3_6_body8, this, true);
		et_other = (EditText) rootView.findViewById(R.id.et_other);
		diagnose0 = (CheckBox) rootView.findViewById(R.id.diagnose0);
		diagnoseLayout = (LinearLayout) rootView
				.findViewById(R.id.diagnoseLayout);
		diagnose4 = (CheckBox) rootView.findViewById(R.id.diagnose4);
		ViewDataUtil.initOtherCheckboxConstraint(diagnose4, et_other);
		diagnose0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				setCheckBoxStatus(diagnoseLayout, isChecked);
			}
		});
	}

	@Override
	public void getData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {

		followUpThreeSixNewborn.setJbzd(getCheckBoxText(diagnoseLayout,
				et_other));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		if (followUpThreeSixNewborn != null) {
			try {
				setCheckBoxText(diagnoseLayout, et_other,
						JsonUtil.jsonToObjects(
								followUpThreeSixNewborn.getJbzd(),
								CheckBoxItem.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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

	private String getCheckBoxText(View layout, EditText editText) {
		LinearLayout linearLayout = (LinearLayout) layout;
		List<CheckBoxItem> mArrayList = new ArrayList<CheckBoxItem>();
		for (int i = 0; i < linearLayout.getChildCount(); i++) {
			View item = linearLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					CheckBoxItem entity = new CheckBoxItem();
					entity.setItem_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText()) && editText != null) {
						entity.setItem_name(editText.getText().toString());
					} else {
						entity.setItem_name(checkBox.getText().toString());
					}
					mArrayList.add(entity);
				}
			}
		}
		try {
			return JsonUtil.objectsToJson(mArrayList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private void setCheckBoxText(View layout, EditText textview,
			List<CheckBoxItem> mList) {
		LinearLayout myLayout = (LinearLayout) layout;
		for (int i = 0; i < mList.size(); i++) {
			int Num = Integer.valueOf(mList.get(i).getItem_num());
			String name = mList.get(i).getItem_name();
			if ((View) (myLayout).getChildAt(Num) instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) (View) (myLayout)
						.getChildAt(Num);
				checkBox.setChecked(true);
				if (Num == myLayout.getChildCount() - 2) {
					textview.setText(name);
				}
			}
		}
	}

	private void setCheckBoxStatus(LinearLayout familyHistory, boolean isChecked) {
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
