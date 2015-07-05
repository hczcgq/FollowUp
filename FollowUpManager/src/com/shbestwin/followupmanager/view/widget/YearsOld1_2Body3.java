package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;
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
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.CheckBoxItem;
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;

public class YearsOld1_2Body3 extends LinearLayout implements
		IBaseYearsOld1_2Body, OnCheckedChangeListener {
	private EditText et_tz, et_sc, et_bmi, et_tw, et_ms, et_qc_1, et_qc_2,
			et_pf, et_ywg, et_ewg, et_tl, et_cyzcs, et_xf, et_fb, et_sz,
			et_wszq, et_glbtz;
	private Spinner sn_yypj, sn_cyzcs;
	private RadioGroup rg_qc, rg_pf, rg_ywg, rg_ewg, rg_tl, rg_xf, rg_fb,
			rg_sz, rg_wszq;
	private RadioButton rb_qc_bh,rb_qc_wbh,rb_pf_wjyc,rb_pf_yc,rb_ywg_wjyc,rb_ywg_yc,rb_ewg_wjyc,rb_ewg_yc,
	rb_tl_tg,rb_tl_wtg,rb_xf_wjyc,rb_xf_yc,rb_fb_wjyc,rb_fb_yc,rb_sz_wjyc,rb_sz_yc,rb_wszq_wjyc,rb_wszq_yc;
	private CheckBox complexion2;
	private CheckBox ricketsSign0, ricketsSign2;
	private boolean isQc = true, ispf = false, isYwg = false, isEwg = false,
			isTl = true, isXf = false, isFb = false, isSz = false,
			isWszq = false;

	private LinearLayout complexionLayout, ricketsSignLayout;

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
		rg_wszq = (RadioGroup) rootView.findViewById(R.id.rg_wszq);
		rg_qc.setOnCheckedChangeListener(this);
		rg_pf.setOnCheckedChangeListener(this);
		rg_ywg.setOnCheckedChangeListener(this);
		rg_ewg.setOnCheckedChangeListener(this);
		rg_tl.setOnCheckedChangeListener(this);
		rg_xf.setOnCheckedChangeListener(this);
		rg_fb.setOnCheckedChangeListener(this);
		rg_sz.setOnCheckedChangeListener(this);
		rg_wszq.setOnCheckedChangeListener(this);

		complexion2 = (CheckBox) rootView.findViewById(R.id.complexion2);
		ricketsSign0 = (CheckBox) rootView.findViewById(R.id.ricketsSign0);
		ricketsSign2 = (CheckBox) rootView.findViewById(R.id.ricketsSign2);
		complexionLayout = (LinearLayout) rootView
				.findViewById(R.id.complexionLayout);
		ricketsSignLayout = (LinearLayout) rootView
				.findViewById(R.id.ricketsSignLayout);
		ViewDataUtil.initOtherCheckboxConstraint(complexion2, et_ms);
		ViewDataUtil.initOtherCheckboxConstraint(ricketsSign2, et_glbtz);
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
	public void getData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		followUpOneTwoNewborn.setTgjcqk_tz(et_tz.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_sc(et_sc.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_bmi(et_bmi.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_tw(et_tw.getText().toString());
		followUpOneTwoNewborn.setTgjcqk_yypj(ViewDataUtil.getSpinnerData(sn_yypj));
		followUpOneTwoNewborn.setTgjcqk_sfqxbh(isQc);
		followUpOneTwoNewborn.setTgjcqk_sfqxbhms(et_qc_1.getText().toString()+ "/" + et_qc_2.getText().toString());
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
		followUpOneTwoNewborn.setTgjcqk_glbtz(getCheckBoxText(ricketsSignLayout, et_glbtz));
		followUpOneTwoNewborn.setTgjcqk_ms(getCheckBoxText(complexionLayout, et_ms));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		if(followUpOneTwoNewborn!=null){
			et_tz.setText(followUpOneTwoNewborn.getTgjcqk_tz());
			et_sc.setText(followUpOneTwoNewborn.getTgjcqk_sc());
			et_bmi.setText(followUpOneTwoNewborn.getTgjcqk_bmi());
			et_tw.setText(followUpOneTwoNewborn.getTgjcqk_tw());
			ViewDataUtil.setSpinnerData(sn_yypj, followUpOneTwoNewborn.getTgjcqk_yypj());
			try {
				setCheckBoxText(complexionLayout, et_ms,
						JsonUtil.jsonToObjects(
								followUpOneTwoNewborn.getTgjcqk_ms(),
								CheckBoxItem.class));
				setCheckBoxText(ricketsSignLayout, et_glbtz, JsonUtil.jsonToObjects(
								followUpOneTwoNewborn.getTgjcqk_glbtz(),
								CheckBoxItem.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			String qc=followUpOneTwoNewborn.getTgjcqk_sfqxbhms();
			if(qc.split("/").length==1){
				et_qc_1.setText(qc.split("/")[0]);
			}else if(qc.split("/").length==2){
				et_qc_1.setText(qc.split("/")[0]);
				et_qc_2.setText(qc.split("/")[1]);
			}
			et_pf.setText(followUpOneTwoNewborn.getTgjcqk_sfpfycms());
			et_ywg.setText(followUpOneTwoNewborn.getTgjcqk_sfwszqycms());
			et_ewg.setText(followUpOneTwoNewborn.getTgjcqk_sfewgycms());
			et_tl.setText(followUpOneTwoNewborn.getTgjcqk_sftlycms());
			et_cyzcs.setText(followUpOneTwoNewborn.getTgjcqk_cyqcs());
			et_xf.setText(followUpOneTwoNewborn.getTgjcqk_sfxftzycms());
			et_fb.setText(followUpOneTwoNewborn.getTgjcqk_sffbczycms());
			et_sz.setText(followUpOneTwoNewborn.getTgjcqk_sfszhddycms());
			et_wszq.setText(followUpOneTwoNewborn.getTgjcqk_sfwszqycms());
			ViewDataUtil.setSpinnerData(sn_cyzcs, followUpOneTwoNewborn.getTgjcqk_cyqcsjl());
			
			if(followUpOneTwoNewborn.getTgjcqk_sfqxbh()){
				rb_qc_bh.setChecked(true);
				rb_qc_wbh.setChecked(false);
			}else {
				rb_qc_bh.setChecked(false);
				rb_qc_wbh.setChecked(true);
			}
			
			if(followUpOneTwoNewborn.getTgjcqk_sfpfyc()){
				rb_pf_yc.setChecked(true);
				rb_pf_wjyc.setChecked(false);
			}else {
				rb_pf_yc.setChecked(false);
				rb_pf_wjyc.setChecked(true);
			}
			
			if(followUpOneTwoNewborn.getTgjcqk_sfywgyc()){
				rb_ywg_yc.setChecked(true);
				rb_ywg_wjyc.setChecked(false);
			}else {
				rb_ywg_yc.setChecked(false);
				rb_ywg_wjyc.setChecked(true);
			}
			
			
			if(followUpOneTwoNewborn.getTgjcqk_sfewgyc()){
				rb_ewg_yc.setChecked(true);
				rb_ewg_wjyc.setChecked(false);
			}else {
				rb_ewg_yc.setChecked(false);
				rb_ewg_wjyc.setChecked(true);
			}
			
			if(followUpOneTwoNewborn.getTgjcqk_sftlyc()){
				rb_tl_tg.setChecked(true);
				rb_tl_wtg.setChecked(false);
			}else {
				rb_tl_tg.setChecked(false);
				rb_tl_wtg.setChecked(true);
			}
			
			if(followUpOneTwoNewborn.getTgjcqk_sfxftzyc()){
				rb_xf_yc.setChecked(true);
				rb_xf_wjyc.setChecked(false);
			}else {
				rb_xf_yc.setChecked(false);
				rb_xf_wjyc.setChecked(true);
			}
			if(followUpOneTwoNewborn.getTgjcqk_sffbczyc()){
				rb_fb_yc.setChecked(true);
				rb_fb_wjyc.setChecked(false);
			}else {
				rb_fb_yc.setChecked(false);
				rb_fb_wjyc.setChecked(true);
			}
			if(followUpOneTwoNewborn.getTgjcqk_sfszhddyc()){
				rb_sz_yc.setChecked(true);
				rb_sz_wjyc.setChecked(false);
			}else {
				rb_sz_yc.setChecked(false);
				rb_sz_wjyc.setChecked(true);
			}
			if(followUpOneTwoNewborn.getTgjcqk_sfwszqyc()){
				rb_wszq_yc.setChecked(true);
				rb_wszq_wjyc.setChecked(false);
			}else {
				rb_wszq_yc.setChecked(false);
				rb_wszq_wjyc.setChecked(true);
			}
		}
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
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub

	}

	private String getCheckBoxText(View layout,EditText editText) {
		LinearLayout linearLayout = (LinearLayout) layout;
		List<CheckBoxItem> mArrayList = new ArrayList<CheckBoxItem>();
		for (int i = 0; i < linearLayout.getChildCount(); i++) {
			View item = linearLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					CheckBoxItem entity = new CheckBoxItem();
					entity.setItem_num(String.valueOf(i));
					if ("其他".equals(checkBox.getText())
							&& editText != null) {
						entity.setItem_name(editText.getText()
								.toString());
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
				if (Num == myLayout.getChildCount() - 2) {
					textview.setText(name);
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
