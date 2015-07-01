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
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;

public class NeonateBody5 extends LinearLayout implements IBaseNeonateBody {
	private EditText et_mqtz, et_tw, et_sc, et_hxpl, et_ml, et_ms, et_qc_1,
			et_qc_2, et_qc_qt, et_ywg, et_ewg, et_b, et_kq, et_xftz, et_fbcz,
			et_szhdd, et_jbbk, et_pf, et_gm, et_wszq, et_jz, et_qd;
	private RadioGroup rg_ywg, rg_ewg, rg_b, rg_kq, rg_xftz, rg_fbcz, rg_szhdd,
			rg_jbbk, rg_gm, rg_wszq, rg_jz, rg_qd;
	private CheckBox complexion0, complexion1, complexion2;
	private CheckBox aurigo0, aurigo1, aurigo2, aurigo3, aurigo4;
	private CheckBox bregma0, bregma1, bregma2, bregma3;
	private CheckBox skin0, skin1, skin2, skin3;
	private boolean isYwg = false, isEwg = false, isB = false, isKq = false,
			isXftz = false, isFbcz = false, isSzhdd = false, isJbbk = false,
			isGm = false, isWszq = false, isJz = false, isQd = false;

	private HashMap<Integer, String> map_ms = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_hdbw = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_qc = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_pf = new HashMap<Integer, String>();

	public NeonateBody5(Context context) {
		this(context, null);
	}

