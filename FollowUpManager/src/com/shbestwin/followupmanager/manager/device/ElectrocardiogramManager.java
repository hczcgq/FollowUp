package com.shbestwin.followupmanager.manager.device;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.shbestwin.followupmanager.model.device.Electrocardiogram;

//01-30 23:19:54.645: I/ElectrocardiogramManager(15120): 正在连接设备
//01-30 23:19:58.830: I/ElectrocardiogramManager(15120): 连接设备成功
//01-30 23:19:58.840: I/ElectrocardiogramManager(15120): 等待HC201发送Hello-regularData包
//01-30 23:21:02.245: I/ElectrocardiogramManager(15211): Name=ECG:HC-201B,address=30:14:09:03:15:10
//01-30 23:21:02.245: I/ElectrocardiogramManager(15211): 正在连接设备
//01-30 23:21:03.970: I/ElectrocardiogramManager(15211): 连接设备成功
//01-30 23:21:03.985: I/ElectrocardiogramManager(15211): 等待HC201发送Hello-regularData包
//01-30 23:21:04.985: I/ElectrocardiogramManager(15211): HC201发送Hello-regularData包，长度=4,内容为：AA 01 01 55 
//01-30 23:21:04.985: I/ElectrocardiogramManager(15211): 上位机回复ACK-Hello-regularData包
//01-30 23:21:04.995: I/ElectrocardiogramManager(15211): 等待HC201发送TotalLength包
//01-30 23:21:06.000: I/ElectrocardiogramManager(15211): HC201发送TotalLength包，长度=4,内容为：AA 02 01 00 
//01-30 23:21:06.000: I/ElectrocardiogramManager(15211): 上位机回复ACKTotalLength包
//01-30 23:21:06.015: I/ElectrocardiogramManager(15211): 等待HC201Data-Regular包
//01-30 23:21:07.060: I/ElectrocardiogramManager(15211): HC201发送Data-Regular包，长度=510,内容为：AA 03 01 00 1E 00 01 00 F4 01 0F 01 1E 17 0F 36 83 31 0B 00 01 00 00 00 94 02 05 00 62 00 F8 00 E6 FF 50 00 23 00 00 40 1E 64 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 F6 FF 08 01 00 00 E6 07 41 08 08 01 3A 0D 5A 00 B1 0C 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00 F6 FF 42 0E 00 00 7F FE 86 FE 3A 0D 70 00 D9 FF 69 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 F7 FF B2 0E 00 00 B0 00 CB 00 70 00 C8 00 E5 FF 98 04 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 04 00 F6 FF 7A 0F 00 00 16 02 38 00 C8 00 26 00 84 02 F3 04 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 F6 FF A0 0F 00 00 00 03 9D FE 26 00 81 00 D1 FF 81 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 06 00 F7 FF 21 10 00 00 7F 00 84 00 81 00 7F 00 FB FF 41 05 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 07 00 F7 FF A0 10 00 00 BF 00 6C 02 7F 00 AF 01 53 FE F1 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 08 00 F7 FF 4F 12 00 00 B4 01 D4 02 AF 01 53 00 E0 FE D8 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 FF FF A3 12 00 00 56 01 F6 02 53 00 FF 01 60 FE 17 00 00 00 00 00 00 00 3A 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 F6 FF A2 14 00 00 D6 03 E8 00 59 00 1F 00 CB FF 66 00 00 00 00 00 00 00 00 00 
//01-30 23:21:07.060: I/ElectrocardiogramManager(15211): 上位机回复ACK-Data包

