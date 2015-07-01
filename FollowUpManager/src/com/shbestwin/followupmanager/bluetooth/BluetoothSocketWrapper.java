package com.shbestwin.followupmanager.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;

/**
 * 
 * @ClassName: BluetoothSocketWrapper
 * @Description: 本地蓝牙Socket接口
 *
 */
public interface BluetoothSocketWrapper {
	public InputStream getInputStream() throws IOException;// 输入流

	public OutputStream getOutputStream() throws IOException;// 输出流

	public String getRemoteDeviceName();// 设备名称

	public void connect() throws IOException;// 连接设备

	public String getRemoteDeviceAddress();// 远程设备的地址

	public void close() throws IOException;// 关闭连接

	public BluetoothSocket getUnderlyingSocket();// 连接重试
}
