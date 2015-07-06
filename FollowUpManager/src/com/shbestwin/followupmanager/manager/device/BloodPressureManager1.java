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
import android.os.Build;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.bluetooth.BluetoothConnector;
import com.shbestwin.followupmanager.bluetooth.BluetoothSocketWrapper;
import com.shbestwin.followupmanager.common.log.Log;
import com.shbestwin.followupmanager.common.util.HexBinary;
import com.shbestwin.followupmanager.manager.PreferencesManager;
import com.shbestwin.followupmanager.model.device.BloodPressure;

/**
 * 
 * @ClassName: BloodPressure1Manager
 * @Description: 血压计（欧姆龙血压计，型号：HEM-7081-IT）
 *
 */
public class BloodPressureManager1 {
	private static final String TAG = BloodPressureManager1.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private static final String DEVICE_NAME = "HEM-7081-IT";// 血压计的名称

	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothServerSocket mBluetoothServerSocket;
	private BluetoothSocket mBluetoothSocket;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;

	private PreferencesManager mPreferencesManager;
	private static final String KEY_PAIRED = "bloodpressure_paired";

	private boolean mPaired;

	public BloodPressureManager1(Activity activity) {
		this.mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		mPreferencesManager = new PreferencesManager(activity);
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

		mPaired = mPreferencesManager.getBoolean(KEY_PAIRED, false);
		if (!mPaired) {
			showTips("当前设备没有跟血压计做授权配对！");
			return false;
		}

		// 等待接收数据
		UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
		try {
			if (Build.VERSION.SDK_INT >= 10) {
				mBluetoothServerSocket = mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(mBluetoothAdapter.getName(), uuid);
			} else {
				mBluetoothServerSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(mBluetoothAdapter.getName(), uuid);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.e(TAG, e.getMessage());
			showTips(R.string.device_connect_failure);
			mPaired = false;
			mPreferencesManager.putBoolean(KEY_PAIRED, false);
			return false;
		}
		return true;
	}

	public BloodPressure readData() {
		BloodPressure bloodPressure = null;
		// 此处阻塞,等待接收来自客户端的连接,也就是血压仪的连接。
		try {
			log.i(TAG, "等待血压计的连接。。。");
			mBluetoothSocket = mBluetoothServerSocket.accept(5000);
			log.i(TAG, "接收到血压计的连接。。。");

			mInputStream = mBluetoothSocket.getInputStream();
			mOutputStream = mBluetoothSocket.getOutputStream();

			Thread.sleep(500);
			// 此处接收"READY",如果血压仪连接过来后，会发送"READY"过来
			byte[] buffer = new byte[5];
			int length = mInputStream.read(buffer);

			log.i(TAG, "读取到血压计连接成功后的数据为：" + HexBinary.bytesToHexString(buffer, 0, length));
			if (length != 5 || buffer[0] != 'R' || buffer[1] != 'E' || buffer[2] != 'A' || buffer[3] != 'D' || buffer[4] != 'Y') {
				log.i(TAG, "血压计连接成功后校验失败！");
			}

			log.i(TAG, "血压计连接成功后校验成功！");

			log.i(TAG, "发送数据请求。。。");
			byte[] sendCmd = new byte[] { 0x47, 0x4D, 0x44, 0x00, 0x00/*
																	 * DATA
																	 * INDEX 1
																	 */, 0x00/*
																			 * DATA
																			 * INDEX
																			 * 2
																			 */, 0x00 /* BCC */};
			// byte[] sendCmd = new byte[] { 0x47, 0x4d, 0x44, 0, (byte) (i
			// >>
			// 8), (byte) i, (byte) ((i >> 8) ^ i) };
			mOutputStream.write(sendCmd);
			mOutputStream.flush();
			log.i(TAG, "数据请求已发送。。。");
			log.i(TAG, "等待数据请求的返回。。。");
			// 发送GMD指令后，要等待500ms，因为血压仪处理要500ms再返回
			Thread.sleep(500);
			// 接收返回的的数据
			buffer = new byte[256];

			length = mInputStream.read(buffer);
			// 数据根据文档解析
			log.i(TAG, "数据请求的返回数据为：" + HexBinary.bytesToHexString(buffer, 0, length));
			bloodPressure = new BloodPressure();

			// 发送指令完毕后，必须发送TOK到血压仪，以结束此次会话
			log.i(TAG, "发送结束会话请求。。。");
			sendCmd = new byte[] { 0x54, 0x4f, 0x4b, (byte) 0xff, (byte) 0xff };
			mOutputStream.write(sendCmd);
			mOutputStream.flush();
			log.i(TAG, "结束会话请求已发送。。。");
			// 写入输出流后,要进行等待500ms,以便血压仪执行处理。
			Thread.sleep(500);
			// 然后接收来自血压仪的返回信息,如果成功的话返回信息为"OK"
			buffer = new byte[2];
			length = mInputStream.read(buffer);
			if (length < 0) {
				log.i(TAG, "会话已经结束");
			}
			log.i(TAG, "结束会话请求返回数据：" + HexBinary.bytesToHexString(buffer, 0, length));
			if (buffer[0] == 'O' && buffer[1] == 'K') {
				log.i(TAG, "会话结束成功！");
			} else {
				log.i(TAG, "会话结束失败！");
			}
		} catch (IOException e) {
			log.e(TAG, "class=" + e.getClass());
			log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			log.e(TAG, e.getMessage());
			e.printStackTrace();
		}

		return bloodPressure;
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

	public static class BloodPressurePairManager {
		private static final String TAG = BloodPressureManager1.class.getSimpleName();
		private Log log = new Log("bluetoothDevice");

		private static final String DEVICE_NAME = "HEM-7081-IT";// 血压计的名称

		private Activity mActivity;

		private BluetoothAdapter mBluetoothAdapter = null;
		private BluetoothConnector mBluetoothConnector = null;
		private BluetoothSocketWrapper mBluetoothSocketWrapper = null;
		private InputStream mInputStream = null;
		private OutputStream mOutputStream = null;

		private String mTipsInfo;

		private PreferencesManager mPreferencesManager;
		private static final String KEY_PAIRED = "bloodpressure_paired";

		public BloodPressurePairManager(Activity activity) {
			this.mActivity = activity;
			mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			mPreferencesManager = new PreferencesManager(activity);
		}

		public boolean pairDevice() {
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

				// 7、连接成功后血压仪会发送"READY"过来,如果安卓设备成功接收到"READY"说明连接成功了,就可以发送"TOK"指令
				byte[] buffer = new byte[5];
				int length = mInputStream.read(buffer);
				if (length != 5 || buffer[0] != 'R' || buffer[1] != 'E' || buffer[2] != 'A' || buffer[3] != 'D' || buffer[4] != 'Y') {
					showTips(R.string.device_connect_failure);
					return false;
				}

				// 结果应该是READY
				log.i(TAG, "read data=" + HexBinary.bytesToHexStringPrint(buffer, 0, length));

				// 8、如果buffer中是"READY"则继续,发送"TOK"指令
				byte[] sendCmd = new byte[] { 0x54, 0x4f, 0x4b, (byte) 0xff, (byte) 0xff };
				mOutputStream.write(sendCmd);
				mOutputStream.flush();
				// 写入输出流后,要等待500ms,服务端处理延时500ms
				Thread.sleep(500);
				// 然后紧接着接收来自服务端也就是血压仪的返回信息,如果成功的话返回"OK"
				buffer = new byte[2];
				length = mInputStream.read(buffer);
				if (length != 2 || buffer[0] != 'O' || buffer[1] != 'K') {
					showTips(R.string.device_connect_failure);
					return false;
				}
				mPreferencesManager.putBoolean(KEY_PAIRED, true);
				showTips("授权配对成功！");
				log.i(TAG, "跟设备连接成功！");
			} catch (IOException e) {
				e.printStackTrace();
				showTips(R.string.device_connect_exception);
				return false;
			} catch (InterruptedException e) {
				e.printStackTrace();
				showTips(R.string.device_connect_exception);
				return false;
			} finally {
				closeDevice();
			}
			showTips(R.string.device_connect_success);
			return true;
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
}