//01-30 23:24:02.425: I/ElectrocardiogramManager(15512): Name=ECG:HC-201B,address=30:14:09:03:15:10
//01-30 23:24:02.425: I/ElectrocardiogramManager(15512): 正在连接设备
//01-30 23:24:04.025: I/ElectrocardiogramManager(15512): 连接设备成功
//01-30 23:24:04.035: I/ElectrocardiogramManager(15512): 等待HC201发送Hello-regularData包
//01-30 23:24:06.040: I/ElectrocardiogramManager(15512): HC201发送Hello-regularData包，长度=4,内容为：AA 01 01 55 
//01-30 23:24:06.040: I/ElectrocardiogramManager(15512): 上位机回复ACK-Hello-regularData包
//01-30 23:24:06.040: I/ElectrocardiogramManager(15512): 等待HC201发送TotalLength包
//01-30 23:24:07.045: I/ElectrocardiogramManager(15512): HC201发送TotalLength包，长度=4,内容为：AA 02 01 00 
//01-30 23:24:07.045: I/ElectrocardiogramManager(15512): 上位机回复ACKTotalLength包
//01-30 23:24:07.055: I/ElectrocardiogramManager(15512): 等待HC201Data-Regular包
//01-30 23:24:08.090: I/ElectrocardiogramManager(15512): HC201发送Data-Regular包，长度=510,内容为：AA 03 01 00 1E 00 01 00 F4 01 0F 01 1E 17 12 36 83 31 11 00 01 00 00 00 76 02 05 00 62 00 F8 00 E6 FF 50 00 23 00 00 40 1E 64 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 F6 FF 8F 00 00 00 4B 08 41 08 8F 00 91 02 5A 00 B1 0C 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00 F6 FF 20 03 00 00 92 01 86 FE 91 02 84 01 D9 FF 69 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 F6 FF A4 04 00 00 36 08 CB 00 84 01 E7 02 E5 FF 98 04 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 04 00 F6 FF 8B 07 00 00 BB 07 38 00 E7 02 58 01 84 02 F3 04 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 F6 FF E3 08 00 00 D0 FD 9D FE 58 01 A0 03 D1 FF 81 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 06 00 F6 FF 83 0C 00 00 32 07 84 00 A0 03 72 00 FB FF 41 05 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 07 00 F6 FF F5 0C 00 00 8C 00 5F 06 72 00 7E 02 70 FD 6B 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 08 00 F6 FF 73 0F 00 00 F0 02 D4 02 7E 02 41 00 E0 FE D8 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 F7 FF B4 0F 00 00 3E 00 45 01 41 00 21 01 F9 FE 5A 00 00 00 00 00 00 00 3A 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 F7 FF D5 10 00 00 46 09 E0 09 21 01 2F 01 66 FF 34 01 00 00 00 00 00 00 00 00 
//01-30 23:24:08.090: I/ElectrocardiogramManager(15512): 上位机回复ACK-Data包

