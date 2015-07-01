package com.shbestwin.followupmanager.view.widget;

import java.util.Date;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class AgednessBody4 extends LinearLayout implements IBaseAgednessBody {
	private FragmentManager fragmentManager;
	private RadioGroup rg_smoking,rg_drinking,rg_sporting;
	 private RadioButton rb_smoking_w,rb_smoking_y,rb_drinking_w,rb_drinking_y,rb_sporting_w,rb_sporting_y;
	private EditText et_smoking,et_drinking,et_sporting_cz,et_sporting_fc,et_ydxm,et_syqk_dqz,et_syqk_mbz,et_mqqk,et_sffwnr,et_xcsfrq,et_sfysqm;
	private Spinner sn_drinking,sn_syqk_cd_1,sn_syqk_cd_2,sn_xltz,sn_zyxw,sn_sfpg;
	private boolean isSmoking=false,isDrinking=false,isSporting=false;
	public AgednessBody4(Context context) {
		this(context, null);
	}

	public AgednessBody4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AgednessBody4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_agedness_body4, this, true);
		rg_smoking=(RadioGroup) rootView.findViewById(R.id.rg_smoking);
		rg_drinking=(RadioGroup) rootView.findViewById(R.id.rg_drinking);
		rg_sporting=(RadioGroup) rootView.findViewById(R.id.rg_sporting);
		
		   rg_smoking = (RadioGroup) rootView.findViewById(R.id.rg_smoking);
	        rg_drinking = (RadioGroup) rootView.findViewById(R.id.rg_drinking);
	        rg_sporting = (RadioGroup) rootView.findViewById(R.id.rg_sporting);
	        rb_smoking_w=(RadioButton) rootView.findViewById(R.id.rb_smoking_w);
	        rb_smoking_y=(RadioButton) rootView.findViewById(R.id.rb_smoking_y);
	        rb_drinking_w=(RadioButton) rootView.findViewById(R.id.rb_drinking_w);
	        rb_drinking_y=(RadioButton) rootView.findViewById(R.id.rb_drinking_y);
	        rb_sporting_w=(RadioButton) rootView.findViewById(R.id.rb_sporting_w);
	        rb_sporting_y=(RadioButton) rootView.findViewById(R.id.rb_sporting_y);
		
		et_smoking=(EditText) rootView.findViewById(R.id.et_smoking);
		et_drinking=(EditText) rootView.findViewById(R.id.et_drinking);
		et_sporting_cz=(EditText) rootView.findViewById(R.id.et_sporting_cz);
		et_sporting_fc=(EditText) rootView.findViewById(R.id.et_sporting_fc);
		et_ydxm=(EditText) rootView.findViewById(R.id.et_ysxm);
		et_syqk_dqz=(EditText) rootView.findViewById(R.id.et_syqk_dqz);
		et_syqk_mbz=(EditText) rootView.findViewById(R.id.et_syqk_mbz);
		et_mqqk=(EditText) rootView.findViewById(R.id.et_mqqk);
		et_sffwnr=(EditText) rootView.findViewById(R.id.et_sffwnr);
		et_xcsfrq=(EditText) rootView.findViewById(R.id.et_xcsfrq);
		et_sfysqm=(EditText) rootView.findViewById(R.id.et_sfysqm);
		
		sn_drinking=(Spinner) rootView.findViewById(R.id.sn_drinking);
		sn_syqk_cd_1=(Spinner) rootView.findViewById(R.id.sn_syqk_cd_1);
		sn_syqk_cd_2=(Spinner) rootView.findViewById(R.id.sn_syqk_cd_2);
		sn_xltz=(Spinner) rootView.findViewById(R.id.sn_xltz);
		sn_zyxw=(Spinner) rootView.findViewById(R.id.sn_zyxw);
		sn_sfpg=(Spinner) rootView.findViewById(R.id.sn_sfpg);
		
		et_xcsfrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_xcsfrq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_xcsfrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		
		rg_smoking.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_smoking_w){
					isSmoking=false;
				}else if(checkedId==R.id.rb_smoking_y){
					isSmoking=true;
				}
			}
		});
		rg_drinking.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_drinking_w){
					isDrinking=false;
				}else if(checkedId==R.id.rb_drinking_y){
					isDrinking=true;
				}
			}
		});
		rg_sporting.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_sporting_w){
					isSporting=false;
				}else if(checkedId==R.id.rb_sporting_y){
					isSporting=true;
				}
			}
		});
	}

	@Override
	public void getData(FollowUpAged followUpAged) {
		followUpAged.setShfszd_sfxy(isSmoking);
		followUpAged.setShfszd_sfxyms(et_smoking.getText().toString());
		followUpAged.setShfszd_sfyj(isDrinking);
		followUpAged.setShfszd_sfyjms(ViewDataUtil.getSpinnerData(sn_drinking)+"/"+et_drinking.getText().toString());
		followUpAged.setShfszd_sfyd(isSporting);
		followUpAged.setShfszd_sfydms(et_sporting_cz.getText().toString()+"/"+et_sporting_fc.getText().toString());
		followUpAged.setShfszd_ydxm(et_ydxm.getText().toString());
		followUpAged.setShfszd_mqqk(et_mqqk.getText().toString());
		followUpAged.setShfszd_sffwnr(et_sffwnr.getText().toString());
		followUpAged.setShfszd_xcsfrq(et_xcsfrq.getText().toString());
		followUpAged.setShfszd_sfysqm(et_sfysqm.getText().toString());
		followUpAged.setShfszd_xltz(ViewDataUtil.getSpinnerData(sn_xltz));
		followUpAged.setShfszd_zyxw(ViewDataUtil.getSpinnerData(sn_zyxw));
		followUpAged.setShfszd_sfpg(ViewDataUtil.getSpinnerData(sn_sfpg));
		followUpAged.setShfszd_syqk(et_syqk_dqz.getText().toString()+"/"+et_syqk_mbz.getText().toString()+"/"+ViewDataUtil.getSpinnerData(sn_syqk_cd_1)+"/"+ViewDataUtil.getSpinnerData(sn_syqk_cd_2));
	}

	@Override
	public void setData(FollowUpAged followUpAged) {
		if(followUpAged!=null){
			 et_smoking.setText(followUpAged.getShfszd_sfxyms());
	            String yj=followUpAged.getShfszd_sfyjms();
	            System.out.println(yj);
	            if(yj.split("/").length!=0) {
	                ViewDataUtil.setSpinnerData(sn_drinking, yj.split("/")[0]);
	                et_drinking.setText(yj.split("/")[1]);
	            }
	            String yd=followUpAged.getShfszd_sfydms();
	            if(yd.split("/").length!=0) {
	                et_sporting_cz.setText(yj.split("/")[0]);
	                et_sporting_fc.setText(yj.split("/")[1]);
	            }
	            et_ydxm.setText(followUpAged.getShfszd_ydxm());
	            String sy=followUpAged.getShfszd_syqk();
	            if(sy.split("/").length!=0) {
	                et_syqk_dqz.setText(sy.split("/")[0]);
	                et_syqk_mbz.setText(sy.split("/")[1]);
	                ViewDataUtil.setSpinnerData(sn_syqk_cd_1, sy.split("/")[2]);
	                ViewDataUtil.setSpinnerData(sn_syqk_cd_2, sy.split("/")[3]);
	            }
	            
	            ViewDataUtil.setSpinnerData(sn_xltz, followUpAged.getShfszd_xltz());
	            ViewDataUtil.setSpinnerData(sn_zyxw, followUpAged.getShfszd_zyxw());
	            ViewDataUtil.setSpinnerData(sn_sfpg, followUpAged.getShfszd_sfpg());
	            et_xcsfrq.setText(followUpAged.getShfszd_xcsfrq());
	            et_sfysqm.setText(followUpAged.getShfszd_sfysqm());
	            et_sffwnr.setText(followUpAged.getShfszd_sffwnr());
	            et_mqqk.setText(followUpAged.getShfszd_mqqk());
	            
	            
	            if(followUpAged.getShfszd_sfxy()) {
	                rb_smoking_y.setChecked(true);
	                rb_smoking_w.setChecked(false);
	            }else {
	                rb_smoking_w.setChecked(true);
	                rb_smoking_y.setChecked(false);
	            }
	            
	            if(followUpAged.getShfszd_sfyj()) {
	                rb_drinking_y.setChecked(true);
	                rb_drinking_w.setChecked(false);
	            }else {
	                rb_drinking_y.setChecked(false);
	                rb_drinking_w.setChecked(true);
	            }
	            
	            if(followUpAged.getShfszd_sfyd()) {
	                rb_sporting_y.setChecked(true);
	                rb_sporting_w.setChecked(false);
	            }else {
	                rb_sporting_y.setChecked(false);
	                rb_sporting_w.setChecked(true);
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
		this.fragmentManager=fragmentManager;
		
	}

}
