package com.shbestwin.followupmanager.view.widget;

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
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body11 extends LinearLayout implements IBaseYearsOld0Body {
	private HashMap<Integer, String> map = new HashMap<Integer, String>();
	private EditText et_other;
	private CheckBox diagnose0, diagnose1, diagnose2, diagnose3, diagnose4;

	public YearsOld0Body11(Context context) {
		this(context, null);
	}

	public YearsOld0Body11(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body11(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_years_old_0_body11, this, true);
		et_other = (EditText) rootView.findViewById(R.id.et_other);

		diagnose0 = (CheckBox) rootView.findViewById(R.id.diagnose0);
		diagnose1 = (CheckBox) rootView.findViewById(R.id.diagnose1);
		diagnose2 = (CheckBox) rootView.findViewById(R.id.diagnose2);
		diagnose3 = (CheckBox) rootView.findViewById(R.id.diagnose3);
		diagnose4 = (CheckBox) rootView.findViewById(R.id.diagnose4);
		diagnose0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		diagnose1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		diagnose2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		diagnose3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
	}

	@Override
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		if(diagnose4.isChecked()){
			map.put(4, et_other.getText().toString());
		}
		
		JsonObject jsonObject=new JsonObject();
		JsonArray jsonArray=new JsonArray();
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			JsonObject object=new JsonObject();
			object.addProperty("jbzd_num", String.valueOf(entry.getKey()));
			object.addProperty("jbzd_name", String.valueOf(entry.getValue()));
			jsonArray.add(object);
		}
		jsonObject.add("jbzd", jsonArray);
		
		followUpOneNewborn.setJbzd(jsonObject.toString());
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
		// TODO Auto-generated method stub

	}
}
