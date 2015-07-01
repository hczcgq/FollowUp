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
import com.shbestwin.followupmanager.model.followup.MentalDiseaseMedicine;
import com.shbestwin.followupmanager.view.adapter.followup.MentalDiseaseMedicineListAdapter;
import com.shbestwin.followupmanager.view.dialog.followup.MentalDiseaseMedicineDialog;

public class MentalDiseaseBody7 extends LinearLayout implements IBaseMentalDiseaseBody {

	private View mentalDiseaseMedicineButton;
	private ListView mentalDiseaseMedicineListView;

	private MentalDiseaseMedicineListAdapter medicineListAdapter;

	public MentalDiseaseBody7(Context context) {
		this(context, null);
	}

	public MentalDiseaseBody7(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MentalDiseaseBody7(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_mental_disease_body7, this, true);
		mentalDiseaseMedicineButton = rootView.findViewById(R.id.mentalDiseaseMedicineButton);
		mentalDiseaseMedicineListView = (ListView) rootView.findViewById(R.id.mentalDiseaseMedicineListView);

		mentalDiseaseMedicineButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMentalDiseaseMedicineDialog();
			}
		});

		List<MentalDiseaseMedicine> diseaseList = new ArrayList<MentalDiseaseMedicine>();
		for (int i = 0; i < 10; i++) {
			diseaseList.add(new MentalDiseaseMedicine());
		}
		medicineListAdapter = new MentalDiseaseMedicineListAdapter(getContext(), diseaseList);
		mentalDiseaseMedicineListView.setAdapter(medicineListAdapter);
	}

	private void showMentalDiseaseMedicineDialog() {
		MentalDiseaseMedicineDialog mentalDiseaseMedicineDialog = MentalDiseaseMedicineDialog.newInstance();
		mentalDiseaseMedicineDialog.show(((FragmentActivity) getContext()).getSupportFragmentManager(), "mentalDiseaseMedicineDialog");
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
