package com.shbestwin.followupmanager.view.widget;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class YearsOld0Body15 extends LinearLayout  implements IBaseYearsOld0Body {
	private EditText et_yzjjy,et_xcsfrq,et_sfysqm;
	private FragmentManager fragmentManager;
	private HashMap<Integer, String> map = new HashMap<Integer, String>();
	private CheckBox guide0, guide1, guide2, guide3, guide4;
	public YearsOld0Body15(Context context) {
		this(context, null);
	}

	public YearsOld0Body15(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body15(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_0_body15, this, true);
		et_yzjjy=(EditText) rootView.findViewById(R.id.et_yzjjy);
		et_xcsfrq=(EditText) rootView.findViewById(R.id.et_xcsfrq);
		et_sfysqm=(EditText) rootView.findViewById(R.id.et_sfysqm);
		et_xcsfrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_xcsfrq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_xcsfrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		

		guide0 = (CheckBox) rootView.findViewById(R.id.guide0);
		guide1 = (CheckBox) rootView.findViewById(R.id.guide1);
		guide2 = (CheckBox) rootView.findViewById(R.id.guide2);
		guide3 = (CheckBox) rootView.findViewById(R.id.guide3);
		guide4 = (CheckBox) rootView.findViewById(R.id.guide4);
		guide0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map.put(0, buttonView.getText().toString());
				} else {
					map.remove(0);
				}
			}
		});
		guide1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map.put(1, buttonView.getText().toString());
				} else {
					map.remove(1);
				}
			}
		});
		guide2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map.put(2, buttonView.getText().toString());
				} else {
					map.remove(2);
				}
			}
		});
		guide3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map.put(3, buttonView.getText().toString());
				} else {
					map.remove(3);
				}
			}
		});
		guide4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map.put(4, buttonView.getText().toString());
				} else {
					map.remove(4);
				}
			}
		});
	}
	@Override
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		JsonObject jsonObject=new JsonObject();
		JsonArray jsonArray=new JsonArray();
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			JsonObject object=new JsonObject();
			object.addProperty("zd_zd_num", String.valueOf(entry.getKey()));
			object.addProperty("zd_zd_name", String.valueOf(entry.getValue()));
			jsonArray.add(object);
		}
		jsonObject.add("zd_zd", jsonArray);
		
		followUpOneNewborn.setZd_yzhjy(et_yzjjy.getText().toString());
		followUpOneNewborn.setZd_xcsfrq(et_xcsfrq.getText().toString());
		followUpOneNewborn.setZd_sfysqm(et_sfysqm.getText().toString());
		followUpOneNewborn.setZd_zd(jsonObject.toString());
	}

	@Override
	public void setData(FollowUpOneNewborn followUpOneNewborn) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		this.fragmentManager=fragmentManager;
	}
}
