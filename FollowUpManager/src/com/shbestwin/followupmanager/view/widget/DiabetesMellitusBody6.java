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
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;
import com.shbestwin.followupmanager.model.followup.Insulin;
import com.shbestwin.followupmanager.view.adapter.followup.InsulinListAdapter;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.InsulinDialog;

public class DiabetesMellitusBody6 extends LinearLayout implements
        IBaseDiabetesMellitusBody, ListItemClickHelp {
    private View insulinButton;

    private ListView insulinListView;

    private InsulinListAdapter insulinListAdapter;

    List<Insulin> insulinList = new ArrayList<Insulin>();

    public DiabetesMellitusBody6(Context context) {
        this(context, null);
    }

    public DiabetesMellitusBody6(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiabetesMellitusBody6(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.view_diabetes_mellitus_body6, this, true);
        insulinButton = rootView.findViewById(R.id.insulinButton);
        insulinListView = (ListView) rootView
                .findViewById(R.id.insulinListView);

        insulinButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showInsulinDialog();
            }
        });

        insulinListAdapter = new InsulinListAdapter(getContext(), insulinList);
        insulinListAdapter.setListItemClickHelp(this);
        insulinListView.setAdapter(insulinListAdapter);
    }

    private void showInsulinDialog() {
        final InsulinDialog insulinDialog = InsulinDialog.newInstance();
        insulinDialog.show(
                ((FragmentActivity) getContext()).getSupportFragmentManager(),
                "insulinDialog");
        insulinDialog.setOnConfirmClickListener(new OnConfirmClickListener() {

            @Override
            public void onConfirmClick() {
                Insulin insulin = insulinDialog.getInsulin();
                insulinList.add(insulin);
                insulinDialog.hide();
                insulinListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
        try {
            followUpDiabetesMellitus
                    .setYds(JsonUtil.objectsToJson(insulinList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
        if (followUpDiabetesMellitus != null) {
            try {
                List<Insulin> lists=JsonUtil.jsonToObjects(followUpDiabetesMellitus.getYds(), Insulin.class);
                if(lists!=null&&lists.size()>0) {
                    insulinList.addAll(lists);
                    insulinListAdapter.notifyDataSetChanged();
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
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(final int position, int which) {
        Insulin insulin = insulinList.get(position);
        switch (which) {
        case R.id.im_delete:
            insulinList.remove(position);
            insulinListAdapter.notifyDataSetChanged();
            break;
        case R.id.im_edit:
            final InsulinDialog inspectionDialog = new InsulinDialog(insulin);
            inspectionDialog.show(((FragmentActivity) getContext())
                    .getSupportFragmentManager(),
                    "hypertensionInspectionDialog");

            inspectionDialog
                    .setOnConfirmClickListener(new OnConfirmClickListener() {

                        @Override
                        public void onConfirmClick() {
                            Insulin item = inspectionDialog.getInsulin();
                            insulinList.set(position, item);
                            inspectionDialog.hide();
                            insulinListAdapter.notifyDataSetChanged();
                        }
                    });

            break;

        default:
            break;
        }
    }

}
