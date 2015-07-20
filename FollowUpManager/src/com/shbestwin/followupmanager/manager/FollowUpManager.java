package com.shbestwin.followupmanager.manager;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.shbestwin.followupmanager.common.util.CollectionUtils;
import com.shbestwin.followupmanager.dao.DaoMaster;
import com.shbestwin.followupmanager.dao.DaoMaster.DevOpenHelper;
import com.shbestwin.followupmanager.dao.DaoSession;
import com.shbestwin.followupmanager.dao.FollowUpAgedDao;
import com.shbestwin.followupmanager.dao.FollowUpDiabetesMellitusDao;
import com.shbestwin.followupmanager.dao.FollowUpDisabledPersonDao;
import com.shbestwin.followupmanager.dao.FollowUpFirstPregnancyDao;
import com.shbestwin.followupmanager.dao.FollowUpFortyTwoDao;
import com.shbestwin.followupmanager.dao.FollowUpHypertensionDao;
import com.shbestwin.followupmanager.dao.FollowUpMentalDiseaseDao;
import com.shbestwin.followupmanager.dao.FollowUpNewbornDao;
import com.shbestwin.followupmanager.dao.FollowUpOneNewbornDao;
import com.shbestwin.followupmanager.dao.FollowUpOneTwoNewbornDao;
import com.shbestwin.followupmanager.dao.FollowUpPostpartumDao;
import com.shbestwin.followupmanager.dao.FollowUpStrokeDao;
import com.shbestwin.followupmanager.dao.FollowUpThreeSixNewbornDao;
import com.shbestwin.followupmanager.dao.FollowUpTwoToFivePregnancyDao;
import com.shbestwin.followupmanager.dao.ReportDiabetesMellitusDao;
import com.shbestwin.followupmanager.dao.ReportHypertensionDao;
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
import com.shbestwin.followupmanager.model.report.ReportDiabetesMellitus;
import com.shbestwin.followupmanager.model.report.ReportHyoertension;

public class FollowUpManager {
	private static FollowUpManager instance = null;
	private Context mContext = null;

	private FollowUpHypertensionDao mFollowUpHypertensionDao;// 高血压
	private FollowUpDiabetesMellitusDao mFollowUpDiabetesMellitusDao;// 糖尿病
	private FollowUpStrokeDao mFollowUpStrokeDao;// 脑卒中
	private FollowUpMentalDiseaseDao mFollowUpMentalDiseaseDao;// 精神病

	private FollowUpFirstPregnancyDao mFollowUpFirstPregnancyDao;// 孕产访视（第1次产前）
	private FollowUpTwoToFivePregnancyDao mFollowUpTwoToFivePregnancyDao;// 孕产访视（第2-5次产前）
	private FollowUpPostpartumDao mFollowUpPostpartumDao;// 孕产访视（产后访视）
	private FollowUpFortyTwoDao mFollowUpFortyTwoDao;// 孕产访视（42天检查）

	private FollowUpNewbornDao mFollowUpNewbornDao;// 儿童访视： 新生儿
	private FollowUpOneNewbornDao mFollowUpOneNewbornDao;// 儿童访视： 1岁内
	private FollowUpOneTwoNewbornDao mFollowUpOneTwoNewbornDao;// 儿童访视： 1-2岁
	private FollowUpThreeSixNewbornDao mFollowUpThreeSixNewbornDao;// 儿童访视： 3-6岁

	private FollowUpAgedDao mFollowUpAgedDao;// 老年随访
	private FollowUpDisabledPersonDao mFollowUpDisabledPersonDao;// 残疾随访
	
	private ReportHypertensionDao mReportHypertensionDao; //高血压报卡
	private ReportDiabetesMellitusDao mReportDiabetesMellitusDao; //糖尿病报卡

	private FollowUpManager(Context applicContext) {
		mContext = applicContext;
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, null);
		SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster daoMaster = new DaoMaster(db);
		DaoSession daoSession = daoMaster.newSession();
		mFollowUpHypertensionDao = daoSession.getFollowUpHypertensionDao();// 高血压
		mFollowUpDiabetesMellitusDao = daoSession.getFollowUpDiabetesMellitusDao();// 糖尿病
		mFollowUpStrokeDao = daoSession.getFollowUpStrokeDao();// 脑卒中
		mFollowUpMentalDiseaseDao = daoSession.getFollowUpMentalDiseaseDao();// 精神病