	public NeonateBody5(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NeonateBody5(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_neonate_body5, this, true);

		et_mqtz = (EditText) rootView.findViewById(R.id.et_mqtz);
		et_tw = (EditText) rootView.findViewById(R.id.et_tw);
		et_sc = (EditText) rootView.findViewById(R.id.et_sc);
		et_hxpl = (EditText) rootView.findViewById(R.id.et_hxpl);
		et_ml = (EditText) rootView.findViewById(R.id.et_ml);
		et_ms = (EditText) rootView.findViewById(R.id.et_ms);
		et_qc_1 = (EditText) rootView.findViewById(R.id.et_qc_1);
		et_qc_2 = (EditText) rootView.findViewById(R.id.et_qc_2);
		et_qc_qt = (EditText) rootView.findViewById(R.id.et_qc_qt);
		et_ywg = (EditText) rootView.findViewById(R.id.et_ywg);
		et_ewg = (EditText) rootView.findViewById(R.id.et_ewg);
		et_b = (EditText) rootView.findViewById(R.id.et_b);
		et_kq = (EditText) rootView.findViewById(R.id.et_kq);
		et_xftz = (EditText) rootView.findViewById(R.id.et_xftz);
		et_fbcz = (EditText) rootView.findViewById(R.id.et_fbcz);
		et_szhdd = (EditText) rootView.findViewById(R.id.et_szhdd);
		et_jbbk = (EditText) rootView.findViewById(R.id.et_jbbk);
		et_pf = (EditText) rootView.findViewById(R.id.et_pf);
		et_gm = (EditText) rootView.findViewById(R.id.et_gm);
		et_wszq = (EditText) rootView.findViewById(R.id.et_wszq);
		et_jz = (EditText) rootView.findViewById(R.id.et_jz);
		et_qd = (EditText) rootView.findViewById(R.id.et_qd);

		rg_ywg = (RadioGroup) rootView.findViewById(R.id.rg_ywg);
		rg_ewg = (RadioGroup) rootView.findViewById(R.id.rg_ewg);
		rg_b = (RadioGroup) rootView.findViewById(R.id.rg_b);
		rg_kq = (RadioGroup) rootView.findViewById(R.id.rg_kq);
		rg_xftz = (RadioGroup) rootView.findViewById(R.id.rg_xftz);
		rg_fbcz = (RadioGroup) rootView.findViewById(R.id.rg_fbcz);
		rg_szhdd = (RadioGroup) rootView.findViewById(R.id.rg_szhdd);
		rg_jbbk = (RadioGroup) rootView.findViewById(R.id.rg_jbbk);
		rg_gm = (RadioGroup) rootView.findViewById(R.id.rg_gm);
		rg_wszq = (RadioGroup) rootView.findViewById(R.id.rg_wszq);
		rg_jz = (RadioGroup) rootView.findViewById(R.id.rg_jz);
		rg_qd = (RadioGroup) rootView.findViewById(R.id.rg_qd);

		rg_ywg.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_ywg_w) {
					isYwg = false;
				} else if (checkedId == R.id.rb_ywg_y) {
					isYwg = true;
				}
			}
		});
		rg_ewg.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_ewg_w) {
					isEwg = false;
				} else if (checkedId == R.id.rb_ewg_y) {
					isEwg = true;
				}
			}
		});
		rg_b.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_b_w) {
					isB = false;
				} else if (checkedId == R.id.rb_b_y) {
					isB = true;
				}
			}
		});
		rg_kq.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_kq_w) {
					isKq = false;
				} else if (checkedId == R.id.rb_kq_y) {
					isKq = true;
				}
			}
		});
		rg_xftz.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_xftz_w) {
					isXftz = false;
				} else if (checkedId == R.id.rb_xftz_y) {
					isXftz = true;
				}
			}
		});
		rg_fbcz.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_fbcz_w) {
					isXftz = false;
				} else if (checkedId == R.id.rb_fbcz_y) {
					isXftz = true;
				}
			}
		});
		rg_szhdd.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_szhdd_w) {
					isSzhdd = false;
				} else if (checkedId == R.id.rb_szhdd_y) {
					isSzhdd = true;
				}
			}
		});
		rg_jbbk.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_jbbk_w) {
					isJbbk = false;
				} else if (checkedId == R.id.rb_jbbk_y) {
					isJbbk = true;
				}
			}
		});
		rg_gm.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_gm_w) {
					isGm = false;
				} else if (checkedId == R.id.rb_gm_y) {
					isGm = true;
				}
			}
		});
		rg_wszq.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_wszq_w) {
					isWszq = false;
				} else if (checkedId == R.id.rb_wszq_y) {
					isWszq = true;
				}
			}
		});
		rg_jz.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_jz_w) {
					isJz = false;
				} else if (checkedId == R.id.rb_jz_y) {
					isJz = true;
				}
			}
		});
		rg_qd.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_qd_w) {
					isQd = false;
				} else if (checkedId == R.id.rb_qd_y) {
					isQd = true;
				}
			}
		});
		
		
		

		complexion0 = (CheckBox) rootView.findViewById(R.id.complexion0);
		complexion1 = (CheckBox) rootView.findViewById(R.id.complexion1);
		complexion2 = (CheckBox) rootView.findViewById(R.id.complexion2);

		complexion0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_ms.put(0, buttonView.getText().toString());
				} else {
					map_ms.remove(0);
				}
			}
		});
		complexion1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_ms.put(1, buttonView.getText().toString());
				} else {
					map_ms.remove(1);
				}
			}
		});

		aurigo0 = (CheckBox) rootView.findViewById(R.id.aurigo0);
		aurigo1 = (CheckBox) rootView.findViewById(R.id.aurigo1);
		aurigo2 = (CheckBox) rootView.findViewById(R.id.aurigo2);
		aurigo3 = (CheckBox) rootView.findViewById(R.id.aurigo3);
		aurigo4 = (CheckBox) rootView.findViewById(R.id.aurigo4);

		aurigo0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_hdbw.put(0, buttonView.getText().toString());
				} else {
					map_hdbw.remove(0);
				}
			}
		});
		aurigo1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_hdbw.put(1, buttonView.getText().toString());
				} else {
					map_hdbw.remove(1);
				}
			}
		});
		aurigo2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_hdbw.put(2, buttonView.getText().toString());
				} else {
					map_hdbw.remove(2);
				}
			}
		});
		aurigo3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_hdbw.put(3, buttonView.getText().toString());
				} else {
					map_hdbw.remove(3);
				}
			}
		});
		aurigo4.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_hdbw.put(4, buttonView.getText().toString());
				} else {
					map_hdbw.remove(4);
				}
			}
		});

		bregma0 = (CheckBox) rootView.findViewById(R.id.bregma0);
		bregma1 = (CheckBox) rootView.findViewById(R.id.bregma1);
		bregma2 = (CheckBox) rootView.findViewById(R.id.bregma2);
		bregma3 = (CheckBox) rootView.findViewById(R.id.bregma3);

		bregma0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_qc.put(0, buttonView.getText().toString());
				} else {
					map_qc.remove(0);
				}
			}
		});
		bregma1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_qc.put(1, buttonView.getText().toString());
				} else {
					map_qc.remove(1);
				}
			}
		});
		bregma2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_qc.put(2, buttonView.getText().toString());
				} else {
					map_qc.remove(2);
				}
			}
		});

		skin0 = (CheckBox) rootView.findViewById(R.id.skin0);
		skin1 = (CheckBox) rootView.findViewById(R.id.skin1);
		skin2 = (CheckBox) rootView.findViewById(R.id.skin2);
		skin3 = (CheckBox) rootView.findViewById(R.id.skin3);

		skin0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_pf.put(0, buttonView.getText().toString());
				} else {
					map_pf.remove(0);
				}
			}
		});
		skin1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_pf.put(1, buttonView.getText().toString());
				} else {
					map_pf.remove(1);
				}
			}
		});
		skin2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_pf.put(2, buttonView.getText().toString());
				} else {
					map_pf.remove(2);
				}
			}
		});
	}

	@Override
	public void getData(FollowUpNewborn followUpNewborn) {
		if (complexion2.isChecked()) {
			map_ms.put(2, et_ms.getText().toString());
		}

		JsonObject jsonObject_ms = new JsonObject();
		JsonArray jsonArray_ms = new JsonArray();
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

		JsonObject jsonObject_hdbw = new JsonObject();
		JsonArray jsonArray_hdbw = new JsonArray();
		Iterator iterator_hdbw = map_hdbw.entrySet().iterator();
		while (iterator_hdbw.hasNext()) {
			Map.Entry entry = (Entry) iterator_hdbw.next();
			JsonObject object = new JsonObject();
			object.addProperty("tgjcqk_hdbw_num",
					String.valueOf(entry.getKey()));
			object.addProperty("tgjcqk_hdbw_name",
					String.valueOf(entry.getValue()));
			jsonArray_hdbw.add(object);
		}
		jsonObject_hdbw.add("tgjcqk_hdbw", jsonArray_hdbw);

		if (bregma3.isChecked()) {
			map_qc.put(3, et_qc_qt.getText().toString());
		}
		JsonObject jsonObject_qc = new JsonObject();
		JsonArray jsonArray_qc = new JsonArray();
		Iterator iterator_qc = map_qc.entrySet().iterator();
		while (iterator_qc.hasNext()) {
			Map.Entry entry = (Entry) iterator_qc.next();
			JsonObject object = new JsonObject();
			object.addProperty("tgjcqk_qxjl_num",
					String.valueOf(entry.getKey()));
			object.addProperty("tgjcqk_qxjl_name",
					String.valueOf(entry.getValue()));
			jsonArray_qc.add(object);
		}
		jsonObject_qc.add("tgjcqk_qxjl", jsonArray_qc);

		if (skin3.isChecked()) {
			map_pf.put(3, et_pf.getText().toString());
		}

		JsonObject jsonObject_pf = new JsonObject();
		JsonArray jsonArray_pf = new JsonArray();
		Iterator iterator_pf = map_pf.entrySet().iterator();
		while (iterator_pf.hasNext()) {
			Map.Entry entry = (Entry) iterator_pf.next();
			JsonObject object = new JsonObject();
			object.addProperty("tgjcqk_pf_num", String.valueOf(entry.getKey()));
			object.addProperty("tgjcqk_pf_name",
					String.valueOf(entry.getValue()));
			jsonArray_pf.add(object);
		}
		jsonObject_pf.add("tgjcqk_pf", jsonArray_pf);

		
		followUpNewborn.setTgjcqk_mqtz(et_mqtz.getText().toString());
		followUpNewborn.setTgjcqk_tw(et_tw.getText().toString());
		followUpNewborn.setTgjcqk_sc(et_sc.getText().toString());
		followUpNewborn.setTgjcqk_hxpl(et_hxpl.getText().toString());
		followUpNewborn.setTgjcqk_ml(et_ml.getText().toString());
		followUpNewborn.setTgjcqk_ms(jsonObject_ms.toString());
		followUpNewborn.setTgjcqk_hdbw(jsonObject_hdbw.toString());
		followUpNewborn.setTgjcqk_qx(et_qc_1.getText().toString()+"*"+et_qc_2.getText().toString());
		followUpNewborn.setTgjcqk_qxjl(jsonObject_qc.toString());
		followUpNewborn.setTgjcqk_sfywgyc(isYwg);
		followUpNewborn.setTgjcqk_sfywgycms(et_ywg.getText().toString());
		followUpNewborn.setTgjcqk_sfewgyc(isEwg);
		followUpNewborn.setTgjcqk_sfewgycms(et_ewg.getText().toString());
		followUpNewborn.setTgjcqk_sfbyc(isB);
		followUpNewborn.setTgjcqk_sfbycms(et_b.getText().toString());
		followUpNewborn.setTgjcqk_sfkqyc(isKq);
		followUpNewborn.setTgjcqk_sfkqycms(et_kq.getText().toString());
		followUpNewborn.setTgjcqk_sfxftzyc(isXftz);
		followUpNewborn.setTgjcqk_sfxftzycms(et_xftz.getText().toString());
		followUpNewborn.setTgjcqk_sffbczyc(isFbcz);
		followUpNewborn.setTgjcqk_sffbczycms(et_fbcz.getText().toString());
		followUpNewborn.setTgjcqk_sfszhddyc(isSzhdd);
		followUpNewborn.setTgjcqk_sfszhddycms(et_szhdd.getText().toString());
		followUpNewborn.setTgjcqk_sfjbbkyc(isJbbk);
		followUpNewborn.setTgjcqk_sfjbbkycms(et_jbbk.getText().toString());
		followUpNewborn.setTgjcqk_sfgmyc(isGm);
		followUpNewborn.setTgjcqk_sfgmycms(et_gm.getText().toString());
		followUpNewborn.setTgjcqk_sfwszqyc(isWszq);
		followUpNewborn.setTgjcqk_sfwszqycms(et_wszq.getText().toString());
		followUpNewborn.setTgjcqk_sfjzyc(isJz);
		followUpNewborn.setTgjcqk_sfjzycms(et_jz.getText().toString());
		followUpNewborn.setTgjcqk_sfqdyc(isQd);
		followUpNewborn.setTgjcqk_sfqdycms(et_qd.getText().toString());
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
