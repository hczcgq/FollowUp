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
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.view.adapter.followup.MedicationListAdapter;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.MedicationDialog;

public class DiabetesMellitusBody5 extends LinearLayout implements  IBaseDiabetesMellitusBody,ListItemClickHelp{
	private View medicationButton;
	private ListView medicationListView;

	private MedicationListAdapter medicationListAdapter;
	private List<Medication> medicationList = new ArrayList<Medication>();
	
	public DiabetesMellitusBody5(Context context) {
		this(context, null);
	}

	public DiabetesMellitusBody5(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DiabetesMellitusBody5(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_diabetes_mellitus_body5, this, true);
		medicationButton = rootView.findViewById(R.id.medicationButton);
		medicationListView = (ListView) rootView.findViewById(R.id.medicationListView);

		medicationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMedicationDialog();
			}
		});
		medicationListAdapter = new MedicationListAdapter(getContext(), medicationList);
		medicationListAdapter.setListItemClickHelp(this);
		medicationListView.setAdapter(medicationListAdapter);
	}

	private void showMedicationDialog() {
		final MedicationDialog medicationDialog = MedicationDialog.newInstance();
		medicationDialog.show(((FragmentActivity) getContext()).getSupportFragmentManager(), "medicationDialog");
		
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
	public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		try {
            followUpDiabetesMellitus.setYyqk(JsonUtil.objectsToJson(medicationList));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void setData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
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
        Medication medication = medicationList.get(position);
        switch (which) {
        case R.id.im_delete:
            medicationList.remove(position);
            medicationListAdapter.notifyDataSetChanged();
            break;
        case R.id.im_edit:
            final MedicationDialog medicationDialog =new  MedicationDialog(medication);
            medicationDialog.show(((FragmentActivity) getContext()).getSupportFragmentManager(), "medicationDialog");
            medicationDialog
                    .setOnConfirmClickListener(new OnConfirmClickListener() {

                        @Override
                        public void onConfirmClick() {
                            Medication item = medicationDialog
                                    .getMedication();
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
