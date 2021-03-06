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
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.CheckBoxItem;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;

public class AgednessBody2 extends LinearLayout implements IBaseAgednessBody {

	private RelativeLayout symptomLayout;
	private CheckBox symptomNone, symptomOther;
	private EditText symptomEdittext;

	public AgednessBody2(Context context) {
		this(context, null);
	}

	public AgednessBody2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AgednessBody2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_agedness_body2, this, true);
		symptomLayout = (RelativeLayout) rootView
				.findViewById(R.id.symptomLayout);
		symptomNone = (CheckBox) rootView.findViewById(R.id.symptomNone);
		symptomOther = (CheckBox) rootView.findViewById(R.id.symptomOther);
		symptomEdittext = (EditText) rootView
				.findViewById(R.id.symptomEdittext);
		ViewDataUtil.initOtherCheckboxConstraint(symptomOther, symptomEdittext);

		symptomNone.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				setCheckBoxStatus(symptomLayout, isChecked);
			}
		});

	}

	@Override
	public void getData(FollowUpAged followUpAged) {
		followUpAged.setZz(getCheckBoxText(symptomLayout, symptomEdittext));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(FollowUpAged followUpAged) {
		if (followUpAged != null) {
			try {
				setCheckBoxText(symptomLayout, symptomEdittext,
						JsonUtil.jsonToObjects(followUpAged.getZz(),
								CheckBoxItem.class));
			} catch (Exception e) {
				e.printStackTrace();
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

	private String getCheckBoxText(View layout, EditText editText) {
		RelativeLayout relativeLayout = (RelativeLayout) layout;
		List<CheckBoxItem> mArrayList = new ArrayList<CheckBoxItem>();
		for (int i = 0; i < relativeLayout.getChildCount(); i++) {
			View item = relativeLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					CheckBoxItem entity = new CheckBoxItem();
					entity.setItem_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText())
							&& symptomEdittext != null) {
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
		RelativeLayout myLayout = (RelativeLayout) layout;
		for (int i = 0; i < mList.size(); i++) {
			int Num = Integer.parseInt(mList.get(i).getItem_num());
			String name = mList.get(i).getItem_name();
			if (myLayout.getChildAt(Num) instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) myLayout.getChildAt(Num);
				checkBox.setChecked(true);
				if (Num == myLayout.getChildCount() - 2) {
					textview.setText(name);
				}
			}
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

}
