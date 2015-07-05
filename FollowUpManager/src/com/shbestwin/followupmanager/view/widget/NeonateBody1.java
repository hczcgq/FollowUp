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
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class NeonateBody1 extends LinearLayout implements IBaseNeonateBody {
	
	private EditText et_fsrq,et_csrl,et_zrys;
	private Spinner sn_fscs;
	private FragmentManager fragmentManager;
	
	public NeonateBody1(Context context) {
		this(context, null);
	}

	public NeonateBody1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NeonateBody1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_neonate_body1, this, true);
		et_fsrq=(EditText) rootView.findViewById(R.id.et_fsrq);
		et_csrl=(EditText) rootView.findViewById(R.id.et_csrl);
		et_zrys=(EditText) rootView.findViewById(R.id.et_zrys);
		sn_fscs=(Spinner) rootView.findViewById(R.id.sn_fscs);
		
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
	public void getData(FollowUpNewborn followUpNewborn) {
		followUpNewborn.setGrxx_fsrq(et_fsrq.getText().toString());
		followUpNewborn.setGrxx_fscs(ViewDataUtil.getSpinnerData(sn_fscs));
		followUpNewborn.setGrxx_csrl(et_csrl.getText().toString());
		followUpNewborn.setGrxx_zrys(et_zrys.getText().toString());
	}

	@Override
	public void setData(FollowUpNewborn followUpNewborn) {
		if(followUpNewborn!=null){
			et_fsrq.setText(followUpNewborn.getGrxx_fsrq());
			et_csrl.setText(followUpNewborn.getGrxx_csrl());
			et_zrys.setText(followUpNewborn.getGrxx_zrys());
			ViewDataUtil.setSpinnerData(sn_fscs, followUpNewborn.getGrxx_fscs());
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
