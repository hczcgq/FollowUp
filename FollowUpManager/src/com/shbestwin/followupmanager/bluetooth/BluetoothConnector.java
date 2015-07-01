package com.shbestwin.followupmanager.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;

import com.shbestwin.followupmanager.common.log.Log;

/**
 * 
 * @ClassName: BluetoothConnector
 * @Description: 蓝牙连接器
 *
 */
public class BluetoothConnector {
	private static final String TAG = BluetoothConnector.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	private BluetoothSocketWrapper bluetoothSocket;
	private BluetoothDevice device;
	private BluetoothAdapter adapter;
	private UUID uuid;

	public BluetoothConnector(BluetoothDevice device, BluetoothAdapter adapter) {
		this.device = device;
		this.adapter = adapter;
		this.uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	}
	
	public BluetoothConnector(BluetoothDevice device, BluetoothAdapter adapter,String uuid) {
		this.device = device;
		this.adapter = adapter;
		this.uuid = UUID.fromString(uuid);
	}

	public BluetoothSocketWrapper connect() throws IOException {// 连接蓝牙设备
		boolean success = false;
		while (selectSocket()) {
			adapter.cancelDiscovery();
			try {
				bluetoothSocket.connect();
				success = true;
				break;
			} catch (IOException e) {
				// try the fallback
				try {
					bluetoothSocket = new FallbackBluetoothSocket(bluetoothSocket.getUnderlyingSocket());
					Thread.sleep(500);
					bluetoothSocket.connect();
					success = true;
					break;
				} catch (FallbackException e1) {
					log.e(TAG, "Could not initialize FallbackBluetoothSocket classes.", e);
				} catch (InterruptedException e1) {
					log.e(TAG, e1.getMessage(), e1);
				} catch (IOException e1) {
					log.e(TAG, "Fallback failed. Cancelling.", e1);
				}
			}
		}
		if (!success) {
			throw new IOException("Could not connect to device: " + device.getAddress());
		}
		return bluetoothSocket;
	}

	private boolean selectSocket() throws IOException {
		BluetoothSocket tmp;
		log.i(TAG, "Attempting to connect to Protocol: " + uuid);
		if (Build.VERSION.SDK_INT >= 10) {// 根据不同的版本，使用不同的创建方式
			tmp = device.createInsecureRfcommSocketToServiceRecord(uuid);
		} else {
			tmp = device.createRfcommSocketToServiceRecord(uuid);
		}
		bluetoothSocket = new LocalBluetoothSocket(tmp);
		return true;
	}

}
