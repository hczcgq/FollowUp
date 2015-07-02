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
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class DiabetesMellitusBody1 extends LinearLayout implements IBaseDiabetesMellitusBody{
	private FragmentManager fragmentManager;
	private EditText et_zrys,et_sfrq;
	private Spinner sn_sffs;
	public DiabetesMellitusBody1(Context context) {
		this(context, null);
	}

	public DiabetesMellitusBody1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DiabetesMellitusBody1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_diabetes_mellitus_body1, this, true);
		et_zrys=(EditText) rootView.findViewById(R.id.et_zrys);
		et_sfrq=(EditText) rootView.findViewById(R.id.et_sfrq);
		sn_sffs=(Spinner) rootView.findViewById(R.id.sn_sffs);
		
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
	public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		followUpDiabetesMellitus.setGrxx_zrys(et_zrys.getText().toString());
		followUpDiabetesMellitus.setGrxx_sfrq(et_sfrq.getText().toString());
		followUpDiabetesMellitus.setGrxx_sffs(ViewDataUtil.getSpinnerData(sn_sffs));
	}

	@Override
	public void setData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		if(followUpDiabetesMellitus!=null) {
		    et_sfrq.setText(followUpDiabetesMellitus.getGrxx_sfrq());
		    et_zrys.setText(followUpDiabetesMellitus.getGrxx_zrys());
		    ViewDataUtil.setSpinnerData(sn_sffs, followUpDiabetesMellitus.getGrxx_sffs());
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
