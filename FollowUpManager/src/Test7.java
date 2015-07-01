

import java.text.DecimalFormat;
import java.util.Locale;

import com.shbestwin.followupmanager.common.util.HexBinary;

//关于脂肪秤的数据结构
//Bit7=1为男性，Bit7=0为女性，Bit6-Bit0:年龄
//如果此字节的数据为0x9E，即10011110
//那么：
//性别为女
//年龄为01001111，即79岁
//是这样吗？

public class Test7 {
	// 总字节数:372

	// FF(0) :0xFF
	// 01(1) :数据总长度高字节
	// 74(2) :数据总长度低字节
	// 00 00 01(3-5) ：产品序列号
	// 01(6) :用户编号（1-用户1,2=用户2，。。。。）

	// 第1组数据
	// 07 DC 01 01 00 02 03 9E A0 01
	// 02 68 00 E9 18 01 A4 0B 01 94
	// 00 F1 02 30 05 42

	// 第2组数据
	// 07 DC 01 01 02 1F 39 9E A0 01
	// 02 4E 00 F1 17 01 A0 05 02 84
	// 00 E7 02 2A 05 3E

	// 第3组数据
	// 07 DC 01 01 02 24 11 A2 AB 01
	// 02 4D 00 BC 19 01 BD 03 02 85
	// 00 CA 02 50 05 5A

	// 第4组数据
	// 07 DC 01 01 02 26 05 A2 AB 01
	// 02 4C 00 BE 19 01 BC 03 02 99
	// 00 C9 02 4F 05 5A

	// 第5组数据
	// 07 DC 01 01 03 07 03 1B 9C 01
	// 01 C5 00 A0 14 01 CC 02 02 92
	// 00 BA 02 65 04 EB

	// 第6组数据
	// 07 DC 01 08 04 0C 04 A2 AA 01
	// 02 52 00 C1 19 01 BA 03 02 58
	// 00 CE 02 4D 05 56

	// 第7组数据
	// 07 DC 01 08 04 0F 0D A2 AA 01
	// 02 54 00 C1 19 01 BA 03 02 51
	// 00 CF 02 4D 05 56

	// 第8组数据
	// 07 DC 01 11 09 10 31 9B AA 01
	// 02 1D 00 8A 18 01 D8 01 02 5B
	// 00 BB 02 75 05 7E

	// 第9组数据
	// 07 DC 02 02 01 16 23 9B AA 01
	// 02 10 00 88 18 01 DA 01 02 B2
	// 00 B7 02 77 05 7C

	// 第10组数据
	// 07 DC 02 02 01 19 0F 9B AA 01
	// 02 0F 00 86 18 01 DA 01 02 AC
	// 00 B7 02 78 05 7C

	// 第11组数据
	// 07 DC 02 02 01 1A 1A 9B AA 01
	// 02 10 00 88 18 01 DA 01 02 B2
	// 00 B7 02 77 05 7C

	// 第12组数据
	// 07 DC 02 0C 0C 29 01 9B AA 01
	// 02 19 00 7F 18 01 DF 01 02 1E
	// 00 BA 02 7E 05 7E

	// 第13组数据
	// 07 DC 02 0C 0C 30 1B 9B AA 01
	// 02 1E 00 83 18 01 DC 01 02 1D
	// 00 BC 02 7A 05 7E

	// 第14组数据
	// 07 DC 02 0C 0D 06 3A 9B AA 01
	// 02 18 00 7F 18 01 DE 01 02 31
	// 00 B9 02 7D 05 7D

	// 52(BCC)

