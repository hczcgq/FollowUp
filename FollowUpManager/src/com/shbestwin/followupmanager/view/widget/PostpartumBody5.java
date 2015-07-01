package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpPostpartum;

public class PostpartumBody5 extends LinearLayout  implements IBasePostpartumBody{
	private RadioGroup rg_el,rg_zg,rg_sk;
	private EditText et_el,et_zg,et_sk,et_qt;
	private Spinner sn_skyhqk;
	private boolean isEl=false,isZg=false,isSk=false;
	public PostpartumBody5(Context context) {
		this(context, null);
	}

	public PostpartumBody5(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PostpartumBody5(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_postpartum_body5, this, true);
		rg_el=(RadioGroup) rootView.findViewById(R.id.rg_el);
		rg_zg=(RadioGroup) rootView.findViewById(R.id.rg_zg);
		rg_sk=(RadioGroup) rootView.findViewById(R.id.rg_sk);
		
		et_el=(EditText) rootView.findViewById(R.id.et_el);
		et_zg=(EditText) rootView.findViewById(R.id.et_zg);
		et_sk=(EditText) rootView.findViewById(R.id.et_sk);
		et_qt=(EditText) rootView.findViewById(R.id.et_qt);
		sn_skyhqk=(Spinner) rootView.findViewById(R.id.sn_skyhqk);
		
		rg_el.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_el_w){
					isEl=false;
				}else if(checkedId==R.id.rb_el_y){
					isEl=true;
				}
			}
		});
		rg_zg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_zg_w){
					isZg=false;
				}else if(checkedId==R.id.rb_zg_y){
					isZg=true;
				}
			}
		});
		rg_sk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_sk_w){
					isSk=false;
				}else if(checkedId==R.id.rb_sk_y){
					isSk=true;
				}
			}
		});
	}
	@Override
	public void getData(FollowUpPostpartum followUpPostpartum) {
		followUpPostpartum.setChjc_sfelyc(isEl);
		followUpPostpartum.setChjc_sfelycms(et_el.getText().toString());
		followUpPostpartum.setChjc_sfzgyc(isZg);
		followUpPostpartum.setChjc_sfzgycms(et_zg.getText().toString());
		followUpPostpartum.setChjc_sfskyc(isSk);
		followUpPostpartum.setChjc_sfskycms(et_sk.getText().toString());
		followUpPostpartum.setChjc_skyhzk(ViewDataUtil.getSpinnerData(sn_skyhqk));
		followUpPostpartum.setChjc_qt(et_qt.getText().toString());
	}

	@Override
	public void setData(FollowUpPostpartum followUpPostpartum) {
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
