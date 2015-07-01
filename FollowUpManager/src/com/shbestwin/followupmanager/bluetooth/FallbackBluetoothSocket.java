package com.shbestwin.followupmanager.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import android.bluetooth.BluetoothSocket;

public class FallbackBluetoothSocket extends LocalBluetoothSocket {

	private BluetoothSocket fallbackSocket;

	public FallbackBluetoothSocket(BluetoothSocket bluetoothSocket) throws FallbackException {
		super(bluetoothSocket);
		try {
			Class<?> clazz = bluetoothSocket.getRemoteDevice().getClass();
			Class<?>[] paramTypes = new Class<?>[] { Integer.TYPE };
			Method m = clazz.getMethod("createRfcommSocket", paramTypes);
			Object[] params = new Object[] { Integer.valueOf(1) };
			fallbackSocket = (BluetoothSocket) m.invoke(bluetoothSocket.getRemoteDevice(), params);
		} catch (Exception e) {
			throw new FallbackException(e);
		}
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return fallbackSocket.getInputStream();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return fallbackSocket.getOutputStream();
	}

	@Override
	public void connect() throws IOException {
		fallbackSocket.connect();
	}

	@Override
	public void close() throws IOException {
		fallbackSocket.close();
	}

}
