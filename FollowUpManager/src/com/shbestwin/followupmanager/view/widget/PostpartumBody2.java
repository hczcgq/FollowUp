package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpPostpartum;

public class PostpartumBody2 extends LinearLayout implements IBasePostpartumBody {
	private EditText et_tw,et_xy_ssy,et_xy_szy,et_mb;
	public PostpartumBody2(Context context) {
		this(context, null);
	}

	public PostpartumBody2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PostpartumBody2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_postpartum_body2, this, true);
		et_tw=(EditText) rootView.findViewById(R.id.et_tw);
		et_xy_ssy=(EditText) rootView.findViewById(R.id.et_xy_ssy);
		et_xy_szy=(EditText) rootView.findViewById(R.id.et_xy_szy);
		et_mb=(EditText) rootView.findViewById(R.id.et_mb);
	}
	@Override
	public void getData(FollowUpPostpartum followUpPostpartum) {
		followUpPostpartum.setJbjc_tw(et_tw.getText().toString());
		followUpPostpartum.setJbjc_xy(et_xy_ssy.getText().toString()+"/"+et_xy_szy.getText().toString());
		followUpPostpartum.setJbjc_mb(et_mb.getText().toString());
	}

	@Override
	public void setData(FollowUpPostpartum followUpPostpartum) {

		if(followUpPostpartum!=null){
			et_tw.setText(followUpPostpartum.getJbjc_tw());
			String xy=followUpPostpartum.getJbjc_xy();
			if(xy.split("/").length==1){
				et_xy_ssy.setText(xy.split("/")[0]);
			}else if(xy.split("/").length==2){
				et_xy_ssy.setText(xy.split("/")[0]);
				et_xy_szy.setText(xy.split("/")[1]);
			}
			et_mb.setText(followUpPostpartum.getJbjc_mb());
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
