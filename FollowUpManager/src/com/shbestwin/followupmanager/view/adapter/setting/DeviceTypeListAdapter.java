package com.shbestwin.followupmanager.view.adapter.setting;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.setting.Device;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class DeviceTypeListAdapter extends ArrayListAdapter<Device>{
	
	public DeviceTypeListAdapter(Context context) {
		super(context);
	}
	
	public DeviceTypeListAdapter(Context context, List<Device> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_setting_device_type_list_item, parent, false);
			holder = new ViewHolder();
			holder.brandTextView = (TextView) convertView.findViewById(R.id.brandTextView);
			holder.modelTextView = (TextView) convertView.findViewById(R.id.modelTextView);
			holder.checkImageView = (ImageView) convertView.findViewById(R.id.checkImageView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Device device = getItem(position);
		holder.brandTextView.setText(device.getBrand());
		holder.modelTextView.setText(device.getModel());
		if (device.isUsing()) {
			holder.checkImageView.setImageResource(R.drawable.icon_check);
		} else {
			holder.checkImageView.setImageResource(0);
		}
		return convertView;
	}

	private static class ViewHolder {
		private TextView brandTextView;
		private TextView modelTextView;
		private ImageView checkImageView;
	}
}
