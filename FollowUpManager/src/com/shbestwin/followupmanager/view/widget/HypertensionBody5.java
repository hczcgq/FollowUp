package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.interfaces.ListItemClickHelp;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;
import com.shbestwin.followupmanager.model.followup.Inspection;
import com.shbestwin.followupmanager.view.adapter.followup.InspectionListAdapter;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.InspectionDialog;

public class HypertensionBody5 extends LinearLayout implements
        IBaseHypertensionBody, ListItemClickHelp {
    private View assistInspectionButton;

    private ListView assistInspectionListView;

    private InspectionListAdapter inspectionListAdapter;

    List<Inspection> inspectionList = new ArrayList<Inspection>();

    public HypertensionBody5(Context context) {
        this(context, null);
    }

    public HypertensionBody5(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HypertensionBody5(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_hypertension_body5, this, true);
        assistInspectionButton = rootView
                .findViewById(R.id.assistInspectionButton);
        assistInspectionListView = (ListView) rootView
                .findViewById(R.id.assistInspectionListView);

        assistInspectionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showInspectionDialog();
            }
        });
        inspectionListAdapter = new InspectionListAdapter(getContext(),
                inspectionList);
        inspectionListAdapter.setListItemClickHelp(this);
        assistInspectionListView.setAdapter(inspectionListAdapter);
    }

    private void showInspectionDialog() {
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
    public void getData(FollowUpHypertension followUpHypertension) {
        try {
            followUpHypertension
                    .setFzjc(JsonUtil.objectsToJson(inspectionList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setData(FollowUpHypertension followUpHypertension) {

    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void setFragment(FragmentManager fragmentManager) {

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
