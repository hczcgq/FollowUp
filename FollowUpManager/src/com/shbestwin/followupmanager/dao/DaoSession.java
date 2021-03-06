package com.shbestwin.followupmanager.dao;

import java.util.Map;

import android.database.sqlite.SQLiteDatabase;

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
import com.shbestwin.followupmanager.model.report.ReportDiabetesMellitus;
import com.shbestwin.followupmanager.model.report.ReportHyoertension;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig archiveInfoDaoConfig;
    private final DaoConfig generalExaminationDaoConfig;
    private final DaoConfig examinationInfoDaoConfig;
    private final DaoConfig followUpHypertensionDaoConfig;
    private final DaoConfig followUpDiabetesMellitusDaoConfig;
    private final DaoConfig followUpStrokeDaoConfig;
    private final DaoConfig followUpMentalDiseaseDaoConfig;
    private final DaoConfig followUpFirstPregnancyDaoConfig;
    private final DaoConfig followUpTwoToFivePregnancyDaoConfig;
    private final DaoConfig followUpPostpartumDaoConfig;
    private final DaoConfig followUpFortyTwoDaoConfig;
    private final DaoConfig followUpNewbornDaoConfig;
    private final DaoConfig followUpOneNewbornDaoConfig;
    private final DaoConfig followUpOneTwoNewbornDaoConfig;
    private final DaoConfig followUpThreeSixNewbornDaoConfig;
    private final DaoConfig followUpAgedDaoConfig;
    private final DaoConfig followUpDisabledPersonDaoConfig;
    private final DaoConfig reportHypertensionDaoConfig;
    private final DaoConfig reportDiabetesMellitusDaoConfig;
    private final DaoConfig accompanyDaoConfig;

    private final ArchiveInfoDao archiveInfoDao;
    private final GeneralExaminationDao generalExaminationDao;
    private final ExaminationInfoDao examinationInfoDao;
    private final FollowUpHypertensionDao followUpHypertensionDao;
    private final FollowUpDiabetesMellitusDao followUpDiabetesMellitusDao;
    private final FollowUpStrokeDao followUpStrokeDao;
    private final FollowUpMentalDiseaseDao followUpMentalDiseaseDao;
    private final FollowUpFirstPregnancyDao followUpFirstPregnancyDao;
    private final FollowUpTwoToFivePregnancyDao followUpTwoToFivePregnancyDao;
    private final FollowUpPostpartumDao followUpPostpartumDao;
    private final FollowUpFortyTwoDao followUpFortyTwoDao;
    private final FollowUpNewbornDao followUpNewbornDao;
    private final FollowUpOneNewbornDao followUpOneNewbornDao;
    private final FollowUpOneTwoNewbornDao followUpOneTwoNewbornDao;
    private final FollowUpThreeSixNewbornDao followUpThreeSixNewbornDao;
    private final FollowUpAgedDao followUpAgedDao;
    private final FollowUpDisabledPersonDao followUpDisabledPersonDao;
    private final ReportHypertensionDao reportHypertensionDao;
    private final ReportDiabetesMellitusDao reportDiabetesMellitusDao;
    private final AccompanyDao accompanyDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        archiveInfoDaoConfig = daoConfigMap.get(ArchiveInfoDao.class).clone();
        archiveInfoDaoConfig.initIdentityScope(type);

        generalExaminationDaoConfig = daoConfigMap.get(GeneralExaminationDao.class).clone();
        generalExaminationDaoConfig.initIdentityScope(type);

        examinationInfoDaoConfig = daoConfigMap.get(ExaminationInfoDao.class).clone();
        examinationInfoDaoConfig.initIdentityScope(type);

        followUpHypertensionDaoConfig = daoConfigMap.get(FollowUpHypertensionDao.class).clone();
        followUpHypertensionDaoConfig.initIdentityScope(type);

        followUpDiabetesMellitusDaoConfig = daoConfigMap.get(FollowUpDiabetesMellitusDao.class).clone();
        followUpDiabetesMellitusDaoConfig.initIdentityScope(type);

        followUpStrokeDaoConfig = daoConfigMap.get(FollowUpStrokeDao.class).clone();
        followUpStrokeDaoConfig.initIdentityScope(type);

        followUpMentalDiseaseDaoConfig = daoConfigMap.get(FollowUpMentalDiseaseDao.class).clone();
        followUpMentalDiseaseDaoConfig.initIdentityScope(type);

        followUpFirstPregnancyDaoConfig = daoConfigMap.get(FollowUpFirstPregnancyDao.class).clone();
        followUpFirstPregnancyDaoConfig.initIdentityScope(type);

        followUpTwoToFivePregnancyDaoConfig = daoConfigMap.get(FollowUpTwoToFivePregnancyDao.class).clone();
        followUpTwoToFivePregnancyDaoConfig.initIdentityScope(type);

        followUpPostpartumDaoConfig = daoConfigMap.get(FollowUpPostpartumDao.class).clone();
        followUpPostpartumDaoConfig.initIdentityScope(type);

        followUpFortyTwoDaoConfig = daoConfigMap.get(FollowUpFortyTwoDao.class).clone();
        followUpFortyTwoDaoConfig.initIdentityScope(type);

        followUpNewbornDaoConfig = daoConfigMap.get(FollowUpNewbornDao.class).clone();
        followUpNewbornDaoConfig.initIdentityScope(type);

        followUpOneNewbornDaoConfig = daoConfigMap.get(FollowUpOneNewbornDao.class).clone();
        followUpOneNewbornDaoConfig.initIdentityScope(type);

        followUpOneTwoNewbornDaoConfig = daoConfigMap.get(FollowUpOneTwoNewbornDao.class).clone();
        followUpOneTwoNewbornDaoConfig.initIdentityScope(type);

        followUpThreeSixNewbornDaoConfig = daoConfigMap.get(FollowUpThreeSixNewbornDao.class).clone();
        followUpThreeSixNewbornDaoConfig.initIdentityScope(type);

        followUpAgedDaoConfig = daoConfigMap.get(FollowUpAgedDao.class).clone();
        followUpAgedDaoConfig.initIdentityScope(type);

        followUpDisabledPersonDaoConfig = daoConfigMap.get(FollowUpDisabledPersonDao.class).clone();
        followUpDisabledPersonDaoConfig.initIdentityScope(type);
        
        reportHypertensionDaoConfig = daoConfigMap.get(ReportHypertensionDao.class).clone();
        reportHypertensionDaoConfig.initIdentityScope(type);
        
        reportDiabetesMellitusDaoConfig = daoConfigMap.get(ReportDiabetesMellitusDao.class).clone();
        reportDiabetesMellitusDaoConfig.initIdentityScope(type);
        
        accompanyDaoConfig = daoConfigMap.get(AccompanyDao.class).clone();
        accompanyDaoConfig.initIdentityScope(type);

        archiveInfoDao = new ArchiveInfoDao(archiveInfoDaoConfig, this);
        generalExaminationDao = new GeneralExaminationDao(generalExaminationDaoConfig, this);
        examinationInfoDao = new ExaminationInfoDao(examinationInfoDaoConfig, this);
        followUpHypertensionDao = new FollowUpHypertensionDao(followUpHypertensionDaoConfig, this);
        followUpDiabetesMellitusDao = new FollowUpDiabetesMellitusDao(followUpDiabetesMellitusDaoConfig, this);
        followUpStrokeDao = new FollowUpStrokeDao(followUpStrokeDaoConfig, this);
        followUpMentalDiseaseDao = new FollowUpMentalDiseaseDao(followUpMentalDiseaseDaoConfig, this);
        followUpFirstPregnancyDao = new FollowUpFirstPregnancyDao(followUpFirstPregnancyDaoConfig, this);
        followUpTwoToFivePregnancyDao = new FollowUpTwoToFivePregnancyDao(followUpTwoToFivePregnancyDaoConfig, this);
        followUpPostpartumDao = new FollowUpPostpartumDao(followUpPostpartumDaoConfig, this);
        followUpFortyTwoDao = new FollowUpFortyTwoDao(followUpFortyTwoDaoConfig, this);
        followUpNewbornDao = new FollowUpNewbornDao(followUpNewbornDaoConfig, this);
        followUpOneNewbornDao = new FollowUpOneNewbornDao(followUpOneNewbornDaoConfig, this);
        followUpOneTwoNewbornDao = new FollowUpOneTwoNewbornDao(followUpOneTwoNewbornDaoConfig, this);
        followUpThreeSixNewbornDao = new FollowUpThreeSixNewbornDao(followUpThreeSixNewbornDaoConfig, this);
        followUpAgedDao = new FollowUpAgedDao(followUpAgedDaoConfig, this);
        followUpDisabledPersonDao = new FollowUpDisabledPersonDao(followUpDisabledPersonDaoConfig, this);
        reportHypertensionDao = new ReportHypertensionDao(reportHypertensionDaoConfig, this);
        reportDiabetesMellitusDao = new ReportDiabetesMellitusDao(reportDiabetesMellitusDaoConfig, this);
        accompanyDao = new AccompanyDao(accompanyDaoConfig, this);

        registerDao(ArchiveInfo.class, archiveInfoDao);
        registerDao(GeneralExamination.class, generalExaminationDao);
        registerDao(ExaminationInfo.class, examinationInfoDao);
        registerDao(FollowUpHypertension.class, followUpHypertensionDao);
        registerDao(FollowUpDiabetesMellitus.class, followUpDiabetesMellitusDao);
        registerDao(FollowUpStroke.class, followUpStrokeDao);
        registerDao(FollowUpMentalDisease.class, followUpMentalDiseaseDao);
        registerDao(FollowUpFirstPregnancy.class, followUpFirstPregnancyDao);
        registerDao(FollowUpTwoToFivePregnancy.class, followUpTwoToFivePregnancyDao);
        registerDao(FollowUpPostpartum.class, followUpPostpartumDao);
        registerDao(FollowUpFortyTwo.class, followUpFortyTwoDao);
        registerDao(FollowUpNewborn.class, followUpNewbornDao);
        registerDao(FollowUpOneNewborn.class, followUpOneNewbornDao);
        registerDao(FollowUpOneTwoNewborn.class, followUpOneTwoNewbornDao);
        registerDao(FollowUpThreeSixNewborn.class, followUpThreeSixNewbornDao);
        registerDao(FollowUpAged.class, followUpAgedDao);
        registerDao(FollowUpDisabledPerson.class, followUpDisabledPersonDao);
        registerDao(ReportHyoertension.class, reportHypertensionDao);
        registerDao(ReportDiabetesMellitus.class, reportDiabetesMellitusDao);
        registerDao(Accompany.class, accompanyDao);
    }
    
    public void clear() {
        archiveInfoDaoConfig.getIdentityScope().clear();
        generalExaminationDaoConfig.getIdentityScope().clear();
        examinationInfoDaoConfig.getIdentityScope().clear();
        followUpHypertensionDaoConfig.getIdentityScope().clear();
        followUpDiabetesMellitusDaoConfig.getIdentityScope().clear();
        followUpStrokeDaoConfig.getIdentityScope().clear();
        followUpMentalDiseaseDaoConfig.getIdentityScope().clear();
        followUpFirstPregnancyDaoConfig.getIdentityScope().clear();
        followUpTwoToFivePregnancyDaoConfig.getIdentityScope().clear();
        followUpPostpartumDaoConfig.getIdentityScope().clear();
        followUpFortyTwoDaoConfig.getIdentityScope().clear();
        followUpNewbornDaoConfig.getIdentityScope().clear();
        followUpOneNewbornDaoConfig.getIdentityScope().clear();
        followUpOneTwoNewbornDaoConfig.getIdentityScope().clear();
        followUpThreeSixNewbornDaoConfig.getIdentityScope().clear();
        followUpAgedDaoConfig.getIdentityScope().clear();
        followUpDisabledPersonDaoConfig.getIdentityScope().clear();
        reportHypertensionDaoConfig.getIdentityScope().clear();
        reportDiabetesMellitusDaoConfig.getIdentityScope().clear();
        accompanyDaoConfig.getIdentityScope().clear();
    }

    public ArchiveInfoDao getArchiveInfoDao() {
        return archiveInfoDao;
    }

    public GeneralExaminationDao getGeneralExaminationDao() {
        return generalExaminationDao;
    }

    public ExaminationInfoDao getExaminationInfoDao() {
        return examinationInfoDao;
    }

    public FollowUpHypertensionDao getFollowUpHypertensionDao() {
        return followUpHypertensionDao;
    }

    public FollowUpDiabetesMellitusDao getFollowUpDiabetesMellitusDao() {
        return followUpDiabetesMellitusDao;
    }

    public FollowUpStrokeDao getFollowUpStrokeDao() {
        return followUpStrokeDao;
    }

    public FollowUpMentalDiseaseDao getFollowUpMentalDiseaseDao() {
        return followUpMentalDiseaseDao;
    }

    public FollowUpFirstPregnancyDao getFollowUpFirstPregnancyDao() {
        return followUpFirstPregnancyDao;
    }

    public FollowUpTwoToFivePregnancyDao getFollowUpTwoToFivePregnancyDao() {
        return followUpTwoToFivePregnancyDao;
    }

    public FollowUpPostpartumDao getFollowUpPostpartumDao() {
        return followUpPostpartumDao;
    }

    public FollowUpFortyTwoDao getFollowUpFortyTwoDao() {
        return followUpFortyTwoDao;
    }

    public FollowUpNewbornDao getFollowUpNewbornDao() {
        return followUpNewbornDao;
    }

    public FollowUpOneNewbornDao getFollowUpOneNewbornDao() {
        return followUpOneNewbornDao;
    }

    public FollowUpOneTwoNewbornDao getFollowUpOneTwoNewbornDao() {
        return followUpOneTwoNewbornDao;
    }

    public FollowUpThreeSixNewbornDao getFollowUpThreeSixNewbornDao() {
        return followUpThreeSixNewbornDao;
    }

    public FollowUpAgedDao getFollowUpAgedDao() {
        return followUpAgedDao;
    }

    public FollowUpDisabledPersonDao getFollowUpDisabledPersonDao() {
        return followUpDisabledPersonDao;
    }
    
    public ReportHypertensionDao getReportHypertensionDao() {
        return reportHypertensionDao;
    }
    
    public ReportDiabetesMellitusDao getReportDiabetesMellitusDao() {
        return reportDiabetesMellitusDao;
    }
    
    public AccompanyDao getAccompanyDao() {
    	return accompanyDao;
    }

}
