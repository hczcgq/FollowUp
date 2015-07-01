import com.shbestwin.followupmanager.model.device.BloodPressure;



public class Test4 {
	public static void main(String[] args) {
		// String str =
		// "BB 05 22 01 14 0C 01 01 08 0A 00 80 45 4B 09 BB 05 22 02 14 0C 01 01 08 1F 00 6F 47 45 FC BB 05 22 03 14 0F 01 02 15 0C 00 76 44 4F E3 BB 05 22 04 14 0F 01 02 15 04 00 78 42 3E 95 BB 05 22 05 14 0C 01 08 05 2C 00 80 60 6D 2C BB 05 22 06 14 0C 01 08 05 2B 00 8A 5E 6A 1B BB 05 22 07 14 0C 01 08 05 2A 00 79 5F 69 EA BB 05 22 08 14 0C 01 01 08 1D 00 86 60 4A 35 BB 05 22 09 14 0C 01 01 08 05 00 78 58 72 D2 BB 05 22 0A 14 0C 01 01 08 04 00 79 59 66 C4 BB 05 22 0B 14 0C 01 01 08 03 00 75 4F 6A D4 BB 05 22 0C 14 0C 01 01 08 02 00 73 4E 59 E6 BB 05 22 0D 14 0C 01 01 08 01 00 74 4D 5D E4 BB 05 22 0E 14 0C 01 01 08 01 00 C7 95 50 81 BB 05 22 0F 14 0C 01 01 08 00 00 78 4E 50 E5";
		// System.out.println(str.replaceAll(" ", ""));
		String data = "BB052201140C0101080A0080454B09BB052202140C0101081F006F4745FCBB052203140F0102150C0076444FE3BB052204140F010215040078423E95BB052205140C0108052C0080606D2CBB052206140C0108052B008A5E6A1BBB052207140C0108052A00795F69EABB052208140C0101081D0086604A35BB052209140C0101080500785872D2BB05220A140C0101080400795966C4BB05220B140C0101080300754F6AD4BB05220C140C0101080200734E59E6BB05220D140C0101080100744D5DE4BB05220E140C0101080100C7955081BB05220F140C0101080000784E50E5";
		parseData(data);
	}

	// BB(1)
	// 05(2)
	// 22(3)
	// 01(4)
	// 14(5) :年高字节
	// 0C(6) :年低字节
	// 01(7) :月（字节最高位表示单位0：mmHg 1：kpa）
	// 01(8) :日
	// 08(9) :时
	// 0A(10) :分
	// 00(11) :高压高字节（字节最高位 1：心律不齐 0：正常）
	// 80(12) :高压低字节
	// 45(13) :低压
	// 4B(14) :脉博
	// 09(15) :BCC
	private static BloodPressure parseData(String data) {
		if (!data.substring(0, 2).equalsIgnoreCase("BB")) {
			return null;
		}
		BloodPressure bloodPressure = new BloodPressure();
		int index = 1;
		for (int i = 0; i < data.length(); i += 30) {
			// 获取收缩压的值
			String shousuo = data.substring(i + 22, i + 24);
			int gao = Integer.parseInt(shousuo, 16);

			String shuzhang = data.substring(i + 24, i + 26);
			int di = Integer.parseInt(shuzhang, 16);

			// 获取舒张压的值
			String maibo = data.substring(i + 26, i + 28);
			int mai = Integer.parseInt(maibo, 16);

			String date = format(Integer.parseInt(data.substring(i + 8, i + 10), 16)) + "" + format(Integer.parseInt(data.substring(i + 10, i + 12), 16)) + "年" + format(Integer.parseInt(data.substring(i + 12, i + 14), 16)) + "月"
					+ format(Integer.parseInt(data.substring(i + 14, i + 16), 16)) + "日 " + format(Integer.parseInt(data.substring(i + 16, i + 18), 16)) + ":" + format(Integer.parseInt(data.substring(i + 18, i + 20), 16));
			// System.out.println("shousuo=" + shousuo + ",shuzhang=" + shuzhang
			// + ",maibo=" + maibo + ",date=" + data.substring(i + 8, i + 10) +
			// data.substring(i + 10, i + 12));
			System.out.println("index=" + index + ",gao=" + gao + ",di=" + di + ",mai=" + mai + ",date=" + date);
			index++;
		}
		return bloodPressure;
	}

