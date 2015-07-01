package com.shbestwin.followupmanager.manager.device;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.log.Log;

/**
 * 蓝牙打印机
 *
 * @version
 */
public class PrintManager {
	private static final String TAG = PrintManager.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private static final String DEVICE_NAME = "RPP-02";// 打印机的名称

	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;

	private String mTipsInfo;

	private boolean mPaired;

	public PrintManager(Activity activity) {
		this.mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	}

	public boolean isPaired() {
		return mPaired;
	}

	/**
	 * 打开连接
	 * 
	 * @return 连接状态
	 */
	public boolean connectDevice() {
		// 0、判断本机是否支持蓝牙设备
		if (mBluetoothAdapter == null) {
			showTips(R.string.device_local_unsupport_bluetooth);
			return false;
		}
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();// 获取所有已配对的设备
		// 1、没有配对的设备
		if (pairedDevices.size() <= 0) {
			showTips(String.format(mActivity.getResources().getString(R.string.device_idcard_no_bonded_devices), DEVICE_NAME));
			return false;
		}

		BluetoothDevice adaptedDevice = null;
		for (Iterator<BluetoothDevice> iterator = pairedDevices.iterator(); iterator.hasNext();) {
			BluetoothDevice device = (BluetoothDevice) iterator.next();
			log.i(TAG, "name=" + device.getName() + ",address=" + device.getAddress());
			if (DEVICE_NAME.equals(device.getName())) {
				adaptedDevice = device;
				break;
			}
		}

		// 2、没有合适的配对设备
		if (adaptedDevice == null) {
			showTips(String.format(mActivity.getResources().getString(R.string.device_idcard_no_bonded_devices), DEVICE_NAME));
			return false;
		}

		// 3、蓝牙设备没有打开时打开设备
		if (!mBluetoothAdapter.isEnabled()) {
			mBluetoothAdapter.enable();
		}

		// 连接蓝牙
		if (!BluetoothPrintDriver.OpenPrinter(adaptedDevice.getAddress())) {
			// 连接失败
			BluetoothPrintDriver.close();
			showTips(BluetoothPrintDriver.ErrorMessage);
			return false;
		}
		return true;
	}

	public void print(String data) {
		System.out.println("///"+data);
		BluetoothPrintDriver.Begin();// 开始打印
		BluetoothPrintDriver.SetAlignMode((byte) 0);// 左对齐
		BluetoothPrintDriver.SetFontEnlarge((byte) 0x00);// 默认宽度、默认高度
		BluetoothPrintDriver.ImportData(data);
		BluetoothPrintDriver.excute();
		BluetoothPrintDriver.ClearData();
	}

	private void showTips(final int resId) {
		mTipsInfo = mActivity.getResources().getString(resId);
		// mActivity.runOnUiThread(new Runnable() {
		// @Override
		// public void run() {
		// ToastUtils.showToast(mActivity, resId);
		// }
		// });
	}

	private void showTips(String tipsInfo) {
		mTipsInfo = tipsInfo;
		// mActivity.runOnUiThread(new Runnable() {
		// @Override
		// public void run() {
		// ToastUtils.showToast(mActivity, resId);
		// }
		// });
	}

	public String getTipsInfo() {
		return mTipsInfo;
	}

	public void closeDevice() {
		BluetoothPrintDriver.close();
	}

}
