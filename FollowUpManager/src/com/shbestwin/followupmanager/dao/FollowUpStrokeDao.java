package com.shbestwin.followupmanager.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.shbestwin.followupmanager.model.followup.FollowUpStroke;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table FOLLOW_UP_STROKE.
*/
public class FollowUpStrokeDao extends AbstractDao<FollowUpStroke, String> {

    public static final String TABLENAME = "FOLLOW_UP_STROKE";

    /**
     * Properties of entity FollowUpStroke.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property FollowUpNo = new Property(0, String.class, "followUpNo", true, "FOLLOW_UP_NO");
        public final static Property Idcard = new Property(1, String.class, "idcard", false, "IDCARD");
        public final static Property Grxx_zrys = new Property(2, String.class, "grxx_zrys", false, "GRXX_ZRYS");
        public final static Property Grxx_sfrq = new Property(3, String.class, "grxx_sfrq", false, "GRXX_SFRQ");
        public final static Property Shxggb_sfxy = new Property(4, Boolean.class, "shxggb_sfxy", false, "SHXGGB_SFXY");
        public final static Property Shxggb_sfxyms = new Property(5, String.class, "shxggb_sfxyms", false, "SHXGGB_SFXYMS");
        public final static Property Shxggb_sfyj = new Property(6, Boolean.class, "shxggb_sfyj", false, "SHXGGB_SFYJ");
        public final static Property Shxggb_sfyjms = new Property(7, String.class, "shxggb_sfyjms", false, "SHXGGB_SFYJMS");
        public final static Property Shxggb_ydmc = new Property(8, String.class, "shxggb_ydmc", false, "SHXGGB_YDMC");
        public final static Property Shxggb_ydsj = new Property(9, String.class, "shxggb_ydsj", false, "SHXGGB_YDSJ");
        public final static Property Shxggb_kfdlmc = new Property(10, String.class, "shxggb_kfdlmc", false, "SHXGGB_KFDLMC");
        public final static Property Shxggb_kfdlsj = new Property(11, String.class, "shxggb_kfdlsj", false, "SHXGGB_KFDLSJ");
        public final static Property Wxyskzgxy_sfmzcxy = new Property(12, String.class, "wxyskzgxy_sfmzcxy", false, "WXYSKZGXY_SFMZCXY");
        public final static Property Wxyskzgxy_sfmzcxyms = new Property(13, String.class, "wxyskzgxy_sfmzcxyms", false, "WXYSKZGXY_SFMZCXYMS");
        public final static Property Wxyskzgxy_yyqk = new Property(14, String.class, "wxyskzgxy_yyqk", false, "WXYSKZGXY_YYQK");
        public final static Property Wxyskztnb_fcrq = new Property(15, String.class, "wxyskztnb_fcrq", false, "WXYSKZTNB_FCRQ");
        public final static Property Wxyskztnb_xtsp = new Property(16, String.class, "wxyskztnb_xtsp", false, "WXYSKZTNB_XTSP");
        public final static Property Wxyskztnb_hbaic = new Property(17, String.class, "wxyskztnb_hbaic", false, "WXYSKZTNB_HBAIC");
        public final static Property Wxyskztnb_yyqk = new Property(18, String.class, "wxyskztnb_yyqk", false, "WXYSKZTNB_YYQK");
        public final static Property Wxyskzgxz_fcrq = new Property(19, String.class, "wxyskzgxz_fcrq", false, "WXYSKZGXZ_FCRQ");
        public final static Property Wxyskzgxz_tg = new Property(20, String.class, "wxyskzgxz_tg", false, "WXYSKZGXZ_TG");
        public final static Property Wxyskzgxz_tc = new Property(21, String.class, "wxyskzgxz_tc", false, "WXYSKZGXZ_TC");
        public final static Property Wxyskzgxz_ldl = new Property(22, String.class, "wxyskzgxz_ldl", false, "WXYSKZGXZ_LDL");
        public final static Property Wxyskzgxz_hdl = new Property(23, String.class, "wxyskzgxz_hdl", false, "WXYSKZGXZ_HDL");
        public final static Property Wxyskzgxz_LP = new Property(24, String.class, "wxyskzgxz_LP", false, "WXYSKZGXZ__LP");
        public final static Property Wxyskzgxz_yyqk = new Property(25, String.class, "wxyskzgxz_yyqk", false, "WXYSKZGXZ_YYQK");
        public final static Property Zdsj_sfzd = new Property(26, Boolean.class, "zdsj_sfzd", false, "ZDSJ_SFZD");
        public final static Property Zdsj_xfczsjzd = new Property(27, String.class, "zdsj_xfczsjzd", false, "ZDSJ_XFCZSJZD");
        public final static Property Zdsj_qtxgsj = new Property(28, String.class, "zdsj_qtxgsj", false, "ZDSJ_QTXGSJ");
        public final static Property Zdsj_ydmc = new Property(29, String.class, "zdsj_ydmc", false, "ZDSJ_YDMC");
        public final static Property Zdsj_ydsj = new Property(30, String.class, "zdsj_ydsj", false, "ZDSJ_YDSJ");
        public final static Property Zdsj_kfdlmc = new Property(31, String.class, "zdsj_kfdlmc", false, "ZDSJ_KFDLMC");
        public final static Property Zdsj_kfdlsj = new Property(32, String.class, "zdsj_kfdlsj", false, "ZDSJ_KFDLSJ");
        public final static Property Zdsj_pf_mrs = new Property(33, String.class, "zdsj_pf_mrs", false, "ZDSJ_PF_MRS");
        public final static Property Zdsj_pf_mrsjg = new Property(34, String.class, "zdsj_pf_mrsjg", false, "ZDSJ_PF_MRSJG");
        public final static Property Zdsj_pf_bi = new Property(35, String.class, "zdsj_pf_bi", false, "ZDSJ_PF_BI");
        public final static Property Zdsj_pf_bijg = new Property(36, String.class, "zdsj_pf_bijg", false, "ZDSJ_PF_BIJG");
        public final static Property Zdsj_pf_sspf = new Property(37, String.class, "zdsj_pf_sspf", false, "ZDSJ_PF_SSPF");
        public final static Property Zdsj_pf_sspfjg = new Property(38, String.class, "zdsj_pf_sspfjg", false, "ZDSJ_PF_SSPFJG");
        public final static Property Zdsj_sfysqm = new Property(39, String.class, "zdsj_sfysqm", false, "ZDSJ_SFYSQM");
        public final static Property CreateTime = new Property(40, String.class, "createTime", false, "CREATE_TIME");
        public final static Property UpdateTime = new Property(41, String.class, "updateTime", false, "UPDATE_TIME");
    };


    public FollowUpStrokeDao(DaoConfig config) {
        super(config);
    }
    
    public FollowUpStrokeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_STROKE' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + // 随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_ZRYS' TEXT," + // 个人信息－责任医师
                "'GRXX_SFRQ' TEXT," + // 个人信息－随访日期
                "'SHXGGB_SFXY' INTEGER," + // 生活习惯改变－是否吸烟
                "'SHXGGB_SFXYMS' TEXT," + // 生活习惯改变－是否吸烟描述
                "'SHXGGB_SFYJ' INTEGER," + // 生活习惯改变－是否饮酒
                "'SHXGGB_SFYJMS' TEXT," + // 生活习惯改变－是否饮酒描述
                "'SHXGGB_YDMC' TEXT," + // 生活习惯改变－运动
                "'SHXGGB_YDSJ' TEXT," + //生活习惯改变－运动时间
                "'SHXGGB_KFDLMC' TEXT," + // 生活习惯改变－康复锻炼
                "'SHXGGB_KFDLSJ' TEXT," + // 生活习惯改变－康复锻炼时间
                "'WXYSKZGXY_SFMZCXY' TEXT," + // 危险因素控制高血压－是否每周测血压
                "'WXYSKZGXY_SFMZCXYMS' TEXT," + // 危险因素控制高血压－是否每周测血压描述
                "'WXYSKZGXY_YYQK' TEXT," + // 危险因素控制高血压－用药情况
                "'WXYSKZTNB_FCRQ' TEXT," + // 危险因素控制糖尿病－复查日期
                "'WXYSKZTNB_XTSP' TEXT," + // 危险因素控制高血压－血糖水平
                "'WXYSKZTNB_HBAIC' TEXT," + // 危险因素控制高血压－HBaic
                "'WXYSKZTNB_YYQK' TEXT," + // 危险因素控制高血压－用药情况
                "'WXYSKZGXZ_FCRQ' TEXT," + // 危险因素控制高血脂－复查日期
                "'WXYSKZGXZ_TG' TEXT," + // 危险因素控制高血脂－TG
                "'WXYSKZGXZ_TC' TEXT," + // 危险因素控制高血脂－TC
                "'WXYSKZGXZ_LDL' TEXT," + // 危险因素控制高血脂－LDL
                "'WXYSKZGXZ_HDL' TEXT," + // 危险因素控制高血脂－HDL
                "'WXYSKZGXZ__LP' TEXT," + // 危险因素控制高血脂_LP
                "'WXYSKZGXZ_YYQK' TEXT," + // 危险因素控制高血脂－用药情况
                "'ZDSJ_SFZD' INTEGER," + // 终点事件－是否终点
                "'ZDSJ_XFCZSJZD' TEXT," + // 终点事件－新发卒中事件诊断
                "'ZDSJ_QTXGSJ' TEXT," + //  终点事件－其他血管事件
                "'ZDSJ_YDMC' TEXT," + //  终点事件－
                "'ZDSJ_YDSJ' TEXT," + //  终点事件－
                "'ZDSJ_KFDLMC' TEXT," + //  终点事件－
                "'ZDSJ_KFDLSJ' TEXT," + //  终点事件－
                "'ZDSJ_PF_MRS' TEXT," + //  终点事件－MRs
                "'ZDSJ_PF_MRSJG' TEXT," + // 终点事件－MRS结果
                "'ZDSJ_PF_BI' TEXT," + //  终点事件－Bi
                "'ZDSJ_PF_BIJG' TEXT," + //  终点事件－Bi结果
                "'ZDSJ_PF_SSPF' TEXT," + //  终点事件－评分－膳食评分
                "'ZDSJ_PF_SSPFJG' TEXT," + //  终点事件－评分－膳食评分结果
                "'ZDSJ_SFYSQM' TEXT," + //  终点事件－随访医生签名
                "'CREATE_TIME' TEXT," + //  创建时间
                "'UPDATE_TIME' TEXT);"); // 更新时间
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'FOLLOW_UP_STROKE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, FollowUpStroke entity) {
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
 
        Boolean shxggb_sfxy = entity.getShxggb_sfxy();
        if (shxggb_sfxy != null) {
            stmt.bindLong(5, shxggb_sfxy ? 1l: 0l);
        }
 
        String shxggb_sfxyms = entity.getShxggb_sfxyms();
        if (shxggb_sfxyms != null) {
            stmt.bindString(6, shxggb_sfxyms);
        }
 
        Boolean shxggb_sfyj = entity.getShxggb_sfyj();
        if (shxggb_sfyj != null) {
            stmt.bindLong(7, shxggb_sfyj ? 1l: 0l);
        }
 
        String shxggb_sfyjms = entity.getShxggb_sfyjms();
        if (shxggb_sfyjms != null) {
            stmt.bindString(8, shxggb_sfyjms);
        }
 
        String shxggb_ydmc = entity.getShxggb_ydmc();
        if (shxggb_ydmc != null) {
            stmt.bindString(9, shxggb_ydmc);
        }
 
        String shxggb_ydsj = entity.getShxggb_ydsj();
        if (shxggb_ydsj != null) {
            stmt.bindString(10, shxggb_ydsj);
        }
 
        String shxggb_kfdlmc = entity.getShxggb_kfdlmc();
        if (shxggb_kfdlmc != null) {
            stmt.bindString(11, shxggb_kfdlmc);
        }
 
        String shxggb_kfdlsj = entity.getShxggb_kfdlsj();
        if (shxggb_kfdlsj != null) {
            stmt.bindString(12, shxggb_kfdlsj);
        }
 
        String wxyskzgxy_sfmzcxy = entity.getWxyskzgxy_sfmzcxy();
        if (wxyskzgxy_sfmzcxy != null) {
            stmt.bindString(13, wxyskzgxy_sfmzcxy);
        }
 
        String wxyskzgxy_sfmzcxyms = entity.getWxyskzgxy_sfmzcxyms();
        if (wxyskzgxy_sfmzcxyms != null) {
            stmt.bindString(14, wxyskzgxy_sfmzcxyms);
        }
 
        String wxyskzgxy_yyqk = entity.getWxyskzgxy_yyqk();
        if (wxyskzgxy_yyqk != null) {
            stmt.bindString(15, wxyskzgxy_yyqk);
        }
 
        String wxyskztnb_fcrq = entity.getWxyskztnb_fcrq();
        if (wxyskztnb_fcrq != null) {
            stmt.bindString(16, wxyskztnb_fcrq);
        }
 
        String wxyskztnb_xtsp = entity.getWxyskztnb_xtsp();
        if (wxyskztnb_xtsp != null) {
            stmt.bindString(17, wxyskztnb_xtsp);
        }
 
        String wxyskztnb_hbaic = entity.getWxyskztnb_hbaic();
        if (wxyskztnb_hbaic != null) {
            stmt.bindString(18, wxyskztnb_hbaic);
        }
 
        String wxyskztnb_yyqk = entity.getWxyskztnb_yyqk();
        if (wxyskztnb_yyqk != null) {
            stmt.bindString(19, wxyskztnb_yyqk);
        }
 
        String wxyskzgxz_fcrq = entity.getWxyskzgxz_fcrq();
        if (wxyskzgxz_fcrq != null) {
            stmt.bindString(20, wxyskzgxz_fcrq);
        }
 
        String wxyskzgxz_tg = entity.getWxyskzgxz_tg();
        if (wxyskzgxz_tg != null) {
            stmt.bindString(21, wxyskzgxz_tg);
        }
 
        String wxyskzgxz_tc = entity.getWxyskzgxz_tc();
        if (wxyskzgxz_tc != null) {
            stmt.bindString(22, wxyskzgxz_tc);
        }
 
        String wxyskzgxz_ldl = entity.getWxyskzgxz_ldl();
        if (wxyskzgxz_ldl != null) {
            stmt.bindString(23, wxyskzgxz_ldl);
        }
 
        String wxyskzgxz_hdl = entity.getWxyskzgxz_hdl();
        if (wxyskzgxz_hdl != null) {
            stmt.bindString(24, wxyskzgxz_hdl);
        }
 
        String wxyskzgxz_LP = entity.getWxyskzgxz_LP();
        if (wxyskzgxz_LP != null) {
            stmt.bindString(25, wxyskzgxz_LP);
        }
 
        String wxyskzgxz_yyqk = entity.getWxyskzgxz_yyqk();
        if (wxyskzgxz_yyqk != null) {
            stmt.bindString(26, wxyskzgxz_yyqk);
        }
 
        Boolean zdsj_sfzd = entity.getZdsj_sfzd();
        if (zdsj_sfzd != null) {
            stmt.bindLong(27, zdsj_sfzd ? 1l: 0l);
        }
 
        String zdsj_xfczsjzd = entity.getZdsj_xfczsjzd();
        if (zdsj_xfczsjzd != null) {
            stmt.bindString(28, zdsj_xfczsjzd);
        }
 
        String zdsj_qtxgsj = entity.getZdsj_qtxgsj();
        if (zdsj_qtxgsj != null) {
            stmt.bindString(29, zdsj_qtxgsj);
        }
 
        String zdsj_ydmc = entity.getZdsj_ydmc();
        if (zdsj_ydmc != null) {
            stmt.bindString(30, zdsj_ydmc);
        }
 
        String zdsj_ydsj = entity.getZdsj_ydsj();
        if (zdsj_ydsj != null) {
            stmt.bindString(31, zdsj_ydsj);
        }
 
        String zdsj_kfdlmc = entity.getZdsj_kfdlmc();
        if (zdsj_kfdlmc != null) {
            stmt.bindString(32, zdsj_kfdlmc);
        }
 
        String zdsj_kfdlsj = entity.getZdsj_kfdlsj();
        if (zdsj_kfdlsj != null) {
            stmt.bindString(33, zdsj_kfdlsj);
        }
 
        String zdsj_pf_mrs = entity.getZdsj_pf_mrs();
        if (zdsj_pf_mrs != null) {
            stmt.bindString(34, zdsj_pf_mrs);
        }
 
        String zdsj_pf_mrsjg = entity.getZdsj_pf_mrsjg();
        if (zdsj_pf_mrsjg != null) {
            stmt.bindString(35, zdsj_pf_mrsjg);
        }
 
        String zdsj_pf_bi = entity.getZdsj_pf_bi();
        if (zdsj_pf_bi != null) {
            stmt.bindString(36, zdsj_pf_bi);
        }
 
        String zdsj_pf_bijg = entity.getZdsj_pf_bijg();
        if (zdsj_pf_bijg != null) {
            stmt.bindString(37, zdsj_pf_bijg);
        }
 
        String zdsj_pf_sspf = entity.getZdsj_pf_sspf();
        if (zdsj_pf_sspf != null) {
            stmt.bindString(38, zdsj_pf_sspf);
        }
 
        String zdsj_pf_sspfjg = entity.getZdsj_pf_sspfjg();
        if (zdsj_pf_sspfjg != null) {
            stmt.bindString(39, zdsj_pf_sspfjg);
        }
 
        String zdsj_sfysqm = entity.getZdsj_sfysqm();
        if (zdsj_sfysqm != null) {
            stmt.bindString(40, zdsj_sfysqm);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(41, createTime);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(42, updateTime);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public FollowUpStroke readEntity(Cursor cursor, int offset) {
        FollowUpStroke entity = new FollowUpStroke( //
            cursor.getString(offset + 0), // followUpNo
            cursor.getString(offset + 1), // idcard
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // grxx_zrys
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // grxx_sfrq
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // shxggb_sfxy
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // shxggb_sfxyms
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // shxggb_sfyj
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // shxggb_sfyjms
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // shxggb_ydmc
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // shxggb_ydsj
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // shxggb_kfdlmc
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // shxggb_kfdlsj
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // wxyskzgxy_sfmzcxy
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // wxyskzgxy_sfmzcxyms
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // wxyskzgxy_yyqk
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // wxyskztnb_fcrq
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // wxyskztnb_xtsp
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // wxyskztnb_hbaic
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // wxyskztnb_yyqk
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // wxyskzgxz_fcrq
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // wxyskzgxz_tg
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // wxyskzgxz_tc
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // wxyskzgxz_ldl
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // wxyskzgxz_hdl
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // wxyskzgxz_LP
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // wxyskzgxz_yyqk
            cursor.isNull(offset + 26) ? null : cursor.getShort(offset + 26) != 0, // zdsj_sfzd
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // zdsj_xfczsjzd
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // zdsj_qtxgsj
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // zdsj_ydmc
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // zdsj_ydsj
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // zdsj_kfdlmc
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // zdsj_kfdlsj
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // zdsj_pf_mrs
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // zdsj_pf_mrsjg
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // zdsj_pf_bi
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // zdsj_pf_bijg
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // zdsj_pf_sspf
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // zdsj_pf_sspfjg
            cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // zdsj_sfysqm
            cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40), // createTime
            cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41) // updateTime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, FollowUpStroke entity, int offset) {
        entity.setFollowUpNo(cursor.getString(offset + 0));
        entity.setIdcard(cursor.getString(offset + 1));
        entity.setGrxx_zrys(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGrxx_sfrq(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setShxggb_sfxy(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setShxggb_sfxyms(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setShxggb_sfyj(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setShxggb_sfyjms(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setShxggb_ydmc(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setShxggb_ydsj(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setShxggb_kfdlmc(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setShxggb_kfdlsj(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setWxyskzgxy_sfmzcxy(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setWxyskzgxy_sfmzcxyms(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setWxyskzgxy_yyqk(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setWxyskztnb_fcrq(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setWxyskztnb_xtsp(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setWxyskztnb_hbaic(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setWxyskztnb_yyqk(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setWxyskzgxz_fcrq(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setWxyskzgxz_tg(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setWxyskzgxz_tc(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setWxyskzgxz_ldl(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setWxyskzgxz_hdl(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setWxyskzgxz_LP(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setWxyskzgxz_yyqk(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setZdsj_sfzd(cursor.isNull(offset + 26) ? null : cursor.getShort(offset + 26) != 0);
        entity.setZdsj_xfczsjzd(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setZdsj_qtxgsj(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setZdsj_ydmc(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setZdsj_ydsj(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setZdsj_kfdlmc(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setZdsj_kfdlsj(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setZdsj_pf_mrs(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setZdsj_pf_mrsjg(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setZdsj_pf_bi(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setZdsj_pf_bijg(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setZdsj_pf_sspf(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setZdsj_pf_sspfjg(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setZdsj_sfysqm(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setCreateTime(cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40));
        entity.setUpdateTime(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(FollowUpStroke entity, long rowId) {
        return entity.getFollowUpNo();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(FollowUpStroke entity) {
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
