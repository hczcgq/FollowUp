package com.shbestwin.followupmanager.view.adapter.archive;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.archive.PastHistory;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class PastHistoryListAdapter extends ArrayListAdapter<PastHistory> {

	public PastHistoryListAdapter(Context context, List<PastHistory> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_archive_past_history_disease_list_item, parent, false);
		}
		if (position % 2 == 0) {
			convertView.setBackgroundColor(cardinalBg);
		} else {
			convertView.setBackgroundColor(evenBg);
		}

		return convertView;
	}

}
