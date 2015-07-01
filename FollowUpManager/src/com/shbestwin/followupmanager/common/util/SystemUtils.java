package com.shbestwin.followupmanager.common.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SystemUtils {

	public static String getVersionName(Context context) {
		String versionName = "";
		try {
			versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}

	public static int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 
	 * @Title: hiddenIME
	 * @Description:隐藏软键盘
	 * @param @param view
	 * @return void
	 * @throws
	 */
	public static void hiddenIME(View view) {
		if (view != null) {
			InputMethodManager inputManger = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	/**
	 * 
	 * @Title: showIME
	 * @Description: 显示软键盘
	 * @param @param focusView
	 * @param @param context
	 * @return void
	 * @throws
	 */
	public static void showIME(View focusView, Context context) {
		if (focusView != null) {
			if (!focusView.isFocused()) {
				focusView.requestFocus();
			}
			// 显示软键盘
			InputMethodManager inputMgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
			inputMgr.showSoftInput(focusView, InputMethodManager.SHOW_IMPLICIT);
		}
	}

	/**
	 * 获取设备的IMSI
	 */
	public static String getIMSI(Context context) {
		TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telManager.getSubscriberId();
	}

	/**
	 * 获取设备的IMEI
	 */
	public static String getIMEI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	/**
	 * 获取设备的MacAddress
	 */
	public static String getMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info == null ? "" : info.getMacAddress();
	}

	/**
	 * 获取设备的DeviceId
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm == null ? "" : tm.getDeviceId();
	}

	/**
	 * 获取设备的AndroidId
	 */
	public static String getAndroidId(Context context) {
		String androidId = android.provider.Settings.System.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
		return androidId;
	}
}
