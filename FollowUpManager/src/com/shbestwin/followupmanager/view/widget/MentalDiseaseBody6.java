package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.interfaces.ListItemClickHelp;
import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;
import com.shbestwin.followupmanager.model.followup.Inspection;
import com.shbestwin.followupmanager.view.adapter.followup.InspectionListAdapter;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.InspectionDialog;

public class MentalDiseaseBody6 extends LinearLayout implements
        IBaseMentalDiseaseBody, ListItemClickHelp {
    private View inspectionResultButton;

    private ListView assistInspectionListView;

    private InspectionListAdapter inspectionListAdapter;

    List<Inspection> inspectionList = new ArrayList<Inspection>();
    
    private Spinner sn_fyycx,sn_zlxg;
    private RadioGroup rg_ywblfy,rg_zz;
    private RadioButton rb_ywblfy_w,rb_ywblfy_y,rb_zz_w,rb_zz_y;
    private EditText et_ywblfy,et_yy,et_jgjks;
    private boolean is_ywblfy=false,is_zz=false;

    public MentalDiseaseBody6(Context context) {
        this(context, null);
    }

    public MentalDiseaseBody6(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MentalDiseaseBody6(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_mental_disease_body6, this, true);
        sn_fyycx=(Spinner) rootView.findViewById(R.id.sn_fyycx);
        sn_zlxg=(Spinner) rootView.findViewById(R.id.sn_zlxg);
        rg_ywblfy=(RadioGroup) rootView.findViewById(R.id.rg_ywblfy);
        rg_zz=(RadioGroup) rootView.findViewById(R.id.rg_zz);
        rb_ywblfy_w=(RadioButton) rootView.findViewById(R.id.rb_ywblfy_w);
        rb_ywblfy_y=(RadioButton) rootView.findViewById(R.id.rb_ywblfy_y);
        rb_zz_w=(RadioButton) rootView.findViewById(R.id.rb_zz_w);
        rb_zz_y=(RadioButton) rootView.findViewById(R.id.rb_zz_y);
        et_ywblfy=(EditText) rootView.findViewById(R.id.et_ywblfy);
        et_yy=(EditText) rootView.findViewById(R.id.et_yy);
        et_jgjks=(EditText) rootView.findViewById(R.id.et_jgjks);
        
        inspectionResultButton = rootView
                .findViewById(R.id.inspectionResultButton);
        assistInspectionListView = (ListView) rootView
                .findViewById(R.id.inspectionResultListView);

        inspectionResultButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showInspectionResultDialog();
            }
        });

        inspectionListAdapter = new InspectionListAdapter(getContext(),
                inspectionList);
        inspectionListAdapter.setListItemClickHelp(this);
        assistInspectionListView.setAdapter(inspectionListAdapter);
        
        
        rg_ywblfy.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if(arg1==R.id.rb_ywblfy_w) {
                    is_ywblfy=false;
                }else {
                    is_ywblfy=true;
                }
            }
        });
        rg_zz.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if(arg1==R.id.rb_zz_w) {
                    is_zz=false;
                }else {
                    is_zz=true;
                }
            }
        });
    }

    private void showInspectionResultDialog() {
        final InspectionDialog hypertensionInspectionDialog = InspectionDialog
                .newInstance();
        hypertensionInspectionDialog.show(
                ((FragmentActivity) getContext()).getSupportFragmentManager(),
                "hypertensionInspectionDialog");
        hypertensionInspectionDialog
                .setOnConfirmClickListener(new OnConfirmClickListener() {

                    @Override
                    public void onConfirmClick() {
                        Inspection inspection = hypertensionInspectionDialog
                                .getInspection();
                        inspectionList.add(inspection);
                        hypertensionInspectionDialog.hide();
                        inspectionListAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void getData(FollowUpMentalDisease followUpMentalDisease) {
        try {
            followUpMentalDisease.setSysfzjc_jcx(JsonUtil
                    .objectsToJson(inspectionList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        followUpMentalDisease.setSysfzjc_fyycx(ViewDataUtil.getSpinnerData(sn_fyycx));
        followUpMentalDisease.setShxggb_zlxg(ViewDataUtil.getSpinnerData(sn_zlxg));
        followUpMentalDisease.setShxggb_sfywblfyms(et_ywblfy.getText().toString());
        followUpMentalDisease.setShxggb_sfzzms(et_yy.getText().toString());
        followUpMentalDisease.setShxggb_jgqks(et_jgjks.getText().toString());
        followUpMentalDisease.setShxggb_sfywblfy(is_ywblfy);
        followUpMentalDisease.setShxggb_sfzz(is_zz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setData(FollowUpMentalDisease followUpMentalDisease) {
        if (followUpMentalDisease != null) {
            List<Inspection> lists;
            try {
                lists = JsonUtil.jsonToObjects(
                        followUpMentalDisease.getSysfzjc_jcx(),
                        Inspection.class);
                if (lists != null && lists.size() > 0) {
                    inspectionList.addAll(lists);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            ViewDataUtil.setSpinnerData(sn_fyycx, followUpMentalDisease.getSysfzjc_fyycx());
            ViewDataUtil.setSpinnerData(sn_zlxg, followUpMentalDisease.getShxggb_zlxg());
            et_jgjks.setText(followUpMentalDisease.getShxggb_jgqks());
            et_yy.setText(followUpMentalDisease.getShxggb_sfzzms());
            et_ywblfy.setText(followUpMentalDisease.getShxggb_sfywblfyms());
            if(followUpMentalDisease.getShxggb_sfywblfy()) {
                rb_ywblfy_w.setChecked(false);
                rb_ywblfy_y.setChecked(true);
            }else {
                rb_ywblfy_w.setChecked(true);
                rb_ywblfy_y.setChecked(false);
            }
            
            if(followUpMentalDisease.getShxggb_sfzz()) {
                rb_zz_w.setChecked(false);
                rb_zz_y.setChecked(true);
            }else {
                rb_zz_w.setChecked(true);
                rb_zz_y.setChecked(false);
            }

        }
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void setFragment(FragmentManager fragmentManager) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(final int position, int which) {
        Inspection inspection = inspectionList.get(position);
        switch (which) {
        case R.id.im_delete:
            inspectionList.remove(position);
            inspectionListAdapter.notifyDataSetChanged();
            break;
        case R.id.im_edit:
            final InspectionDialog inspectionDialog = new InspectionDialog(
                    inspection);
            inspectionDialog.show(((FragmentActivity) getContext())
                    .getSupportFragmentManager(),
                    "hypertensionInspectionDialog");

            inspectionDialog
                    .setOnConfirmClickListener(new OnConfirmClickListener() {

                        @Override
                        public void onConfirmClick() {
                            Inspection inspection = inspectionDialog
                                    .getInspection();
                            inspectionList.set(position, inspection);
                            inspectionDialog.hide();
                            inspectionListAdapter.notifyDataSetChanged();
                        }
                    });

            break;

        default:
            break;
        }
    }
}
