package com.shbestwin.followupmanager.view.widget;

import java.util.Date;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class AgednessBody1 extends LinearLayout implements IBaseAgednessBody {
	private FragmentManager fragmentManager;
	private EditText et_zrys,et_sfrq;
	private Spinner sn_sfzqjy,sn_sffs,sn_sfxz,sn_xlzt;
	public AgednessBody1(Context context) {
		this(context, null);
	}

	public AgednessBody1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AgednessBody1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_agedness_body1, this, true);
		et_zrys=(EditText) rootView.findViewById(R.id.et_zrys);
		et_sfrq=(EditText) rootView.findViewById(R.id.et_sfrq);
		sn_sfzqjy=(Spinner) rootView.findViewById(R.id.sn_sfzqjy);
		sn_sffs=(Spinner) rootView.findViewById(R.id.sn_sffs);
		sn_sfxz=(Spinner) rootView.findViewById(R.id.sn_sfxz);
		sn_xlzt=(Spinner) rootView.findViewById(R.id.sn_xlzt);
		
		et_sfrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_sfrq.setOnClickListener(new OnClickListener() {
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
								et_sfrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
	}

	@Override
	public void getData(FollowUpAged followUpAged) {

		followUpAged.setGrxx_zrys(et_zrys.getText().toString());
		followUpAged.setGrxx_sfrq(et_sfrq.getText().toString());
		followUpAged.setGrxx_sfzqjy(ViewDataUtil.getSpinnerData(sn_sfzqjy));
		followUpAged.setGrxx_sffs(ViewDataUtil.getSpinnerData(sn_sffs));
		followUpAged.setGrxx_sfxz(ViewDataUtil.getSpinnerData(sn_sfxz));
		followUpAged.setGrxx_xlzt(ViewDataUtil.getSpinnerData(sn_xlzt));
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
		this.fragmentManager=fragmentManager;
	}

}
