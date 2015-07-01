

import java.text.DecimalFormat;

import com.shbestwin.followupmanager.common.util.HexBinary;

//常规测量数据是分析结果和心电波形数据集成的一个文件。
//前边 3552byte 为此段数据的分析结果
//从3553byte开始后的10800个byte为心电波形数据
//数据波形都是经过前期平滑滤波和高低滤波器之后的结果，可以直接展示到屏幕上（当然有可能需要做放缩或者抽取）
//心电数据格式为16bit的有符号整形变量，也就是2byte为一个数据，低位在前。
//与实际电压之间的比例关系为1mv对应数据1010，例如，某一个常规测量数据结构体中AVR_Rvolt（R波形的平均电压）字段为2020，表示这次常规数据所有的R波的平均高度为2mV
//如测量的波形数据显示，如果电脑屏幕上1mV需要对应5mm的高度，那么在实际绘画的时候需要调整波形数据值在1010的时候对应屏幕5mm高度。
//手持心电图测量仪的采样率为250HZ,也就是每秒中产生250个数据点（每个点2个byte，0.5KB/s）,例如屏幕绘画时走纸速度要求25mm每秒，而25mm在屏幕上某个分辨率下对应了100个像素点，那么满足这个
//25mm每秒的走纸速度需要采取将波形文件的每250个数据抽取100个数据的方式来满足这种对应关系，以满足时间的需求。
public class Test9 {
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("0.0");
		
		String str = "0F 02 04 15 39 0B 83 31 0F 00 01 00 00 00 A0 02 00 00 00 00 00 00 00 00 00 00 00 00 00 40 1E 64 00 00 00 00 7A 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 F7 FF 96 03 00 00 F7 09 9B 07 1C 01 A8 00 5C 02 4A 0B 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00 F7 FF 3E 04 00 00 65 09 68 08 A8 00 EA 00 FD 00 08 0E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 F7 FF 28 05 00 00 40 08 42 09 EA 00 1D 01 FE FE 0B 0A 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 04 00 F7 FF 45 06 00 00 6B 07 C9 08 1D 01 B7 00 A2 FE C4 0A 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 F7 FF FC 06 00 00 16 0A 1C 09 B7 00 96 00 FA 00 74 0F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 06 00 F7 FF 92 07 00 00 27 08 37 08 96 00 64 00 F0 FF 87 11 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 07 00 F7 FF F6 07 00 00 62 07 61 08 64 00 4E 00 01 FF A9 0F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 08 00 F7 FF 44 08 00 00 5C 07 24 00 4E 00 74 00 38 07 C4 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 F7 FF B8 08 00 00 73 09 6A 08 74 00 E6 00 09 01 22 0D 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 F6 FF 9E 09 00 00 01 09 D2 00 E6 00 D8 06 21 00 8B 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0B 00 F6 FF 76 10 00 00 B8 07 2D 01 D8 06 EF 01 85 FF 52 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 F7 FF 65 12 00 00 10 04 49 03 EF 01 87 00 C7 00 F3 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0D 00 F7 FF EC 12 00 00 61 01 A3 01 87 00 C0 01 BE FF 20 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 F7 FF AC 14 00 00 D0 00 75 03 76 00 45 00 5B FD 7B 05 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F 00 F6 FF 6C 08 00 00 CB 01 00 00 45 00 19 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 00 F6 FF F1 10 00 00 1F 05 00 00 19 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 11 00 F6 FF F2 10 00 00 27 05 00 00 01 00 02 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 12 00 F7 FF F4 11 00 00 13 03 4F 06 02 01 5B 00 C4 FC 77 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 F6 FF 4F 12 00 00 AE 03 00 00 5B 00 75 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 14 00 00 00 C5 14 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ";
		byte[] buffer = HexBinary.encode(str.replaceAll(" ", ""));
		System.out.println(buffer.length);
		// Year(1byte) :year=08代表2008
		String year = formatYear(buffer[0]);
		// Month(1byte) :month=10表示十月
		String month = formatDate(buffer[1]);
		// Day(1byte) :
		String day = formatDate(buffer[2]);
		// Hour(1byte) :
		String hour = formatDate(buffer[3]);
		// Minute(1byte) :
		String minute = formatDate(buffer[4]);
		// Second(1byte) :
		String second = formatDate(buffer[5]);
		System.out.println(year + "年" + month + "月" + day + "日 " + hour + "时" + minute + "分" + second + "秒");
		// UserID(1byte) :用户ID,0-3
		int userId = getInt(buffer[6]);
		System.out.println("userId=" + userId);
		// isRight(1byte) :波形判断结果
		byte isRight = buffer[7];//00110001
		// Bit7-6 :表示这段数据的ST段是否正常,0-正常，1-ST段抬高，2-ST段压低
		int STStatus = (isRight & 0xc0) >> 6;// 11000000
		System.out.println("STStatus=" + STStatus);
		// Bit5 :0-没有发现心律失常，-心律失常
		int heartRhythmStatus = getInt(isRight, 5);
		System.out.println("heartRhythmStatus=" + heartRhythmStatus);
		// Bit4 :0-波形质量正常，1-波形质量过差
		int waveformQuality = getInt(isRight, 4);
		System.out.println("waveformQuality=" + waveformQuality);
		// Bit3-Bit1 :000(0)-心率正常，001(1)-心率稍慢，010(2)-心律过慢，011(3)-心律稍快，100(4)-心律过快，101(5)-导连脱落
		String heartRateStatus = getInt(isRight, 3)+""+getInt(isRight,2)+""+getInt(isRight, 1);
		System.out.println("heartRateStatus="+heartRateStatus);
		// Bit0 :整体波形是否正常，如果bit5-bit1代表的意义都正常，此位为0，否则为1
		int wholeWaveform = getInt(isRight,0);
		System.out.println("wholeWaveform="+wholeWaveform);
		
