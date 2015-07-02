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

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.interfaces.ListItemClickHelp;
import com.shbestwin.followupmanager.model.followup.FollowUpStroke;
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.view.adapter.followup.MedicationListAdapter;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.MedicationDialog;

public class CerebralApoplexyBody3 extends LinearLayout implements
        IBaseCerebralApoplexyBody, ListItemClickHelp {
    private View medicationButton;

    private ListView medicationListView;

    private RadioButton rb_w, rb_y;

    private MedicationListAdapter medicationListAdapter;

    private List<Medication> medicationList = new ArrayList<Medication>();

    private RadioGroup rg_check;

    private EditText et_ssy, et_szy;

    private boolean isHas = false;

    public CerebralApoplexyBody3(Context context) {
        this(context, null);
    }

    public CerebralApoplexyBody3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CerebralApoplexyBody3(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_cerebral_apoplexy_body3, this, true);
        medicationButton = rootView.findViewById(R.id.medicationButton);
        medicationListView = (ListView) rootView
                .findViewById(R.id.medicationListView);
        rg_check = (RadioGroup) rootView.findViewById(R.id.rg_check);
        et_ssy = (EditText) rootView.findViewById(R.id.et_ssy);
        et_szy = (EditText) rootView.findViewById(R.id.et_szy);
        rb_w = (RadioButton) rootView.findViewById(R.id.rb_w);
        rb_y = (RadioButton) rootView.findViewById(R.id.rb_y);
        rg_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_w) {
                    isHas = false;
                } else if (checkedId == R.id.rb_y) {
                    isHas = true;
                }
            }
        });

        medicationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showMedicationDialog();
            }
        });

        medicationListAdapter = new MedicationListAdapter(getContext(),
                medicationList);
        medicationListAdapter.setListItemClickHelp(this);
        medicationListView.setAdapter(medicationListAdapter);
    }

    private void showMedicationDialog() {
        final MedicationDialog medicationDialog = MedicationDialog
                .newInstance();
        medicationDialog.show(
                ((FragmentActivity) getContext()).getSupportFragmentManager(),
                "medicationDialog");

        medicationDialog
                .setOnConfirmClickListener(new OnConfirmClickListener() {

                    @Override
                    public void onConfirmClick() {
                        Medication medication = medicationDialog
                                .getMedication();
                        medicationList.add(medication);
                        medicationDialog.hide();
                        medicationListAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void getData(FollowUpStroke followUpStroke) {
        followUpStroke.setWxyskzgxy_sfmzcxy(String.valueOf(isHas));
        followUpStroke.setWxyskzgxy_sfmzcxyms(et_ssy.getText().toString() + "/"
                + et_szy.getText().toString());
        try {
            followUpStroke.setWxyskzgxy_yyqk(JsonUtil
                    .objectsToJson(medicationList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setData(FollowUpStroke followUpStroke) {
        if (followUpStroke != null) {
            if (Boolean.valueOf(followUpStroke.getWxyskzgxy_sfmzcxy())) {
                rb_w.setChecked(false);
                rb_y.setChecked(true);
            } else {
                rb_w.setChecked(true);
                rb_y.setChecked(false);
            }
            String xy = followUpStroke.getWxyskzgxy_sfmzcxyms();
            if (xy.split("/").length ==1) {
                et_ssy.setText(xy.split("/")[0]);
            }else  if (xy.split("/").length ==2) {
                et_ssy.setText(xy.split("/")[0]);
                et_szy.setText(xy.split("/")[1]);
            }

            try {
                List<Medication> lists = JsonUtil.jsonToObjects(
                        followUpStroke.getWxyskzgxy_yyqk(), Medication.class);
                if (lists != null && lists.size() > 0) {
                    medicationList.addAll(lists);
                    medicationListAdapter.notifyDataSetChanged();
                }
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void setFragment(FragmentManager fragmentManager) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(final int position, int which) {
        Medication medication = medicationList.get(position);
        switch (which) {
        case R.id.im_delete:
            medicationList.remove(position);
            medicationListAdapter.notifyDataSetChanged();
            break;
        case R.id.im_edit:
            final MedicationDialog medicationDialog = new MedicationDialog(
                    medication);
            medicationDialog.show(((FragmentActivity) getContext())
                    .getSupportFragmentManager(), "medicationDialog");
            medicationDialog
                    .setOnConfirmClickListener(new OnConfirmClickListener() {

                        @Override
                        public void onConfirmClick() {
                            Medication item = medicationDialog.getMedication();
                            medicationList.set(position, item);
                            medicationDialog.hide();
                            medicationListAdapter.notifyDataSetChanged();
                        }
                    });

            break;

        default:
            break;
        }
    }
}
