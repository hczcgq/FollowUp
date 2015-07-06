package com.shbestwin.followupmanager.fragment.setting;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.manager.DeviceManager;
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.model.setting.Device;
import com.shbestwin.followupmanager.model.setting.SettingDevices;
import com.shbestwin.followupmanager.view.adapter.setting.DeviceListAdapter;
import com.shbestwin.followupmanager.view.adapter.setting.DeviceTypeListAdapter;
import com.shbestwin.followupmanager.view.adapter.setting.DeviceTypeListAdapter.DeviceListener;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.DeviceNameDialog;
import com.shbestwin.followupmanager.view.dialog.followup.MedicationDialog;

public class DeviceFragment extends BaseFragment implements DeviceListener {

	private ListView deviceListView, deviceTypeListView;
	private View deviceTypeLayout;

	private DeviceListAdapter deviceListAdapter;
	private DeviceTypeListAdapter deviceTypeListAdapter;

	private List<SettingDevices> settingDevicesList;
	private int checkedIndex = -1;

	public static DeviceFragment newInstance() {
		DeviceFragment fragment = new DeviceFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_setting_device,
				container, false);
		deviceListView = (ListView) rootView.findViewById(R.id.deviceListView);
		deviceTypeListView = (ListView) rootView
				.findViewById(R.id.deviceTypeListView);
		deviceTypeLayout = rootView.findViewById(R.id.deviceTypeLayout);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		settingDevicesList = DeviceManager.getInstance(getActivity())
				.getDeviceList();
		deviceListAdapter = new DeviceListAdapter(getActivity(),
				settingDevicesList);
		deviceListView.setAdapter(deviceListAdapter);

		deviceTypeListAdapter = new DeviceTypeListAdapter(getActivity());
		deviceTypeListAdapter.setDeviceListener(this);
		deviceTypeListView.setAdapter(deviceTypeListAdapter);

		deviceListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				deviceTypeLayout.setVisibility(View.VISIBLE);
				checkedIndex = position;
				List<Device> deviceTypeList = settingDevicesList.get(position)
						.getAll();
				deviceTypeListAdapter.setList(deviceTypeList);
			}
		});

		deviceTypeListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				saveChecked(position);
			}
		});
	}

	private void saveChecked(int position) {
		SettingDevices settingDevices = settingDevicesList.get(checkedIndex);
		List<Device> deviceAll = settingDevices.getAll();
		// 看当前是否已经是选中状态
		if (deviceAll.get(position).isUsing()) {
			return;
		}
		Device checkedDevice = settingDevices.getAll().get(position);
		checkedDevice.setUsing(true);
		settingDevices.setSelected(checkedDevice);
		for (int i = 0; i < deviceAll.size(); i++) {
			deviceAll.get(i).setUsing(position == i ? true : false);
		}
		DeviceManager.getInstance(getActivity()).saveOrUpdateDeviceList(
				settingDevicesList);
		deviceTypeListAdapter.setList(deviceAll);
	}

	@Override
	public void editBoothName(final Device device) {
		final DeviceNameDialog medicationDialog = DeviceNameDialog
				.newInstance();
		medicationDialog.show(
				((FragmentActivity) getActivity()).getSupportFragmentManager(),
				"medicationDialog");

		medicationDialog
				.setOnConfirmClickListener(new OnConfirmClickListener() {

					@Override
					public void onConfirmClick() {
						String name = medicationDialog.getName();
						medicationDialog.hide();
						saveBoothName(device,name);
					}
				});
	}
	
	private void saveBoothName(Device device,String name){
		
		SharedPreferences preferences=getActivity().getSharedPreferences("DEVICE_NAME", Context.MODE_PRIVATE);
		
		SharedPreferences.Editor editor=preferences.edit();
//		editor.putInt("", name);
	}

}
