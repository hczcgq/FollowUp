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
import com.shbestwin.followupmanager.model.followup.FollowUpFortyTwo;

public class Inspect42Body6 extends LinearLayout  implements IBaseInspect42Body{
	private RadioGroup rg_zd;
	private EditText et_qt;
	private String zd_name="性保健";
	private RadioButton rb_xbj,rb_by,rb_cmrwy,rb_qt;
	public Inspect42Body6(Context context) {
		this(context, null);
	}

	public Inspect42Body6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Inspect42Body6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_inspect_42_body6, this, true);
		rg_zd=(RadioGroup) rootView.findViewById(R.id.rg_zd);
		et_qt=(EditText) rootView.findViewById(R.id.et_qt);
		rb_xbj=(RadioButton) rootView.findViewById(R.id.rb_xbj);
		rb_by=(RadioButton) rootView.findViewById(R.id.rb_by);
		rb_cmrwy=(RadioButton) rootView.findViewById(R.id.rb_cmrwy);
		rb_qt=(RadioButton) rootView.findViewById(R.id.rb_qt);
		
		rg_zd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_xbj){
					zd_name="性保健";
				}else if(checkedId==R.id.rb_by){
					zd_name="避孕";
				}else if(checkedId==R.id.rb_cmrwy){
					zd_name="纯母乳喂养6个月";
				}else if(checkedId==R.id.rb_qt){
					zd_name=et_qt.getText().toString();
				}
			}
		});
	}
	@Override
	public void getData(FollowUpFortyTwo followUpFortyTwo) {
		followUpFortyTwo.setZd_name(zd_name);
	}

	@Override
	public void setData(FollowUpFortyTwo followUpFortyTwo) {
		if(followUpFortyTwo!=null){
			if(followUpFortyTwo.getZd_name().equals("性保健")){
				rb_xbj.setChecked(true);
				rb_by.setChecked(false);
				rb_cmrwy.setChecked(false);
				rb_qt.setChecked(false);
			}else if(followUpFortyTwo.getZd_name().equals("避孕")){
				rb_xbj.setChecked(false);
				rb_by.setChecked(true);
				rb_cmrwy.setChecked(false);
				rb_qt.setChecked(false);
			}else if(followUpFortyTwo.getZd_name().equals("纯母乳喂养6个月")){
				rb_xbj.setChecked(false);
				rb_by.setChecked(false);
				rb_cmrwy.setChecked(true);
				rb_qt.setChecked(false);
			}else {
				rb_xbj.setChecked(false);
				rb_by.setChecked(false);
				rb_cmrwy.setChecked(false);
				rb_qt.setChecked(true);
				et_qt.setText(followUpFortyTwo.getZd_name());
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