		mFollowUpFirstPregnancyDao = daoSession.getFollowUpFirstPregnancyDao();// 孕产访视（第1次产前）
		mFollowUpTwoToFivePregnancyDao = daoSession.getFollowUpTwoToFivePregnancyDao();// 孕产访视（第2-5次产前）
		mFollowUpPostpartumDao = daoSession.getFollowUpPostpartumDao();// 孕产访视（产后访视）
		mFollowUpFortyTwoDao = daoSession.getFollowUpFortyTwoDao();// 孕产访视（42天检查）

		mFollowUpNewbornDao = daoSession.getFollowUpNewbornDao();// 儿童访视： 新生儿
		mFollowUpOneNewbornDao = daoSession.getFollowUpOneNewbornDao();// 儿童访视：
																		// 1岁内
		mFollowUpOneTwoNewbornDao = daoSession.getFollowUpOneTwoNewbornDao();// 儿童访视：
																				// 1-2岁
		mFollowUpThreeSixNewbornDao = daoSession.getFollowUpThreeSixNewbornDao();// 儿童访视：
																					// 3-6岁

		mFollowUpAgedDao = daoSession.getFollowUpAgedDao();// 老年随访
		mFollowUpDisabledPersonDao = daoSession.getFollowUpDisabledPersonDao();// 残疾随访
		
		
		mReportHypertensionDao=daoSession.getReportHypertensionDao(); //糖尿病报卡
		mReportDiabetesMellitusDao=daoSession.getReportDiabetesMellitusDao(); //糖尿病报卡
	}

	public static synchronized FollowUpManager getInstance(Context context) {
		if (instance == null) {
			synchronized (FollowUpManager.class) {
				if (instance == null) {
					instance = new FollowUpManager(context.getApplicationContext());
				}
			}
		}
		return instance;
	}

	public FollowUpHypertension getFollowUpHypertension(String followUpNo) {
		return mFollowUpHypertensionDao.load(followUpNo);
	}

	public FollowUpHypertension getFollowUpHypertensionByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpHypertensionDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpHypertensionDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpHypertension> followUpHypertensionList = mFollowUpHypertensionDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(followUpHypertensionList)) {
			return followUpHypertensionList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpHypertension(FollowUpHypertension followUpHypertension) {
		this.mFollowUpHypertensionDao.insertOrReplace(followUpHypertension);
	}

	public FollowUpDiabetesMellitus getFollowUpDiabetesMellitus(String followUpNo) {
		return mFollowUpDiabetesMellitusDao.load(followUpNo);
	}

	public FollowUpDiabetesMellitus getFollowUpDiabetesMellitusByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpDiabetesMellitusDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpDiabetesMellitusDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpDiabetesMellitus> FollowUpDiabetesMellitusList = mFollowUpDiabetesMellitusDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpDiabetesMellitusList)) {
			return FollowUpDiabetesMellitusList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpDiabetesMellitus(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		this.mFollowUpDiabetesMellitusDao.insertOrReplace(followUpDiabetesMellitus);
	}

	public FollowUpStroke getFollowUpStroke(String followUpNo) {
		return mFollowUpStrokeDao.load(followUpNo);
	}

	public FollowUpStroke getFollowUpStrokeByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpStrokeDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpStrokeDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpStroke> FollowUpStrokeList = mFollowUpStrokeDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpStrokeList)) {
			return FollowUpStrokeList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpStroke(FollowUpStroke followUpStroke) {
		this.mFollowUpStrokeDao.insertOrReplace(followUpStroke);
	}

	public FollowUpMentalDisease getFollowUpMentalDisease(String followUpNo) {
		return mFollowUpMentalDiseaseDao.load(followUpNo);
	}

	public FollowUpMentalDisease getFollowUpMentalDiseaseByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpMentalDiseaseDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpMentalDiseaseDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpMentalDisease> FollowUpMentalDiseaseList = mFollowUpMentalDiseaseDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpMentalDiseaseList)) {
			return FollowUpMentalDiseaseList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpMentalDisease(FollowUpMentalDisease followUpMentalDisease) {
		this.mFollowUpMentalDiseaseDao.insertOrReplace(followUpMentalDisease);
	}

	public FollowUpFirstPregnancy getFollowUpFirstPregnancy(String followUpNo) {
		return mFollowUpFirstPregnancyDao.load(followUpNo);
	}

	public FollowUpFirstPregnancy getFollowUpFirstPregnancyByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpFirstPregnancyDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpFirstPregnancyDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpFirstPregnancy> FollowUpFirstPregnancyList = mFollowUpFirstPregnancyDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpFirstPregnancyList)) {
			return FollowUpFirstPregnancyList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpFirstPregnancy(FollowUpFirstPregnancy followUpFirstPregnancy) {
		this.mFollowUpFirstPregnancyDao.insertOrReplace(followUpFirstPregnancy);
	}

	public FollowUpTwoToFivePregnancy getFollowUpTwoToFivePregnancy(String followUpNo) {
		return mFollowUpTwoToFivePregnancyDao.load(followUpNo);
	}

	public FollowUpTwoToFivePregnancy getFollowUpTwoToFivePregnancyByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpTwoToFivePregnancyDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpTwoToFivePregnancyDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpTwoToFivePregnancy> FollowUpTwoToFivePregnancyList = mFollowUpTwoToFivePregnancyDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpTwoToFivePregnancyList)) {
			return FollowUpTwoToFivePregnancyList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpTwoToFivePregnancy(FollowUpTwoToFivePregnancy followUpTwoToFivePregnancy) {
		this.mFollowUpTwoToFivePregnancyDao.insertOrReplace(followUpTwoToFivePregnancy);
	}

	public FollowUpPostpartum getFollowUpPostpartum(String followUpNo) {
		return mFollowUpPostpartumDao.load(followUpNo);
	}

	public FollowUpPostpartum getFollowUpPostpartumByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpPostpartumDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpPostpartumDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpPostpartum> FollowUpPostpartumList = mFollowUpPostpartumDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpPostpartumList)) {
			return FollowUpPostpartumList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpPostpartum(FollowUpPostpartum followUpPostpartum) {
		this.mFollowUpPostpartumDao.insertOrReplace(followUpPostpartum);
	}

	public FollowUpFortyTwo getFollowUpFortyTwo(String followUpNo) {
		return mFollowUpFortyTwoDao.load(followUpNo);
	}

	public FollowUpFortyTwo getFollowUpFortyTwoByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpFortyTwoDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpFortyTwoDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpFortyTwo> FollowUpFortyTwoList = mFollowUpFortyTwoDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpFortyTwoList)) {
			return FollowUpFortyTwoList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpFortyTwo(FollowUpFortyTwo followUpFortyTwo) {
		this.mFollowUpFortyTwoDao.insertOrReplace(followUpFortyTwo);
	}

	public FollowUpNewborn getFollowUpNewborn(String followUpNo) {
		return mFollowUpNewbornDao.load(followUpNo);
	}

	public FollowUpNewborn getFollowUpNewbornByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpNewbornDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpNewbornDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpNewborn> FollowUpNewbornList = mFollowUpNewbornDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpNewbornList)) {
			return FollowUpNewbornList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpNewborn(FollowUpNewborn followUpNewborn) {
		this.mFollowUpNewbornDao.insertOrReplace(followUpNewborn);
	}

	public FollowUpOneNewborn getFollowUpOneNewborn(String followUpNo) {
		return mFollowUpOneNewbornDao.load(followUpNo);
	}

	public FollowUpOneNewborn getFollowUpOneNewbornByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpOneNewbornDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpOneNewbornDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpOneNewborn> FollowUpOneNewbornList = mFollowUpOneNewbornDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpOneNewbornList)) {
			return FollowUpOneNewbornList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpOneNewborn(FollowUpOneNewborn followUpOneNewborn) {
		this.mFollowUpOneNewbornDao.insertOrReplace(followUpOneNewborn);
	}

	public FollowUpOneTwoNewborn getFollowUpOneTwoNewborn(String followUpNo) {
		return mFollowUpOneTwoNewbornDao.load(followUpNo);
	}

	public FollowUpOneTwoNewborn getFollowUpOneTwoNewbornByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpOneTwoNewbornDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpOneTwoNewbornDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpOneTwoNewborn> FollowUpOneTwoNewbornList = mFollowUpOneTwoNewbornDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpOneTwoNewbornList)) {
			return FollowUpOneTwoNewbornList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpOneTwoNewborn(FollowUpOneTwoNewborn followUpOneTwoNewborn) {
		this.mFollowUpOneTwoNewbornDao.insertOrReplace(followUpOneTwoNewborn);
	}

	public FollowUpThreeSixNewborn getFollowUpThreeSixNewborn(String followUpNo) {
		return mFollowUpThreeSixNewbornDao.load(followUpNo);
	}

	public FollowUpThreeSixNewborn getFollowUpThreeSixNewbornByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpThreeSixNewbornDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpThreeSixNewbornDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpThreeSixNewborn> FollowUpThreeSixNewbornList = mFollowUpThreeSixNewbornDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpThreeSixNewbornList)) {
			return FollowUpThreeSixNewbornList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpThreeSixNewborn(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		this.mFollowUpThreeSixNewbornDao.insertOrReplace(followUpThreeSixNewborn);
	}

	public FollowUpAged getFollowUpAged(String followUpNo) {
		return mFollowUpAgedDao.load(followUpNo);
	}

	public FollowUpAged getFollowUpAgedByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpAgedDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpAgedDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpAged> FollowUpAgedList = mFollowUpAgedDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpAgedList)) {
			return FollowUpAgedList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpAged(FollowUpAged followUpAged) {
		this.mFollowUpAgedDao.insertOrReplace(followUpAged);
	}

	public FollowUpDisabledPerson getFollowUpDisabledPerson(String followUpNo) {
		return mFollowUpDisabledPersonDao.load(followUpNo);
	}

	public FollowUpDisabledPerson getFollowUpDisabledPersonByIdcard(String idcard) {
		StringBuilder where = new StringBuilder(" where ");
		where.append(FollowUpDisabledPersonDao.Properties.Idcard.columnName).append("=?");
		where.append(" order by ").append(FollowUpDisabledPersonDao.Properties.CreateTime.columnName).append(" desc");
		List<FollowUpDisabledPerson> FollowUpDisabledPersonList = mFollowUpDisabledPersonDao.queryRaw(where.toString(), idcard);
		if (!CollectionUtils.isEmpty(FollowUpDisabledPersonList)) {
			return FollowUpDisabledPersonList.get(0);
		}
		return null;
	}

	public void saveOrUpdateFollowUpDisabledPerson(FollowUpDisabledPerson followUpDisabledPerson) {
		this.mFollowUpDisabledPersonDao.insertOrReplace(followUpDisabledPerson);
	}
	
	
	
	
	//报卡
	public ReportHyoertension getReportHyoertension(String followUpNo) {
        return mReportHypertensionDao.load(followUpNo);
    }

    public ReportHyoertension getReportHyoertensionByIdcard(String idcard) {
        StringBuilder where = new StringBuilder(" where ");
        where.append(ReportHypertensionDao.Properties.Idcard.columnName).append("=?");
        where.append(" order by ").append(ReportHypertensionDao.Properties.CreateTime.columnName).append(" desc");
        List<ReportHyoertension> reportHyoertensionsList = mReportHypertensionDao.queryRaw(where.toString(), idcard);
        if (!CollectionUtils.isEmpty(reportHyoertensionsList)) {
            return reportHyoertensionsList.get(0);
        }
        return null;
    }

    public void saveOrUpdateReportHyoertension(ReportHyoertension reportHyoertension) {
        this.mReportHypertensionDao.insertOrReplace(reportHyoertension);
    }
    
    
    public ReportDiabetesMellitus getReportDiabetesMellitus(String followUpNo) {
        return mReportDiabetesMellitusDao.load(followUpNo);
    }

    public ReportDiabetesMellitus getReportDiabetesMellitusByIdcard(String idcard) {
        StringBuilder where = new StringBuilder(" where ");
        where.append(ReportDiabetesMellitusDao.Properties.Idcard.columnName).append("=?");
        where.append(" order by ").append(ReportDiabetesMellitusDao.Properties.CreateTime.columnName).append(" desc");
        List<ReportDiabetesMellitus> reportDiabetesMellitusList = mReportDiabetesMellitusDao.queryRaw(where.toString(), idcard);
        if (!CollectionUtils.isEmpty(reportDiabetesMellitusList)) {
            return reportDiabetesMellitusList.get(0);
        }
        return null;
    }

    public void saveOrUpdateReportDiabetesMellitus(ReportDiabetesMellitus reportDiabetesMellitus) {
        this.mReportDiabetesMellitusDao.insertOrReplace(reportDiabetesMellitus);
    }

}
