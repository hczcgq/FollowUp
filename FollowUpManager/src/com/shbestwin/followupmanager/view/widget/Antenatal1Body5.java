package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FimalyHistory;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;

public class Antenatal1Body5 extends LinearLayout  implements IBaseAntenatal1Body {
	private LinearLayout familyHistoryLayout;
	private EditText familyHistoryEditTextOther;
	private CheckBox familyHistoryOther;
	public Antenatal1Body5(Context context) {
		this(context, null);
	}

	public Antenatal1Body5(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body5(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body5, this, true);
		familyHistoryLayout = (LinearLayout) rootView.findViewById(R.id.familyHistoryLayout);
		familyHistoryOther = (CheckBox) rootView.findViewById(R.id.familyHistoryOther);
		familyHistoryEditTextOther = (EditText) rootView.findViewById(R.id.familyHistoryEditTextOther);
		ViewDataUtil.initOtherCheckboxConstraint(familyHistoryOther,
				familyHistoryEditTextOther);
		
		
	}
	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		System.out.println(getCheckBoxText());
		followUpFirstPregnancy.setJzslb(getCheckBoxText());
	}
	
	private String getCheckBoxText() {
		List<FimalyHistory> allergyHistoriesList = new ArrayList<FimalyHistory>();
		for (int i = 0; i < familyHistoryLayout.getChildCount(); i++) {
			View item = familyHistoryLayout.getChildAt(i);
			FimalyHistory allergyHistories = new FimalyHistory();
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					allergyHistories.setJzslb_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText())) {
						allergyHistories.setJzslb_name(familyHistoryEditTextOther.getText().toString());
					} else {
						allergyHistories.setJzslb_name(checkBox.getText().toString());
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

	}

	@Override
	public boolean validate() {
		if (!ViewDataUtil.validateOtherCheckbox(familyHistoryOther,
				familyHistoryEditTextOther)) {
			ToastUtils.showToast(getContext(), "请输入其他症状信息！");
			return false;
		}
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		
	}
}
