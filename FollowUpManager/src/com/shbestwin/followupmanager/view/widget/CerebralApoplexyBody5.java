package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.model.followup.FollowUpStroke;
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.view.adapter.followup.MedicationListAdapter;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;
import com.shbestwin.followupmanager.view.dialog.followup.MedicationDialog;

public class CerebralApoplexyBody5 extends LinearLayout implements IBaseCerebralApoplexyBody {
	private View medicationButton;
	private ListView medicationListView;

	private MedicationListAdapter medicationListAdapter;
	
	private EditText et_fcrq,et_tg,et_tc,et_ldl,et_hdl,et_lp;
	
	private FragmentManager fragmentManager;

	public CerebralApoplexyBody5(Context context) {
		this(context, null);
	}

	public CerebralApoplexyBody5(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CerebralApoplexyBody5(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_cerebral_apoplexy_body5, this, true);
		medicationButton = rootView.findViewById(R.id.medicationButton);
		medicationListView = (ListView) rootView.findViewById(R.id.medicationListView);
		et_fcrq = (EditText) rootView.findViewById(R.id.et_fcrq);
		et_tg = (EditText) rootView.findViewById(R.id.et_tg);
		et_tc = (EditText) rootView.findViewById(R.id.et_tc);
		et_ldl = (EditText) rootView.findViewById(R.id.et_ldl);
		et_hdl = (EditText) rootView.findViewById(R.id.et_hdl);
		et_lp = (EditText) rootView.findViewById(R.id.et_lp);
		et_fcrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_fcrq.setOnClickListener(new OnClickListener() {
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
								et_fcrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});

		medicationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMedicationDialog();
			}
		});

		List<Medication> medicationList = new ArrayList<Medication>();
		for (int i = 0; i < 10; i++) {
			medicationList.add(new Medication());
		}
		medicationListAdapter = new MedicationListAdapter(getContext(), medicationList);
		medicationListView.setAdapter(medicationListAdapter);
	}

	private void showMedicationDialog() {
		MedicationDialog medicationDialog = MedicationDialog.newInstance();
		medicationDialog.show(((FragmentActivity) getContext()).getSupportFragmentManager(), "medicationDialog");
	}

	@Override
	public void getData(FollowUpStroke followUpStroke) {

		followUpStroke.setWxyskzgxz_fcrq(et_fcrq.getText().toString());
		followUpStroke.setWxyskzgxz_tg(et_tg.getText().toString());
		followUpStroke.setWxyskzgxz_tc(et_tc.getText().toString());
		followUpStroke.setWxyskzgxz_ldl(et_ldl.getText().toString());
		followUpStroke.setWxyskzgxz_hdl(et_hdl.getText().toString());
		followUpStroke.setWxyskzgxz_LP(et_lp.getText().toString());
	}

	@Override
	public void setData(FollowUpStroke followUpStroke) {
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
