package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpStroke;
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.view.adapter.followup.MedicationListAdapter;
import com.shbestwin.followupmanager.view.dialog.followup.MedicationDialog;

public class CerebralApoplexyBody3 extends LinearLayout implements IBaseCerebralApoplexyBody {
	private View medicationButton;
	private ListView medicationListView;

	private MedicationListAdapter medicationListAdapter;
	
	private RadioGroup rg_check;
	private EditText et_ssy,et_szy;
	private boolean isHas=false;

	public CerebralApoplexyBody3(Context context) {
		this(context, null);
	}

	public CerebralApoplexyBody3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CerebralApoplexyBody3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_cerebral_apoplexy_body3, this, true);
		medicationButton = rootView.findViewById(R.id.medicationButton);
		medicationListView = (ListView) rootView.findViewById(R.id.medicationListView);
		rg_check=(RadioGroup) rootView.findViewById(R.id.rg_check);
		et_ssy=(EditText) rootView.findViewById(R.id.et_ssy);
		et_szy=(EditText) rootView.findViewById(R.id.et_szy);
		rg_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_w){
					isHas=false;
				}else if(checkedId==R.id.rb_y){
					isHas=true;
				}
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
		followUpStroke.setWxyskzgxy_sfmzcxy(String.valueOf(isHas));
		followUpStroke.setWxyskzgxy_sfmzcxyms(et_ssy.getText().toString()+"/"+et_szy.getText().toString());
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
		// TODO Auto-generated method stub
		
	}
}
