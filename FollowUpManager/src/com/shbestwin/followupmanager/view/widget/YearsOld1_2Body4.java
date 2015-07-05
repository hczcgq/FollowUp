package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;

public class YearsOld1_2Body4 extends LinearLayout implements
		IBaseYearsOld1_2Body, OnCheckedChangeListener {
	private RadioGroup rg_fwtt, rg_dyshwx, rg_sbsnzjkzt, rg_szk, rg_xcs,
			rg_sszwj, rg_hfs, rg_zjzsdzjwx, rg_rs, rg_yszcz;

	private boolean is_fwtt = true, is_dyshwx = true, is_sbsnzjkzt = true,
			is_szk = true, is_xcs = true, is_sszwj = true, is_hfs = true,
			is_zjzsdzjwx = true, is_rs = true, is_yszcz = true;

	private RadioButton rb_fwtt_f, rb_fwtt_s, rb_dyshwx_f, rb_dyshwx_s,
			rb_sbsnzjkzt_f, rb_sbsnzjkzt_s, rb_szk_f, rb_szk_s, rb_xcs_f,
			rb_xcs_s, rb_sszwj_f, rb_sszwj_s, rb_hfs_f, rb_hfs_s,
			rb_zjzsdzjwx_f, rb_zjzsdzjwx_s, rb_rs_f, rb_rs_s, rb_yszcz_f,
			rb_yszcz_s;

	public YearsOld1_2Body4(Context context) {
		this(context, null);
	}

	public YearsOld1_2Body4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld1_2Body4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_years_old_1_2_body4, this, true);
		rb_fwtt_f = (RadioButton) rootView.findViewById(R.id.rb_fwtt_f);
		rb_fwtt_s = (RadioButton) rootView.findViewById(R.id.rb_fwtt_s);
		rb_dyshwx_f = (RadioButton) rootView.findViewById(R.id.rb_dyshwx_f);
		rb_dyshwx_s = (RadioButton) rootView.findViewById(R.id.rb_dyshwx_s);
		rb_sbsnzjkzt_f = (RadioButton) rootView
				.findViewById(R.id.rb_sbsnzjkzt_f);
		rb_sbsnzjkzt_s = (RadioButton) rootView
				.findViewById(R.id.rb_sbsnzjkzt_s);
		rb_szk_f = (RadioButton) rootView.findViewById(R.id.rb_szk_f);
		rb_szk_s = (RadioButton) rootView.findViewById(R.id.rb_szk_s);
		rb_xcs_f = (RadioButton) rootView.findViewById(R.id.rb_xcs_f);
		rb_xcs_s = (RadioButton) rootView.findViewById(R.id.rb_xcs_s);
		rb_sszwj_f = (RadioButton) rootView.findViewById(R.id.rb_sszwj_f);
		rb_sszwj_s = (RadioButton) rootView.findViewById(R.id.rb_sszwj_s);
		rb_hfs_f = (RadioButton) rootView.findViewById(R.id.rb_hfs_f);
		rb_hfs_s = (RadioButton) rootView.findViewById(R.id.rb_hfs_s);
		rb_zjzsdzjwx_f = (RadioButton) rootView
				.findViewById(R.id.rb_zjzsdzjwx_f);
		rb_zjzsdzjwx_s = (RadioButton) rootView
				.findViewById(R.id.rb_zjzsdzjwx_s);
		rb_rs_f = (RadioButton) rootView.findViewById(R.id.rb_rs_f);
		rb_rs_s = (RadioButton) rootView.findViewById(R.id.rb_rs_s);
		rb_yszcz_f = (RadioButton) rootView.findViewById(R.id.rb_yszcz_f);
		rb_yszcz_s = (RadioButton) rootView.findViewById(R.id.rb_yszcz_s);

		rg_fwtt = (RadioGroup) rootView.findViewById(R.id.rg_fwtt);
		rg_dyshwx = (RadioGroup) rootView.findViewById(R.id.rg_dyshwx);
		rg_sbsnzjkzt = (RadioGroup) rootView.findViewById(R.id.rg_sbsnzjkzt);
		rg_szk = (RadioGroup) rootView.findViewById(R.id.rg_szk);
		rg_xcs = (RadioGroup) rootView.findViewById(R.id.rg_xcs);
		rg_sszwj = (RadioGroup) rootView.findViewById(R.id.rg_sszwj);
		rg_hfs = (RadioGroup) rootView.findViewById(R.id.rg_hfs);
		rg_zjzsdzjwx = (RadioGroup) rootView.findViewById(R.id.rg_zjzsdzjwx);
		rg_rs = (RadioGroup) rootView.findViewById(R.id.rg_rs);
		rg_yszcz = (RadioGroup) rootView.findViewById(R.id.rg_yszcz);

		rg_fwtt.setOnCheckedChangeListener(this);
		rg_dyshwx.setOnCheckedChangeListener(this);
		rg_sbsnzjkzt.setOnCheckedChangeListener(this);
		rg_szk.setOnCheckedChangeListener(this);
		rg_xcs.setOnCheckedChangeListener(this);
		rg_sszwj.setOnCheckedChangeListener(this);
		rg_hfs.setOnCheckedChangeListener(this);
		rg_zjzsdzjwx.setOnCheckedChangeListener(this);
		rg_rs.setOnCheckedChangeListener(this);
		rg_yszcz.setOnCheckedChangeListener(this);
	}

	@Override
	public void getData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		followUpOneTwoNewborn.setFysc_hyszrdxhtp(is_fwtt);
		followUpOneTwoNewborn.setFysc_hyszrdxhtp(is_dyshwx);
		followUpOneTwoNewborn.setFysc_zjdzw(is_sbsnzjkzt);
		followUpOneTwoNewborn.setFysc_hjwjfdbzhwl(is_szk);
		followUpOneTwoNewborn.setFysc_nzjdxbzql(is_xcs);
		followUpOneTwoNewborn.setFysc_hssgysddz(is_sszwj);
		followUpOneTwoNewborn.setFysc_hzrzjwghstbw(is_hfs);
		followUpOneTwoNewborn.setFysc_nwcjdzlrbqfzs(is_zjzsdzjwx);
		followUpOneTwoNewborn.setFysc_hzjp(is_rs);
		followUpOneTwoNewborn.setFysc_hdg37kjmhwj(is_yszcz);
	}

	@Override
	public void setData(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		if (followUpOneTwoNewborn != null) {
			if (followUpOneTwoNewborn.getFysc_hyysjbbmm()) {
				rb_fwtt_s.setChecked(true);
				rb_fwtt_f.setChecked(false);
			} else {
				rb_fwtt_s.setChecked(false);
				rb_fwtt_f.setChecked(true);
			}
			
			if (followUpOneTwoNewborn.getFysc_hyszrdxhtp()) {
				rb_dyshwx_s.setChecked(true);
				rb_dyshwx_f.setChecked(false);
			} else {
				rb_dyshwx_s.setChecked(false);
				rb_dyshwx_f.setChecked(true);
			}
			
			if (followUpOneTwoNewborn.getFysc_zjdzw()) {
				rb_sbsnzjkzt_s.setChecked(true);
				rb_sbsnzjkzt_f.setChecked(false);
			} else {
				rb_sbsnzjkzt_s.setChecked(false);
				rb_sbsnzjkzt_f.setChecked(true);
			}
			
			if (followUpOneTwoNewborn.getFysc_hjwjfdbzhwl()) {
				rb_szk_s.setChecked(true);
				rb_szk_f.setChecked(false);
			} else {
				rb_szk_s.setChecked(false);
				rb_szk_f.setChecked(true);
			}
			
			if (followUpOneTwoNewborn.getFysc_nzjdxbzql()) {
				rb_xcs_s.setChecked(true);
				rb_xcs_f.setChecked(false);
			} else {
				rb_xcs_s.setChecked(false);
				rb_xcs_f.setChecked(true);
			}
			
			if (followUpOneTwoNewborn.getFysc_hssgysddz()) {
				rb_sszwj_s.setChecked(true);
				rb_sszwj_f.setChecked(false);
			} else {
				rb_sszwj_s.setChecked(false);
				rb_sszwj_f.setChecked(true);
			}
			
			if (followUpOneTwoNewborn.getFysc_hzrzjwghstbw()) {
				rb_hfs_s.setChecked(true);
				rb_hfs_f.setChecked(false);
			} else {
				rb_hfs_s.setChecked(false);
				rb_hfs_f.setChecked(true);
			}
			
			if (followUpOneTwoNewborn.getFysc_nwcjdzlrbqfzs()) {
				rb_zjzsdzjwx_s.setChecked(true);
				rb_zjzsdzjwx_f.setChecked(false);
			} else {
				rb_zjzsdzjwx_s.setChecked(false);
				rb_zjzsdzjwx_f.setChecked(true);
			}
			
			if (followUpOneTwoNewborn.getFysc_hzjp()) {
				rb_rs_s.setChecked(true);
				rb_rs_f.setChecked(false);
			} else {
				rb_rs_s.setChecked(false);
				rb_rs_f.setChecked(true);
			}
			
			if (followUpOneTwoNewborn.getFysc_hdg37kjmhwj()) {
				rb_yszcz_s.setChecked(true);
				rb_yszcz_f.setChecked(false);
			} else {
				rb_yszcz_s.setChecked(false);
				rb_yszcz_f.setChecked(true);
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
		if (checkedId == R.id.rb_fwtt_f) {
			is_fwtt = false;
		} else if (checkedId == R.id.rb_fwtt_s) {
			is_fwtt = true;
		}

		if (checkedId == R.id.rb_dyshwx_f) {
			is_dyshwx = false;
		} else if (checkedId == R.id.rb_dyshwx_s) {
			is_dyshwx = true;
		}

		if (checkedId == R.id.rb_sbsnzjkzt_f) {
			is_sbsnzjkzt = false;
		} else if (checkedId == R.id.rb_sbsnzjkzt_s) {
			is_sbsnzjkzt = true;
		}
		if (checkedId == R.id.rb_szk_f) {
			is_szk = false;
		} else if (checkedId == R.id.rb_szk_s) {
			is_szk = true;
		}
		if (checkedId == R.id.rb_xcs_f) {
			is_xcs = false;
		} else if (checkedId == R.id.rb_xcs_s) {
			is_xcs = true;
		}
		if (checkedId == R.id.rb_sszwj_f) {
			is_sszwj = false;
		} else if (checkedId == R.id.rb_sszwj_s) {
			is_sszwj = true;
		}

		if (checkedId == R.id.rb_hfs_f) {
			is_hfs = false;
		} else if (checkedId == R.id.rb_hfs_s) {
			is_hfs = true;
		}

		if (checkedId == R.id.rb_zjzsdzjwx_f) {
			is_zjzsdzjwx = false;
		} else if (checkedId == R.id.rb_zjzsdzjwx_s) {
			is_zjzsdzjwx = true;
		}
		if (checkedId == R.id.rb_rs_f) {
			is_rs = false;
		} else if (checkedId == R.id.rb_rs_s) {
			is_rs = true;
		}
		if (checkedId == R.id.rb_yszcz_f) {
			is_yszcz = false;
		} else if (checkedId == R.id.rb_yszcz_s) {
			is_yszcz = true;
		}
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub

	}
}
