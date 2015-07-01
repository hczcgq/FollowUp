package com.shbestwin.followupmanager.view.widget;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.R.bool;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body3 extends LinearLayout implements IBaseYearsOld0Body,
		OnCheckedChangeListener,
		android.widget.CompoundButton.OnCheckedChangeListener {
	private EditText et_tz, et_sc, et_bmi, et_yw, et_ms, et_qc_1, et_qc_2,
			et_jbbk, et_pf, et_ywg, et_ewg, et_b, et_kq, et_kq_k, et_tl, et_xf,
			et_fb, et_sz, et_qb, et_wszq, et_gm;
	private Spinner sn_yypj;
	private RadioGroup rg_qc, rg_jbbk, rg_pf, rg_ywg, rg_ewg, rg_b, rg_kq,
			rg_tl, rg_xf, rg_fb, rg_sz, rg_wszq, rg_gm;
	private CheckBox complexion0, complexion1, complexion2;
	private CheckBox umbilicalRegion0, umbilicalRegion1, umbilicalRegion2,
			umbilicalRegion3, umbilicalRegion4;
	private CheckBox ricketsSymptom0, ricketsSymptom1, ricketsSymptom2,
			ricketsSymptom3;
	private CheckBox ricketsSign0, ricketsSign1, ricketsSign2, ricketsSign3;
	private CheckBox hipActivity0, hipActivity1, hipActivity2;

	private HashMap<Integer, String> map_ms = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_qb = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_glbzz = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_glbtz = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_hgjhd = new HashMap<Integer, String>();
	private boolean isQc = true, isJbbk = false, ispf = false, isYwg = false,
			isEwg = false, isB = false, isKq = false, isTl = true,
			isXf = false, isFb = false, isSz = false, isWszq = false,
			isGm = false;

	public YearsOld0Body3(Context context) {
		this(context, null);
	}

	public YearsOld0Body3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_years_old_0_body3, this, true);

		et_tz = (EditText) rootView.findViewById(R.id.et_tz);
		et_sc = (EditText) rootView.findViewById(R.id.et_sc);
		et_bmi = (EditText) rootView.findViewById(R.id.et_bmi);
		et_yw = (EditText) rootView.findViewById(R.id.et_yw);
		et_ms = (EditText) rootView.findViewById(R.id.et_ms);
		et_qc_1 = (EditText) rootView.findViewById(R.id.et_qc_1);
		et_qc_2 = (EditText) rootView.findViewById(R.id.et_qc_2);
		et_jbbk = (EditText) rootView.findViewById(R.id.et_jbbk);
		et_pf = (EditText) rootView.findViewById(R.id.et_pf);
		et_ywg = (EditText) rootView.findViewById(R.id.et_ywg);
		et_ewg = (EditText) rootView.findViewById(R.id.et_ewg);
		et_b = (EditText) rootView.findViewById(R.id.et_b);
		et_kq = (EditText) rootView.findViewById(R.id.et_kq);
		et_kq_k = (EditText) rootView.findViewById(R.id.et_kq_k);
		et_tl = (EditText) rootView.findViewById(R.id.et_tl);
		et_xf = (EditText) rootView.findViewById(R.id.et_xf);
		et_fb = (EditText) rootView.findViewById(R.id.et_fb);
		et_sz = (EditText) rootView.findViewById(R.id.et_sz);
		et_qb = (EditText) rootView.findViewById(R.id.et_qb);
		et_wszq = (EditText) rootView.findViewById(R.id.et_wszq);
		et_gm = (EditText) rootView.findViewById(R.id.et_gm);

		sn_yypj = (Spinner) rootView.findViewById(R.id.sn_yypj);

		rg_qc = (RadioGroup) rootView.findViewById(R.id.rg_qc);
		rg_jbbk = (RadioGroup) rootView.findViewById(R.id.rg_jbbk);
		rg_pf = (RadioGroup) rootView.findViewById(R.id.rg_pf);
		rg_ywg = (RadioGroup) rootView.findViewById(R.id.rg_ywg);
		rg_ewg = (RadioGroup) rootView.findViewById(R.id.rg_ewg);
		rg_b = (RadioGroup) rootView.findViewById(R.id.rg_b);
		rg_kq = (RadioGroup) rootView.findViewById(R.id.rg_kq);
		rg_tl = (RadioGroup) rootView.findViewById(R.id.rg_tl);
		rg_xf = (RadioGroup) rootView.findViewById(R.id.rg_fb);
		rg_fb = (RadioGroup) rootView.findViewById(R.id.rg_tl);
		rg_sz = (RadioGroup) rootView.findViewById(R.id.rg_sz);
		rg_wszq = (RadioGroup) rootView.findViewById(R.id.rg_wszq);
		rg_gm = (RadioGroup) rootView.findViewById(R.id.rg_gm);
		rg_qc.setOnCheckedChangeListener(this);
		rg_jbbk.setOnCheckedChangeListener(this);
		rg_pf.setOnCheckedChangeListener(this);
		rg_ywg.setOnCheckedChangeListener(this);
		rg_ewg.setOnCheckedChangeListener(this);
		rg_b.setOnCheckedChangeListener(this);
		rg_kq.setOnCheckedChangeListener(this);
		rg_tl.setOnCheckedChangeListener(this);
		rg_xf.setOnCheckedChangeListener(this);
		rg_fb.setOnCheckedChangeListener(this);
		rg_sz.setOnCheckedChangeListener(this);
		rg_wszq.setOnCheckedChangeListener(this);
		rg_gm.setOnCheckedChangeListener(this);

		complexion0 = (CheckBox) rootView.findViewById(R.id.complexion0);
		complexion1 = (CheckBox) rootView.findViewById(R.id.complexion1);
		complexion2 = (CheckBox) rootView.findViewById(R.id.complexion2);
		complexion0.setOnCheckedChangeListener(this);
		complexion1.setOnCheckedChangeListener(this);
		complexion2.setOnCheckedChangeListener(this);

		umbilicalRegion0 = (CheckBox) rootView
				.findViewById(R.id.umbilicalRegion0);
		umbilicalRegion1 = (CheckBox) rootView
				.findViewById(R.id.umbilicalRegion1);
		umbilicalRegion2 = (CheckBox) rootView
				.findViewById(R.id.umbilicalRegion2);
		umbilicalRegion3 = (CheckBox) rootView
				.findViewById(R.id.umbilicalRegion3);
		umbilicalRegion4 = (CheckBox) rootView
				.findViewById(R.id.umbilicalRegion4);
		umbilicalRegion0.setOnCheckedChangeListener(this);
		umbilicalRegion1.setOnCheckedChangeListener(this);
		umbilicalRegion2.setOnCheckedChangeListener(this);
		umbilicalRegion3.setOnCheckedChangeListener(this);
		umbilicalRegion4.setOnCheckedChangeListener(this);

		ricketsSymptom0 = (CheckBox) rootView
				.findViewById(R.id.ricketsSymptom0);
		ricketsSymptom1 = (CheckBox) rootView
				.findViewById(R.id.ricketsSymptom1);
		ricketsSymptom2 = (CheckBox) rootView
				.findViewById(R.id.ricketsSymptom2);
		ricketsSymptom3 = (CheckBox) rootView
				.findViewById(R.id.ricketsSymptom3);
		ricketsSymptom0.setOnCheckedChangeListener(this);
		ricketsSymptom1.setOnCheckedChangeListener(this);
		ricketsSymptom2.setOnCheckedChangeListener(this);
		ricketsSymptom3.setOnCheckedChangeListener(this);

		ricketsSign0 = (CheckBox) rootView.findViewById(R.id.ricketsSign0);
		ricketsSign1 = (CheckBox) rootView.findViewById(R.id.ricketsSign1);
		ricketsSign2 = (CheckBox) rootView.findViewById(R.id.ricketsSign2);
		ricketsSign3 = (CheckBox) rootView.findViewById(R.id.ricketsSign3);
		ricketsSign0.setOnCheckedChangeListener(this);
		ricketsSign1.setOnCheckedChangeListener(this);
		ricketsSign2.setOnCheckedChangeListener(this);
		ricketsSign3.setOnCheckedChangeListener(this);

		hipActivity0 = (CheckBox) rootView.findViewById(R.id.hipActivity0);
		hipActivity1 = (CheckBox) rootView.findViewById(R.id.hipActivity1);
		hipActivity2 = (CheckBox) rootView.findViewById(R.id.hipActivity2);
		hipActivity0.setOnCheckedChangeListener(this);
		hipActivity1.setOnCheckedChangeListener(this);
		hipActivity2.setOnCheckedChangeListener(this);
	}

	@Override
	public void getData(FollowUpOneNewborn followUpOneNewborn) {

		JsonObject jsonObject_ms = new JsonObject();
		JsonObject jsonObject_qb = new JsonObject();
		JsonObject jsonObject_glbzz = new JsonObject();
		JsonObject jsonObject_glbtz = new JsonObject();
		JsonObject jsonObject_hgjhd = new JsonObject();

		JsonArray jsonArray_ms = new JsonArray();
		JsonArray jsonArray_qb = new JsonArray();
		JsonArray jsonArray_glbzz = new JsonArray();
		JsonArray jsonArray_glbtz = new JsonArray();
		JsonArray jsonArray_hgjhd = new JsonArray();

		if (complexion2.isChecked()) {
			map_ms.put(2, et_ms.getText().toString());
		}
		if (umbilicalRegion4.isChecked()) {
			map_qb.put(4, et_qb.getText().toString());
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

		Iterator iterator_qd = map_qb.entrySet().iterator();
		while (iterator_qd.hasNext()) {
			Map.Entry entry = (Entry) iterator_qd.next();
			JsonObject object = new JsonObject();
			object.addProperty("tgjcqk_qb_num", String.valueOf(entry.getKey()));
			object.addProperty("tgjcqk_qb_name",
					String.valueOf(entry.getValue()));
			jsonArray_qb.add(object);
		}
		jsonObject_qb.add("tgjcqk_qb", jsonArray_qb);

		Iterator iterator_glbzz = map_glbzz.entrySet().iterator();
		while (iterator_glbzz.hasNext()) {
			Map.Entry entry = (Entry) iterator_glbzz.next();
			JsonObject object = new JsonObject();
			object.addProperty("tgjcqk_glbzz_num",
					String.valueOf(entry.getKey()));
			object.addProperty("tgjcqk_glbzz_name",
					String.valueOf(entry.getValue()));
			jsonArray_glbzz.add(object);
		}
		jsonObject_glbzz.add("tgjcqk_glbzz", jsonArray_glbzz);

		Iterator iterator_glbtz = map_glbtz.entrySet().iterator();
		while (iterator_glbtz.hasNext()) {
			Map.Entry entry = (Entry) iterator_glbtz.next();
			JsonObject object = new JsonObject();
			object.addProperty("tgjcqk_glbtz_num",
					String.valueOf(entry.getKey()));
			object.addProperty("tgjcqk_glbtz_name",
					String.valueOf(entry.getValue()));
			jsonArray_glbtz.add(object);
		}
		jsonObject_glbtz.add("tgjcqk_glbtz", jsonArray_glbtz);

		Iterator iterator_hgjhd = map_ms.entrySet().iterator();
		while (iterator_hgjhd.hasNext()) {
			Map.Entry entry = (Entry) iterator_hgjhd.next();
			JsonObject object = new JsonObject();
			object.addProperty("tgjcqk_kgjhd_num",
					String.valueOf(entry.getKey()));
			object.addProperty("tgjcqk_kgjhd_name",
					String.valueOf(entry.getValue()));
			jsonArray_hgjhd.add(object);
		}
		jsonObject_hgjhd.add("tgjcqk_kgjhd", jsonArray_hgjhd);

		followUpOneNewborn.setTgjcqk_tz(et_tz.getText().toString());
		followUpOneNewborn.setTgjcqk_sc(et_sc.getText().toString());
		followUpOneNewborn.setTgjcqk_bmi(et_bmi.getText().toString());
		followUpOneNewborn.setTgjcqk_yw(et_yw.getText().toString());
		followUpOneNewborn.setTgjcqk_yypj(ViewDataUtil.getSpinnerData(sn_yypj));
		followUpOneNewborn.setTgjcqk_ms(jsonObject_ms.toString());
		followUpOneNewborn.setTgjcqk_sfqxbh(isQc);
		followUpOneNewborn.setTgjcqk_sfqxbhms(et_qc_1.getText().toString()
				+ "*" + et_qc_2.getText().toString());
		followUpOneNewborn.setTgjcqk_sfjbbk(isJbbk);
		followUpOneNewborn.setTgjcqk_sfjbbkms(et_jbbk.getText().toString());
		followUpOneNewborn.setTgjcqk_sfpfyc(ispf);
		followUpOneNewborn.setTgjcqk_sfpfycms(et_pf.getText().toString());
		followUpOneNewborn.setTgjcqk_sfywgyc(isYwg);
		followUpOneNewborn.setTgjcqk_sfywgycms(et_ywg.getText().toString());
		followUpOneNewborn.setTgjcqk_sfewgyc(isEwg);
		followUpOneNewborn.setTgjcqk_sfewgycms(et_ewg.getText().toString());
		followUpOneNewborn.setTgjcqk_sfbyc(isB);
		followUpOneNewborn.setTgjcqk_sfbycms(et_b.getText().toString());
		followUpOneNewborn.setTgjcqk_sfkqyc(isKq);
		followUpOneNewborn.setTgjcqk_sfkqycms(et_kq.getText().toString() + "/"
				+ et_kq_k.getText().toString());
		followUpOneNewborn.setTgjcqk_sftlyc(isTl);
		followUpOneNewborn.setTgjcqk_sftlycms(et_tl.getText().toString());
		followUpOneNewborn.setTgjcqk_sfxftzyc(isXf);
		followUpOneNewborn.setTgjcqk_sfxftzycms(et_xf.getText().toString());
		followUpOneNewborn.setTgjcqk_sffbczyc(isFb);
		followUpOneNewborn.setTgjcqk_sffbczycms(et_fb.getText().toString());
		followUpOneNewborn.setTgjcqk_sfszhddyc(isSz);
		followUpOneNewborn.setTgjcqk_sfszhddycms(et_sz.getText().toString());
		followUpOneNewborn.setTgjcqk_qb(jsonObject_qb.toString());
		followUpOneNewborn.setTgjcqk_glbzz(jsonObject_glbzz.toString());
		followUpOneNewborn.setTgjcqk_glbtz(jsonObject_glbtz.toString());
		followUpOneNewborn.setTgjcqk_kgjhd(jsonObject_hgjhd.toString());
		followUpOneNewborn.setTgjcqk_sfwszqyc(isWszq);
		followUpOneNewborn.setTgjcqk_sfwszqycms(et_wszq.getText().toString());
		followUpOneNewborn.setTgjcqk_sfgmyc(isGm);
		followUpOneNewborn.setTgjcqk_sfgmycms(et_gm.getText().toString());

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
		case R.id.umbilicalRegion0:
			if (isChecked) {
				map_qb.put(0, buttonView.getText().toString());
			} else {
				map_qb.remove(0);
			}
			break;
		case R.id.umbilicalRegion1:
			if (isChecked) {
				map_qb.put(1, buttonView.getText().toString());
			} else {
				map_qb.remove(1);
			}
			break;
		case R.id.umbilicalRegion2:
			if (isChecked) {
				map_qb.put(2, buttonView.getText().toString());
			} else {
				map_qb.remove(2);
			}
			break;
		case R.id.umbilicalRegion3:
			if (isChecked) {
				map_qb.put(3, buttonView.getText().toString());
			} else {
				map_qb.remove(3);
			}
			break;
		case R.id.umbilicalRegion4:
			if (isChecked) {
				map_qb.put(4, buttonView.getText().toString());
			} else {
				map_qb.remove(4);
			}
			break;

		//
		case R.id.ricketsSymptom0:
			if (isChecked) {
				map_glbzz.put(0, buttonView.getText().toString());
			} else {
				map_glbzz.remove(0);
			}
			break;
		case R.id.ricketsSymptom1:
			if (isChecked) {
				map_glbzz.put(1, buttonView.getText().toString());
			} else {
				map_glbzz.remove(1);
			}
			break;
		case R.id.ricketsSymptom2:
			if (isChecked) {
				map_glbzz.put(2, buttonView.getText().toString());
			} else {
				map_glbzz.remove(2);
			}
			break;
		case R.id.ricketsSymptom3:
			if (isChecked) {
				map_glbzz.put(3, buttonView.getText().toString());
			} else {
				map_glbzz.remove(3);
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
		case R.id.ricketsSign3:
			if (isChecked) {
				map_glbtz.put(3, buttonView.getText().toString());
			} else {
				map_glbtz.remove(3);
			}
			break;

		//
		case R.id.hipActivity0:
			if (isChecked) {
				map_hgjhd.put(0, buttonView.getText().toString());
			} else {
				map_hgjhd.remove(0);
			}
			break;
		case R.id.hipActivity1:
			if (isChecked) {
				map_hgjhd.put(1, buttonView.getText().toString());
			} else {
				map_hgjhd.remove(1);
			}
			break;
		case R.id.hipActivity2:
			if (isChecked) {
				map_hgjhd.put(2, buttonView.getText().toString());
			} else {
				map_hgjhd.remove(2);
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == R.id.rb_qc_b) {
			isQc = true;
		} else if (checkedId == R.id.rb_qc_w) {
			isQc = false;
		}
		if (checkedId == R.id.rb_jbbk_w) {
			isJbbk = false;
		} else if (checkedId == R.id.rb_jbbk_y) {
			isJbbk = true;
		}
		if (checkedId == R.id.rb_pf_w) {
			ispf = false;
		} else if (checkedId == R.id.rb_pf_w) {
			ispf = true;
		}
		if (checkedId == R.id.rb_ywg_w) {
			isYwg = false;
		} else if (checkedId == R.id.rb_ywg_y) {
			isYwg = true;
		}
		if (checkedId == R.id.rb_ewg_w) {
			isEwg = false;
		} else if (checkedId == R.id.rb_ewg_y) {
			isEwg = true;
		}
		if (checkedId == R.id.rb_b_w) {
			isB = false;
		} else if (checkedId == R.id.rb_b_y) {
			isB = true;
		}
		if (checkedId == R.id.rb_kq_w) {
			isKq = false;
		} else if (checkedId == R.id.rb_kq_y) {
			isKq = true;
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
		if (checkedId == R.id.rb_gm_wjyc) {
			isGm = false;
		} else if (checkedId == R.id.rb_gm_yc) {
			isGm = true;
		}
	}
}
