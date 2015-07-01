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

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpPostpartum;

public class PostpartumBody6 extends LinearLayout  implements IBasePostpartumBody{
	private RadioGroup rg_zd;
	private String zd_name="个人卫生";
	public PostpartumBody6(Context context) {
		this(context, null);
	}

	public PostpartumBody6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PostpartumBody6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_postpartum_body6, this, true);
		rg_zd=(RadioGroup) rootView.findViewById(R.id.rg_zd);
		
		rg_zd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_grws){
					zd_name="个人卫生";
				}else if(checkedId==R.id.rb_xl){
					zd_name="心理";
				}else if(checkedId==R.id.rb_yy){
					zd_name="营养";
				}else if(checkedId==R.id.rb_mrwy){
					zd_name="母乳喂养";
				}else if(checkedId==R.id.rb_xsethl){
					zd_name="新生儿护理与喂养";
				}
			}
		});
	}
	@Override
	public void getData(FollowUpPostpartum followUpPostpartum) {
		followUpPostpartum.setZd_name(zd_name);
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