//02-04 22:02:33.085: I/ElectrocardiogramManager(6334): 正在连接设备
//02-04 22:02:34.025: I/ElectrocardiogramManager(6334): 连接设备成功
//02-04 22:02:34.035: I/ElectrocardiogramManager(6334): 等待HC201发送Hello-regularData包
//02-04 22:02:34.855: I/ElectrocardiogramManager(6334): HC201发送Hello-regularData包为：AA 01 01 55 
//02-04 22:02:34.855: I/ElectrocardiogramManager(6334): 上位机回复ACK-Hello-regularData包
//02-04 22:02:34.855: I/ElectrocardiogramManager(6334): 等待HC201发送TotalLength包
//02-04 22:02:34.920: I/ElectrocardiogramManager(6334): HC201发送TotalLength包为：AA 02 01 00 
//02-04 22:02:34.920: I/ElectrocardiogramManager(6334): 记录总数为：1
//02-04 22:02:34.920: I/ElectrocardiogramManager(6334): 上位机回复ACKTotalLength包
//02-04 22:02:34.920: I/ElectrocardiogramManager(6334): 等待HC201Data-Regular包
//02-04 22:02:34.980: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=1,packageLength=500
//02-04 22:02:35.070: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:35.110: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=2,packageLength=500
//02-04 22:02:35.195: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:35.240: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=3,packageLength=500
//02-04 22:02:35.330: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:35.370: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=4,packageLength=500
//02-04 22:02:35.480: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:35.515: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=5,packageLength=500
//02-04 22:02:35.610: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:35.650: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=6,packageLength=500
//02-04 22:02:35.735: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:35.770: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=7,packageLength=500
//02-04 22:02:35.875: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:35.910: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=8,packageLength=52
//02-04 22:02:35.920: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:35.955: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=9,packageLength=500
//02-04 22:02:36.045: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:36.085: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=10,packageLength=500
//02-04 22:02:36.170: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:36.215: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=11,packageLength=500
//02-04 22:02:36.290: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:36.330: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=12,packageLength=500
//02-04 22:02:36.415: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:36.450: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=13,packageLength=500
//02-04 22:02:36.535: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:36.570: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=14,packageLength=500
//02-04 22:02:36.665: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:36.700: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=15,packageLength=500
//02-04 22:02:36.795: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:36.835: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=16,packageLength=500
//02-04 22:02:36.955: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:37.000: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=17,packageLength=500
//02-04 22:02:37.080: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:37.120: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=18,packageLength=500
//02-04 22:02:37.220: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:37.260: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=19,packageLength=500
//02-04 22:02:37.340: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:37.380: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=20,packageLength=500
//02-04 22:02:37.465: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:37.505: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=21,packageLength=500
//02-04 22:02:37.615: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:37.655: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=22,packageLength=500
//02-04 22:02:37.740: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:37.800: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=23,packageLength=500
//02-04 22:02:37.855: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:37.895: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=24,packageLength=500
//02-04 22:02:37.980: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:38.035: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=25,packageLength=500
//02-04 22:02:38.130: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:38.165: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=26,packageLength=500
//02-04 22:02:38.250: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:38.290: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=27,packageLength=500
//02-04 22:02:38.375: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:38.415: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=28,packageLength=500
//02-04 22:02:38.505: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:38.545: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=29,packageLength=500
//02-04 22:02:38.630: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:38.665: I/ElectrocardiogramManager(6334): recordIndex=1,packageCount=30,packageIndex=30,packageLength=300
//02-04 22:02:38.710: I/ElectrocardiogramManager(6334): 上位机回复本次ACK-Data包
//02-04 22:02:38.950: I/ElectrocardiogramManager(6334): 第1条记录,大小14352,数据为：0F 02 04 15 39 0B 83 31 0F 00 01 00 00 00 A0 02 00 00 00 00 00 00 00 00 00 00 00 00 00 40 1E 64 00 00 00 00 7A 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 F7 FF 96 03 00 00 F7 09 9B 07 1C 01 A8 00 5C 02 4A 0B 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00 F7 FF 3E 04 00 00 65 09 68 08 A8 00 EA 00 FD 00 08 0E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 F7 FF 28 05 00 00 40 08 42 09 EA 00 1D 01 FE FE 0B 0A 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 04 00 F7 FF 45 06 00 00 6B 07 C9 08 1D 01 B7 00 A2 FE C4 0A 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 F7 FF FC 06 00 00 16 0A 1C 09 B7 00 96 00 FA 00 74 0F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 06 00 F7 FF 92 07 00 00 27 08 37 08 96 00 64 00 F0 FF 87 11 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 07 00 F7 FF F6 07 00 00 62 07 61 08 64 00 4E 00 01 FF A9 0F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 08 00 F7 FF 44 08 00 00 5C 07 24 00 4E 00 74 00 38 07 C4 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 F7 FF B8 08 00 00 73 09 6A 08 74 00 E6 00 09 01 22 0D 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 F6 FF 9E 09 00 00 01 09 D2 00 E6 00 D8 06 21 00 8B 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0B 00 F6 FF 76 10 00 00 B8 07 2D 01 D8 06 EF 01 85 FF 52 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 F7 FF 65 12 00 00 10 04 49 03 EF 01 87 00 C7 00 F3 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0D 00 F7 FF EC 12 00 00 61 01 A3 01 87 00 C0 01 BE FF 20 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 F7 FF AC 14 00 00 D0 00 75 03 76 00 45 00 5B FD 7B 05 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F 00 F6 FF 6C 08 00 00 CB 01 00 00 45 00 19 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 00 F6 FF F1 10 00 00 1F 05 00 00 19 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 11 00 F6 FF F2 10 00 00 27 05 00 00 01 00 02 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 12 00 F7 FF F4 11 00 00 13 03 4F 06 02 01 5B 00 C4 FC 77 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 F6 FF 4F 12 00 00 AE 03 00 00 5B 00 75 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 14 00 00 00 C5 14 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 
//02-04 22:02:38.950: I/ElectrocardiogramManager(6334): 数据读取完毕。。。

