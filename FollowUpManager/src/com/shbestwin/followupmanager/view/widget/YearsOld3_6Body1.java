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
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class YearsOld3_6Body1 extends LinearLayout implements IBaseYearsOld3_6Body{
	private EditText et_fsrq,et_zrys,et_csrl;
	private Spinner sn_yn;
	private FragmentManager fragmentManager;
	public YearsOld3_6Body1(Context context) {
		this(context, null);
	}

	public YearsOld3_6Body1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld3_6Body1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_3_6_body1, this, true);
		et_fsrq=(EditText) rootView.findViewById(R.id.et_fsrq);
		et_zrys=(EditText) rootView.findViewById(R.id.et_zrys);
		et_csrl=(EditText) rootView.findViewById(R.id.et_csrl);
		sn_yn=(Spinner) rootView.findViewById(R.id.sn_yn);
		
		et_fsrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_fsrq.setOnClickListener(new OnClickListener() {
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
								et_fsrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
	}

	@Override
	public void getData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		followUpThreeSixNewborn.setGrxx_sjnl(et_csrl.getText().toString());
		followUpThreeSixNewborn.setGrxx_fsrq(et_fsrq.getText().toString());
		followUpThreeSixNewborn.setGrxx_zrys(et_zrys.getText().toString());
		followUpThreeSixNewborn.setGrxx_nl(ViewDataUtil.getSpinnerData(sn_yn));
	}

	@Override
	public void setData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		if(followUpThreeSixNewborn!=null){
			et_fsrq.setText(followUpThreeSixNewborn.getGrxx_fsrq());
			et_zrys.setText(followUpThreeSixNewborn.getGrxx_zrys());
			et_csrl.setText(followUpThreeSixNewborn.getGrxx_sjnl());
			ViewDataUtil.setSpinnerData(sn_yn, followUpThreeSixNewborn.getGrxx_nl());
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
