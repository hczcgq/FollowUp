package com.shbestwin.followupmanager.view.adapter;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.UploadManageItem;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class UploadManageAdapter extends ArrayListAdapter<UploadManageItem> {

	public UploadManageAdapter(Context context, List<UploadManageItem> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_upload_manager_list_item, parent, false);
			holder = new ViewHolder();
			holder.number = (TextView) convertView.findViewById(R.id.number);
			holder.type = (TextView) convertView.findViewById(R.id.type);
			holder.state = (TextView) convertView.findViewById(R.id.state);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		UploadManageItem item = getItem(position);
		holder.number.setText((position + 1)+"");
		holder.type.setText(item.getName());
		holder.state.setText(item.getState());
		holder.time.setText(item.getCreate_time());
		return convertView;
	}

	private static class ViewHolder {
		private TextView number;
		private TextView type;
		private TextView state;
		private TextView time;
	}
}
