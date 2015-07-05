package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.CheckBoxItem;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body3 extends LinearLayout implements IBaseYearsOld0Body,
		OnCheckedChangeListener
		 {
	private EditText et_tz, et_sc, et_bmi, et_yw, et_ms, et_qc_1, et_qc_2,
			et_jbbk, et_pf, et_ywg, et_ewg, et_b, et_kq, et_kq_k, et_tl, et_xf,
			et_fb, et_sz, et_qb, et_wszq, et_gm;
	private Spinner sn_yypj;
	private RadioGroup rg_qc, rg_jbbk, rg_pf, rg_ywg, rg_ewg, rg_b, rg_kq,
			rg_tl, rg_xf, rg_fb, rg_sz, rg_wszq, rg_gm;
	private CheckBox complexion2;
	private CheckBox umbilicalRegion0, umbilicalRegion4;
	private CheckBox ricketsSymptom0;
	private CheckBox ricketsSign0;
	private boolean isQc = true, isJbbk = false, ispf = false, isYwg = false,
			isEwg = false, isB = false, isKq = false, isTl = true,
			isXf = false, isFb = false, isSz = false, isWszq = false,
			isGm = false;

	private LinearLayout complexionLayout, umbilicalRegionLayout,
			ricketsSymptomLayout, ricketsSignLayout, hipActivityLayout;

	private RadioButton rb_qc_bh, rb_qc_wbh, rb_pf_wjyc, rb_pf_yc, rb_ywg_wjyc,
			rb_ywg_yc, rb_ewg_wjyc, rb_ewg_yc, rb_tl_tg, rb_tl_wtg, rb_xf_wjyc,
			rb_xf_yc, rb_fb_wjyc, rb_fb_yc, rb_sz_wjyc, rb_sz_yc, rb_wszq_wjyc,
			rb_wszq_yc,rb_b_wjyc,rb_b_yc,rb_kq_wjyc,rb_kq_yc,rb_jbbk_w,rb_jbbk_y;

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
		
		rb_qc_bh=(RadioButton) rootView.findViewById(R.id.rb_qc_bh);
		rb_qc_wbh=(RadioButton) rootView.findViewById(R.id.rb_qc_wbh);
		rb_pf_wjyc=(RadioButton) rootView.findViewById(R.id.rb_pf_wjyc);
		rb_pf_yc=(RadioButton) rootView.findViewById(R.id.rb_pf_yc);
		rb_ywg_wjyc=(RadioButton) rootView.findViewById(R.id.rb_ywg_wjyc);
		rb_ywg_yc=(RadioButton) rootView.findViewById(R.id.rb_ywg_yc);
		rb_ewg_wjyc=(RadioButton) rootView.findViewById(R.id.rb_ewg_wjyc);
		rb_ewg_yc=(RadioButton) rootView.findViewById(R.id.rb_ewg_yc);
		rb_tl_tg=(RadioButton) rootView.findViewById(R.id.rb_tl_tg);
		rb_tl_wtg=(RadioButton) rootView.findViewById(R.id.rb_tl_wtg);
		rb_xf_wjyc=(RadioButton) rootView.findViewById(R.id.rb_xf_wjyc);
		rb_xf_yc=(RadioButton) rootView.findViewById(R.id.rb_xf_yc);
		rb_fb_wjyc=(RadioButton) rootView.findViewById(R.id.rb_fb_wjyc);
		rb_fb_yc=(RadioButton) rootView.findViewById(R.id.rb_fb_yc);
		rb_sz_wjyc=(RadioButton) rootView.findViewById(R.id.rb_sz_wjyc);
		rb_sz_yc=(RadioButton) rootView.findViewById(R.id.rb_sz_yc);
		rb_wszq_wjyc=(RadioButton) rootView.findViewById(R.id.rb_wszq_wjyc);
		rb_wszq_yc=(RadioButton) rootView.findViewById(R.id.rb_wszq_yc);
		rb_b_wjyc=(RadioButton) rootView.findViewById(R.id.rb_b_wjyc);
		rb_b_yc=(RadioButton) rootView.findViewById(R.id.rb_b_yc);
		rb_kq_wjyc=(RadioButton) rootView.findViewById(R.id.rb_kq_wjyc);
		rb_kq_yc=(RadioButton) rootView.findViewById(R.id.rb_kq_yc);
		rb_jbbk_w=(RadioButton) rootView.findViewById(R.id.rb_jbbk_w);
		rb_jbbk_y=(RadioButton) rootView.findViewById(R.id.rb_jbbk_y);

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

		complexion2 = (CheckBox) rootView.findViewById(R.id.complexion2);

		umbilicalRegion0 = (CheckBox) rootView
				.findViewById(R.id.umbilicalRegion0);
		umbilicalRegion4 = (CheckBox) rootView
				.findViewById(R.id.umbilicalRegion4);
		ricketsSymptom0 = (CheckBox) rootView
				.findViewById(R.id.ricketsSymptom0);
		ricketsSign0 = (CheckBox) rootView.findViewById(R.id.ricketsSign0);
		complexionLayout = (LinearLayout) rootView
				.findViewById(R.id.complexionLayout);
		umbilicalRegionLayout = (LinearLayout) rootView
				.findViewById(R.id.umbilicalRegionLayout);
		ricketsSymptomLayout = (LinearLayout) rootView
				.findViewById(R.id.ricketsSymptomLayout);
		ricketsSignLayout = (LinearLayout) rootView
				.findViewById(R.id.ricketsSignLayout);
		hipActivityLayout = (LinearLayout) rootView
				.findViewById(R.id.hipActivityLayout);
		ViewDataUtil.initOtherCheckboxConstraint(complexion2, et_ms);
		ViewDataUtil.initOtherCheckboxConstraint(umbilicalRegion4, et_qb);
		ricketsSymptom0
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						setCheckBoxStatus(ricketsSymptomLayout, isChecked);
					}
				});
		ricketsSign0
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						setCheckBoxStatus(ricketsSignLayout, isChecked);
					}
				});
	}

	@Override
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		followUpOneNewborn.setTgjcqk_tz(et_tz.getText().toString());
		followUpOneNewborn.setTgjcqk_sc(et_sc.getText().toString());
		followUpOneNewborn.setTgjcqk_bmi(et_bmi.getText().toString());
		followUpOneNewborn.setTgjcqk_yw(et_yw.getText().toString());
		followUpOneNewborn.setTgjcqk_yypj(ViewDataUtil.getSpinnerData(sn_yypj));

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

		followUpOneNewborn.setTgjcqk_sfwszqyc(isWszq);
		followUpOneNewborn.setTgjcqk_sfwszqycms(et_wszq.getText().toString());
		followUpOneNewborn.setTgjcqk_sfgmyc(isGm);
		followUpOneNewborn.setTgjcqk_sfgmycms(et_gm.getText().toString());
		followUpOneNewborn
				.setTgjcqk_ms(getCheckBoxText(complexionLayout, et_ms));
		followUpOneNewborn.setTgjcqk_qb(getCheckBoxText(umbilicalRegionLayout,
				et_qb));
		followUpOneNewborn.setTgjcqk_glbzz(getCheckBoxText(
				ricketsSymptomLayout, null));
		followUpOneNewborn.setTgjcqk_glbtz(getCheckBoxText(ricketsSignLayout,
				null).toString());
		followUpOneNewborn.setTgjcqk_kgjhd(getCheckBoxText(hipActivityLayout,
				null));

	}

	@Override
	public void setData(FollowUpOneNewborn followUpOneNewborn) {

		if (followUpOneNewborn != null) {
			et_tz.setText(followUpOneNewborn.getTgjcqk_tz());
			et_sc.setText(followUpOneNewborn.getTgjcqk_sc());
			et_bmi.setText(followUpOneNewborn.getTgjcqk_bmi());
			et_yw.setText(followUpOneNewborn.getTgjcqk_yw());
			ViewDataUtil.setSpinnerData(sn_yypj,
					followUpOneNewborn.getTgjcqk_yypj());
			try {
				setCheckBoxText(complexionLayout, et_ms,
						JsonUtil.jsonToObjects(
								followUpOneNewborn.getTgjcqk_ms(),
								CheckBoxItem.class));
				setCheckBoxText(umbilicalRegionLayout, et_qb,
						JsonUtil.jsonToObjects(
								followUpOneNewborn.getTgjcqk_qb(),
								CheckBoxItem.class));
				setCheckBoxText(ricketsSymptomLayout, null,
						JsonUtil.jsonToObjects(
								followUpOneNewborn.getTgjcqk_glbzz(),
								CheckBoxItem.class));
				setCheckBoxText(ricketsSignLayout, null,
						JsonUtil.jsonToObjects(
								followUpOneNewborn.getTgjcqk_glbtz(),
								CheckBoxItem.class));
				setCheckBoxText(hipActivityLayout, null,
						JsonUtil.jsonToObjects(
								followUpOneNewborn.getTgjcqk_kgjhd(),
								CheckBoxItem.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			String qc = followUpOneNewborn.getTgjcqk_sfqxbhms();
			if (qc.split("/").length == 1) {
				et_qc_1.setText(qc.split("/")[0]);
			} else if (qc.split("/").length == 2) {
				et_qc_1.setText(qc.split("/")[0]);
				et_qc_2.setText(qc.split("/")[1]);
			}
			et_jbbk.setText(followUpOneNewborn.getTgjcqk_sfjbbkms());
			et_pf.setText(followUpOneNewborn.getTgjcqk_sfpfycms());
			et_ywg.setText(followUpOneNewborn.getTgjcqk_sfwszqycms());
			et_ewg.setText(followUpOneNewborn.getTgjcqk_sfewgycms());
			et_b.setText(followUpOneNewborn.getTgjcqk_sfbycms());
			et_kq.setText(followUpOneNewborn.getTgjcqk_sfkqycms());
			et_tl.setText(followUpOneNewborn.getTgjcqk_sftlycms());
			et_xf.setText(followUpOneNewborn.getTgjcqk_sfxftzycms());
			et_fb.setText(followUpOneNewborn.getTgjcqk_sffbczycms());
			et_sz.setText(followUpOneNewborn.getTgjcqk_sfszhddycms());
			et_wszq.setText(followUpOneNewborn.getTgjcqk_sfwszqycms());
			et_gm.setText(followUpOneNewborn.getTgjcqk_sfgmycms());

			if (followUpOneNewborn.getTgjcqk_sfqxbh()) {
				rb_qc_bh.setChecked(true);
				rb_qc_wbh.setChecked(false);
			} else {
				rb_qc_bh.setChecked(false);
				rb_qc_wbh.setChecked(true);
			}

			if (followUpOneNewborn.getTgjcqk_sfpfyc()) {
				rb_pf_yc.setChecked(true);
				rb_pf_wjyc.setChecked(false);
			} else {
				rb_pf_yc.setChecked(false);
				rb_pf_wjyc.setChecked(true);
			}

			if (followUpOneNewborn.getTgjcqk_sfywgyc()) {
				rb_ywg_yc.setChecked(true);
				rb_ywg_wjyc.setChecked(false);
			} else {
				rb_ywg_yc.setChecked(false);
				rb_ywg_wjyc.setChecked(true);
			}

			if (followUpOneNewborn.getTgjcqk_sfewgyc()) {
				rb_ewg_yc.setChecked(true);
				rb_ewg_wjyc.setChecked(false);
			} else {
				rb_ewg_yc.setChecked(false);
				rb_ewg_wjyc.setChecked(true);
			}

			if (followUpOneNewborn.getTgjcqk_sftlyc()) {
				rb_tl_tg.setChecked(true);
				rb_tl_wtg.setChecked(false);
			} else {
				rb_tl_tg.setChecked(false);
				rb_tl_wtg.setChecked(true);
			}

			if (followUpOneNewborn.getTgjcqk_sfxftzyc()) {
				rb_xf_yc.setChecked(true);
				rb_xf_wjyc.setChecked(false);
			} else {
				rb_xf_yc.setChecked(false);
				rb_xf_wjyc.setChecked(true);
			}
			if (followUpOneNewborn.getTgjcqk_sffbczyc()) {
				rb_fb_yc.setChecked(true);
				rb_fb_wjyc.setChecked(false);
			} else {
				rb_fb_yc.setChecked(false);
				rb_fb_wjyc.setChecked(true);
			}
			if (followUpOneNewborn.getTgjcqk_sfszhddyc()) {
				rb_sz_yc.setChecked(true);
				rb_sz_wjyc.setChecked(false);
			} else {
				rb_sz_yc.setChecked(false);
				rb_sz_wjyc.setChecked(true);
			}
			if (followUpOneNewborn.getTgjcqk_sfwszqyc()) {
				rb_wszq_yc.setChecked(true);
				rb_wszq_wjyc.setChecked(false);
			} else {
				rb_wszq_yc.setChecked(false);
				rb_wszq_wjyc.setChecked(true);
			}
			if (followUpOneNewborn.getTgjcqk_sfbyc()) {
				rb_b_yc.setChecked(true);
				rb_b_wjyc.setChecked(false);
			} else {
				rb_b_yc.setChecked(false);
				rb_b_wjyc.setChecked(true);
			}
			if (followUpOneNewborn.getTgjcqk_sfkqyc()) {
				rb_kq_yc.setChecked(true);
				rb_kq_wjyc.setChecked(false);
			} else {
				rb_kq_yc.setChecked(false);
				rb_kq_wjyc.setChecked(true);
			}
			if (followUpOneNewborn.getTgjcqk_sfjbbk()) {
				rb_jbbk_y.setChecked(true);
				rb_jbbk_w.setChecked(false);
			} else {
				rb_jbbk_y.setChecked(false);
				rb_jbbk_w.setChecked(true);
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

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == R.id.rb_qc_bh) {
			isQc = true;
		} else if (checkedId == R.id.rb_qc_wbh) {
			isQc = false;
		}
		if (checkedId == R.id.rb_jbbk_w) {
			isJbbk = false;
		} else if (checkedId == R.id.rb_jbbk_y) {
			isJbbk = true;
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
		if (checkedId == R.id.rb_b_wjyc) {
			isB = false;
		} else if (checkedId == R.id.rb_b_yc) {
			isB = true;
		}
		if (checkedId == R.id.rb_kq_wjyc) {
			isKq = false;
		} else if (checkedId == R.id.rb_kq_yc) {
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
