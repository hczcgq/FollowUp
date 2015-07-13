package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.interfaces.ListItemClickHelp;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.view.adapter.followup.MedicationListAdapter;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.MedicationDialog;

public class HypertensionBody9 extends LinearLayout  implements IBaseHypertensionBody,ListItemClickHelp{
    private RadioButton rb_w,rb_y;
	private RadioGroup rg_check;
	private EditText et_other;
	private LinearLayout ll_yyqk;
	private boolean isHas=false;
	
	 private View medicationButton;

	    private ListView medicationListView;

	    private MedicationListAdapter medicationListAdapter;

	    private List<Medication> medicationList = new ArrayList<Medication>();
	
	public HypertensionBody9(Context context) {
		this(context, null);
	}

	public HypertensionBody9(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody9(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_hypertension_body9, this, true);
		rg_check=(RadioGroup) rootView.findViewById(R.id.rg_check);
		et_other=(EditText) rootView.findViewById(R.id.et_other);
		rb_w=(RadioButton) rootView.findViewById(R.id.rb_w);
        rb_y=(RadioButton) rootView.findViewById(R.id.rb_y);
        ll_yyqk=(LinearLayout) findViewById(R.id.ll_yyqk);
		rg_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_w){
					isHas=false;
					ll_yyqk.setVisibility(View.GONE);
				}else if(checkedId==R.id.rb_y){
					isHas=true;
					ll_yyqk.setVisibility(View.VISIBLE);

				}
			}
		});
		
		
		 medicationButton = rootView.findViewById(R.id.medicationButton);
	        medicationListView = (ListView) rootView
	                .findViewById(R.id.medicationListView);

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
	public void getData(FollowUpHypertension followUpHypertension) {
		followUpHypertension.setYyqk_yy(isHas);
//		followUpHypertension.setYyqk_yyms(et_other.getText().toString());
		try {
			followUpHypertension.setYyqk_yyms(JsonUtil
			         .objectsToJson(medicationList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setData(FollowUpHypertension followUpHypertension) {
	    if(followUpHypertension!=null) {
//	        et_other.setText(followUpHypertension.getYyqk_yyms());
	        if(followUpHypertension.getYyqk_yy()) {
	            rb_w.setChecked(false);
                rb_y.setChecked(true);
				ll_yyqk.setVisibility(View.VISIBLE);
            }else {
                rb_w.setChecked(true);
                rb_y.setChecked(false);
				ll_yyqk.setVisibility(View.GONE);

            }
	        
	        try {
                List<Medication> lists=JsonUtil.jsonToObjects(followUpHypertension.getYyqk_yyms(), Medication.class);
                if(lists!=null&&lists.size()>0) {
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
