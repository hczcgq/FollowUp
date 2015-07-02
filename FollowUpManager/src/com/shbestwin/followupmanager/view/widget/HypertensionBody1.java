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
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class HypertensionBody1 extends LinearLayout implements
		IBaseHypertensionBody {

	private EditText et_zrys, et_sfrq;
	private Spinner sn_sffs, sn_gxylx;
	private FragmentManager fragment;

	public HypertensionBody1(Context context) {
		this(context, null);
	}

	public HypertensionBody1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody1(final Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_hypertension_body1, this, true);
		et_zrys = (EditText) rootView.findViewById(R.id.et_zrys);
		et_sfrq = (EditText) rootView.findViewById(R.id.et_sfrq);
		sn_sffs = (Spinner) rootView.findViewById(R.id.sn_sffs);
		sn_gxylx = (Spinner) rootView.findViewById(R.id.sn_gxylx);

		et_sfrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_sfrq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragment, "datePickerDialog");
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
	public void getData(FollowUpHypertension followUpHypertension) {

		followUpHypertension.setGrxx_zrys(et_zrys.getText().toString());
		followUpHypertension.setGrxx_sfrq(et_sfrq.getText().toString());
		followUpHypertension.setGrxx_sffs(ViewDataUtil.getSpinnerData(sn_sffs));
		followUpHypertension.setGrxx_gxylx(ViewDataUtil.getSpinnerData(sn_gxylx));

	}

	@Override
	public void setData(FollowUpHypertension followUpHypertension) {
	    if(followUpHypertension!=null) {
	        et_sfrq.setText(followUpHypertension.getGrxx_sfrq());;
	        et_zrys.setText(followUpHypertension.getGrxx_zrys());
	        ViewDataUtil.setSpinnerData(sn_gxylx, followUpHypertension.getGrxx_gxylx());
	        ViewDataUtil.setSpinnerData(sn_sffs, followUpHypertension.getGrxx_sffs());
	    }
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragment) {
		this.fragment = fragment;
	}
}
