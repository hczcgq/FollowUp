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
import android.widget.Spinner;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.CheckBoxItem;
import com.shbestwin.followupmanager.model.followup.FollowUpDisabledPerson;

public class DisabilityBody4 extends LinearLayout implements
		IBaseDisabilityBody {
	private EditText et_gnxl_cy, et_gnxl_fc, et_yy, et_zjqx;

	private Spinner sn_xldd, sn_xlpg;

	private RelativeLayout rehabilitationLayout, rehabilitationGoalLayout;

	private CheckBox rehabilitationOther, rehabilitationGoalOther;

	private EditText rehabilitationEdittext, rehabilitationGoalEdittext;

	public DisabilityBody4(Context context) {
		this(context, null);
	}

	public DisabilityBody4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DisabilityBody4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_disability_body4, this, true);
		et_gnxl_cy = (EditText) rootView.findViewById(R.id.et_gnxl_cy);
		et_gnxl_fc = (EditText) rootView.findViewById(R.id.et_gnxl_fc);
		et_yy = (EditText) rootView.findViewById(R.id.et_yy);
		et_zjqx = (EditText) rootView.findViewById(R.id.et_zjqx);

		sn_xldd = (Spinner) rootView.findViewById(R.id.sn_xldd);
		sn_xlpg = (Spinner) rootView.findViewById(R.id.sn_xlpg);

		rehabilitationLayout = (RelativeLayout) rootView
				.findViewById(R.id.rehabilitationLayout);
		rehabilitationGoalLayout = (RelativeLayout) rootView
				.findViewById(R.id.rehabilitationGoalLayout);
		rehabilitationOther = (CheckBox) rootView
				.findViewById(R.id.rehabilitationOther);
		rehabilitationGoalOther = (CheckBox) rootView
				.findViewById(R.id.rehabilitationGoalOther);
		rehabilitationEdittext = (EditText) rootView
				.findViewById(R.id.rehabilitationEdittext);
		rehabilitationGoalEdittext = (EditText) rootView
				.findViewById(R.id.rehabilitationGoalEdittext);

		ViewDataUtil.initOtherCheckboxConstraint(rehabilitationOther,
				rehabilitationEdittext);
		ViewDataUtil.initOtherCheckboxConstraint(rehabilitationGoalOther,
				rehabilitationGoalEdittext);

	}

	@Override
	public void getData(FollowUpDisabledPerson followUpDisabledPerson) {
		followUpDisabledPerson
				.setKffw_kfxm(getRehabProject(rehabilitationLayout));
		followUpDisabledPerson
				.setKffw_kfmb(getRehabProject(rehabilitationGoalLayout));
		followUpDisabledPerson.setKffw_gnxl(et_gnxl_cy.getText().toString()
				+ "/" + et_gnxl_fc.getText().toString());
		followUpDisabledPerson.setKffw_xldz(ViewDataUtil
				.getSpinnerData(sn_xldd));
		followUpDisabledPerson.setKffw_xlpg(ViewDataUtil
				.getSpinnerData(sn_xlpg));
		followUpDisabledPerson.setKffw_yy(et_yy.getText().toString());
		followUpDisabledPerson.setKffw_zjqx(et_zjqx.getText().toString());
	}

	@Override
	public void setData(FollowUpDisabledPerson followUpDisabledPerson) {
		if (followUpDisabledPerson != null) {
			et_yy.setText(followUpDisabledPerson.getKffw_yy());
			et_zjqx.setText(followUpDisabledPerson.getKffw_zjqx());
			ViewDataUtil.setSpinnerData(sn_xldd,
					followUpDisabledPerson.getKffw_xldz());
			ViewDataUtil.setSpinnerData(sn_xlpg,
					followUpDisabledPerson.getKffw_xlpg());
			String gnxl = followUpDisabledPerson.getKffw_gnxl();
			if (gnxl.split("/").length != 0) {
				et_gnxl_cy.setText(gnxl.split("/")[0]);
			} else if (gnxl.split("/").length == 2) {
				et_gnxl_cy.setText(gnxl.split("/")[0]);
				et_gnxl_fc.setText(gnxl.split("/")[1]);
			}
			try {
				setCheckBoxText(rehabilitationLayout, rehabilitationEdittext,
						JsonUtil.jsonToObjects(
								followUpDisabledPerson.getKffw_kfxm(),
								CheckBoxItem.class));
				setCheckBoxText(rehabilitationGoalLayout,
						rehabilitationGoalEdittext, JsonUtil.jsonToObjects(
								followUpDisabledPerson.getKffw_kfmb(),
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

	private String getRehabProject(View layout) {
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
							&& rehabilitationEdittext != null) {
						entity.setItem_name(rehabilitationEdittext.getText()
								.toString());
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
			int Num = Integer.valueOf(mList.get(i).getItem_num());
			String name = mList.get(i).getItem_name();
			if ((View) (myLayout).getChildAt(Num) instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) (View) (myLayout)
						.getChildAt(Num);
				checkBox.setChecked(true);
				if (Num == rehabilitationGoalLayout.getChildCount() - 2) {
					textview.setText(name);
				}
			}
		}
	}

}
