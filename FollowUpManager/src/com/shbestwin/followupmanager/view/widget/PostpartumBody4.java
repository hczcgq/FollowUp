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
import com.shbestwin.followupmanager.model.followup.FimalyHistory;
import com.shbestwin.followupmanager.model.followup.FollowUpPostpartum;
import com.shbestwin.followupmanager.model.followup.PostpartumLeft;
import com.shbestwin.followupmanager.model.followup.PostpartumRight;

public class PostpartumBody4 extends LinearLayout implements
		IBasePostpartumBody {

	private RelativeLayout PostpartumLeftLayout, PostpartumRightLayout;
	private CheckBox PostpartumLeftOther, PostpartumRightOther;
	private CheckBox PostpartumLeftNone, PostpartumRightNone;
	private EditText PostpartumLeftEdittext, postpartumRightEditText;
	private boolean isPastHistoryLeft=false,isPastHistoryRight=false;

	public PostpartumBody4(Context context) {
		this(context, null);
	}

	public PostpartumBody4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PostpartumBody4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_postpartum_body4, this, true);

		PostpartumLeftLayout = (RelativeLayout) rootView
				.findViewById(R.id.PostpartumLeftLayout);
		PostpartumRightLayout = (RelativeLayout) rootView
				.findViewById(R.id.PostpartumRightLayout);
		PostpartumLeftOther = (CheckBox) rootView
				.findViewById(R.id.PostpartumLeftOther);
		PostpartumRightOther = (CheckBox) rootView
				.findViewById(R.id.PostpartumRightOther);
		PostpartumLeftEdittext = (EditText) rootView
				.findViewById(R.id.PostpartumLeftEdittext);
		postpartumRightEditText = (EditText) rootView
				.findViewById(R.id.postpartumRightEditText);
		PostpartumLeftNone = (CheckBox) rootView
				.findViewById(R.id.PostpartumLeftNone);
		PostpartumRightNone = (CheckBox) rootView
				.findViewById(R.id.PostpartumRightNone);

		ViewDataUtil.initOtherCheckboxConstraint(PostpartumLeftOther,
				PostpartumLeftEdittext);
		ViewDataUtil.initOtherCheckboxConstraint(PostpartumRightOther,
				postpartumRightEditText);
		
//		PostpartumLeftNone
//				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//					@Override
//					public void onCheckedChanged(CompoundButton buttonView,
//							boolean isChecked) {
//						
//						isPastHistoryLeft = !isChecked;
//						setCheckBoxStatus(PostpartumLeftLayout, isChecked);
//					}
//				});
//		setCheckBoxStatus(PostpartumLeftLayout, true);
//
//		PostpartumRightNone
//				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//					@Override
//					public void onCheckedChanged(CompoundButton buttonView,
//							boolean isChecked) {
//						isPastHistoryRight= !isChecked;
//						setCheckBoxStatus(PostpartumRightLayout, isChecked);
//					}
//				});
//		setCheckBoxStatus(PostpartumRightLayout, true);

	}

	@Override
	public void getData(FollowUpPostpartum followUpPostpartum) {
		followUpPostpartum.setRfjc_zcrx(getLeftCheckBoxText());
		followUpPostpartum.setRfjc_ycrx(getRightCheckBoxText());
	}

	private String getLeftCheckBoxText() {
		List<PostpartumLeft> allergyHistoriesList = new ArrayList<PostpartumLeft>();
		for (int i = 0; i < PostpartumLeftLayout.getChildCount(); i++) {
			View item = PostpartumLeftLayout.getChildAt(i);
			PostpartumLeft allergyHistories = new PostpartumLeft();
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					allergyHistories.setRfjc_zcrx_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText())) {
						allergyHistories
								.setRfjc_zcrx_name(PostpartumLeftEdittext
										.getText().toString());
					} else {
						allergyHistories.setRfjc_zcrx_name(checkBox.getText()
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

	private String getRightCheckBoxText() {
		List<PostpartumRight> allergyHistoriesList = new ArrayList<PostpartumRight>();
		for (int i = 0; i < PostpartumRightLayout.getChildCount(); i++) {
			View item = PostpartumRightLayout.getChildAt(i);
			PostpartumRight allergyHistories = new PostpartumRight();
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					allergyHistories.setRfjc_ycrx_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText())) {
						allergyHistories
								.setRfjc_ycrx_name(postpartumRightEditText
										.getText().toString());
					} else {
						allergyHistories.setRfjc_ycrx_name(checkBox.getText()
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
	public void setData(FollowUpPostpartum followUpPostpartum) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
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