		// Howmany(1byte) :这个数组表中有多少个R波被检测到，从1开始计数
		int howmany = getInt(buffer[8]);
		System.out.println("howmany="+howmany);
		// unused(2byte) :不用管，这个是下位机在Flash中标定删除
		int unused = getShort(buffer, 9);
		System.out.println("unused="+unused);
		
		//0F 02 04 15 39 0B 83 31(7) 0F(8) 00(9) 01(10) 00(11) 00(12) 00(13) A0(14) 02(15)
		// HowmanySuccess(1byte) ：从1开始计数，多少个波形成功分析
		int howmanySuccess = getShort(buffer, 11);
		System.out.println("howmanySuccess="+howmanySuccess);
		// AVR_HeartRate(2byte) :此次心电记录的心率信息，如：600表示心率60,605表示心率60.5
		float AVR_HeartRate = getShort(buffer, 13) * 0.1F;
		System.out.println("AVR_HeartRate="+df.format(AVR_HeartRate));
		
		// AVR_PR(2byte) :此次检测全局的PR间期
		float AVR_PR = getShort(buffer, 15) * 0.1F;
		System.out.println("AVR_PR="+df.format(AVR_PR));
		
		// AVR_QT(2byte) :此次检测的平均QT间期
		float AVR_QT = getShort(buffer, 17) * 0.1F;
		System.out.println("AVR_QT="+df.format(AVR_QT));
		
		// AVR_Rvolt(2byte) :此次检测的平均R波电压 (l16?)
		float AVR_Rvolt = getShort(buffer, 19) * 0.1F;
		System.out.println("AVR_Rvolt="+df.format(AVR_Rvolt));
		
		// AVR_Pvolt(2byte) :此次检测的平均p波电压
		float AVR_Pvolt = getShort(buffer, 21) * 0.1F;
		System.out.println("AVR_Pvolt="+df.format(AVR_Pvolt));
		
		// AVR_Tvolt(2byte) :此次检测平均t波电压
		float AVR_Tvolt = getShort(buffer, 23) * 0.1F;
		System.out.println("AVR_Tvolt="+df.format(AVR_Tvolt));
		
		// AVR_STvolt(2byte) :ST段平均电压
		float AVR_STvolt = getShort(buffer, 25) * 0.1F;
		System.out.println("AVR_STvolt="+df.format(AVR_STvolt));
	}

	private static int getInt(byte b, int index) {
		return (b & (0x01 << index)) >> index;
	}

	/**
	 * 转换short为byte
	 * 
	 * @param s
	 *            需要转换的short
	 * @param index
	 */
	public static byte[] putShort(short s, int index) {
		byte[] b = new byte[2];
		b[index + 1] = (byte) (s >> 8);
		b[index + 0] = (byte) (s >> 0);
		return b;
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

	public static String formatYear(byte b) {
		int year = getInt(b);
		if (year < 10) {
			return "200" + year;
		}
		return "20" + year;
	}

	public static String formatDate(byte b) {
		int date = getInt(b);
		if (date < 10) {
			return "0" + date;
		}
		return date + "";
	}
}
