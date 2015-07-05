package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpFortyTwo;

public class Inspect42Body2 extends LinearLayout  implements IBaseInspect42Body{
	private EditText et_tw,et_xy_ssy,et_xy_szy,et_mb,et_tz;
	public Inspect42Body2(Context context) {
		this(context, null);
	}

	public Inspect42Body2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Inspect42Body2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_inspect_42_body2, this, true);
		et_tw=(EditText) rootView.findViewById(R.id.et_tw);
		et_xy_ssy=(EditText) rootView.findViewById(R.id.et_xy_ssy);
		et_xy_szy=(EditText) rootView.findViewById(R.id.et_xy_szy);
		et_mb=(EditText) rootView.findViewById(R.id.et_mb);
		et_tz=(EditText) rootView.findViewById(R.id.et_tz);
	}
	@Override
	public void getData(FollowUpFortyTwo followUpFortyTwo) {

		followUpFortyTwo.setJbjc_tw(et_tw.getText().toString());
		followUpFortyTwo.setJbjc_xy(et_xy_ssy.getText().toString()+"/"+et_xy_szy.getText().toString());
		followUpFortyTwo.setJbjc_mb(et_mb.getText().toString());
		followUpFortyTwo.setJbjc_tz(et_tz.getText().toString());
	}

	@Override
	public void setData(FollowUpFortyTwo followUpFortyTwo) {
		if(followUpFortyTwo!=null){
			et_tw.setText(followUpFortyTwo.getJbjc_tw());
			String xy=followUpFortyTwo.getJbjc_xy();
			if(xy.split("/").length==1){
				et_xy_ssy.setText(xy.split("/")[0]);
			}else if(xy.split("/").length==2){
				et_xy_ssy.setText(xy.split("/")[0]);
				et_xy_szy.setText(xy.split("/")[1]);
			}
			et_mb.setText(followUpFortyTwo.getJbjc_mb());
			et_tz.setText(followUpFortyTwo.getJbjc_tz());
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
