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
import com.shbestwin.followupmanager.model.device.BloodPressure;

/**
 * 
 * @ClassName: BloodPressureManager
 * @Description: 血压计（EBELTER倍泰，型号：ePW-19B）
 *
 */
// 数据传送格式前提：
// 血压计关机状态-》按记忆M键调出记忆-》M键长按3s蓝牙设备打开处于被找状态-》和上位机蓝牙修通OK-》和上位机蓝牙接通后，不发任何数据，只等待上位机命令
// BB 05 22 01 14 0C 01 01 08 0A 00 80 45 4B 09 BB 05 22 02 14 0C 01 01 08 1F 00
// 6F 47 45 FC BB 05 22 03 14 0F 01 02 15 0C 00 76 44 4F E3 BB 05 22 04 14 0F 01
// 02 15 04 00 78 42 3E 95 BB 05 22 05 14 0C 01 08 05 2C 00 80 60 6D 2C BB 05 22
// 06 14 0C 01 08 05 2B 00 8A 5E 6A 1B BB 05 22 07 14 0C 01 08 05 2A 00 79 5F 69
// EA BB 05 22 08 14 0C 01 01 08 1D 00 86 60 4A 35 BB 05 22 09 14 0C 01 01 08 05
// 00 78 58 72 D2 BB 05 22 0A 14 0C 01 01 08 04 00 79 59 66 C4 BB 05 22 0B 14 0C
// 01 01 08 03 00 75 4F 6A D4 BB 05 22 0C 14 0C 01 01 08 02 00 73 4E 59 E6 BB 05
// 22 0D 14 0C 01 01 08 01 00 74 4D 5D E4 BB 05 22 0E 14 0C 01 01 08 01 00 C7 95
// 50 81
// BB 05 22 0F 14 0C 01 01 08 00 00 78 4E 50 E5
public class BloodPressureManager {
	private static final String TAG = BloodPressureManager.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private static final String DEVICE_NAME = "BP";// 血压计的名称。型号：ePW-19B

	private static byte[] CMD_REQUEST_DATA = new byte[5];
	
	private static byte[] CMD_SEND_SUCCESS_DATA = new byte[5];

	static {
		CMD_REQUEST_DATA[0] = (byte) 0xaa;
		CMD_REQUEST_DATA[1] = 0x05;
		CMD_REQUEST_DATA[2] = 0x11;
		CMD_REQUEST_DATA[3] = 0x05;
		CMD_REQUEST_DATA[4] = (byte) (CMD_REQUEST_DATA[0] ^ CMD_REQUEST_DATA[1] ^ CMD_REQUEST_DATA[2] ^ CMD_REQUEST_DATA[3]);
		
		CMD_SEND_SUCCESS_DATA[0] = (byte) 0xaa;
		CMD_SEND_SUCCESS_DATA[1] = 0x05;
		CMD_SEND_SUCCESS_DATA[2] = 0x55;
		CMD_SEND_SUCCESS_DATA[3] = 0x05;
		CMD_SEND_SUCCESS_DATA[4] = (byte) (CMD_SEND_SUCCESS_DATA[0] ^ CMD_SEND_SUCCESS_DATA[1] ^ CMD_SEND_SUCCESS_DATA[2] ^ CMD_SEND_SUCCESS_DATA[3]);
	}

	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothConnector mBluetoothConnector = null;
	private BluetoothSocketWrapper mBluetoothSocketWrapper = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;

	public BloodPressureManager(Activity activity) {
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

		// 4、创建蓝牙Socket
		try {
			mBluetoothConnector = new BluetoothConnector(adaptedDevice, mBluetoothAdapter);
			// 5、连接读卡器设备
			mBluetoothSocketWrapper = mBluetoothConnector.connect();
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

	public BloodPressure readData() {
		BloodPressure bloodPressure = null;
		try {
			mOutputStream.write(CMD_REQUEST_DATA);
			mOutputStream.flush();

			Thread.sleep(1000L);
			byte[] buffer = new byte[512];
			int length = mInputStream.read(buffer);
			log.i(TAG, "返回数据，长度=" + length);

			if (length < 0) {
				log.i(TAG, "设备已断开");
				showTips(R.string.device_connect_exception);
				return bloodPressure;
			}
			log.i(TAG, "返回数据：" + HexBinary.bytesToHexStringPrint(buffer, 0, length));
			// if (length >= 15) {
			//
			// }
			bloodPressure = parseData(HexBinary.bytesToHexString(buffer, 0, length));
			
//			mOutputStream.write(CMD_SEND_SUCCESS_DATA);
//			mOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bloodPressure;
	}

	// BB(1)
	// 05(2)
	// 22(3)
	// 01(4)
	// 14(5) :年高字节
	// 0C(6) :年低字节
	// 01(7) :月（字节最高位表示单位0：mmHg 1：kpa）
	// 01(8) :日
	// 08(9) :时
	// 0A(10) :分
	// 00(11) :高压高字节（字节最高位 1：心律不齐 0：正常）
	// 80(12) :高压低字节
	// 45(13) :低压
	// 4B(14) :脉博
	// 09(15) :BCC
	private BloodPressure parseData(String data) {
		if (!data.substring(0, 2).equalsIgnoreCase("BB")) {
			return null;
		}
		BloodPressure bloodPressure = new BloodPressure();
		int index = 1;
		for (int i = 0; i < data.length(); i += 30) {
			// 获取收缩压的值
			String shousuo = data.substring(i + 22, i + 24);
			int gao = Integer.parseInt(shousuo, 16);

			// 获取舒张压的值
			String shuzhang = data.substring(i + 24, i + 26);
			int di = Integer.parseInt(shuzhang, 16);

			// 获取脉搏的值
			String maibo = data.substring(i + 26, i + 28);
			int mai = Integer.parseInt(maibo, 16);

			String date = format(Integer.parseInt(data.substring(i + 8, i + 10), 16)) + "" + format(Integer.parseInt(data.substring(i + 10, i + 12), 16)) + "年" + format(Integer.parseInt(data.substring(i + 12, i + 14), 16)) + "月"
					+ format(Integer.parseInt(data.substring(i + 14, i + 16), 16)) + "日 " + format(Integer.parseInt(data.substring(i + 16, i + 18), 16)) + ":" + format(Integer.parseInt(data.substring(i + 18, i + 20), 16));
			log.i(TAG, "index=" + index + ",gao=" + gao + ",di=" + di + ",mai=" + mai + ",date=" + date);
			index++;
			
			bloodPressure.setSystolicPressure(gao);//收缩压
			bloodPressure.setDiastolicPressure(di);//舒张压
			bloodPressure.setPulseRate(mai);//脉搏
			break;
		}
		return bloodPressure;
	}

	private static String format(int number) {
		if (number < 10) {
			return "0" + number;
		}
		return "" + number;
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
