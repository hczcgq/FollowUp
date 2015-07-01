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
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;

public class NeonateBody3 extends LinearLayout  implements IBaseNeonateBody{
	private EditText et_dbxz,et_dbcs,et_dbys;
	private CheckBox stoolProperty0,stoolProperty1,stoolProperty2;
	private CheckBox stoolColor0,stoolColor1,stoolColor2,stoolColor3;
	private CheckBox sleepProblems0,sleepProblems1,sleepProblems2,sleepProblems3;
	private HashMap<Integer, String> map_dbxz = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_dbys = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_smwt = new HashMap<Integer, String>();
	public NeonateBody3(Context context) {
		this(context, null);
	}

	public NeonateBody3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NeonateBody3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_neonate_body3, this, true);
		
		et_dbxz = (EditText) rootView.findViewById(R.id.et_dbxz);
		et_dbcs = (EditText) rootView.findViewById(R.id.et_dbcs);
		et_dbys = (EditText) rootView.findViewById(R.id.et_dbys);
		
		stoolProperty0 = (CheckBox) rootView.findViewById(R.id.stoolProperty0);
		stoolProperty1 = (CheckBox) rootView.findViewById(R.id.stoolProperty1);
		stoolProperty2 = (CheckBox) rootView.findViewById(R.id.stoolProperty2);
		
		stoolProperty0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_dbxz.put(0, buttonView.getText().toString());
				} else {
					map_dbxz.remove(0);
				}
			}
		});
		stoolProperty1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_dbxz.put(1, buttonView.getText().toString());
				} else {
					map_dbxz.remove(1);
				}
			}
		});
		
		
		stoolColor0 = (CheckBox) rootView.findViewById(R.id.stoolColor0);
		stoolColor1 = (CheckBox) rootView.findViewById(R.id.stoolColor1);
		stoolColor2 = (CheckBox) rootView.findViewById(R.id.stoolColor2);
		stoolColor3 = (CheckBox) rootView.findViewById(R.id.stoolColor3);
		
		stoolColor0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_dbys.put(0, buttonView.getText().toString());
				} else {
					map_dbys.remove(0);
				}
			}
		});
		stoolColor1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_dbys.put(1, buttonView.getText().toString());
				} else {
					map_dbys.remove(1);
				}
			}
		});
		stoolColor2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_dbys.put(2, buttonView.getText().toString());
				} else {
					map_dbys.remove(2);
				}
			}
		});
		
		sleepProblems0 = (CheckBox) rootView.findViewById(R.id.sleepProblems0);
		sleepProblems1 = (CheckBox) rootView.findViewById(R.id.sleepProblems1);
		sleepProblems2 = (CheckBox) rootView.findViewById(R.id.sleepProblems2);
		sleepProblems3 = (CheckBox) rootView.findViewById(R.id.sleepProblems3);
		
		sleepProblems0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_smwt.put(0, buttonView.getText().toString());
				} else {
					map_smwt.remove(0);
				}
			}
		});
		sleepProblems1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_smwt.put(1, buttonView.getText().toString());
				} else {
					map_smwt.remove(1);
				}
			}
		});
		sleepProblems2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_smwt.put(2, buttonView.getText().toString());
				} else {
					map_smwt.remove(2);
				}
			}
		});
		sleepProblems3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_smwt.put(3, buttonView.getText().toString());
				} else {
					map_smwt.remove(3);
				}
			}
		});
	}
	@Override
	public void getData(FollowUpNewborn followUpNewborn) {
		if(stoolProperty2.isChecked()){
			map_dbxz.put(2, et_dbxz.getText().toString());
		}
		
		JsonObject jsonObject_dbxz=new JsonObject();
		JsonArray jsonArray_dbxz=new JsonArray();
		Iterator iterator_dbxz = map_dbxz.entrySet().iterator();
		while (iterator_dbxz.hasNext()) {
			Map.Entry entry = (Entry) iterator_dbxz.next();
			JsonObject object=new JsonObject();
			object.addProperty("jbstzk_dbxz_num", String.valueOf(entry.getKey()));
			object.addProperty("jbstzk_dbxz_name", String.valueOf(entry.getValue()));
			jsonArray_dbxz.add(object);
		}
		jsonObject_dbxz.add("jbstzk_dbxz", jsonArray_dbxz);
		
		
		if(stoolColor3.isChecked()){
			map_dbys.put(3, et_dbys.getText().toString());
		}
		
		JsonObject jsonObject_dbys=new JsonObject();
		JsonArray jsonArray_dbys=new JsonArray();
		Iterator iterator_dbys= map_dbys.entrySet().iterator();
		while (iterator_dbys.hasNext()) {
			Map.Entry entry = (Entry) iterator_dbys.next();
			JsonObject object=new JsonObject();
			object.addProperty("jbstzk_dbys_num", String.valueOf(entry.getKey()));
			object.addProperty("jbstzk_dbys_name", String.valueOf(entry.getValue()));
			jsonArray_dbys.add(object);
		}
		jsonObject_dbys.add("jbstzk_dbys", jsonArray_dbys);
		
		JsonObject jsonObject_smwt=new JsonObject();
		JsonArray jsonArray_smwt=new JsonArray();
		Iterator iterator_smwt= map_dbys.entrySet().iterator();
		while (iterator_smwt.hasNext()) {
			Map.Entry entry = (Entry) iterator_smwt.next();
			JsonObject object=new JsonObject();
			object.addProperty("jbstzk_smwt_num", String.valueOf(entry.getKey()));
			object.addProperty("jbstzk_smwt_name", String.valueOf(entry.getValue()));
			jsonArray_smwt.add(object);
		}
		jsonObject_smwt.add("jbstzk_smwt", jsonArray_smwt);
		
		
		followUpNewborn.setJbstzk_dbxz(jsonObject_dbxz.toString());
		followUpNewborn.setJbstzk_dbcs(et_dbcs.getText().toString());
		followUpNewborn.setJbstzk_dbys(jsonObject_dbys.toString());
		followUpNewborn.setJbstzk_smwt(jsonObject_smwt.toString());
		
		
		
	}

	@Override
	public void setData(FollowUpNewborn followUpNewborn) {
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
