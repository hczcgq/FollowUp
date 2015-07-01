package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;

public class AgednessBody3 extends LinearLayout implements IBaseAgednessBody {
	private EditText et_xy_ssy,et_xy_szy,et_xl,et_yw,et_sg,et_tz_dqz,et_tz_mbz,et_tzzs_dqz,et_tzzs_mbz,et_xzdgc,et_gmdzdb,
					et_xgysz,et_dmdzdb,et_kfxt,et_qt;
	public AgednessBody3(Context context) {
		this(context, null);
	}

	public AgednessBody3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AgednessBody3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_agedness_body3, this, true);
		et_xy_ssy=(EditText) rootView.findViewById(R.id.et_xy_ssy);
		et_xy_szy=(EditText) rootView.findViewById(R.id.et_xy_szy);
		et_xl=(EditText) rootView.findViewById(R.id.et_xl);
		et_yw=(EditText) rootView.findViewById(R.id.et_yw);
		et_sg=(EditText) rootView.findViewById(R.id.et_sg);
		et_tz_dqz=(EditText) rootView.findViewById(R.id.et_tz_dqz);
		et_tz_mbz=(EditText) rootView.findViewById(R.id.et_tz_mbz);
		et_tzzs_dqz=(EditText) rootView.findViewById(R.id.et_tzzs_dqz);
		et_tzzs_mbz=(EditText) rootView.findViewById(R.id.et_tzzs_mbz);
		et_xzdgc=(EditText) rootView.findViewById(R.id.et_xzdgc);
		et_gmdzdb=(EditText) rootView.findViewById(R.id.et_gmdzdb);
		et_xgysz=(EditText) rootView.findViewById(R.id.et_xgysz);
		et_dmdzdb=(EditText) rootView.findViewById(R.id.et_dmdzdb);
		et_kfxt=(EditText) rootView.findViewById(R.id.et_kfxt);
		et_qt=(EditText) rootView.findViewById(R.id.et_qt);
	}
	@Override
	public void getData(FollowUpAged followUpAged) {
		followUpAged.setTz_xy(et_xy_ssy.getText().toString()+"/"+et_xy_szy.getText().toString());
		followUpAged.setTz_xl(et_xl.getText().toString());
		followUpAged.setTz_yw(et_yw.getText().toString());
		followUpAged.setTz_sg(et_sg.getText().toString());
		followUpAged.setTz_tz(et_tz_dqz.getText().toString()+"/"+et_tz_mbz.getText().toString());
		followUpAged.setTz_tzzs(et_tzzs_dqz.getText().toString()+"/"+et_tzzs_mbz.getText().toString());
		followUpAged.setTz_xzdgc(et_xzdgc.getText().toString());
		followUpAged.setTz_gmddb(et_gmdzdb.getText().toString());
		followUpAged.setTz_xgysz(et_xgysz.getText().toString());
		followUpAged.setTz_dmddb(et_dmdzdb.getText().toString());
		followUpAged.setTz_kfxt(et_kfxt.getText().toString());
		followUpAged.setTz_qt(et_qt.getText().toString());
	}

	@Override
	public void setData(FollowUpAged followUpAged) {
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
