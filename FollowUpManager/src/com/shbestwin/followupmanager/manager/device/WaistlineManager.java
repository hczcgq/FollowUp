package com.shbestwin.followupmanager.manager.device;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.bluetooth.BluetoothConnector;
import com.shbestwin.followupmanager.bluetooth.BluetoothSocketWrapper;
import com.shbestwin.followupmanager.common.log.Log;
import com.shbestwin.followupmanager.common.util.HexBinary;

/**
 * 
 * @ClassName: WaistlineManager
 * @Description: 腰围尺
 *
 */
public class WaistlineManager {
	private static final String TAG = WaistlineManager.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private static final String DEVICE_NAME = "MB_YWC_1441120022";
	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothConnector mBluetoothConnector = null;
	private BluetoothSocketWrapper mBluetoothSocketWrapper = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;

	public WaistlineManager(Activity activity) {
		this.mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
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

		// 4、创建蓝牙Socket
		try {
			mBluetoothConnector = new BluetoothConnector(adaptedDevice, mBluetoothAdapter);
			log.i(TAG, "正在连接设备。。。");
			// 5、连接设备
			mBluetoothSocketWrapper = mBluetoothConnector.connect();
			log.i(TAG, "设备连接成功。。。");
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

	public int readData() {
		int result = 0;
		byte[] buffer = new byte[14];
		try {
			Thread.sleep(200);
			log.i(TAG, "发送数据请求指令。。。");
			mOutputStream.write("YWC GET".getBytes());
			mOutputStream.flush();

			log.i(TAG, "数据请求指令已发送。。。");
			Thread.sleep(1000);
			int length = mInputStream.read(buffer);
			if (length != -1) {
				String data = HexBinary.bytesToHexStringPrint(buffer, 0, length);
				log.i(TAG, "获取的数据：" + data);
				// 解析数据并返回
				//02-10 20:16:17.370: I/WaistlineManager(6760): 获取的数据：53 45 54 20 43 4F 4E 54 52 4F 4C 20 45 43 
				//02-10 20:19:48.590: I/WaistlineManager(7018): 获取的数据：53 45 54 20 50 52 4F 46 49 4C 45 20 53 50 
				//02-10 20:20:02.175: I/WaistlineManager(7018): 获取的数据：55 AA 00 09 02 08 4D 42 59 57 43 20 00 B5 
				//腰围尺数据：0x55，0xAA，米，分米，厘米，毫米（保留），“M”，“B”，“Y”，“W”，“C”，版本，标志字节（保留），校验和，版本：BCD码，0x10表示1.0版本
				if (buffer[0] == (byte)0x55 && buffer[1] == (byte)0xAA) {//表示合法的数据
					//00(2) 09(3) 02(4)
					result = (buffer[2] & 0xff) * 100 + (buffer[3] & 0xff) * 10 + (buffer[4] & 0xff);
				} else {
					showTips(R.string.device_read_data_exception);
				}
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.i(TAG, "获取数据异常：" + e.getMessage());
			showTips(R.string.device_read_data_exception);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
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

		if (mBluetoothSocketWrapper != null) {
			try {
				mBluetoothSocketWrapper.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}