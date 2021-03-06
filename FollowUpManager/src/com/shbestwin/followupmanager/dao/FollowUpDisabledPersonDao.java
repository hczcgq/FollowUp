package com.shbestwin.followupmanager.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.shbestwin.followupmanager.model.followup.FollowUpDisabledPerson;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table FOLLOW_UP_DISABLED_PERSON.
*/
public class FollowUpDisabledPersonDao extends AbstractDao<FollowUpDisabledPerson, String> {

    public static final String TABLENAME = "FOLLOW_UP_DISABLED_PERSON";

    /**
     * Properties of entity FollowUpDisabledPerson.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property FollowUpNo = new Property(0, String.class, "followUpNo", true, "FOLLOW_UP_NO");
        public final static Property Idcard = new Property(1, String.class, "idcard", false, "IDCARD");
        public final static Property Grxx_zrys = new Property(2, String.class, "grxx_zrys", false, "GRXX_ZRYS");
        public final static Property Grxx_sfrq = new Property(3, String.class, "grxx_sfrq", false, "GRXX_SFRQ");
        public final static Property Grxx_sffs = new Property(4, String.class, "grxx_sffs", false, "GRXX_SFFS");
        public final static Property Grxx_knqt = new Property(5, String.class, "grxx_knqt", false, "GRXX_KNQT");
        public final static Property Grxx_kfxq = new Property(6, String.class, "grxx_kfxq", false, "GRXX_KFXQ");
        public final static Property Cjnx_cjzbh = new Property(7, String.class, "cjnx_cjzbh", false, "CJNX_CJZBH");
        public final static Property Cjnx_zycj = new Property(8, String.class, "cjnx_zycj", false, "CJNX_ZYCJ");
        public final static Property Cjnx_dccj = new Property(9, String.class, "cjnx_dccj", false, "CJNX_DCCJ");
        public final static Property Cjnx_cjrq = new Property(10, String.class, "cjnx_cjrq", false, "CJNX_CJRQ");
        public final static Property Cjnx_cjcd = new Property(11, String.class, "cjnx_cjcd", false, "CJNX_CJCD");
        public final static Property Cjnx_cjyy = new Property(12, String.class, "cjnx_cjyy", false, "CJNX_CJYY");
        public final static Property Ybqk_zz = new Property(13, String.class, "ybqk_zz", false, "YBQK_ZZ");
        public final static Property Ybqk_tz = new Property(14, String.class, "ybqk_tz", false, "YBQK_TZ");
        public final static Property Ybqk_xy = new Property(15, String.class, "ybqk_xy", false, "YBQK_XY");
        public final static Property Ybqk_xl = new Property(16, String.class, "ybqk_xl", false, "YBQK_XL");
        public final static Property Ybqk_qt = new Property(17, String.class, "ybqk_qt", false, "YBQK_QT");
        public final static Property Kffw_kfxm = new Property(18, String.class, "kffw_kfxm", false, "KFFW_KFXM");
        public final static Property Kffw_kfmb = new Property(19, String.class, "kffw_kfmb", false, "KFFW_KFMB");
        public final static Property Kffw_gnxl = new Property(20, String.class, "kffw_gnxl", false, "KFFW_GNXL");
        public final static Property Kffw_xldz = new Property(21, String.class, "kffw_xldz", false, "KFFW_XLDZ");
        public final static Property Kffw_xlpg = new Property(22, String.class, "kffw_xlpg", false, "KFFW_XLPG");
        public final static Property Kffw_yy = new Property(23, String.class, "kffw_yy", false, "KFFW_YY");
        public final static Property Kffw_zjqx = new Property(24, String.class, "kffw_zjqx", false, "KFFW_ZJQX");
        public final static Property Shfszd_sfxy = new Property(25, Boolean.class, "shfszd_sfxy", false, "SHFSZD_SFXY");
        public final static Property Shfszd_sfxyms = new Property(26, String.class, "shfszd_sfxyms", false, "SHFSZD_SFXYMS");
        public final static Property Shfszd_sfyj = new Property(27, Boolean.class, "shfszd_sfyj", false, "SHFSZD_SFYJ");
        public final static Property Shfszd_sfyjms = new Property(28, String.class, "shfszd_sfyjms", false, "SHFSZD_SFYJMS");
        public final static Property Shfszd_sfyd = new Property(29, Boolean.class, "shfszd_sfyd", false, "SHFSZD_SFYD");
        public final static Property Shfszd_sfydms = new Property(30, String.class, "shfszd_sfydms", false, "SHFSZD_SFYDMS");
        public final static Property Shfszd_ydxm = new Property(31, String.class, "shfszd_ydxm", false, "SHFSZD_YDXM");
        public final static Property Shfszd_syqk = new Property(32, String.class, "shfszd_syqk", false, "SHFSZD_SYQK");
        public final static Property Shfszd_xltz = new Property(33, String.class, "shfszd_xltz", false, "SHFSZD_XLTZ");
        public final static Property Shfszd_zyxw = new Property(34, String.class, "shfszd_zyxw", false, "SHFSZD_ZYXW");
        public final static Property Shfszd_sfpg = new Property(35, String.class, "shfszd_sfpg", false, "SHFSZD_SFPG");
        public final static Property Shfszd_kfjy = new Property(36, String.class, "shfszd_kfjy", false, "SHFSZD_KFJY");
        public final static Property Shfszd_xcsfrq = new Property(37, String.class, "shfszd_xcsfrq", false, "SHFSZD_XCSFRQ");
        public final static Property Shfszd_sfysqm = new Property(38, String.class, "shfszd_sfysqm", false, "SHFSZD_SFYSQM");
        public final static Property CreateTime = new Property(39, String.class, "createTime", false, "CREATE_TIME");
        public final static Property UpdateTime = new Property(40, String.class, "updateTime", false, "UPDATE_TIME");
    };


    public FollowUpDisabledPersonDao(DaoConfig config) {
        super(config);
    }
    
    public FollowUpDisabledPersonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_DISABLED_PERSON' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + // 0: followUpNo
                "'IDCARD' TEXT NOT NULL ," + // 1: idcard
                "'GRXX_ZRYS' TEXT," + // 2: grxx_zrys
                "'GRXX_SFRQ' TEXT," + // 3: grxx_sfrq
                "'GRXX_SFFS' TEXT," + // 4: grxx_sffs
                "'GRXX_KNQT' TEXT," + // 5: grxx_knqt
                "'GRXX_KFXQ' TEXT," + // 6: grxx_kfxq
                "'CJNX_CJZBH' TEXT," + // 7: cjnx_cjzbh
                "'CJNX_ZYCJ' TEXT," + // 8: cjnx_zycj
                "'CJNX_DCCJ' TEXT," + // 9: cjnx_dccj
                "'CJNX_CJRQ' TEXT," + // 10: cjnx_cjrq
                "'CJNX_CJCD' TEXT," + // 11: cjnx_cjcd
                "'CJNX_CJYY' TEXT," + // 12: cjnx_cjyy
                "'YBQK_ZZ' TEXT," + // 13: ybqk_zz
                "'YBQK_TZ' TEXT," + // 14: ybqk_tz
                "'YBQK_XY' TEXT," + // 15: ybqk_xy
                "'YBQK_XL' TEXT," + // 16: ybqk_xl
                "'YBQK_QT' TEXT," + // 17: ybqk_qt
                "'KFFW_KFXM' TEXT," + // 18: kffw_kfxm
                "'KFFW_KFMB' TEXT," + // 19: kffw_kfmb
                "'KFFW_GNXL' TEXT," + // 20: kffw_gnxl
                "'KFFW_XLDZ' TEXT," + // 21: kffw_xldz
                "'KFFW_XLPG' TEXT," + // 22: kffw_xlpg
                "'KFFW_YY' TEXT," + // 23: kffw_yy
                "'KFFW_ZJQX' TEXT," + // 24: kffw_zjqx
                "'SHFSZD_SFXY' INTEGER," + // 25: shfszd_sfxy
                "'SHFSZD_SFXYMS' TEXT," + // 26: shfszd_sfxyms
                "'SHFSZD_SFYJ' INTEGER," + // 27: shfszd_sfyj
                "'SHFSZD_SFYJMS' TEXT," + // 28: shfszd_sfyjms
                "'SHFSZD_SFYD' INTEGER," + // 29: shfszd_sfyd
                "'SHFSZD_SFYDMS' TEXT," + // 30: shfszd_sfydms
                "'SHFSZD_YDXM' TEXT," + // 31: shfszd_ydxm
                "'SHFSZD_SYQK' TEXT," + // 32: shfszd_syqk
                "'SHFSZD_XLTZ' TEXT," + // 33: shfszd_xltz
                "'SHFSZD_ZYXW' TEXT," + // 34: shfszd_zyxw
                "'SHFSZD_SFPG' TEXT," + // 35: shfszd_sfpg
                "'SHFSZD_KFJY' TEXT," + // 36: shfszd_kfjy
                "'SHFSZD_XCSFRQ' TEXT," + // 37: shfszd_xcsfrq
                "'SHFSZD_SFYSQM' TEXT," + // 38: shfszd_sfysqm
                "'CREATE_TIME' TEXT," + // 39: createTime
                "'UPDATE_TIME' TEXT);"); // 40: updateTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'FOLLOW_UP_DISABLED_PERSON'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, FollowUpDisabledPerson entity) {
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
 
        String grxx_knqt = entity.getGrxx_knqt();
        if (grxx_knqt != null) {
            stmt.bindString(6, grxx_knqt);
        }
 
        String grxx_kfxq = entity.getGrxx_kfxq();
        if (grxx_kfxq != null) {
            stmt.bindString(7, grxx_kfxq);
        }
 
        String cjnx_cjzbh = entity.getCjnx_cjzbh();
        if (cjnx_cjzbh != null) {
            stmt.bindString(8, cjnx_cjzbh);
        }
 
        String cjnx_zycj = entity.getCjnx_zycj();
        if (cjnx_zycj != null) {
            stmt.bindString(9, cjnx_zycj);
        }
 
        String cjnx_dccj = entity.getCjnx_dccj();
        if (cjnx_dccj != null) {
            stmt.bindString(10, cjnx_dccj);
        }
 
        String cjnx_cjrq = entity.getCjnx_cjrq();
        if (cjnx_cjrq != null) {
            stmt.bindString(11, cjnx_cjrq);
        }
 
        String cjnx_cjcd = entity.getCjnx_cjcd();
        if (cjnx_cjcd != null) {
            stmt.bindString(12, cjnx_cjcd);
        }
 
        String cjnx_cjyy = entity.getCjnx_cjyy();
        if (cjnx_cjyy != null) {
            stmt.bindString(13, cjnx_cjyy);
        }
 
        String ybqk_zz = entity.getYbqk_zz();
        if (ybqk_zz != null) {
            stmt.bindString(14, ybqk_zz);
        }
 
        String ybqk_tz = entity.getYbqk_tz();
        if (ybqk_tz != null) {
            stmt.bindString(15, ybqk_tz);
        }
 
        String ybqk_xy = entity.getYbqk_xy();
        if (ybqk_xy != null) {
            stmt.bindString(16, ybqk_xy);
        }
 
        String ybqk_xl = entity.getYbqk_xl();
        if (ybqk_xl != null) {
            stmt.bindString(17, ybqk_xl);
        }
 
        String ybqk_qt = entity.getYbqk_qt();
        if (ybqk_qt != null) {
            stmt.bindString(18, ybqk_qt);
        }
 
        String kffw_kfxm = entity.getKffw_kfxm();
        if (kffw_kfxm != null) {
            stmt.bindString(19, kffw_kfxm);
        }
 
        String kffw_kfmb = entity.getKffw_kfmb();
        if (kffw_kfmb != null) {
            stmt.bindString(20, kffw_kfmb);
        }
 
        String kffw_gnxl = entity.getKffw_gnxl();
        if (kffw_gnxl != null) {
            stmt.bindString(21, kffw_gnxl);
        }
 
        String kffw_xldz = entity.getKffw_xldz();
        if (kffw_xldz != null) {
            stmt.bindString(22, kffw_xldz);
        }
 
        String kffw_xlpg = entity.getKffw_xlpg();
        if (kffw_xlpg != null) {
            stmt.bindString(23, kffw_xlpg);
        }
 
        String kffw_yy = entity.getKffw_yy();
        if (kffw_yy != null) {
            stmt.bindString(24, kffw_yy);
        }
 
        String kffw_zjqx = entity.getKffw_zjqx();
        if (kffw_zjqx != null) {
            stmt.bindString(25, kffw_zjqx);
        }
 
        Boolean shfszd_sfxy = entity.getShfszd_sfxy();
        if (shfszd_sfxy != null) {
            stmt.bindLong(26, shfszd_sfxy ? 1l: 0l);
        }
 
        String shfszd_sfxyms = entity.getShfszd_sfxyms();
        if (shfszd_sfxyms != null) {
            stmt.bindString(27, shfszd_sfxyms);
        }
 
        Boolean shfszd_sfyj = entity.getShfszd_sfyj();
        if (shfszd_sfyj != null) {
            stmt.bindLong(28, shfszd_sfyj ? 1l: 0l);
        }
 
        String shfszd_sfyjms = entity.getShfszd_sfyjms();
        if (shfszd_sfyjms != null) {
            stmt.bindString(29, shfszd_sfyjms);
        }
 
        Boolean shfszd_sfyd = entity.getShfszd_sfyd();
        if (shfszd_sfyd != null) {
            stmt.bindLong(30, shfszd_sfyd ? 1l: 0l);
        }
 
        String shfszd_sfydms = entity.getShfszd_sfydms();
        if (shfszd_sfydms != null) {
            stmt.bindString(31, shfszd_sfydms);
        }
 
        String shfszd_ydxm = entity.getShfszd_ydxm();
        if (shfszd_ydxm != null) {
            stmt.bindString(32, shfszd_ydxm);
        }
 
        String shfszd_syqk = entity.getShfszd_syqk();
        if (shfszd_syqk != null) {
            stmt.bindString(33, shfszd_syqk);
        }
 
        String shfszd_xltz = entity.getShfszd_xltz();
        if (shfszd_xltz != null) {
            stmt.bindString(34, shfszd_xltz);
        }
 
        String shfszd_zyxw = entity.getShfszd_zyxw();
        if (shfszd_zyxw != null) {
            stmt.bindString(35, shfszd_zyxw);
        }
 
        String shfszd_sfpg = entity.getShfszd_sfpg();
        if (shfszd_sfpg != null) {
            stmt.bindString(36, shfszd_sfpg);
        }
 
        String shfszd_kfjy = entity.getShfszd_kfjy();
        if (shfszd_kfjy != null) {
            stmt.bindString(37, shfszd_kfjy);
        }
 
        String shfszd_xcsfrq = entity.getShfszd_xcsfrq();
        if (shfszd_xcsfrq != null) {
            stmt.bindString(38, shfszd_xcsfrq);
        }
 
        String shfszd_sfysqm = entity.getShfszd_sfysqm();
        if (shfszd_sfysqm != null) {
            stmt.bindString(39, shfszd_sfysqm);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(40, createTime);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(41, updateTime);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public FollowUpDisabledPerson readEntity(Cursor cursor, int offset) {
        FollowUpDisabledPerson entity = new FollowUpDisabledPerson( //
            cursor.getString(offset + 0), // followUpNo
            cursor.getString(offset + 1), // idcard
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // grxx_zrys
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // grxx_sfrq
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // grxx_sffs
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // grxx_knqt
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // grxx_kfxq
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // cjnx_cjzbh
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // cjnx_zycj
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // cjnx_dccj
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // cjnx_cjrq
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // cjnx_cjcd
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // cjnx_cjyy
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // ybqk_zz
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // ybqk_tz
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // ybqk_xy
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // ybqk_xl
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // ybqk_qt
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // kffw_kfxm
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // kffw_kfmb
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // kffw_gnxl
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // kffw_xldz
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // kffw_xlpg
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // kffw_yy
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // kffw_zjqx
            cursor.isNull(offset + 25) ? null : cursor.getShort(offset + 25) != 0, // shfszd_sfxy
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // shfszd_sfxyms
            cursor.isNull(offset + 27) ? null : cursor.getShort(offset + 27) != 0, // shfszd_sfyj
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // shfszd_sfyjms
            cursor.isNull(offset + 29) ? null : cursor.getShort(offset + 29) != 0, // shfszd_sfyd
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // shfszd_sfydms
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // shfszd_ydxm
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // shfszd_syqk
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // shfszd_xltz
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // shfszd_zyxw
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // shfszd_sfpg
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // shfszd_kfjy
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // shfszd_xcsfrq
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // shfszd_sfysqm
            cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // createTime
            cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40) // updateTime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, FollowUpDisabledPerson entity, int offset) {
        entity.setFollowUpNo(cursor.getString(offset + 0));
        entity.setIdcard(cursor.getString(offset + 1));
        entity.setGrxx_zrys(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGrxx_sfrq(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGrxx_sffs(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setGrxx_knqt(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setGrxx_kfxq(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCjnx_cjzbh(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCjnx_zycj(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCjnx_dccj(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCjnx_cjrq(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCjnx_cjcd(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCjnx_cjyy(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setYbqk_zz(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setYbqk_tz(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setYbqk_xy(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setYbqk_xl(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setYbqk_qt(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setKffw_kfxm(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setKffw_kfmb(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setKffw_gnxl(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setKffw_xldz(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setKffw_xlpg(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setKffw_yy(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setKffw_zjqx(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setShfszd_sfxy(cursor.isNull(offset + 25) ? null : cursor.getShort(offset + 25) != 0);
        entity.setShfszd_sfxyms(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setShfszd_sfyj(cursor.isNull(offset + 27) ? null : cursor.getShort(offset + 27) != 0);
        entity.setShfszd_sfyjms(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setShfszd_sfyd(cursor.isNull(offset + 29) ? null : cursor.getShort(offset + 29) != 0);
        entity.setShfszd_sfydms(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setShfszd_ydxm(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setShfszd_syqk(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setShfszd_xltz(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setShfszd_zyxw(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setShfszd_sfpg(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setShfszd_kfjy(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setShfszd_xcsfrq(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setShfszd_sfysqm(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setCreateTime(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setUpdateTime(cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(FollowUpDisabledPerson entity, long rowId) {
        return entity.getFollowUpNo();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(FollowUpDisabledPerson entity) {
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