	private static String format(int number) {
		if (number < 10) {
			return "0" + number;
		}
		return "" + number;
	}
	
//	index=1,gao=128,di=69,mai=75,date=2012年01月01日 08:10
//	index=2,gao=111,di=71,mai=69,date=2012年01月01日 08:31
//	index=3,gao=118,di=68,mai=79,date=2015年01月02日 21:12
//	index=4,gao=120,di=66,mai=62,date=2015年01月02日 21:04
//	index=5,gao=128,di=96,mai=109,date=2012年01月08日 05:44
//	index=6,gao=138,di=94,mai=106,date=2012年01月08日 05:43
//	index=7,gao=121,di=95,mai=105,date=2012年01月08日 05:42
//	index=8,gao=134,di=96,mai=74,date=2012年01月01日 08:29
//	index=9,gao=120,di=88,mai=114,date=2012年01月01日 08:05
//	index=10,gao=121,di=89,mai=102,date=2012年01月01日 08:04
//	index=11,gao=117,di=79,mai=106,date=2012年01月01日 08:03
//	index=12,gao=115,di=78,mai=89,date=2012年01月01日 08:02
//	index=13,gao=116,di=77,mai=93,date=2012年01月01日 08:01
//	index=14,gao=199,di=149,mai=80,date=2012年01月01日 08:01
//	index=15,gao=120,di=78,mai=80,date=2012年01月01日 08:00
	
//	01-29 22:02:15.260: I/BloodPressureManager(8145): index=1,gao=128,di=69,mai=75,date=2012年01月01日 08:10
//			01-29 22:02:15.260: I/BloodPressureManager(8145): index=2,gao=111,di=71,mai=69,date=2012年01月01日 08:31
//			01-29 22:02:15.260: I/BloodPressureManager(8145): index=3,gao=118,di=68,mai=79,date=2015年01月02日 21:12
//			01-29 22:02:15.260: I/BloodPressureManager(8145): index=4,gao=120,di=66,mai=62,date=2015年01月02日 21:04
//			01-29 22:02:15.265: I/BloodPressureManager(8145): index=5,gao=128,di=96,mai=109,date=2012年01月08日 05:44
//			01-29 22:02:15.265: I/BloodPressureManager(8145): index=6,gao=138,di=94,mai=106,date=2012年01月08日 05:43
//			01-29 22:02:15.270: I/BloodPressureManager(8145): index=7,gao=121,di=95,mai=105,date=2012年01月08日 05:42
//			01-29 22:02:15.270: I/BloodPressureManager(8145): index=8,gao=134,di=96,mai=74,date=2012年01月01日 08:29
//			01-29 22:02:15.270: I/BloodPressureManager(8145): index=9,gao=120,di=88,mai=114,date=2012年01月01日 08:05
//			01-29 22:02:15.275: I/BloodPressureManager(8145): index=10,gao=121,di=89,mai=102,date=2012年01月01日 08:04
//			01-29 22:02:15.275: I/BloodPressureManager(8145): index=11,gao=117,di=79,mai=106,date=2012年01月01日 08:03
//			01-29 22:02:15.280: I/BloodPressureManager(8145): index=12,gao=115,di=78,mai=89,date=2012年01月01日 08:02
//			01-29 22:02:15.280: I/BloodPressureManager(8145): index=13,gao=116,di=77,mai=93,date=2012年01月01日 08:01
//			01-29 22:02:15.280: I/BloodPressureManager(8145): index=14,gao=199,di=149,mai=80,date=2012年01月01日 08:01
//			01-29 22:02:15.285: I/BloodPressureManager(8145): index=15,gao=120,di=78,mai=80,date=2012年01月01日 08:00
//119 69 74
}

