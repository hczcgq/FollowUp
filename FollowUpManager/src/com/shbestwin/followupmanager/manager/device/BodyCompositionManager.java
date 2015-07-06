package com.shbestwin.followupmanager.manager.device;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Set;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.bluetooth.BluetoothConnector;
import com.shbestwin.followupmanager.bluetooth.BluetoothSocketWrapper;
import com.shbestwin.followupmanager.common.log.Log;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.HexBinary;
import com.shbestwin.followupmanager.model.device.BodyComposition;

//01-30 22:51:54.035: I/BodyCompositionManager(10699): 返回数据，长度=372
//01-30 22:51:54.210: I/BodyCompositionManager(10699): 返回数据：FF 01 74 00 00 01 01 07 DC 01 01 00 02 03 9E A0 01 02 68 00 E9 18 01 A4 0B 01 94 00 F1 02 30 05 42 07 DC 01 01 02 1F 39 9E A0 01 02 4E 00 F1 17 01 A0 05 02 84 00 E7 02 2A 05 3E 07 DC 01 01 02 24 11 A2 AB 01 02 4D 00 BC 19 01 BD 03 02 85 00 CA 02 50 05 5A 07 DC 01 01 02 26 05 A2 AB 01 02 4C 00 BE 19 01 BC 03 02 99 00 C9 02 4F 05 5A 07 DC 01 01 03 07 03 1B 9C 01 01 C5 00 A0 14 01 CC 02 02 92 00 BA 02 65 04 EB 07 DC 01 08 04 0C 04 A2 AA 01 02 52 00 C1 19 01 BA 03 02 58 00 CE 02 4D 05 56 07 DC 01 08 04 0F 0D A2 AA 01 02 54 00 C1 19 01 BA 03 02 51 00 CF 02 4D 05 56 07 DC 01 11 09 10 31 9B AA 01 02 1D 00 8A 18 01 D8 01 02 5B 00 BB 02 75 05 7E 07 DC 02 02 01 16 23 9B AA 01 02 10 00 88 18 01 DA 01 02 B2 00 B7 02 77 05 7C 07 DC 02 02 01 19 0F 9B AA 01 02 0F 00 86 18 01 DA 01 02 AC 00 B7 02 78 05 7C 07 DC 02 02 01 1A 1A 9B AA 01 02 10 00 88 18 01 DA 01 02 B2 00 B7 02 77 05 7C 07 DC 02 0C 0C 29 01 9B AA 01 02 19 00 7F 18 01 DF 01 02 1E 00 BA 02 7E 05 7E 07 DC 02 0C 0C 30 1B 9B AA 01 02 1E 00 83 18 01 DC 01 02 1D 00 BC 02 7A 05 7E 07 DC 02 0C 0D 06 3A 9B AA 01 02 18 00 7F 18 01 DE 01 02 31 00 B9 02 7D 05 7D 52 

/**
 * 
 * @ClassName: BodyCompositionManager
 * @Description: 脂肪秤（人体成分）
 *
 */
public class BodyCompositionManager {
	private static final String TAG = BodyCompositionManager.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private static final String DEVICE_NAME = "Belter";// 脂肪秤的名称

	private static byte[] CMD_REQUEST_DATA = new byte[6];

	static {
		CMD_REQUEST_DATA[0] = (byte) 0xAA;
		CMD_REQUEST_DATA[1] = (byte) 0x0A;
		CMD_REQUEST_DATA[2] = 0x29;
		CMD_REQUEST_DATA[3] = 0x06;
		CMD_REQUEST_DATA[4] = 0x00;
		CMD_REQUEST_DATA[5] = (byte) (CMD_REQUEST_DATA[0] ^ CMD_REQUEST_DATA[1] ^ CMD_REQUEST_DATA[2] ^ CMD_REQUEST_DATA[3] ^ CMD_REQUEST_DATA[4]);
	}

	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothConnector mBluetoothConnector = null;
	private BluetoothSocketWrapper mBluetoothSocketWrapper = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;

