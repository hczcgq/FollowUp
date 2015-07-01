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
import android.widget.RelativeLayout;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;
import com.shbestwin.followupmanager.model.followup.SelfHistory;

public class Antenatal1Body7 extends LinearLayout implements
		IBaseAntenatal1Body {
	private EditText selfHistoryEdittext;
	private CheckBox selfHistoryOther;
	private RelativeLayout selfHistoryLayout;

	public Antenatal1Body7(Context context) {
		this(context, null);
	}

	public Antenatal1Body7(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body7(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_antenatal1_body7, this, true);
		selfHistoryEdittext = (EditText) rootView
				.findViewById(R.id.selfHistoryEdittext);
		selfHistoryOther = (CheckBox) rootView
				.findViewById(R.id.selfHistoryOther);
		selfHistoryLayout = (RelativeLayout) rootView
				.findViewById(R.id.selfHistoryLayout);
		ViewDataUtil.initOtherCheckboxConstraint(selfHistoryOther,
				selfHistoryEdittext);
	}

	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {

		followUpFirstPregnancy.setGrs(getCheckBoxText());

	}

	private String getCheckBoxText() {
		List<SelfHistory> allergyHistoriesList = new ArrayList<SelfHistory>();
		for (int i = 0; i < selfHistoryLayout.getChildCount(); i++) {
			View item = selfHistoryLayout.getChildAt(i);
			SelfHistory allergyHistories = new SelfHistory();
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					allergyHistories.setGrs_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText())) {
						allergyHistories.setGrs_name(selfHistoryEdittext
								.getText().toString());
					} else {
						allergyHistories.setGrs_name(checkBox.getText()
								.toString());
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
		if (!ViewDataUtil.validateOtherCheckbox(selfHistoryOther,
				selfHistoryEdittext)) {
			ToastUtils.showToast(getContext(), "请输入其他症状信息！");
			return false;
		}
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub

	}
}
