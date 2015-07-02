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
import com.shbestwin.followupmanager.interfaces.ListItemClickHelp;
import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.view.adapter.followup.MedicationListAdapter;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.MedicationDialog;

public class MentalDiseaseBody7 extends LinearLayout implements IBaseMentalDiseaseBody ,ListItemClickHelp{

	private View mentalDiseaseMedicineButton;
	private ListView medicationListView;

	 private MedicationListAdapter medicationListAdapter;

	    private List<Medication> medicationList = new ArrayList<Medication>();

	public MentalDiseaseBody7(Context context) {
		this(context, null);
	}

	public MentalDiseaseBody7(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MentalDiseaseBody7(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_mental_disease_body7, this, true);
		mentalDiseaseMedicineButton = rootView.findViewById(R.id.mentalDiseaseMedicineButton);
		medicationListView = (ListView) rootView.findViewById(R.id.mentalDiseaseMedicineListView);

		mentalDiseaseMedicineButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMentalDiseaseMedicineDialog();
			}
		});

		 medicationListAdapter = new MedicationListAdapter(getContext(),
	                medicationList);
	        medicationListAdapter.setListItemClickHelp(this);
	        medicationListView.setAdapter(medicationListAdapter);
	}

	private void showMentalDiseaseMedicineDialog() {
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
	public void getData(FollowUpMentalDisease followUpMentalDisease) {
//	    try {
//	        followUpMentalDisease.set(JsonUtil
//                    .objectsToJson(medicationList));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
	}

	@Override
	public void setData(FollowUpMentalDisease followUpMentalDisease) {
//	    if (followUpMentalDisease != null) {
//            try {
//                List<Medication> lists=JsonUtil.jsonToObjects(followUpMentalDisease.getYyqk(), Medication.class);
//                if(lists!=null&&lists.size()>0) {
//                    medicationList.addAll(lists);
//                    medicationListAdapter.notifyDataSetChanged();
//                }
//               
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            
//        }
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
