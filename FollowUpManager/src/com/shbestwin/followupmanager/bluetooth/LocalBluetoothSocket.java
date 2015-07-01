package com.shbestwin.followupmanager.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;

/**
 * 
 * @ClassName: LocalBluetoothSocket
 * @Description: 本地蓝牙Socket
 *
 */
public class LocalBluetoothSocket implements BluetoothSocketWrapper {

	private BluetoothSocket socket;

	public LocalBluetoothSocket(BluetoothSocket bluetoothSocket) {
		this.socket = bluetoothSocket;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return socket.getInputStream();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return socket.getOutputStream();
	}

	@Override
	public String getRemoteDeviceName() {
		return socket.getRemoteDevice().getName();
	}

	@Override
	public void connect() throws IOException {
		socket.connect();
	}

	@Override
	public String getRemoteDeviceAddress() {
		return socket.getRemoteDevice().getAddress();
	}

	@Override
	public void close() throws IOException {
		socket.close();
	}

	@Override
	public BluetoothSocket getUnderlyingSocket() {
		return socket;
	}

}
