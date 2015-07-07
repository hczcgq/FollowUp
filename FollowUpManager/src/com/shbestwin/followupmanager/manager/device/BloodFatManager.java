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
import com.shbestwin.followupmanager.model.device.BloodFat;

/**
 * 
 * @ClassName: BloodFatManager
 * @Description: 血脂（健康在线，干式血尿生化分析仪）
 *
 */
public class BloodFatManager {

	private static final String TAG = BloodFatManager.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private String DEVICE_NAME = "Hihol";// 血脂的名称。，型号：BU-34

	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothConnector mBluetoothConnector = null;
	private BluetoothSocketWrapper mBluetoothSocketWrapper = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;

	public BloodFatManager(Activity activity) {
		this.mActivity = activity;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		SharedPreferences preferences=mActivity.getSharedPreferences("DEVICE_NAME", Context.MODE_PRIVATE);
		if(preferences.contains("BloodFat_BU")){
			String device=preferences.getString("BloodFat_BU", "");
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

	public BloodFat readData() {
		BloodFat bloodFat = new BloodFat();

		// 1、鉴权发送
		if (!connectReq()) {
			log.i(TAG, "鉴权请求失败！");
			showTips(R.string.device_read_data_exception);
			return bloodFat;
		}

		// 2、请求设备发送
		if (!deviceInfoReq()) {
			log.i(TAG, "请求设备发送失败！");
			showTips(R.string.device_read_data_exception);
			return bloodFat;
		}

		// 3、请求数据发送
		if (!dataReq(bloodFat)) {
			log.i(TAG, "请求数据发送失败！");
			showTips(R.string.device_read_data_exception);
			bloodFat = null;
			return bloodFat;
		}

		return bloodFat;
	}

	// 1、鉴权发送
	private boolean connectReq() {
		int repeatCount = 5;// 重试次数
		while (repeatCount > 0) {
			repeatCount--;
			try {
				// a7 b8 00 01 00 00 00 2c 01 00 01 02 03 04 05 06 07 08 09 0a
				// 0b 0c 00 00 00 01 00 00 00 00 01 01 01 01 02 02 02 02 03 03
				// 03 03 60 ec
				byte[] send = { (byte) 0xa7, (byte) 0xb8, 0, 1, 0, 0, 0, 0x2c, 1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0xa, 0xb, 0xc, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 0x60, (byte) 0xec };
				log.i(TAG, "开始鉴权发送");
				mOutputStream.write(send);
				mOutputStream.flush();
				Thread.sleep(500);
				byte[] readbuf = new byte[512];// 接收缓冲区
				int readsize = mInputStream.read(readbuf);
				log.i(TAG, "第" + (5 - repeatCount) + "次[鉴权请求]返回的数据长度为：" + readsize);
				if (readsize > 0) {
					log.i(TAG, "第" + (5 - repeatCount) + "次[鉴权请求]返回的数据为：" + HexBinary.bytesToHexStringPrint(readbuf, 0, readsize));
				}
				if (readsize >= 12) {// 读取数据成功
					// 返回的数据是否正确
					// 正确数据为：a7 b8 00 01 00 00 00 0c 01 01 21 4b
					if (readbuf[0] == (byte) 0xa7 && readbuf[1] == (byte) 0xb8 && readbuf[2] == 0 && readbuf[3] == 1 && readbuf[4] == 0 && readbuf[5] == 0 && readbuf[6] == 0 && readbuf[7] == 0x0c && readbuf[8] == 1 && readbuf[9] == 1
							&& readbuf[10] == 0x21 && readbuf[11] == 0x4b) {
						// 鉴权通过
						log.i(TAG, "第" + (5 - repeatCount) + "次[鉴权请求]通过");
						return true;
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
				log.e(TAG, "发送鉴权请求出错！" + e.getMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	// 2、发送请求设备
	private boolean deviceInfoReq() {
		int repeatCount = 5;// 重试次数
		while (repeatCount > 0) {
			repeatCount--;
			try {
				// a7 b8 00 01 00 00 00 0c 02 00 c0 70
				byte[] send = { (byte) 0xa7, (byte) 0xb8, 0, 1, 0, 0, 0, 0xc, 2, 0, (byte) 0xc0, 0x70 };
				mOutputStream.write(send);
				mOutputStream.flush();
				Thread.sleep(500);
				byte[] readbuf = new byte[512];// 接收缓冲区
				int readsize = mInputStream.read(readbuf);
				log.i(TAG, "第" + (5 - repeatCount) + "次[设备请求]返回的数据长度为：" + readsize);
				if (readsize > 0) {
					log.i(TAG, "第" + (5 - repeatCount) + "次[设备请求]返回的数据为：" + HexBinary.bytesToHexStringPrint(readbuf, 0, readsize));
				}
				if (readsize >= 131) {// 读取数据成功
					// 返回的数据是否正确
					// 正确数据为：a7 b8 00 01 00 00 00 83 02 01 01 00 04 00 00 00 00
					// 00 00 00 00 00 00 00 00 09 14 0c 01 01 00 00 00 01 00 01
					// 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
					// 00 00 00 00 00 11 00 08 03 0D 00 0e 03 0d 00 0f 03 0d 00
					// 10 03 0d 00 11 03 0d 00 12 03 01 00 13 03 01 00 14 03 01
					// 00 15 03 01 00 16 03 01 00 17 03 01 00 18 03 01 00 19 03
					// 01 00 1a 03 01 00 1b 03 01 00 1c 03 01 00 f1 03 0d cc a1

					short crc = encode(readbuf, 129);// CRC较验算法,在后面已具体列出
					if ((byte) (crc >> 8) == readbuf[130] && (byte) crc == readbuf[129]) {// 成功读取设备的信息
						// 1、读取设备ID 14至25位SendMachineId
						String sendMachineId = "";

						for (int i = 0; i < 12; i++) {
							if (readbuf[i + 14] > 0)
								sendMachineId += (char) readbuf[i + 14];
						}
						sendMachineId.toString().trim();

						// 2、读取设备出厂时间
						String sendOutTime = "";

						int t = 2000 + readbuf[27];
						sendOutTime += t;
						sendOutTime += "-";
						if (readbuf[28] < 10) {
							sendOutTime += "0";
						}
						sendOutTime += readbuf[28];
						sendOutTime += "-";
						if (readbuf[29] < 10) {
							sendOutTime += "0";
						}
						sendOutTime += readbuf[29];
						sendOutTime.toString().trim();

						// 3、设备出厂批次
						String sendOutId = "";

						for (int i = 0; i < 4; i++) {
							if (readbuf[i + 30] > 0)
								sendOutId += (char) readbuf[i + 30];
						}
						sendOutId.toString().trim();

						// 4、读取软件版本号
						String sendVersion = "V";

						// sendVersion += readbuf[34];
						sendVersion += (readbuf[35] - 48);
						sendVersion += ".";
						sendVersion += (readbuf[36] - 48);
						// sendVersion += (readbuf[37]-30);
						sendVersion.toString().trim();

						log.i(TAG, "设备ID:" + sendMachineId);
						log.i(TAG, "出厂时间:" + sendOutTime);
						log.i(TAG, "设备出厂批次:" + sendOutId);
						log.i(TAG, "软件版本号:" + sendVersion);
						// 读设备数据成功
						log.i(TAG, "第" + (5 - repeatCount) + "次[设备请求]通过");
						return true;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.e(TAG, "发送设备请求出错！" + e.getMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	// 3、请求数据发送
	private boolean dataReq(BloodFat bloodFat) {
		int repeatCount = 5;// 重试次数
		while (repeatCount > 0) {
			repeatCount--;
			try {
				// a7 b8 00 01 00 00 00 0c 08 00 b0 8d
				byte[] send = { (byte) 0xa7, (byte) 0xb8, 0, 1, 0, 0, 0, 0xc, 8, 0, (byte) 0xb0, (byte) 0x8d };
				mOutputStream.write(send);
				mOutputStream.flush();
				Thread.sleep(500);
				byte[] readbuf = new byte[512];// 接收缓冲区
				int readsize = mInputStream.read(readbuf);
				log.i(TAG, "第" + (5 - repeatCount) + "次[数据请求]返回的数据长度为：" + readsize);
				if (readsize > 0) {
					log.i(TAG, "第" + (5 - repeatCount) + "次[数据请求]返回的数据为：" + HexBinary.bytesToHexStringPrint(readbuf, 0, readsize));
				}
				if (readsize >= 55) {
					// 返回的数据是否正确
					// 正确数据为：a7 b8 00 01 00 00 00 37 08 01 01 00 00 00 01 03 00
					// 00 13 14 0c 01 01 01 01 01 01 00 00 02 02 08 00 00 03 00
					// 00 13 14 0c 01 01 01 01 01 01 00 00 02 02 00 00 00 xx xx

					short crc = encode(readbuf, 53); // CRC较验算法,在后面已具体列出
					if ((byte) (crc >> 8) == readbuf[54] && (byte) crc == readbuf[53]) {
						float fvalue = 0;
						String sendTestType = "";
						switch ((byte) readbuf[31]) {
						case (byte) 0x08:
							sendTestType = "血糖";
							fvalue = getValue(readbuf);

							bloodFat.setType(BloodFat.TYPE_BLOOD_GLUCOSE);
							bloodFat.setBloodGlucose(fvalue);
							break;
						case (byte) 0x0E:
							sendTestType = "血总胆固醇";
							fvalue = getValue(readbuf);

							bloodFat.setType(BloodFat.TYPE_BLOOD_CHOL);
							bloodFat.setBloodCHOL(fvalue);
							break;
						case (byte) 0x0F:
							sendTestType = "血甘油三脂";
							fvalue = getValue(readbuf);

							bloodFat.setType(BloodFat.TYPE_BLOOD_TG);
							bloodFat.setBloodTG(fvalue);
							break;
						case (byte) 0x10:
							sendTestType = "血高密度脂蛋白";
							fvalue = getValue(readbuf);

							bloodFat.setType(BloodFat.TYPE_BLOOD_HDL);
							bloodFat.setBloodHDL(fvalue);
							break;
						default:
							bloodFat = null;
							sendTestType = "未知";
							break;
						}

						String sendTestTime = "";
						int t = 2000 + readbuf[20];
						sendTestTime += t;
						sendTestTime += "-";
						if (readbuf[21] < 10) {
							sendTestTime += "0";
						}
						sendTestTime += readbuf[21];
						sendTestTime += "-";
						if (readbuf[22] < 10) {
							sendTestTime += "0";
						}
						sendTestTime += readbuf[22];
						sendTestTime += " ";
						if (readbuf[23] < 10) {
							sendTestTime += "0";
						}
						sendTestTime += readbuf[23];
						sendTestTime += ":";
						if (readbuf[24] < 10) {
							sendTestTime += "0";
						}
						sendTestTime += readbuf[24];
						sendTestTime += ":00";
						String sendPageId = "";
						if (sendTestType.equals("血糖")) {
							sendPageId = "U";
							for (int i = 1; i < 4; i++) {
								if (readbuf[i + 46] > 0)
									sendPageId += (char) readbuf[i + 46];
							}
						} else {
							for (int i = 0; i < 4; i++) {
								if (readbuf[i + 46] > 0)
									sendPageId += (char) readbuf[i + 46];
							}
						}
						log.i(TAG, "pageId" + sendPageId + "," + sendTestTime + ":" + sendTestType + "=" + fvalue);
						// 读取数据通过
						log.i(TAG, "第" + (5 - repeatCount) + "次[数据请求]通过");
						return true;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.e(TAG, "发送数据请求出错！" + e.getMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	public float getValue(byte[] readbuf) {
		return (float) (1000 * (-48 + readbuf[27]) + 100 * (-48 + readbuf[28]) + 10 * (-48 + readbuf[29]) + (-48 + readbuf[30])) / 100.0F;
	}

	public short encode(byte[] b, int len) { // CRC校验算法
		int i, j;
		int crc_reg = 0;
		short index;
		int to_xor;
		for (i = 0; i < len; i++) {
			index = (short) ((crc_reg ^ b[i]) & 0xff);
			to_xor = index;
			for (j = 0; j < 8; j++) {
				if ((to_xor & 0x0001) != 0) {
					to_xor = (to_xor >> 1) ^ 0x8408;
				} else {
					to_xor >>= 1;
				}
			}
			crc_reg = (crc_reg >> 8) ^ to_xor;
		}
		return (short) crc_reg;
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
