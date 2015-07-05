package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;

public class Antenatal1Body11 extends LinearLayout  implements IBaseAntenatal1Body {
	private RadioGroup rg_wy,rg_yd,rg_gj,rg_zg,rg_fj;
	private EditText et_wy,et_yd,et_gj,et_zg,et_fj;
	private boolean isWyYc=false,isYdYc=false,isGjYc=false,isZgYc=false,isFjYc=false;
	private RadioButton rb_wy_w,rb_wy_y,rb_yd_w,rb_yd_y,rb_gj_w,rb_gj_y,rb_zg_w,rb_zg_y,rb_fj_w,rb_fj_y;
	public Antenatal1Body11(Context context) {
		this(context, null);
	}

	public Antenatal1Body11(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Antenatal1Body11(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_antenatal1_body11, this, true);
		rb_wy_w=(RadioButton) rootView.findViewById(R.id.rb_wy_w);
		rb_wy_y=(RadioButton) rootView.findViewById(R.id.rb_wy_y);
		rb_yd_w=(RadioButton) rootView.findViewById(R.id.rb_yd_w);
		rb_yd_y=(RadioButton) rootView.findViewById(R.id.rb_yd_y);
		rb_gj_w=(RadioButton) rootView.findViewById(R.id.rb_gj_w);
		rb_gj_y=(RadioButton) rootView.findViewById(R.id.rb_gj_y);
		rb_zg_w=(RadioButton) rootView.findViewById(R.id.rb_zg_w);
		rb_zg_y=(RadioButton) rootView.findViewById(R.id.rb_zg_y);
		rb_fj_w=(RadioButton) rootView.findViewById(R.id.rb_fj_w);
		rb_fj_y=(RadioButton) rootView.findViewById(R.id.rb_fj_y);
		
		rg_wy=(RadioGroup) rootView.findViewById(R.id.rg_wy);
		rg_yd=(RadioGroup) rootView.findViewById(R.id.rg_yd);
		rg_gj=(RadioGroup) rootView.findViewById(R.id.rg_gj);
		rg_zg=(RadioGroup) rootView.findViewById(R.id.rg_zg);
		rg_fj=(RadioGroup) rootView.findViewById(R.id.rg_fj);
		et_wy=(EditText) rootView.findViewById(R.id.et_wy);
		et_yd=(EditText) rootView.findViewById(R.id.et_yd);
		et_gj=(EditText) rootView.findViewById(R.id.et_gj);
		et_zg=(EditText) rootView.findViewById(R.id.et_zg);
		et_fj=(EditText) rootView.findViewById(R.id.et_fj);
		rg_wy.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_wy_w){
					isWyYc=false;
				}else if(checkedId==R.id.rb_wy_y){
					isWyYc=true;
				}
			}
		});
		rg_yd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(checkedId==R.id.rb_yd_w){
			isYdYc=false;
		}else if(checkedId==R.id.rb_yd_y){
			isYdYc=true;
		}
	}
});
		rg_gj.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(checkedId==R.id.rb_gj_w){
			isGjYc=false;
		}else if(checkedId==R.id.rb_gj_y){
			isGjYc=true;
		}
	}
});
		rg_zg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(checkedId==R.id.rb_zg_w){
			isZgYc=false;
		}else if(checkedId==R.id.rb_zg_y){
			isZgYc=true;
		}
	}
});
		rg_fj.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(checkedId==R.id.rb_fj_w){
			isFjYc=false;
		}else if(checkedId==R.id.rb_fj_y){
			isFjYc=true;
		}
	}
});
	}
	@Override
	public void getData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		followUpFirstPregnancy.setFkjc_sfwyyc(isWyYc);
		followUpFirstPregnancy.setFkjc_sfydyc(isYdYc);
		followUpFirstPregnancy.setFkjc_sfgjyc(isGjYc);
		followUpFirstPregnancy.setFkjc_sfzgyc(isZgYc);
		followUpFirstPregnancy.setFkjc_sffjyc(isFjYc);
		followUpFirstPregnancy.setTingz_sfwyycms(et_wy.getText().toString());
		followUpFirstPregnancy.setTingz_sfydycms(et_yd.getText().toString());
		followUpFirstPregnancy.setTingz_sfgjycms(et_gj.getText().toString());
		followUpFirstPregnancy.setTingz_sfzgycms(et_zg.getText().toString());
		followUpFirstPregnancy.setTingz_sffjycms(et_fj.getText().toString());
	}

	@Override
	public void setData(FollowUpFirstPregnancy followUpFirstPregnancy) {
		if(followUpFirstPregnancy!=null){
			et_wy.setText(followUpFirstPregnancy.getTingz_sfwyycms());
			et_yd.setText(followUpFirstPregnancy.getTingz_sfydycms());
			et_gj.setText(followUpFirstPregnancy.getTingz_sfgjycms());
			et_zg.setText(followUpFirstPregnancy.getTingz_sfzgycms());
			et_fj.setText(followUpFirstPregnancy.getTingz_sffjycms());
			
			if(followUpFirstPregnancy.getFkjc_sfwyyc()){
				rb_wy_y.setChecked(true);
				rb_wy_w.setChecked(false);
			}else {
				rb_wy_y.setChecked(true);
				rb_wy_w.setChecked(true);
			}
			
			if(followUpFirstPregnancy.getFkjc_sfydyc()){
				rb_yd_y.setChecked(true);
				rb_yd_w.setChecked(false);
			}else {
				rb_yd_y.setChecked(true);
				rb_yd_w.setChecked(true);
			}
			if(followUpFirstPregnancy.getFkjc_sfgjyc()){
				rb_gj_y.setChecked(true);
				rb_gj_w.setChecked(false);
			}else {
				rb_gj_y.setChecked(true);
				rb_gj_w.setChecked(true);
			}
			if(followUpFirstPregnancy.getFkjc_sfzgyc()){
				rb_zg_y.setChecked(true);
				rb_zg_w.setChecked(false);
			}else {
				rb_zg_y.setChecked(true);
				rb_zg_w.setChecked(true);
			}
			if(followUpFirstPregnancy.getFkjc_sffjyc()){
				rb_fj_y.setChecked(true);
				rb_fj_w.setChecked(false);
			}else {
				rb_fj_y.setChecked(true);
				rb_fj_w.setChecked(true);
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
}
