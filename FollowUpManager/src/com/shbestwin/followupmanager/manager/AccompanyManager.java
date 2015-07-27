package com.shbestwin.followupmanager.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.http.util.TextUtils;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.dao.AccompanyDao;
import com.shbestwin.followupmanager.dao.AccompanyDao.Properties;
import com.shbestwin.followupmanager.dao.DaoMaster;
import com.shbestwin.followupmanager.dao.DaoMaster.DevOpenHelper;
import com.shbestwin.followupmanager.dao.DaoSession;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.model.ArchiveInfo;

public class AccompanyManager {
	private static AccompanyManager instance = null;
	private Context mContext = null;

	private AccompanyDao mAccompanyDao;

	private AccompanyManager(Context applicContext) {
		mContext = applicContext;
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, null);
		SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster daoMaster = new DaoMaster(db);
		DaoSession daoSession = daoMaster.newSession();
		mAccompanyDao = daoSession.getAccompanyDao();
	}

	public static synchronized AccompanyManager getInstance(Context context) {
		if (instance == null) {
			synchronized (AccompanyManager.class) {
				if (instance == null) {
					instance = new AccompanyManager(context.getApplicationContext());
				}
			}
		}
		return instance;
	}

	public void saveOrUpdateAccompany(Accompany archiveInfo) {
		mAccompanyDao.insertOrReplace(archiveInfo);
	}

	public Accompany getAccompanyById(String idcard) {
		return mAccompanyDao.load(idcard);
		
	}
	
	public List<Accompany> getAccompanyList() {
		return mAccompanyDao.loadAll();
	}
	
	public List<Accompany> getAccompanyListGroupBy() {
//		return mAccompanyDao.queryRaw(" group by CREATE_TIME,IDCARD ", null);
		
		StringBuilder where = new StringBuilder(" group by IDCARD,CRURRENT_TIME ");
		return mAccompanyDao.queryRaw(where.toString(), new String[]{});
	}
	
	public List<Accompany> getAccompanyListAlready() {
		StringBuilder where = new StringBuilder(" where ");
		where.append(Properties.reported.columnName).append(" = ").append("1").append(" group by CRURRENT_TIME,IDCARD ");
		return mAccompanyDao.queryRaw(where.toString(), new String[]{});
	}
	
	public List<Accompany> getAccompanyNextDate() {
		StringBuilder where = new StringBuilder(" where NEXT_TIME  not null order by next_time asc limit 1  ");
		return mAccompanyDao.queryRaw(where.toString(), new String[]{});
	}

	
	
	public void addAccompany(String current_date,String next_date,int tag) {
		Accompany accompany=MyApplication.getInstance().getAccompany(tag);
		if(accompany==null){
			ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
			if (archiveInfo != null) {
				accompany = new Accompany();
				accompany.setAccompanyNo(new SimpleDateFormat("yyyyMMdd").format(new Date())+archiveInfo.getIdcard()+tag);
				accompany.setIdcard(archiveInfo.getIdcard());
				accompany.setName(archiveInfo.getName());
				accompany.setGender(archiveInfo.getGender());
				accompany.setBirthday(archiveInfo.getBirthday());
				accompany.setEthnic(archiveInfo.getEthnic());
				accompany.setTelephone(archiveInfo.getTelephone());
				accompany.setAddress(archiveInfo.getFamilyAddress());
				if(TextUtils.isEmpty(current_date)){
					accompany.setCurrent_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				}else {
					accompany.setCurrent_time(current_date);
				}
				accompany.setNext_time(next_date);
				accompany.setAccompany_item(getTagName(tag));
				accompany.setCreate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				accompany.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				accompany.setReported("1");
			}
		}else {
			accompany.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			accompany.setCurrent_time(current_date);
			accompany.setNext_time(next_date);
			accompany.setReported("1");
		}
		saveOrUpdateAccompany(accompany);
	}
	
	private String getTagName(int tag) {
		String msg="";
		if(tag==Accompany.ACNO_REPORTHYPERTENSION){
			msg="(报卡)高血压";
		}else if(tag==Accompany.ACNO_REPORTDIABETESMELLITUS){
			msg="(报卡)糖尿病";
		}else if(tag==Accompany.ACNO_HYPERTENSION){
			msg="高血压";
		}else if(tag==Accompany.ACNO_DIABETESMELLITUS){
			msg="糖尿病";
		}else if(tag==Accompany.ACNO_CEREBRALAPOPLWXY){
			msg="脑卒中";
		}else if(tag==Accompany.ACNO_MENTALDISEASE){
			msg="精神病";
		}else if(tag==Accompany.ACNO_ANTENATAL1){
			msg="孕产访视(第1次产前)";
		}else if(tag==Accompany.ACNO_ANTENATAL2_5){
			msg="孕产访视(第2-5次产前)";
		}else if(tag==Accompany.ACNO_POSTPARTUM){
			msg="孕产访视(产后访视)";
		}else if(tag==Accompany.ACNO_INSPECT42){
			msg="孕产访视(24天检查)";
		}else if(tag==Accompany.ACNO_NEONATE){
			msg="儿童访视(新生儿)";
		}else if(tag==Accompany.ACNO_YEAESONLD0){
			msg="儿童访视(1岁内)";
		}else if(tag==Accompany.ACNO_YEAESONLD1_2){
			msg="儿童访视(1-2岁)";
		}else if(tag==Accompany.ACNO_YEAESONLD3_6){
			msg="儿童访视(3-6岁)";
		}else if(tag==Accompany.ACNO_AGEDNESS){
			msg="老年随访";
		}else if(tag==Accompany.ACNO_DISABILITY){
			msg="残疾访视";
		}
		return msg;
	}
}
