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

public class NeonateBody7 extends LinearLayout  implements IBaseNeonateBody{
	
	private EditText et_tsyzjcl,et_xcsfrq,et_xcsfdd,et_sfysqm;
	private FragmentManager fragmentManager;
	private CheckBox rehabilitationGoal0, rehabilitationGoal1, rehabilitationGoal2, rehabilitationGoal3, rehabilitationGoal4;
	private HashMap<Integer, String> map = new HashMap<Integer, String>();
	
	public NeonateBody7(Context context) {
		this(context, null);
	}

	public NeonateBody7(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NeonateBody7(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_neonate_body7, this, true);
		et_tsyzjcl=(EditText) rootView.findViewById(R.id.et_tsyzjcl);
		et_xcsfrq=(EditText) rootView.findViewById(R.id.et_xcsfrq);
		et_xcsfdd=(EditText) rootView.findViewById(R.id.et_tsyzjcl);
		et_sfysqm=(EditText) rootView.findViewById(R.id.et_sfysqm);
		
		rehabilitationGoal0 = (CheckBox) rootView.findViewById(R.id.rehabilitationGoal0);
		rehabilitationGoal1 = (CheckBox) rootView.findViewById(R.id.rehabilitationGoal1);
		rehabilitationGoal2 = (CheckBox) rootView.findViewById(R.id.rehabilitationGoal2);
		rehabilitationGoal3 = (CheckBox) rootView.findViewById(R.id.rehabilitationGoal3);
		rehabilitationGoal4 = (CheckBox) rootView.findViewById(R.id.rehabilitationGoal4);

		rehabilitationGoal0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		rehabilitationGoal1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		rehabilitationGoal2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		rehabilitationGoal3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		rehabilitationGoal4.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
	public void getData(FollowUpNewborn followUpNewborn) {
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			JsonObject object = new JsonObject();
			object.addProperty("zd_zdmc_num", String.valueOf(entry.getKey()));
			object.addProperty("zd_zdmc_name",
					String.valueOf(entry.getValue()));
			jsonArray.add(object);
		}
		jsonObject.add("zd_zdmc", jsonArray);
		
		followUpNewborn.setZd_xcsfrq(et_xcsfrq.getText().toString());
		followUpNewborn.setZd_xcfsdd(et_xcsfdd.getText().toString());
		followUpNewborn.setZd_sfysqm(et_sfysqm.getText().toString());
		followUpNewborn.setZd_tsyzjcl(et_tsyzjcl.getText().toString());
		followUpNewborn.setZd_zdmc(jsonObject.toString());
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
		this.fragmentManager=fragmentManager;
	}

}
