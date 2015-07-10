package com.shbestwin.followupmanager.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.CollectionUtils;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.dao.DaoMaster;
import com.shbestwin.followupmanager.dao.DaoMaster.DevOpenHelper;
import com.shbestwin.followupmanager.dao.DaoSession;
import com.shbestwin.followupmanager.dao.ExaminationInfoDao;
import com.shbestwin.followupmanager.dao.GeneralExaminationDao;
import com.shbestwin.followupmanager.fragment.examination.AgednessTestFragment;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;
import com.shbestwin.followupmanager.model.examination.Question;

public class ExaminationManager {
    private static ExaminationManager instance = null;

    private Context mContext = null;

    private ExaminationInfoDao mExaminationInfoDao;

    private GeneralExaminationDao mGeneralExaminationDao;

    private ExaminationManager(Context applicContext) {
        mContext = applicContext;
        DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mExaminationInfoDao = daoSession.getExaminationInfoDao();
        mGeneralExaminationDao = daoSession.getGeneralExaminationDao();
    }

    public static synchronized ExaminationManager getInstance(Context context) {
        if (instance == null) {
            synchronized (ExaminationManager.class) {
                if (instance == null) {
                    instance = new ExaminationManager(
                            context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void saveOrUpdateExaminationInfo(ExaminationInfo examinationInfo) {
        mExaminationInfoDao.insertOrReplace(examinationInfo);
    }

    public ExaminationInfo getExaminationInfoById(String examinationNo) {
        return mExaminationInfoDao.load(examinationNo);
    }

    public ExaminationInfo getExaminationInfoByIdcard(String idcard) {
        StringBuilder where = new StringBuilder(" where ");
        where.append(ExaminationInfoDao.Properties.Idcard.columnName).append(
                "=?");
        where.append(" order by ")
                .append(ExaminationInfoDao.Properties.CreateTime.columnName)
                .append(" desc");
        List<ExaminationInfo> examinationInfoList = mExaminationInfoDao
                .queryRaw(where.toString(), idcard);
        if (!CollectionUtils.isEmpty(examinationInfoList)) {
            return examinationInfoList.get(0);
        }
        return null;
    }

    public void saveOrUpdateGeneralExamination(
            GeneralExamination generalExamination) {
        mGeneralExaminationDao.insertOrReplace(generalExamination);
    }

    public GeneralExamination getGeneralExaminationById(String examinationNo) {
        return mGeneralExaminationDao.load(examinationNo);
    }

    public List<Question> getPhysiqueQuestions() {
        List<Question> questions = null;
        XmlResourceParser parser = mContext.getResources().getXml(
                R.xml.test_physique);
        try {
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                case XmlPullParser.START_DOCUMENT: {
                    questions = new ArrayList<Question>();
                    break;
                }
                case XmlPullParser.START_TAG: {
                    if (parser.getName().contains("item")) {
                        String _type = parser.getAttributeValue(null, "type");
                        String _question = parser.nextText();
                        questions.add(new Question(_question, _type));
                    }
                    break;
                }
                case XmlPullParser.END_TAG: {
                    break;
                }
                }
                type = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public List<Question> getPsychologicaQuestions(String _type) {
        List<Question> questions = null;
        int resId = 0;
        if ("sds".equals(_type)) {
            resId = R.xml.test_sds;
        } else if ("sas".equals(_type)) {
            resId = R.xml.test_sas;
        } else if ("psqi".equals(_type)) {
            resId = R.xml.test_psqi;
        } else if ("saq".equals(_type)) {
            resId = R.xml.test_saq;
        } else if ("ucla".equals(_type)) {
            resId = R.xml.test_ucla;
        } else if ("gcq".equals(_type)) {
            resId = R.xml.test_gcq;
        } else if ("scl90".equals(_type)) {
            resId = R.xml.test_scl90;
        } else if ("qlsca".equals(_type)) {
            resId = R.xml.test_qlsca;
        }
        if (resId <= 0) {
            return null;
        }
        XmlResourceParser parser = mContext.getResources().getXml(resId);
        try {
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                case XmlPullParser.START_DOCUMENT: {
                    questions = new ArrayList<Question>();
                    break;
                }
                case XmlPullParser.START_TAG: {
                    if (parser.getName().contains("item")) {
                        String _question = parser.nextText();
                        questions.add(new Question(_question, _type));
                    }
                    break;
                }
                case XmlPullParser.END_TAG: {
                    break;
                }
                }
                type = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public List<String> getPsychologicaOptionss(String _type) {
        List<String> options = null;
        int resId = 0;
        if ("sds".equals(_type)) {
        } else if ("sas".equals(_type)) {
        } else if ("psqi".equals(_type)) {
            resId = R.xml.test_psqi_options;
        } else if ("saq".equals(_type)) {
        } else if ("ucla".equals(_type)) {
        } else if ("gcq".equals(_type)) {
        } else if ("scl90".equals(_type)) {
        } else if ("qlsca".equals(_type)) {
            resId = R.xml.test_qlsca_options;
        }
        if (resId <= 0) {
            return null;
        }
        XmlResourceParser parser = mContext.getResources().getXml(resId);
        try {
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                case XmlPullParser.START_DOCUMENT: {
                    options = new ArrayList<String>();
                    break;
                }
                case XmlPullParser.START_TAG: {
                    if (parser.getName().contains("item")) {
                        String option = parser.nextText();
                        options.add(option);
                    }
                    break;
                }
                case XmlPullParser.END_TAG: {
                    break;
                }
                }
                type = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return options;
    }

    public List<Question> getAgednessQuestions(int _type) {
        List<Question> questions = null;
        int resId = 0;
        switch (_type) {
        case AgednessTestFragment.TYPE_SELF_CARE:
            resId = R.xml.test_agedness_self_care;
            break;
        case AgednessTestFragment.TYPE_DEPRESSION:
            resId = R.xml.test_agedness_depression;
            break;
        case AgednessTestFragment.TYPE_INTELLIGENCE:
            resId = R.xml.test_agedness_intelligence;
            break;
        }
        if (resId <= 0) {
            return null;
        }
        XmlResourceParser parser = mContext.getResources().getXml(resId);
        try {
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                case XmlPullParser.START_DOCUMENT: {
                    questions = new ArrayList<Question>();
                    break;
                }
                case XmlPullParser.START_TAG: {
                    if (parser.getName().contains("item")) {
                        String _question = parser.nextText();
                        questions.add(new Question(_question, _type + ""));
                    }
                    break;
                }
                case XmlPullParser.END_TAG: {
                    break;
                }
                }
                type = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * 
     * @Title: getPrintTemplate
     * @Description: 获取打印模版数据
     * @param @param printResId
     * @param @param examinationNo
     * @param @return
     * @return String
     * @throws
     */
    public String getPrintTemplate(int printResId, String examinationNo) {
        StringBuilder result = new StringBuilder();
        InputStream in = null;
        BufferedReader br = null;
        try {
            in = mContext.getResources().openRawResource(printResId);
            String str;
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            while ((str = br.readLine()) != null) {
                result.append(str);
                result.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String data = "";

        if (!TextUtils.isEmpty(result)) {
            data = result.toString();
            // 替换标题
            data.replace("{title}", mContext.getString(R.string.print_title));

            ArchiveInfo archiveInfo = MyApplication.getInstance()
                    .getArchiveInfo();
            String personal_name = "";
            String personal_sex = "";
            String personal_age = "";
            if (archiveInfo != null) {
                personal_name = archiveInfo.getName();
                personal_sex = archiveInfo.getGender();
                personal_age = DateUtils.getAgeByBirthday(archiveInfo
                        .getBirthday()) + "";
            }
            // 替换用户信息
            data.replace("{personal_name}", personal_name)
                    .replace("{personal_sex}", personal_sex)
                    .replace("{personal_age}", personal_age)
                    .replace("{test_num}", examinationNo)
                    .replace("{community_name}",mContext.getString(R.string.print_community_name))
                    .replace(
                            "{community_address}",
                            mContext.getString(R.string.print_community_address))
                    .replace("{community_phone}",
                            mContext.getString(R.string.print_community_phone))
                    .replace(
                            "{community_service_hotline}",
                            mContext.getString(R.string.print_community_service_hotline));
        }
        return data;
    }
}
