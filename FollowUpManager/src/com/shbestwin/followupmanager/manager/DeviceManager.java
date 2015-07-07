package com.shbestwin.followupmanager.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.constant.PreferencesConstant;
import com.shbestwin.followupmanager.model.setting.Device;
import com.shbestwin.followupmanager.model.setting.SettingDevices;

public class DeviceManager {
	private static DeviceManager instance = null;

	private Context mContext = null;
	private PreferencesManager mPreferencesManager;

	private DeviceManager(Context applicContext) {
		mContext = applicContext;
		mPreferencesManager = new PreferencesManager(mContext);
	}

	public static synchronized DeviceManager getInstance(Context context) {
		if (instance == null) {
			synchronized (DeviceManager.class) {
				if (instance == null) {
					instance = new DeviceManager(context.getApplicationContext());
				}
			}
		}
		return instance;
	}

	public List<SettingDevices> getDeviceList() {
		String deviceList = mPreferencesManager.getString(PreferencesConstant.KEY_DEVICE_LIST);
		if (TextUtils.isEmpty(deviceList)) {
			return getDefaultDeviceList();
		}
		
		Type listType = new TypeToken<List<SettingDevices>>() {
		}.getType();
		List<SettingDevices> settingDevices = new Gson().fromJson(deviceList, listType);
		return settingDevices;
	}

	public void saveOrUpdateDeviceList(List<SettingDevices> devices) {
		String deviceList = new Gson().toJson(devices);
		mPreferencesManager.putString(PreferencesConstant.KEY_DEVICE_LIST, deviceList);
	}

	private List<SettingDevices> getDefaultDeviceList() {
		InputStream in = null;
		try {
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().enableComplexMapKeySerialization().create();
			in = mContext.getResources().openRawResource(R.raw.default_device_list);
			Reader reader = new InputStreamReader(in);
			List<SettingDevices> settingDevices = gson.fromJson(reader, new TypeToken<List<SettingDevices>>() {
			}.getType());
			return settingDevices;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public Device getDeviceByTypeId(String typeId) {
		List<SettingDevices> settingDevicesList = getDeviceList();
		for (SettingDevices settingDevices : settingDevicesList) {
			if (settingDevices.getSelected().getTypeId().equals(typeId)) {
				return settingDevices.getSelected();
			}
		}
		return null;
	}

}
