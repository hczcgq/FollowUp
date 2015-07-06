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
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.ivsign.android.IDCReader.IDCReaderSDK;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.device.IDCardInfo;

public class ReadIDCardManager {
	private static final String DEVICE_NAME = "CVR-100B";
//	private static final String DEVICE_NAME2 = "IDCReader";
//	private static final String DEVICE_NAME3 = "COM2";
//	private static final String DEVICE_NAME4 = "BOLUTEK";

	private static byte[] CMD_FIND = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x20, 0x01, 0x22 };
	private static byte[] CMD_SELT = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x20, 0x02, 0x21 };
	private static byte[] CMD_READ = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x30, 0x01, 0x32 };

	private Activity mActivity;

//	private BluetoothServerSocket mBluetoothServerSocket = null;
	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothSocket mBluetoothSocket = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private byte[] mReceiveData = new byte[1500];// 接收到的数据
	private String[] mDecodeInfo = new String[10];// 解压数据
	
	private String mTipsInfo;

	public ReadIDCardManager(Activity activity) {
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
			//|| DEVICE_NAME2.equals(device.getName()) || DEVICE_NAME3.equals(device.getName()) || DEVICE_NAME4.equals(device.getName())
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

		// 4、使得蓝牙处于可发现模式，持续时间150s
		Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);// 使得蓝牙处于可发现模式，持续时间150s
		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 150);

		// 5、创建蓝牙Socket
		try {
			UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
			if (Build.VERSION.SDK_INT >= 10) {// 根据不同的版本，使用不同的创建方式
				mBluetoothSocket = adaptedDevice.createInsecureRfcommSocketToServiceRecord(uuid);
			} else {
				mBluetoothSocket = adaptedDevice.createRfcommSocketToServiceRecord(uuid);
			}
			
//			mBluetoothServerSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("IDCardServerSocket", uuid);

			// 6、连接读卡器设备
			mBluetoothSocket.connect();

			// 7、获取输入输出流
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

	public IDCardInfo readCardInfo() {
		IDCardInfo cardInfo = null;
		int readcount = 15;// 尝试读取的次数
		try {
			while (readcount > 1) {
				int readFlag = -99;
				readFlag = readCard();
				readcount = readcount - 1;
				if (readFlag > 0) {
					readcount = 0;
					cardInfo = new IDCardInfo();
					cardInfo.setName(mDecodeInfo[0]);
					cardInfo.setGender(mDecodeInfo[1]);
					cardInfo.setEthnic(mDecodeInfo[2]);
					cardInfo.setBirthday(mDecodeInfo[3]);
					cardInfo.setAddress(mDecodeInfo[4]);
					cardInfo.setIdcard(mDecodeInfo[5]);
					cardInfo.setAuthority(mDecodeInfo[6]);
					cardInfo.setValidDate( mDecodeInfo[7] + "-" + mDecodeInfo[8]);
					String result = 
							"姓名：" + mDecodeInfo[0] + "\n" +
							"性别：" + mDecodeInfo[1] + "\n" + 
							"民族：" + mDecodeInfo[2] + "\n" + 
							"出生日期：" + mDecodeInfo[3] + "\n" + 
							"地址：" + mDecodeInfo[4] + "\n" + 
							"身份号码：" + mDecodeInfo[5] + "\n" + 
							"签发机关："+ mDecodeInfo[6] + "\n" +
							"有效期限：" + mDecodeInfo[7] + "-" + mDecodeInfo[8] + "\n" + 
							"最后一位：" + mDecodeInfo[9] + "\n";
					Log.i("hejunbin", "result="+result);
					if (readFlag == 1) {
						cardInfo.setPicturePath(Environment.getExternalStorageDirectory() + "/wltlib/zp.bmp");
					} else {//读取图片错误，
						//"照片解码失败，请检查路径" + Environment.getExternalStorageDirectory() + "/wltlib/"
					}
					return cardInfo;
				} else {
					if (readFlag == -2) {
						showTips(R.string.device_connect_exception);
					}
					if (readFlag == -3) {
						showTips(R.string.device_idcard_no_card);
					}
					if (readFlag == -4) {
						showTips(R.string.device_idcard_no_card);
					}
					if (readFlag == -5) {
						showTips(R.string.device_idcard_read_failure);
					}
					if (readFlag == -99) {
						showTips(R.string.device_read_data_exception);
					}
				}
				Thread.sleep(100);
			}

		}catch (InterruptedException e) {
			e.printStackTrace();
			showTips(R.string.device_read_data_exception);
		}
		return cardInfo;
	}

	private int readCard() {
		int readFlag = -99;
		try {
			if ((mInputStream == null) || (mInputStream == null)) {
				readFlag = -2;// 连接异常
				return readFlag;
			}
			mOutputStream.write(CMD_FIND);
			Thread.sleep(200);
			int dataLen = mInputStream.read(mReceiveData);
			if (mReceiveData[9] == -97) {
				mOutputStream.write(CMD_SELT);
				Thread.sleep(200);
				dataLen = mInputStream.read(mReceiveData);
				if (mReceiveData[9] == -112) {
					mOutputStream.write(CMD_READ);
					Thread.sleep(1000);
					byte[] tempData = new byte[1500];
					if (mInputStream.available() > 0) {
						dataLen = mInputStream.read(tempData);
					} else {
						Thread.sleep(500);
						if (mInputStream.available() > 0) {
							dataLen = mInputStream.read(tempData);
						}
					}
					int flag = 0;
					if (dataLen < 1294) {
						for (int i = 0; i < dataLen; i++, flag++) {
							mReceiveData[flag] = tempData[i];
						}
						Thread.sleep(1000);
						if (mInputStream.available() > 0) {
							dataLen = mInputStream.read(tempData);
						} else {
							Thread.sleep(500);
							if (mInputStream.available() > 0) {
								dataLen = mInputStream.read(tempData);
							}
						}
						for (int i = 0; i < dataLen; i++, flag++) {
							mReceiveData[flag] = tempData[i];
						}

					} else {
						for (int i = 0; i < dataLen; i++, flag++) {
							mReceiveData[flag] = tempData[i];
						}
					}
					tempData = null;
					if (flag == 1295) {
						if (mReceiveData[9] == -112) {

							byte[] dataBuf = new byte[256];
							for (int i = 0; i < 256; i++) {
								dataBuf[i] = mReceiveData[14 + i];
							}
							String TmpStr = new String(dataBuf, "UTF16-LE");
							System.out.println("身份证信息："+tempData);
							TmpStr = new String(TmpStr.getBytes("UTF-8"));
							mDecodeInfo[0] = TmpStr.substring(0, 15);
							mDecodeInfo[1] = TmpStr.substring(15, 16);
							mDecodeInfo[2] = TmpStr.substring(16, 18);
							mDecodeInfo[3] = TmpStr.substring(18, 26);
							mDecodeInfo[4] = TmpStr.substring(26, 61);
							mDecodeInfo[5] = TmpStr.substring(61, 79);
							mDecodeInfo[6] = TmpStr.substring(79, 94);
							mDecodeInfo[7] = TmpStr.substring(94, 102);
							mDecodeInfo[8] = TmpStr.substring(102, 110);
							mDecodeInfo[9] = TmpStr.substring(110, 128);
							if (mDecodeInfo[1].equals("1"))
								mDecodeInfo[1] = "男";
							else
								mDecodeInfo[1] = "女";
							try {
								int code = Integer.parseInt(mDecodeInfo[2].toString());
								mDecodeInfo[2] = decodeNation(code);
							} catch (Exception e) {
								mDecodeInfo[2] = "";
							}

							// 照片解码
							try {
								int ret = IDCReaderSDK.init();
								if (ret == 0) {
									byte[] datawlt = new byte[1384];
									byte[] byLicData = { (byte) 0x05, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x5B, (byte) 0x03, (byte) 0x33, (byte) 0x01, (byte) 0x5A, (byte) 0xB3, (byte) 0x1E, (byte) 0x00 };
									for (int i = 0; i < 1295; i++) {
										datawlt[i] = mReceiveData[i];
									}
									int t = IDCReaderSDK.unpack(datawlt, byLicData);
									if (t == 1) {
										readFlag = 1;// 读卡成功
									} else {
										readFlag = 6;// 照片解码异常
									}
								} else {
									readFlag = 6;// 照片解码异常
								}
							} catch (Exception e) {
								readFlag = 6;// 照片解码异常
							}

						} else {
							readFlag = -5;// 读卡失败！
						}
					} else {
						readFlag = -5;// 读卡失败
					}
				} else {
					readFlag = -4;// 选卡失败
				}
			} else {
				readFlag = -3;// 寻卡失败
			}

		} catch (IOException e) {
			readFlag = -99;// 读取数据异常
		} catch (InterruptedException e) {
			readFlag = -99;// 读取数据异常
		}
		return readFlag;
	}

	private String decodeNation(int code) {
		String nation;
		switch (code) {
		case 1:
			nation = "汉";
			break;
		case 2:
			nation = "蒙古";
			break;
		case 3:
			nation = "回";
			break;
		case 4:
			nation = "藏";
			break;
		case 5:
			nation = "维吾尔";
			break;
		case 6:
			nation = "苗";
			break;
		case 7:
			nation = "彝";
			break;
		case 8:
			nation = "壮";
			break;
		case 9:
			nation = "布依";
			break;
		case 10:
			nation = "朝鲜";
			break;
		case 11:
			nation = "满";
			break;
		case 12:
			nation = "侗";
			break;
		case 13:
			nation = "瑶";
			break;
		case 14:
			nation = "白";
			break;
		case 15:
			nation = "土家";
			break;
		case 16:
			nation = "哈尼";
			break;
		case 17:
			nation = "哈萨克";
			break;
		case 18:
			nation = "傣";
			break;
		case 19:
			nation = "黎";
			break;
		case 20:
			nation = "傈僳";
			break;
		case 21:
			nation = "佤";
			break;
		case 22:
			nation = "畲";
			break;
		case 23:
			nation = "高山";
			break;
		case 24:
			nation = "拉祜";
			break;
		case 25:
			nation = "水";
			break;
		case 26:
			nation = "东乡";
			break;
		case 27:
			nation = "纳西";
			break;
		case 28:
			nation = "景颇";
			break;
		case 29:
			nation = "柯尔克孜";
			break;
		case 30:
			nation = "土";
			break;
		case 31:
			nation = "达斡尔";
			break;
		case 32:
			nation = "仫佬";
			break;
		case 33:
			nation = "羌";
			break;
		case 34:
			nation = "布朗";
			break;
		case 35:
			nation = "撒拉";
			break;
		case 36:
			nation = "毛南";
			break;
		case 37:
			nation = "仡佬";
			break;
		case 38:
			nation = "锡伯";
			break;
		case 39:
			nation = "阿昌";
			break;
		case 40:
			nation = "普米";
			break;
		case 41:
			nation = "塔吉克";
			break;
		case 42:
			nation = "怒";
			break;
		case 43:
			nation = "乌孜别克";
			break;
		case 44:
			nation = "俄罗斯";
			break;
		case 45:
			nation = "鄂温克";
			break;
		case 46:
			nation = "德昂";
			break;
		case 47:
			nation = "保安";
			break;
		case 48:
			nation = "裕固";
			break;
		case 49:
			nation = "京";
			break;
		case 50:
			nation = "塔塔尔";
			break;
		case 51:
			nation = "独龙";
			break;
		case 52:
			nation = "鄂伦春";
			break;
		case 53:
			nation = "赫哲";
			break;
		case 54:
			nation = "门巴";
			break;
		case 55:
			nation = "珞巴";
			break;
		case 56:
			nation = "基诺";
			break;
		case 97:
			nation = "其他";
			break;
		case 98:
			nation = "外国血统中国籍人士";
			break;
		default:
			nation = "";
			break;
		}
		return nation;
	}

	private void showTips(final int resId) {
		mTipsInfo = mActivity.getResources().getString(resId);
//		mActivity.runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//				ToastUtils.showToast(mActivity, resId);
//			}
//		});
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
		
//		if (mBluetoothServerSocket != null) {
//			try {
//				mBluetoothServerSocket.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
