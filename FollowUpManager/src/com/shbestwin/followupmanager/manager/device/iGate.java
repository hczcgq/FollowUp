package com.shbestwin.followupmanager.manager.device;

import android.util.Log;

public class iGate {

	public void iGateDeviceSendData(int type) {
		Log.i("hejunbin", "开始处理业务，type=" + type);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Log.i("hejunbin", "处理业务完成，type=" + type);
		if (gateCallBacks != null) {
			gateCallBacks.iGateDeviceReceivedData();
		}
	}

	private iGateCallBacks gateCallBacks;

	public void setGateCallBacks(iGateCallBacks gateCallBacks) {
		this.gateCallBacks = gateCallBacks;
	}
	

	public interface iGateCallBacks {
		public void iGateDeviceReceivedData();
	}

}
