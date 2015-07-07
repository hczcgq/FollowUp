package com.shbestwin.followupmanager.manager.device;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.log.Log;
import com.shbestwin.followupmanager.common.util.HexBinary;

/**
 * 
 * @ClassName: WaistlineManager
 * @Description: 腰围尺
 *
 */
public class CopyOfWaistlineManager {
	private static final String TAG = CopyOfWaistlineManager.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private String DEVICE_NAME = "MB_YWC_1441120016";
	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothServerSocket mBluetoothServerSocket = null;
	private BluetoothSocket mBluetoothSocket = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;

	public CopyOfWaistlineManager(Activity activity) {
		this.mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		SharedPreferences preferences=mActivity.getSharedPreferences("DEVICE_NAME", Context.MODE_PRIVATE);
		if(preferences.contains("Waistline")){
			String device=preferences.getString("Waistline", "");
			if(device!=""&&device!=null){
				DEVICE_NAME=device;
			}
		}
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

		// 2、发现设备
//		Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);  
//		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 150);
		
		// 4、创建蓝牙ServerSocket
		try {
			UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");  
			mBluetoothServerSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("sonka-bl",uuid);
			log.i(TAG, "服务监听已经启动。。。");
			
			//5、等待设备连接
			mBluetoothSocket = mBluetoothServerSocket.accept();
			log.i(TAG, "设备已接入。。。");
			//6、获取输入输出流
			mInputStream = mBluetoothSocket.getInputStream();
			mOutputStream = mBluetoothSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
			showTips(R.string.device_connect_exception);
			return false;
		}

		if ((mInputStream != null) && (mOutputStream != null)) {
			showTips(R.string.device_connect_success);
			return true;
		} else {
			showTips(R.string.device_connect_failure);
			return false;
		}
	}

	public int readData() {
		int data = -1;
		byte[] buffer = new byte[14];
		try {
			int length = mInputStream.read(buffer);
			if (length != -1) {
				log.i(TAG, "获取的数据：" + HexBinary.bytesToHexStringPrint(buffer, 0, length));
				//解析数据并返回
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.i(TAG, "获取数据异常：" + e.getMessage());
			showTips(R.string.device_read_data_exception);
		}
		return data;
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
		
		if (mBluetoothServerSocket != null) {
			try {
				mBluetoothServerSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}