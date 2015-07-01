package com.shbestwin.followupmanager.view.widget;

import android.R.bool;
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
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;

public class Antenatal1Body12 extends LinearLayout implements
		IBaseAntenatal1Body {
	private EditText et_xcg_xhdbz, et_xcg_bxbjsz, et_xcg_xxbjsz, et_xcg_qt;
	private EditText et_ncg_ndb, et_ncg_nt, et_ncg_qt, et_ggn_xqgbzam,
			et_ggn_xqgczam, et_ggn_bdb, et_ggn_zdhs, et_ggn_jhdhs;
	private EditText et_sgn_xqjg, et_sgn_xnsd, et_sgn_xjnd, et_sgn_xnnd,
			et_ydfmw_qt;
	private Spinner sn_ncg_ntt, sn_ncg_nqx, sn_mdxq, sn_hivktjc;
	private RadioGroup rg_ydfmw;
	private String ydfmw_name = "未见异常";
	private boolean isHas=false;

	public Antenatal1Body12(Context context) {
		this(context, null);
	}

	public Antenatal1Body12(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body12(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_antenatal1_body12, this, true);
		et_xcg_xhdbz = (EditText) rootView.findViewById(R.id.et_xcg_xhdbz);
		et_xcg_bxbjsz = (EditText) rootView.findViewById(R.id.et_xcg_bxbjsz);
		et_xcg_xxbjsz = (EditText) rootView.findViewById(R.id.et_xcg_xxbjsz);
		et_xcg_qt = (EditText) rootView.findViewById(R.id.et_xcg_qt);
		et_ncg_ndb = (EditText) rootView.findViewById(R.id.et_ncg_ndb);
		et_ncg_nt = (EditText) rootView.findViewById(R.id.et_ncg_nt);
		et_ncg_qt = (EditText) rootView.findViewById(R.id.et_ncg_qt);
		et_ggn_xqgbzam = (EditText) rootView.findViewById(R.id.et_ggn_xqgbzam);
		et_ggn_xqgczam = (EditText) rootView.findViewById(R.id.et_ggn_xqgczam);
		et_ggn_bdb = (EditText) rootView.findViewById(R.id.et_ggn_bdb);
		et_ggn_zdhs = (EditText) rootView.findViewById(R.id.et_ggn_zdhs);
		et_ggn_jhdhs = (EditText) rootView.findViewById(R.id.et_ggn_jhdhs);
		et_sgn_xqjg = (EditText) rootView.findViewById(R.id.et_sgn_xqjg);
		et_sgn_xnsd = (EditText) rootView.findViewById(R.id.et_sgn_xnsd);
		et_sgn_xjnd = (EditText) rootView.findViewById(R.id.et_sgn_xjnd);
		et_sgn_xnnd = (EditText) rootView.findViewById(R.id.et_sgn_xnnd);
		et_ydfmw_qt = (EditText) rootView.findViewById(R.id.et_ydfmw_qt);
		sn_ncg_ntt = (Spinner) rootView.findViewById(R.id.sn_ncg_ntt);
		sn_ncg_nqx = (Spinner) rootView.findViewById(R.id.sn_ncg_nqx);
		sn_mdxq = (Spinner) rootView.findViewById(R.id.sn_mdxq);
		sn_hivktjc = (Spinner) rootView.findViewById(R.id.sn_hivktjc);
		rg_ydfmw = (RadioGroup) rootView.findViewById(R.id.rg_ydfmw);

		rg_ydfmw.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_wjyc) {
					ydfmw_name = "未见异常";
					isHas=false;
				} else if (checkedId == R.id.rb_dc) {
					ydfmw_name = "滴虫";
					isHas=true;
				} else if (checkedId == R.id.rb_mj) {
					ydfmw_name = "霉菌";
					isHas=true;
				} else if (checkedId == R.id.rb_qt) {
					isHas=true;
					ydfmw_name = et_ydfmw_qt.getText().toString();
				}
			}
		});

	}

	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		followUpFirstPregnancy.setFzjc_xhdbz(et_xcg_xhdbz.getText().toString());
		followUpFirstPregnancy.setFzjc_bxbjsz(et_xcg_bxbjsz.getText().toString());
		followUpFirstPregnancy.setFzjc_xxbjsz(et_xcg_xxbjsz.getText().toString());
		followUpFirstPregnancy.setFzjc_xcgqt(et_xcg_qt.getText().toString());
		
		followUpFirstPregnancy.setFzjc_ndb(et_ncg_ndb.getText().toString());
		followUpFirstPregnancy.setFzjc_nt(et_ncg_nt.getText().toString());
		followUpFirstPregnancy.setFzjc_ntt(ViewDataUtil.getSpinnerData(sn_ncg_ntt));
		followUpFirstPregnancy.setFzjc_nqx(ViewDataUtil.getSpinnerData(sn_ncg_nqx));
		followUpFirstPregnancy.setFzjc_ncgqt(et_ncg_qt.getText().toString());
		
		followUpFirstPregnancy.setFzjc_xqgbzam(et_ggn_xqgbzam.getText().toString());
		followUpFirstPregnancy.setFzjc_bdb(et_ggn_bdb.getText().toString());
		followUpFirstPregnancy.setFzjc_zdhs(et_ggn_zdhs.getText().toString());
		followUpFirstPregnancy.setFzjc_jhdhs(et_ggn_jhdhs.getText().toString());
		
		followUpFirstPregnancy.setFzjc_sfydfmwyc(isHas);
		followUpFirstPregnancy.setFzjc_sfydfmwycms(ydfmw_name);
		
		followUpFirstPregnancy.setFzjc_mdxqxsy(ViewDataUtil.getSpinnerData(sn_mdxq));
		followUpFirstPregnancy.setFzjc_HIVktjc(ViewDataUtil.getSpinnerData(sn_hivktjc));
	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {
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
