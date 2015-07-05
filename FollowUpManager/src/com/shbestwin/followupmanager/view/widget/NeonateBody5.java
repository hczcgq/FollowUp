package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.CheckBoxItem;
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;

public class NeonateBody5 extends LinearLayout implements IBaseNeonateBody {
	private EditText et_mqtz, et_tw, et_sc, et_hxpl, et_ml, et_ms, et_qc_1,
			et_qc_2, et_qc_qt, et_ywg, et_ewg, et_b, et_kq, et_xftz, et_fbcz,
			et_szhdd, et_jbbk, et_pf, et_gm, et_wszq, et_jz, et_qd;
	private RadioGroup rg_ywg, rg_ewg, rg_b, rg_kq, rg_xftz, rg_fbcz, rg_szhdd,
			rg_jbbk, rg_gm, rg_wszq, rg_jz, rg_qd;
	private CheckBox complexion2;
	private CheckBox aurigo0;
	private CheckBox bregma3;
	private CheckBox skin3;
	private boolean isYwg = false, isEwg = false, isB = false, isKq = false,
			isXftz = false, isFbcz = false, isSzhdd = false, isJbbk = false,
			isGm = false, isWszq = false, isJz = false, isQd = false;
	private LinearLayout complexionLayout,aurigoLayout,bregmaLayout,skinLayout;
	
	private RadioButton rb_ywg_w,rb_ywg_y,rb_ewg_w,rb_ewg_y,rb_b_w,rb_b_y,rb_kq_w,rb_kq_y,rb_xftz_w,rb_xftz_y,
	rb_fbcz_w,rb_fbcz_y,rb_szhdd_w,rb_szhdd_y,rb_jbbk_w,rb_jbbk_y,rb_gm_w,rb_gm_y,rb_wszq_w,rb_wszq_y,rb_jz_w,rb_jz_y,
	rb_qd_w,rb_qd_y;
	
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
		rb_ywg_w = (RadioButton) rootView.findViewById(R.id.rb_ywg_w);
		rb_ywg_y = (RadioButton) rootView.findViewById(R.id.rb_ywg_y);
		rb_ewg_w = (RadioButton) rootView.findViewById(R.id.rb_ewg_w);
		rb_ewg_y = (RadioButton) rootView.findViewById(R.id.rb_ewg_y);
		rb_b_w = (RadioButton) rootView.findViewById(R.id.rb_b_w);
		rb_b_y = (RadioButton) rootView.findViewById(R.id.rb_b_y);
		rb_kq_w = (RadioButton) rootView.findViewById(R.id.rb_kq_w);
		rb_kq_y = (RadioButton) rootView.findViewById(R.id.rb_kq_y);
		rb_xftz_w = (RadioButton) rootView.findViewById(R.id.rb_xftz_w);
		rb_xftz_y = (RadioButton) rootView.findViewById(R.id.rb_xftz_y);
		rb_fbcz_w = (RadioButton) rootView.findViewById(R.id.rb_fbcz_w);
		rb_fbcz_y = (RadioButton) rootView.findViewById(R.id.rb_fbcz_y);
		rb_szhdd_w = (RadioButton) rootView.findViewById(R.id.rb_szhdd_w);
		rb_szhdd_y = (RadioButton) rootView.findViewById(R.id.rb_szhdd_y);
		rb_jbbk_w = (RadioButton) rootView.findViewById(R.id.rb_jbbk_w);
		rb_jbbk_y = (RadioButton) rootView.findViewById(R.id.rb_jbbk_y);
		rb_gm_w = (RadioButton) rootView.findViewById(R.id.rb_gm_w);
		rb_gm_y = (RadioButton) rootView.findViewById(R.id.rb_gm_y);
		rb_wszq_w = (RadioButton) rootView.findViewById(R.id.rb_wszq_w);
		rb_wszq_y = (RadioButton) rootView.findViewById(R.id.rb_wszq_y);
		rb_jz_w = (RadioButton) rootView.findViewById(R.id.rb_jz_w);
		rb_jz_y = (RadioButton) rootView.findViewById(R.id.rb_jz_y);
		rb_qd_w = (RadioButton) rootView.findViewById(R.id.rb_qd_w);
		rb_qd_y = (RadioButton) rootView.findViewById(R.id.rb_qd_y);

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
		
		
		

