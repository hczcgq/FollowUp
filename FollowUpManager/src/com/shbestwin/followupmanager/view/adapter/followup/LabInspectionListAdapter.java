package com.shbestwin.followupmanager.view.adapter.followup;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.interfaces.ListItemClickHelp;
import com.shbestwin.followupmanager.model.followup.LabInspection;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class LabInspectionListAdapter extends ArrayListAdapter<LabInspection> {
	private List<LabInspection> list;

	public LabInspectionListAdapter(Context context, List<LabInspection> list) {
		super(context, list);
		this.list=list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_followup_children_lab_inspection_list_item, parent, false);
		}
		if (position % 2 == 0) {
			convertView.setBackgroundColor(cardinalBg);
		} else {
			convertView.setBackgroundColor(evenBg);
		}
		 TextView tv_jcxm=(TextView) convertView.findViewById(R.id.tv_jcxm);
		 TextView tv_jcr=(TextView) convertView.findViewById(R.id.tv_jcr);
		 TextView tv_jcjg=(TextView) convertView.findViewById(R.id.tv_jcjg);
		 TextView tv_jcsj=(TextView) convertView.findViewById(R.id.tv_jcsj);
		 ImageView im_edit=(ImageView) convertView.findViewById(R.id.im_edit);
		 ImageView im_delete=(ImageView) convertView.findViewById(R.id.im_delete);
		
		 LabInspection inspection=list.get(position);
		 tv_jcxm.setText(inspection.getSysjc_sysjcxm());
		 tv_jcr.setText(inspection.getSysjc_jcjg());
		 tv_jcjg.setText(inspection.getSysjc_jcr());
		 tv_jcsj.setText(inspection.getSysjc_jcsj());
		 
		 im_edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clickHelp.onClick(position, R.id.im_edit);
			}
		});
		 
		 im_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					clickHelp.onClick(position, R.id.im_delete);
				}
			});
		 
		 
		return convertView;
	}
	
	private ListItemClickHelp clickHelp;
	public void setListItemClickHelp(ListItemClickHelp clickHelp){
		this.clickHelp=clickHelp;
	}
}
