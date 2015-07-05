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
import com.shbestwin.followupmanager.model.followup.FollowUpPostpartum;

public class PostpartumBody4 extends LinearLayout implements
		IBasePostpartumBody {

	private RelativeLayout PostpartumLeftLayout, PostpartumRightLayout;
	private CheckBox PostpartumLeftOther, PostpartumRightOther;
	private CheckBox PostpartumLeftNone, PostpartumRightNone;
	private EditText PostpartumLeftEdittext, postpartumRightEditText;

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
		
		PostpartumLeftNone
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						setCheckBoxStatus(PostpartumLeftLayout, isChecked);
					}
				});
		setCheckBoxStatus(PostpartumLeftLayout, true);

		PostpartumRightNone
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						setCheckBoxStatus(PostpartumRightLayout, isChecked);
					}
				});
		setCheckBoxStatus(PostpartumRightLayout, true);

	}

	@Override
	public void getData(FollowUpPostpartum followUpPostpartum) {
		followUpPostpartum.setRfjc_zcrx(getLeftCheckBoxText(PostpartumLeftLayout,
				PostpartumLeftEdittext));
		followUpPostpartum.setRfjc_ycrx(getLeftCheckBoxText(
				PostpartumRightLayout, postpartumRightEditText));
	}

	private String getLeftCheckBoxText(View layout, EditText editText) {
		RelativeLayout relativeLayout = (RelativeLayout) layout;
		List<CheckBoxItem> allergyHistoriesList = new ArrayList<CheckBoxItem>();
		for (int i = 0; i < relativeLayout.getChildCount(); i++) {
			View item = relativeLayout.getChildAt(i);
			CheckBoxItem allergyHistories = new CheckBoxItem();
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					allergyHistories.setItem_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText())) {
						allergyHistories.setItem_name(editText.getText()
								.toString());
					} else {
						allergyHistories.setItem_name(checkBox.getText()
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
		if(followUpPostpartum!=null){
			try {
				setCheckBoxText(PostpartumLeftLayout, PostpartumLeftEdittext,
						JsonUtil.jsonToObjects(
								followUpPostpartum.getRfjc_zcrx(),
								CheckBoxItem.class));
				setCheckBoxText(PostpartumRightOther, postpartumRightEditText,
						JsonUtil.jsonToObjects(
								followUpPostpartum.getRfjc_ycrx(),
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

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub

	}
}