		complexion2 = (CheckBox) rootView.findViewById(R.id.complexion2);
		aurigo0 = (CheckBox) rootView.findViewById(R.id.aurigo0);
		bregma3 = (CheckBox) rootView.findViewById(R.id.bregma3);
		skin3 = (CheckBox) rootView.findViewById(R.id.skin3);
		complexionLayout = (LinearLayout) rootView.findViewById(R.id.complexionLayout);
		aurigoLayout = (LinearLayout) rootView.findViewById(R.id.aurigoLayout);
		bregmaLayout = (LinearLayout) rootView.findViewById(R.id.bregmaLayout);
		skinLayout = (LinearLayout) rootView.findViewById(R.id.skinLayout);

		ViewDataUtil.initOtherCheckboxConstraint(complexion2, et_ms);
		ViewDataUtil.initOtherCheckboxConstraint(bregma3, et_qc_qt);
		ViewDataUtil.initOtherCheckboxConstraint(skin3, et_pf);
		aurigo0
		.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				setCheckBoxStatus(aurigoLayout, isChecked);
			}
		});
	}

	@Override
	public void getData(FollowUpNewborn followUpNewborn) {
		

		
		followUpNewborn.setTgjcqk_mqtz(et_mqtz.getText().toString());
		followUpNewborn.setTgjcqk_tw(et_tw.getText().toString());
		followUpNewborn.setTgjcqk_sc(et_sc.getText().toString());
		followUpNewborn.setTgjcqk_hxpl(et_hxpl.getText().toString());
		followUpNewborn.setTgjcqk_ml(et_ml.getText().toString());
		followUpNewborn.setTgjcqk_qx(et_qc_1.getText().toString()+"/"+et_qc_2.getText().toString());
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
		
		followUpNewborn.setTgjcqk_ms(getCheckBoxText(complexionLayout, et_ms));
		followUpNewborn.setTgjcqk_hdbw(getCheckBoxText(aurigoLayout, null));
		followUpNewborn.setTgjcqk_qxjl(getCheckBoxText(bregmaLayout, et_qc_qt));
		followUpNewborn.setTgjcqk_pf(getCheckBoxText(skinLayout, et_pf));
	}

	@Override
	public void setData(FollowUpNewborn followUpNewborn) {

		if (followUpNewborn != null) {
			et_mqtz.setText(followUpNewborn.getTgjcqk_mqtz());
			et_sc.setText(followUpNewborn.getTgjcqk_sc());
			et_tw.setText(followUpNewborn.getTgjcqk_tw());
			et_hxpl.setText(followUpNewborn.getTgjcqk_hxpl());
			et_ml.setText(followUpNewborn.getTgjcqk_ml());
			try {
				setCheckBoxText(complexionLayout, et_ms,
						JsonUtil.jsonToObjects(
								followUpNewborn.getTgjcqk_ms(),
								CheckBoxItem.class));
				setCheckBoxText(aurigoLayout, null,
						JsonUtil.jsonToObjects(
								followUpNewborn.getTgjcqk_hdbw(),
								CheckBoxItem.class));
				setCheckBoxText(bregmaLayout, et_qc_qt,
						JsonUtil.jsonToObjects(
								followUpNewborn.getTgjcqk_qxjl(),
								CheckBoxItem.class));
				setCheckBoxText(skinLayout, et_pf,
						JsonUtil.jsonToObjects(
								followUpNewborn.getTgjcqk_pf(),
								CheckBoxItem.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			String qc = followUpNewborn.getTgjcqk_qx();
			if (qc.split("/").length == 1) {
				et_qc_1.setText(qc.split("/")[0]);
			} else if (qc.split("/").length == 2) {
				et_qc_1.setText(qc.split("/")[0]);
				et_qc_2.setText(qc.split("/")[1]);
			}
			et_jbbk.setText(followUpNewborn.getTgjcqk_sfjbbkycms());
			et_ywg.setText(followUpNewborn.getTgjcqk_sfwszqycms());
			et_ewg.setText(followUpNewborn.getTgjcqk_sfewgycms());
			et_b.setText(followUpNewborn.getTgjcqk_sfbycms());
			et_kq.setText(followUpNewborn.getTgjcqk_sfkqycms());
			et_xftz.setText(followUpNewborn.getTgjcqk_sfxftzycms());
			et_fbcz.setText(followUpNewborn.getTgjcqk_sffbczycms());
			et_szhdd.setText(followUpNewborn.getTgjcqk_sfszhddycms());
			et_gm.setText(followUpNewborn.getTgjcqk_sfgmycms());
			et_wszq.setText(followUpNewborn.getTgjcqk_sfwszqycms());
			et_qd.setText(followUpNewborn.getTgjcqk_sfqdycms());
			et_jz.setText(followUpNewborn.getTgjcqk_sfjzycms());
			
			if (followUpNewborn.getTgjcqk_sfywgyc()) {
				rb_ywg_y.setChecked(true);
				rb_ywg_w.setChecked(false);
			} else {
				rb_ywg_y.setChecked(false);
				rb_ywg_w.setChecked(true);
			}

			if (followUpNewborn.getTgjcqk_sfewgyc()) {
				rb_wszq_y.setChecked(true);
				rb_wszq_w.setChecked(false);
			} else {
				rb_wszq_y.setChecked(false);
				rb_wszq_w.setChecked(true);
			}

			if (followUpNewborn.getTgjcqk_sfbyc()) {
				rb_b_y.setChecked(true);
				rb_b_w.setChecked(false);
			} else {
				rb_b_y.setChecked(false);
				rb_b_w.setChecked(true);
			}

			if (followUpNewborn.getTgjcqk_sfkqyc()) {
				rb_kq_y.setChecked(true);
				rb_kq_w.setChecked(false);
			} else {
				rb_kq_y.setChecked(false);
				rb_kq_w.setChecked(true);
			}

			if (followUpNewborn.getTgjcqk_sfxftzyc()) {
				rb_xftz_y.setChecked(true);
				rb_xftz_w.setChecked(false);
			} else {
				rb_xftz_y.setChecked(false);
				rb_xftz_w.setChecked(true);
			}

			if (followUpNewborn.getTgjcqk_sffbczyc()) {
				rb_fbcz_y.setChecked(true);
				rb_fbcz_w.setChecked(false);
			} else {
				rb_fbcz_y.setChecked(false);
				rb_fbcz_w.setChecked(true);
			}
			if (followUpNewborn.getTgjcqk_sfszhddyc()) {
				rb_szhdd_y.setChecked(true);
				rb_szhdd_w.setChecked(false);
			} else {
				rb_szhdd_y.setChecked(false);
				rb_szhdd_w.setChecked(true);
			}
			if (followUpNewborn.getTgjcqk_sfjbbkyc()) {
				rb_jbbk_w.setChecked(true);
				rb_jbbk_y.setChecked(false);
			} else {
				rb_jbbk_w.setChecked(false);
				rb_jbbk_y.setChecked(true);
			}
			if (followUpNewborn.getTgjcqk_sfgmyc()) {
				rb_gm_y.setChecked(true);
				rb_gm_w.setChecked(false);
			} else {
				rb_gm_y.setChecked(false);
				rb_gm_w.setChecked(true);
			}
			if (followUpNewborn.getTgjcqk_sfwszqyc()) {
				rb_wszq_y.setChecked(true);
				rb_wszq_w.setChecked(false);
			} else {
				rb_wszq_y.setChecked(false);
				rb_wszq_w.setChecked(true);
			}
			if (followUpNewborn.getTgjcqk_sfjzyc()) {
				rb_jz_y.setChecked(true);
				rb_jz_y.setChecked(false);
			} else {
				rb_jz_y.setChecked(false);
				rb_jz_w.setChecked(true);
			}
			if (followUpNewborn.getTgjcqk_sfqdyc()) {
				rb_qd_y.setChecked(true);
				rb_qd_w.setChecked(false);
			} else {
				rb_qd_y.setChecked(false);
				rb_qd_w.setChecked(true);
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
	
	private String getCheckBoxText(View layout, EditText editText) {
		LinearLayout linearLayout = (LinearLayout) layout;
		List<CheckBoxItem> mArrayList = new ArrayList<CheckBoxItem>();
		for (int i = 0; i < linearLayout.getChildCount(); i++) {
			View item = linearLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					CheckBoxItem entity = new CheckBoxItem();
					entity.setItem_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText()) && editText != null) {
						entity.setItem_name(editText.getText().toString());
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
		LinearLayout myLayout = (LinearLayout) layout;
		for (int i = 0; i < mList.size(); i++) {
			int Num = Integer.valueOf(mList.get(i).getItem_num());
			String name = mList.get(i).getItem_name();
			if ((View) (myLayout).getChildAt(Num) instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) (View) (myLayout)
						.getChildAt(Num);
				checkBox.setChecked(true);
				if (textview != null) {
					if (Num == myLayout.getChildCount() - 2) {
						textview.setText(name);
					}
				}
			}
		}
	}

	private void setCheckBoxStatus(LinearLayout familyHistory, boolean isChecked) {
		for (int i = 2; i < familyHistory.getChildCount(); i++) {
			View item = familyHistory.getChildAt(i);
			if (item instanceof CheckBox) {
				((CheckBox) item).setEnabled(!isChecked);
				if (isChecked) {
					((CheckBox) item).setChecked(!isChecked);
				}
			}
		}
	}

}
