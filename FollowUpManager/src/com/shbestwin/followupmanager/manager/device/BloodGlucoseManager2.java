package com.shbestwin.followupmanager.manager.device;

import android.app.Activity;
import android.util.Log;

import com.shbestwin.followupmanager.manager.device.iGate.iGateCallBacks;
import com.shbestwin.followupmanager.model.device.BloodGlucose;

/**
 * 
 * @ClassName: BloodGlucoseManager
 * @Description: 血糖仪（101A0000047）
 *
 */
public class BloodGlucoseManager2 implements iGateCallBacks {

	private iGate mIgate = null;

	private String mTipsInfo;

	private int sendType;// 1:获取meter ID 2:获取数据

	private static Object LOCK = new Object();

	private BloodGlucose bloodGlucose;

	public BloodGlucoseManager2(Activity activity) {
		mIgate = new iGate();
		mIgate.setGateCallBacks(this);
	}

	@Override
	public void iGateDeviceReceivedData() {
		Log.i("hejunbin", "sendType=" + sendType);
		if (sendType == 1) {
			sendType = 2;
			Log.i("hejunbin", "正在发送获取数据请求。。。");
			mIgate.iGateDeviceSendData(2);
			Log.i("hejunbin", "获取数据请求已发送。。。");
		} else {
			synchronized (LOCK) {
				// 读取数据
				bloodGlucose = new BloodGlucose();
				LOCK.notify();
			}
		}
	}

	/**
	 * 打开连接
	 * 
	 * @return 连接状态
	 */
	public boolean connectDevice() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	public BloodGlucose readData() {
		synchronized (LOCK) {
			sendType = 1;
			bloodGlucose = null;
			// 发送数据
			Log.i("hejunbin", "正在发送获取meter id请求。。。");
			mIgate.iGateDeviceSendData(1);
			Log.i("hejunbin", "获取meter id请求已发送。。。");
			if (bloodGlucose == null) {
				try {
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return bloodGlucose;
	}

	public String getTipsInfo() {
		return mTipsInfo;
	}

	/**
	 * 关闭设备
	 */
	public void closeDevice() {

	}

}
