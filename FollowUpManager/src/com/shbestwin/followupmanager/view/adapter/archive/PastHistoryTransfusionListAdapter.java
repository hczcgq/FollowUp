package com.shbestwin.followupmanager.view.adapter.archive;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.interfaces.PastHistoryListItemClickHelp;
import com.shbestwin.followupmanager.model.archive.PastHistoryTransfusion;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class PastHistoryTransfusionListAdapter extends ArrayListAdapter<PastHistoryTransfusion> {
	private List<PastHistoryTransfusion> mList;
	private PastHistoryListItemClickHelp callback;
	public PastHistoryTransfusionListAdapter(Context context, List<PastHistoryTransfusion> list,PastHistoryListItemClickHelp callback) {
		super(context, list);
		mList = list;
		this.callback = callback;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		Holder holder = new Holder();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_archive_past_history_disease_list_item, parent, false);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.date  = (TextView) convertView.findViewById(R.id.date);
			holder.update = (ImageView) convertView.findViewById(R.id.update);
			holder.delete = (ImageView) convertView.findViewById(R.id.delete);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		holder.name.setText(mList.get(position).getName());
		holder.date.setText(mList.get(position).getDate());
		
		final View view = convertView;
        final int p = position;
        final int one = holder.update.getId();
        final int two = holder.delete.getId();
        
		holder.update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callback.onClickTransfusion(view, parent, p, one);
			}
		});
		holder.delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				callback.onClickTransfusion(view, parent, p, two);
			}
		});
		return convertView;
	}

	public class Holder{
		public TextView name;
		public TextView date;
		public ImageView update;
		public ImageView delete;
	}
	
}
