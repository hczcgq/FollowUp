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
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;

public class NeonateBody3 extends LinearLayout  implements IBaseNeonateBody{
	private EditText et_dbxz,et_dbcs,et_dbys;
	private CheckBox stoolProperty2;
	private CheckBox stoolColor3,sleepProblems0;
	private LinearLayout stoolPropertyLayout,stoolColorLayout,sleepProblemsLayout;
	public NeonateBody3(Context context) {
		this(context, null);
	}

	public NeonateBody3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NeonateBody3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_neonate_body3, this, true);
		
		et_dbxz = (EditText) rootView.findViewById(R.id.et_dbxz);
		et_dbcs = (EditText) rootView.findViewById(R.id.et_dbcs);
		et_dbys = (EditText) rootView.findViewById(R.id.et_dbys);
		
		stoolPropertyLayout = (LinearLayout) rootView.findViewById(R.id.stoolPropertyLayout);
		stoolColorLayout = (LinearLayout) rootView.findViewById(R.id.stoolColorLayout);
		sleepProblemsLayout = (LinearLayout) rootView.findViewById(R.id.sleepProblemsLayout);
		stoolProperty2 = (CheckBox) rootView.findViewById(R.id.stoolProperty2);
		stoolColor3 = (CheckBox) rootView.findViewById(R.id.stoolColor3);
		sleepProblems0 = (CheckBox) rootView.findViewById(R.id.sleepProblems0);
		ViewDataUtil.initOtherCheckboxConstraint(stoolProperty2, et_dbxz);
		ViewDataUtil.initOtherCheckboxConstraint(stoolColor3, et_dbys);
		sleepProblems0
		.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				setCheckBoxStatus(sleepProblemsLayout, isChecked);
			}
		});
		
	}
	@Override
	public void getData(FollowUpNewborn followUpNewborn) {
		followUpNewborn.setJbstzk_dbxz(getCheckBoxText(stoolPropertyLayout, et_dbxz));
		followUpNewborn.setJbstzk_dbcs(et_dbcs.getText().toString());
		followUpNewborn.setJbstzk_dbys(getCheckBoxText(stoolColorLayout, et_dbys));
		followUpNewborn.setJbstzk_smwt(getCheckBoxText(sleepProblemsLayout, null));
	}

	@Override
	public void setData(FollowUpNewborn followUpNewborn) {
		if(followUpNewborn!=null){
			et_dbcs.setText(followUpNewborn.getJbstzk_dbcs());
			try {
				setCheckBoxText(stoolPropertyLayout, et_dbxz,
						JsonUtil.jsonToObjects(
								followUpNewborn.getJbstzk_dbxz(),
								CheckBoxItem.class));
				setCheckBoxText(stoolColorLayout, et_dbys,
						JsonUtil.jsonToObjects(
								followUpNewborn.getJbstzk_dbys(),
								CheckBoxItem.class));
				setCheckBoxText(sleepProblemsLayout, null,
						JsonUtil.jsonToObjects(
								followUpNewborn.getJbstzk_smwt(),
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
				if (textview != null) {
					if (Num == myLayout.getChildCount() - 2) {
						textview.setText(name);
					}
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
