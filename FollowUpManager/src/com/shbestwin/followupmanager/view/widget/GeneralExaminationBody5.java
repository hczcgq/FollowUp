package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;

public class GeneralExaminationBody5 extends LinearLayout implements
        IBaseGeneralExaminationBody {
    private RadioGroup eyegroundRadioGroup, barrelChestRadioGroup,
            breathSoundsRadioGroup, murmurRadioGroup, tendernessRadioGroup,
            enclosedMassRadioGroup, hepatomegalyRadioGroup,
            splenauxeRadioGroup, voicedSoundRadioGroup,
            immersionFootRadioGroup, arteriopalmusRadioGroup,
            rectalTouchRadioGroup, breastRadioGroup, vulvaRadioGroup,
            vaginaRadioGroup, cervixRadioGroup, corporaUteriRadioGroup,
            adnexaRadioGroup;

    private EditText et_yd, et_pf, et_gm, et_lbj, et_hxy, et_ly, et_xl, et_zy,
            et_yt, et_bk, et_gd, et_pd, et_ydxzy, et_gmzz, et_rx, et_wy,
            et_yind, et_gj, et_gt, et_fj, et_qt;

    private Spinner sn_xl;

    private RelativeLayout skinLayout;

    private LinearLayout scleraLayout, lymphGlandLayout, raleLayout;

    private boolean isEye = false, isBarre = true, isBreath = true,
            isMurm = false, isTender = false, isEnclose = false,
            isHepa = false, isSplen = false, isVoice = false;

    private String s_xzsz = "无", s_zbdmbd = "未触及", s_gmzz = "未见异常",
            s_rx = "未见异常", s_wy = "未见异常", s_yd = "未见异常", s_gj = "未见异常",
            s_gt = "未见异常", s_fj = "未见异常";

    public GeneralExaminationBody5(Context context) {
        this(context, null);
    }

    public GeneralExaminationBody5(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GeneralExaminationBody5(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_general_examination_body5, this, true);
        et_qt = (EditText) rootView.findViewById(R.id.et_qt);
        et_fj = (EditText) rootView.findViewById(R.id.et_fj);
        adnexaRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.adnexaRadioGroup);
        et_gt = (EditText) rootView.findViewById(R.id.et_gt);
        corporaUteriRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.corporaUteriRadioGroup);
        et_gj = (EditText) rootView.findViewById(R.id.et_gj);
        cervixRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.cervixRadioGroup);
        et_yind = (EditText) rootView.findViewById(R.id.et_yind);
        vaginaRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.vaginaRadioGroup);
        et_wy = (EditText) rootView.findViewById(R.id.et_wy);
        vulvaRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.vulvaRadioGroup);
        et_rx = (EditText) rootView.findViewById(R.id.et_rx);
        breastRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.breastRadioGroup);
        et_gmzz = (EditText) rootView.findViewById(R.id.et_gmzz);
        rectalTouchRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.rectalTouchRadioGroup);
        arteriopalmusRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.arteriopalmusRadioGroup);
        immersionFootRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.immersionFootRadioGroup);
        et_ydxzy = (EditText) rootView.findViewById(R.id.et_ydxzy);
        voicedSoundRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.voicedSoundRadioGroup);
        et_pd = (EditText) rootView.findViewById(R.id.et_pd);
        splenauxeRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.splenauxeRadioGroup);
        et_gd = (EditText) rootView.findViewById(R.id.et_gd);
        hepatomegalyRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.hepatomegalyRadioGroup);
        et_bk = (EditText) rootView.findViewById(R.id.et_bk);
        enclosedMassRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.enclosedMassRadioGroup);
        et_yt = (EditText) rootView.findViewById(R.id.et_yt);
        tendernessRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.tendernessRadioGroup);
        et_zy = (EditText) rootView.findViewById(R.id.et_zy);
        murmurRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.murmurRadioGroup);
        sn_xl = (Spinner) rootView.findViewById(R.id.sn_xl);
        et_xl = (EditText) rootView.findViewById(R.id.et_xl);
        et_ly = (EditText) rootView.findViewById(R.id.et_ly);
        raleLayout = (LinearLayout) rootView.findViewById(R.id.raleLayout);
        et_lbj = (EditText) rootView.findViewById(R.id.et_lbj);
        lymphGlandLayout = (LinearLayout) rootView
                .findViewById(R.id.lymphGlandLayout);
        et_gm = (EditText) rootView.findViewById(R.id.et_gm);
        scleraLayout = (LinearLayout) rootView.findViewById(R.id.scleraLayout);
        et_pf = (EditText) rootView.findViewById(R.id.et_pf);
        skinLayout = (RelativeLayout) rootView.findViewById(R.id.skinLayout);
        et_yd = (EditText) rootView.findViewById(R.id.et_yd);
        et_hxy = (EditText) rootView.findViewById(R.id.et_hxy);
        eyegroundRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.eyegroundRadioGroup);
        barrelChestRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.barrelChestRadioGroup);
        breathSoundsRadioGroup = (RadioGroup) rootView
                .findViewById(R.id.breathSoundsRadioGroup);
        eyegroundRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.eyegroundRadioButton0) {
                            isEye = false;
                        } else {
                            isEye = true;
                        }
                    }
                });
        barrelChestRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.barrelChestRadioButton1) {
                            isBarre = true;
                        } else {
                            isBarre = false;
                        }
                    }
                });
        breathSoundsRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.breathSoundsRadioButton0) {
                            isBreath = true;
                        } else {
                            isBreath = false;
                        }
                    }
                });
        murmurRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.murmurRadioButton0) {
                            isMurm = false;
                        } else {
                            isMurm = true;
                        }
                    }
                });
        tendernessRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.tendernessRadioButton0) {
                            isTender = false;
                        } else {
                            isTender = true;
                        }
                    }
                });
        enclosedMassRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.enclosedMassRadioButton0) {
                            isEnclose = false;
                        } else {
                            isEnclose = true;
                        }
                    }
                });
        hepatomegalyRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.hepatomegalyRadioButton0) {
                            isHepa = false;
                        } else {
                            isHepa = true;
                        }
                    }
                });
        splenauxeRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.splenauxeRadioButton0) {
                            isSplen = false;
                        } else {
                            isSplen = true;
                        }
                    }
                });
        voicedSoundRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.voicedSoundRadioButton0) {
                            isVoice = false;
                        } else {
                            isVoice = true;
                        }
                    }
                });

        immersionFootRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.immersionFootRadioButton0) {
                            s_xzsz = "无";
                        } else if (checkid == R.id.immersionFootRadioButton1) {
                            s_xzsz = "单侧";
                        } else if (checkid == R.id.immersionFootRadioButton2) {
                            s_xzsz = "双侧不对称";
                        } else if (checkid == R.id.immersionFootRadioButton3) {
                            s_xzsz = "双侧对称";
                        }
                    }
                });
        arteriopalmusRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.arteriopalmusRadioButton0) {
                            s_zbdmbd = "未触及";
                        } else if (checkid == R.id.arteriopalmusRadioButton1) {
                            s_zbdmbd = "触及双侧对称";
                        } else if (checkid == R.id.arteriopalmusRadioButton2) {
                            s_zbdmbd = "触及左侧弱或消失";
                        } else if (checkid == R.id.arteriopalmusRadioButton3) {
                            s_zbdmbd = "触及右侧弱或消失";
                        }
                    }
                });

        rectalTouchRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.rectalTouchRadioButton0) {
                            s_gmzz = "未及异常";
                        } else if (checkid == R.id.rectalTouchRadioButton1) {
                            s_gmzz = "触痛";
                        } else if (checkid == R.id.rectalTouchRadioButton2) {
                            s_gmzz = "包块";
                        } else if (checkid == R.id.rectalTouchRadioButton3) {
                            s_gmzz = "前列腺异常";
                        } else if (checkid == R.id.rectalTouchRadioButton4) {
                            s_gmzz = null;
                        } else if (checkid == R.id.rectalTouchRadioButton5) {
                            s_gmzz = "拒检";
                        }
                    }
                });

        breastRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.breastRadioButton0) {
                            s_rx = "未及异常";
                        } else if (checkid == R.id.breastRadioButton1) {
                            s_rx = "乳房切除";
                        } else if (checkid == R.id.breastRadioButton2) {
                            s_rx = "异常泌乳";
                        } else if (checkid == R.id.breastRadioButton3) {
                            s_rx = "乳腺包块";
                        } else if (checkid == R.id.breastRadioButton4) {
                            s_rx = null;
                        } else if (checkid == R.id.breastRadioButton5) {
                            s_rx = "拒检";
                        }
                    }
                });

        vulvaRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.vulvaRadioButton0) {
                            s_wy = "未见异常";
                        } else if (checkid == R.id.vulvaRadioButton1) {
                            s_wy = null;
                        } else if (checkid == R.id.vulvaRadioButton2) {
                            s_wy = "拒检";
                        }
                    }
                });
        vaginaRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.vaginaRadioButton0) {
                            s_yd = "未见异常";
                        } else if (checkid == R.id.vaginaRadioButton1) {
                            s_yd = null;
                        } else if (checkid == R.id.vaginaRadioButton2) {
                            s_yd = "拒检";
                        }
                    }
                });
        cervixRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.cervixRadioButton0) {
                            s_gj = "未见异常";
                        } else if (checkid == R.id.cervixRadioButton1) {
                            s_gj = null;
                        } else if (checkid == R.id.cervixRadioButton2) {
                            s_gj = "拒检";
                        }
                    }
                });
        corporaUteriRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.corporaUteriRadioButton0) {
                            s_gt = "未见异常";
                        } else if (checkid == R.id.corporaUteriRadioButton1) {
                            s_gt = null;
                        } else if (checkid == R.id.corporaUteriRadioButton2) {
                            s_gt = "拒检";
                        }
                    }
                });
        adnexaRadioGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int checkid) {
                        if (checkid == R.id.adnexaRadioButton0) {
                            s_fj = "未见异常";
                        } else if (checkid == R.id.adnexaRadioButton1) {
                            s_fj = null;
                        } else if (checkid == R.id.adnexaRadioButton2) {
                            s_fj = "拒检";
                        }
                    }
                });

    }

    @Override
    public void getData(GeneralExamination generalExamination) {
        if (s_gmzz == null) {
            s_gmzz = et_gmzz.getText().toString();
        }
        if (s_rx == null) {
            s_rx = et_rx.getText().toString();
        }
        if (s_wy == null) {
            s_wy = et_wy.getText().toString();
        }
        if (s_yd == null) {
            s_yd = et_yind.getText().toString();
        }
        if (s_gj == null) {
            s_gj = et_gj.getText().toString();
        }
        if (s_gt == null) {
            s_gt = et_gt.getText().toString();
        }
        if (s_fj == null) {
            s_fj = et_fj.getText().toString();
        }

        generalExamination.setCt_sfydyc(isEye);
        generalExamination.setCt_ydycms(et_yd.getText().toString());
        generalExamination.setCt_pf(ViewDataUtil.getCheckboxData(skinLayout,
                et_pf));
        generalExamination.setCt_gm(ViewDataUtil.getCheckboxData(scleraLayout,
                et_gm));
        generalExamination.setCt_lbj(ViewDataUtil.getCheckboxData(
                lymphGlandLayout, et_lbj));
        generalExamination.setCt_sftzx(isBarre);
        generalExamination.setCt_sfhxy(isBreath);
        generalExamination.setCt_hxyms(et_hxy.getText().toString());
        generalExamination.setCt_lbj(ViewDataUtil.getCheckboxData(raleLayout,
                et_ly));
        generalExamination.setCt_rate(et_xl.getText().toString());
        generalExamination.setCt_rhythm(ViewDataUtil.getSpinnerData(sn_xl));
        generalExamination.setCt_sfzy(isMurm);
        generalExamination.setCt_zyms(et_zy.getText().toString());
        generalExamination.setCt_sfyt(isTender);
        generalExamination.setCt_ytms(et_yt.getText().toString());
        generalExamination.setCt_sfbk(isEnclose);
        generalExamination.setCt_bkms(et_bk.getText().toString());
        generalExamination.setCt_sfgd(isHepa);
        generalExamination.setCt_gdms(et_gd.getText().toString());
        generalExamination.setCt_sfpd(isSplen);
        generalExamination.setCt_pdms(et_pd.getText().toString());
        generalExamination.setCt_sfydxzy(isVoice);
        generalExamination.setCt_ydxzyms(et_ydxzy.getText().toString());
        generalExamination.setCt_xzsz(s_xzsz);
        generalExamination.setCt_zbdmbd(s_zbdmbd);
        generalExamination.setCt_kmzz(s_gmzz);
        generalExamination.setCt_rx(s_rx);
        generalExamination.setCt_wy(s_wy);
        generalExamination.setCt_yd(s_yd);
        generalExamination.setCt_(s_gj);
        generalExamination.setCt_gt(s_gt);
        generalExamination.setCt_fj(s_fj);
        generalExamination.setCt_qt(et_qt.getText().toString());

    }

    @Override
    public void setData(GeneralExamination generalExamination) {
        if (generalExamination != null) {
            if (generalExamination.getCt_sfydyc()) {
                eyegroundRadioGroup.check(R.id.eyegroundRadioButton1);
            } else {
                eyegroundRadioGroup.check(R.id.eyegroundRadioButton0);
            }
            et_yd.setText(generalExamination.getCt_ydycms());
            ViewDataUtil.setCheckboxData(skinLayout, et_pf,
                    generalExamination.getCt_pf());
            ViewDataUtil.setCheckboxData(scleraLayout, et_gm,
                    generalExamination.getCt_gm());
            ViewDataUtil.setCheckboxData(lymphGlandLayout, et_lbj,
                    generalExamination.getCt_lbj());
            if (generalExamination.getCt_sftzx()) {
                barrelChestRadioGroup.check(R.id.barrelChestRadioButton1);
            } else {
                barrelChestRadioGroup.check(R.id.barrelChestRadioButton2);
            }
            if (generalExamination.getCt_sfhxy()) {
                breathSoundsRadioGroup.check(R.id.breathSoundsRadioButton0);
            } else {
                breathSoundsRadioGroup.check(R.id.breathSoundsRadioButton1);
            }
            et_hxy.setText(generalExamination.getCt_hxyms());
            ViewDataUtil.setCheckboxData(raleLayout, et_ly,
                    generalExamination.getCt_ly());
            et_xl.setText(generalExamination.getCt_rate());
            ViewDataUtil.setSpinnerData(sn_xl,
                    generalExamination.getCt_rhythm());
            if (generalExamination.getCt_sfzy()) {
                murmurRadioGroup.check(R.id.murmurRadioButton0);
            } else {
                murmurRadioGroup.check(R.id.murmurRadioButton1);
            }
            et_zy.setText(generalExamination.getCt_zyms());
            if (generalExamination.getCt_sfyt()) {
                tendernessRadioGroup.check(R.id.tendernessRadioButton1);
            } else {
                tendernessRadioGroup.check(R.id.tendernessRadioButton0);
            }
            et_yt.setText(generalExamination.getCt_ytms());

            if (generalExamination.getCt_sfbk()) {
                enclosedMassRadioGroup.check(R.id.enclosedMassRadioButton1);
            } else {
                enclosedMassRadioGroup.check(R.id.enclosedMassRadioButton0);
            }
            et_bk.setText(generalExamination.getCt_bkms());
            if (generalExamination.getCt_sfgd()) {
                hepatomegalyRadioGroup.check(R.id.hepatomegalyRadioButton1);
            } else {
                hepatomegalyRadioGroup.check(R.id.hepatomegalyRadioButton0);
            }
            et_gd.setText(generalExamination.getCt_gdms());
            if (generalExamination.getCt_sfpd()) {
                splenauxeRadioGroup.check(R.id.splenauxeRadioButton1);
            } else {
                splenauxeRadioGroup.check(R.id.splenauxeRadioButton0);
            }
            et_pd.setText(generalExamination.getCt_pdms());
            if (generalExamination.getCt_sfydxzy()) {
                voicedSoundRadioGroup.check(R.id.voicedSoundRadioButton1);
            } else {
                voicedSoundRadioGroup.check(R.id.voicedSoundRadioButton0);
            }
            et_ydxzy.setText(generalExamination.getCt_ydxzyms());

            if (generalExamination.getCt_xzsz() != null
                    && generalExamination.getCt_xzsz() != "") {
                if (generalExamination.getCt_xzsz().equals("无")) {
                    immersionFootRadioGroup
                            .check(R.id.immersionFootRadioButton0);
                } else if (generalExamination.getCt_xzsz().equals("单侧")) {
                    immersionFootRadioGroup
                            .check(R.id.immersionFootRadioButton1);
                } else if (generalExamination.getCt_xzsz().equals("双侧不对称")) {
                    immersionFootRadioGroup
                            .check(R.id.immersionFootRadioButton2);
                } else if (generalExamination.getCt_xzsz().equals("双侧对称")) {
                    immersionFootRadioGroup
                            .check(R.id.immersionFootRadioButton3);
                }
            }
            if (generalExamination.getCt_zbdmbd() != null
                    && generalExamination.getCt_zbdmbd() != "") {
                if (generalExamination.getCt_zbdmbd().equals("未触及")) {
                    arteriopalmusRadioGroup
                            .check(R.id.arteriopalmusRadioButton0);
                } else if (generalExamination.getCt_zbdmbd().equals("触及双侧对称")) {
                    arteriopalmusRadioGroup
                            .check(R.id.arteriopalmusRadioButton1);
                } else if (generalExamination.getCt_zbdmbd().equals("触及左侧弱或消失")) {
                    arteriopalmusRadioGroup
                            .check(R.id.arteriopalmusRadioButton2);
                } else if (generalExamination.getCt_zbdmbd().equals("触及右侧弱或消失")) {
                    arteriopalmusRadioGroup
                            .check(R.id.arteriopalmusRadioButton3);
                }
            }

            if (generalExamination.getCt_kmzz() != null
                    && generalExamination.getCt_kmzz() != "") {
                if (generalExamination.getCt_kmzz().equals("未及异常")) {
                    rectalTouchRadioGroup.check(R.id.rectalTouchRadioButton0);
                } else if (generalExamination.getCt_kmzz().equals("触痛")) {
                    rectalTouchRadioGroup.check(R.id.rectalTouchRadioButton1);
                } else if (generalExamination.getCt_kmzz().equals("包块")) {
                    rectalTouchRadioGroup.check(R.id.rectalTouchRadioButton2);
                } else if (generalExamination.getCt_kmzz().equals("前列腺异常")) {
                    rectalTouchRadioGroup.check(R.id.rectalTouchRadioButton3);
                } else if (generalExamination.getCt_kmzz().equals("拒检")) {
                    rectalTouchRadioGroup.check(R.id.rectalTouchRadioButton5);
                } else {
                    rectalTouchRadioGroup.check(R.id.rectalTouchRadioButton4);
                    et_gmzz.setText(generalExamination.getCt_kmzz());
                }
            }
            if (generalExamination.getCt_rx() != null
                    && generalExamination.getCt_rx() != "") {
                if (generalExamination.getCt_rx().equals("未及异常")) {
                    breastRadioGroup.check(R.id.breastRadioButton0);
                } else if (generalExamination.getCt_rx().equals("乳房切除")) {
                    breastRadioGroup.check(R.id.breastRadioButton1);
                } else if (generalExamination.getCt_rx().equals("异常泌乳")) {
                    breastRadioGroup.check(R.id.breastRadioButton2);
                } else if (generalExamination.getCt_rx().equals("乳腺包块")) {
                    breastRadioGroup.check(R.id.breastRadioButton3);
                } else if (generalExamination.getCt_rx().equals("拒检")) {
                    breastRadioGroup.check(R.id.breastRadioButton5);
                } else {
                    breastRadioGroup.check(R.id.breastRadioButton4);
                    et_rx.setText(generalExamination.getCt_rx());
                }
            }

            if (generalExamination.getCt_wy() != null
                    && generalExamination.getCt_wy() != "") {
                if (generalExamination.getCt_wy().equals("未见异常")) {
                    vulvaRadioGroup.check(R.id.vulvaRadioButton0);
                } else if (generalExamination.getCt_wy().equals("拒检")) {
                    vulvaRadioGroup.check(R.id.vulvaRadioButton2);
                } else {
                    vulvaRadioGroup.check(R.id.vulvaRadioButton1);
                    et_wy.setText(generalExamination.getCt_wy());
                }
            }

            if (generalExamination.getCt_yd() != null
                    && generalExamination.getCt_yd() != "") {
                if (generalExamination.getCt_yd().equals("未见异常")) {
                    vaginaRadioGroup.check(R.id.vaginaRadioButton0);
                } else if (generalExamination.getCt_yd().equals("拒检")) {
                    vaginaRadioGroup.check(R.id.vaginaRadioButton2);
                } else {
                    vaginaRadioGroup.check(R.id.vaginaRadioButton1);
                    et_yind.setText(generalExamination.getCt_yd());
                }
            }

            if (generalExamination.getCt_() != null
                    && generalExamination.getCt_() != "") {
                if (generalExamination.getCt_().equals("未见异常")) {
                    cervixRadioGroup.check(R.id.cervixRadioButton0);
                } else if (generalExamination.getCt_().equals("拒检")) {
                    cervixRadioGroup.check(R.id.cervixRadioButton2);
                } else {
                    cervixRadioGroup.check(R.id.cervixRadioButton1);
                    et_gj.setText(generalExamination.getCt_());
                }
            }

            if (generalExamination.getCt_gt() != null
                    && generalExamination.getCt_gt() != "") {
                if (generalExamination.getCt_gt().equals("未见异常")) {
                    corporaUteriRadioGroup.check(R.id.corporaUteriRadioButton0);
                } else if (generalExamination.getCt_gt().equals("拒检")) {
                    corporaUteriRadioGroup.check(R.id.corporaUteriRadioButton2);
                } else {
                    corporaUteriRadioGroup.check(R.id.corporaUteriRadioButton1);
                    et_gt.setText(generalExamination.getCt_gt());
                }
            }

            if (generalExamination.getCt_fj() != null
                    && generalExamination.getCt_fj() != "") {
                if (generalExamination.getCt_fj().equals("未见异常")) {
                    adnexaRadioGroup.check(R.id.adnexaRadioButton0);
                } else if (generalExamination.getCt_fj().equals("拒检")) {
                    adnexaRadioGroup.check(R.id.adnexaRadioButton1);
                } else {
                    adnexaRadioGroup.check(R.id.adnexaRadioButton2);
                    et_fj.setText(generalExamination.getCt_fj());
                }
            }

            et_qt.setText(generalExamination.getCt_qt());
        }
    }

    @Override
    public boolean validate() {
        return true;
    }
}
