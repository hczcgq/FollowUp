package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;
import com.shbestwin.followupmanager.model.followup.MentalDiseaseInspection;
import com.shbestwin.followupmanager.view.adapter.followup.MentalDiseaseInspectionListAdapter;
import com.shbestwin.followupmanager.view.dialog.followup.MentalDiseaseInspectionDialog;

public class MentalDiseaseBody6 extends LinearLayout  implements IBaseMentalDiseaseBody{
	private View inspectionResultButton;
	private ListView inspectionResultListView;

	private MentalDiseaseInspectionListAdapter inspectionListAdapter;

	public MentalDiseaseBody6(Context context) {
		this(context, null);
	}

	public MentalDiseaseBody6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MentalDiseaseBody6(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_mental_disease_body6, this, true);
		inspectionResultButton = rootView.findViewById(R.id.inspectionResultButton);
		inspectionResultListView = (ListView) rootView.findViewById(R.id.inspectionResultListView);

		inspectionResultButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showInspectionResultDialog();
			}
		});

		List<MentalDiseaseInspection> diseaseList = new ArrayList<MentalDiseaseInspection>();
		for (int i = 0; i < 10; i++) {
			diseaseList.add(new MentalDiseaseInspection());
		}
		inspectionListAdapter = new MentalDiseaseInspectionListAdapter(getContext(), diseaseList);
		inspectionResultListView.setAdapter(inspectionListAdapter);
	}
	
	private void showInspectionResultDialog() {
		MentalDiseaseInspectionDialog mentalDiseaseInspectionDialog = MentalDiseaseInspectionDialog.newInstance();
		mentalDiseaseInspectionDialog.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "mentalDiseaseInspectionDialog");
	}
	@Override
	public void getData(FollowUpMentalDisease followUpMentalDisease) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setData(FollowUpMentalDisease followUpMentalDisease) {
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
