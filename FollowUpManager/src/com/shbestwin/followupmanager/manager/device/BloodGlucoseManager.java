package com.shbestwin.followupmanager.manager.device;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;
import cn.novacomm.ble.iGate;
import cn.novacomm.ble.iGateCallBacks;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.log.Log;
import com.shbestwin.followupmanager.model.device.BloodGlucose;

/**
 * 
 * @ClassName: BloodGlucoseManager
 * @Description: 血糖仪（101A0000047）
 *
 */
public class BloodGlucoseManager implements iGateCallBacks {
	private static final String TAG = BloodGlucoseManager.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private String DEVICE_NAME = "101A0000047";// 血糖仪设备名称
	private String deviceAddress;

	// 获取meter id
	private static String CMD_METER_ID = "&TB 50299";
	// 获取数据
	private static String CMD_DATA = "&N0 41854‍";

	private Activity mActivity;

	private iGate mIgate = null;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothSocket mBluetoothSocket = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;

	private int sendType;// 1:获取meter ID 2:获取数据

//	private static Object LOCK = new Object();

	private BloodGlucose bloodGlucose;

	public BloodGlucoseManager(Activity activity) {
		this.mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		mIgate = new iGate(activity, UUID.fromString("C14D2C0A-401F-B7A9-841F-E2E93B80F631"), this);
		mIgate.initialize(false);// 不自动连接
		SharedPreferences preferences=mActivity.getSharedPreferences("DEVICE_NAME", Context.MODE_PRIVATE);
		if(preferences.contains("BloodGlucose")){
			String device=preferences.getString("BloodGlucose", "");
			if(device!=""&&device!=null){
				DEVICE_NAME=device;
			}
		}
	}

	@Override
	public void iGateDeviceConnected(String bluetoothAddress) {
		log.i(TAG, String.format("iGate connected %s", bluetoothAddress));
	}

	@Override
	public void iGateDeviceDisConnected(String bluetoothAddress) {
		log.i(TAG, String.format("disconnected %s", bluetoothAddress));
	}

	@Override
	public void iGateDeviceFound(final String bluetoothAddress, final int rssi, final byte[] record) {
		log.i(TAG, String.format("iGate found device %s", bluetoothAddress));
	}

	@Override
	public void iGateDeviceLinkLossAlertLevelReport(final String bluetoothAddress, byte alertLevel) {
		log.i(TAG, String.format("Link loss alert level %s,%d", bluetoothAddress, alertLevel));
	}

	@Override
	public void iGateDeviceReceivedData(final String bluetoothAddress, final byte[] data) {
		try {
			final String tmpValue = new String(data, "UTF-8");
			if (sendType == 1) {
				log.i(TAG, "meter id data=" + tmpValue);
				sendType = 2;
				mIgate.iGateDeviceSendData(deviceAddress, CMD_DATA.getBytes());
			} else {
//				synchronized (LOCK) {
					log.i(TAG, "data=" + tmpValue);
					// 读取数据
					bloodGlucose = new BloodGlucose();
//					LOCK.notify();
//				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void iGateDeviceServiceBonded(String bluetoothAddress) {
		log.i(TAG, String.format("iGate bonded %s", bluetoothAddress));
	}

	@Override
	public void iGateDeviceTxPowerReport(final String bluetoothAddress, byte txPower) {
		log.i(TAG, String.format("Tx Power %s,%d", bluetoothAddress, txPower));
	}

	@Override
	public void iGateDeviceUpdateRssi(final String bluetoothAddress, final int rssi) {
		log.i(TAG, String.format("Rssi report %s,%d", mIgate.iGateDeviceGetName(bluetoothAddress), rssi));
	}

	@Override
	public void iGateHostDidUpdateState(iGateHostState arg0) {

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
			showTips(R.string.device_idcard_no_bonded_devices);
			return false;
		}

		BluetoothDevice adaptedDevice = null;
		for (Iterator<BluetoothDevice> iterator = pairedDevices.iterator(); iterator.hasNext();) {
			BluetoothDevice device = (BluetoothDevice) iterator.next();
			// log.i(TAG,
			// "Name="+device.getName()+",address="+device.getAddress());
			if (DEVICE_NAME.equals(device.getName())) {
				adaptedDevice = device;
				break;
			}
		}

		// 2、没有合适的配对设备
		if (adaptedDevice == null) {
			showTips(R.string.device_idcard_no_bonded_devices);
			return false;
		}

		// 3、蓝牙设备没有打开时打开设备
		if (!mBluetoothAdapter.isEnabled()) {
			mBluetoothAdapter.enable();
		}
		try {
			deviceAddress = adaptedDevice.getAddress();
			log.i(TAG, "正在连接设备。。。");
			mIgate.iGateDeviceConnect(deviceAddress);// 连接设备
			log.i(TAG, "设备连接成功。。。");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			showTips(R.string.device_connect_exception);
			return false;
		}
	}

	public BloodGlucose readData() {
//		synchronized (LOCK) {
			sendType = 1;
			bloodGlucose = null;
			// 发送数据
			log.i(TAG, "正在发送获取meter id请求。。。");
			mIgate.iGateDeviceSendData(deviceAddress, CMD_METER_ID.getBytes());
			log.i(TAG, "获取meter id请求已发送。。。");
//			if (bloodGlucose == null) {
//				try {
//					LOCK.wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return bloodGlucose;
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

	public String getTipsInfo() {
		return mTipsInfo;
	}

	/**
	 * 关闭设备
	 */
	public void closeDevice() {
		if (mInputStream != null) {
			try {
				mInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (mOutputStream != null) {
			try {
				mOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (mBluetoothSocket != null) {
			try {
				mBluetoothSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
