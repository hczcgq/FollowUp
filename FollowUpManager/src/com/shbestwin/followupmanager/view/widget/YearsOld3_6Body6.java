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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;

public class YearsOld3_6Body6 extends LinearLayout implements
		IBaseYearsOld3_6Body, OnCheckedChangeListener {
	private EditText et_fy, et_fx, et_ws, et_qt;
	private CheckBox illness0, illness1, illness2, illness3, illness4;
	private HashMap<Integer, String> map_ms = new HashMap<Integer, String>();

	public YearsOld3_6Body6(Context context) {
		this(context, null);
	}

	public YearsOld3_6Body6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld3_6Body6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_years_old_3_6_body6, this, true);
		et_fy = (EditText) rootView.findViewById(R.id.et_fy);
		et_fx = (EditText) rootView.findViewById(R.id.et_fx);
		et_ws = (EditText) rootView.findViewById(R.id.et_ws);
		et_qt = (EditText) rootView.findViewById(R.id.et_qt);

		illness0 = (CheckBox) rootView.findViewById(R.id.illness0);
		illness1 = (CheckBox) rootView.findViewById(R.id.illness1);
		illness2 = (CheckBox) rootView.findViewById(R.id.illness2);
		illness3 = (CheckBox) rootView.findViewById(R.id.illness3);
		illness4 = (CheckBox) rootView.findViewById(R.id.illness4);

		illness0.setOnCheckedChangeListener(this);
		illness1.setOnCheckedChangeListener(this);
		illness2.setOnCheckedChangeListener(this);
		illness3.setOnCheckedChangeListener(this);
		illness4.setOnCheckedChangeListener(this);

	}

	@Override
	public void getData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		if (illness4.isChecked()) {
			map_ms.put(4, et_qt.getText().toString());
		}
		JsonObject jsonObject_ms = new JsonObject();
		JsonArray jsonArray_ms = new JsonArray();
		Iterator iterator_ms = map_ms.entrySet().iterator();
		while (iterator_ms.hasNext()) {
			Map.Entry entry = (Entry) iterator_ms.next();
			JsonObject object = new JsonObject();
			object.addProperty("hbqk_num", String.valueOf(entry.getKey()));
			object.addProperty("hbqk_name",
					String.valueOf(entry.getValue()));
			if(entry.getKey()=="1"){
				object.addProperty("hbqk_name_cs", et_fy.getText().toString());
			}else if(entry.getKey()=="2"){
				object.addProperty("hbqk_name_cs",et_fx.getText().toString());
			}else if(entry.getKey()=="3"){
				object.addProperty("hbqk_name_cs", et_ws.getText().toString());
			}
			jsonArray_ms.add(object);
		}
		jsonObject_ms.add("hbqk", jsonArray_ms);
		
		followUpThreeSixNewborn.setHbqk(jsonObject_ms.toString());
	}

	@Override
	public void setData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		switch (buttonView.getId()) {
		case R.id.illness0:
			if (isChecked) {
				map_ms.put(0, buttonView.getText().toString());
			} else {
				map_ms.remove(0);
			}
			break;
		case R.id.illness1:
			if (isChecked) {
				map_ms.put(1, buttonView.getText().toString());
			} else {
				map_ms.remove(1);
			}
			break;
		case R.id.illness2:
			if (isChecked) {
				map_ms.put(2, buttonView.getText().toString());
			} else {
				map_ms.remove(2);
			}
			break;
		case R.id.illness3:
			if (isChecked) {
				map_ms.put(2, buttonView.getText().toString());
			} else {
				map_ms.remove(2);
			}
			break;

		}

	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
	}

}
