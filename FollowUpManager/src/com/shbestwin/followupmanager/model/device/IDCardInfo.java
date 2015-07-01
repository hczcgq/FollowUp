package com.shbestwin.followupmanager.model.device;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

public class IDCardInfo {

	private String name;// 姓名
	private String gender;// 性别
	private String ethnic;// 民族
	private String birthday;// 出生日期
	private String address;// 地址
	private String idcard;// 身份号码
	private String authority;// 签发机关
	private String validDate;// 有效期限
	private String picturePath;// 图像

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			name = name.trim();
		}
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address != null) {
			address = address.trim();
		}
		this.address = address;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		if (authority != null) {
			authority = authority.trim();
		}
		this.authority = authority;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Bitmap getPicture() {
		if (TextUtils.isEmpty(picturePath)) {
			return null;
		}
		File file = new File(picturePath);
		if (!file.exists()) {
			return null;
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(picturePath);
			Bitmap bmp = BitmapFactory.decodeStream(fis);
			return bmp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "IDCardInfo [name=" + name + ", gender=" + gender + ", ethnic="
				+ ethnic + ", birthday=" + birthday + ", address=" + address
				+ ", idcard=" + idcard + ", authority=" + authority
				+ ", validDate=" + validDate + ", picturePath=" + picturePath
				+ "]";
	}

}
