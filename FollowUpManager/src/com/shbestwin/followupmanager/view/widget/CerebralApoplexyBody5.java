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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

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

public class CerebralApoplexyBody5 extends LinearLayout implements IBaseCerebralApoplexyBody,ListItemClickHelp {
	private View medicationButton;
	private ListView medicationListView;

	 private MedicationListAdapter medicationListAdapter;

	    private List<Medication> medicationList = new ArrayList<Medication>();
	
	private EditText et_fcrq,et_tg,et_tc,et_ldl,et_hdl,et_lp;
	
	private RadioGroup rg_kxxbyw;
	private RadioButton rb_w,rb_y;
	private Spinner sn_zl,sn_yf;
	private EditText et_qthbyw;
	private boolean isHas=false;
	
	private FragmentManager fragmentManager;

	public CerebralApoplexyBody5(Context context) {
		this(context, null);
	}

	public CerebralApoplexyBody5(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CerebralApoplexyBody5(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_cerebral_apoplexy_body5, this, true);
		medicationButton = rootView.findViewById(R.id.medicationButton);
		medicationListView = (ListView) rootView.findViewById(R.id.medicationListView);
		et_fcrq = (EditText) rootView.findViewById(R.id.et_fcrq);
		et_tg = (EditText) rootView.findViewById(R.id.et_tg);
		et_tc = (EditText) rootView.findViewById(R.id.et_tc);
		et_ldl = (EditText) rootView.findViewById(R.id.et_ldl);
		et_hdl = (EditText) rootView.findViewById(R.id.et_hdl);
		et_lp = (EditText) rootView.findViewById(R.id.et_lp);
		rg_kxxbyw = (RadioGroup) rootView.findViewById(R.id.rg_kxxbyw);
		rb_w = (RadioButton) rootView.findViewById(R.id.rb_w);
		rb_y = (RadioButton) rootView.findViewById(R.id.rb_y);
		sn_zl = (Spinner) rootView.findViewById(R.id.sn_zl);
		sn_yf = (Spinner) rootView.findViewById(R.id.sn_yf);
		et_qthbyw = (EditText) rootView.findViewById(R.id.et_qthbyw);
		
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
		
		rg_kxxbyw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
               if(arg1==R.id.rb_w) {
                   isHas=false;
               }else {
                isHas=true;
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
		followUpStroke.setWxyskzgxz_fcrq(et_fcrq.getText().toString());
		followUpStroke.setWxyskzgxz_tg(et_tg.getText().toString());
		followUpStroke.setWxyskzgxz_tc(et_tc.getText().toString());
		followUpStroke.setWxyskzgxz_ldl(et_ldl.getText().toString());
		followUpStroke.setWxyskzgxz_hdl(et_hdl.getText().toString());
		followUpStroke.setWxyskzgxz_LP(et_lp.getText().toString());
		 try {
	            followUpStroke.setWxyskzgxz_yyqk(JsonUtil
	                    .objectsToJson(medicationList));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	@SuppressWarnings("unchecked")
    @Override
	public void setData(FollowUpStroke followUpStroke) {
	    if (followUpStroke != null) {
            et_fcrq.setText(followUpStroke.getWxyskzgxz_fcrq());
            et_tg.setText(followUpStroke.getWxyskzgxz_tg());
            et_tc.setText(followUpStroke.getWxyskzgxz_tc());
            et_ldl.setText(followUpStroke.getWxyskzgxz_ldl());
            et_hdl.setText(followUpStroke.getWxyskzgxz_hdl());
            et_lp.setText(followUpStroke.getWxyskzgxz_LP());
            try {
                List<Medication> lists = JsonUtil.jsonToObjects(
                        followUpStroke.getWxyskzgxz_yyqk(), Medication.class);
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
		this.fragmentManager=fragmentManager;
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
