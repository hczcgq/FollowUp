package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;

public class YearsOld3_6Body2 extends LinearLayout implements
		IBaseYearsOld3_6Body, OnCheckedChangeListener {
	private EditText et_tz, et_sc, et_bmi, et_sl_zy, et_sl_yy, et_cyzzs_1,
			et_cyzzs_2, et_tl, et_yb, et_eb, et_xf, et_fb, et_wszq, et_ggyctz;
	private Spinner sn_tgfypj, sn_cyzzs_jl;
	private RadioGroup rg_tl, rg_yb, rg_eb, rg_xf, rg_fb, rg_wszq, rg_ggyctz;

	private boolean isTl = true, isYb = false, isEb = false, isXf = false,
			isFb = true, isWszq = false, isGgyctz = false;;

	public YearsOld3_6Body2(Context context) {
		this(context, null);
	}

	public YearsOld3_6Body2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld3_6Body2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_years_old_3_6_body2, this, true);

		et_tz = (EditText) rootView.findViewById(R.id.et_tz);
		et_sc = (EditText) rootView.findViewById(R.id.et_sc);
		et_bmi = (EditText) rootView.findViewById(R.id.et_bmi);
		et_sl_zy = (EditText) rootView.findViewById(R.id.et_sl_zy);
		et_sl_yy = (EditText) rootView.findViewById(R.id.et_sl_yy);
		et_cyzzs_1 = (EditText) rootView.findViewById(R.id.et_cyzzs_1);
		et_cyzzs_2 = (EditText) rootView.findViewById(R.id.et_cyzzs_2);
		et_tl = (EditText) rootView.findViewById(R.id.et_tl);
		et_yb = (EditText) rootView.findViewById(R.id.et_yb);
		et_eb = (EditText) rootView.findViewById(R.id.et_eb);
		et_xf = (EditText) rootView.findViewById(R.id.et_xf);
		et_fb = (EditText) rootView.findViewById(R.id.et_fb);
		et_wszq = (EditText) rootView.findViewById(R.id.et_wszq);
		et_ggyctz = (EditText) rootView.findViewById(R.id.et_ggyctz);

		sn_tgfypj = (Spinner) rootView.findViewById(R.id.sn_tgfypj);
		sn_cyzzs_jl = (Spinner) rootView.findViewById(R.id.sn_cyzzs_jl);

		rg_tl = (RadioGroup) rootView.findViewById(R.id.rg_tl);
		rg_yb = (RadioGroup) rootView.findViewById(R.id.rg_yb);
		rg_eb = (RadioGroup) rootView.findViewById(R.id.rg_eb);
		rg_xf = (RadioGroup) rootView.findViewById(R.id.rg_xf);
		rg_fb = (RadioGroup) rootView.findViewById(R.id.rg_fb);
		rg_wszq = (RadioGroup) rootView.findViewById(R.id.rg_wszq);
		rg_ggyctz = (RadioGroup) rootView.findViewById(R.id.rg_ggyctz);
		rg_tl.setOnCheckedChangeListener(this);
		rg_yb.setOnCheckedChangeListener(this);
		rg_eb.setOnCheckedChangeListener(this);
		rg_xf.setOnCheckedChangeListener(this);
		rg_fb.setOnCheckedChangeListener(this);
		rg_wszq.setOnCheckedChangeListener(this);
		rg_ggyctz.setOnCheckedChangeListener(this);
	}

	@Override
	public void getData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		followUpThreeSixNewborn.setTgjcqk_tz(et_tz.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_sc(et_sc.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_bmi(et_bmi.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_tgfypj(ViewDataUtil.getSpinnerData(sn_tgfypj));
		followUpThreeSixNewborn.setTgjcqk_sl(et_sl_zy.getText().toString()+"/"+et_sl_yy.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_cyqcs(et_cyzzs_1.getText().toString()+"/"+et_cyzzs_2.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_cyqcsjl(ViewDataUtil.getSpinnerData(sn_cyzzs_jl));
		followUpThreeSixNewborn.setTgjcqk_sftlyc(isTl);
		followUpThreeSixNewborn.setTgjcqk_sftlycms(et_tl.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_sfywgyc(isYb);
		followUpThreeSixNewborn.setTgjcqk_sfywgycms(et_yb.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_sfebyc(isEb);
		followUpThreeSixNewborn.setTgjcqk_sfebycms(et_eb.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_sfxftzyc(isXf);
		followUpThreeSixNewborn.setTgjcqk_sfxftzycms(et_xf.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_sffbczyc(isFb);
		followUpThreeSixNewborn.setTgjcqk_sffbczycms(et_fb.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_sfwszqyc(isWszq);
		followUpThreeSixNewborn.setTgjcqk_sfwszqycms(et_wszq.getText().toString());
		followUpThreeSixNewborn.setTgjcqk_sfggyctzyc(isGgyctz);
		followUpThreeSixNewborn.setTgjcqk_sfggyctzycms(et_ggyctz.getText().toString());
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
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		if (checkedId == R.id.rb_tl_tg) {
			isTl = true;
		} else if (checkedId == R.id.rb_tl_wtg) {
			isTl = false;
		}

		if (checkedId == R.id.rb_yb_wjyc) {
			isYb = false;
		} else if (checkedId == R.id.rb_yb_yc) {
			isYb = true;
		}

		if (checkedId == R.id.rb_eb_wjyc) {
			isEb = false;
		} else if (checkedId == R.id.rb_eb_yc) {
			isEb = true;
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

		if (checkedId == R.id.rb_wszq_wjyc) {
			isWszq = false;
		} else if (checkedId == R.id.rb_wszq_yc) {
			isWszq = true;
		}

		if (checkedId == R.id.rb_ggyctz_wjyc) {
			isGgyctz = false;
		} else if (checkedId == R.id.rb_ggyctz_yc) {
			isGgyctz = true;
		}

	}

}