/**
 * 
 * @ClassName: ElectrocardiogramManager
 * @Description: 心电仪(HC201)
 * @author junbin.he
 * @date 2015年1月30日 上午9:56:31
 *
 */
public class ElectrocardiogramManager {
	private static final String TAG = ElectrocardiogramManager.class.getSimpleName();
	private Log log = new Log("bluetoothDevice");

	// 01-30 23:09:52.265: I/ElectrocardiogramManager(14237):
	// Name=ECG:HC-201B,address=30:14:09:03:15:10

	private static final String DEVICE_NAME = "ECG:HC-201B";// 心电仪设备名称(HC201)

	private Activity mActivity;

	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothConnector mBluetoothConnector = null;
	private BluetoothSocketWrapper mBluetoothSocketWrapper = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;

	private String mTipsInfo;

	public ElectrocardiogramManager(Activity activity) {
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
			showTips(R.string.device_idcard_no_bonded_devices);
			return false;
		}

		BluetoothDevice adaptedDevice = null;
		for (Iterator<BluetoothDevice> iterator = pairedDevices.iterator(); iterator.hasNext();) {
			BluetoothDevice device = (BluetoothDevice) iterator.next();
			log.i(TAG, "Name=" + device.getName() + ",address=" + device.getAddress());
			if (DEVICE_NAME.equals(device.getName())) {
				adaptedDevice = device;
				break;
			}
		}

		// 2、没有合适的配对设备
		if (adaptedDevice == null) {
			showTips(R.string.device_idcard_no_bonded_devices);
			return false;
		}