	public BodyCompositionManager(Activity activity) {
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

	public BodyComposition readData() {
		BodyComposition bodyComposition = null;
		try {
			mOutputStream.write(CMD_REQUEST_DATA);
			mOutputStream.flush();

			Thread.sleep(1000L);
			byte[] buffer = new byte[3];
			int length = mInputStream.read(buffer);
			log.i(TAG, "返回数据，长度=" + length);
			if (length < 0) {
				log.i(TAG, "设备已断开");
				showTips(R.string.device_connect_exception);
				return bodyComposition;
			}

			if (length != 3) {
				log.i(TAG, "设备已断开");
				showTips(R.string.device_connect_exception);
				return bodyComposition;
			}
			int len = getShort(buffer, 1);// 数据的总长度
			log.i(TAG, "数据总长度：" + len);
			int currentLen = 3;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(len);
			byteArrayOutputStream.write(buffer);
			while (currentLen < len) {
				buffer = new byte[2014];
				int tempLen = mInputStream.read(buffer);
				log.i(TAG, "长度="+tempLen + "返回数据：" + HexBinary.bytesToHexStringPrint(buffer, 0, tempLen));
				if (tempLen < 0) {
					break;
				}
				byteArrayOutputStream.write(buffer, 0, tempLen);
				currentLen += tempLen;
			}

			buffer = byteArrayOutputStream.toByteArray();
			log.i(TAG, "返回数据：" + HexBinary.bytesToHexStringPrint(buffer, 0, len));
			DecimalFormat df = new DecimalFormat("0.0");
			
			long currentDate = 0;
			if (len >= 34) {// 表示至少包含一组数据
				int index = 1;
				for (int i = 7; i < len - 1; i += 26) {
					// System.out.println("第" + index + "组数据:" +
					// bytesToHexString(bytes, i, i + 26));
					// 07(0):年高字节
					// DC(1):年第字节
					// 01(2):月
					// 01(3):日
					// 00(4):时
					// 02(5):分
					// 03(6):秒
					// 10011110
					// 1111001
					String date = getShort(buffer, i) + "年" + getInt(buffer[i + 2]) + "月" + getInt(buffer[i + 3]) + "日 " + getInt(buffer[i + 4]) + "时" + getInt(buffer[i + 5]) + "分" + getInt(buffer[i + 6]) + "秒";
					// 9E(7):Bit7=1为男性，Bit7=0为女性，Bit6-Bit0:年龄
					// date=2012年1月1日 2时36分17秒
					long _currentDate = DateUtils.parseDate(date, "yyyy年MM月dd日 HH时mm分ss秒").getTime();
					if (currentDate >= _currentDate) {
						continue;
					}
					currentDate = _currentDate;
					bodyComposition = new BodyComposition();
					
					int age = buffer[i + 7] & 0x7f;
					bodyComposition.setAge(age);
					
					String gendar = "";
					if ((buffer[i + 7] & 0x80) == 0x00) {// 女性
						gendar = "女";
					} else {
						gendar = "男";
					}
					bodyComposition.setGendar(gendar);
					
					// A0(8):身高（cm）
					int height = getInt(buffer[i + 8]);
					bodyComposition.setHeight(height);
					// 01(9):运动级别（1=非运动员，2-业余运动员，3=专业运动员）
					int sportLevel = getInt(buffer[i + 9]);
					bodyComposition.setSportLevel(sportLevel);
					// 02(10):体重高字节
					// 68(11):体重低字节
					float weight = getShort(buffer, i + 10) * 0.1F;
					bodyComposition.setWeight(weight);
					// 00(12):脂肪高字节
					// E9(13):脂肪低字节
					float fat = getShort(buffer, i + 12) * 0.1F;
					bodyComposition.setFat(fat);
					// 18(14):骨量
					float boneMass = getInt(buffer[i + 14]) * 0.1F;
					bodyComposition.setBoneMass(boneMass);
					// 01(15):肌肉高字节
					// A4(16):肌肉低字节
					float muscle = getShort(buffer, i + 15) * 0.1F;
					bodyComposition.setMuscle(muscle);
					// 0B(17):内脏脂肪
					int visceralFat = getInt(buffer[i + 17]);
					bodyComposition.setVisceralFat(visceralFat);
					// 01(18):人体阻抗值高字节
					// 94(19):人体阻抗值低字节
					int bodyImpedance = getShort(buffer, i + 18);
					bodyComposition.setBodyImpedance(bodyImpedance);
					// 00(20):BMI高字节
					// F1(21):BMI低字节
					float BMI = getShort(buffer, i + 20) * 0.1F;
					bodyComposition.setBMI(BMI);
					// 02(22):水分高字节
					// 30(23):水分低字节
					float bodyWater = getShort(buffer, i + 22) * 0.1F;
					bodyComposition.setBodyWater(bodyWater);
					// 05(24):KCAL值高字节
					// 42(25):KCAL值低字节
					int KCAL = getShort(buffer, i + 24);
					bodyComposition.setKCAL(KCAL);

					log.i(TAG, "第" + index + "组数据:date=" + date + ",gendar=" + gendar + ",age=" + age + ",height=" + height + ",sportLevel=" + sportLevel + ",weight=" + df.format(weight) + ",fat=" + df.format(fat) + "%,boneMass="
							+ boneMass + ",muscle=" + muscle + "%,visceralFat=" + visceralFat + ",bodyImpedance=" + bodyImpedance + ",BMI=" + BMI + ",bodyWater=" + bodyWater + "%,KCAL=" + KCAL);
					index++;

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bodyComposition;
	}

	/**
	 * 通过byte数组取到short
	 * 
	 * @param b
	 * @param index
	 *            第几位开始取
	 * @return
	 */
	public static short getShort(byte[] b, int index) {
		return (short) (((b[index + 0] << 8) | b[index + 1] & 0xff));
	}

	public static int getInt(byte b) {
		return b & 0xff;
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
