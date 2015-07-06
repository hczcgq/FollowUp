package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody6 extends LinearLayout implements
		IBaseGeneralExaminationBody {

	private EditText et_xhdb, et_bxb, et_xxb, et_xcg_qt, et_ndb, et_nt, et_nbz,
			et_nysjd, et_ncg_qt, et_kfxt, et_xdt, et_nwlbdb, et_thxhdb,
			et_xqgbzam, et_xqgczam, et_bdb, et_zdhs, et_jhdhs, et_xqjg,
			et_xnsd, et_xjnd, et_xnnd, et_zdgc, et_dmdzdb, et_gysz, et_gmdzdb,
			et_apky, et_xbxsx, et_bc, et_gjtp, et_qt;
	private Spinner sn_ndb, sn_nt, sn_ntt, sn_nqx, sn_yxgybmky, sn_yxgyhxkt,
			sn_yxgyeky, sn_yxgyekt;

	private RadioGroup rg_xdt, rg_dbqx, rg_xbxsx, rg_bc, rg_gjtp;
	private RadioButton rb_xdt_zc, rb_xdt_yc, rb_dbqx_yinx, rb_dbqx_yangx,
			rb_xbxsx_zc, rb_xbxsx_yc, rb_bc_zc, rb_bc_yc, rb_gjtp_zc,
			rb_gjtp_yc;

	private boolean isXdt = true, isDbqx = false, isXbxsx = true, isBc = true,
			isGjtp = true;

	public GeneralExaminationBody6(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody6(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_general_examination_body6, this, true);

		et_xhdb = (EditText) rootView.findViewById(R.id.et_xhdb);
		et_bxb = (EditText) rootView.findViewById(R.id.et_bxb);
		et_xxb = (EditText) rootView.findViewById(R.id.et_xxb);
		et_xcg_qt = (EditText) rootView.findViewById(R.id.et_xcg_qt);
		et_ndb = (EditText) rootView.findViewById(R.id.et_ndb);
		et_nt = (EditText) rootView.findViewById(R.id.et_nt);
		et_nbz = (EditText) rootView.findViewById(R.id.et_nbz);
		et_nysjd = (EditText) rootView.findViewById(R.id.et_nysjd);
		et_ncg_qt = (EditText) rootView.findViewById(R.id.et_ncg_qt);
		et_kfxt = (EditText) rootView.findViewById(R.id.et_kfxt);
		et_xdt = (EditText) rootView.findViewById(R.id.et_xdt);
		et_nwlbdb = (EditText) rootView.findViewById(R.id.et_nwlbdb);
		et_thxhdb = (EditText) rootView.findViewById(R.id.et_thxhdb);
		et_xqgbzam = (EditText) rootView.findViewById(R.id.et_xqgbzam);
		et_xqgczam = (EditText) rootView.findViewById(R.id.et_xqgczam);
		et_bdb = (EditText) rootView.findViewById(R.id.et_bdb);
		et_zdhs = (EditText) rootView.findViewById(R.id.et_zdhs);
		et_jhdhs = (EditText) rootView.findViewById(R.id.et_jhdhs);
		et_xqjg = (EditText) rootView.findViewById(R.id.et_xqjg);
		et_xnsd = (EditText) rootView.findViewById(R.id.et_xnsd);
		et_xjnd = (EditText) rootView.findViewById(R.id.et_xjnd);
		et_xnnd = (EditText) rootView.findViewById(R.id.et_xnnd);
		et_zdgc = (EditText) rootView.findViewById(R.id.et_zdgc);
		et_dmdzdb = (EditText) rootView.findViewById(R.id.et_dmdzdb);
		et_gysz = (EditText) rootView.findViewById(R.id.et_gysz);
		et_gmdzdb = (EditText) rootView.findViewById(R.id.et_gmdzdb);
		et_apky = (EditText) rootView.findViewById(R.id.et_apky);
		et_xbxsx = (EditText) rootView.findViewById(R.id.et_xbxsx);
		et_bc = (EditText) rootView.findViewById(R.id.et_bc);
		et_gjtp = (EditText) rootView.findViewById(R.id.et_gjtp);
		et_qt = (EditText) rootView.findViewById(R.id.et_qt);

		sn_ndb = (Spinner) rootView.findViewById(R.id.sn_ndb);
		sn_nt = (Spinner) rootView.findViewById(R.id.sn_nt);
		sn_ntt = (Spinner) rootView.findViewById(R.id.sn_ntt);
		sn_nqx = (Spinner) rootView.findViewById(R.id.sn_nqx);
		sn_yxgybmky = (Spinner) rootView.findViewById(R.id.sn_yxgybmky);
		sn_yxgyhxkt = (Spinner) rootView.findViewById(R.id.sn_yxgyhxkt);
		sn_yxgyeky = (Spinner) rootView.findViewById(R.id.sn_yxgyeky);
		sn_yxgyekt = (Spinner) rootView.findViewById(R.id.sn_yxgyekt);

		rg_xdt = (RadioGroup) rootView.findViewById(R.id.rg_xdt);
		rg_dbqx = (RadioGroup) rootView.findViewById(R.id.rg_dbqx);
		rg_xbxsx = (RadioGroup) rootView.findViewById(R.id.rg_xbxsx);
		rg_bc = (RadioGroup) rootView.findViewById(R.id.rg_bc);
		rg_gjtp = (RadioGroup) rootView.findViewById(R.id.rg_gjtp);

		rb_xdt_zc = (RadioButton) rootView.findViewById(R.id.rb_xdt_zc);
		rb_xdt_yc = (RadioButton) rootView.findViewById(R.id.rb_xdt_yc);
		rb_dbqx_yinx = (RadioButton) rootView.findViewById(R.id.rb_dbqx_yinx);
		rb_dbqx_yangx = (RadioButton) rootView.findViewById(R.id.rb_dbqx_yangx);
		rb_xbxsx_zc = (RadioButton) rootView.findViewById(R.id.rb_xbxsx_zc);
		rb_xbxsx_yc = (RadioButton) rootView.findViewById(R.id.rb_xbxsx_yc);
		rb_bc_zc = (RadioButton) rootView.findViewById(R.id.rb_bc_zc);
		rb_bc_yc = (RadioButton) rootView.findViewById(R.id.rb_bc_yc);
		rb_gjtp_zc = (RadioButton) rootView.findViewById(R.id.rb_gjtp_zc);
		rb_gjtp_yc = (RadioButton) rootView.findViewById(R.id.rb_gjtp_yc);

		rg_xdt.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_xdt_zc) {
					isXdt = true;
				} else {
					isXdt = false;
				}
			}
		});

		rg_dbqx.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_dbqx_yinx) {
					isDbqx = false;
				} else {
					isDbqx = true;
				}
			}
		});

		rg_xbxsx.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_xbxsx_zc) {
					isXbxsx = true;
				} else {
					isXbxsx = false;
				}
			}
		});

		rg_bc.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_bc_zc) {
					isBc = true;
				} else {
					isBc = false;
				}
			}
		});

		rg_gjtp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_gjtp_zc) {
					isGjtp = true;
				} else {
					isGjtp = false;
				}
			}
		});
	}

	@Override
	public void getData(GeneralExamination generalExamination) {
		generalExamination.setFzjc_xhdb(et_xhdb.getText().toString());
		generalExamination.setFzjc_bxb(et_bxb.getText().toString());
		generalExamination.setFzjc_xxb(et_xxb.getText().toString());
		generalExamination.setFzjc_xcgqt(et_xcg_qt.getText().toString());
		
		generalExamination.setFzjc_ndb(et_ndb.getText().toString());
		generalExamination.setFzjc_nt(et_nt.getText().toString());
		generalExamination.setFzjc_nbz(et_nbz.getText().toString());
		generalExamination.setFzjc_nysjd(et_nysjd.getText().toString());
		generalExamination.setFzjc_ncgqt(et_ncg_qt.getText().toString());
		generalExamination.setFzjc_ntxz(ViewDataUtil.getSpinnerData(sn_nt));
		generalExamination.setFzjc_ndbxz(ViewDataUtil.getSpinnerData(sn_ndb));
		generalExamination.setFzjc_nttxz(ViewDataUtil.getSpinnerData(sn_ntt));
		generalExamination.setFzjc_nqxxz(ViewDataUtil.getSpinnerData(sn_nqx));
		
		generalExamination.setFzjc_kfxt(et_kfxt.getText().toString());
		generalExamination.setFzjc_sfxdtzc(isXdt);
		generalExamination.setFzjc_xdtycxx(et_xdt.getText().toString());
		generalExamination.setFzjc_sfdbqx(isDbqx);
		generalExamination.setFzjc_nwlbdb(et_nwlbdb.getText().toString());
		generalExamination.setFzjc_thxhdb(et_thxhdb.getText().toString());
		
		generalExamination.setFzjc_bmky(ViewDataUtil.getSpinnerData(sn_yxgybmky));
		generalExamination.setFzjc_hxkt(ViewDataUtil.getSpinnerData(sn_yxgyhxkt));
		generalExamination.setFzjc_eky(ViewDataUtil.getSpinnerData(sn_yxgyeky));
		generalExamination.setFzjc_ekt(ViewDataUtil.getSpinnerData(sn_yxgyekt));
		
		generalExamination.setFzjc_xqgbzam(et_xqgbzam.getText().toString());
		generalExamination.setFzjc_xqgczam(et_xqgczam.getText().toString());
		generalExamination.setFzjc_bdb(et_bdb.getText().toString());
		generalExamination.setFzjc_zdhs(et_zdhs.getText().toString());
		generalExamination.setFzjc_zdhs(et_zdgc.getText().toString());
		generalExamination.setFzjc_jhdhs(et_jhdhs.getText().toString());
		generalExamination.setFzjc_xqjg(et_xqjg.getText().toString());
		generalExamination.setFzjc_xnsd(et_xnsd.getText().toString());
		generalExamination.setFzjc_xjnd(et_xjnd.getText().toString());
		generalExamination.setFzjc_xnnd(et_xnnd.getText().toString());
		generalExamination.setFzjc_zdgc(et_zdgc.getText().toString());
		generalExamination.setFzjc_dmdzdb(et_dmdzdb.getText().toString());
		generalExamination.setFzjc_gysz(et_gysz.getText().toString());
		generalExamination.setFzjc_gmdzdb(et_gmdzdb.getText().toString());
		generalExamination.setFzjc_apky(et_apky.getText().toString());
		
		generalExamination.setFzjc_sfxbxxpzc(isXbxsx);
		generalExamination.setFzjc_xbxxpycms(et_xbxsx.getText().toString());
		generalExamination.setFzjc_sfbczc(isBc);
		generalExamination.setFzjc_bcycms(et_bc.getText().toString());
		generalExamination.setFzjc_sfgjpzc(isGjtp);
		generalExamination.setFzjc_gjpycms(et_gjtp.getText().toString());
		generalExamination.setFzjc_qt(et_qt.getText().toString());
		
		
		
		
	}

	@Override
	public void setData(GeneralExamination generalExamination) {
		if(generalExamination!=null){
			et_xhdb.setText(generalExamination.getFzjc_xhdb());
			et_bxb.setText(generalExamination.getFzjc_bxb());
			et_xxb.setText(generalExamination.getFzjc_xxb());
			et_xcg_qt.setText(generalExamination.getFzjc_xcgqt());
			
			et_ndb.setText(generalExamination.getFzjc_ndb());
			et_nt.setText(generalExamination.getFzjc_nt());
			et_nbz.setText(generalExamination.getFzjc_nbz());
			et_nysjd.setText(generalExamination.getFzjc_nysjd());
			et_ncg_qt.setText(generalExamination.getFzjc_ncgqt());
			ViewDataUtil.setSpinnerData(sn_nt, generalExamination.getFzjc_ntxz());
			ViewDataUtil.setSpinnerData(sn_ndb,generalExamination.getFzjc_ndbxz());
			ViewDataUtil.setSpinnerData(sn_ntt,generalExamination.getFzjc_nttxz());
			ViewDataUtil.setSpinnerData(sn_nqx,generalExamination.getFzjc_nqxxz());
			
			et_kfxt.setText(generalExamination.getFzjc_kfxt());
			if(generalExamination.getFzjc_sfxdtzc()){
				rb_xdt_zc.setChecked(true);
				rb_xdt_yc.setChecked(false);
			}else {
				rb_xdt_zc.setChecked(false);
				rb_xdt_yc.setChecked(true);
			}
			et_xdt.setText(generalExamination.getFzjc_xdtycxx());
			if(generalExamination.getFzjc_sfdbqx()){
				rb_dbqx_yangx.setChecked(true);
				rb_dbqx_yinx.setChecked(false);
			}else {
				rb_dbqx_yangx.setChecked(false);
				rb_dbqx_yinx.setChecked(true);
			}
			et_nwlbdb.setText(generalExamination.getFzjc_nwlbdb());
			et_thxhdb.setText(generalExamination.getFzjc_thxhdb());
			
			ViewDataUtil.setSpinnerData(sn_yxgybmky,generalExamination.getFzjc_bmky());
			ViewDataUtil.setSpinnerData(sn_yxgyhxkt,generalExamination.getFzjc_hxkt());
			ViewDataUtil.setSpinnerData(sn_yxgyeky,generalExamination.getFzjc_eky());
			ViewDataUtil.setSpinnerData(sn_yxgyekt,generalExamination.getFzjc_ekt());
			
			et_xqgbzam.setText(generalExamination.getFzjc_xqgbzam());
			et_xqgczam.setText(generalExamination.getFzjc_xqgczam());
			et_bdb.setText(generalExamination.getFzjc_bdb());
			et_zdgc.setText(generalExamination.getFzjc_zdhs());
			et_jhdhs.setText(generalExamination.getFzjc_jhdhs());
			et_xqjg.setText(generalExamination.getFzjc_xqjg());
			et_xnsd.setText(generalExamination.getFzjc_xnsd());
			et_xjnd.setText(generalExamination.getFzjc_xjnd());
			et_xnnd.setText(generalExamination.getFzjc_xnnd());
			et_zdgc.setText(generalExamination.getFzjc_zdgc());
			et_dmdzdb.setText(generalExamination.getFzjc_dmdzdb());
			et_gysz.setText(generalExamination.getFzjc_gysz());
			et_gmdzdb.setText(generalExamination.getFzjc_gmdzdb());
			et_apky.setText(generalExamination.getFzjc_apky());
			
			if(generalExamination.getFzjc_sfxbxxpzc()){
				rb_xbxsx_zc.setChecked(true);
				rb_xbxsx_yc.setChecked(false);
			}else {
				rb_xbxsx_zc.setChecked(false);
				rb_xbxsx_yc.setChecked(true);
			}
			et_xbxsx.setText(generalExamination.getFzjc_xbxxpycms());
			if(generalExamination.getFzjc_sfbczc()){
				rb_bc_zc.setChecked(true);
				rb_bc_yc.setChecked(false);
			}else {
				rb_bc_zc.setChecked(false);
				rb_bc_yc.setChecked(true);
			}
			et_bc.setText(generalExamination.getFzjc_bcycms());
			if(generalExamination.getFzjc_sfgjpzc()){
				rb_gjtp_zc.setChecked(true);
				rb_gjtp_yc.setChecked(false);
			}else {
				rb_gjtp_zc.setChecked(false);
				rb_gjtp_yc.setChecked(true);
			}
			et_gjtp.setText(generalExamination.getFzjc_gjpycms());
			et_qt.setText(generalExamination.getFzjc_qt());
		}
	}

	@Override
	public boolean validate() {
		return true;
	}
}
