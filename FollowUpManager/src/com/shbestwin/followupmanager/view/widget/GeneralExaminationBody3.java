package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;
import com.shbestwin.followupmanager.model.examination.Poison;
import com.shbestwin.followupmanager.model.followup.Inspection;

public class GeneralExaminationBody3 extends LinearLayout implements
        IBaseGeneralExaminationBody {

    private Spinner sn_dlpl, sn_yjpl, sn_yjzl;

    private EditText et_mcdlsj, et_jcdlsj, et_dlfs, et_rxyl, et_ksxyln,
            et_jyln, et_ryjl, et_ksyjln, et_jjln, et_gz, et_cysj;

    private LinearLayout dietLayout;

    private RadioGroup rg_xyzk, rg_sfjj, rg_sfzj, rg_zybwhysjcs;

    private String s_xyzk = "从不吸烟";

    private boolean isJj = false, isZj = true, isZyb = false;

    private EditText et_fc, et_fc_qt, et_fswz, et_fswz_qt, et_wlys, et_wlys_qt,
            et_hxwz, et_hxwz_qt, et_qt, et_qt_qt;

    private RadioGroup rg_fc, rg_fswz, rg_wlys, rg_hxwz, rg_qt;

    private boolean isFc = false, isFswz = false, isWlys = false,
            isHxwz = false, isQt = false;

    public GeneralExaminationBody3(Context context) {
        this(context, null);
    }

    public GeneralExaminationBody3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GeneralExaminationBody3(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_general_examination_body3, this, true);
        et_fc = (EditText) rootView.findViewById(R.id.et_fc);
        et_fc_qt = (EditText) rootView.findViewById(R.id.et_fc_qt);
        et_fswz = (EditText) rootView.findViewById(R.id.et_fswz);
        et_fswz_qt = (EditText) rootView.findViewById(R.id.et_fswz_qt);
        et_wlys = (EditText) rootView.findViewById(R.id.et_wlys);
        et_wlys_qt = (EditText) rootView.findViewById(R.id.et_wlys_qt);
        et_hxwz = (EditText) rootView.findViewById(R.id.et_hxwz);
        et_hxwz_qt = (EditText) rootView.findViewById(R.id.et_hxwz_qt);
        et_qt = (EditText) rootView.findViewById(R.id.et_qt);
        et_qt_qt = (EditText) rootView.findViewById(R.id.et_qt_qt);

        rg_fc = (RadioGroup) rootView.findViewById(R.id.rg_fc);
        rg_fswz = (RadioGroup) rootView.findViewById(R.id.rg_fswz);
        rg_wlys = (RadioGroup) rootView.findViewById(R.id.rg_wlys);
        rg_hxwz = (RadioGroup) rootView.findViewById(R.id.rg_hxwz);
        rg_qt = (RadioGroup) rootView.findViewById(R.id.rg_qt);

        rg_xyzk = (RadioGroup) rootView.findViewById(R.id.rg_xyzk);
        rg_sfjj = (RadioGroup) rootView.findViewById(R.id.rg_sfjj);
        rg_sfzj = (RadioGroup) rootView.findViewById(R.id.rg_sfzj);
        rg_zybwhysjcs = (RadioGroup) rootView.findViewById(R.id.rg_zybwhysjcs);

        et_mcdlsj = (EditText) rootView.findViewById(R.id.et_mcdlsj);
        et_jcdlsj = (EditText) rootView.findViewById(R.id.et_jcdlsj);
        et_dlfs = (EditText) rootView.findViewById(R.id.et_dlfs);
        et_rxyl = (EditText) rootView.findViewById(R.id.et_rxyl);
        et_ksxyln = (EditText) rootView.findViewById(R.id.et_ksxyln);
        et_jyln = (EditText) rootView.findViewById(R.id.et_jyln);
        et_ryjl = (EditText) rootView.findViewById(R.id.et_ryjl);
        et_ksyjln = (EditText) rootView.findViewById(R.id.et_ksyjln);
        et_jjln = (EditText) rootView.findViewById(R.id.et_jjln);
        et_gz = (EditText) rootView.findViewById(R.id.et_gz);
        et_cysj = (EditText) rootView.findViewById(R.id.et_cysj);

        sn_dlpl = (Spinner) rootView.findViewById(R.id.sn_dlpl);
        sn_yjpl = (Spinner) rootView.findViewById(R.id.sn_yjpl);
        sn_yjzl = (Spinner) rootView.findViewById(R.id.sn_yjzl);

        dietLayout = (LinearLayout) rootView.findViewById(R.id.dietLayout);

        rg_xyzk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (arg1 == R.id.rb_xyzk_cbxy) {
                    s_xyzk = "从不吸烟";
                } else if (arg1 == R.id.rb_xyzk_yjy) {
                    s_xyzk = "过去吸，已戒烟";
                } else if (arg1 == R.id.rb_xyzk_xy) {
                    s_xyzk = "吸烟";
                }
            }
        });
        rg_sfzj.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (arg1 == R.id.rb_sfzj_s) {
                    isZj = true;
                } else {
                    isZj = false;
                }
            }
        });

        rg_sfjj.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (arg1 == R.id.rb_sfjj_yjj) {
                    isJj = true;
                } else {
                    isJj = true;
                }
            }
        });

        rg_zybwhysjcs.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (arg1 == R.id.rb_zybwhysjcs_w) {
                    isZyb = false;
                } else {
                    isZyb = true;
                }
            }
        });

        rg_fc.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (arg1 == R.id.dustRadioButton0) {
                    isFc = false;
                } else {
                    isFc = true;
                }
            }
        });
        rg_fswz.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (arg1 == R.id.radiogenRadioButton0) {
                    isFswz = false;
                } else {
                    isFswz = true;
                }
            }
        });
        rg_wlys.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (arg1 == R.id.physicalFactorRadioButton0) {
                    isWlys = false;
                } else {
                    isWlys = true;
                }
            }
        });
        rg_hxwz.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (arg1 == R.id.chemicalRadioButton0) {
                    isHxwz = false;
                } else {
                    isHxwz = true;
                }
            }
        });
        rg_qt.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (arg1 == R.id.otherRadioButton0) {
                    isQt = false;
                } else {
                    isQt = true;
                }
            }
        });
    }

    @Override
    public void getData(GeneralExamination generalExamination) {
        generalExamination.setShfs_dlpl(ViewDataUtil.getSpinnerData(sn_dlpl));
        generalExamination.setShfs_mcdlsj(et_mcdlsj.getText().toString());
        generalExamination.setShfs_jcdlsj(et_jcdlsj.getText().toString());
        generalExamination.setShfs_dlfs(et_dlfs.getText().toString());
        generalExamination.setShfs_ysxg(ViewDataUtil.getCheckboxData(
                dietLayout, null));
        generalExamination.setShfs_xyzk(s_xyzk);
        generalExamination.setShfs_rxyl(et_rxyl.getText().toString());
        generalExamination.setShfs_ksxynl(et_ksxyln.getText().toString());
        generalExamination.setShfs_jynl(et_jyln.getText().toString());
        generalExamination.setShfs_yjpl(ViewDataUtil.getSpinnerData(sn_yjpl));
        generalExamination.setShfs_ryjl(et_ryjl.getText().toString());
        generalExamination.setShfs_sfjy(isJj);
        generalExamination.setShfs_ksyjnl(et_ksyjln.getText().toString());
        generalExamination.setShfs_sfzj(isZj);
        generalExamination.setShfs_yjzl(ViewDataUtil.getSpinnerData(sn_yjzl));
        generalExamination.setShfs_jjnl(et_jjln.getText().toString());
        generalExamination.setShfs_zybwhysjcs(isZyb);
        generalExamination.setShfs_gz(et_gz.getText().toString());
        generalExamination.setShfs_cysj(et_cysj.getText().toString());

        List<Poison> lists = new ArrayList<Poison>();
        Poison poison_fc = new Poison(et_fc.getText().toString(), String.valueOf(isFc),
                et_fc_qt.getText().toString());
        Poison poison_fswz = new Poison(et_fswz.getText().toString(),  String.valueOf(isFswz),
                et_fswz_qt.getText().toString());
        Poison poison_wlys = new Poison(et_wlys.getText().toString(),  String.valueOf(isWlys),
                et_wlys_qt.getText().toString());
        Poison poison_hxys = new Poison(et_hxwz.getText().toString(),  String.valueOf(isHxwz),
                et_hxwz_qt.getText().toString());
        Poison poison_qt = new Poison(et_qt.getText().toString(),  String.valueOf(isQt),
                et_qt_qt.getText().toString());
        lists.add(poison_fc);
        lists.add(poison_fswz);
        lists.add(poison_wlys);
        lists.add(poison_hxys);
        lists.add(poison_qt);
        try {
            generalExamination.setShfs_dwzl(JsonUtil.objectsToJson(lists));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setData(GeneralExamination generalExamination) {
        if (generalExamination != null) {
            ViewDataUtil.setSpinnerData(sn_dlpl,
                    generalExamination.getShfs_dlpl());
            ViewDataUtil.setSpinnerData(sn_yjpl,
                    generalExamination.getShfs_yjpl());
            ViewDataUtil.setSpinnerData(sn_yjzl,
                    generalExamination.getShfs_yjzl());
            ViewDataUtil.setCheckboxData(dietLayout, null,
                    generalExamination.getShfs_ysxg());
            et_mcdlsj.setText(generalExamination.getShfs_mcdlsj());
            et_jcdlsj.setText(generalExamination.getShfs_jcdlsj());
            et_dlfs.setText(generalExamination.getShfs_dlfs());
            et_rxyl.setText(generalExamination.getShfs_rxyl());
            et_ksxyln.setText(generalExamination.getShfs_ksxynl());
            et_jyln.setText(generalExamination.getShfs_jynl());
            et_ryjl.setText(generalExamination.getShfs_ryjl());
            et_ksyjln.setText(generalExamination.getShfs_ksyjnl());
            et_jjln.setText(generalExamination.getShfs_jjnl());
            et_gz.setText(generalExamination.getShfs_gz());
            et_cysj.setText(generalExamination.getShfs_cysj());

            if (generalExamination.getShfs_xyzk() != null
                    && generalExamination.getShfs_xyzk() != "") {
                if (generalExamination.getShfs_xyzk().equals("从不吸烟")) {
                    rg_xyzk.check(R.id.rb_xyzk_cbxy);
                } else if (generalExamination.getShfs_xyzk().equals("过去吸，已戒烟")) {
                    rg_xyzk.check(R.id.rb_xyzk_yjy);
                } else if (generalExamination.getShfs_xyzk().equals("吸烟")) {
                    rg_xyzk.check(R.id.rb_xyzk_xy);
                }
            }

            if (generalExamination.getShfs_sfjy()) {
                rg_sfjj.check(R.id.rb_sfjj_yjj);
            } else {
                rg_sfjj.check(R.id.rb_sfjj_wjj);
            }

            if (generalExamination.getShfs_sfzj()) {
                rg_sfzj.check(R.id.rb_sfzj_s);
            } else {
                rg_sfzj.check(R.id.rb_sfzj_f);
            }

            if (generalExamination.getShfs_zybwhysjcs()) {
                rg_zybwhysjcs.check(R.id.rb_zjzsdzjwx_s);
            } else {
                rg_zybwhysjcs.check(R.id.rb_zjzsdzjwx_f);
            }

            List<Poison> lists;
            try {
                lists = JsonUtil.jsonToObjects(
                        generalExamination.getShfs_dwzl(), Poison.class);
                if (lists != null && lists.size() > 0) {
                	for(int i=0;i<lists.size();i++){
                		showDw(lists.get(i),i);
                	}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void showDw(Poison poison,int index){
    	if(index==0){
    		et_fc.setText(poison.getName());
    		et_fc_qt.setText(poison.getMeasurename());
    		if(Boolean.parseBoolean(poison.getIsmeasure())){
    			rg_fc.check(R.id.dustRadioButton1);
    		}else {
    			rg_fc.check(R.id.dustRadioButton0);
			}
    	}else if(index==1){
    		et_fswz.setText(poison.getName());
    		et_fswz_qt.setText(poison.getMeasurename());
    		if(Boolean.parseBoolean(poison.getIsmeasure())){
    			rg_fswz.check(R.id.radiogenRadioButton1);
    		}else {
    			rg_fswz.check(R.id.radiogenRadioButton0);
			}
    	}else if(index==2){
    		et_wlys.setText(poison.getName());
    		et_wlys_qt.setText(poison.getMeasurename());
    		if(Boolean.parseBoolean(poison.getIsmeasure())){
    			rg_wlys.check(R.id.physicalFactorRadioButton1);
    		}else {
    			rg_fc.check(R.id.physicalFactorRadioButton0);
			}
    	}else if(index==3){
    		et_hxwz.setText(poison.getName());
    		et_hxwz_qt.setText(poison.getMeasurename());
    		if(Boolean.parseBoolean(poison.getIsmeasure())){
    			rg_hxwz.check(R.id.chemicalRadioButton1);
    		}else {
    			rg_hxwz.check(R.id.chemicalRadioButton0);
			}
    	}else if(index==4){
    		et_qt.setText(poison.getName());
    		et_qt_qt.setText(poison.getMeasurename());
    		if(Boolean.parseBoolean(poison.getIsmeasure())){
    			rg_qt.check(R.id.otherRadioButton1);
    		}else {
    			rg_qt.check(R.id.otherRadioButton0);
			}
    	}
    }

    @Override
    public boolean validate() {
        return true;
    }
}
