package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body4 extends LinearLayout implements IBaseYearsOld0Body,
		OnCheckedChangeListener {
	private RadioGroup rg_fwtt, rg_dyshwx, rg_sbsnzjkzt, rg_szk, rg_xcs,
			rg_sszwj, rg_hfs, rg_zjzsdzjwx, rg_rs, rg_yszcz;

	private boolean is_fwtt = true, is_dyshwx = true, is_sbsnzjkzt = true,
			is_szk = true, is_xcs = true, is_sszwj = true, is_hfs = true,
			is_zjzsdzjwx = true, is_rs = true, is_yszcz = true;

	public YearsOld0Body4(Context context) {
		this(context, null);
	}

	public YearsOld0Body4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_years_old_0_body4, this, true);
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
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		followUpOneNewborn.setFysc_sffwtt(is_fwtt);
		followUpOneNewborn.setFysc_sfdyshwx(is_dyshwx);
		followUpOneNewborn.setFysc_sfyszcz(is_yszcz);
		followUpOneNewborn.setFysc_sfrs(is_rs);
		followUpOneNewborn.setFysc_sfzjzsdzjwx(is_zjzsdzjwx);
		followUpOneNewborn.setFysc_sfhfs(is_hfs);
		followUpOneNewborn.setFysc_sfsszwj(is_sszwj);
		followUpOneNewborn.setFysc_sfxcs(is_xcs);
		followUpOneNewborn.setFysc_sfszk(is_szk);
		followUpOneNewborn.setFysc_sfjbsnzjkzt(is_sbsnzjkzt);
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
}
