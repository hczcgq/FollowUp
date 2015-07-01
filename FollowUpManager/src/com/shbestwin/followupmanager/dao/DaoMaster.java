package com.shbestwin.followupmanager.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import com.shbestwin.followupmanager.dao.ArchiveInfoDao;
import com.shbestwin.followupmanager.dao.GeneralExaminationDao;
import com.shbestwin.followupmanager.dao.ExaminationInfoDao;
import com.shbestwin.followupmanager.dao.FollowUpHypertensionDao;
import com.shbestwin.followupmanager.dao.FollowUpDiabetesMellitusDao;
import com.shbestwin.followupmanager.dao.FollowUpStrokeDao;
import com.shbestwin.followupmanager.dao.FollowUpMentalDiseaseDao;
import com.shbestwin.followupmanager.dao.FollowUpFirstPregnancyDao;
import com.shbestwin.followupmanager.dao.FollowUpTwoToFivePregnancyDao;
import com.shbestwin.followupmanager.dao.FollowUpPostpartumDao;
import com.shbestwin.followupmanager.dao.FollowUpFortyTwoDao;
import com.shbestwin.followupmanager.dao.FollowUpNewbornDao;
import com.shbestwin.followupmanager.dao.FollowUpOneNewbornDao;
import com.shbestwin.followupmanager.dao.FollowUpOneTwoNewbornDao;
import com.shbestwin.followupmanager.dao.FollowUpThreeSixNewbornDao;
import com.shbestwin.followupmanager.dao.FollowUpAgedDao;
import com.shbestwin.followupmanager.dao.FollowUpDisabledPersonDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 1): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
	public static final int SCHEMA_VERSION = 1;
	public static final String SCHEMA_NAME = "follow_up_manager.db";

	/** Creates underlying database table using DAOs. */
	public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
		ArchiveInfoDao.createTable(db, ifNotExists);
		GeneralExaminationDao.createTable(db, ifNotExists);
		ExaminationInfoDao.createTable(db, ifNotExists);
		FollowUpHypertensionDao.createTable(db, ifNotExists);
		FollowUpDiabetesMellitusDao.createTable(db, ifNotExists);
		FollowUpStrokeDao.createTable(db, ifNotExists);
		FollowUpMentalDiseaseDao.createTable(db, ifNotExists);
		FollowUpFirstPregnancyDao.createTable(db, ifNotExists);
		FollowUpTwoToFivePregnancyDao.createTable(db, ifNotExists);
		FollowUpPostpartumDao.createTable(db, ifNotExists);
		FollowUpFortyTwoDao.createTable(db, ifNotExists);
		FollowUpNewbornDao.createTable(db, ifNotExists);
		FollowUpOneNewbornDao.createTable(db, ifNotExists);
		FollowUpOneTwoNewbornDao.createTable(db, ifNotExists);
		FollowUpThreeSixNewbornDao.createTable(db, ifNotExists);
		FollowUpAgedDao.createTable(db, ifNotExists);
		FollowUpDisabledPersonDao.createTable(db, ifNotExists);
	}

	/** Drops underlying database table using DAOs. */
	public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
		ArchiveInfoDao.dropTable(db, ifExists);
		GeneralExaminationDao.dropTable(db, ifExists);
		ExaminationInfoDao.dropTable(db, ifExists);
		FollowUpHypertensionDao.dropTable(db, ifExists);
		FollowUpDiabetesMellitusDao.dropTable(db, ifExists);
		FollowUpStrokeDao.dropTable(db, ifExists);
		FollowUpMentalDiseaseDao.dropTable(db, ifExists);
		FollowUpFirstPregnancyDao.dropTable(db, ifExists);
		FollowUpTwoToFivePregnancyDao.dropTable(db, ifExists);
		FollowUpPostpartumDao.dropTable(db, ifExists);
		FollowUpFortyTwoDao.dropTable(db, ifExists);
		FollowUpNewbornDao.dropTable(db, ifExists);
		FollowUpOneNewbornDao.dropTable(db, ifExists);
		FollowUpOneTwoNewbornDao.dropTable(db, ifExists);
		FollowUpThreeSixNewbornDao.dropTable(db, ifExists);
		FollowUpAgedDao.dropTable(db, ifExists);
		FollowUpDisabledPersonDao.dropTable(db, ifExists);
	}

	public static abstract class OpenHelper extends SQLiteOpenHelper {

		public OpenHelper(Context context, CursorFactory factory) {
			super(context, SCHEMA_NAME, factory, SCHEMA_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
			createAllTables(db, false);
		}
	}

	/** WARNING: Drops all table on Upgrade! Use only during development. */
	public static class DevOpenHelper extends OpenHelper {
		public DevOpenHelper(Context context, CursorFactory factory) {
			super(context, factory);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
			dropAllTables(db, true);
			onCreate(db);
		}
	}

	public DaoMaster(SQLiteDatabase db) {
		super(db, SCHEMA_VERSION);
		registerDaoClass(ArchiveInfoDao.class);
		registerDaoClass(GeneralExaminationDao.class);
		registerDaoClass(ExaminationInfoDao.class);
		registerDaoClass(FollowUpHypertensionDao.class);
		registerDaoClass(FollowUpDiabetesMellitusDao.class);
		registerDaoClass(FollowUpStrokeDao.class);
		registerDaoClass(FollowUpMentalDiseaseDao.class);
		registerDaoClass(FollowUpFirstPregnancyDao.class);
		registerDaoClass(FollowUpTwoToFivePregnancyDao.class);
		registerDaoClass(FollowUpPostpartumDao.class);
		registerDaoClass(FollowUpFortyTwoDao.class);
		registerDaoClass(FollowUpNewbornDao.class);
		registerDaoClass(FollowUpOneNewbornDao.class);
		registerDaoClass(FollowUpOneTwoNewbornDao.class);
		registerDaoClass(FollowUpThreeSixNewbornDao.class);
		registerDaoClass(FollowUpAgedDao.class);
		registerDaoClass(FollowUpDisabledPersonDao.class);
	}

	public DaoSession newSession() {
		return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
	}

	public DaoSession newSession(IdentityScopeType type) {
		return new DaoSession(db, type, daoConfigMap);
	}

}