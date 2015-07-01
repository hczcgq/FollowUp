package com.shbestwin.followupmanager.manager;

import java.io.File;

import android.content.Context;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.common.util.FileUtils;
import com.shbestwin.followupmanager.model.MedicationInfo;

public class MedicationGuideManager {
	private static MedicationGuideManager instance = null;
	private Context mContext = null;

	private MedicationGuideManager(Context applicContext) {
		mContext = applicContext;
	}

	public static synchronized MedicationGuideManager getInstance(Context context) {
		if (instance == null) {
			synchronized (MedicationGuideManager.class) {
				if (instance == null) {
					instance = new MedicationGuideManager(context.getApplicationContext());
				}
			}
		}
		return instance;
	}

	/**
	 * 
	 * @Title: getMedicationList
	 * @Description: 获取药物列表
	 * @param @return
	 * @return List<String>
	 * @throws
	 */
	public String[] getMedicationList() {
		String dirPath = MyApplication.getInstance().getResourceDir() + "yyzd" + File.separator;
		File dirFile = new File(dirPath);
		if (dirFile.exists() && dirFile.isDirectory()) {
			return dirFile.list();
		}
		return null;
	}

	public MedicationInfo getMedicationInfo(String medicationName) {
		String dirPath = MyApplication.getInstance().getResourceDir() + "yyzd" + File.separator + medicationName + File.separator;
		System.out.println("Path:"+dirPath);
		File dirFile = new File(dirPath);
		if (dirFile.exists() && dirFile.isDirectory()) {
			MedicationInfo medicationInfo = new MedicationInfo();
			// 1、获取药理学
			String filePath = dirPath + "药理学.txt";
			medicationInfo.setPharmacology(FileUtils.readFile(filePath));
			
			// 2、获取适用症
			filePath = dirPath + "适应证.txt";
			medicationInfo.setIndications(FileUtils.readFile(filePath));
			
			// 3、获取禁忌症
			filePath = dirPath + "禁忌证.txt";
			medicationInfo.setContraindications(FileUtils.readFile(filePath));
			
			// 4、获取不良反应
			filePath = dirPath + "不良反应.txt";
			medicationInfo.setAdverseReactions(FileUtils.readFile(filePath));
			
			// 5、获取注意事项
			filePath = dirPath + "注意事项.txt";
			medicationInfo.setNotes(FileUtils.readFile(filePath));
			
			// 6、获取相互作用
			filePath = dirPath + "药物相互作用.txt";
			medicationInfo.setInteraction(FileUtils.readFile(filePath));
			return medicationInfo;
		}
		return null;
	}
}
