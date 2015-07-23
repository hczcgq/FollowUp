package com.shbestwin.followupmanager.manager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.shbestwin.followupmanager.dao.ArchiveInfoDao;
import com.shbestwin.followupmanager.dao.ArchiveInfoDao.Properties;
import com.shbestwin.followupmanager.dao.DaoMaster;
import com.shbestwin.followupmanager.dao.DaoMaster.DevOpenHelper;
import com.shbestwin.followupmanager.dao.DaoSession;
import com.shbestwin.followupmanager.model.ArchiveInfo;

public class ArchiveInfoManager {
	private static ArchiveInfoManager instance = null;
	private Context mContext = null;

	private ArchiveInfoDao mArchiveInfoDao;

	private ArchiveInfoManager(Context applicContext) {
		mContext = applicContext;
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, null);
		SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster daoMaster = new DaoMaster(db);
		DaoSession daoSession = daoMaster.newSession();
		mArchiveInfoDao = daoSession.getArchiveInfoDao();
	}

	public static synchronized ArchiveInfoManager getInstance(Context context) {
		if (instance == null) {
			synchronized (ArchiveInfoManager.class) {
				if (instance == null) {
					instance = new ArchiveInfoManager(context.getApplicationContext());
				}
			}
		}
		return instance;
	}

	public void saveOrUpdateArchiveInfo(ArchiveInfo archiveInfo) {
		mArchiveInfoDao.insertOrReplace(archiveInfo);
	}

	public ArchiveInfo getArchiveInfoById(String idcard) {
		return mArchiveInfoDao.load(idcard);
		
	}

	public List<ArchiveInfo> getArchiveInfoList(String name, String idcard, String cardNo) {
		StringBuilder where = new StringBuilder(" where ");
		List<String> selectionArg = new ArrayList<String>();
		if (!TextUtils.isEmpty(name)) {
			where.append(Properties.Name.columnName).append(" like ?");
			selectionArg.add("%" + name + "%");
		}
		if (!TextUtils.isEmpty(idcard)) {
			if (selectionArg.size() > 0) {
				where.append(" and ");
			}
			where.append(Properties.Idcard.columnName).append(" like ?");
			selectionArg.add("%" + idcard + "%");
		}
		if (!TextUtils.isEmpty(cardNo)) {
			if (selectionArg.size() > 0) {
				where.append(" and ");
			}
			where.append(Properties.CardNo.columnName).append(" like ?");
			selectionArg.add("%" + cardNo + "%");
		}
		if (selectionArg.size() <= 0) {
			return new ArrayList<ArchiveInfo>();
		}
		return mArchiveInfoDao.queryRaw(where.toString(), selectionArg.toArray(new String[selectionArg.size()]));
	}
	
	public List<ArchiveInfo> getArchiveInfoList() {
		return mArchiveInfoDao.loadAll();
	}

}
