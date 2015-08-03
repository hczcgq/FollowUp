package com.shbestwin.followupmanager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Application;
import android.os.Environment;
import android.text.TextUtils;

import com.shbestwin.followupmanager.common.log.Log;
import com.shbestwin.followupmanager.common.util.ActivityStackHelper;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.manager.AccompanyManager;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.manager.FollowUpManager;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;
import com.shbestwin.followupmanager.model.followup.FollowUpDisabledPerson;
import com.shbestwin.followupmanager.model.followup.FollowUpFirstPregnancy;
import com.shbestwin.followupmanager.model.followup.FollowUpFortyTwo;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;
import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;
import com.shbestwin.followupmanager.model.followup.FollowUpNewborn;
import com.shbestwin.followupmanager.model.followup.FollowUpOneNewborn;
import com.shbestwin.followupmanager.model.followup.FollowUpOneTwoNewborn;
import com.shbestwin.followupmanager.model.followup.FollowUpPostpartum;
import com.shbestwin.followupmanager.model.followup.FollowUpStroke;
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;
import com.shbestwin.followupmanager.model.followup.FollowUpTwoToFivePregnancy;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;

public class MyApplication extends Application {
	
//	public static String serverUrl="http://180.153.53.247:82/ylms";
	public static String serverUrl="http://183.247.164.200/ylms";
	
	private static MyApplication mInstance = null;

	private ArchiveInfo archiveInfo;

	private ExaminationInfo examinationInfo;// 体检信息

	private GeneralExamination generalExamination;// 普通体检信息

	private String followUpNo;// 随访编号

	private FollowUpHypertension followUpHypertension;// 高血压
	private FollowUpDiabetesMellitus followUpDiabetesMellitus;// 糖尿病
	private FollowUpStroke followUpStroke;// 脑卒中
	private FollowUpMentalDisease followUpMentalDisease;// 精神病

	private FollowUpFirstPregnancy followUpFirstPregnancy;// 孕产访视（第1次产前）
	private FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy;// 孕产访视（第2-5次产前）
	private FollowUpPostpartum followUpPostpartum;// 孕产访视（产后访视）
	private FollowUpFortyTwo followUpFortyTwo;// 孕产访视（42天检查）

	private FollowUpNewborn followUpNewborn;// 儿童访视： 新生儿
	private FollowUpOneNewborn followUpOneNewborn;// 儿童访视： 1岁内
	private FollowUpOneTwoNewborn followUpOneTwoNewborn;// 儿童访视： 1-2岁
	private FollowUpThreeSixNewborn followUpThreeSixNewborn;// 儿童访视： 3-6岁

	private FollowUpAged followUpAged;// 老年随访
	private FollowUpDisabledPerson followUpDisabledPerson;// 残疾随访
	


