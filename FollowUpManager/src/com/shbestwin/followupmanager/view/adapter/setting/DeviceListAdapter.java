package com.shbestwin.followupmanager.view.adapter.setting;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.setting.Device;
import com.shbestwin.followupmanager.model.setting.SettingDevices;
import com.shbestwin.followupmanager.view.adapter.ArrayListAdapter;

public class DeviceListAdapter extends ArrayListAdapter<SettingDevices> {

	public DeviceListAdapter(Context context, List<SettingDevices> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.view_setting_device_list_item, parent, false);
			holder = new ViewHolder();
			holder.orderTextView = (TextView) convertView.findViewById(R.id.orderTextView);
			holder.deviceNameTextView = (TextView) convertView.findViewById(R.id.deviceNameTextView);
			holder.deviceTypeTextView = (TextView) convertView.findViewById(R.id.deviceTypeTextView);
			holder.brandTextView = (TextView) convertView.findViewById(R.id.brandTextView);
			holder.modelTextView = (TextView) convertView.findViewById(R.id.modelTextView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Device device = getItem(position).getSelected();
		holder.orderTextView.setText((position + 1) + ".");
		holder.deviceNameTextView.setText(device.getName());
		holder.deviceTypeTextView.setText("仪器类型：" + device.getTypeName());
		holder.brandTextView.setText("品牌：" + device.getBrand());
		holder.modelTextView.setText("型号：" + device.getModel());
		return convertView;
	}

	private static class ViewHolder {
		private TextView orderTextView;
		private TextView deviceNameTextView;
		private TextView deviceTypeTextView;
		private TextView brandTextView;
		private TextView modelTextView;
	}
}
