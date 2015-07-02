package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.interfaces.ListItemClickHelp;
import com.shbestwin.followupmanager.model.followup.FollowUpStroke;
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.view.adapter.followup.MedicationListAdapter;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;
import com.shbestwin.followupmanager.view.dialog.followup.MedicationDialog;
import com.tencent.bugly.proguard.f;

public class CerebralApoplexyBody4 extends LinearLayout implements
        IBaseCerebralApoplexyBody, ListItemClickHelp {
    private View medicationButton;

    private ListView medicationListView;

    private MedicationListAdapter medicationListAdapter;

    private List<Medication> medicationList = new ArrayList<Medication>();

    private EditText et_fcrq, et_xtsp, et_hbaic;

    private FragmentManager fragmentManager;

    public CerebralApoplexyBody4(Context context) {
        this(context, null);
    }

    public CerebralApoplexyBody4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CerebralApoplexyBody4(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_cerebral_apoplexy_body4, this, true);
        medicationButton = rootView.findViewById(R.id.medicationButton);

        medicationListView = (ListView) rootView
                .findViewById(R.id.medicationListView);
        et_fcrq = (EditText) rootView.findViewById(R.id.et_fcrq);
        et_xtsp = (EditText) rootView.findViewById(R.id.et_xtsp);
        et_hbaic = (EditText) rootView.findViewById(R.id.et_hbaic);
        et_fcrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
        et_fcrq.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = DatePickerDialog
                        .newInstance();
                datePickerDialog.show(fragmentManager, "datePickerDialog");
                datePickerDialog
                        .setOnDatePickerListener(new OnDatePickerListener() {
                            @Override
                            public void onConfirmClick(long timeInMillis,
                                    String formatDate) {
                                et_fcrq.setText(formatDate);
                                datePickerDialog.hide();
                            }
                        });
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

        followUpStroke.setWxyskztnb_fcrq(et_fcrq.getText().toString());
        followUpStroke.setWxyskztnb_xtsp(et_xtsp.getText().toString());
        followUpStroke.setWxyskztnb_hbaic(et_hbaic.getText().toString());
        try {
            followUpStroke.setWxyskztnb_yyqk(JsonUtil
                    .objectsToJson(medicationList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setData(FollowUpStroke followUpStroke) {
        if (followUpStroke != null) {
            et_fcrq.setText(followUpStroke.getWxyskztnb_fcrq());
            et_xtsp.setText(followUpStroke.getWxyskztnb_xtsp());
            et_hbaic.setText(followUpStroke.getWxyskztnb_hbaic());

            try {
                List<Medication> lists = JsonUtil.jsonToObjects(
                        followUpStroke.getWxyskztnb_yyqk(), Medication.class);
                if (lists != null && lists.size() > 0) {
                    medicationList.addAll(lists);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
        this.fragmentManager = fragmentManager;
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
