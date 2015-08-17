package com.shbestwin.followupmanager.manager.device;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.bluetooth.BluetoothConnector;
import com.shbestwin.followupmanager.bluetooth.BluetoothSocketWrapper;
import com.shbestwin.followupmanager.common.log.Log;
import com.shbestwin.followupmanager.common.util.HexBinary;
import com.shbestwin.followupmanager.model.device.BloodGlucose;

/**
 * 
 * @ClassName: BloodGlucoseManager1
 * @Description: 血糖仪（101A0000047）
 *
 */
public class BloodGlucoseManager1 {
	private static final String TAG = BloodGlucoseManager1.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private String DEVICE_NAME = "101A0000047";// 血糖仪设备名称

	// 获取meter id
	private static String CMD_METER_ID = "T1 62558";
	// 获取数据
	private static String CMD_DATA = "&N0 41854‍";

	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothConnector mBluetoothConnector = null;
	private BluetoothSocketWrapper mBluetoothSocketWrapper = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;


	public BloodGlucoseManager1(Activity activity) {
		this.mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		SharedPreferences preferences=mActivity.getSharedPreferences("DEVICE_NAME", Context.MODE_PRIVATE);
		if(preferences.contains("BloodGlucose")){
			String device=preferences.getString("BloodGlucose", "");
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
			 log.i(TAG,"Name="+device.getName()+",address="+device.getAddress());
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
		// 4、创建蓝牙Socket
		try {
			String uuid = "C14D2C0A-401F-B7A9-841F-E2E93B80F631";
			mBluetoothConnector = new BluetoothConnector(adaptedDevice, mBluetoothAdapter,uuid);
			log.i(TAG, "正在连接设备");
			// 5、连接读卡器设备
			mBluetoothSocketWrapper = mBluetoothConnector.connect();
			log.i(TAG, "连接设备成功");
			// 6、获取输入输出流
			mInputStream = mBluetoothSocketWrapper.getInputStream();
			mOutputStream = mBluetoothSocketWrapper.getOutputStream();
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

	public BloodGlucose readData() {
		BloodGlucose bloodGlucose = null;
		// 发送数据
		try {
			log.i(TAG, "正在发送meta ID数据");
			mOutputStream.write(CMD_METER_ID.getBytes());
			mOutputStream.flush();

			Thread.sleep(1000L);
			byte[] buffer = new byte[128];
			int length = mInputStream.read(buffer);
			log.i(TAG, "返回数据，长度=" + length);

			if (length < 0) {
				log.i(TAG, "设备已断开");
				showTips(R.string.device_connect_exception);
				return bloodGlucose;
			}
			log.i(TAG, "METER_ID返回数据：" + HexBinary.bytesToHexStringPrint(buffer, 0, length));
			
			log.i(TAG, "正在发送data数据");
			mOutputStream.write(CMD_DATA.getBytes());
			mOutputStream.flush();
			
			Thread.sleep(1000L);
			length = mInputStream.read(buffer);
			log.i(TAG, "返回数据，长度=" + length);
			if (length < 0) {
				log.i(TAG, "设备已断开");
				showTips(R.string.device_connect_exception);
				return bloodGlucose;
			}
			log.i(TAG, "DATA返回数据：" + HexBinary.bytesToHexStringPrint(buffer, 0, length));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 获取数据
		return bloodGlucose;
	}

	private void showTips(final int resId) {
		mTipsInfo = mActivity.getResources().getString(resId);
	}

	private void showTips(String tipsInfo) {
		mTipsInfo = tipsInfo;
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

		if (mBluetoothSocketWrapper != null) {
			try {
				mBluetoothSocketWrapper.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