	public static MyApplication getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		initLog();
		initCrash();
//		initTest();
	}

	private void initTest() {
		archiveInfo = new ArchiveInfo();
		archiveInfo.setIdcard("441481198709283116");
		archiveInfo.setName("hejunbin");
		archiveInfo.setBirthday("1987-09-28");
		archiveInfo.setGender("男");
	}

	public void setArchiveInfo(ArchiveInfo archiveInfo) {
		this.archiveInfo = archiveInfo;
		if(archiveInfo!=null){
			if (TextUtils.isEmpty(this.archiveInfo.getCreateDate())) {
				this.archiveInfo.setCreateDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
			}
		}
	}

	public ArchiveInfo getArchiveInfo() {
		return archiveInfo;
	}

	public ExaminationInfo getExaminationInfo() {
		if (examinationInfo == null && archiveInfo != null) {
			examinationInfo = ExaminationManager.getInstance(this).getExaminationInfoByIdcard(archiveInfo.getIdcard());
		}
		return examinationInfo;
	}

	public void setExaminationInfo(ExaminationInfo examinationInfo) {
		this.examinationInfo = examinationInfo;
	}

	public GeneralExamination getGeneralExamination() {
		if (generalExamination == null) {
			ExaminationInfo examinationInfo = getExaminationInfo();
			if (examinationInfo != null) {
				generalExamination = ExaminationManager.getInstance(this).getGeneralExaminationById(examinationInfo.getExaminationNo());
			}
		}
		return generalExamination;
	}

	public void setGeneralExamination(GeneralExamination generalExamination) {
		this.generalExamination = generalExamination;
	}

	public String getFollowUpNo() {
		return followUpNo;
	}

	public void setFollowUpNo(String followUpNo) {
		this.followUpNo = followUpNo;
		// 重新清空随访管理的相关数据
		followUpHypertension = null;// 高血压
		followUpDiabetesMellitus = null;// 糖尿病
		followUpStroke = null;// 脑卒中
		followUpMentalDisease = null;// 精神病

		followUpFirstPregnancy = null;// 孕产访视（第1次产前）
		followUpTwoToFivePregnancy = null;// 孕产访视（第2-5次产前）
		followUpPostpartum = null;// 孕产访视（产后访视）
		followUpFortyTwo = null;// 孕产访视（42天检查）

		followUpNewborn = null;// 儿童访视： 新生儿
		followUpOneNewborn = null;// 儿童访视： 1岁内
		followUpOneTwoNewborn = null;// 儿童访视： 1-2岁
		followUpThreeSixNewborn = null;// 儿童访视： 3-6岁

		followUpAged = null;// 老年随访
		followUpDisabledPerson = null;// 残疾随访
	}

	public FollowUpHypertension getFollowUpHypertension() {
		if (followUpHypertension == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpHypertension = FollowUpManager.getInstance(this).getFollowUpHypertensionByIdcard(archiveInfo.getIdcard());
			} else {
				followUpHypertension = FollowUpManager.getInstance(this).getFollowUpHypertension(followUpNo);
			}

		}
		return followUpHypertension;
	}

	public void setFollowUpHypertension(FollowUpHypertension followUpHypertension) {
		this.followUpHypertension = followUpHypertension;
	}

	public FollowUpDiabetesMellitus getFollowUpDiabetesMellitus() {
		if (followUpDiabetesMellitus == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpDiabetesMellitus = FollowUpManager.getInstance(this).getFollowUpDiabetesMellitusByIdcard(archiveInfo.getIdcard());
			} else {
				followUpDiabetesMellitus = FollowUpManager.getInstance(this).getFollowUpDiabetesMellitus(followUpNo);
			}

		}
		return followUpDiabetesMellitus;
	}

	public void setFollowUpDiabetesMellitus(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		this.followUpDiabetesMellitus = followUpDiabetesMellitus;
	}

	public FollowUpStroke getFollowUpStroke() {
		if (followUpStroke == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpStroke = FollowUpManager.getInstance(this).getFollowUpStrokeByIdcard(archiveInfo.getIdcard());
			} else {
				followUpStroke = FollowUpManager.getInstance(this).getFollowUpStroke(followUpNo);
			}

		}
		return followUpStroke;
	}

	public void setFollowUpStroke(FollowUpStroke followUpStroke) {
		this.followUpStroke = followUpStroke;
	}

	public FollowUpMentalDisease getFollowUpMentalDisease() {
		if (followUpMentalDisease == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpMentalDisease = FollowUpManager.getInstance(this).getFollowUpMentalDiseaseByIdcard(archiveInfo.getIdcard());
			} else {
				followUpMentalDisease = FollowUpManager.getInstance(this).getFollowUpMentalDisease(followUpNo);
			}

		}
		return followUpMentalDisease;
	}

	public void setFollowUpMentalDisease(FollowUpMentalDisease followUpMentalDisease) {
		this.followUpMentalDisease = followUpMentalDisease;
	}

	public FollowUpFirstPregnancy getFollowUpFirstPregnancy() {
		if (followUpFirstPregnancy == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpFirstPregnancy = FollowUpManager.getInstance(this).getFollowUpFirstPregnancyByIdcard(archiveInfo.getIdcard());
			} else {
				followUpFirstPregnancy = FollowUpManager.getInstance(this).getFollowUpFirstPregnancy(followUpNo);
			}

		}
		return followUpFirstPregnancy;
	}

	public void setFollowUpFirstPregnancy(FollowUpFirstPregnancy followUpFirstPregnancy) {
		this.followUpFirstPregnancy = followUpFirstPregnancy;
	}

	public FollowUpTwoToFivePregnancy getFollowUpTwoToFivePregnancy() {
		if (followUpTwoToFivePregnancy == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpTwoToFivePregnancy = FollowUpManager.getInstance(this).getFollowUpTwoToFivePregnancyByIdcard(archiveInfo.getIdcard());
			} else {
				followUpTwoToFivePregnancy = FollowUpManager.getInstance(this).getFollowUpTwoToFivePregnancy(followUpNo);
			}

		}
		return followUpTwoToFivePregnancy;
	}

	public void setFollowUpTwoToFivePregnancy(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
		this.followUpTwoToFivePregnancy = followUpTwoToFivePregnancy;
	}

	public FollowUpPostpartum getFollowUpPostpartum() {
		if (followUpPostpartum == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpPostpartum = FollowUpManager.getInstance(this).getFollowUpPostpartumByIdcard(archiveInfo.getIdcard());
			} else {
				followUpPostpartum = FollowUpManager.getInstance(this).getFollowUpPostpartum(followUpNo);
			}

		}
		return followUpPostpartum;
	}

	public void setFollowUpPostpartum(FollowUpPostpartum followUpPostpartum) {
		this.followUpPostpartum = followUpPostpartum;
	}

	public FollowUpFortyTwo getFollowUpFortyTwo() {
		if (followUpFortyTwo == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpFortyTwo = FollowUpManager.getInstance(this).getFollowUpFortyTwoByIdcard(archiveInfo.getIdcard());
			} else {
				followUpFortyTwo = FollowUpManager.getInstance(this).getFollowUpFortyTwo(followUpNo);
			}

		}
		return followUpFortyTwo;
	}

	public void setFollowUpFortyTwo(FollowUpFortyTwo followUpFortyTwo) {
		this.followUpFortyTwo = followUpFortyTwo;
	}

	public FollowUpNewborn getFollowUpNewborn() {
		if (followUpNewborn == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpNewborn = FollowUpManager.getInstance(this).getFollowUpNewbornByIdcard(archiveInfo.getIdcard());
			} else {
				followUpNewborn = FollowUpManager.getInstance(this).getFollowUpNewborn(followUpNo);
			}

		}
		return followUpNewborn;
	}

	public void setFollowUpNewborn(FollowUpNewborn followUpNewborn) {
		this.followUpNewborn = followUpNewborn;
	}

	public FollowUpOneNewborn getFollowUpOneNewborn() {
		if (followUpOneNewborn == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpOneNewborn = FollowUpManager.getInstance(this).getFollowUpOneNewbornByIdcard(archiveInfo.getIdcard());
			} else {
				followUpOneNewborn = FollowUpManager.getInstance(this).getFollowUpOneNewborn(followUpNo);
			}

		}
		return followUpOneNewborn;
	}

	public void setFollowUpOneNewborn(FollowUpOneNewborn followUpOneNewborn) {
		this.followUpOneNewborn = followUpOneNewborn;
	}

	public FollowUpOneTwoNewborn getFollowUpOneTwoNewborn() {
		if (followUpOneTwoNewborn == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpOneTwoNewborn = FollowUpManager.getInstance(this).getFollowUpOneTwoNewbornByIdcard(archiveInfo.getIdcard());
			} else {
				followUpOneTwoNewborn = FollowUpManager.getInstance(this).getFollowUpOneTwoNewborn(followUpNo);
			}

		}
		return followUpOneTwoNewborn;
	}

	public void setFollowUpOneTwoNewborn(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		this.followUpOneTwoNewborn = followUpOneTwoNewborn;
	}

	public FollowUpThreeSixNewborn getFollowUpThreeSixNewborn() {
		if (followUpThreeSixNewborn == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpThreeSixNewborn = FollowUpManager.getInstance(this).getFollowUpThreeSixNewbornByIdcard(archiveInfo.getIdcard());
			} else {
				followUpThreeSixNewborn = FollowUpManager.getInstance(this).getFollowUpThreeSixNewborn(followUpNo);
			}

		}
		return followUpThreeSixNewborn;
	}

	public void setFollowUpThreeSixNewborn(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		this.followUpThreeSixNewborn = followUpThreeSixNewborn;
	}

	public FollowUpAged getFollowUpAged() {
		if (followUpAged == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpAged = FollowUpManager.getInstance(this).getFollowUpAgedByIdcard(archiveInfo.getIdcard());
			} else {
				followUpAged = FollowUpManager.getInstance(this).getFollowUpAged(followUpNo);
			}

		}
		return followUpAged;
	}

	public void setFollowUpAged(FollowUpAged followUpAged) {
		this.followUpAged = followUpAged;
	}

	public FollowUpDisabledPerson getFollowUpDisabledPerson() {
		if (followUpDisabledPerson == null) {
			if (TextUtils.isEmpty(followUpNo) && archiveInfo != null) {
				// 根据idcard加载最新一条数据
				followUpDisabledPerson = FollowUpManager.getInstance(this).getFollowUpDisabledPersonByIdcard(archiveInfo.getIdcard());
			} else {
				followUpDisabledPerson = FollowUpManager.getInstance(this).getFollowUpDisabledPerson(followUpNo);
			}

		}
		return followUpDisabledPerson;
	}

	public void setFollowUpDisabledPerson(FollowUpDisabledPerson followUpDisabledPerson) {
		this.followUpDisabledPerson = followUpDisabledPerson;
	}
	
	
	
	public Accompany getAccompany(int tag) {
		if (archiveInfo != null) {
			return  AccompanyManager.getInstance(this).getAccompanyById(new SimpleDateFormat("yyyyMMdd").format(new Date())+archiveInfo.getIdcard()+tag);
		}
		return null;
	}
	

	/**
	 * 
	 * @Title: initLog
	 * @Description: 初始化log
	 * @param
	 * @return void
	 * @throws
	 */
	private void initLog() {
		Log.isLogToConsole(true);
		Log.isLogToFile(false);
		Log.setLogPath("shbestwin/FollowUpManager/log");
	}

	/**
	 * 
	 * @Title: initCrash
	 * @Description: 初始化异常信息
	 * @param
	 * @return void
	 * @throws
	 */
	private void initCrash() {
		String appId = getString(R.string.bugly_app_id);
		boolean isDebug = true; // true代表App处于调试阶段，false代表App发布阶段
		UserStrategy strategy = new UserStrategy(getApplicationContext()); // App的策略Bean
		strategy.setAppVersion("FollowUpManager_" + SystemUtils.getVersionName(getApplicationContext()));// App的版本
		CrashReport.initCrashReport(getApplicationContext(), appId, isDebug, strategy); // 初始化SDK
	}

	public String getAppDir() {
		String dir = Environment.getExternalStorageDirectory().getPath() + File.separator + getPackageName() + File.separator;
		return dir;
	}

	public String getResourceDir() {
		String dir = getAppDir() + "resource" + File.separator;
		return dir;
	}

	public void exitApp() {
		ActivityStackHelper.getInstance().clear();
	}
}
