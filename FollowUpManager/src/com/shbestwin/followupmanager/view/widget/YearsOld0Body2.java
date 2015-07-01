package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;

public class YearsOld0Body2 extends LinearLayout  implements IBaseYearsOld0Body {
	private Spinner sn_wyfs,sn_mfhz,sn_cngn,sn_dhhgn,sn_sy;
	private EditText et_wmrcs,et_nf,et_tjfs,et_cs,et_fywssd;
	public YearsOld0Body2(Context context) {
		this(context, null);
	}

	public YearsOld0Body2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld0Body2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_0_body2, this, true);
		sn_wyfs=(Spinner) rootView.findViewById(R.id.sn_wyfs);
		sn_mfhz=(Spinner) rootView.findViewById(R.id.sn_mfhz);
		sn_cngn=(Spinner) rootView.findViewById(R.id.sn_cngn);
		sn_dhhgn=(Spinner) rootView.findViewById(R.id.sn_dhhgn);
		sn_sy=(Spinner) rootView.findViewById(R.id.sn_sy);
		et_wmrcs=(EditText) rootView.findViewById(R.id.et_wmrcs);
		et_nf=(EditText) rootView.findViewById(R.id.et_nf);
		et_tjfs=(EditText) rootView.findViewById(R.id.et_tjfs);
		et_cs=(EditText) rootView.findViewById(R.id.et_cs);
		et_fywssd=(EditText) rootView.findViewById(R.id.et_fywssd);
	}
	@Override
	public void getData(FollowUpOneNewborn followUpOneNewborn) {
		followUpOneNewborn.setWyqk_wyfs(ViewDataUtil.getSpinnerData(sn_wyfs));
		followUpOneNewborn.setWyqk_wmrcs(et_wmrcs.getText().toString());
		followUpOneNewborn.setWyqk_nf(et_nf.getText().toString());
		followUpOneNewborn.setWyqk_mfhz(ViewDataUtil.getSpinnerData(sn_mfhz));
		followUpOneNewborn.setWyqk_cngn(ViewDataUtil.getSpinnerData(sn_cngn));
		followUpOneNewborn.setWyqk_dhhgn(ViewDataUtil.getSpinnerData(sn_dhhgn));
		followUpOneNewborn.setWyqk_tjfs(et_tjfs.getText().toString());
		followUpOneNewborn.setWyqk_cs(et_cs.getText().toString());
		followUpOneNewborn.setWyqk_sy(ViewDataUtil.getSpinnerData(sn_sy));
		followUpOneNewborn.setWyqk_fywssd(et_fywssd.getText().toString());
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
}