		// 3、蓝牙设备没有打开时打开设备
		if (!mBluetoothAdapter.isEnabled()) {
			mBluetoothAdapter.enable();
		}
		// 4、创建蓝牙Socket
		try {
			mBluetoothConnector = new BluetoothConnector(adaptedDevice, mBluetoothAdapter);
			log.i(TAG, "正在连接设备");
			// 5、连接读卡器设备
			mBluetoothSocketWrapper = mBluetoothConnector.connect();
			log.i(TAG, "连接设备成功");
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

	public Electrocardiogram readData() {
		Electrocardiogram electrocardiogram = null;
		try {

			// 1、----------------------HC201发送Hello-regularData包，等待上位机回复。----------------------
			if (!requestHelloRegularData()) {
				showTips(R.string.device_connect_exception);
				return electrocardiogram;
			}

			// 2、----------------------HC201发送TotalLength包，等待上位机回复----------------------
			int recordCount = requestTotalLength();
			if (recordCount <= 0) {
				showTips(R.string.device_connect_exception);
				return electrocardiogram;
			}

			// 3、----------------------HC201发送Data-Regular包（重复）----------------------
			// （其中包含：这是第几条心电数据，这条心电数据有多少个数据包，此次发送是第几个数据包，这个数据包的长度是多少byte）
			// 0xaa (0)
			// 0x03(结构体)或者0x04(数据)(1)
			//
			// 第几条心电数据低8bit(2)
			// 第几条心电数据高8bit(3)
			//
			// 本条数据有多少数据包低8bit(4)
			// 本条数据有多少数据包高8bit(5)
			//
			// 本次是第几个数据包低8bit(6)
			// 本次是第几个数据包高8bit(7)
			//
			// 本次数据传输长度length低8bit
			// 本次数据传输长度length高8bit
			//
			// PlayLoad....
			//
			log.i(TAG, "等待HC201Data-Regular包");
			int recordIndex = 0;
			DecimalFormat df = new DecimalFormat("0.0");
			long currentRecordDate = 0;
			while (recordCount > 0) {
				ByteArrayOutputStream itemArrayOutputStream = new ByteArrayOutputStream();
				while (true) {

					// 每条数据包的读取
					// 1、读取前10个字节，获取当前数据包的长度
					if (!readSectionData(10)) {
						showTips(R.string.device_connect_exception);
						return electrocardiogram;
					}

					byte[] headerData = cacheData.toByteArray();
					
					// 第几条心电数据
					recordIndex = getShort(headerData, 2);
					// 本条数据有多少个数据包
					int packageCount = getShort(headerData, 4);
					// 本次是第几个数据包
					int packageIndex = getShort(headerData, 6);
					// 本次数据传输长度
					int packageLength = getShort(headerData, 8);
					log.i(TAG, "recordIndex=" + recordIndex + ",packageCount=" + packageCount + ",packageIndex=" + packageIndex + ",packageLength=" + packageLength);
					// 读取本次数据
					if (!readSectionData(packageLength)) {
						showTips(R.string.device_connect_exception);
						return electrocardiogram;
					}
					// 将数据写到itemData中
					itemArrayOutputStream.write(cacheData.toByteArray());
					itemArrayOutputStream.flush();

					log.i(TAG, "上位机回复本次ACK-Data包");
					byte[] data = { 0x55, headerData[1], headerData[6], headerData[7], 0x0a };
					mOutputStream.write(data);
					mOutputStream.flush();

					if (packageIndex >= packageCount) {
						break;
					}
				}

				byte[] itemData = itemArrayOutputStream.toByteArray();
				log.i(TAG, "第" + recordIndex + "条记录,大小" + itemData.length + ",数据为：" + HexBinary.bytesToHexStringPrint(itemData));
				 if (itemData.length != 14352) {
					electrocardiogram = null;
					showTips(R.string.device_read_data_exception);
					return electrocardiogram;
				}
				//解析当前记录的数据
				electrocardiogram = new Electrocardiogram();
				
				// Year(1byte) :year=08代表2008
				String year = formatYear(itemData[0]);
				// Month(1byte) :month=10表示十月
				String month = formatDate(itemData[1]);
				// Day(1byte) :
				String day = formatDate(itemData[2]);
				// Hour(1byte) :
				String hour = formatDate(itemData[3]);
				// Minute(1byte) :
				String minute = formatDate(itemData[4]);
				// Second(1byte) :
				String second = formatDate(itemData[5]);
				String date = year + "年" + month + "月" + day + "日 " + hour + "时" + minute + "分" + second + "秒";
				
				long dateLong = DateUtils.parseDate(date, "yyyy年MM月dd日 HH时mm分ss秒").getTime();
				log.i(TAG,date);
				if(dateLong <= currentRecordDate) {
					continue;
				} else {
					currentRecordDate = dateLong;
				}
				electrocardiogram.setDate(date);
				// UserID(1byte) :用户ID,0-3
				int userId = getInt(itemData[6]);
				log.i(TAG,"userId=" + userId);
				// isRight(1byte) :波形判断结果
				byte isRight = itemData[7];// 00110001
				// Bit7-6 :表示这段数据的ST段是否正常,0-正常，1-ST段抬高，2-ST段压低
				int STStatus = (isRight & 0xc0) >> 6;// 11000000
				electrocardiogram.setSTStatus(STStatus);
				log.i(TAG,"ST段是否正常,0-正常，1-ST段抬高，2-ST段压低=" + STStatus);
				// Bit5 :0-没有发现心律失常，-1心律失常
				int heartRhythmStatus = getInt(isRight, 5);
				electrocardiogram.setHeartRhythmStatus(heartRhythmStatus);
				log.i(TAG,"0-没有发现心律失常，1-心律失常=" + heartRhythmStatus);
				// Bit4 :0-波形质量正常，1-波形质量过差
				int waveformQuality = getInt(isRight, 4);
				electrocardiogram.setWaveformQuality(waveformQuality);
				log.i(TAG,"0-波形质量正常，1-波形质量过差=" + waveformQuality);
				// Bit3-Bit1
				// :000(0)-心率正常，001(1)-心率稍慢，010(2)-心律过慢，011(3)-心律稍快，100(4)-心律过快，101(5)-导连脱落
				String heartRateStatus = getInt(isRight, 3) + "" + getInt(isRight, 2) + "" + getInt(isRight, 1);
				electrocardiogram.setHeartRateStatus(heartRateStatus);
				log.i(TAG,"000(0)-心率正常，001(1)-心率稍慢，010(2)-心律过慢，011(3)-心律稍快，100(4)-心律过快，101(5)-导连脱落=" + heartRateStatus);
				// Bit0 :整体波形是否正常，如果bit5-bit1代表的意义都正常，此位为0，否则为1
				int wholeWaveform = getInt(isRight, 0);
				electrocardiogram.setWholeWaveform(wholeWaveform);
				log.i(TAG,"整体波形是否正常，如果bit5-bit1代表的意义都正常，此位为0，否则为1=" + wholeWaveform);

				// Howmany(1byte) :这个数组表中有多少个R波被检测到，从1开始计数
				int howmany = getInt(itemData[8]);
				electrocardiogram.setHowmany(howmany);
				log.i(TAG,"有多少个R波被检测到，从1开始计数=" + howmany);
				// unused(2byte) :不用管，这个是下位机在Flash中标定删除
				int unused = getShort(itemData, 9);
				log.i(TAG,"不用管，这个是下位机在Flash中标定删除=" + unused);

				// 0F 02 04 15 39 0B 83 31(7) 0F(8) 00(9) 01(10) 00(11) 00(12) 00(13)
				// A0(14) 02(15)
				// HowmanySuccess(1byte) ：从1开始计数，多少个波形成功分析
				int howmanySuccess = getShort(itemData, 11);
				electrocardiogram.setHowmanySuccess(howmanySuccess);
				log.i(TAG,"从1开始计数，多少个波形成功分析=" + howmanySuccess);
				// AVR_HeartRate(2byte) :此次心电记录的心率信息，如：600表示心率60,605表示心率60.5
				float AVR_HeartRate = getShort(itemData, 14) * 0.1F;
				electrocardiogram.setHeartRate(AVR_HeartRate);
				log.i(TAG,"此次心电记录的心率信息=" + df.format(AVR_HeartRate));

				// AVR_PR(2byte) :此次检测全局的PR间期
				int AVR_PR = getShort(itemData, 16) * 2;
				electrocardiogram.setPROfAVR(AVR_PR);
				log.i(TAG,"此次检测全局的PR间期=" + AVR_PR);

				// AVR_QT(2byte) :此次检测的平均QT间期
				int AVR_QT = getShort(itemData, 18) * 2;
				electrocardiogram.setQTOfAVR(AVR_QT);
				log.i(TAG,"此次检测的平均QT间期=" + AVR_QT);

				// AVR_Rvolt(2byte) :此次检测的平均R波电压 (l16?)
				float AVR_Rvolt = getShort(itemData, 20) * 0.00099F;
				electrocardiogram.setRvoltOfAVR(AVR_Rvolt);
				log.i(TAG,"此次检测的平均R波电压 =" + df.format(AVR_Rvolt));

				// AVR_Pvolt(2byte) :此次检测的平均p波电压
				float AVR_Pvolt = getShort(itemData, 22) * 0.00099F;
				electrocardiogram.setPvoltOfAVR(AVR_Pvolt);
				log.i(TAG,"此次检测的平均p波电压=" + df.format(AVR_Pvolt));

				// AVR_Tvolt(2byte) :此次检测平均t波电压
				float AVR_Tvolt = getShort(itemData, 24) * 0.00099F;
				electrocardiogram.setTvoltOfAVR(AVR_Tvolt);
				log.i(TAG,"此次检测平均t波电压=" + df.format(AVR_Tvolt));

				// AVR_STvolt(2byte) :ST段平均电压
				float AVR_STvolt = getShort(itemData, 26) * 0.00099F;
				electrocardiogram.setSTvoltOfAVR(AVR_STvolt);
				log.i(TAG,"ST段平均电压=" + df.format(AVR_STvolt));
				
				
				ArrayList<Integer> points = new ArrayList<Integer>();
				// 心电数据起始位置
				int ecgIndex = 3552;
				int dataIndex = 0;
				// 采集点总数
				for (int i = 0; i < 5400; i++) {
					dataIndex = ecgIndex + i * 2;
					// 读取采集点
					short data = getShort(itemData, dataIndex);
					points.add((int)data);
				}
				electrocardiogram.setPoints(points);
				recordCount--;
			}
			log.i(TAG, "数据读取完毕。。。");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return electrocardiogram;
	}

	private int getInt(byte b, int index) {
		return (b & (0x01 << index)) >> index;
	}

	public int getInt(byte b) {
		return b & 0xff;
	}

	public String formatYear(byte b) {
		int year = getInt(b);
		if (year < 10) {
			return "200" + year;
		}
		return "20" + year;
	}

	public String formatDate(byte b) {
		int date = getInt(b);
		if (date < 10) {
			return "0" + date;
		}
		return date + "";
	}

	private boolean requestHelloRegularData() throws IOException {
		// 请求的数据为：0xaa,0x01,0x01,0x55
		log.i(TAG, "等待HC201发送Hello-regularData包");
		// 1.1、读取Hello-regularData包
		if (!readSectionData(4)) {
			return false;
		}
		log.i(TAG, "HC201发送Hello-regularData包为：" + HexBinary.bytesToHexStringPrint(cacheData.toByteArray()));

		// 1.2、核对数据包是否正确
		byte[] helloRegularData = cacheData.toByteArray();
		if (helloRegularData[0] != (byte) 0xaa || helloRegularData[1] != (byte) 0x01 || helloRegularData[2] != (byte) 0x01 || helloRegularData[3] != (byte) 0x55) {
			return false;
		}

		// 1.3、上位机回复ACK-Hello-regularData包
		// 发送数据为：0x55,0x01,0x01,0xaa,0x0a
		log.i(TAG, "上位机回复ACK-Hello-regularData包");
		byte[] helloRegularResponse = { 0x55, 0x01, 0x01, (byte) 0xaa, 0x0a };
		mOutputStream.write(helloRegularResponse);
		mOutputStream.flush();
		return true;
	}

	private int requestTotalLength() throws IOException {
		int recordCount = -1;
		// 请求的数据为：0xaa,0x02,LowByte(总长度的低8bit),HighByte(总长度的高8bit)
		log.i(TAG, "等待HC201发送TotalLength包");

		// 2.1、读取TotalLength包
		if (!readSectionData(4)) {
			return recordCount;
		}
		log.i(TAG, "HC201发送TotalLength包为：" + HexBinary.bytesToHexStringPrint(cacheData.toByteArray()));

		// 2.2、核对数据包是否正确
		byte[] totalLengthData = cacheData.toByteArray();
		if (totalLengthData[0] != (byte) 0xaa || totalLengthData[1] != (byte) 0x02) {
			showTips(R.string.device_connect_exception);
			return recordCount;
		}
		// 2.3、获取记录总数
		recordCount = getShort(totalLengthData, 2);
		log.i(TAG, "记录总数为：" + recordCount);

		// 2.4、上位机回复ACKTotalLength包
		// 发送数据为：0x55,0x02,0x00,0x00,0x0a
		log.i(TAG, "上位机回复ACKTotalLength包");
		byte[] totalLengthResponse = { 0x55, 0x02, 0x00, 0x00, 0x0a };
		mOutputStream.write(totalLengthResponse);
		mOutputStream.flush();
		return recordCount;
	}

	private ByteArrayOutputStream cacheData = null;

	private boolean readSectionData(int totalLen) {
		byte[] buffer = new byte[totalLen];
		cacheData = new ByteArrayOutputStream(totalLen);
		int currentLen = 0;
		while (currentLen < totalLen) {
			int len = 0;
			try {
				len = mInputStream.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			if (len < 0) {
				return false;
			}
			cacheData.write(buffer, 0, len);
			currentLen += len;
			if (currentLen < totalLen) {
				buffer = new byte[totalLen - currentLen];
			}
		}
		return true;
	}

	/**
	 * 通过byte数组取到short
	 * 
	 * @param b
	 * @param index
	 *            第几位开始取
	 * @return
	 */
	public short getShort(byte[] b, int index) {
		return (short) (((b[index + 0] << 8) | b[index + 1] & 0xff));
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
