package com.shbestwin.followupmanager.model.device;

import java.util.ArrayList;

public class Electrocardiogram {
	private String date;// 日期
	private int STStatus;// ST段是否正常,0-正常，1-ST段抬高，2-ST段压低
	private int heartRhythmStatus;// 0-没有发现心律失常，-心律失常
	private int waveformQuality;// 0-波形质量正常，1-波形质量过差
	private String heartRateStatus;// 000(0)-心率正常，001(1)-心率稍慢，010(2)-心律过慢，011(3)-心律稍快，100(4)-心律过快，101(5)-导连脱落
	private int wholeWaveform;// :整体波形是否正常，如果bit5-bit1代表的意义都正常，此位为0，否则为1
	private int howmany;// 有多少个R波被检测到，从1开始计数
	private int howmanySuccess;// 从1开始计数，多少个波形成功分析
	private float heartRate;// 心率
	private int PROfAVR;// 此次检测全局的PR间期
	private int QTOfAVR;// 此次检测的平均QT间期
	private float RvoltOfAVR;// 此次检测的平均R波电压
	private float PvoltOfAVR;// 此次检测的平均p波电压
	private float TvoltOfAVR;// 此次检测平均t波电压
	private float STvoltOfAVR;// ST段平均电压
	private ArrayList<Integer> points;// 心电图波形数据

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSTStatus() {
		return STStatus;
	}

	public void setSTStatus(int sTStatus) {
		STStatus = sTStatus;
	}

	public int getHeartRhythmStatus() {
		return heartRhythmStatus;
	}

	public void setHeartRhythmStatus(int heartRhythmStatus) {
		this.heartRhythmStatus = heartRhythmStatus;
	}

	public int getWaveformQuality() {
		return waveformQuality;
	}

	public void setWaveformQuality(int waveformQuality) {
		this.waveformQuality = waveformQuality;
	}

	public String getHeartRateStatus() {
		return heartRateStatus;
	}

	public void setHeartRateStatus(String heartRateStatus) {
		this.heartRateStatus = heartRateStatus;
	}

	public int getWholeWaveform() {
		return wholeWaveform;
	}

	public void setWholeWaveform(int wholeWaveform) {
		this.wholeWaveform = wholeWaveform;
	}

	public int getHowmany() {
		return howmany;
	}

	public void setHowmany(int howmany) {
		this.howmany = howmany;
	}

	public int getHowmanySuccess() {
		return howmanySuccess;
	}

	public void setHowmanySuccess(int howmanySuccess) {
		this.howmanySuccess = howmanySuccess;
	}

	public float getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(float heartRate) {
		this.heartRate = heartRate;
	}

	public int getPROfAVR() {
		return PROfAVR;
	}

	public void setPROfAVR(int pROfAVR) {
		PROfAVR = pROfAVR;
	}

	public int getQTOfAVR() {
		return QTOfAVR;
	}

	public void setQTOfAVR(int qTOfAVR) {
		QTOfAVR = qTOfAVR;
	}

	public float getRvoltOfAVR() {
		return RvoltOfAVR;
	}

	public void setRvoltOfAVR(float rvoltOfAVR) {
		RvoltOfAVR = rvoltOfAVR;
	}

	public float getPvoltOfAVR() {
		return PvoltOfAVR;
	}

	public void setPvoltOfAVR(float pvoltOfAVR) {
		PvoltOfAVR = pvoltOfAVR;
	}

	public float getTvoltOfAVR() {
		return TvoltOfAVR;
	}

	public void setTvoltOfAVR(float tvoltOfAVR) {
		TvoltOfAVR = tvoltOfAVR;
	}

	public float getSTvoltOfAVR() {
		return STvoltOfAVR;
	}

	public void setSTvoltOfAVR(float sTvoltOfAVR) {
		STvoltOfAVR = sTvoltOfAVR;
	}

	public ArrayList<Integer> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Integer> points) {
		this.points = points;
	}

	public String getConclusion() {
		StringBuilder result = new StringBuilder();
		// ST段是否正常,0-正常，1-ST段抬高，2-ST段压低
		if (STStatus == 0) {
			result.append("ST段正常");
		} else if (STStatus == 1) {
			result.append("ST段抬高");
		} else if (STStatus == 2) {
			result.append("ST段压低");
		}
		// 0-没有发现心律失常，-心律失常
		if (heartRhythmStatus == 0) {
			result.append("，没有发现心律失常");
		} else {
			result.append("，心律失常");
		}

		// 0-波形质量正常，1-波形质量过差
		if (waveformQuality == 0) {
			result.append("，波形质量正常");
		} else {
			result.append("，波形质量过差");
		}

		// 000(0)-心率正常，001(1)-心率稍慢，010(2)-心律过慢，011(3)-心律稍快，100(4)-心律过快，101(5)-导连脱落
		if (heartRateStatus.equals("000")) {
			result.append("，心率正常");
		} else if (heartRateStatus.equals("001")) {
			result.append("，心率稍慢");
		} else if (heartRateStatus.equals("010")) {
			result.append("，心律过慢");
		} else if (heartRateStatus.equals("011")) {
			result.append("，心律稍快");
		} else if (heartRateStatus.equals("100")) {
			result.append("，心律过快");
		} else if (heartRateStatus.equals("101")) {
			result.append("，导连脱落");
		}

		// :整体波形是否正常，如果bit5-bit1代表的意义都正常，此位为0，否则为1
		if (wholeWaveform == 0) {
			result.append("，整体波形正常");
		} else {
			result.append("，整体波形异常");
		}

		return result.toString();
	}

	@Override
	public String toString() {
		return "Electrocardiogram [date=" + date + ", STStatus=" + STStatus + ", heartRhythmStatus=" + heartRhythmStatus + ", waveformQuality=" + waveformQuality + ", heartRateStatus=" + heartRateStatus + ", wholeWaveform=" + wholeWaveform
				+ ", howmany=" + howmany + ", howmanySuccess=" + howmanySuccess + ", heartRate=" + heartRate + ", PROfAVR=" + PROfAVR + ", QTOfAVR=" + QTOfAVR + ", RvoltOfAVR=" + RvoltOfAVR + ", PvoltOfAVR=" + PvoltOfAVR + ", TvoltOfAVR="
				+ TvoltOfAVR + ", STvoltOfAVR=" + STvoltOfAVR + ", points=" + points + "]";
	}

}
