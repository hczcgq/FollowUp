package com.shbestwin.followupmanager.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table FOLLOW_UP_HYPERTENSION.
*/
public class FollowUpHypertensionDao extends AbstractDao<FollowUpHypertension, String> {

    public static final String TABLENAME = "FOLLOW_UP_HYPERTENSION";

    /**
     * Properties of entity FollowUpHypertension.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property FollowUpNo = new Property(0, String.class, "followUpNo", true, "FOLLOW_UP_NO");
        public final static Property Idcard = new Property(1, String.class, "idcard", false, "IDCARD");
        public final static Property Grxx_zrys = new Property(2, String.class, "grxx_zrys", false, "GRXX_ZRYS");
        public final static Property Grxx_sfrq = new Property(3, String.class, "grxx_sfrq", false, "GRXX_SFRQ");
        public final static Property Grxx_sffs = new Property(4, String.class, "grxx_sffs", false, "GRXX_SFFS");
        public final static Property Grxx_gxylx = new Property(5, String.class, "grxx_gxylx", false, "GRXX_GXYLX");
        public final static Property Zz = new Property(6, String.class, "zz", false, "ZZ");
        public final static Property Tz_xy = new Property(7, String.class, "tz_xy", false, "TZ_XY");
        public final static Property Tz_xl = new Property(8, String.class, "tz_xl", false, "TZ_XL");
        public final static Property Tz_tz = new Property(9, String.class, "tz_tz", false, "TZ_TZ");
        public final static Property Tz_sg = new Property(10, String.class, "tz_sg", false, "TZ_SG");
        public final static Property Tz_tzzs = new Property(11, String.class, "tz_tzzs", false, "TZ_TZZS");
        public final static Property Tz_qt = new Property(12, String.class, "tz_qt", false, "TZ_QT");
        public final static Property Shzdfs_rxyl = new Property(13, String.class, "shzdfs_rxyl", false, "SHZDFS_RXYL");
        public final static Property Shzdfs_ryjl = new Property(14, String.class, "shzdfs_ryjl", false, "SHZDFS_RYJL");
        public final static Property Shzdfs_ydl = new Property(15, String.class, "shzdfs_ydl", false, "SHZDFS_YDL");
        public final static Property Shzdfs_syqk = new Property(16, String.class, "shzdfs_syqk", false, "SHZDFS_SYQK");
        public final static Property Shzdfs_xltz = new Property(17, String.class, "shzdfs_xltz", false, "SHZDFS_XLTZ");
        public final static Property Fzjc = new Property(18, String.class, "fzjc", false, "FZJC");
        public final static Property Fyycx = new Property(19, String.class, "fyycx", false, "FYYCX");
        public final static Property Ywblfy_ywblfy = new Property(20, Boolean.class, "ywblfy_ywblfy", false, "YWBLFY_YWBLFY");
        public final static Property Jkwt_ywblfyms = new Property(21, String.class, "jkwt_ywblfyms", false, "JKWT_YWBLFYMS");
        public final static Property Ccsffl = new Property(22, String.class, "ccsffl", false, "CCSFFL");
        public final static Property Yyqk_yy = new Property(23, Boolean.class, "yyqk_yy", false, "YYQK_YY");
        public final static Property Yyqk_yyms = new Property(24, String.class, "yyqk_yyms", false, "YYQK_YYMS");
        public final static Property Zljy = new Property(25, String.class, "zljy", false, "ZLJY");
        public final static Property Zz_sfzz = new Property(26, Boolean.class, "zz_sfzz", false, "ZZ_SFZZ");
        public final static Property Zz_sfzzms = new Property(27, String.class, "zz_sfzzms", false, "ZZ_SFZZMS");
        public final static Property Zz_jgjks = new Property(28, String.class, "zz_jgjks", false, "ZZ_JGJKS");
        public final static Property Zzhf_rq = new Property(29, String.class, "zzhf_rq", false, "ZZHF_RQ");
        public final static Property Zzhf_bqgsh = new Property(30, String.class, "zzhf_bqgsh", false, "ZZHF_BQGSH");
        public final static Property Zzhf_hbz = new Property(31, String.class, "zzhf_hbz", false, "ZZHF_HBZ");
        public final static Property Zzhf_qtjb = new Property(32, String.class, "zzhf_qtjb", false, "ZZHF_QTJB");
        public final static Property Zzhf_xcsfrq = new Property(33, String.class, "zzhf_xcsfrq", false, "ZZHF_XCSFRQ");
        public final static Property Zzhf_sfysqm = new Property(34, String.class, "zzhf_sfysqm", false, "ZZHF_SFYSQM");
        public final static Property CreateTime = new Property(35, String.class, "createTime", false, "CREATE_TIME");
        public final static Property UpdateTime = new Property(36, String.class, "updateTime", false, "UPDATE_TIME");
    };


    public FollowUpHypertensionDao(DaoConfig config) {
        super(config);
    }
    
    public FollowUpHypertensionDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_HYPERTENSION' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + // 0: followUpNo
                "'IDCARD' TEXT NOT NULL ," + // 1: idcard
                "'GRXX_ZRYS' TEXT," + // 2: grxx_zrys
                "'GRXX_SFRQ' TEXT," + // 3: grxx_sfrq
                "'GRXX_SFFS' TEXT," + // 4: grxx_sffs
                "'GRXX_GXYLX' TEXT," + // 5: grxx_gxylx
                "'ZZ' TEXT," + // 6: zz
                "'TZ_XY' TEXT," + // 7: tz_xy
                "'TZ_XL' TEXT," + // 8: tz_xl
                "'TZ_TZ' TEXT," + // 9: tz_tz
                "'TZ_SG' TEXT," + // 10: tz_sg
                "'TZ_TZZS' TEXT," + // 11: tz_tzzs
                "'TZ_QT' TEXT," + // 12: tz_qt
                "'SHZDFS_RXYL' TEXT," + // 13: shzdfs_rxyl
                "'SHZDFS_RYJL' TEXT," + // 14: shzdfs_ryjl
                "'SHZDFS_YDL' TEXT," + // 15: shzdfs_ydl
                "'SHZDFS_SYQK' TEXT," + // 16: shzdfs_syqk
                "'SHZDFS_XLTZ' TEXT," + // 17: shzdfs_xltz
                "'FZJC' TEXT," + // 18: fzjc
                "'FYYCX' TEXT," + // 19: fyycx
                "'YWBLFY_YWBLFY' INTEGER," + // 20: ywblfy_ywblfy
                "'JKWT_YWBLFYMS' TEXT," + // 21: jkwt_ywblfyms
                "'CCSFFL' TEXT," + // 22: ccsffl
                "'YYQK_YY' INTEGER," + // 23: yyqk_yy
                "'YYQK_YYMS' TEXT," + // 24: yyqk_yyms
                "'ZLJY' TEXT," + // 25: zljy
                "'ZZ_SFZZ' INTEGER," + // 26: zz_sfzz
                "'ZZ_SFZZMS' TEXT," + // 27: zz_sfzzms
                "'ZZ_JGJKS' TEXT," + // 28: zz_jgjks
                "'ZZHF_RQ' TEXT," + // 29: zzhf_rq
                "'ZZHF_BQGSH' TEXT," + // 30: zzhf_bqgsh
                "'ZZHF_HBZ' TEXT," + // 31: zzhf_hbz
                "'ZZHF_QTJB' TEXT," + // 32: zzhf_qtjb
                "'ZZHF_XCSFRQ' TEXT," + // 33: zzhf_xcsfrq
                "'ZZHF_SFYSQM' TEXT," + // 34: zzhf_sfysqm
                "'CREATE_TIME' TEXT," + // 35: createTime
                "'UPDATE_TIME' TEXT);"); // 36: updateTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'FOLLOW_UP_HYPERTENSION'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, FollowUpHypertension entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getFollowUpNo());
        stmt.bindString(2, entity.getIdcard());
 
        String grxx_zrys = entity.getGrxx_zrys();
        if (grxx_zrys != null) {
            stmt.bindString(3, grxx_zrys);
        }
 
        String grxx_sfrq = entity.getGrxx_sfrq();
        if (grxx_sfrq != null) {
            stmt.bindString(4, grxx_sfrq);
        }
 
        String grxx_sffs = entity.getGrxx_sffs();
        if (grxx_sffs != null) {
            stmt.bindString(5, grxx_sffs);
        }
 
        String grxx_gxylx = entity.getGrxx_gxylx();
        if (grxx_gxylx != null) {
            stmt.bindString(6, grxx_gxylx);
        }
 
        String zz = entity.getZz();
        if (zz != null) {
            stmt.bindString(7, zz);
        }
 
        String tz_xy = entity.getTz_xy();
        if (tz_xy != null) {
            stmt.bindString(8, tz_xy);
        }
 
        String tz_xl = entity.getTz_xl();
        if (tz_xl != null) {
            stmt.bindString(9, tz_xl);
        }
 
        String tz_tz = entity.getTz_tz();
        if (tz_tz != null) {
            stmt.bindString(10, tz_tz);
        }
 
        String tz_sg = entity.getTz_sg();
        if (tz_sg != null) {
            stmt.bindString(11, tz_sg);
        }
 
        String tz_tzzs = entity.getTz_tzzs();
        if (tz_tzzs != null) {
            stmt.bindString(12, tz_tzzs);
        }
 
        String tz_qt = entity.getTz_qt();
        if (tz_qt != null) {
            stmt.bindString(13, tz_qt);
        }
 
        String shzdfs_rxyl = entity.getShzdfs_rxyl();
        if (shzdfs_rxyl != null) {
            stmt.bindString(14, shzdfs_rxyl);
        }
 
        String shzdfs_ryjl = entity.getShzdfs_ryjl();
        if (shzdfs_ryjl != null) {
            stmt.bindString(15, shzdfs_ryjl);
        }
 
        String shzdfs_ydl = entity.getShzdfs_ydl();
        if (shzdfs_ydl != null) {
            stmt.bindString(16, shzdfs_ydl);
        }
 
        String shzdfs_syqk = entity.getShzdfs_syqk();
        if (shzdfs_syqk != null) {
            stmt.bindString(17, shzdfs_syqk);
        }
 
        String shzdfs_xltz = entity.getShzdfs_xltz();
        if (shzdfs_xltz != null) {
            stmt.bindString(18, shzdfs_xltz);
        }
 
        String fzjc = entity.getFzjc();
        if (fzjc != null) {
            stmt.bindString(19, fzjc);
        }
 
        String fyycx = entity.getFyycx();
        if (fyycx != null) {
            stmt.bindString(20, fyycx);
        }
 
        Boolean ywblfy_ywblfy = entity.getYwblfy_ywblfy();
        if (ywblfy_ywblfy != null) {
            stmt.bindLong(21, ywblfy_ywblfy ? 1l: 0l);
        }
 
        String jkwt_ywblfyms = entity.getJkwt_ywblfyms();
        if (jkwt_ywblfyms != null) {
            stmt.bindString(22, jkwt_ywblfyms);
        }
 
        String ccsffl = entity.getCcsffl();
        if (ccsffl != null) {
            stmt.bindString(23, ccsffl);
        }
 
        Boolean yyqk_yy = entity.getYyqk_yy();
        if (yyqk_yy != null) {
            stmt.bindLong(24, yyqk_yy ? 1l: 0l);
        }
 
        String yyqk_yyms = entity.getYyqk_yyms();
        if (yyqk_yyms != null) {
            stmt.bindString(25, yyqk_yyms);
        }
 
        String zljy = entity.getZljy();
        if (zljy != null) {
            stmt.bindString(26, zljy);
        }
 
        Boolean zz_sfzz = entity.getZz_sfzz();
        if (zz_sfzz != null) {
            stmt.bindLong(27, zz_sfzz ? 1l: 0l);
        }
 
        String zz_sfzzms = entity.getZz_sfzzms();
        if (zz_sfzzms != null) {
            stmt.bindString(28, zz_sfzzms);
        }
 
        String zz_jgjks = entity.getZz_jgjks();
        if (zz_jgjks != null) {
            stmt.bindString(29, zz_jgjks);
        }
 
        String zzhf_rq = entity.getZzhf_rq();
        if (zzhf_rq != null) {
            stmt.bindString(30, zzhf_rq);
        }
 
        String zzhf_bqgsh = entity.getZzhf_bqgsh();
        if (zzhf_bqgsh != null) {
            stmt.bindString(31, zzhf_bqgsh);
        }
 
        String zzhf_hbz = entity.getZzhf_hbz();
        if (zzhf_hbz != null) {
            stmt.bindString(32, zzhf_hbz);
        }
 
        String zzhf_qtjb = entity.getZzhf_qtjb();
        if (zzhf_qtjb != null) {
            stmt.bindString(33, zzhf_qtjb);
        }
 
        String zzhf_xcsfrq = entity.getZzhf_xcsfrq();
        if (zzhf_xcsfrq != null) {
            stmt.bindString(34, zzhf_xcsfrq);
        }
 
        String zzhf_sfysqm = entity.getZzhf_sfysqm();
        if (zzhf_sfysqm != null) {
            stmt.bindString(35, zzhf_sfysqm);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(36, createTime);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(37, updateTime);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public FollowUpHypertension readEntity(Cursor cursor, int offset) {
        FollowUpHypertension entity = new FollowUpHypertension( //
            cursor.getString(offset + 0), // followUpNo
            cursor.getString(offset + 1), // idcard
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // grxx_zrys
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // grxx_sfrq
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // grxx_sffs
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // grxx_gxylx
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // zz
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // tz_xy
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // tz_xl
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // tz_tz
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // tz_sg
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // tz_tzzs
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // tz_qt
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // shzdfs_rxyl
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // shzdfs_ryjl
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // shzdfs_ydl
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // shzdfs_syqk
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // shzdfs_xltz
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // fzjc
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // fyycx
            cursor.isNull(offset + 20) ? null : cursor.getShort(offset + 20) != 0, // ywblfy_ywblfy
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // jkwt_ywblfyms
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // ccsffl
            cursor.isNull(offset + 23) ? null : cursor.getShort(offset + 23) != 0, // yyqk_yy
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // yyqk_yyms
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // zljy
            cursor.isNull(offset + 26) ? null : cursor.getShort(offset + 26) != 0, // zz_sfzz
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // zz_sfzzms
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // zz_jgjks
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // zzhf_rq
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // zzhf_bqgsh
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // zzhf_hbz
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // zzhf_qtjb
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // zzhf_xcsfrq
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // zzhf_sfysqm
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // createTime
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36) // updateTime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, FollowUpHypertension entity, int offset) {
        entity.setFollowUpNo(cursor.getString(offset + 0));
        entity.setIdcard(cursor.getString(offset + 1));
        entity.setGrxx_zrys(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGrxx_sfrq(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGrxx_sffs(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setGrxx_gxylx(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setZz(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTz_xy(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTz_xl(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setTz_tz(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setTz_sg(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setTz_tzzs(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setTz_qt(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setShzdfs_rxyl(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setShzdfs_ryjl(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setShzdfs_ydl(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setShzdfs_syqk(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setShzdfs_xltz(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setFzjc(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setFyycx(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setYwblfy_ywblfy(cursor.isNull(offset + 20) ? null : cursor.getShort(offset + 20) != 0);
        entity.setJkwt_ywblfyms(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setCcsffl(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setYyqk_yy(cursor.isNull(offset + 23) ? null : cursor.getShort(offset + 23) != 0);
        entity.setYyqk_yyms(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setZljy(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setZz_sfzz(cursor.isNull(offset + 26) ? null : cursor.getShort(offset + 26) != 0);
        entity.setZz_sfzzms(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setZz_jgjks(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setZzhf_rq(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setZzhf_bqgsh(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setZzhf_hbz(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setZzhf_qtjb(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setZzhf_xcsfrq(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setZzhf_sfysqm(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setCreateTime(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setUpdateTime(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(FollowUpHypertension entity, long rowId) {
        return entity.getFollowUpNo();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(FollowUpHypertension entity) {
        if(entity != null) {
            return entity.getFollowUpNo();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
