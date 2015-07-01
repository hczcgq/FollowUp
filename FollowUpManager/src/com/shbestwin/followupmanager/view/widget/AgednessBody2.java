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
import android.widget.Spinner;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;

public class AgednessBody2 extends LinearLayout  implements IBaseAgednessBody{
	private HashMap<Integer, String> map = new HashMap<Integer, String>();
	private EditText et_other;
	private CheckBox symptom0,symptom1,symptom2,symptom3,symptom4,symptom5,symptom6,symptom7,symptom8,symptom9;
	public AgednessBody2(Context context) {
		this(context, null);
	}

	public AgednessBody2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AgednessBody2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_agedness_body2, this, true);
		et_other=(EditText) rootView.findViewById(R.id.et_other);
		
		symptom0 = (CheckBox) rootView.findViewById(R.id.symptom0);
		symptom1 = (CheckBox) rootView.findViewById(R.id.symptom1);
		symptom2 = (CheckBox) rootView.findViewById(R.id.symptom2);
		symptom3 = (CheckBox) rootView.findViewById(R.id.symptom3);
		symptom4 = (CheckBox) rootView.findViewById(R.id.symptom4);
		symptom5 = (CheckBox) rootView.findViewById(R.id.symptom5);
		symptom6 = (CheckBox) rootView.findViewById(R.id.symptom6);
		symptom7 = (CheckBox) rootView.findViewById(R.id.symptom7);
		symptom8 = (CheckBox) rootView.findViewById(R.id.symptom8);
		symptom9 = (CheckBox) rootView.findViewById(R.id.symptom9);
		
		symptom0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		symptom1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		symptom2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		symptom3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		symptom4.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		symptom5.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map.put(5, buttonView.getText().toString());
				} else {
					map.remove(5);
				}
			}
		});
		symptom6.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map.put(6, buttonView.getText().toString());
				} else {
					map.remove(6);
				}
			}
		});
		symptom7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map.put(7, buttonView.getText().toString());
				} else {
					map.remove(7);
				}
			}
		});
		symptom8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map.put(8, buttonView.getText().toString());
				} else {
					map.remove(8);
				}
			}
		});
	}
	@Override
	public void getData(FollowUpAged followUpAged) {
		if(symptom9.isChecked()){
			map.put(9, et_other.getText().toString());
		}
		
		JsonObject jsonObject=new JsonObject();
		JsonArray jsonArray=new JsonArray();
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			JsonObject object=new JsonObject();
			object.addProperty("zz_num", String.valueOf(entry.getKey()));
			object.addProperty("zz_name", String.valueOf(entry.getValue()));
			jsonArray.add(object);
		}
		jsonObject.add("zz", jsonArray);
		
		followUpAged.setZz(jsonObject.toString());
	}

	@Override
	public void setData(FollowUpAged followUpAged) {
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
