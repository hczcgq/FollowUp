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
import com.shbestwin.followupmanager.model.followup.FollowUpDisabledPerson;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class DisabilityBody1 extends LinearLayout implements IBaseDisabilityBody {
	private EditText et_sfrq,et_zrys;
	private Spinner sn_sffs,sn_knqt,sn_kfxq;
	private FragmentManager fragmentManager;
	public DisabilityBody1(Context context) {
		this(context, null);
	}

	public DisabilityBody1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DisabilityBody1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_disability_body1, this, true);
		et_sfrq=(EditText) rootView.findViewById(R.id.et_sfqr);
		et_zrys=(EditText) rootView.findViewById(R.id.et_zrys);
		sn_sffs=(Spinner) rootView.findViewById(R.id.sn_sffs);
		sn_knqt=(Spinner) rootView.findViewById(R.id.sn_knqt);
		sn_kfxq=(Spinner) rootView.findViewById(R.id.sn_kfxq);
		
		
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
	public void getData(FollowUpDisabledPerson followUpDisabledPerson) {
		followUpDisabledPerson.setGrxx_sfrq(et_sfrq.getText().toString());
		followUpDisabledPerson.setGrxx_sffs(ViewDataUtil.getSpinnerData(sn_sffs));
		followUpDisabledPerson.setGrxx_knqt(ViewDataUtil.getSpinnerData(sn_knqt));
		followUpDisabledPerson.setGrxx_kfxq(ViewDataUtil.getSpinnerData(sn_kfxq));
		followUpDisabledPerson.setGrxx_zrys(et_zrys.getText().toString());
	}

	@Override
	public void setData(FollowUpDisabledPerson followUpDisabledPerson) {
	    if(followUpDisabledPerson!=null) {
    	    et_sfrq.setText(followUpDisabledPerson.getGrxx_sfrq());
    	    et_zrys.setText(followUpDisabledPerson.getGrxx_zrys());
    	    ViewDataUtil.setSpinnerData(sn_sffs, followUpDisabledPerson.getGrxx_sffs());
    	    ViewDataUtil.setSpinnerData(sn_knqt, followUpDisabledPerson.getGrxx_knqt());
    	    ViewDataUtil.setSpinnerData(sn_kfxq, followUpDisabledPerson.getGrxx_kfxq());
	    }
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		this.fragmentManager=fragmentManager;
	}

}
