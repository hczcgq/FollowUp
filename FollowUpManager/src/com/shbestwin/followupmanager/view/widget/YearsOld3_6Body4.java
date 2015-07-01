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
import com.shbestwin.followupmanager.model.followup.FollowUpThreeSixNewborn;
import com.shbestwin.followupmanager.model.followup.LabInspection;
import com.shbestwin.followupmanager.view.adapter.followup.LabInspectionListAdapter;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.LabInspectionDialog;

public class YearsOld3_6Body4 extends LinearLayout  implements IBaseYearsOld3_6Body,ListItemClickHelp{
	private View labInspectionButton;
	private ListView labInspectionListView;

	private LabInspectionListAdapter inspectionListAdapter;
    
    List<LabInspection> inspectionList = new ArrayList<LabInspection>();
	
	public YearsOld3_6Body4(Context context) {
		this(context, null);
	}

	public YearsOld3_6Body4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YearsOld3_6Body4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_years_old_3_6_body4, this, true);
		labInspectionButton = rootView.findViewById(R.id.labInspectionButton);
		labInspectionListView = (ListView) rootView.findViewById(R.id.labInspectionListView);

		labInspectionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showInspectionDialog();
			}
		});

		inspectionListAdapter = new LabInspectionListAdapter(getContext(), inspectionList);
        inspectionListAdapter.setListItemClickHelp(this);
        labInspectionListView.setAdapter(inspectionListAdapter);
	}

	private void showInspectionDialog() {
    
        final LabInspectionDialog hypertensionInspectionDialog = LabInspectionDialog
                .newInstance();
        hypertensionInspectionDialog.show(
                ((FragmentActivity) getContext()).getSupportFragmentManager(),
                "hypertensionInspectionDialog");
        hypertensionInspectionDialog
                .setOnConfirmClickListener(new OnConfirmClickListener() {

                    @Override
                    public void onConfirmClick() {
                        LabInspection inspection = hypertensionInspectionDialog
                                .getInspection();
                        inspectionList.add(inspection);
                        hypertensionInspectionDialog.hide();
                        inspectionListAdapter.notifyDataSetChanged();
                    }
                });
    }
	
	@Override
	public void getData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
	    try {
	        followUpThreeSixNewborn
                    .setSysjc(JsonUtil.objectsToJson(inspectionList));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void setData(FollowUpThreeSixNewborn followUpThreeSixNewborn) {
		// TODO Auto-generated method stub
		
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

        LabInspection inspection = inspectionList.get(position);
        switch (which) {
        case R.id.im_delete:
            inspectionList.remove(position);
            inspectionListAdapter.notifyDataSetChanged();
            break;
        case R.id.im_edit:
            final LabInspectionDialog inspectionDialog = new LabInspectionDialog(
                    inspection);
            inspectionDialog.show(((FragmentActivity) getContext())
                    .getSupportFragmentManager(),
                    "hypertensionInspectionDialog");

            inspectionDialog
                    .setOnConfirmClickListener(new OnConfirmClickListener() {

                        @Override
                        public void onConfirmClick() {
                            LabInspection inspection = inspectionDialog
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
