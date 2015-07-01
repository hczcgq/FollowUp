package com.shbestwin.followupmanager.view.widget;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;

public class YearsOld1_2Body3 extends LinearLayout implements
		IBaseYearsOld1_2Body, OnCheckedChangeListener,
		android.widget.RadioGroup.OnCheckedChangeListener {
	private EditText et_tz, et_sc, et_bmi, et_tw, et_ms, et_qc_1, et_qc_2,
			et_pf, et_ywg, et_ewg, et_tl, et_cyzcs, et_xf, et_fb, et_sz, et_bt,
			et_wszq, et_glbtz;
	private Spinner sn_yypj, sn_cyzcs;
	private RadioGroup rg_qc, rg_pf, rg_ywg, rg_ewg, rg_tl, rg_xf, rg_fb,
			rg_sz, rg_bt, rg_wszq;
	private CheckBox complexion0, complexion1, complexion2;
	private CheckBox ricketsSign0, ricketsSign1, ricketsSign2;
	private HashMap<Integer, String> map_ms = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_glbtz = new HashMap<Integer, String>();
	private boolean isQc = true, ispf = false, isYwg = false, isEwg = false,
			isTl = true, isXf = false, isFb = false, isSz = false,
			isWszq = false;

	public YearsOld1_2Body3(Context context) {
		this(context, null);
	}

	public YearsOld1_2Body3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld1_2Body3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_years_old_1_2_body3, this, true);

		et_tz = (EditText) rootView.findViewById(R.id.et_tz);
		et_sc = (EditText) rootView.findViewById(R.id.et_sc);
		et_bmi = (EditText) rootView.findViewById(R.id.et_bmi);
		et_tw = (EditText) rootView.findViewById(R.id.et_tw);
		et_ms = (EditText) rootView.findViewById(R.id.et_ms);
		et_qc_1 = (EditText) rootView.findViewById(R.id.et_qc_1);
		et_qc_2 = (EditText) rootView.findViewById(R.id.et_qc_2);
		et_pf = (EditText) rootView.findViewById(R.id.et_pf);
		et_ywg = (EditText) rootView.findViewById(R.id.et_ywg);
		et_ewg = (EditText) rootView.findViewById(R.id.et_ewg);
		et_tl = (EditText) rootView.findViewById(R.id.et_tl);
		et_cyzcs = (EditText) rootView.findViewById(R.id.et_cyzcs);
		et_xf = (EditText) rootView.findViewById(R.id.et_xf);
		et_fb = (EditText) rootView.findViewById(R.id.et_fb);
		et_sz = (EditText) rootView.findViewById(R.id.et_sz);
		et_bt = (EditText) rootView.findViewById(R.id.et_bt);
		et_wszq = (EditText) rootView.findViewById(R.id.et_wszq);
		et_glbtz = (EditText) rootView.findViewById(R.id.et_glbtz);

		sn_yypj = (Spinner) rootView.findViewById(R.id.sn_yypg);
		sn_cyzcs = (Spinner) rootView.findViewById(R.id.sn_cyzcs);

		rg_qc = (RadioGroup) rootView.findViewById(R.id.rg_qc);
		rg_pf = (RadioGroup) rootView.findViewById(R.id.rg_pf);
		rg_ywg = (RadioGroup) rootView.findViewById(R.id.rg_ywg);
		rg_ewg = (RadioGroup) rootView.findViewById(R.id.rg_ewg);
		rg_tl = (RadioGroup) rootView.findViewById(R.id.rg_tl);
		rg_xf = (RadioGroup) rootView.findViewById(R.id.rg_xf);
		rg_fb = (RadioGroup) rootView.findViewById(R.id.rg_fb);
		rg_sz = (RadioGroup) rootView.findViewById(R.id.rg_sz);
		rg_bt = (RadioGroup) rootView.findViewById(R.id.rg_bt);
		rg_wszq = (RadioGroup) rootView.findViewById(R.id.rg_wszq);
		rg_qc.setOnCheckedChangeListener(this);
		rg_pf.setOnCheckedChangeListener(this);
		rg_ywg.setOnCheckedChangeListener(this);
		rg_ewg.setOnCheckedChangeListener(this);
		rg_tl.setOnCheckedChangeListener(this);
		rg_xf.setOnCheckedChangeListener(this);
		rg_fb.setOnCheckedChangeListener(this);
		rg_sz.setOnCheckedChangeListener(this);
		rg_bt.setOnCheckedChangeListener(this);
		rg_wszq.setOnCheckedChangeListener(this);

		complexion0 = (CheckBox) rootView.findViewById(R.id.complexion0);
		complexion1 = (CheckBox) rootView.findViewById(R.id.complexion1);
		complexion2 = (CheckBox) rootView.findViewById(R.id.complexion2);
		complexion0.setOnCheckedChangeListener(this);
		complexion1.setOnCheckedChangeListener(this);
		complexion2.setOnCheckedChangeListener(this);

		ricketsSign0 = (CheckBox) rootView.findViewById(R.id.ricketsSign0);
		ricketsSign1 = (CheckBox) rootView.findViewById(R.id.ricketsSign1);
		ricketsSign2 = (CheckBox) rootView.findViewById(R.id.ricketsSign2);
		ricketsSign0.setOnCheckedChangeListener(this);
		ricketsSign1.setOnCheckedChangeListener(this);
		ricketsSign2.setOnCheckedChangeListener(this);
	}

	@Override
	public void getData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		JsonObject jsonObject_ms = new JsonObject();
		JsonObject jsonObject_glbtz = new JsonObject();
		
		JsonArray jsonArray_ms = new JsonArray();
		JsonArray jsonArray_glbtz = new JsonArray();
		
		if (complexion2.isChecked()) {
			map_ms.put(2, et_ms.getText().toString());
		}
		if (ricketsSign2.isChecked()) {
			map_glbtz.put(2, et_glbtz.getText().toString());
		}
		
		Iterator iterator_ms = map_ms.entrySet().iterator();
		while (iterator_ms.hasNext()) {
			Map.Entry entry = (Entry) iterator_ms.next();
			JsonObject object = new JsonObject();
			object.addProperty("tgjcqk_ms_num", String.valueOf(entry.getKey()));
			object.addProperty("tgjcqk_ms_name",
					String.valueOf(entry.getValue()));
			jsonArray_ms.add(object);
		}
		jsonObject_ms.add("tgjcqk_ms", jsonArray_ms);

		Iterator iterator_glbtz = map_glbtz.entrySet().iterator();
		while (iterator_glbtz.hasNext()) {
			Map.Entry entry = (Entry) iterator_glbtz.next();
			JsonObject object = new JsonObject();
			object.addProperty("tgjcqk_glbtz_num	", String.valueOf(entry.getKey()));
			object.addProperty("tgjcqk_glbtz_name",
					String.valueOf(entry.getValue()));
			jsonArray_glbtz.add(object);
		}
		jsonObject_glbtz.add("tgjcqk_glbtz", jsonArray_glbtz);
		
		followUpOneTwoNewborn.setTgjcqk_tz(et_tz.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_sc(et_sc.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_bmi(et_bmi.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_tw(et_tw.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_yypj(ViewDataUtil.getSpinnerData(sn_yypj));
		followUpOneTwoNewborn.setTgjcqk_ms(jsonObject_ms.toString());
		followUpOneTwoNewborn.setTgjcqk_sfqxbh(isQc);
		followUpOneTwoNewborn.setTgjcqk_sfqxbhms(et_qc_1.getText().toString()+"*"+et_qc_2.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_sfpfyc(ispf);
		followUpOneTwoNewborn.setTgjcqk_sfpfycms(et_pf.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_sfywgyc(isYwg);
		followUpOneTwoNewborn.setTgjcqk_sfywgycms(et_ywg.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_sfewgyc(isEwg);
		followUpOneTwoNewborn.setTgjcqk_sfewgycms(et_ewg.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_sftlyc(isTl);
		followUpOneTwoNewborn.setTgjcqk_sftlycms(et_tl.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_cyqcs(et_cyzcs.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_cyqcsjl(ViewDataUtil.getSpinnerData(sn_cyzcs));
		followUpOneTwoNewborn.setTgjcqk_sfxftzyc(isXf);
		followUpOneTwoNewborn.setTgjcqk_sfxftzycms(et_xf.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_sffbczyc(isFb);
		followUpOneTwoNewborn.setTgjcqk_sffbczycms(et_fb.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_sfszhddyc(isSz);
		followUpOneTwoNewborn.setTgjcqk_sfszhddycms(et_sz.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_sfwszqyc(isWszq);
		followUpOneTwoNewborn.setTgjcqk_sfwszqycms(et_wszq.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_glbtz(jsonObject_glbtz.toString());
	}

	@Override
	public void setData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == R.id.rb_qc_bh) {
			isQc = true;
		} else if (checkedId == R.id.rb_qc_wbh) {
			isQc = false;
		}

		if (checkedId == R.id.rb_pf_wjyc) {
			ispf = false;
		} else if (checkedId == R.id.rb_pf_yc) {
			ispf = true;
		}
		if (checkedId == R.id.rb_ywg_wjyc) {
			isYwg = false;
		} else if (checkedId == R.id.rb_ywg_yc) {
			isYwg = true;
		}
		if (checkedId == R.id.rb_ewg_wjyc) {
			isEwg = false;
		} else if (checkedId == R.id.rb_ewg_yc) {
			isEwg = true;
		}

		if (checkedId == R.id.rb_tl_tg) {
			isTl = true;
		} else if (checkedId == R.id.rb_tl_wtg) {
			isTl = false;
		}
		if (checkedId == R.id.rb_xf_wjyc) {
			isXf = false;
		} else if (checkedId == R.id.rb_xf_yc) {
			isXf = true;
		}
		if (checkedId == R.id.rb_fb_wjyc) {
			isFb = false;
		} else if (checkedId == R.id.rb_fb_yc) {
			isFb = true;
		}
		if (checkedId == R.id.rb_sz_wjyc) {
			isSz = false;
		} else if (checkedId == R.id.rb_sz_yc) {
			isSz = true;
		}
		if (checkedId == R.id.rb_wszq_wjyc) {
			isWszq = false;
		} else if (checkedId == R.id.rb_wszq_yc) {
			isWszq = true;
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.complexion0:
			if (isChecked) {
				map_ms.put(0, buttonView.getText().toString());
			} else {
				map_ms.remove(0);
			}
			break;
		case R.id.complexion1:
			if (isChecked) {
				map_ms.put(1, buttonView.getText().toString());
			} else {
				map_ms.remove(1);
			}
			break;
		case R.id.complexion2:
			if (isChecked) {
				map_ms.put(2, buttonView.getText().toString());
			} else {
				map_ms.remove(2);
			}
			break;

		//
		case R.id.ricketsSign0:
			if (isChecked) {
				map_glbtz.put(0, buttonView.getText().toString());
			} else {
				map_glbtz.remove(0);
			}
			break;
		case R.id.ricketsSign1:
			if (isChecked) {
				map_glbtz.put(1, buttonView.getText().toString());
			} else {
				map_glbtz.remove(1);
			}
			break;
		case R.id.ricketsSign2:
			if (isChecked) {
				map_glbtz.put(2, buttonView.getText().toString());
			} else {
				map_glbtz.remove(2);
			}
			break;
		}
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub

	}
}
