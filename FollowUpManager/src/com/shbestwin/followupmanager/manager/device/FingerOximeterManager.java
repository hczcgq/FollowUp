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
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.log.Log;
import com.shbestwin.followupmanager.common.util.CRC8;
import com.shbestwin.followupmanager.common.util.HexBinary;
import com.shbestwin.followupmanager.model.device.BloodOximeter;

/**
 * 
 * @ClassName: FingerOximeterManager
 * @Description: 手指血氧仪（PC-60NW-1）
 *
 */
public class FingerOximeterManager {
	private static final String TAG = FingerOximeterManager.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private static final String DEVICE_NAME = "PC-60NW-1";

	// 查询版本
	private static byte[] CMD_QUERY_VERSION = new byte[6];
	// 使能参数发送
	private static byte[] CMD_SLAVE_ENABLE = new byte[7];
	// 定时下行心跳包
	private static byte[] CMD_HEART_BEAT = new byte[7];

	static {
		CMD_QUERY_VERSION[0] = Integer.valueOf("AA", 16).byteValue();// 包头:AA
		CMD_QUERY_VERSION[1] = Integer.valueOf("55", 16).byteValue();// 包头:55
		CMD_QUERY_VERSION[2] = Integer.valueOf("0F", 16).byteValue();// 令牌:0F
		CMD_QUERY_VERSION[3] = Integer.valueOf("02", 16).byteValue();// 长度=内容长度+校验和长度
		CMD_QUERY_VERSION[4] = Integer.valueOf("83", 16).byteValue();// 内容：Data0(0x83)
		CMD_QUERY_VERSION[5] = CRC8.calcCrc8(CMD_QUERY_VERSION, 0, CMD_QUERY_VERSION.length - 1);

		CMD_SLAVE_ENABLE[0] = Integer.valueOf("AA", 16).byteValue();// 包头:AA
		CMD_SLAVE_ENABLE[1] = Integer.valueOf("55", 16).byteValue();// 包头:55
		CMD_SLAVE_ENABLE[2] = Integer.valueOf("0F", 16).byteValue();// 令牌:0F
		CMD_SLAVE_ENABLE[3] = Integer.valueOf("03", 16).byteValue();// 长度=内容长度+校验和长度
		CMD_SLAVE_ENABLE[4] = Integer.valueOf("84", 16).byteValue();// 内容：Data0(0x84)
		CMD_SLAVE_ENABLE[5] = Integer.valueOf("01", 16).byteValue();// 内容：Data1(0x01)
		CMD_SLAVE_ENABLE[6] = CRC8.calcCrc8(CMD_SLAVE_ENABLE, 0, CMD_SLAVE_ENABLE.length - 1);

		CMD_HEART_BEAT[0] = Integer.valueOf("AA", 16).byteValue();// 包头:AA
		CMD_HEART_BEAT[1] = Integer.valueOf("55", 16).byteValue();// 包头:55
		CMD_HEART_BEAT[2] = Integer.valueOf("0F", 16).byteValue();// 令牌:0F
		CMD_HEART_BEAT[3] = Integer.valueOf("03", 16).byteValue();// 长度=内容长度+校验和长度
		CMD_HEART_BEAT[4] = Integer.valueOf("80", 16).byteValue();// 内容：Data0(0x80)
		CMD_HEART_BEAT[5] = Integer.valueOf("05", 16).byteValue();// 内容：Data1(心跳包的时间间隔(2s~60s))
		CMD_HEART_BEAT[6] = CRC8.calcCrc8(CMD_HEART_BEAT, 0, CMD_HEART_BEAT.length - 1);
	}

	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothSocket mBluetoothSocket = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;

	public FingerOximeterManager(Activity activity) {
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
			UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
			if (Build.VERSION.SDK_INT >= 10) {// 根据不同的版本，使用不同的创建方式
				mBluetoothSocket = adaptedDevice.createInsecureRfcommSocketToServiceRecord(uuid);
			} else {
				mBluetoothSocket = adaptedDevice.createRfcommSocketToServiceRecord(uuid);
			}

			// 5、连接读卡器设备
			mBluetoothSocket.connect();

			// 6、获取输入输出流
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

	// private Timer timer = new Timer();
	//
	// private class HeartBeatTask extends TimerTask {
	//
	// @Override
	// public void run() {
	// if (mOutputStream != null) {
	// try {
	// mOutputStream.write(CMD_HEART_BEAT);
	// mOutputStream.flush();
	// } catch (IOException e) {
	// e.printStackTrace();
	// log.e(TAG, "心跳发送出现异常");
	// }
	// }
	// }
	// }

	public BloodOximeter readData() {
		BloodOximeter bloodOximeter = null;
		// 1、发起查询版本请求
		try {
			mOutputStream.write(CMD_QUERY_VERSION);
			mOutputStream.flush();
			log.i(TAG, "成功发送查询指令。。。");
			Thread.sleep(500L);

			byte[] buffer = new byte[256];
			int length = mInputStream.read(buffer);
			log.i(TAG, "返回数据，长度=" + length);

			if (length < 0) {
				log.i(TAG, "设备已断开");
				showTips(R.string.device_connect_exception);
				return bloodOximeter;
			}
			log.i(TAG, "查询返回数据：" + HexBinary.bytesToHexStringPrint(buffer, 0, length));

			// 2、读取数据
			mOutputStream.write(CMD_SLAVE_ENABLE);
			mOutputStream.flush();
			log.i(TAG, "成功发送使能参数。。。");
			Thread.sleep(500L);

			buffer = new byte[256];
			length = mInputStream.read(buffer);
			log.i(TAG, "返回数据，长度=" + length);

			if (length < 0) {
				log.i(TAG, "设备已断开");
				showTips(R.string.device_connect_exception);
				return bloodOximeter;
			}
			log.i(TAG, "使能参数返回数据：" + HexBinary.bytesToHexStringPrint(buffer, 0, length));
			// timer.schedule(new HeartBeatTask(), 5 * 1000);

			buffer = new byte[256];
			Thread.sleep(500);
			length = mInputStream.read(buffer);
			log.i(TAG, "返回数据，长度=" + length);

			if (length < 0) {
				log.i(TAG, "设备已断开");
				showTips(R.string.device_connect_exception);
				return bloodOximeter;
			}
			log.i(TAG, "数据返回数据：" + HexBinary.bytesToHexStringPrint(buffer, 0, length));
			// 数据返回数据：
			// AA 0
			// 55 1
			// 0F 2
			// 08 3(数据长度)
			// 01 4:数据类型固定为0x01,表示该包为参数数据包
			// 61 5:血氧数据。范围为 0%~100%;0 代表无效值。
			// 56 00 (6-7)://脉率数据。低字节在前,高字节在后;范围 0~511bpm。0 代表无效值。
			// 18 8://PI(血流灌注指数)数据。范围 0%~25.5%;0 代表无效值。
			// 00 9://00 成人模式
			// 1C 10
			// F0 11
			if (buffer.length >= 12 && buffer[4] == 0X01) {
				String result = HexBinary.bytesToHexString(buffer);
				// 获取血氧值
				int bloodOxygen = Integer.parseInt(result.substring(0 + 10, 0 + 12), 16);
				// 获取脉率的值
				int pulseRate = Integer.parseInt(result.substring(0 + 12, 0 + 14), 16);
				log.i(TAG, "bloodOxygen=" + bloodOxygen + ",pulseRate=" + pulseRate);
				bloodOximeter = new BloodOximeter(bloodOxygen, pulseRate);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bloodOximeter;
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
		// if (timer != null) {
		// timer.cancel();
		// }
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
