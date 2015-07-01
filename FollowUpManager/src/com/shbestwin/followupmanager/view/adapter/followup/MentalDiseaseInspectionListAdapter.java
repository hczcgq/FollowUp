package com.shbestwin.followupmanager.view.adapter.followup;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.MentalDiseaseInspection;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class MentalDiseaseInspectionListAdapter extends ArrayListAdapter<MentalDiseaseInspection> {

	public MentalDiseaseInspectionListAdapter(Context context, List<MentalDiseaseInspection> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_followup_mental_disease_inspection_list_item, parent, false);
		}
		if (position % 2 == 0) {
			convertView.setBackgroundColor(cardinalBg);
		} else {
			convertView.setBackgroundColor(evenBg);
		}

		return convertView;
	}

}