	// 单组数据
	// 07(0):年高字节
	// DC(1):年第字节
	// 01(2):月
	// 01(3):日
	// 00(4):时
	// 02(5):分
	// 03(6):秒
	// 9E(7):Bit7=1为男性，Bit7=0为女性，Bit6-Bit0:年龄
	// A0(8):身高（cm）
	// 01(9):运动级别（1=非运动员，2-业余运动员，3=专业运动员）
	// 02(10):体重高字节
	// 68(11):体重低字节
	// 00(12):脂肪高字节
	// E9(13):脂肪低字节
	// 18(14):骨量
	// 01(15):肌肉高字节
	// A4(16):肌肉低字节
	// 0B(17):内脏脂肪
	// 01(18):人体阻抗值高字节
	// 94(19):人体阻抗值低字节
	// 00(20):BMI高字节
	// F1(21):BMI低字节
	// 02(22):水分高字节
	// 30(23):水分低字节
	// 05(24):KCAL值高字节
	// 42(25):KCAL值低字节
	public static void main(String[] args) {
		String str = "FF 01 74 00 00 01 01 07 DC 01 01 00 02 03 9E A0 01 02 68 00 E9 18 01 A4 0B 01 94 00 F1 02 30 05 42 07 DC 01 01 02 1F 39 9E A0 01 02 4E 00 F1 17 01 A0 05 02 84 00 E7 02 2A 05 3E 07 DC 01 01 02 24 11 A2 AB 01 02 4D 00 BC 19 01 BD 03 02 85 00 CA 02 50 05 5A 07 DC 01 01 02 26 05 A2 AB 01 02 4C 00 BE 19 01 BC 03 02 99 00 C9 02 4F 05 5A 07 DC 01 01 03 07 03 1B 9C 01 01 C5 00 A0 14 01 CC 02 02 92 00 BA 02 65 04 EB 07 DC 01 08 04 0C 04 A2 AA 01 02 52 00 C1 19 01 BA 03 02 58 00 CE 02 4D 05 56 07 DC 01 08 04 0F 0D A2 AA 01 02 54 00 C1 19 01 BA 03 02 51 00 CF 02 4D 05 56 07 DC 01 11 09 10 31 9B AA 01 02 1D 00 8A 18 01 D8 01 02 5B 00 BB 02 75 05 7E 07 DC 02 02 01 16 23 9B AA 01 02 10 00 88 18 01 DA 01 02 B2 00 B7 02 77 05 7C 07 DC 02 02 01 19 0F 9B AA 01 02 0F 00 86 18 01 DA 01 02 AC 00 B7 02 78 05 7C 07 DC 02 02 01 1A 1A 9B AA 01 02 10 00 88 18 01 DA 01 02 B2 00 B7 02 77 05 7C 07 DC 02 0C 0C 29 01 9B AA 01 02 19 00 7F 18 01 DF 01 02 1E 00 BA 02 7E 05 7E 07 DC 02 0C 0C 30 1B 9B AA 01 02 1E 00 83 18 01 DC 01 02 1D 00 BC 02 7A 05 7E 07 DC 02 0C 0D 06 3A 9B AA 01 02 18 00 7F 18 01 DE 01 02 31 00 B9 02 7D 05 7D 52";
		// System.out.println( ByteBuffer.wrap(new byte[] { 0x01, 0x74
		// }).getShort());

		// System.out.println(getShort(a, 0));
		// System.out.println(bytesToHexString(putShort((short) 372, 0)));

		byte[] bytes = HexBinary.encode(str.replaceAll(" ", ""));
		short length = getShort(bytes, 1);
		System.out.println("length=" + length);
		// 8+26
		if (length >= 34) {// 表示至少包含一组数据
			int index = 1;
			for (int i = 7; i < length - 1; i += 26) {
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
				String date = getShort(bytes, i) + "年" + getInt(bytes[i + 2]) + "月" + getInt(bytes[i + 3]) + "日 " + getInt(bytes[i + 4]) + "时" + getInt(bytes[i + 5]) + "分" + getInt(bytes[i + 6]) + "秒";
				// 9E(7):Bit7=1为男性，Bit7=0为女性，Bit6-Bit0:年龄
				int age = bytes[i + 7] & 0x7f;
				String gendar = "";
				if ((bytes[i + 7] & 0x80) == 0x00) {// 女性
					gendar = "女";
				} else {
					gendar = "男";

				}

				// A0(8):身高（cm）
				int height = getInt(bytes[i + 8]);
				// 01(9):运动级别（1=非运动员，2-业余运动员，3=专业运动员）
				int sportLevel = getInt(bytes[i + 9]);
				// 02(10):体重高字节
				// 68(11):体重低字节
				float weight = getShort(bytes, i + 10) * 0.1F;
				// 00(12):脂肪高字节
				// E9(13):脂肪低字节
				float fat = getShort(bytes, i + 12) * 0.1F;
				// 18(14):骨量
				float boneMass = getInt(bytes[i + 14]) * 0.1F;
				// 01(15):肌肉高字节
				// A4(16):肌肉低字节
				float muscle = getShort(bytes, i + 15) * 0.1F;
				// 0B(17):内脏脂肪
				int visceralFat = getInt(bytes[i + 17]);
				// 01(18):人体阻抗值高字节
				// 94(19):人体阻抗值低字节
				int bodyImpedance = getShort(bytes, i + 18);
				// 00(20):BMI高字节
				// F1(21):BMI低字节
				float BMI = getShort(bytes, i + 20) * 0.1F;
				// 02(22):水分高字节
				// 30(23):水分低字节
				float bodyWater = getShort(bytes, i + 22) * 0.1F;
				// 05(24):KCAL值高字节
				// 42(25):KCAL值低字节
				int KCAL = getShort(bytes, i + 24);

				DecimalFormat df = new DecimalFormat("0.0");
				System.out.println("第" + index + "组数据:date=" + date + ",gendar=" + gendar + ",age=" + age + ",height=" + height + ",sportLevel=" + sportLevel + ",weight=" + df.format(weight) + ",fat=" + df.format(fat) + "%,boneMass="
						+ boneMass + ",muscle=" + muscle + "%,visceralFat=" + visceralFat + ",bodyImpedance=" + bodyImpedance + ",BMI=" + BMI + ",bodyWater=" + bodyWater + "%,KCAL=" + KCAL);
				index++;
			}
		}
		// System.out.println(HexBinary.decode(bytes));
	}

	public static int bytesToInt(byte[] b, int index) {
		int i = 0;
		i |= (b[index + 0] << 24) & 0xFF000000;
		i |= (b[index + 1] << 16) & 0xFF0000;
		return i;
	}

	public static final String bytesToHexString(byte[] bArray, int start, int len) {
		StringBuffer sb = new StringBuffer();
		String sTemp;
		for (int i = start; i < len; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase(Locale.CHINA));
		}
		return sb.toString();
	}

	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString((int) (0xFF & bArray[i]));
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase(Locale.CHINA));
		}
		return sb.toString();
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
		b[index + 0] = (byte) (s >> 8);
		b[index + 1] = (byte) (s >> 0);
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

	public static double getDouble(byte[] b, int index) {
		return (b[index + 0] & 0xff) + 0.1 * (b[index + 1] & 0xff);
	}
}
