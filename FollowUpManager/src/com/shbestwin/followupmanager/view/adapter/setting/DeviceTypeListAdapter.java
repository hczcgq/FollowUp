package com.shbestwin.followupmanager.view.adapter.setting;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
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
			holder.editImageView = (ImageView) convertView.findViewById(R.id.edit);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		final Device device = getItem(position);
		holder.brandTextView.setText(device.getBrand());
		holder.modelTextView.setText(device.getModel());
		if (device.isUsing()) {
			holder.checkImageView.setImageResource(R.drawable.icon_check);
		} else {
			holder.checkImageView.setImageResource(0);
		}
		
		holder.editImageView.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                listener.editBoothName(device);
            }
        });
		return convertView;
	}

	private static class ViewHolder {
		private TextView brandTextView;
		private TextView modelTextView;
		private ImageView checkImageView;
		private ImageView editImageView;
	}
	
	DeviceListener listener;
	public void setDeviceListener(DeviceListener listener){
		this.listener=listener;
	}
	
	public interface DeviceListener{
		public void editBoothName(Device device);
	}
}
